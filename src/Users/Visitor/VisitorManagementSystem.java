package Users.Visitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import NotificationSystem.Event;
import NotificationSystem.NotificationService;
import Tickets.ItemAbstract;
import Tickets.ItemFactory;
import Tickets.Subscription;
import Tickets.Ticket;
import Tickets.ItemFactory.ItemType;
import Tickets.Types.SubscriptionType;
import Tickets.Types.TicketType;
import UI.Input;
import UI.Output;
import UI.Logger.LogLevel;
import UI.Logger.Logger;
import Users.Worker.Worker;
import interfaces.ItemTypeInterface;
import interfaces.VisitorSystemInterface;

public class VisitorManagementSystem implements VisitorSystemInterface {
	
	private static 				VisitorSystemInterface _instance;
	private Set<Visitor> 		visitors;
	private Set<ItemAbstract> 	tickets;
	private Set<ItemAbstract> 	issuedTickets;
	private static Worker 		workerLoggedIn;
	
	private VisitorManagementSystem() {
		visitors = new HashSet<Visitor>();
		tickets = new HashSet<ItemAbstract>();
		issuedTickets = new HashSet<ItemAbstract>();
	}
	
	public static synchronized VisitorSystemInterface getInstance() {
		if (_instance == null) {
			_instance = new VisitorManagementSystem();
		}
		return _instance;
	}

	@Override
	public boolean issueTicket(Ticket ticket) {
		
		if(Output.isNull(ticket, "Ticket")) {
			return false;
		}
		
		if(ticket.isAlreadyUsed()) {
			Logger.log("Ticket is already used. please buy new ticket");
			return false;
		}
			
		if(ticket.isCancelled()) {
			Logger.log("Ticket is cancelled. please buy new ticket");
			return false;
		}
			
		
		ticket.setAlreadyUsed(true);
		return issuedTickets.add(ticket);
	}
	
	@Override
	public boolean issueSubscription(Subscription subscription) {
		
		if(Output.isNull(subscription, "subscription")) {
			return false;
		}
		
			
		if(subscription.isCancelled()) {
			Logger.log("Ticket is cancelled. please buy new ticket");
			return false;
		}
		
		if(LocalDate.now().isAfter(subscription.getExpiryDate())) {
			Logger.log("The subscription date is expired");
			return false;
		}
			
			
		
		subscription.setAlreadyUsed(true);
		issuedTickets.add(subscription);
		return true;
	}
	
	@Override
	public boolean buyTicket() {
		
		Visitor visitor = VisitorIdentification();
		if(visitor != null) {
			Ticket ticket = (Ticket) ItemFactory.getItem(ItemType.Ticket, workerLoggedIn.getUsername(), visitor.getId());
			return tickets.add(ticket);
		}
		return false;
	}
	
	
	@Override
	public boolean buySubscription() {	
		Visitor visitor = VisitorIdentification();

		if(visitor != null) {
			Subscription subscription = (Subscription) ItemFactory.getItem(ItemType.Subscription, workerLoggedIn.getUsername(), visitor.getId());
			return tickets.add(subscription);
		}

		return true;
	}
	
	public Visitor VisitorIdentification() {
		int id = Input.getIDFromUser();
		Visitor visitor = getVisitorByID(id);
		
		if (Output.isNull(visitor, "Visitor")) {
			Output.execute(addNewVisitor(id),"Create new Visitor", "");
			visitor = getVisitorByID(id);
		}
			
		return visitor;
		
	}
	
	
    @Override
	public int getTicketsCount() {
        return getTickets().size();
    }
    
    @Override
	public int getSubscriptionsCount() {
    	return getSubscriptions().size();
    }
    
    @Override
	public Ticket findTicketByVisitorID(int visitorID) {
    	Ticket ticket = null;
    	for (Ticket t : getTickets()) {
    		if (t.getVisitorID() == visitorID) {
    			if(t.isAvailable())
    				return t;
    			else
    				ticket = t;
    		}
    			
    	}
    	return ticket;
    	
    }
    
    @Override
	public Subscription findSubscriptionByVisitorID(int visitorID) {
    	
    	for (Subscription s : getSubscriptions()) {
        		if (s.getVisitorID() == visitorID || ((Subscription)s).getSecondVisitorID() == visitorID)
        			return s;
    	}
    	return null;
    	
    }
    
	public List<Ticket> getTickets() {
		return tickets.stream().filter(ticket -> ticket instanceof Ticket).map(ticket->(Ticket)ticket).collect(Collectors.toList());
	}

	public List<Subscription> getSubscriptions() {
		return tickets.stream().filter(subscribe -> subscribe instanceof Subscription).map(subscribe->(Subscription)subscribe).collect(Collectors.toList());
	}
	

	@Override
	public String getLastPurchasedItem() {
		return tickets.stream().toList().get(getTicketsCount() - 1).toString();
	}

	@Override
	public boolean cancelSubscription()  {
		int id = Input.getIDFromUser();
		Subscription sub = findSubscriptionByVisitorID(id);
		
    	if (sub == null) {
    		Logger.log("Subscription not found");
    		return false;
    	}
    	if(sub.isAlreadyUsed()) {
    		Logger.log("Subscription is already used");
    		return false;
    	}
    	
    	if(sub.isCancelled()) {
    		Logger.log("Subscription is already cancelled");
    		return true;
    	}
    		
    	sub.setCancelled(true);
    	return sub.isCancelled();
	}
	
    @Override
	public boolean cancelTicket()  {
    	int id = Input.getIDFromUser();
    	Ticket ticket = findTicketByVisitorID(id);
    	
    	if (Output.isNull(ticket, "Ticket")) {
    		return false;
    	}
    	    	if (ticket == null) {
    		Logger.log("Ticket - not found");
    		return false;
    	}
    	if(ticket.isAlreadyUsed()) {
    		Logger.log("Ticket is already used");
    		return false;
    	}
    		
    	if(ticket.isCancelled())
    		Logger.log("Ticket is already cancelled");
    	
    	
    	ticket.setCancelled(true);
    	return ticket.isCancelled();
    }
    
    public Visitor getVisitorByID(int visitorID) {
    	for (Visitor v : visitors) {
    		if (v.getId() == visitorID)
    			return v;
    	}
    	return null;
    }


	@Override
	public String getPurchaseHistory(int visitorID, LocalDate purchaseDate) {
		List<String> purchaseHistory = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (Ticket t : getTickets()) {
			if (t.getVisitorID() == visitorID && t.getPurchaseDate().equals(purchaseDate))
				purchaseHistory.add(t.toString());
		}
		
		for (Subscription s : getSubscriptions()) {
			if ((s.getVisitorID() == visitorID || s.getSecondVisitorID() == visitorID) && s.getPurchaseDate().equals(purchaseDate))
				purchaseHistory.add(s.toString());
		}
		
		
		if(purchaseHistory.size() == 0)		
			return null;
		
		for (String h : purchaseHistory) {
			sb.append(h + "\n");
		}
		
		return sb.toString();
	}

	
	private boolean addNewVisitor(int id) {
		Logger.log("New Visitor Details: ");

		String firstName = Input.getName("Enter your first name");
		String lastName = Input.getName("Enter your last name");
		LocalDate birthdate = Input.getPastDate("Birthdate");
		String phone = Input.getPhoneNumber();
		return visitors.add(new Visitor(id, firstName, lastName, birthdate, phone));
	}
	
	@Override
	public void removeSubscriptionType() {
		SubscriptionType.printCustomSubscriptionTypes();
		int typeIndex = SubscriptionType.chooseCustomType();
		ItemTypeInterface type = SubscriptionType.getType(typeIndex);
		if(SubscriptionType.getCustomTypes().remove(type)) {
			Logger.log("Subscription: " + type.getName() + " removed successfully");
			NotificationService.getService().notify(Event.WORKER_MESSAGE,workerLoggedIn.getUsername() + "removed Subscription: " + type.getName());
		}
			
		else
			Logger.log("Remove Subscription - failed");
	}

	@Override
	public void removeTicketType() {
		TicketType.printCustomTicketTypes();
		int typeIndex = TicketType.chooseCustomType();
		ItemTypeInterface type = TicketType.getType(typeIndex);
		if(TicketType.getCustomTypes().remove(type)) {
			Logger.log("Ticket: " + type.getName() + " removed successfully");
			NotificationService.getService().notify(Event.WORKER_MESSAGE,workerLoggedIn.getUsername() + "removed Ticket: " + type.getName());
		}
		else
			Logger.log("Remove Ticket - failed", LogLevel.ERROR);
		}


	@Override
	public Worker getWorkerLoggedIn() {
		return workerLoggedIn;
	}

	public void setWorkerLoggedIn(Worker workerLoggedIn) {
		VisitorManagementSystem.workerLoggedIn = workerLoggedIn;
	}


	
	public Set<ItemAbstract> getIssuedTickets() {
		return issuedTickets;
	}


}

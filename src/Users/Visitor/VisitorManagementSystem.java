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
import Tickets.Subscription;
import Tickets.Ticket;
import Tickets.Types.SubscriptionType;
import Tickets.Types.TicketType;
import UI.Input;
import UI.Output;
import Users.Worker.Worker;
import interfaces.ItemTypeInterface;
import interfaces.VisitorSystemInterface;

public class VisitorManagementSystem implements VisitorSystemInterface {
	
	private static 				VisitorSystemInterface _instance;
	private Set<Visitor> 		visitors;
	private Set<ItemAbstract> 	tickets;
	private Set<ItemAbstract> 	issuedTickets;
	private static Worker 		workerLoggedIn;
	private String 				selledBy;
	
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
			System.out.println("Ticket is already used. please buy new ticket");
			return false;
		}
			
		if(ticket.isCancelled()) {
			System.out.println("Ticket is cancelled. please buy new ticket");
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
			System.out.println("Ticket is cancelled. please buy new ticket");
			return false;
		}
		
		if(LocalDate.now().isAfter(subscription.getExpiryDate())) {
			System.out.println("The subscription date is expired");
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
			ItemTypeInterface type = TicketType.chooseTicketType();
			Ticket ticket = new Ticket(visitor.getId(), type, selledBy);
			return tickets.add(ticket);
		}
		return false;
	}
	
	
	@Override
	public boolean buySubscription() {	
		Visitor visitor = VisitorIdentification();

		if(visitor != null) {
			Subscription subscription;
			ItemTypeInterface type = SubscriptionType.chooseSubscriptionType();
			
			if (type.getName().contains("couple")) {
				
				Visitor secondVisitor = VisitorIdentification();
				if (secondVisitor == null)
					return false;
				subscription = new Subscription(type, visitor.getId(), secondVisitor.getId(), selledBy);
			}
			else
				subscription = new Subscription(type, visitor.getId(), selledBy);
			
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
    		System.out.println("Subscription not found");
    		return false;
    	}
    	if(sub.isAlreadyUsed()) {
    		System.out.println("Subscription is already used");
    		return false;
    	}
    	
    	if(sub.isCancelled()) {
    		System.out.println("Subscription is already cancelled");
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
    		System.out.println("Ticket - not found");
    		return false;
    	}
    	if(ticket.isAlreadyUsed()) {
    		System.out.println("Ticket is already used");
    		return false;
    	}
    		
    	if(ticket.isCancelled())
    		System.out.println("Ticket is already cancelled");
    	
    	
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
		System.out.println("New Visitor Details: ");

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
			System.out.println("Subscription: " + type.getName() + " removed successfully");
			NotificationService.getService().notify(Event.WORKER_MESSAGE,workerLoggedIn.getUsername() + "removed Subscription: " + type.getName());
		}
			
		else
			System.out.println("Remove Subscription - failed");
	}

	@Override
	public void removeTicketType() {
		TicketType.printCustomTicketTypes();
		int typeIndex = TicketType.chooseCustomType();
		ItemTypeInterface type = TicketType.getType(typeIndex);
		if(TicketType.getCustomTypes().remove(type)) {
			System.out.println("Ticket: " + type.getName() + " removed successfully");
			NotificationService.getService().notify(Event.WORKER_MESSAGE,workerLoggedIn.getUsername() + "removed Ticket: " + type.getName());
		}
		else
			System.out.println("Remove Ticket - failed");
		}


	@Override
	public Worker getWorkerLoggedIn() {
		return workerLoggedIn;
	}

	public void setWorkerLoggedIn(Worker workerLoggedIn) {
		VisitorManagementSystem.workerLoggedIn = workerLoggedIn;
		this.setSelledBySignature(workerLoggedIn.getFirstName() +" "+ workerLoggedIn.getLastName() + " Worker ID: "+ workerLoggedIn.getWorkerId());
	}


	public void setSelledBySignature(String selledBy) {

		this.selledBy = selledBy;
	}

	
	public Set<ItemAbstract> getIssuedTickets() {
		return issuedTickets;
	}


}

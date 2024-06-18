package Users.Visitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import IO.Input;
import Tickets.Subscription;
import Tickets.Ticket;
import Tickets.Types.SubscriptionType;
import Tickets.Types.TicketType;
import Users.Worker.Worker;
import zoo.ExceptionZoo;

public class VisitorManagementSystem implements VisitorSystemInterface {
	private static VisitorSystemInterface _instance;
	private Set<Visitor> visitors;
	private Set<Ticket> tickets;
	private Set<Subscription> subscriptions;
	private static Worker workerLoggedIn;
	private String servedBySignature;
	
	private VisitorManagementSystem() {
		visitors = new HashSet<Visitor>();
		tickets = new HashSet<Ticket>();
		subscriptions = new HashSet<Subscription>();
		
	}
	
	public static synchronized VisitorSystemInterface getInstance() {
		if (_instance == null) {
			_instance = new VisitorManagementSystem();
		}
		return _instance;
	}

	public synchronized boolean issueTicket(Ticket ticket) {
		
		if(isNull(ticket, "Ticket")) {
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
		return true;
	}
	
	public synchronized boolean issueSubscription(Subscription subscription) {
		
		if(isNull(subscription, "subscription")) {
			return false;
		}
		
		if(subscription.isAlreadyUsed()) {
			System.out.println("Ticket is already used. please buy new ticket");
			return false;
		}
			
		if(subscription.isCancelled()) {
			System.out.println("Ticket is cancelled. please buy new ticket");
			return false;
		}
			
		
		subscription.setAlreadyUsed(true);
		return true;
	}
	
	@Override
	public synchronized boolean buyTicket() throws Exception {
		
		Visitor visitor = VisitorIdentification();
		if(visitor != null) {
			TicketType type = chooseTicketType();
			LocalDate  date = Input.getTicketDate();
			Ticket ticket = new Ticket(visitor.getId(), type, date);
			return tickets.add(ticket);
		}
		return false;
	}
	
	
	@Override
	public synchronized boolean buySubscription() throws Exception {	
		Visitor visitor = VisitorIdentification();

		if(visitor != null) {
			Subscription subscription;
			SubscriptionType type = chooseSubscriptionType();
			
			if (type.getName().contains("couple")) {
				
				Visitor secondVisitor = VisitorIdentification();
				if (secondVisitor == null)
					return false;
				subscription = new Subscription(type, visitor.getId(), secondVisitor.getId());
			}
			else
				subscription = new Subscription(type, visitor.getId());
			
			return subscriptions.add(subscription);
		}

		return true;
	}
	
	public Visitor VisitorIdentification() throws Exception {
		int id = Input.getIDFromUser();
		Visitor visitor = getVisitorByID(id);
		
		if (isNull(visitor, "Visitor")) {
			execute(addNewVisitor(id),"Create new Visitor", "");
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
    	for (Ticket t : tickets) {
    		if (t.getVisitorID() == visitorID) {
    			if(t.isAvailable())
    				return t;
    			else
    				ticket = t;
    		}
    			
    	}
    	return ticket;
    	
    }
    
	public Subscription findSubscriptionByVisitorID(int visitorID) {
    	
    	for (Subscription s : subscriptions) {
    		if (s.getVisitorID() == visitorID || s.getSecondVisitorID() == visitorID)
    			return s;
    	}
    	return null;
    	
    }
    
	public List<Ticket> getTickets() {
		return tickets.stream().toList();
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions.stream().toList();
	}

	public Ticket getLastPurchasedTicket() {
		return getTickets().get(getTicketsCount() -1);
	}

	public Subscription getLastPurchasedSubscription() {
		return getSubscriptions().get(getSubscriptionsCount() -1);
	}

	@Override
	public boolean cancelSubscription() throws Exception {
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
    		
    	return sub.isCancelled();
	}
	
    @Override
	public boolean cancelTicket() throws Exception {
    	int id = Input.getIDFromUser();
    	Ticket ticket = findTicketByVisitorID(id);
    	
    	if (isNull(ticket, "Ticket")) {
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
		for (Ticket t : tickets) {
			if (t.getVisitorID() == visitorID && t.getPurchaseDate().equals(purchaseDate))
				purchaseHistory.add(t.toString());
		}
		
		for (Subscription s : subscriptions) {
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

	public TicketType chooseTicketType() throws Exception {
		
		TicketType type = TicketType.NULL;
		
		while (type == TicketType.NULL) {
			printTicketTypes();
			int typeChoice = Input.getNumberInRange(1, TicketType.getLastIndex());
			type = TicketType.getType(typeChoice);
			if (type != TicketType.NULL)
				System.out.println("Ticket type: "+ type.getName() + " Price: "+ type.getPrice() + " ILS");
		}
		
		return type;
	}
	
	private SubscriptionType chooseSubscriptionType() throws Exception {
		
		SubscriptionType type = SubscriptionType.NULL;
		while (type == SubscriptionType.NULL) {
			printSubscriptionTypes();
			int typeChoice = Input.getNumberInRange(1, SubscriptionType.getLastIndex());
			type = SubscriptionType.getType(typeChoice);
			if (type != SubscriptionType.NULL)
				System.out.println("Ticket type: "+ type.getName() + " Price: "+ type.getPrice() + " ILS");
		}
		
		return type;
	}
	
	private void printTicketTypes() {
		int i = 1;
		for (TicketType type : TicketType.values()) {
			if (!type.equals(TicketType.NULL))
				System.out.println(type.getIndex() + ". " + type.getName() + ":\t" + type.getPrice() + " ILS");
		}
	}
	
	private void printSubscriptionTypes() {
		int i = 1;
		for (SubscriptionType type : SubscriptionType.values()) {
			if (!type.equals(SubscriptionType.NULL))
				System.out.println(type.getIndex()+ ". " +type.getName() + ": \t" + type.getPrice() + " ILS");
		}
	}
	
	private boolean addNewVisitor(int id) throws Exception {
		System.out.println("New Visitor Details: ");

		String firstName = Input.getName("Enter your first name:");
		String lastName = Input.getName("Enter your last name:");
		LocalDate birthdate = Input.getPastDate("Birthdate");
		String phone = Input.getPhoneNumber();
		return visitors.add(new Visitor(id, firstName, lastName, birthdate, phone));
	}
	
	@Override
	public void exit() {
		System.out.println("Back to login menu");
		
	}
	
	private int getMenuChoice(int min, int max) {
		int choice = -1;
		
		while (choice == -1) {
			try {
				choice = Input.getNumberInRange(min, max);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		return choice;
	}

	private void printMenu() {
		System.out.println("Visitors management system menu:");
		System.out.println("1. Buy ticket");
		System.out.println("2. Buy Subscription");
		System.out.println("3. Cancel Ticket");
		System.out.println("4. Cancel Subscription");
		System.out.println("5. Find ticket by ID");
		System.out.println("6. Print purchase history by visitor ID & Date");
		System.out.println("7. Issue Ticket");
		System.out.println("8. Issue Subscriber");
		System.out.println("0. log-out");
	}
	
	public void mainMenu(Worker worker) throws Exception, ExceptionZoo {
		this.setWorkerLoggedIn(worker);
		this.setServedBySignature(worker.getFirstName() + worker.getLastName() + "worker ID: "+worker.getWorkerId());
		
		while (true) {
			printMenu();
			int choice = getMenuChoice(0,8);
			switch (choice) {
				case 0: {
					exit();
					return;
				}
				case 1:{
					execute(buyTicket(), "Ticket purchasing", getLastPurchasedTicket().toString());
					break;
				}
				case 2:{
					execute(buySubscription(), "Subscription purchasing", getLastPurchasedSubscription().toString()); 
					break;
				}
				case 3:{
					execute(cancelTicket(), "Cancel Ticket", "");
					break;
				}
				case 4:{
					execute(cancelSubscription(), "Cancel Subscription", "");
					break;
				}
				case 5:{
						isNull(findTicketByVisitorID(Input.getIDFromUser()), "Ticket");
					break;
				}
				case 6:{
						int id = Input.getIDFromUser();
						LocalDate purchaseDate = Input.getPastDate("Purchase date");
						isNull(getPurchaseHistory(id, purchaseDate), "Purchase history for ID " + id);
					break;
				}
				case 7:{
					
					execute(issueTicket(findTicketByVisitorID(Input.getIDFromUser())), "Issue Ticket", "Enjoy your visit");
					break;
				}	
				case 8:{
					execute(issueSubscription(findSubscriptionByVisitorID(Input.getIDFromUser())), "Issue Subscription", "Enjoy your visit");
					
					break;
				}	
			}
		}
	
		
	}
	

	public static void execute(boolean res, String description, String successDetails) {
		if (res)
			System.out.println(description + " done successfully\n" + successDetails);
		else
			System.out.println(description + " failed");
	}
	
	public static boolean isNull(Object obj, String description) {
		if(obj == null) {
			System.out.println(description + " - Not found");
			return true;
		}
			System.out.println(obj.toString());
			return false;
	}

	public static Worker getWorkerLoggedIn() {
		return workerLoggedIn;
	}

	public void setWorkerLoggedIn(Worker workerLoggedIn) {
		VisitorManagementSystem.workerLoggedIn = workerLoggedIn;
	}

	public String getServedBySignature() {
		return servedBySignature;
	}

	public void setServedBySignature(String servedBySignature) {
		this.servedBySignature = servedBySignature;
	}
	

}

package Tickets;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import IO.Input;
import Tickets.Types.SubscriptionType;
import Tickets.Types.TicketType;

public class TicketSystem {
	private static TicketSystem _instance;
	private int ammount = 0;
	private List<Ticket> tickets;
	private List<Subscription> subscriptions;
	
	private TicketSystem() {
		tickets 		= new ArrayList<Ticket>();
		subscriptions = new ArrayList<Subscription>();
	}
	
	public static synchronized TicketSystem getInstance() {
		if (_instance == null) {
			_instance = new TicketSystem();
		}
		return _instance;
	}

	public synchronized void issueTicket(Ticket ticket) {
		ticket.setAlreadyUsed(true);
	}
	
	public synchronized boolean buyTicket() throws Exception {
		int 	   id 	= Input.getNineDigitNumber();
		TicketType type = chooseTicketType();
		LocalDate  date = Input.getTicketDate();
		
		Ticket ticket = new Ticket(id, type, date);
		
		boolean passed = tickets.add(ticket);
		if(passed)
			ammount += ticket.getPrice();
		return passed;
	}
	
	
	public synchronized boolean buySubscription() throws Exception {
		Subscription subscription;
		int id = Input.getNineDigitNumber();
		SubscriptionType type = chooseSubscriptionType();
		
		if (type.getName().contains("couple")) {
			System.out.println("Second visitor");
			int idSecond = Input.getNineDigitNumber();
			subscription = new Subscription(type, id, idSecond);
		}
		else
			subscription = new Subscription(type, id);
		
		boolean passed = subscriptions.add(subscription);
		if(passed)
			ammount += subscription.getPrice();
		return passed;
	}
	
	public synchronized boolean buySubscription(SubscriptionType subscriptionType, int visitorID, int secondVisitorID) {
		Subscription subscription = new Subscription(subscriptionType, visitorID, secondVisitorID);
		return subscriptions.add(subscription);
	}

	
    public int getTicketsCount() {
        return tickets.size();
    }
    
    public int getSubscriptionsCount() {
    	return subscriptions.size();
    }
    
    public Ticket findTicketByVisitorID(int visitorID) {
    	for (Ticket ticket : tickets) {
    		if (ticket.getVisitorID() == visitorID)
    			return ticket;
    	}
    	return null;
    }
    
    public boolean cancelTicket() throws Exception {
    	Ticket ticket = findTicketByVisitorID(Input.getNineDigitNumber());
    	if (ticket == null) {
    		System.out.println("Ticket not found");
    		return false;
    	}
    	if(ticket.isAlreadyUsed()) {
    		System.out.println("Ticket is already used");
    		return false;
    	}
    		
    	boolean canceled = tickets.remove(ticket);
    	if (canceled)
    		ammount -= ticket.getPrice();
    	
    	return canceled;
    	
    }
    

	public List<Ticket> getTickets() {
		return tickets;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}
	
	public Ticket getLastPurchasedTicket() {
		return tickets.get(getTicketsCount() -1);
	}
	
	public Subscription getLastPurchasedSubscription() {
		return subscriptions.get(getSubscriptionsCount() -1);
	}

	public boolean cancelSubscription() throws Exception {
		Subscription sub = findTicketBySubscriptionByVisitorID(Input.getNineDigitNumber());
    	if (sub == null) {
    		System.out.println("Subscription not found");
    		return false;
    	}
    	if(sub.isAlreadyUsed()) {
    		System.out.println("Subscription is already used");
    		return false;
    	}
    		
    	boolean canceled = subscriptions.remove(sub);
    	if (canceled)
    		ammount -= sub.getPrice();
    	
    	return canceled;
	}

	private Subscription findTicketBySubscriptionByVisitorID(int nineDigitNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPurchaseHistory(int visitorID) {
		List<Ticket> ticketHistory = new ArrayList<Ticket>();
		StringBuilder sb = new StringBuilder();
		for (Ticket t : tickets) {
			if (t.getVisitorID() == visitorID)
				ticketHistory.add(t);
		}
		
		if(ticketHistory.size() == 0)		
			return null;
		
		for (Ticket t : ticketHistory) {
			sb.append(t.toString() + "\n");
		}
		
		return sb.toString();
	}
	
	public String getPurchaseHistory(LocalDate date) {
		List<Ticket> ticketHistory = new ArrayList<Ticket>();
		StringBuilder sb = new StringBuilder();
		for (Ticket t : tickets) {
			if (t.getPurchaseDate().equals(date))
				ticketHistory.add(t);
		}
		
		if(ticketHistory.size() == 0)		
			return null;
		
		for (Ticket t : ticketHistory) {
			sb.append(t.toString() + "\n");
		}
		
		return sb.toString();
	}

	
	public TicketType chooseTicketType() {
		
		TicketType type = TicketType.NULL;
		
		while (type == TicketType.NULL) {
			printTicketTypes();
			String typeChoice = Input.getTicketType();
			type = TicketType.getByName(typeChoice);
			if (type != TicketType.NULL)
				System.out.println("Ticket type: "+ type.getName() + " Price: "+ type.getPrice() + " ILS");
		}
		
		return type;
	}
	
	private SubscriptionType chooseSubscriptionType() {
		SubscriptionType type = SubscriptionType.NULL;
		while (type == SubscriptionType.NULL) {
			printSubscriptionTypes();
			String typeChoice = Input.getTicketType();
			type = SubscriptionType.getByName(typeChoice);
			if (type != SubscriptionType.NULL)
				System.out.println("Ticket type: "+ type.getName() + " Price: "+ type.getPrice() + " ILS");
		}
		
		return type;
	}
	
	private void printTicketTypes() {
		int i = 1;
		for (TicketType type : TicketType.values()) {
			if (!type.equals(TicketType.NULL) && !type.equals(TicketType.SUBSCRIBER))
				System.out.println((i++)+ ". " + type.getName() + ":\t" + type.getPrice() + " ILS");
		}
	}
	
	private void printSubscriptionTypes() {
		int i = 1;
		for (SubscriptionType type : SubscriptionType.values()) {
			if (!type.equals(SubscriptionType.NULL))
				System.out.println((i++)+ ". " +type.getName() + ": \t" + type.getPrice() + " ILS");
		}
	}
}

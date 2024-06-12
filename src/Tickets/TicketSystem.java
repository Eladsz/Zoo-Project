package Tickets;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketSystem {
	private static TicketSystem _instance;
	private List<Ticket> issuedTickets;
	private List<Subscription> issuedSubscriptions;
	
	private TicketSystem() {
		issuedTickets = new ArrayList<Ticket>();
		issuedSubscriptions = new ArrayList<Subscription>();
	}
	
	public static synchronized TicketSystem getInstance() {
		if (_instance == null) {
			_instance = new TicketSystem();
		}
		return _instance;
	}

	public synchronized Ticket issueTicket(TicketType ticketType, int visitorID, LocalDate date, boolean cancelationable) {
		Ticket ticket = new Ticket(visitorID, ticketType, date, cancelationable);
		issuedTickets.add(ticket);
		return ticket;
	}
	
	
	public synchronized Subscription issueSubscription(SubscriptionType subscriptionType, int visitorID, boolean cancelationable) {
		Subscription subscription = new Subscription(subscriptionType, visitorID,  cancelationable);
		issueTicket(TicketType.SUBSCRIPTION, visitorID, subscription.getExpiryDate(), cancelationable);
		issuedSubscriptions.add(subscription);
		return subscription;
	}
	
	public synchronized Subscription issueSubscription(SubscriptionType subscriptionType, int visitorID,int secondVisitorID, boolean cancelationable) {
		Subscription subscription = new Subscription(subscriptionType, visitorID,  	secondVisitorID, cancelationable);
		issueTicket(TicketType.SUBSCRIPTION, visitorID, subscription.getExpiryDate(), cancelationable);
		issueTicket(TicketType.SUBSCRIPTION, secondVisitorID, subscription.getExpiryDate(), cancelationable);
		issuedSubscriptions.add(subscription);
		return subscription;
	}
	
    public int getTicketCount() {
        return issuedTickets.size();
    }
    
    public int getSubscriptionCount() {
    	return issuedSubscriptions.size();
    }
}

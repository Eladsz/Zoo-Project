package interfaces;

import java.time.LocalDate;

import Tickets.Subscription;
import Tickets.Ticket;
import Users.Worker.Worker;

public interface VisitorSystemInterface {
	
	int getTicketsCount();
	int getSubscriptionsCount();
	boolean buyTicket() ;
	boolean buySubscription() ;
	Ticket findTicketByVisitorID(int visitorID);
	boolean cancelTicket() ;
	boolean cancelSubscription() ;
	String getPurchaseHistory(int visitorID, LocalDate date);
	String getLastPurchasedItem();
	boolean issueTicket(Ticket ticket);
	boolean issueSubscription(Subscription subscription);
	Subscription findSubscriptionByVisitorID(int visitorID);
	Worker getWorkerLoggedIn();
	void removeTicketType();
	void removeSubscriptionType();

}
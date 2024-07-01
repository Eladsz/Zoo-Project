package interfaces;

import java.time.LocalDate;
import Tickets.Ticket;

public interface VisitorSystemInterface {
	
	int getTicketsCount();
	int getSubscriptionsCount();
	boolean buyTicket() throws Exception;
	boolean buySubscription() throws Exception;
	Ticket findTicketByVisitorID(int visitorID);
	boolean cancelTicket() throws Exception;
	boolean cancelSubscription() throws Exception;
	String getPurchaseHistory(int visitorID, LocalDate date);
	void updateItemPrice() throws Exception;
	void removeItem();
	void AddNewItem() throws Exception;

}
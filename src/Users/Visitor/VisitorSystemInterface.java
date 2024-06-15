package Users.Visitor;

import java.time.LocalDate;

import Tickets.Ticket;
import Tickets.Types.SubscriptionType;
import Tickets.Types.TicketType;

public interface VisitorSystemInterface {
	
	public void    mainMenu() throws Exception;
	public boolean buyTicket(TicketType type);
	public boolean buySubscription(SubscriptionType type);
	public boolean cancelTicket(int visitorID);
	public boolean cancelSubscription(int visitorID);
	public Ticket findTicket(int visitorID);
	public void printPurchaseHistory(int visitorID);
	public void printPurchaseHistory(LocalDate date);
	public void exit();
}

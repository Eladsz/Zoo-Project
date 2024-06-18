package Users.Visitor;

import java.time.LocalDate;
import Tickets.Ticket;
import Users.Worker.Worker;
import zoo.ExceptionZoo;

public interface VisitorSystemInterface {
	
	int getTicketsCount();

	int getSubscriptionsCount();

	boolean buyTicket() throws Exception;

	boolean buySubscription() throws Exception;

	Ticket findTicketByVisitorID(int visitorID);

	boolean cancelTicket() throws Exception;

	boolean cancelSubscription() throws Exception;

	String getPurchaseHistory(int visitorID, LocalDate date);

	void exit();

	void mainMenu(Worker worker) throws Exception, ExceptionZoo;


}
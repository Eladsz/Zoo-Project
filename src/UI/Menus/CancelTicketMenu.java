package UI.Menus;

import UI.Output;
import Users.Visitor.VisitorManagementSystem;

public class CancelTicketMenu extends Menu{

	public CancelTicketMenu() {
		super("Cancel Ticket Menu");
		AddOption("Cancel Ticket", 									v-> Output.execute(VisitorManagementSystem.getInstance().cancelTicket(), "Ticket Cancel", ""));
		AddOption("Cancel Subscription", 							v-> Output.execute(VisitorManagementSystem.getInstance().cancelSubscription(), "Subscription Cancel", ""));
	}

}

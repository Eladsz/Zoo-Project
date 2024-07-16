package UI.Menus;

import UI.Output;
import Users.Visitor.VisitorManagementSystem;

public class BuyTicketMenu extends Menu {

	public BuyTicketMenu() {
		super("Buy Ticket Menu");
		AddOption("Buy Ticket", 		v-> Output.execute(VisitorManagementSystem.getInstance().buyTicket(), "Ticket purchasing", VisitorManagementSystem.getInstance().getLastPurchasedItem()));
		AddOption("Buy Subscription", 	v-> Output.execute(VisitorManagementSystem.getInstance().buySubscription(), "Subscription purchasing" ,VisitorManagementSystem.getInstance().getLastPurchasedItem()));
	}
	
}

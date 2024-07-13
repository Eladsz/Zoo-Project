
package UI.Menus;
import UI.Input;
import UI.Output;
import UI.Menus.MenuFactory.MenuType;
import Users.Visitor.VisitorManagementSystem;
public class MainVisitorSystemMenu extends Menu {

	public MainVisitorSystemMenu() {
		super("Main Menu", "Log-out");
		AddOption("Buy Ticket", 									v-> Output.execute(VisitorManagementSystem.getInstance().buyTicket(), "Ticket purchasing", VisitorManagementSystem.getInstance().getLastPurchasedItem()));
		AddOption("Buy Subscription", 								v-> Output.execute(VisitorManagementSystem.getInstance().buySubscription(), "Subscription purchasing" ,VisitorManagementSystem.getInstance().getLastPurchasedItem()));
		AddOption("Cancel Ticket", 									v-> Output.execute(VisitorManagementSystem.getInstance().cancelTicket(), "Ticket Cancel", ""));
		AddOption("Cancel Subscription", 							v-> Output.execute(VisitorManagementSystem.getInstance().cancelSubscription(), "Subscription Cancel", ""));
		AddOption("Find ticket by ID", 								v-> Output.isNull(VisitorManagementSystem.getInstance().findTicketByVisitorID(Input.getIDFromUser()), "Ticket"));
		AddOption("Print purchase history by visitor ID & Date", 	v-> Output.isNull(VisitorManagementSystem.getInstance().getPurchaseHistory(Input.getIDFromUser(), Input.getPastDate("Purchase date")), "Purchase history"));
		AddOption("Issue Ticket",									v-> Output.execute(VisitorManagementSystem.getInstance().issueTicket(VisitorManagementSystem.getInstance().findTicketByVisitorID(Input.getIDFromUser())), "Issue Ticket", "Enjoy your visit"));
		AddOption("Issue Subscriber",								v-> Output.execute(VisitorManagementSystem.getInstance().issueSubscription(VisitorManagementSystem.getInstance().findSubscriptionByVisitorID(Input.getIDFromUser())), "Issue Subscription", "Enjoy your visit"));
		AddOption("Add new Item", 									v-> MenuFactory.getMenu(MenuType.ADD_NEW_ITEM).mainMenu());
		AddOption("Remove Item", 									v-> MenuFactory.getMenu(MenuType.REMOVE_ITEM).mainMenu());
		AddOption("Update Item price", 								v-> MenuFactory.getMenu(MenuType.UPDATE_ITEM_PRICE).mainMenu());
	}
	
}

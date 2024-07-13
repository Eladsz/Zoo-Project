package UI.Menus;

import NotificationSystem.Event;
import NotificationSystem.NotificationService;
import Tickets.Types.SubscriptionType;
import Tickets.Types.TicketType;
import UI.Input;
import UI.Output;
import Users.Visitor.VisitorManagementSystem;

public class AddNewItemMenu extends Menu {
    private int price;
    private String name;
	public AddNewItemMenu() {
		super("Add new item menu");
		initValues();
		AddOption("Add New Ticket Type", 		v->Output.execute(TicketType.addCustomType(name, price),"Add new ticket type", ""));
		AddOption("Add New Subscription Type", 	v->Output.execute(SubscriptionType.addCustomType(name, price), "Add new subscription type",""));
	}
	
	public void initValues() {
		price = Input.getNumberInRange(0, Integer.MAX_VALUE,"Enter the price");
		name = Input.getString("New item name: ");
	}
	
	@Override
	public void AfterAction() {
		super.AfterAction();
		NotificationService.getService().notify(Event.NEW_ITEM, "New item added! " + name + " price: " + price);
		NotificationService.getService().notify(Event.WORKER_MESSAGE, VisitorManagementSystem.getInstance().getWorkerLoggedIn().getUsername() + " created new item => " + name + " price: " + price);
	}

}

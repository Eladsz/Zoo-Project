package UI.Menus;

import NotificationSystem.Event;
import NotificationSystem.NotificationService;
import Tickets.Types.SubscriptionType;
import Tickets.Types.TicketType;
import UI.Input;
import UI.Logger.Logger;
import interfaces.ItemTypeInterface;

public class UpdateItemMenu extends Menu {

	int newPrice;
	int oldPrice;
	ItemTypeInterface type = null;
	
	public UpdateItemMenu() {
		super("Update Item Price Menu");
		AddOption("Update Ticket Price", v -> updateTicket());
		AddOption("Update Subscription", v -> updateSubscription());
	}

	@Override
	public void AfterAction() {
		super.AfterAction();
		NotificationService.getService().notify(Event.ITEM_UPDATE, "Item updated: " + type.getName() + " price: "+ oldPrice + " changed to price: " + newPrice);
		if (newPrice < oldPrice) {
			NotificationService.getService().notify(Event.SALE, "New SALE!! " + type.getName() + " price changed from " + oldPrice + " to: " + newPrice);
		}
	}
	
	public void updateTicket() {
		type = TicketType.chooseTicketType();
		oldPrice = type.getPrice();

		newPrice = Input.getNumberInRange(0, 1000, "Enter new ticket price");
		type.setPrice(newPrice);
		Logger.log("Ticket updated: " + type.getName() + " " + type.getPrice() + " ILS");
	}
	
	public void updateSubscription() {
		type = SubscriptionType.chooseSubscriptionType();
		oldPrice = type.getPrice();
		newPrice = Input.getNumberInRange(0, 1000, "Enter new ticket price");
		type.setPrice(newPrice);
		Logger.log("Subscription updated: " + type.getName() + " " + type.getPrice() + " ILS");
	}
	


}

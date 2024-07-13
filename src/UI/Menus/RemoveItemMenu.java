package UI.Menus;

import Tickets.Types.SubscriptionType;
import Tickets.Types.TicketType;
import Users.Visitor.VisitorManagementSystem;

public class RemoveItemMenu extends Menu {
    boolean emptyCustomTicketTypes = false;
    boolean emptyCustomSubscriptionTypes = false;
	public RemoveItemMenu() {
		super("Remove Item Menu");
		keep = false;
		if(TicketType.getCustomTypes() != null && TicketType.getCustomTypes().size() > 0)
			AddOption("Remove custom ticket type", v->VisitorManagementSystem.getInstance().removeTicketType());
		else {
			System.out.println("Custom ticket types are empty");
			emptyCustomTicketTypes = true;
		}
		
		if(SubscriptionType.getCustomTypes() != null && SubscriptionType.getCustomTypes().size() > 0)
			AddOption("Remove custom subscription type", v->VisitorManagementSystem.getInstance().removeSubscriptionType());
		else {
			System.out.println("Custom Subscription types are empty");
			emptyCustomSubscriptionTypes = true;
		}
		
		if(isEmpty()) 
			System.out.println("There are not custom items to remove");
		
	}
	
	@Override
	public void mainMenu() {
		if(isEmpty()) 
			return;
		super.mainMenu();
	}
	
	public boolean isEmpty() {
		return emptyCustomSubscriptionTypes && emptyCustomTicketTypes;
	}
	
}

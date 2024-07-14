package Tickets;

import Tickets.Types.SubscriptionType;
import Tickets.Types.TicketType;
import UI.Input;
import UI.Logger.LogLevel;
import UI.Logger.Logger;

public class ItemFactory {
	
	public enum ItemType{
		Ticket,
		Subscription
	}
	
	public static ItemAbstract getItem(ItemType type, String LoggedInUsername, int visitorID) {
		
		switch(type) {
		case Ticket:
			TicketType tType = (TicketType)TicketType.chooseTicketType();
			return new Ticket(visitorID, tType, LoggedInUsername);
		case Subscription:
			SubscriptionType sType = (SubscriptionType) SubscriptionType.chooseSubscriptionType();
			if (sType.getName().contains("couple"))
				return new Subscription(sType, visitorID, Input.getIDFromUser(), LoggedInUsername);
			return new Subscription(sType, visitorID, LoggedInUsername);
		}
		
		Logger.log("ERROR: ItemFactory.getItem() - returns null",LogLevel.ERROR);
		return null;
	}
	

}
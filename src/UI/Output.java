package UI;

import Tickets.Types.SubscriptionType;
import Tickets.Types.TicketType;
import UI.Logger.LogLevel;
import UI.Logger.Logger;
import interfaces.ItemTypeInterface;

public class Output {
	public static void execute(boolean res, String description, String successDetails) {
		if (res)
			Logger.log(description + " done successfully\n" + successDetails);
		else
			Logger.log(description + " failed", LogLevel.ERROR);
	}
	
	public static boolean isNull(Object obj, String description) {
		if(obj == null) {
			Logger.log(description + " - Not found",LogLevel.ERROR);
			return true;
		}
			Logger.log(obj.toString());
			return false;
	}
	
	public static void printSubscriptionTypes() {
		for (SubscriptionType type : SubscriptionType.values()) {
			if (!type.equals(SubscriptionType.NULL))
				Logger.log(type.getIndex()+ ". " +type.getName() + ": \t" + type.getPrice() + " ILS");
		}
		for (ItemTypeInterface type : SubscriptionType.getCustomTypes()) {
				Logger.log(type.getIndex()+ ". " +type.getName() + ": \t" + type.getPrice() + " ILS");
		}
	}
	
	public static void printTicketTypes() {
		for (TicketType type : TicketType.values()) {
			if (!type.equals(TicketType.NULL))
				Logger.log(type.getIndex() + ". " + type.getName() + ":\t" + type.getPrice() + " ILS");
		}
		for (ItemTypeInterface type : TicketType.getCustomTypes()) {
				Logger.log(type.getIndex() + ". " + type.getName() + ":\t" + type.getPrice() + " ILS");
		}
	}
}

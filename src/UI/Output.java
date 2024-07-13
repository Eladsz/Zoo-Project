package UI;

import Tickets.Types.SubscriptionType;
import Tickets.Types.TicketType;
import interfaces.ItemTypeInterface;

public class Output {
	public static void execute(boolean res, String description, String successDetails) {
		if (res)
			System.out.println(description + " done successfully\n" + successDetails);
		else
			System.out.println(description + " failed");
	}
	
	public static boolean isNull(Object obj, String description) {
		if(obj == null) {
			System.out.println(description + " - Not found");
			return true;
		}
			System.out.println(obj.toString());
			return false;
	}
	
	public static void printSubscriptionTypes() {
		for (SubscriptionType type : SubscriptionType.values()) {
			if (!type.equals(SubscriptionType.NULL))
				System.out.println(type.getIndex()+ ". " +type.getName() + ": \t" + type.getPrice() + " ILS");
		}
		for (ItemTypeInterface type : SubscriptionType.getCustomTypes()) {
				System.out.println(type.getIndex()+ ". " +type.getName() + ": \t" + type.getPrice() + " ILS");
		}
	}
	
	public static void printTicketTypes() {
		for (TicketType type : TicketType.values()) {
			if (!type.equals(TicketType.NULL))
				System.out.println(type.getIndex() + ". " + type.getName() + ":\t" + type.getPrice() + " ILS");
		}
		for (ItemTypeInterface type : TicketType.getCustomTypes()) {
				System.out.println(type.getIndex() + ". " + type.getName() + ":\t" + type.getPrice() + " ILS");
		}
	}
}

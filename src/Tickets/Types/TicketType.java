package Tickets.Types;

import java.util.ArrayList;
import java.util.List;

import UI.Input;
import UI.Output;
import interfaces.ItemTypeInterface;

public enum TicketType implements ItemTypeInterface { 
	
	NULL          (0,"Null", 		999),
	CHILD		  (1,"Child", 		 54),
	ADULT		  (2,"Adult", 		 71),
	SENIOR		  (3,"Senior",		 54),
	POLICE        (4,"Police", 		 54),
	SOLDIER       (5,"Soldier", 	 54),
	STUDENT		  (6,"Student", 	 54),
	DISABLED      (7,"Disabled",     35);

	private String name;
	private int    price;
	private final int 	 index;
	private static List<CustomTicketType> customTypes = new ArrayList<>();
	
	TicketType(int index, String name, int price){
		this.name = name;
		this.price = price;
		this.index = index;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getPrice() {
		return price;
	}
	
	public static ItemTypeInterface getType(String name) {
		for (TicketType type : TicketType.values()) {
			if (type.name.equals(name))
				return type;
		}
		
		for (CustomTicketType type : getCustomTypes()) {
			if (type.getName().equals(name))
				return type;
		}
		return TicketType.NULL;
	}
	
	public static ItemTypeInterface getType(int index) {
		for (TicketType type : TicketType.values()) {
			if (type.index == index)
				return type;
		}
		
		for (CustomTicketType type : getCustomTypes()) {
			if (type.getIndex() == index)
				return type;
		}
		
		return TicketType.NULL;
	}
	
	@Override
	public int getIndex() {
		return index;
	}
	
	public static int getLastIndex() {
		return TicketType.values().length -1 + getCustomTypes().size();
	}
	
	public static boolean addCustomType(String name, int price) {
		return getCustomTypes().add(new CustomTicketType(getLastIndex() +1, name, price));
	}

	public static List<CustomTicketType> getCustomTypes() {
		return customTypes;
	}

	@Override
	public void setPrice(int newPrice) {
		this.price = newPrice;
		
	}

	@Override
	public void setName(String newName) {
		this.name = newName;
	}
	
	public static ItemTypeInterface chooseTicketType() {
		
		ItemTypeInterface type = TicketType.NULL;
		
		while (type == TicketType.NULL) {
			Output.printTicketTypes();
			int typeChoice = Input.getNumberInRange(1, TicketType.getLastIndex());
			type = TicketType.getType(typeChoice);
			if (type != TicketType.NULL)
				System.out.println("Ticket type: "+ type.getName() + " Price: "+ type.getPrice() + " ILS");
		}
		
		return type;
	}
	
	public static void printCustomTicketTypes() {
		System.out.println("Choose a ticket type: ");
		for (ItemTypeInterface type : TicketType.getCustomTypes()) {
		   System.out.println(type.getIndex() + ". " + type.getName() + " " + type.getPrice() + " ILS");
		}
	}
	
	public static int chooseCustomType()  {
		int lastIndex = customTypes.size() -1;
		int choice = -1;
		while (choice == -1) {
			try {
				choice = Input.getNumberInRange(customTypes.get(0).getIndex(), customTypes.get(lastIndex).getIndex());
				return choice;
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return choice;
	}

}



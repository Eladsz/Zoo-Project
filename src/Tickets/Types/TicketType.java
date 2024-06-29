package Tickets.Types;

import java.util.ArrayList;
import java.util.List;

public enum TicketType implements TypeInterface { 
	
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
	
	public static TypeInterface getType(String name) {
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
	
	public static TypeInterface getType(int index) {
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

}



package Tickets.Types;

public enum TicketType { 
	
	NULL          (0,"Null", 999),
	CHILD		  (1,"Child", 		 54),
	ADULT		  (2,"Adult", 		 71),
	SENIOR		  (3,"Senior",		 54),
	POLICE        (4,"Police", 54),
	SOLDIER       (5,"Soldier", 54),
	STUDENT		  (6,"Student", 		 54),
	DISABLED      (7,"Disabled", 		 35);

	private final String name;
	private final int    price;
	private final int 	 index;

	
	TicketType(int index, String name, int price){
		this.name = name;
		this.price = price;
		this.index = index;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public static TicketType getType(String name) {
		for (TicketType type : TicketType.values()) {
			if (type.name.equals(name))
				return type;
		}
		return TicketType.NULL;
	}
	
	public static TicketType getType(int index) {
		for (TicketType type : TicketType.values()) {
			if (type.index == index)
				return type;
		}
		return TicketType.NULL;
	}
	
	public int getIndex() {
		return index;
	}
	
	public static int getLastIndex() {
		return TicketType.values().length -1;
	}
	

}

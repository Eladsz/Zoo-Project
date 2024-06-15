package Tickets.Types;

public enum TicketType { 
	
	NULL          ("Null", 999),
	CHILD		  ("Child", 		 54),
	ADULT		  ("Adult", 		 71),
	SENIOR		  ("Senior",		 54),
	POLICE        ("Police", 54),
	SOLDIER       ("Soldier", 54),
	STUDENT		  ("Student", 		 54),
	DISABLED      ("Disabled", 		 35),
	SUBSCRIBER    ("Subscriber", 0);

	private final String name;
	private final int    price;

	
	TicketType(String name, int price){
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public static TicketType getByName(String name) {
		for (TicketType type : TicketType.values()) {
			if (type.name.equals(name))
				return type;
		}
		return TicketType.NULL;
	}
	
	public static TicketType getByPrice(int price) {
		for (TicketType type : TicketType.values()) {
			if (type.price == price)
				return type;
		}
		return TicketType.NULL;
	}

}

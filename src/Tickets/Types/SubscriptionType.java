package Tickets.Types;

public enum SubscriptionType {
	
	NULL          			(0, "Null", 999),
	CHILD					(1, "subscription child", 245),
	ADULT					(2, "subscription adult", 330),
	SENIOR					(3, "subscription senior", 245),
	POLICE					(4, "subscription police officer", 245),
	SOLDIER					(5, "subscription soldier", 245),
	STUDENT					(6, "subscription student", 245),
	DISABLED				(7, "subscription disabled", 245),
	COUPLE					(8, "subscription couple", 570),
	COUPLE_PLUS_ONE			(9, "subscription couple + 1", 690),
	COUPLE_PLUS_TWO			(10, "subscription couple + 2", 750),
	COUPLE_PLUS_THREE		(11, "subscription couple + 3", 810),
	COUPLE_PLUS_FOUR		(12, "subscription couple + 4", 860),
	COUPLE_PLUS_FIVE		(13, "subscription couple + 5", 915),
	COUPLE_PLUS_SIX_OR_MORE	(14, "subscription couple + 6 or more", 970),
	PARENT_PLUS_ONE			(15, "subscription parent + 1", 515),
	PARENT_PLUS_TWO			(16, "subscription parent + 2", 665),
	PARENT_PLUS_THREE		(17, "subscription parent + 3", 735),
	PARENT_PLUS_FOUR		(18, "subscription parent + 4", 755),
	PARENT_PLUS_FIVE		(19, "subscription parent + 5", 795),
	PARENT_PLUS_SIX_OR_MORE	(20, "subscription parent + 6 or more", 830);
	
	private final String name;
	private final int    price;
	private final int    index;
	
	SubscriptionType(int index, String name, int price){
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
	
	public static SubscriptionType getType(String name) {
		for (SubscriptionType type : SubscriptionType.values()) {
			if (type.name.equals(name))
				return type;
		}
		return SubscriptionType.NULL;
	}
	
	
	public static SubscriptionType getType(int index) {
		for (SubscriptionType type : SubscriptionType.values()) {
			if (type.index == index)
				return type;
		}
		return SubscriptionType.NULL;
	}
	public int getIndex() {
		return index;
	}
	
	public static int getLastIndex() {
		return SubscriptionType.values().length -1;
	}
	
	
}

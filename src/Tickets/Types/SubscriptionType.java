package Tickets.Types;

public enum SubscriptionType {
	
	CHILD					("subscription child", 245),
	ADULT					("subscription adult", 330),
	SENIOR					("subscription senior", 245),
	POLICE					("subscription police officer", 245),
	SOLDIER					("subscription soldier", 245),
	STUDENT					("subscription student", 245),
	DISABLED				("subscription disabled", 245),
	COUPLE					("subscription couple", 570),
	COUPLE_PLUS_ONE			("subscription couple + 1", 690),
	COUPLE_PLUS_TWO			("subscription couple + 2", 750),
	COUPLE_PLUS_THREE		("subscription couple + 3", 810),
	COUPLE_PLUS_FOUR		("subscription couple + 4", 860),
	COUPLE_PLUS_FIVE		("subscription couple + 5", 915),
	COUPLE_PLUS_SIX_OR_MORE	("subscription couple + 6 or more", 970),
	PARENT_PLUS_ONE			("subscription parent + 1", 515),
	PARENT_PLUS_TWO			("subscription parent + 2", 665),
	PARENT_PLUS_THREE		("subscription parent + 3", 735),
	PARENT_PLUS_FOUR		("subscription parent + 4", 755),
	PARENT_PLUS_FIVE		("subscription parent + 5", 795),
	PARENT_PLUS_SIX_OR_MORE	("subscription parent + 6 or more", 830);
	
	private final String name;
	private final int    price;
	
	SubscriptionType(String name, int price){
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
	
}

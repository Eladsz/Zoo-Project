package Tickets;

public class SubscriptionIDGenerator {
	private static 	SubscriptionIDGenerator _instance = null;
	private int 	id;
	
	private SubscriptionIDGenerator() {
		this.id = 0;
	}
	
	public static SubscriptionIDGenerator getInstance() {
		if (_instance == null) {
			_instance = new SubscriptionIDGenerator();
		}
		return _instance;
	}
	
	public int getID() {
		return id++;
	}
	
	@Override
	public String toString() {
		return "Subscription ID Generator [id = " + id + "]";
	}
}

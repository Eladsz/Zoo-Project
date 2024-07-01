package Tickets;

import java.time.LocalDate;

import interfaces.ItemTypeInterface;

public class Subscription extends ItemAbstract{
	
	private ItemTypeInterface subscriptionType;
	private int secondVisitorID;
	private LocalDate expiryDate;

	public Subscription(ItemTypeInterface type, int visitorID, String selledBy) {
		super(visitorID, type.getPrice(), "Subscription", type.getName(), selledBy);
		this.subscriptionType = type;
		this.visitorID = visitorID;
		this.expiryDate = LocalDate.now().plusYears(1);
	}
	
	public Subscription(ItemTypeInterface type, int visitorID, int secondVisitorID, String selledBy) {
		this(type, visitorID, selledBy);
		this.secondVisitorID = secondVisitorID;
	}

	@Override
	public String toString() {
		return "\n\nSubscription ID: " + getId() + "\n"
				+ "Subscription Type: " + getTypeName()+ "\n"
				+ "visitor ID: " + visitorID + "\n"
				+ "second Visitor ID: " + secondVisitorID + "\n"
				+ "Purchase Date:" + purchaseDate+ "\n"
				+ "Expiry Date: " + expiryDate + "\n"
				+ "price: " + price + "\n"
				+ "is already used? "  + isAlreadyUsed + "\n"
				+ "is cancelled? " + cancelled + "\n"
				+ "Selled by " + getSelledBy() + "\n\n";
	}

	public ItemTypeInterface getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(ItemTypeInterface subscriptionType) {
		this.subscriptionType = subscriptionType;
		
		setPrice(subscriptionType.getPrice());
		setTypeName(subscriptionType.getName());
	}


	public int getSecondVisitorID() {
		return secondVisitorID;
	}

	public void setSecondVisitorID(int secondVisitorID) {
		this.secondVisitorID = secondVisitorID;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}


	

}

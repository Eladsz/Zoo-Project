package Tickets;

import java.time.LocalDate;

import Tickets.Types.SubscriptionType;

public class Subscription extends ItemAbstract{
	
	private SubscriptionType subscriptionType;
	private int secondVisitorID;
	private LocalDate expiryDate;

	public Subscription(SubscriptionType subscriptionType, int visitorID) {
		super(visitorID, subscriptionType.getPrice(), "Subscription", subscriptionType.getName());
		this.subscriptionType = subscriptionType;
		this.visitorID = visitorID;
		this.expiryDate = LocalDate.now().plusYears(1);
	}
	
	public Subscription(SubscriptionType subscriptionType, int visitorID, int secondVisitorID) {
		this(subscriptionType, visitorID);
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
				+ "price: " + price
				+ "is already used?"  + isAlreadyUsed + "\n"
				+ "is cancelled? " + cancelled + "\n\n";
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
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

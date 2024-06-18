package Tickets;

import java.time.LocalDate;

import Tickets.Types.SubscriptionType;

public class Subscription extends ItemAbstract{
	
	private SubscriptionType subscriptionType;
	private int secondVisitorID;
	private LocalDate expiryDate;

	public Subscription(SubscriptionType subscriptionType, int visitorID, String selledBy) {
		super(visitorID, subscriptionType.getPrice(), "Subscription", subscriptionType.getName(), selledBy);
		this.subscriptionType = subscriptionType;
		this.visitorID = visitorID;
		this.expiryDate = LocalDate.now().plusYears(1);
	}
	
	public Subscription(SubscriptionType subscriptionType, int visitorID, int secondVisitorID, String selledBy) {
		this(subscriptionType, visitorID, selledBy);
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
				+ "is cancelled? " + cancelled + "\n"
				+ "Selled by " + getSelledBy() + "\n\n";
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

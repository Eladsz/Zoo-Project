package Tickets;

import java.time.LocalDate;

import Tickets.Types.SubscriptionType;
import Tickets.Types.IDGenerators.SubscriptionIDGenerator;

public class Subscription {
	
	private int subscriptionID;
	private SubscriptionType subscriptionType;
	private int visitorID;
	private int secondVisitorID;
	private LocalDate purchaseDate;
	private LocalDate expiryDate;
	private boolean isAlreadyUsed;
	private int price;
	private String subscriptionName;
	

	public Subscription(SubscriptionType subscriptionType, int visitorID) {
		this.subscriptionID = SubscriptionIDGenerator.getInstance().getID();
		this.subscriptionType = subscriptionType;
		this.visitorID = visitorID;
		this.isAlreadyUsed = false;
		this.purchaseDate = LocalDate.now();
		this.expiryDate = LocalDate.now().plusYears(1);
	}
	
	public Subscription(SubscriptionType subscriptionType, int visitorID, int secondVisitorID) {
		this(subscriptionType, visitorID);
		this.secondVisitorID = secondVisitorID;

	}

	@Override
	public String toString() {
		return "Subscription ID: " + subscriptionID + ", Subscription Type: " + subscriptionName
				+ ", visitor ID: " + visitorID + ", second Visitor ID: " + secondVisitorID + ", Purchase Date:" + purchaseDate
				+ ", Expiry Date: " + expiryDate + ", is Already Used?"  + isAlreadyUsed + ", price: " + price;
	}

	public int getSubscriptionID() {
		return subscriptionID;
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
		this.price = subscriptionType.getPrice();
		this.subscriptionName = subscriptionType.getName();
	}

	public int getVisitorID() {
		return visitorID;
	}

	public void setVisitorID(int visitorID) {
		this.visitorID = visitorID;
	}

	public int getSecondVisitorID() {
		return secondVisitorID;
	}

	public void setSecondVisitorID(int secondVisitorID) {
		this.secondVisitorID = secondVisitorID;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean isCancelationable() {
		return isAlreadyUsed;
	}

	public void setAllreadyUsed(boolean alreadyUsed) {
		this.isAlreadyUsed = alreadyUsed;
	}

	public int getPrice() {
		return price;
	}

	public String getSubscriptionName() {
		return subscriptionName;
	}

	public boolean isAlreadyUsed() {
		return isAlreadyUsed;
	}
	

}

package Tickets;

import java.time.LocalDate;

import Tickets.Types.IDGenerators.ItemIDGenerator;

public abstract class ItemAbstract {

	private int 			id;
	protected int 			visitorID;
	protected int 			price;
	protected LocalDate 	purchaseDate;
	protected boolean     	isAlreadyUsed;
	protected boolean       cancelled;
	private String 			description;
	private String    		typeName;
	
	public ItemAbstract(int visitorID, int price, String description, String typeName) {
		this.id = ItemIDGenerator.getInstance().getID();
		this.setDescription(description);
		this.setTypeName(typeName);
		this.visitorID = visitorID;
		this.price = price;
		this.purchaseDate = LocalDate.now();
		this.isAlreadyUsed = false;
		this.cancelled = false;
	}
	public int getId() {
		return id;
	}
	public int getVisitorID() {
		return visitorID;
	}
	public void setVisitorID(int visitorID) {
		this.visitorID = visitorID;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public boolean isAlreadyUsed() {
		return isAlreadyUsed;
	}
	public void setAlreadyUsed(boolean isAlreadyUsed) {
		this.isAlreadyUsed = isAlreadyUsed;
	}
	public boolean isCancelled() {
		return cancelled;
	}
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public boolean isAvailable() {
		return !cancelled && !isAlreadyUsed;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}

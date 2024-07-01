package Tickets;

import java.time.LocalDate;

import interfaces.ItemTypeInterface;

public class Ticket extends ItemAbstract {

	private ItemTypeInterface  	ticketType;

	public Ticket(int visitorID, ItemTypeInterface type, String selledBy) {
		super(visitorID, type.getPrice(), "Ticket" ,type.getName(), selledBy);
		this.setTicketType(type);
		this.setVisitorID(visitorID);
		this.purchaseDate = LocalDate.now();
		this.setAlreadyUsed(false);
		this.setCancelled(false);
	}
	

	@Override
	public String toString() {
		return "\n\nticket ID: " + getId() + "\n"
				+ "visitor ID: " + visitorID + "\n"
				+ "price: " + price + "\n"
				+ "purchase Date: "+ purchaseDate + "\n"
				+ "ticket Type: " + getTypeName() + "\n"
				+ "is already used? " + isAlreadyUsed + "\n"
				+ "is cancelled? " + cancelled + "\n"
				+ "Selled by " + getSelledBy() + "\n\n";
	}


	public ItemTypeInterface getTicketType() {
		return ticketType;
	}

	public void setTicketType(ItemTypeInterface type) {
		this.ticketType = type;
		setPrice(type.getPrice());
		this.setTypeName(type.getName());
	}
	



}

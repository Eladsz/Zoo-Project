package Tickets;

import java.time.LocalDate;

import Tickets.Types.TypeInterface;

public class Ticket extends ItemAbstract {

	private TypeInterface  	ticketType;

	public Ticket(int visitorID, TypeInterface type, String selledBy) {
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


	public TypeInterface getTicketType() {
		return ticketType;
	}

	public void setTicketType(TypeInterface type) {
		this.ticketType = type;
		setPrice(type.getPrice());
		this.setTypeName(type.getName());
	}
	



}

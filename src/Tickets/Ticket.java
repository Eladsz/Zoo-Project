package Tickets;

import java.time.LocalDate;

import Tickets.Types.TicketType;

public class Ticket extends ItemAbstract {

	private 	LocalDate 		date;
	private 	TicketType  	ticketType;

	public Ticket(int visitorID, TicketType ticketType, LocalDate date, String selledBy) {
		super(visitorID, ticketType.getPrice(), "Ticket" ,ticketType.getName(), selledBy);
		this.setTicketType(ticketType);
		this.setVisitorID(visitorID);
		this.purchaseDate = LocalDate.now();
		this.setDate(date);
		this.setAlreadyUsed(false);
		this.setCancelled(false);
	}
	

	@Override
	public String toString() {
		return "\n\nticket ID: " + getId() + "\n"
				+ "visitor ID: " + visitorID + "\n"
				+ "price: " + price + "\n"
				+ "purchase Date: "+ purchaseDate + "\n"
				+ "date: " + date + "\n"
				+ "ticket Type: " + getTypeName() + "\n"
				+ "is already used? " + isAlreadyUsed + "\n"
				+ "is cancelled? " + cancelled + "\n"
				+ "Selled by " + getSelledBy() + "\n\n";
	}



	public LocalDate getDate() {
		return date;
	}

	public boolean setDate(LocalDate date) {
		
		if (date.isBefore(LocalDate.now()))
			return false;
		
		this.date = date;
		return true;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
		setPrice(ticketType.getPrice());
		this.setTypeName(ticketType.getName());
	}
	



}

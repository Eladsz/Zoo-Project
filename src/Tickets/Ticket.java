package Tickets;

import java.time.LocalDate;

import Tickets.Types.TicketType;
import Tickets.Types.IDGenerators.TicketIDGenerator;

public class Ticket {
	
	
	private 	int 			ticketID;
	private 	int 			visitorID;
	protected 	int 			price;
	private 	LocalDate 		purchaseDate;
	private 	LocalDate 		date;
	private 	TicketType  	ticketType;
	protected 	String 	    	ticketName;
	private 	boolean     	cancelationable;
	
	public Ticket(int visitorID, TicketType ticketType, LocalDate date, boolean cancelationable) {
		this.ticketID = TicketIDGenerator.getInstance().getID();
		this.setTicketType(ticketType);
		this.setVisitorID(visitorID);
		this.purchaseDate = LocalDate.now();
		this.setDate(date);
		this.cancelationable = cancelationable;
	}
	

	@Override
	public String toString() {
		return "Ticket [ticketID=" + ticketID + ", visitorID=" + visitorID + ", price=" + price + ", purchaseDate="
				+ purchaseDate + ", date=" + date + ", ticketType=" + ticketType + ", ticketName=" + ticketName
				+ ", cancelationable=" + cancelationable + "]\n\n";
	}


	public int getID() {
		return ticketID;
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

	public LocalDate getPurchaseDate() {
		return purchaseDate;
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
		this.price = ticketType.getPrice();
		this.ticketName = ticketType.getName();
	}
	

	public String getTicketName() {
		return ticketName;
	}

	public boolean isCancelationable() {
		return cancelationable;
	}


}

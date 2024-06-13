package Users;

import java.time.LocalDate;

import Tickets.Ticket;

public class Visitor extends Person{
	
	Ticket ticket;
	
	public Visitor(int id, String firstName, String lastName, LocalDate birthDate, String phoneNumber, Ticket ticket) {
		super(id, firstName, lastName, birthDate, phoneNumber);
		this.ticket = ticket;
	}
	
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


}

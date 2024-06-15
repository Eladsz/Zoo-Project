package Users.Visitor;

import java.time.LocalDate;

import Tickets.Ticket;
import Users.Abstracts.Person;

public class Visitor extends Person{
	
	Ticket ticket;
	
	public Visitor(int id, String firstName, String lastName, LocalDate birthDate, String phoneNumber) {
		super(id, firstName, lastName, birthDate, phoneNumber);
	}
	


	@Override
	public String toString() {
		return "Visitor " +super.toString() + " ticket=" + ticket.toString() + "\n\n";
	}
	
	public void SetTicket(Ticket ticket) {
		this.ticket = ticket;
	}


	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


}

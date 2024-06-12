package Tickets;

public class TicketIDGenerator {
	private static 	TicketIDGenerator _instance = null;
	private int 	id;
	
	private TicketIDGenerator() {
		this.id = 0;
	}
	
	public static TicketIDGenerator getInstance() {
		if (_instance == null) {
			_instance = new TicketIDGenerator();
		}
		return _instance;
	}
	
	public int getID() {
		return id++;
	}
	
	@Override
	public String toString() {
		return "Ticket ID Generator [id = " + id + "]";
	}
}


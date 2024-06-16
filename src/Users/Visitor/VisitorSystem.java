package Users.Visitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import IO.Input;
import Tickets.Ticket;
import Tickets.TicketSystem;
import zoo.ExceptionZoo;
public class VisitorSystem {

	private static VisitorSystem _instance = null;
	private List<Visitor> visitors;
	
	private VisitorSystem(){
		visitors = new ArrayList<Visitor>();
	}
	
	public static VisitorSystem getInstance() {
		if(_instance == null) {
			_instance = new VisitorSystem();
		}
		return _instance;
	}
	
	public int getVisitorsCount() {
		return visitors.size();
	}
	
	public List<Visitor> getVisitors(){
		return visitors;
	}
	
	public List<Ticket> getVisitorsTicketsList(){
		return visitors.stream().map(Visitor::getTicket).collect(Collectors.toList());
	}


	public void exit() {
		System.out.println("Back to login menu");
		
	}

	public void mainMenu() throws Exception, ExceptionZoo {
		while (true) {
			printMenu();
			int choice = getMenuChoice();
			switch (choice) {
				case 0: {
					exit();
					return;
				}
				case 1:{
					execute(TicketSystem.getInstance().buyTicket(), "Ticket purchasing", TicketSystem.getInstance().getLastPurchasedTicket().toString());
					break;
				}
				case 2:{
					execute(TicketSystem.getInstance().buySubscription(), "Subscription purchasing", TicketSystem.getInstance().getLastPurchasedSubscription().toString()); 
					break;
				}
				case 3:{
					execute(TicketSystem.getInstance().cancelTicket(), "Cancel Ticket", "");
					break;
				}
				case 4:{
					execute(TicketSystem.getInstance().cancelSubscription(), "Cancel Subscription", "");
					break;
				}
				case 5:{
						checkNull(TicketSystem.getInstance().findTicketByVisitorID(Input.getNineDigitNumber()), "Ticket");
					break;
				}
				case 6:{
						int id = Input.getNineDigitNumber();
						checkNull(TicketSystem.getInstance().getPurchaseHistory(id), "Purchase history for ID " + id);
					break;
				}
				case 7:{
					LocalDate purchaseDate = Input.getPastDate("Purchase date");
					checkNull(TicketSystem.getInstance().getPurchaseHistory(purchaseDate), "Purchase history for date " + purchaseDate);
					break;
				}	
			}
		}
	
		
	}
	
	public static void execute(boolean res, String description, String successDetails) {
		if (res)
			System.out.println(description + " done successfully\n" + successDetails);
		else
			System.out.println(description + " failed");
	}
	
	public static void checkNull(Object obj, String description) {
		if(obj == null)
			System.out.println(description + " - Not found");
		else
			System.out.println(obj.toString());
	}

	


	private int getMenuChoice() {
		int choice = -1;
		
		while (choice == -1) {
			try {
				choice = Input.getNumberInRange(0, 7);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		return choice;
	}

	private void printMenu() {
		System.out.println("Visitors management system menu:");
		System.out.println("1. Buy ticket");
		System.out.println("2. Buy Subscription");
		System.out.println("3. Cancel Ticket");
		System.out.println("4. Cancel Subscription");
		System.out.println("5. Find ticket by ID");
		System.out.println("6. Print purchase history by visitor ID");
		System.out.println("7. Print purchase history by purchasing date");
		System.out.println("0. log-out");
	}
	
	
	
	private void addNewVisitor() throws Exception {
		System.out.println("Visitor Details: ");
		int id = Input.getNineDigitNumber();
		String firstName = Input.getName("Enter your first name:");
		String lastName = Input.getName("Enter your last name:");
		LocalDate birthdate = Input.getPastDate("Birthdate");
		String phone = Input.getPhoneNumber();
		
		visitors.add(new Visitor(id, firstName, lastName, birthdate, phone));
		
	}
	
	


}

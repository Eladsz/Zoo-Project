package Users.Visitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import IO.Input;
import Tickets.Ticket;
import Tickets.Types.SubscriptionType;
import Tickets.Types.TicketType;
import zoo.ExceptionZoo;
public class VisitorSystem implements VisitorSystemInterface {

	private static VisitorSystem _instance = null;
	private List<Visitor> visitors;
	private Scanner sc = new Scanner(System.in);
	
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
	
	public List<Ticket> getTicketsList(){
		return visitors.stream().map(Visitor::getTicket).collect(Collectors.toList());
	}



	@Override
	public boolean buyTicket(TicketType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buySubscription(SubscriptionType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelTicket(int visitorID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelSubscription(int visitorID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Ticket findTicket(int visitorID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printPurchaseHistory(int visitorID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printPurchaseHistory(LocalDate date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		System.out.println("Back to login menu");
		
	}

	@Override
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
					
			        TicketType type = TicketType.NULL;
			        while (type == TicketType.NULL) {
			        	printTicketTypes();
			        	String typeChoice = Input.getTicketType();
			        	System.out.println("DEBUG     " + typeChoice);
			        	if (typeChoice == "0")
			        		return;
			        	type = TicketType.getByName(typeChoice);
			        	if (type != TicketType.NULL)
			        		System.out.println("Ticket type: "+ type.getName() + " Price: "+ type.getPrice());
			        }
					addNewVisitor();
					
					break;
				}
				case 2:{
					break;
				}
				case 3:{
					break;
				}
				case 4:{
					break;
				}
				case 5:{
					break;
				}
				case 6:{
					break;
				}
				case 7:{
					break;
				}	
			}
		}
	
		
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
		System.out.println("7. Print purchase history");
		System.out.println("0. log-out");
	}
	
	private void addNewVisitor() throws Exception {
		System.out.println("Visitor Details: ");
		int id = Input.getNineDigitNumber();
		String firstName = Input.getName("Enter your first name:");
		String lastName = Input.getName("Enter your last name:");
		LocalDate birthdate = Input.getBirthdate();
		String phone = Input.getPhoneNumber();
		
		visitors.add(new Visitor(id, firstName, lastName, birthdate, phone));
		
	}
	
	private void printTicketTypes() {
		for (TicketType type : TicketType.values()) {
			if (!type.equals(TicketType.NULL))
				System.out.println(type.getName() + ": " + type.getPrice() + " ILS");
		}
	}
	


}

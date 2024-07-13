package Users.Visitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import NotificationSystem.Event;
import NotificationSystem.NotificationService;
import Tickets.ItemAbstract;
import Tickets.Subscription;
import Tickets.Ticket;
import Tickets.Types.CustomTicketType;
import Tickets.Types.SubscriptionType;
import Tickets.Types.TicketType;
import UserInput.Input;
import Users.Worker.Worker;
import interfaces.MenuInterface;
import interfaces.ItemTypeInterface;
import interfaces.VisitorSystemInterface;

public class VisitorManagementSystem implements VisitorSystemInterface, MenuInterface {
	
	private static 				VisitorSystemInterface _instance;
	private Set<Visitor> 		visitors;
	private Set<ItemAbstract> 	tickets;
	private Set<ItemAbstract> 	issuedTickets;
	private static Worker 		workerLoggedIn;
	private String 				selledBy;
	
	private VisitorManagementSystem() {
		visitors = new HashSet<Visitor>();
		tickets = new HashSet<ItemAbstract>();
		issuedTickets = new HashSet<ItemAbstract>();
	}
	
	public static synchronized VisitorSystemInterface getInstance() {
		if (_instance == null) {
			_instance = new VisitorManagementSystem();
		}
		return _instance;
	}

	public synchronized boolean issueTicket(Ticket ticket) {
		
		if(isNull(ticket, "Ticket")) {
			return false;
		}
		
		if(ticket.isAlreadyUsed()) {
			System.out.println("Ticket is already used. please buy new ticket");
			return false;
		}
			
		if(ticket.isCancelled()) {
			System.out.println("Ticket is cancelled. please buy new ticket");
			return false;
		}
			
		
		ticket.setAlreadyUsed(true);
		return issuedTickets.add(ticket);
	}
	
	public synchronized boolean issueSubscription(Subscription subscription) {
		
		if(isNull(subscription, "subscription")) {
			return false;
		}
		
			
		if(subscription.isCancelled()) {
			System.out.println("Ticket is cancelled. please buy new ticket");
			return false;
		}
		
		if(LocalDate.now().isAfter(subscription.getExpiryDate())) {
			System.out.println("The subscription date is expired");
			return false;
		}
			
			
		
		subscription.setAlreadyUsed(true);
		issuedTickets.add(subscription);
		return true;
	}
	
	@Override
	public synchronized boolean buyTicket() throws Exception {
		
		Visitor visitor = VisitorIdentification();
		if(visitor != null) {
			ItemTypeInterface type = chooseTicketType();
			Ticket ticket = new Ticket(visitor.getId(), type, selledBy);
			return tickets.add(ticket);
		}
		return false;
	}
	
	
	@Override
	public synchronized boolean buySubscription() throws Exception {	
		Visitor visitor = VisitorIdentification();

		if(visitor != null) {
			Subscription subscription;
			ItemTypeInterface type = chooseSubscriptionType();
			
			if (type.getName().contains("couple")) {
				
				Visitor secondVisitor = VisitorIdentification();
				if (secondVisitor == null)
					return false;
				subscription = new Subscription(type, visitor.getId(), secondVisitor.getId(), selledBy);
			}
			else
				subscription = new Subscription(type, visitor.getId(), selledBy);
			
			return tickets.add(subscription);
		}

		return true;
	}
	
	public Visitor VisitorIdentification() throws Exception {
		int id = Input.getIDFromUser();
		Visitor visitor = getVisitorByID(id);
		
		if (isNull(visitor, "Visitor")) {
			execute(addNewVisitor(id),"Create new Visitor", "");
			visitor = getVisitorByID(id);
		}
			
		return visitor;
		
	}
	
	
    @Override
	public int getTicketsCount() {
        return getTickets().size();
    }
    
    @Override
	public int getSubscriptionsCount() {
    	return getSubscriptions().size();
    }
    
    @Override
	public Ticket findTicketByVisitorID(int visitorID) {
    	Ticket ticket = null;
    	for (Ticket t : getTickets()) {
    		if (t.getVisitorID() == visitorID) {
    			if(t.isAvailable())
    				return t;
    			else
    				ticket = t;
    		}
    			
    	}
    	return ticket;
    	
    }
    
	public Subscription findSubscriptionByVisitorID(int visitorID) {
    	
    	for (Subscription s : getSubscriptions()) {
        		if (s.getVisitorID() == visitorID || ((Subscription)s).getSecondVisitorID() == visitorID)
        			return s;
    	}
    	return null;
    	
    }
    
	public List<Ticket> getTickets() {
		return tickets.stream().filter(ticket -> ticket instanceof Ticket).map(ticket->(Ticket)ticket).collect(Collectors.toList());
	}

	public List<Subscription> getSubscriptions() {
		return tickets.stream().filter(subscribe -> subscribe instanceof Subscription).map(subscribe->(Subscription)subscribe).collect(Collectors.toList());
	}

	public Ticket getLastPurchasedTicket() {
		return getTickets().get(getTicketsCount() -1);
	}

	public Subscription getLastPurchasedSubscription() {
		return getSubscriptions().get(getSubscriptionsCount() -1);
	}

	@Override
	public boolean cancelSubscription() throws Exception {
		int id = Input.getIDFromUser();
		Subscription sub = findSubscriptionByVisitorID(id);
		
    	if (sub == null) {
    		System.out.println("Subscription not found");
    		return false;
    	}
    	if(sub.isAlreadyUsed()) {
    		System.out.println("Subscription is already used");
    		return false;
    	}
    	
    	if(sub.isCancelled()) {
    		System.out.println("Subscription is already cancelled");
    		return true;
    	}
    		
    	sub.setCancelled(true);
    	return sub.isCancelled();
	}
	
    @Override
	public boolean cancelTicket() throws Exception {
    	int id = Input.getIDFromUser();
    	Ticket ticket = findTicketByVisitorID(id);
    	
    	if (isNull(ticket, "Ticket")) {
    		return false;
    	}
    	    	if (ticket == null) {
    		System.out.println("Ticket - not found");
    		return false;
    	}
    	if(ticket.isAlreadyUsed()) {
    		System.out.println("Ticket is already used");
    		return false;
    	}
    		
    	if(ticket.isCancelled())
    		System.out.println("Ticket is already cancelled");
    	
    	
    	ticket.setCancelled(true);
    	return ticket.isCancelled();
    }
    
    public Visitor getVisitorByID(int visitorID) {
    	for (Visitor v : visitors) {
    		if (v.getId() == visitorID)
    			return v;
    	}
    	return null;
    }


	@Override
	public String getPurchaseHistory(int visitorID, LocalDate purchaseDate) {
		List<String> purchaseHistory = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (Ticket t : getTickets()) {
			if (t.getVisitorID() == visitorID && t.getPurchaseDate().equals(purchaseDate))
				purchaseHistory.add(t.toString());
		}
		
		for (Subscription s : getSubscriptions()) {
			if ((s.getVisitorID() == visitorID || s.getSecondVisitorID() == visitorID) && s.getPurchaseDate().equals(purchaseDate))
				purchaseHistory.add(s.toString());
		}
		
		
		if(purchaseHistory.size() == 0)		
			return null;
		
		for (String h : purchaseHistory) {
			sb.append(h + "\n");
		}
		
		return sb.toString();
	}

	public ItemTypeInterface chooseTicketType() throws Exception {
		
		ItemTypeInterface type = TicketType.NULL;
		
		while (type == TicketType.NULL) {
			printTicketTypes();
			int typeChoice = Input.getNumberInRange(1, TicketType.getLastIndex());
			type = TicketType.getType(typeChoice);
			if (type != TicketType.NULL)
				System.out.println("Ticket type: "+ type.getName() + " Price: "+ type.getPrice() + " ILS");
		}
		
		return type;
	}
	
	private ItemTypeInterface chooseSubscriptionType() throws Exception {
		
		ItemTypeInterface type = SubscriptionType.NULL;
		while (type == SubscriptionType.NULL) {
			printSubscriptionTypes();
			int typeChoice = Input.getNumberInRange(1, SubscriptionType.getLastIndex());
			type = SubscriptionType.getType(typeChoice);
			if (type != SubscriptionType.NULL)
				System.out.println("Ticket type: "+ type.getName() + " Price: "+ type.getPrice() + " ILS");
		}
		
		return type;
	}
	
	private void printTicketTypes() {
		for (TicketType type : TicketType.values()) {
			if (!type.equals(TicketType.NULL))
				System.out.println(type.getIndex() + ". " + type.getName() + ":\t" + type.getPrice() + " ILS");
		}
		for (ItemTypeInterface type : TicketType.getCustomTypes()) {
				System.out.println(type.getIndex() + ". " + type.getName() + ":\t" + type.getPrice() + " ILS");
		}
	}
	
	private void printSubscriptionTypes() {
		for (SubscriptionType type : SubscriptionType.values()) {
			if (!type.equals(SubscriptionType.NULL))
				System.out.println(type.getIndex()+ ". " +type.getName() + ": \t" + type.getPrice() + " ILS");
		}
		for (ItemTypeInterface type : SubscriptionType.getCustomTypes()) {
				System.out.println(type.getIndex()+ ". " +type.getName() + ": \t" + type.getPrice() + " ILS");
		}
	}
	
	private boolean addNewVisitor(int id) throws Exception {
		System.out.println("New Visitor Details: ");

		String firstName = Input.getName("Enter your first name");
		String lastName = Input.getName("Enter your last name");
		LocalDate birthdate = Input.getPastDate("Birthdate");
		String phone = Input.getPhoneNumber();
		return visitors.add(new Visitor(id, firstName, lastName, birthdate, phone));
	}
	
	@Override
	public void exit() {
		System.out.println("Back to login menu");
		
	}
	
	private int getMenuChoice(int min, int max) {
		int choice = -1;
		
		while (choice == -1) {
			try {
				choice = Input.getNumberInRange(min, max);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		return choice;
	}

	@Override
	public void printMenu() {
		System.out.println("\n\n\n*********************************");
		System.out.println("Visitors management system menu:");
		System.out.println(" 1. Buy ticket");
		System.out.println(" 2. Buy Subscription");
		System.out.println(" 3. Cancel Ticket");
		System.out.println(" 4. Cancel Subscription");
		System.out.println(" 5. Find ticket by ID");
		System.out.println(" 6. Print purchase history by visitor ID & Date");
		System.out.println(" 7. Issue Ticket");
		System.out.println(" 8. Issue Subscriber");
		System.out.println(" 9. Add new Item");
		System.out.println("10. Remove Item");
		System.out.println("11. Update Item price");
		System.out.println(" 0. log-out");
		System.out.println("*********************************");
	}
	
	@Override
	public void mainMenu() throws Exception {
		
		while (true) {
			printMenu();
			int choice = getMenuChoice(0,11);
			switch (choice) {
				case 0: {
					exit();
					return;
				}
				case 1:{
					execute(buyTicket(), "Ticket purchasing", getLastPurchasedTicket().toString());
					break;
				}
				case 2:{
					execute(buySubscription(), "Subscription purchasing", getLastPurchasedSubscription().toString()); 
					break;
				}
				case 3:{
					execute(cancelTicket(), "Ticket Cancel", "");
					break;
				}
				case 4:{
					execute(cancelSubscription(), "Subscription Cancel", "");
					break;
				}
				case 5:{
						isNull(findTicketByVisitorID(Input.getIDFromUser()), "Ticket");
					break;
				}
				case 6:{
						int id = Input.getIDFromUser();
						LocalDate purchaseDate = Input.getPastDate("Purchase date");
						isNull(getPurchaseHistory(id, purchaseDate), "Purchase history for ID " + id);
					break;
				}
				case 7:{
					
					execute(issueTicket(findTicketByVisitorID(Input.getIDFromUser())), "Issue Ticket", "Enjoy your visit");
					break;
				}	
				case 8:{
					execute(issueSubscription(findSubscriptionByVisitorID(Input.getIDFromUser())), "Issue Subscription", "Enjoy your visit");
					
					break;
				}	
				case 9:{
					AddNewItem();
					
					break;
				}	
				case 10:{
					removeItem();
					break;
				}
				case 11: {
					updateItemPrice();
					break;
				}
			}
		}
	
		
	}

	@Override
	public void updateItemPrice() throws Exception {
		
		int oldPrice = 0;
		int newPrice = 0;
		ItemTypeInterface type = null;
		
		System.out.println("Choose item:");
		System.out.println("0. Return to main menu");
		System.out.println("1. Update ticket");
		System.out.println("2. Update subscription");
		int choice = getMenuChoice(0,2);
		switch (choice) {
			case 0:{
				return;
			}
			case 1: {
					type = chooseTicketType();
					oldPrice = type.getPrice();

					newPrice = Input.getNumberInRange(0, 1000, "Enter new ticket price");
					type.setPrice(newPrice);
					System.out.println("Ticket updated: " + type.getName() + " " + type.getPrice() + " ILS");
					
					break;
			}
			case 2:{
				type = chooseSubscriptionType();
				oldPrice = type.getPrice();
				newPrice = Input.getNumberInRange(0, 1000, "Enter new ticket price");
				type.setPrice(newPrice);
				System.out.println("Subscription updated: " + type.getName() + " " + type.getPrice() + " ILS");
				break;
			}
		}
		
		NotificationService.getService().notify(Event.ITEM_UPDATE, "Item updated: " + type.getName() + " price: "+ oldPrice + " changed to price: " + newPrice);
		if (newPrice < oldPrice) {
			NotificationService.getService().notify(Event.SALE, "New SALE!! " + type.getName() + " price changed from " + oldPrice + " to: " + newPrice);
		}
	}
	
	@Override
	public void AddNewItem() throws Exception {
		System.out.println("Choose item:");
		System.out.println("0. Return to main menu");
		System.out.println("1. Add new ticket type");
		System.out.println("2. Add new subscription type");
		int choice = getMenuChoice(0,2);
		String newItemName = Input.getString("New item name: ");
		int price = Input.getNumberInRange(0, Integer.MAX_VALUE,"Enter the price");
		
		switch (choice) {
			case 0:{
				return;
			}
			case 1: {
				
				execute(TicketType.addCustomType(newItemName, price),"Add new ticket type", "");
				break;
			}
			case 2:{
				execute(SubscriptionType.addCustomType(newItemName, price), "Add new subscription type","");
				break;
			}
		}
		
		NotificationService.getService().notify(Event.NEW_ITEM, "New item added! " + newItemName + " price: " + price);
		NotificationService.getService().notify(Event.WORKER_MESSAGE,workerLoggedIn.getUsername() + " created new item => " + newItemName + " price: " + price);
	}
	
	@Override
	public void removeItem() {
		System.out.println("Choose item:");
		System.out.println("0. Return to main menu");
		System.out.println("1. Remove custom ticket type");
		System.out.println("2. Remove custom subscription type");
		int choice = getMenuChoice(0,2);
		switch (choice) {
			case 0:{
				return;
			}
			case 1: {
				if(TicketType.getCustomTypes() != null && TicketType.getCustomTypes().size() > 0) {
					RemoveTicketType();
				}
				else
					System.out.println("Custom ticket types are empty");

				break;
			}
			case 2:{
				
				if(SubscriptionType.getCustomTypes() != null && SubscriptionType.getCustomTypes().size() > 0) {
					removeSubscriptionType();
				}				
				else
					System.out.println("Custom Subscription types are empty");

				break;
			}
		}
	}

	private void removeSubscriptionType() {
		printCustomSubscriptionTypes();
		int typeIndex = chooseCustomType(SubscriptionType.getCustomTypes());
		ItemTypeInterface type = SubscriptionType.getType(typeIndex);
		if(SubscriptionType.getCustomTypes().remove(type)) {
			System.out.println("Subscription: " + type.getName() + " removed successfully");
			NotificationService.getService().notify(Event.WORKER_MESSAGE,workerLoggedIn.getUsername() + "removed Subscription: " + type.getName());
		}
			
		else
			System.out.println("Remove Subscription - failed");
	}

	private void RemoveTicketType() {
		printCustomTicketTypes();
		int typeIndex = chooseCustomType(TicketType.getCustomTypes());
		ItemTypeInterface type = TicketType.getType(typeIndex);
		if(TicketType.getCustomTypes().remove(type)) {
			System.out.println("Ticket: " + type.getName() + " removed successfully");
			NotificationService.getService().notify(Event.WORKER_MESSAGE,workerLoggedIn.getUsername() + "removed Ticket: " + type.getName());
		}
		else
			System.out.println("Remove Ticket - failed");
		}

	private int chooseCustomType(List<CustomTicketType> customTypes)  {
		int lastIndex = customTypes.size() -1;
		int choice = -1;
		while (choice == -1) {
			try {
				choice = Input.getNumberInRange(customTypes.get(0).getIndex(), customTypes.get(lastIndex).getIndex());
				return choice;
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return choice;
	}

	private void printCustomTicketTypes() {
		System.out.println("Choose a ticket type: ");
		for (ItemTypeInterface type : TicketType.getCustomTypes()) {
		   System.out.println(type.getIndex() + ". " + type.getName() + " " + type.getPrice() + " ILS");
		}
	}
	
	private void printCustomSubscriptionTypes() {
		System.out.println("Choose a ticket type: ");
		for (ItemTypeInterface type : SubscriptionType.getCustomTypes()) {
		   System.out.println(type.getIndex() + ". " + type.getName() + " " + type.getPrice() + " ILS");
		}
	}
	

	public static void execute(boolean res, String description, String successDetails) {
		if (res)
			System.out.println(description + " done successfully\n" + successDetails);
		else
			System.out.println(description + " failed");
	}
	
	public static boolean isNull(Object obj, String description) {
		if(obj == null) {
			System.out.println(description + " - Not found");
			return true;
		}
			System.out.println(obj.toString());
			return false;
	}

	public static Worker getWorkerLoggedIn() {
		return workerLoggedIn;
	}

	public void setWorkerLoggedIn(Worker workerLoggedIn) {
		VisitorManagementSystem.workerLoggedIn = workerLoggedIn;
		this.setSelledBySignature(workerLoggedIn.getFirstName() +" "+ workerLoggedIn.getLastName() + " Worker ID: "+ workerLoggedIn.getWorkerId());
	}


	public void setSelledBySignature(String selledBy) {

		this.selledBy = selledBy;
	}

	
	public Set<ItemAbstract> getIssuedTickets() {
		return issuedTickets;
	}

	

}

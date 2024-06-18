package Users.Worker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import IO.Input;
import Users.Visitor.VisitorManagementSystem;

public class WorkersAuthenticationSystem implements WorkersAuthenticationInterface {

	private List<Worker> workers;
	private static WorkersAuthenticationSystem _instance;
	
	
	private WorkersAuthenticationSystem() {
		workers = new ArrayList<Worker>();
		workers.add(new Worker(1111, "admin", "admin", LocalDate.now(), "052-0000000", "admin", "admin")); // HARD_CODED Admin user
	}
	
	public static synchronized WorkersAuthenticationSystem getInstance() {
		if (_instance == null) {
			_instance = new WorkersAuthenticationSystem();
		}
		return _instance;
	}
	
	@Override
	public void mainMenu() throws Exception {
		
		PrintMainMenu();
		int choice = getMainMenuChoice();
		
		switch (choice) {
			case 1: {
				createNewWorkerAccount();
				mainMenu();
				break;
			}
			case 2:{
				Worker worker = login(Input.getString("Enter your username: "), Input.getString("Enter your password: "));
				if(worker != null) {
					 VisitorManagementSystem.getInstance().mainMenu(worker);
				}
					
				mainMenu();
			 break;
			}
			case 0:{
				exit();
				return;
			}
			default: {
				System.out.println("Back to main menu:");
				mainMenu();
			}
			
		}
		
	}

	private void PrintMainMenu() {
		System.out.println("1. Create new worker account");
		System.out.println("2. Log-in");
		System.out.println("0. Back");
		System.out.println("Please Enter your choice: ");
	}

	private int getMainMenuChoice() {
		int choice = -1;
		
		while (choice == -1) {
			try {
				choice = Input.getNumberInRange(0, 2);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		return choice;
	}


	@Override
	public Worker login(String username, String password) {
		if (isUsernameAlreadyExists(username))
		{
		    for (Worker w : workers) {
		    	if (w.authenticate(username, password)) {
		    		System.out.println("Hello, " + w.getFirstName() + " You are logged in!");
		    		return w;
		    	}
		    		
		    }
		}
		else
			System.out.println("This username is not found please try again");
		return null;
	}

	@Override
	public List<Account> getWorkersAccounts() {
		if (workers != null && workers.size() > 0)
			return workers.stream().map(Worker::getAccount).collect(Collectors.toList());
		return null;
	}



	@Override
	public void exit() {
      System.out.println();
      System.out.println("Back to main menu");
      System.out.println();
	}

	@Override
	public boolean isUsernameAlreadyExists(String username) {
		
		if (workers == null)
			return false;
		
		for (Worker w : workers) {
			if (w.getUsername().equals(username))
				return true;
		}
		return false;
	}

	@Override
	public boolean isIDAlreadyExists(int id) {
		if(workers.stream().map(Worker::getId).collect(Collectors.toList()).contains(id))
			return true;
		return false;
	}

	@Override
	public void createNewWorkerAccount() throws Exception {
		int id = enterWorkerID();
		String firstName = Input.getName("First Name");
		String lastName = Input.getName("Last Name");
		LocalDate birthDate = Input.getPastDate("Birthdate");
		String phone = Input.getPhoneNumber();
		String username = enterUsername();
		String password = Input.getString("Enter your password: ");
		
		boolean successfullyCreated = workers.add(new Worker(id, firstName, lastName, birthDate, phone, username, password));
		if (successfullyCreated)
			System.out.println("New worker account has been created: First name: " + firstName + " Last Name: " + lastName + " Username: "+ username);
		else 
			System.out.println("Failed to create new worker account - please try again");

	}

	private String enterUsername() {
		boolean exist = true;
		String username = "";
		
		while (exist) {
			username = Input.getString("Enter your username:");
			exist = isUsernameAlreadyExists(username);
			if (exist)
				System.out.println("username is already exist please try another one");
		}
		
		return username;
	}

	private int enterWorkerID() throws Exception {
		boolean exist = true;
		int id = 0;
		while (exist) {
			System.out.println("Please enter your ID:");
			id = Input.getIDFromUser();
			if(!isIDAlreadyExists(id))
				exist = false;
			else
			{
				System.out.print("ID is already exist.");
				System.out.print("0. Back");
				System.out.print("1. Retry");
				int choise = Input.getNumberInRange(0, 1);
				if (choise == 0)
					mainMenu();
			}	
		}
		return id;
	}

}

package Users.Worker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import UI.Input;
import UI.Logger.LogLevel;
import UI.Logger.Logger;
import UI.Menus.MenuFactory;
import UI.Menus.MenuFactory.MenuType;
import Users.Visitor.VisitorManagementSystem;
import interfaces.AuthenticationSystemInterface;

public class WorkersAuthenticationSystem implements AuthenticationSystemInterface {

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
	public void Login() {
		Worker worker = validateWorker(Input.getString("Enter your username:"), Input.getString("Enter your password:"));
		if(worker != null) {
			((VisitorManagementSystem) VisitorManagementSystem.getInstance()).setWorkerLoggedIn(worker);
			MenuFactory.getMenu(MenuType.MAIN).mainMenu();
		}
	}



	@Override
	public Worker validateWorker(String username, String password) {
		if (isUsernameAlreadyExists(username))
		{
		    for (Worker w : workers) {
		    	if (w.validateUsernameAndPassowrd(username, password)) {
		    		Logger.log("Hello, " + w.getFirstName() + " You are logged in!");
		    		return w;
		    	}
		    		
		    }
		}
		else
			Logger.log("This username is not found please try again", LogLevel.ERROR);
		return null;
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
	public void createNewAccount() {
		int id = enterWorkerID();
		if (id == 0)
			return;
		String firstName = Input.getName("First Name");
		String lastName = Input.getName("Last Name");
		LocalDate birthDate = Input.getPastDate("Birthdate");
		String phone = Input.getPhoneNumber();
		String username = enterUsername();
		String password = Input.getString("Enter your password: ");
		
		boolean successfullyCreated = workers.add(new Worker(id, firstName, lastName, birthDate, phone, username, password));
		if (successfullyCreated)
			Logger.log("New worker account has been created: First name: " + firstName + " Last Name: " + lastName + " Username: "+ username);
		else 
			Logger.log("Failed to create new worker account - please try again", LogLevel.ERROR);

	}

	private String enterUsername() {
		boolean exist = true;
		String username = "";
		
		while (exist) {
			username = Input.getString("Enter your username:");
			exist = isUsernameAlreadyExists(username);
			if (exist)
				Logger.log("username is already exist please try another one");
		}
		
		return username;
	}

	private int enterWorkerID() {
		boolean exist = true;
		int id = 0;
		while (exist) {
			Logger.log("Please enter your ID:");
			id = Input.getIDFromUser();
			if(!isIDAlreadyExists(id))
				exist = false;
			else
			{
				Logger.log("ID is already exist.");
				Logger.log("0. Back");
				Logger.log("1. Retry");
				int choise = Input.getNumberInRange(0, 1);
				if (choise == 0)
					return choise;
			}	
		}
		return id;
	}

}

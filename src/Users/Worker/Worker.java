package Users.Worker;

import java.time.LocalDate;

import NotificationSystem.Event;
import NotificationSystem.MobileAppListener;
import NotificationSystem.NotificationService;
import Users.Abstracts.Person;

public class Worker extends Person{

	private final int workerId;
	private String username;
	private String password;


	public Worker(int id, String firstName, String lastName, LocalDate birthDate, String phoneNumber, String username, String password) {
		super(id, firstName, lastName, birthDate, phoneNumber);
		this.workerId = WorkerIDGenerator.getInstance().getID();
		this.username = username;
		this.password = password;
		NotificationService.getService().subscribe(Event.WORKER_MESSAGE, new MobileAppListener(username));
		NotificationService.getService().subscribe(Event.NEW_ITEM, new MobileAppListener(username));
		NotificationService.getService().subscribe(Event.SALE, new MobileAppListener(username));
		NotificationService.getService().subscribe(Event.ITEM_UPDATE, new MobileAppListener(username));
		
	}
	

	public int getWorkerId() {
		return workerId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public boolean changePassowrd(String username, String NewPassword, String oldPassword) {
		if(validateUsernameAndPassowrd(username, oldPassword)) {
			this.password = NewPassword;
			return true;
		}
		
		return false;
			
	}
	
	public boolean changeUsername(String username, String password, String newUsername) {
		if  (validateUsernameAndPassowrd(username, newUsername)) {			
			this.username = newUsername;
			return true;
		}
		return false;
	}
	
	
	
	public boolean validateUsernameAndPassowrd(String username, String passowrd) {
		if(this.username.equals(username) && this.password.equals(passowrd)) {			
			return true;
		}
		return false;
	}


}

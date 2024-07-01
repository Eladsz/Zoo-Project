package Users.Worker;

import java.time.LocalDate;

import NotificationSystem.Event;
import NotificationSystem.MobileAppListener;
import NotificationSystem.NotificationService;
import Users.Abstracts.Person;

public class Worker extends Person{

	private final int workerId;
	private Account account;


	public Worker(int id, String firstName, String lastName, LocalDate birthDate, String phoneNumber, String username, String password) {
		super(id, firstName, lastName, birthDate, phoneNumber);
		this.workerId = WorkerIDGenerator.getInstance().getID();
		account = new Account(username, password);
		NotificationService.getService().subscribe(Event.WORKER_MESSAGE, new MobileAppListener(username));
		NotificationService.getService().subscribe(Event.NEW_ITEM, new MobileAppListener(username));
		NotificationService.getService().subscribe(Event.SALE, new MobileAppListener(username));
		NotificationService.getService().subscribe(Event.ITEM_UPDATE, new MobileAppListener(username));
		
	}
	

	public int getWorkerId() {
		return workerId;
	}
	
	public String getUsername() {
		return account.getUsername();
	}
	
	public boolean changePassowrd(String username, String NewPassword, String oldPassword) {
		return account.setPassword(username, oldPassword, NewPassword);
	}
	
	public boolean changeUsername(String username, String password, String newUsername) {
		return account.setUsername(username, password, newUsername);
	}
	
	public boolean authenticate(String username, String password) {
		return account.authenticate(username, password);
	}
	
	public Account getAccount() {
		return account;
	}


}

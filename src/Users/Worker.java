package Users;

import java.time.LocalDate;

public class Worker extends Person{
	
	private static int worker = 1;
	private final int workerId;
	private Account account;


	public Worker(int id, String firstName, String lastName, LocalDate birthDate, String phoneNumber, String username, String password) {
		super(id, firstName, lastName, birthDate, phoneNumber);
		this.workerId = worker++;
		account = new Account(username, password);
		
	}
	

	public int getWorkerId() {
		return workerId;
	}
	
	public String getUsername() {
		return account.getUserName();
	}
	
	public boolean changePassowrd(String NewPassword, String oldPassword) {
		return account.setPassword(oldPassword, NewPassword);
	}
	
	public boolean changeUsername(String newUsername, String password) {
		return account.setUserName(newUsername, password);
	}


}

package interfaces;

import java.util.List;

import Users.Worker.Account;
import Users.Worker.Worker;

public interface AuthenticationSystemInterface {
	
	public void    			createNewAccount() throws Exception;
	public Worker 			login(String username, String password);
	public List<Account> 	getAccounts();
	public boolean 			isUsernameAlreadyExists(String username);
	public boolean 			isIDAlreadyExists(int id);
}

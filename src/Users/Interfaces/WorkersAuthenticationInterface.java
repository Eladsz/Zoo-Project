package Users.Interfaces;

import java.util.List;

import Users.Worker.Account;
import Users.Worker.Worker;

public interface WorkersAuthenticationInterface {
	public void    mainMenu() throws Exception;
	public void    createNewWorkerAccount() throws Exception;
	public Worker login(String username, String password);
	public List<Account> getWorkersAccounts();
	public boolean isUsernameAlreadyExists(String username);
	public boolean isIDAlreadyExists(int id);
	public void exit();
}

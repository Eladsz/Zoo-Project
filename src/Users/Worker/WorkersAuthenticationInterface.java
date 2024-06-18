package Users.Worker;

import java.util.List;

public interface WorkersAuthenticationInterface {
	public void    mainMenu() throws Exception;
	public void    createNewWorkerAccount() throws Exception;
	public Worker login(String username, String password);
	public List<Account> getWorkersAccounts();
	public boolean isUsernameAlreadyExists(String username);
	public boolean isIDAlreadyExists(int id);
	public void exit();
}

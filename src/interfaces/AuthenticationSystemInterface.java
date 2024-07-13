package interfaces;

import Users.Worker.Worker;

public interface AuthenticationSystemInterface {
	
	public void    			createNewAccount() throws Exception;
	public void 			Login();
	public boolean 			isUsernameAlreadyExists(String username);
	public boolean 			isIDAlreadyExists(int id);
	public Worker 			validateWorker(String username, String password);
}

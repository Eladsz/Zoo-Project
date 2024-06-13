package Users;

import java.security.KeyPair;

public class Account {
	
	private KeyPair data;
	private String userName;
	private String password;
	
	public Account(String userName, String password) {
		this.userName =  userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public boolean setUserName(String userName, String password) {
		if (authenticate(password)) {
			this.userName = userName;
			return true;
		}
		
		return false;
			
	}


	public boolean setPassword(String oldPassword,String newPassword) {
		if (authenticate(oldPassword)) {
			this.password = newPassword;
			return true;
		}
			
		return false;
	}
	
	public boolean authenticate(String password) {
		return this.password.equals(password);
	}
	
	
}

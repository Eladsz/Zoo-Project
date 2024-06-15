package Users.Worker;

import java.util.Objects;

public class Account {
	
	private Pair<String, String> userDetails;
	
	public Account(String userName, String password) {
		userDetails = new Pair<String, String>(userName, password);
	}

	public String getUsername() {
		return userDetails.getKey();
	}

	public boolean setUsername(String username, String password, String newUsername) {
		if (authenticate(username,password)) {
			userDetails.setKey(newUsername);
			return true;
		}
		
		return false;
			
	}


	@Override
	public int hashCode() {
		return Objects.hash(userDetails);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(userDetails.getKey(), other.userDetails.getKey());
	}

	public boolean setPassword(String username ,String oldPassword,String newPassword) {
		if (authenticate(username ,oldPassword)) {
			userDetails.setValue(newPassword);
			return true;
		}
			
		return false;
	}
	
	public boolean authenticate(String username ,String password) {
		return this.userDetails.getKey().equals(username) && this.userDetails.getValue().equals(password);
	}
	
	
}

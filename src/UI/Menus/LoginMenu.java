package UI.Menus;

import Users.Worker.WorkersAuthenticationSystem;

public class LoginMenu extends Menu {

	public LoginMenu() {
		super("Log-in Menu");
		AddOption("Create New Worker Account", v-> WorkersAuthenticationSystem.getInstance().createNewAccount());
		AddOption("Log-In",  v-> WorkersAuthenticationSystem.getInstance().Login());
	}

}

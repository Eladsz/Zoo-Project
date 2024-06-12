package visitor;

import java.util.Scanner;

public class VisitorSystem {

	private Scanner scan = new Scanner(System.in);

	public void logIn() {

		boolean access = false;

		System.out.println("Welcome to the visitor management system");

		do {

			System.out.println("If you have not registered to the system please enter 0, to exit enter -1");
			
			System.out.print("user name: ");
			String userName = scan.nextLine();
			System.out.println(userName);
			if(userName.equals("-1")) return; //Exit system
			else if (userName.equals("0")) {
				registerUser();
				continue;
			}
			System.out.println("password: ");
			String password = scan.nextLine();
			
			access = true;//authentication(userName, password);
		
			
		

		} while (!access);
	}

	private void registerUser() {
		// TODO Auto-generated method stub
		
	}

}

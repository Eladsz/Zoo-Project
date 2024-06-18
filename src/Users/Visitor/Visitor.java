package Users.Visitor;

import java.time.LocalDate;

import Users.Abstracts.Person;

public class Visitor extends Person{

	public Visitor(int visitorID ,String firstName, String lastName, LocalDate birthDate, String phoneNumber) {
		super(visitorID, firstName, lastName, birthDate, phoneNumber);
	}
	
}

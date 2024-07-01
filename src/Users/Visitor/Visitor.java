package Users.Visitor;

import java.time.LocalDate;

import NotificationSystem.Event;
import NotificationSystem.NotificationService;
import NotificationSystem.SMSListener;
import Users.Abstracts.Person;

public class Visitor extends Person{

	public Visitor(int visitorID ,String firstName, String lastName, LocalDate birthDate, String phoneNumber) {
		super(visitorID, firstName, lastName, birthDate, phoneNumber);
		NotificationService.getService().subscribe(Event.SALE, new SMSListener(firstName, lastName, phoneNumber));
		NotificationService.getService().subscribe(Event.NEW_ITEM, new SMSListener(firstName, lastName, phoneNumber));
		NotificationService.getService().subscribe(Event.CUSTOMER_MESSAGE, new SMSListener(firstName, lastName, phoneNumber));
	}
	
}

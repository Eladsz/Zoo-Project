package NotificationSystem;

public record SMSListener(String firstName, String lastName, String phoneNumber) implements Listener {

	@Override
	public void update(Event eventType) {
		System.out.println("Sending SMS message to: " + firstName + " " + lastName + " phone: " + phoneNumber + " concerning "+ eventType + ": " + eventType.getMsg());
	}

	
}

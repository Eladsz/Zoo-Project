package NotificationSystem;

import UI.Logger.LogLevel;
import UI.Logger.Logger;

public record SMSListener(String firstName, String lastName, String phoneNumber) implements Listener {

	@Override
	public void update(Event eventType) {
		Logger.log("Sending SMS message to: " + firstName + " " + lastName + " phone: " + phoneNumber + " concerning "+ eventType + ": " + eventType.getMsg(), LogLevel.DEBUG);
	}

	
}

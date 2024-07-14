package NotificationSystem;

import UI.Logger.LogLevel;
import UI.Logger.Logger;

public record MobileAppListener(String username) implements Listener {

	@Override
	public void update(Event eventType) {
		Logger.log("Sending mobile notification to message to: " + username + " concerning "+ eventType + ": " + eventType.getMsg(), LogLevel.DEBUG);
		
	}
	
}

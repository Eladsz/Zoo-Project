package NotificationSystem;

public record MobileAppListener(String username) implements Listener {

	@Override
	public void update(Event eventType) {
		System.out.println("Sending mobile notification to message to: " + username + " concerning "+ eventType + ": " + eventType.getMsg());
		
	}
	
}

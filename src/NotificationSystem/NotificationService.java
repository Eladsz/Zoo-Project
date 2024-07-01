
package NotificationSystem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NotificationService {
	private static Map<Event, List<Listener>> customers;
	private static NotificationService _instance;
	
	private NotificationService() {
		customers = new HashMap<Event, List<Listener>>();
		Arrays.stream(Event.values()).forEach(event -> customers.put(event, new ArrayList<Listener>()));
	}
	
	public static synchronized NotificationService getService() {
		if (_instance == null) {
			_instance = new NotificationService();
		}
		return _instance;
	}
	
	public void subscribe(Event eventType, Listener listener) {
		customers.get(eventType).add(listener);
	}
	
	public  void unsubscribe(Event eventType, Listener listener) {
		customers.get(eventType).remove(listener);
	}
	
    public void notify(Event eventType, String message) {
    	eventType.setMsg(message);
        customers.get(eventType).forEach(listener -> listener.update(eventType));
    }
}

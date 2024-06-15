package Users.Worker;

public class WorkerIDGenerator {
	
	private static 	WorkerIDGenerator _instance = null;
	private int 	id;
	
	private WorkerIDGenerator() {
		this.id = 0;
	}
	
	public static WorkerIDGenerator getInstance() {
		if (_instance == null) {
			_instance = new WorkerIDGenerator();
		}
		return _instance;
	}
	
	public int getID() {
		return id++;
	}
	
	@Override
	public String toString() {
		return "Ticket ID Generator [id = " + id + "]";
	}
}

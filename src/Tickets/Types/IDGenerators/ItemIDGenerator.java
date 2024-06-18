package Tickets.Types.IDGenerators;

public class ItemIDGenerator {
	private static 	ItemIDGenerator _instance = null;
	private int 	id;
	
	private ItemIDGenerator() {
		this.id = 1;
	}
	
	public static ItemIDGenerator getInstance() {
		if (_instance == null) {
			_instance = new ItemIDGenerator();
		}
		return _instance;
	}
	
	public int getID() {
		return id++;
	}
	
	@Override
	public String toString() {
		return "Item ID Generator [id = " + id + "]";
	}
}


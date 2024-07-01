package NotificationSystem;

public enum Event {
	NEW_ITEM	("New Item"),
	SALE		("New Sale"),
	ITEM_UPDATE ("Item updated"),
	CUSTOMER_MESSAGE ("New Message"),
	WORKER_MESSAGE ("New workers message");



	private String msg;
	
	Event(String msg){
		this.setMsg(msg);
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}


}
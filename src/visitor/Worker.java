package visitor;

import java.time.LocalDate;

public class Worker {
	
	private static int worker = 1;

	private final int workerId;
	private int id;
	private String fName;
	private String lName;
	private LocalDate birthDate;

	private int phoneNumber;

	public Worker(int id, String fName, String lName, LocalDate birthDate, int phoneNumber) {

		this.workerId = worker++;
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}
	

	public int getWorkerId() {
		return workerId;
	}

	public int getId() {
		return id;
	}

	public String getfName() {
		return fName;
	}

	public String getlName() {
		return lName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}

}

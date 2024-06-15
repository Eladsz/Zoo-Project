package Users.Abstracts;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Person {
	@Override
	public String toString() {
		return "id=" + id + " \nfirstName=" + firstName + "\nlastName=" + lastName + "\nbirthDate=" + birthDate
				+ "\nphoneNumber=" + phoneNumber + "\n";
	}

	private int id;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String phoneNumber;
	
	public Person(int id, String firstName, String lastName, LocalDate birthDate, String phoneNumber) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return id == other.id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


}

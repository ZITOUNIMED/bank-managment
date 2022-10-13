package bank.managment.centralAdmin.entities;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 6670872435825356323L;
	private String firstName;
	private String lastName;
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
	
}

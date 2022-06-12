package capstone.model;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	//private variables
	private static final long serialVersionUID = 2L;
	private String fName;
	private String lName;
	private String username;
	private String password;
	private String email;
	private String birthdate;
	private int ID;
	
	public User() {
	
	}
	

	public User(String fName, String lName, String username, String password, String email, String birthdate) {
		this.fName = fName;
		this.lName = lName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.birthdate = birthdate;
	}


	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthday) {
		this.birthdate = birthday;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	

}


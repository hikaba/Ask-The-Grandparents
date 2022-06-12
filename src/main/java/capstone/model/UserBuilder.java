package capstone.model;

public class UserBuilder {
	private String fName;
	private String lName;
	private String username;
	private String password;
	private String email;
	private String birthdate;
	
	public UserBuilder() {
	}

	public UserBuilder setfName(String fName) {
		this.fName = fName;
		return this;
	}

	public UserBuilder setlName(String lName) {
		this.lName = lName;
		return this;
	}

	public UserBuilder setUsername(String username) {
		this.username = username;
		return this;

	}

	public UserBuilder setPassword(String password) {
		this.password = password;
		return this;

	}

	public UserBuilder setEmail(String email) {
		this.email = email;
		return this;

	}

	public UserBuilder setBirthdate(String birthdate) {
		this.birthdate = birthdate;
		return this;

	}
	
	public User createUser() {
		return new User(fName, lName, username, password, email, birthdate);
	}
	
	

}

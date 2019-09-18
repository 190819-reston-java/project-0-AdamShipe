package com.revature.model;

public class Account {
	
	private int id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String email;
	private double balance;
	private boolean animationPref;
	
	public Account(int id, String userName, String password, String firstName, String lastName,
			String birthDate, String email, double balance, boolean animationPref) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.balance = balance;
		this.animationPref = animationPref;
	}



	@Override
	public String toString() {
		return "Account [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", birthDate=" + birthDate + ", email=" + email + ", balance=" + balance
				+ ", animationPref=" + animationPref + "]";
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean getAnimationPref() {
		return animationPref;
	}
	
	public void setAnimationPref(boolean preference) {
		this.animationPref = preference;
	}
	
	
	
}

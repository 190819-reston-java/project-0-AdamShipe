package com.revature.model;

public class UserAccount {
	
	private String password;
	private String firstName;
	private String lastName;
	private double accountBalance;
	private String email;
	
	public UserAccount(String password, String firstName, String lastName, double accountBalance, String email) {
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountBalance = accountBalance;
		this.email = email;
	}
	
	public UserAccount() {
		this("12345", "DefaultFirst", "DefaultLast", 10000.00,"default@default.com");
	}
	
	@Override
	public String toString() {
		return "UserAccount [password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", accountBalance=" + accountBalance + ", email=" + email + "]";
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

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

package application;

import java.util.ArrayList;

import application.Flight;

public class User {
	
	private int UserId; 
	private String FirstName; 
	private String LastName; 
	private String Address;
	private int ZipCode; 
	private String State;
	private String UserName;
	private String Password; 
	private String Email;
	private String SSN; 
	private String SecurityQustion; 
	private String SecurityAnwser;
	private String action; 
	private ArrayList<Flight> flight;
	
	
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getZipCode() {
		return ZipCode;
	}
	public void setZipCode(int zipCode) {
		ZipCode = zipCode;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	public String getSecurityQustion() {
		return SecurityQustion;
	}
	public void setSecurityQustion(String securityQustion) {
		SecurityQustion = securityQustion;
	}
	public String getSecurityAnwser() {
		return SecurityAnwser;
	}
	public void setSecurityAnwser(String securityAnwser) {
		SecurityAnwser = securityAnwser;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	

}

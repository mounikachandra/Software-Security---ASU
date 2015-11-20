package com.java.softsec.models;

import java.util.Date;

public class ExternalUser {
	
	private int exUserId;
	private String lastName;
	private String firstName;
	private String sSN;
	private Date dOB;
	private String email;
	private String address;
	private String city; 
	private String phone;
	private String userName; 
	private String password;
	private int isMerchant;
	private int currentUser ;
	private int enabled;
	
	public int getExUserId() {
		return exUserId;
	}
	public void setExUserId(int exUserId) {
		this.exUserId = exUserId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getsSN() {
		return sSN;
	}
	public void setsSN(String sSN) {
		this.sSN = sSN;
	}
	public Date getdOB() {
		return dOB;
	}
	public void setdOB(Date dOB) {
		this.dOB = dOB;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public int getIsMerchant() {
		return isMerchant;
	}
	public void setIsMerchant(int isMerchant) {
		this.isMerchant = isMerchant;
	}
	public int getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(int currentUser) {
		this.currentUser = currentUser;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
}

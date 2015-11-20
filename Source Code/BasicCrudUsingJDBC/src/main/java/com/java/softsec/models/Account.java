package com.java.softsec.models;

public class Account {
	private int accNo;
	private int exUserId;
	private double balance;
	private int isActive;
	
	public Account()
	{
		
	}
	public Account(int accNo, int exUserId, double balance) {
		this.accNo=accNo;
		this.exUserId=exUserId;
		this.balance=balance;
	}
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public int getExUserId() {
		return exUserId;
	}
	public void setExUserId(int exUserId) {
		this.exUserId = exUserId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString(){
		return accNo+"";
		
	}
	
}

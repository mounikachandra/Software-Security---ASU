package com.java.softsec.models;

import java.util.Date;

public class Payment {
	private int merchantAccountNumber;
	private int customerAccountNumber;
	private double requestAmount;
	private int isPaid;
	private Date created;
	private Date updated;
	private int reqID;
	

	public Payment(){
		
	}
	
	public Payment(int merchantAccountNumber, int customerAccountNumber,
			double requestAmount, int isPaid, Date created, Date updated,
			String merchantName, int reqID) {
		super();
		this.merchantAccountNumber = merchantAccountNumber;
		this.customerAccountNumber = customerAccountNumber;
		this.requestAmount = requestAmount;
		this.isPaid = isPaid;
		this.created = created;
		this.updated = updated;
		this.merchantName = merchantName;
		this.reqID=reqID;
	}
	private String merchantName;
	public int getMerchantAccountNumber() {
		return merchantAccountNumber;
	}
	public void setMerchantAccountNumber(int merchantAccountNumber) {
		this.merchantAccountNumber = merchantAccountNumber;
	}
	public int getCustomerAccountNumber() {
		return customerAccountNumber;
	}
	public void setCustomerAccountNumber(int customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}
	public double getRequestAmount() {
		return requestAmount;
	}
	public void setRequestAmount(double requestAmount) {
		this.requestAmount = requestAmount;
	}
	public int getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(int isPaid) {
		this.isPaid = isPaid;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	public int getReqID() {
		return reqID;
	}

	public void setReqID(int reqID) {
		this.reqID = reqID;
	}
	
	/*Payment(int merchantAccountNumber, int customerAccountNumber, double requestAmount, int isPaid, Date created, Date updated, String merchantName){
		this.merchantAccountNumber=merchantAccountNumber;
		this.customerAccountNumber=customerAccountNumber;
		this.requestAmount=requestAmount;
		this.isPaid=isPaid;
		this.created=created;
		this.updated=updated;
		this.merchantName=merchantName;
	}*/
	
	
}

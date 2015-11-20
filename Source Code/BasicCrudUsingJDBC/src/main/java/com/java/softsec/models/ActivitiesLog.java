package com.java.softsec.models;

public class ActivitiesLog {
	private int logId;
	private int customerId;
	private int accountNo;
	private int employeeId;
	private String activityDetails;
	private String created; // DateTime
	
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getActivityDetails() {
		return activityDetails;
	}
	public void setActivityDetails(String activityDetails) {
		this.activityDetails = activityDetails;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
}

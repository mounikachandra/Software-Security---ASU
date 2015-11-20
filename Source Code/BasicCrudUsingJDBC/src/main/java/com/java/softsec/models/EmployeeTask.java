package com.java.softsec.models;

public class EmployeeTask {
	private int tid;
	private int customerAccNo;
	private int actionId;
	private int transId;
	private String comments;
	private int acknowledged;
	private int escalated;
	private int processed;
	private String created;
	private String updated;
	
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getCustomerAccNo() {
		return customerAccNo;
	}
	public void setCustomerAccNo(int customerAccNo) {
		this.customerAccNo = customerAccNo;
	}
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getAcknowledged() {
		return acknowledged;
	}
	public void setAcknowledged(int acknowledged) {
		this.acknowledged = acknowledged;
	}
	public int getEscalated() {
		return escalated;
	}
	public void setEscalated(int escalated) {
		this.escalated = escalated;
	}
	public int getProcessed() {
		return processed;
	}
	public void setProcessed(int processed) {
		this.processed = processed;
	}
	
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
}

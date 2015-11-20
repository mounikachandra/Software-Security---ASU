package com.java.softsec.models;

import java.util.Date;

public class AccountSummary {
	private int tid;
	private int fromAccNo;
	private int toAccNo;
	private double creditAmount;
	private double debitAmount;
	private Date created;
	private Date updated;
	private int processed;
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getFromAccNo() {
		return fromAccNo;
	}
	public void setFromAccNo(int fromAccNo) {
		this.fromAccNo = fromAccNo;
	}
	public int getToAccNo() {
		return toAccNo;
	}
	public void setToAccNo(int toAccNo) {
		this.toAccNo = toAccNo;
	}
	public double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}
	public double getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
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
	public int getProcessed() {
		return processed;
	}
	public void setProcessed(int processed) {
		this.processed = processed;
	}
}

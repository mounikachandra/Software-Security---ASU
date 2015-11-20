package com.java.softsec.models;

public class TransferPayment {
	private String account;
	private String amount;
	private String customeracc;
	private String errormsg;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCustomeracc() {
		return customeracc;
	}
	public void setCustomeracc(String customeracc) {
		this.customeracc = customeracc;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
}

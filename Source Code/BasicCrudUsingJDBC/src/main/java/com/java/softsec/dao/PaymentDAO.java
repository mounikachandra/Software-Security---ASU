package com.java.softsec.dao;

import java.util.List;

import com.java.softsec.models.Payment;


public interface PaymentDAO {

	public List<Payment> getPendingPayments(int externalUserId);

	public String updateRequest(int fromAccountNumber, int toAccountNumber,double amount, int reqid);

	void insertPayment(int merchantAccount, int customerAccount, double amount);
		
}

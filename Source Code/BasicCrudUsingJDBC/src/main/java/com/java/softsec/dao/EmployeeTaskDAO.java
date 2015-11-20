package com.java.softsec.dao;

import java.util.List;

import com.java.softsec.models.Account;
import com.java.softsec.models.AccountSummary;
import com.java.softsec.models.EmployeeTask;

public interface EmployeeTaskDAO {
	
	public EmployeeTask getInEmployeeTask(); 		// non-admin
	
	public EmployeeTask getInAdminEmployeeTask();	// admin
	
	public AccountSummary getTransaction(int id);
	
	public Account getAccount(int accNo);
	
	public void deleteAccount(int accNo, int taskid);
	
	public void doTransaction(int tranid, int taskid);
	
	public void escalateToAdmin(int taskid);
	
	public void resetAcknowledged(int taskid);
	
	public void updateTaskAsProcessed(int taskid);

	void createEmpTaskCreateAcc(String userID);

	void deleteAccountReq(int accNo);

	void activateNewAccount(int newaccno);

	void updateTransaction(int tranid, int taskid);

	//public void activateNewAccount(int newaccno);

	//public void updateTransaction(int tranid, int taskid);


	
}

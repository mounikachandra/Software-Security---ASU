package com.java.softsec.dao;

import java.util.List;

import com.java.softsec.models.Account;
import com.java.softsec.models.AccountSummary;

public interface AccountDAO {
	public void saveOrUpdate(Account account);
    
    public void delete(int AccNo);
     
    public Account get(int AccNo);
    
    public String Credit(int AccNo, double amount);
    
    public String Debit(int AccNo, double amount);
    
    public void UpdateSummary(int FromAccNo, int ToAccNo, double CreditAmt,double DebitAmt);
     
    public List<Account> list();
    
    public List<Account> getAllAccounts(int ExUserId);

	public String setTransfer(int fromAccountNumber, int toAccountNumber,double amount);

	List<Account> list(String userid);

	List<AccountSummary> ViewSummary(int accNo, int processed);
	
	


}

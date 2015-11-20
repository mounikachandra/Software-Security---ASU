package com.java.softsec.dao;

import java.util.List;

import com.java.softsec.models.ExternalUser;;

public interface ExternalUserDAO {
	public void saveOrUpdate(ExternalUser externalUser);
    
    public void delete(int exUserId);
     
    public ExternalUser get(int exUserId );
     
    public List<ExternalUser> list();
    
    public void getOutstandingPayment(int customerAccountNumber);

	public ExternalUser ifExists(String mail);
	
	 public ExternalUser get(String username );

	void saveOrUpdate(ExternalUser externalUser, String userID);
	
	//public List<String> ifnewRegisteredUserExists(String mail);

	void update_password(String password, String email);

	List<String> ifnewRegisteredUserExists(String mail);

	public String getexternID(String username);

	//public void update_password(String password, String email);
    
}

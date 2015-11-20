package com.java.softsec.dao;

import java.util.List;

import com.java.softsec.models.InternalUser;
import com.java.softsec.models.InternalUser2;

public interface InternalUserDAO {
	
	public void saveOrUpdate(InternalUser internalUser);

    public void delete(int inUserId);
    
   // public void activate(int inUserId);
     
    public InternalUser get(int inUserId );
     
    public List<InternalUser> list();

	public void activate(int inUserId);

	public InternalUser getInUser(String username);

	public void saveOrUpdate2(InternalUser2 form);

	public List<String> ifnewRegisteredUserExists(String mail);
}

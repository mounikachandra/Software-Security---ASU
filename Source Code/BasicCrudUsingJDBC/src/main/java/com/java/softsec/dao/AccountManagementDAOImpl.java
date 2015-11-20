package com.java.softsec.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.java.softsec.models.Account;
import com.java.softsec.models.AccountManagement;

public class AccountManagementDAOImpl implements AccountManagementDAO {
	
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataSource dataSource;	

	public void setDataSource(DataSource dataSource) {
		this.dataSource =dataSource;
	}
	

	public JdbcTemplate getJdbcTemplate() {
		if(jdbcTemplate == null)
			jdbcTemplate = new JdbcTemplate(dataSource); 
		
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
/*
	@Override
	public AccountManagement get(List<Account> listAcc,String action) {
		AccountManagement accmgnt=new AccountManagement();
		
		accmgnt.setListAccount(listAcc);
		accmgnt.setAction(action);
		return accmgnt;
	}*/


	@Override
	public AccountManagement get() {
		// TODO Auto-generated method stub
		return null;
	}

}

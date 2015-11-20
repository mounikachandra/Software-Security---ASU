package com.java.softsec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.java.softsec.models.AccountSummary;

public class AccountSummaryDAOImpl implements AccountSummaryDAO {
	
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
	
	@Override
	public List<AccountSummary> getTransact(int fromAccount){
		
		String sql="SELECT fromAccNo, toAccNo,creditAmount FROM account_summary WHERE fromAccNo="+fromAccount+" OR toAccNo="+fromAccount+"";
		System.out.println(sql);
	    return getJdbcTemplate().query(sql, new ResultSetExtractor<List<AccountSummary>>() {
	   	 
	    	@Override
	        public List<AccountSummary> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<AccountSummary> accList = new ArrayList<AccountSummary>();
	            while (rs.next()) {
	            	AccountSummary acc = new AccountSummary();
		            acc.setFromAccNo(rs.getInt("fromAccNo"));
		            acc.setToAccNo(rs.getInt("toAccNo"));
		            acc.setCreditAmount(rs.getInt("creditAmount"));  
		            accList.add(acc);
	            }	 
	            return accList;
	        }	 
	    });	
	}

}

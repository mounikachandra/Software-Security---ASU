package com.java.softsec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.java.softsec.models.Account;
import com.java.softsec.models.AccountSummary;
import com.java.softsec.models.EmployeeTask;

public class EmployeeTaskDAOImpl implements EmployeeTaskDAO{
	
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
	public EmployeeTask getInEmployeeTask() {
		String sql = "SELECT * FROM employee_tasks WHERE acknowledged = 0 and escalated = 0 ORDER BY created LIMIT 1";
		
		return getJdbcTemplate().query(sql, new ResultSetExtractor<EmployeeTask>() {
		   	 
	        @Override
	        public EmployeeTask extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	EmployeeTask task = new EmployeeTask();
					
					int task_id = rs.getInt("tid");
					
					//get current date time with Date()
					Date date = new Date();
					String sql = "UPDATE employee_tasks SET acknowledged = 1, updated = '"+dateFormat.format(date)+"'  WHERE tid = " + task_id;
		            
					getJdbcTemplate().update(sql);
		            
		            task.setTid(task_id);
		            task.setCustomerAccNo(rs.getInt("customerAccNo"));
		            task.setActionId(rs.getInt("actionId"));
		            task.setTransId(rs.getInt("transId"));
		            task.setComments(rs.getString("comments"));
		            task.setAcknowledged(1);
		            task.setEscalated(rs.getInt("escalated"));
		            task.setProcessed(rs.getInt("processed"));
		            task.setCreated(rs.getString("created"));
		            task.setUpdated(dateFormat.format(date));
		            
		            return task;
	            }
	            return null;
	        }
	    });
	}

	@Override
	public EmployeeTask getInAdminEmployeeTask() {
		String sql = "SELECT * FROM employee_tasks WHERE acknowledged = 0 and escalated = 1 ORDER BY created LIMIT 1";
		
		return getJdbcTemplate().query(sql, new ResultSetExtractor<EmployeeTask>() {
		   	 
	        @Override
	        public EmployeeTask extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	EmployeeTask task = new EmployeeTask();
					
					int task_id = rs.getInt("tid");
					
					Date date = new Date();
					String sql = "UPDATE employee_tasks SET acknowledged = 1, updated = '"+dateFormat.format(date)+"' WHERE tid = " + task_id;
		            
					getJdbcTemplate().update(sql);
		            
		            task.setTid(task_id);
		            task.setCustomerAccNo(rs.getInt("customerAccNo"));
		            task.setActionId(rs.getInt("actionId"));
		            task.setTransId(rs.getInt("transId"));
		            task.setComments(rs.getNString("comments"));
		            task.setAcknowledged(1);
		            task.setEscalated(rs.getInt("escalated"));
		            task.setProcessed(rs.getInt("processed"));
		            task.setCreated(rs.getString("created"));
		            task.setUpdated(dateFormat.format(date));
		            
		            return task;
	            }
	            return null;
	        }
	    });
	}
	
	@Override
	public AccountSummary getTransaction(int id) {
		String sql = "SELECT * FROM account_summary WHERE tId = " + id;
		
		return getJdbcTemplate().query(sql, new ResultSetExtractor<AccountSummary>() {
		   	 
	        @Override
	        public AccountSummary extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	AccountSummary trans = new AccountSummary();
					trans.setTid(rs.getInt("tId"));
					trans.setFromAccNo(rs.getInt("fromAccNo"));
					trans.setToAccNo(rs.getInt("toAccNo"));
					trans.setCreditAmount(rs.getDouble("creditAmount"));
					trans.setDebitAmount(rs.getDouble("debitAmount"));
					trans.setCreated(rs.getDate("created"));
					trans.setUpdated(rs.getDate("updated"));
					trans.setProcessed(rs.getInt("processed"));
		            return trans;
	            }
	            return null;
	        }
	    });
	}

	@Override
	public Account getAccount(int accNo) {
		String sql = "SELECT * FROM accounts WHERE accNo = " + accNo;
	    return getJdbcTemplate().query(sql, new ResultSetExtractor<Account>() {
	   	 
	        @Override
	        public Account extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Account account = new Account();
		            account.setAccNo(rs.getInt("accNo"));
		            account.setExUserId(rs.getInt("exUserId"));
		            account.setBalance(rs.getDouble("balance"));
		            account.setIsActive(rs.getInt("isActive"));
		            return account;
	            }
	            return null;
	        }
	 
	    });
	}
/*
	@Override
	public void doTransaction(int tranid, int taskid) {
		String sql = "UPDATE account_summary SET processed = 1 WHERE tId = " + tranid;
		getJdbcTemplate().update(sql);
		
		updateTaskAsProcessed(taskid);
	}*/
	
	@Override
	public void escalateToAdmin(int taskid) {
		Date date = new Date();
		String sql = "UPDATE employee_tasks SET acknowledged = 0, escalated = 1, updated = '"+dateFormat.format(date)+"' WHERE tId = " + taskid;
		getJdbcTemplate().update(sql);
	}

	@Override
	public void resetAcknowledged(int taskid) {
		Date date = new Date();
		String sql = "UPDATE employee_tasks SET acknowledged = 0, updated = '"+dateFormat.format(date)+"' WHERE tid = " + taskid;
		getJdbcTemplate().update(sql);
	}

	@Override
	public void deleteAccount(int accNo, int taskid) {
		String sql = "UPDATE accounts SET isActive = 0 WHERE accNo = " + accNo;
		getJdbcTemplate().update(sql);
		updateTaskAsProcessed(taskid);
	}

	@Override
	public void updateTaskAsProcessed(int taskid) {
		Date date = new Date();
		String sql = "UPDATE employee_tasks SET processed = 1, updated = '"+dateFormat.format(date)+"' WHERE tId = " + taskid;
		getJdbcTemplate().update(sql);	
	}
	
	@Override
	public void createEmpTaskCreateAcc(String userID)
	{
		String sql="INSERT INTO accounts (exUserId,isActive) VALUES ((SELECT exUserId FROM external_users WHERE userName='"+userID+"'),0);";
		getJdbcTemplate().execute(sql);
		List<String> accNo=getAccNo(userID);
		String lastestAccNo=accNo.get(0);
		String sql1="INSERT INTO employee_tasks (customerAccNo,actionId) VALUES ("+lastestAccNo+",3);";
		 
		getJdbcTemplate().execute(sql1);
		// get the latest account
		
		
	}
	
	
	@Override 
	public void  deleteAccountReq(int accNo)
	{
		String sql1="INSERT INTO employee_tasks (customerAccNo,actionId) VALUES ("+accNo+",2);";
		 
		getJdbcTemplate().execute(sql1);
	}
	
	private  List<String> getAccNo(String userID)
	{
		String sql = "SELECT MAX(accNo) AS last_updatedacc FROM accounts WHERE exUserId=(SELECT exUserId FROM external_users WHERE userName='"+userID+"') ";
	    List<String> pkeys = getJdbcTemplate().query(sql, new RowMapper<String>() {
	 
	        @Override
	        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
	            String pkey = rs.getInt("last_updatedacc")+"";        
	 
	            return pkey;
	        }
	 
	    });
	 
	    return pkeys;
	}


	@Override
	public void activateNewAccount(int newaccno) {
		String sql = "UPDATE accounts SET isActive = 1 WHERE accNo = " + newaccno;
		getJdbcTemplate().update(sql);		
	}


	@Override
	public void updateTransaction(int tranid, int taskid) {
		// TODO Auto-generated method stub
		String sql = "UPDATE account_summary SET processed = 1 WHERE tId = " + tranid;
		getJdbcTemplate().update(sql);		
		updateTaskAsProcessed(taskid);
		
	}


	@Override
	public void doTransaction(int tranid, int taskid) {
		// TODO Auto-generated method stub
		
	}
	
}
 
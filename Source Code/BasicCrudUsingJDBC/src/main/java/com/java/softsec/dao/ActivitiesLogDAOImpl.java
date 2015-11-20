package com.java.softsec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.java.softsec.models.ActivitiesLog;

public class ActivitiesLogDAOImpl implements ActivitiesLogDAO{

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
	public List<ActivitiesLog> listAllLogs() {
		String sql = "SELECT * FROM activities_log";
	    List<ActivitiesLog> listLogs = getJdbcTemplate().query(sql, new RowMapper<ActivitiesLog>() {
	        @Override
	        public ActivitiesLog mapRow(ResultSet rs, int rowNum) throws SQLException {
	            ActivitiesLog log = new ActivitiesLog();
	            log.setLogId(rs.getInt("id"));
	            log.setCustomerId(rs.getInt("customerId"));
	            log.setAccountNo(rs.getInt("accountNo"));
	            log.setActivityDetails(rs.getString("activityDetails"));
	            log.setCreated(rs.getString("created"));
	            return log;
	        }
	 
	    });
	 
	    return listLogs;
	}

	@Override
	public List<ActivitiesLog> listLogsWithCustId(int cust_id) {
		String sql = "SELECT * FROM activities_log WHERE customerId = " + cust_id;
	    List<ActivitiesLog> listLogs = getJdbcTemplate().query(sql, new RowMapper<ActivitiesLog>() {
	        @Override
	        public ActivitiesLog mapRow(ResultSet rs, int rowNum) throws SQLException {
	            ActivitiesLog log = new ActivitiesLog();
	            log.setLogId(rs.getInt("id"));
	            log.setCustomerId(rs.getInt("customerId"));
	            log.setAccountNo(rs.getInt("accountNo"));
	            log.setActivityDetails(rs.getString("activityDetails"));
	            log.setCreated(rs.getString("created"));
	            return log;
	        }
	 
	    });
	 
	    return listLogs;
	}

	@Override
	public List<ActivitiesLog> listLogsWithAccNo(int acc_no) {
		String sql = "SELECT * FROM activities_log WHERE accountNo = " + acc_no;
	    List<ActivitiesLog> listLogs = getJdbcTemplate().query(sql, new RowMapper<ActivitiesLog>() {
	        @Override
	        public ActivitiesLog mapRow(ResultSet rs, int rowNum) throws SQLException {
	            ActivitiesLog log = new ActivitiesLog();
	            log.setLogId(rs.getInt("id"));
	            log.setCustomerId(rs.getInt("customerId"));
	            log.setAccountNo(rs.getInt("accountNo"));
	            log.setActivityDetails(rs.getString("activityDetails"));
	            log.setCreated(rs.getString("created"));
	            return log;
	        }
	 
	    });
	 
	    return listLogs;
	}
	
	@Override
	public List<ActivitiesLog> listLogsWithCustIdAccNo(int cust_id, int acc_no) {
		String sql = "SELECT * FROM activities_log WHERE accountNo = " + acc_no + " AND customerId = " + cust_id;
	    List<ActivitiesLog> listLogs = getJdbcTemplate().query(sql, new RowMapper<ActivitiesLog>() {
	        @Override
	        public ActivitiesLog mapRow(ResultSet rs, int rowNum) throws SQLException {
	            ActivitiesLog log = new ActivitiesLog();
	            log.setLogId(rs.getInt("id"));
	            log.setCustomerId(rs.getInt("customerId"));
	            log.setAccountNo(rs.getInt("accountNo"));
	            log.setActivityDetails(rs.getString("activityDetails"));
	            log.setCreated(rs.getString("created"));
	            return log;
	        }
	    });
	 
	    return listLogs;
	}
	
	@Override
	public List<ActivitiesLog> listLogsWithEmpId(int emp_id) {
		String sql = "SELECT * FROM activities_log WHERE employeeId = " + emp_id;
	    List<ActivitiesLog> listLogs = getJdbcTemplate().query(sql, new RowMapper<ActivitiesLog>() {
	        @Override
	        public ActivitiesLog mapRow(ResultSet rs, int rowNum) throws SQLException {
	            ActivitiesLog log = new ActivitiesLog();
	            log.setLogId(rs.getInt("id"));
	            log.setCustomerId(rs.getInt("customerId"));
	            log.setEmployeeId(rs.getInt("employeeId"));
	            log.setAccountNo(rs.getInt("accountNo"));
	            log.setActivityDetails(rs.getString("activityDetails"));
	            log.setCreated(rs.getString("created"));
	            return log;
	        }
	 
	    });
	 
	    return listLogs;
	}
	
	@Override
	public void insertLog(int custid, String msg) {
		String sql = "INSERT INTO activities_log (customerId, activityDetails) VALUES ("+custid+", '"+msg+"')";
		getJdbcTemplate().update(sql);
	}
	
	@Override
	public void insertLog(int custid, int accno, String msg) {
		String sql = "INSERT INTO activities_log (customerId, accountNo, activityDetails) VALUES ("+custid+", "+accno+", '"+msg+"')";
		getJdbcTemplate().update(sql);
		return;
	}
	
	@Override
	public void insertLogWithCustEmpId(int custid, int empid, String msg){
		String sql = "INSERT INTO activities_log (customerId, employeeId, activityDetails) VALUES ("+custid+", "+empid+", '"+msg+"')";
		getJdbcTemplate().update(sql);
	}
	
	@Override 
	public void insertLogWithAll(int custid, int accno, int empid, String msg){
		String sql = "INSERT INTO activities_log (customerId, accountNo, employeeId, activityDetails) VALUES ("+custid+", "+accno+", "+empid+", '"+msg+"')";
		getJdbcTemplate().update(sql);
	}

}

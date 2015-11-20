package com.java.softsec.dao;

import java.util.List;

import com.java.softsec.models.ActivitiesLog;

public interface ActivitiesLogDAO {
	
	public List<ActivitiesLog> listAllLogs();
	
	public List<ActivitiesLog> listLogsWithAccNo(int acc_no);
	
	public List<ActivitiesLog> listLogsWithCustId(int cust_id);
	
	public List<ActivitiesLog> listLogsWithCustIdAccNo(int cust_id, int acc_no);
	
	public List<ActivitiesLog> listLogsWithEmpId(int emp_id);
	
	public void insertLog(int custid, String msg);
	
	public void insertLog(int custid, int accno, String msg);

	public void insertLogWithCustEmpId(int custid, int empid, String msg);

	public void insertLogWithAll(int custid, int accno, int empid, String msg);
	
}

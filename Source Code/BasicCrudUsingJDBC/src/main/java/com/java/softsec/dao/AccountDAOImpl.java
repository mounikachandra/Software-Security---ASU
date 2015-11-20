package com.java.softsec.dao;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;






import com.java.softsec.models.Account;
import com.java.softsec.models.AccountSummary;

public class AccountDAOImpl implements AccountDAO {

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
	
	//have setter method
	

	
	@Override
	public void saveOrUpdate(Account account) {
		// TODO Auto-generated method stub
		if(account.getAccNo() >0)
		{
			String sql="UPDATE accounts SET ExUserId=?, Balance=? WHERE AccNo=?;";
			getJdbcTemplate().update(sql, account.getExUserId(),account.getBalance(),account.getAccNo());			
		}
		else
		{
			String sql="INSERT INTO accounts (ExUserId,Balance) VALUES (1000001,12.0);";
			getJdbcTemplate().update(sql, account.getExUserId(),account.getBalance());
		}
	}

	@Override
	public void delete(int AccNo) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM accounts WHERE AccNo=?;";
		getJdbcTemplate().update(sql, AccNo);
	}

	@Override
	public List<Account> list() {
		String sql = "SELECT * FROM accounts";
	    List<Account> listAccount = getJdbcTemplate().query(sql, new RowMapper<Account>() {
	 
	        @Override
	        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Account account = new Account();
	            account.setAccNo(rs.getInt("AccNo"));
	            account.setExUserId(rs.getInt("ExUserId"));
	            account.setBalance(rs.getDouble("Balance"));  
	            return account;
	        }
	 
	    });
	 
	    return listAccount;
	}

	@Override
	public Account get(int AccNo) {
		// implementation details goes here...
		String sql = "SELECT * FROM accounts WHERE isActive=1 AND AccNo=" + AccNo;
	    return getJdbcTemplate().query(sql, new ResultSetExtractor<Account>() {
	 
	        @Override
	        public Account extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Account account = new Account();
		            account.setAccNo(rs.getInt("AccNo"));
		            account.setExUserId(rs.getInt("ExUserId"));
		            account.setBalance(rs.getDouble("Balance"));  
		            return account;
	            }	 
	            return null;
	        }
	 
	    });		
	}
	

	@Override
	public List<Account> getAllAccounts(int ExUserId) {
		// implementation details goes here...
		String sql = "SELECT * FROM accounts WHERE isActive=1 and ExUserId=" + ExUserId;
	    return getJdbcTemplate().query(sql, new ResultSetExtractor<List<Account>>() {
	 
	        @Override
	        public List<Account> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<Account> accountList = new ArrayList<Account>();
	            while (rs.next()) {
	            	Account account = new Account();
		            account.setAccNo(rs.getInt("AccNo"));
		            account.setExUserId(rs.getInt("ExUserId"));
		            account.setBalance(rs.getDouble("Balance"));  
		            accountList.add(account);
	            }	 
	            return accountList;
	        }
	 
	    });		
	}
	
	public static String getCurrentDate(){
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp now = new java.sql.Timestamp(today.getTime());
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
		 try {
			Date date = format.parse(now.toString());
			//System.out.println(format.format(date));
			return format.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return "";
	}
	
	
	@Override
	public String setTransfer(int fromAccountNumber, int toAccountNumber, double amount) {
		
		Account account=new Account();
		Account fromAccount= get(fromAccountNumber);
		Account toAccount = get(toAccountNumber);
		
		if(amount <= 0){
			return "Enter valid amount";
		}
		
		if(toAccount == null){
			return "Account doesn't exist or transfer failed";
		}
		
		if(fromAccount.getBalance() < amount){
			return "Not enough balance";
		}
		
		//update account summary
		//update transactions
		
		if(amount <= 500){
			toAccount.setBalance(toAccount.getBalance() + amount);
			saveOrUpdate(toAccount);
			
			fromAccount.setBalance(fromAccount.getBalance() - amount);
			saveOrUpdate(fromAccount);
			
			String sql="INSERT INTO account_summary (FromAccNo,ToAccNo,CreditAmount,DebitAmount,Created, Updated,Processed) VALUES ("+fromAccountNumber+","+toAccountNumber+",0,"+amount+",'"+getCurrentDate()+"',NULL,1)";
			getJdbcTemplate().update(sql);
			
		} else {
			String sql="INSERT INTO account_summary (FromAccNo,ToAccNo,CreditAmount,DebitAmount,Created, Updated,Processed) VALUES ("+fromAccountNumber+","+toAccountNumber+",0,"+amount+",'"+getCurrentDate()+"',NULL,0)";
			getJdbcTemplate().update(sql);
			
			int transid = gettransid();
			String sql1="INSERT INTO employee_tasks (customerAccNo,actionId,transId,acknowledged,escalated, processed) VALUES ("+fromAccountNumber+",1,"+transid+",0,0,0)";
			getJdbcTemplate().update(sql1);
		}
		
		return "Success";
		/*
		else{
			String sql="INSERT INTO accounts (ExUserId,Balance) VALUES (1000001,12.0);";
			jdbcTemplate.update(sql, account.getExUserId(),account.getBalance());
		}*/
	}
	
	public String Credit(int AccNo, double amount){
		Account acc=get(AccNo);
		if(acc==null)
			return "Account doesn't exist or transfer failed";

		
		acc.setBalance(acc.getBalance() + amount);

		saveOrUpdate(acc);
		UpdateSummary(AccNo, AccNo, amount, -1);
		return "Success";
	}
	
	public String Debit(int AccNo, double amount){
		Account acc=get(AccNo);
		if(acc==null)
			return "Account doesn't exist or transfer failed";

		double balance=acc.getBalance();
		if(balance < amount)
			return "Not enough balance";
		
		acc.setBalance(acc.getBalance() - amount);

		saveOrUpdate(acc);
		UpdateSummary(AccNo, AccNo, -1,amount);
		return "Success";
	}
	
		public void UpdateSummary(int FromAccNo, int ToAccNo, double CreditAmt,double DebitAmt){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		int flag=0;
		if(CreditAmt > 0)
			if(CreditAmt >500)
				flag=0;
			else
				flag=1;
		
		if(DebitAmt > 0)
			if(DebitAmt >500)
				flag=0;
			else
				flag=1;
		
		
		CreditAmt=(CreditAmt > 0)?CreditAmt : 0;
		DebitAmt =(DebitAmt> 0) ? DebitAmt :0;
		String sql="INSERT INTO account_summary (FromAccNo,ToAccNo,CreditAmount,DebitAmount,Created,Updated,Processed) VALUES (?,?,?,?,?,?,?);";
		
		getJdbcTemplate().update(sql, FromAccNo,ToAccNo,CreditAmt,DebitAmt,dateFormat.format(date),dateFormat.format(date), flag);
	}
		
		@Override
		public List<Account> list(String userid) {
			String sql = "SELECT * FROM accounts WHERE isActive=1 AND ExUserId=(SELECT exUserId FROM external_users WHERE userName='"+userid+"');";
		    List<Account> listAccount = getJdbcTemplate().query(sql, new RowMapper<Account>() {
		 
		        @Override
		        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		            Account account = new Account();
		            account.setAccNo(rs.getInt("AccNo"));
		            account.setExUserId(rs.getInt("ExUserId"));
		            account.setBalance(rs.getDouble("Balance"));  
		            return account;
		        }
		 
		    });
		 
		    return listAccount;
		}
		
		@Override
		public List<AccountSummary> ViewSummary(int accNo, int processed) {
			String sql = "SELECT * FROM account_summary WHERE processed = "+processed+" and (fromAccNo = "+accNo+" OR toAccNo = "+accNo+")";
		    List<AccountSummary> listSummary = getJdbcTemplate().query(sql, new RowMapper<AccountSummary>() {
		 
		        @Override
		        public AccountSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	AccountSummary summary = new AccountSummary();
		        	summary.setTid(rs.getInt("tId"));
		        	summary.setFromAccNo(rs.getInt("fromAccNo"));
		        	summary.setToAccNo(rs.getInt("toAccNo"));
		        	summary.setCreditAmount(rs.getDouble("creditAmount"));
		        	summary.setDebitAmount(rs.getDouble("debitAmount"));
		        	summary.setCreated(rs.getDate("created"));
		        	summary.setUpdated(rs.getDate("updated"));
		        	summary.setProcessed(rs.getInt("processed"));
		            
		            return summary;
		        }
		 
		    });
		    return listSummary;	
		}
		private Integer gettransid()
		{
			String sql="SELECT MAX(tid) as tidlast FROM account_summary;";
		    List<String> pkeys = getJdbcTemplate().query(sql, new RowMapper<String>() {
		 
		        @Override
		        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		            String pkey = rs.getInt("tidlast")+"";        
		 
		            return pkey;
		        }
		 
		    });
		 
		    return Integer.parseInt(pkeys.get(0));
		}
	
}

package com.java.softsec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.java.softsec.models.Account;
import com.java.softsec.models.Payment;


public class PaymentDAOImpl implements PaymentDAO{
	
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
	public List<Payment> getPendingPayments(int externalUserId) {
		
		String sql = "SELECT rp.ReqId,rp.MerchantAccNo,rp.CustomerAccNo,rp.Request_Amount,rp.Paid,rp.Created,rp.Updated,eu.firstname FROM request_payment rp, accounts ac, external_users eu WHERE rp.Paid=0 AND rp.customerAccNo IN (SELECT accNo FROM software_sec1.accounts WHERE exUserId ="+ externalUserId+") AND rp.merchantAccNo = ac.accNo AND ac.exUserId = eu.exUserId";
	    
		System.out.println(sql);
		
		return getJdbcTemplate().query(sql, new ResultSetExtractor<List<Payment>>() {

			@Override
			public List<Payment> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<Payment> payments  = new ArrayList<Payment>();
				while(rs.next()){
					Payment payment = new Payment();
					payment.setMerchantAccountNumber(rs.getInt("MerchantAccNo"));
					payment.setCustomerAccountNumber(rs.getInt("CustomerAccNo"));
					payment.setIsPaid(rs.getInt("Paid"));
					payment.setRequestAmount(rs.getDouble("Request_Amount"));
					payment.setCreated(rs.getDate("Created"));
					payment.setUpdated(rs.getDate("Updated"));	
					payment.setMerchantName(rs.getString("firstname"));
					payment.setReqID(rs.getInt("ReqId"));
					payments.add(payment);
				}
				
				return payments;
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
	public String updateRequest(int fromAccountNumber, int toAccountNumber,double amount, int reqid)
	{
		String sql="UPDATE request_payment SET Paid=1,Updated='"+getCurrentDate()+"' WHERE ReqId="+reqid+"";
		System.out.println(sql);
		getJdbcTemplate().update(sql);
		
		return " Transfer Successfull";
	}
	
	@Override
	public void insertPayment(int merchantAccount, int customerAccount,double amount) {
		
		String sql="INSERT INTO request_payment (merchantAccNo,customerAccNo,request_Amount,created,updated) VALUES ("+merchantAccount+","+customerAccount+","+amount+",'"+getCurrentDate()+"','"+getCurrentDate()+"');";
		getJdbcTemplate().execute(sql);
		
	}
}

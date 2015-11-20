package com.java.softsec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.java.softsec.models.ExternalUser;

public class ExternalUserDAOImpl implements ExternalUserDAO {
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
	public void saveOrUpdate(ExternalUser externalUser) {
		// TODO Auto-generated method stub
		// implementation details goes here...
		if (externalUser.getExUserId()> 0) {
			// update
			String sql = "UPDATE external_users SET lastName = ?, firstName = ?,  sSN = ?, dOB = ?, email = ?, address = ?, city = ?, phone = ?, userName = ?,"+
					 " PASSWORD = ?, isMerchant =? , currentUser = ? WHERE exUserId = ?";
			getJdbcTemplate().update(sql,externalUser.getLastName(),externalUser.getFirstName(),
					externalUser.getsSN(),
					externalUser.getdOB(),
					externalUser.getEmail(),
					externalUser.getAddress(),
					externalUser.getCity(),
					externalUser.getPhone(),
					externalUser.getUserName(),
					externalUser.getPassword(),
					externalUser.getIsMerchant(),
					externalUser.getCurrentUser(),
					externalUser.getExUserId()
					);
		} else {
			// insert
			String sql = "INSERT INTO external_users (lastName, firstName,sSN,dOB,email,address,city, phone,userName,PASSWORD,isMerchant,currentUser)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?, ?);";
			getJdbcTemplate().update(sql, externalUser.getLastName(),
					externalUser.getFirstName(),
					externalUser.getsSN(),
					externalUser.getdOB(),
					externalUser.getEmail(),
					externalUser.getAddress(),
					externalUser.getCity(),
					externalUser.getPhone(),
					externalUser.getUserName(),
					externalUser.getPassword(),
					externalUser.getIsMerchant(),
					externalUser.getCurrentUser(),
					externalUser.getExUserId());
		}
	}

	@Override
	public void delete(int exUserId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM external_users WHERE exUserId=";
		getJdbcTemplate().update(sql, exUserId);
	}

	@Override
	public ExternalUser get(int exUserId) {
		// TODO Auto-generated method stub
				String sql = "SELECT * FROM external_users WHERE exUserId=" + exUserId;
			    return getJdbcTemplate().query(sql, new ResultSetExtractor<ExternalUser>() {
			 
			        @Override
			        public ExternalUser extractData(ResultSet rs) throws SQLException,
			                DataAccessException {
			            if (rs.next()) {
			                ExternalUser externalUser = new ExternalUser();
			                
			                externalUser.setExUserId(rs.getInt("exUserId"));
			                externalUser.setLastName(rs.getString("lastName"));
			                externalUser.setFirstName(rs.getString("firstName"));
							externalUser.setsSN(rs.getString("sSN"));
							externalUser.setdOB(rs.getDate("dOB"));
							externalUser.setEmail(rs.getString("email"));
							externalUser.setAddress(rs.getString("address"));
							externalUser.setCity(rs.getString("city"));
							externalUser.setPhone(rs.getString("phone"));
							externalUser.setUserName(rs.getString("userName"));
							externalUser.setPassword(rs.getString("password"));
							externalUser.setIsMerchant(rs.getInt("isMerchant"));
							externalUser.setCurrentUser(rs.getInt("currentUser"));
			                
							return externalUser;
			            }
			 
			            return null;
			        }
			 
			    });
	}

	@Override
	public List<ExternalUser> list() {
		String sql = "SELECT * FROM external_users";
		List<ExternalUser> listInUser = getJdbcTemplate().query(sql, new RowMapper<ExternalUser>() {
			 
	        @Override
	        public ExternalUser mapRow(ResultSet rs, int rowNum) throws SQLException{
	        	ExternalUser externalUser = new ExternalUser();
                
                externalUser.setExUserId(rs.getInt("exUserId"));
                externalUser.setLastName(rs.getString("lastName"));
                externalUser.setFirstName(rs.getString("firstName"));
				externalUser.setsSN(rs.getString("sSN"));
				externalUser.setdOB(rs.getDate("dOB"));
				externalUser.setEmail(rs.getString("email"));
				externalUser.setAddress(rs.getString("address"));
				externalUser.setCity(rs.getString("city"));
				externalUser.setPhone(rs.getString("phone"));
				externalUser.setUserName(rs.getString("userName"));
				externalUser.setPassword(rs.getString("password"));
				externalUser.setIsMerchant(rs.getInt("isMerchant"));
				externalUser.setCurrentUser(rs.getInt("currentUser"));
                
				return externalUser;
	            //return null;
	        }
	    });
		return listInUser;
	}

	@Override
	public void getOutstandingPayment(int customerAccountNumber) {
		//  SELECT fname.firstName,fname.exUserId FROM external_users AS fname WHERE fname.ExUserId IN(SELECT ac.ExUserId FROM accounts AS ac,request_payment AS py WHERE py.CustomerAccNo=1234567890 AND py.Paid=0 AND py.MerchantAccNo=ac.AccNo)  	
	}
	
	/*
	@Override
	public String ifExists(String mail){
		
		String sql="SELECT email FROM external_users WHERE email='"+mail+"'";
		
		List<String> strLst  = getJdbcTemplate().query(sql,new RowMapper{
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return rs.getString(1);
	            }
	});
		
		return null;
		
	}*/
	
	public ExternalUser ifExists(String mail){
		
		String sql = "SELECT email FROM external_users WHERE email='"+mail+"'";
	    return getJdbcTemplate().query(sql, new ResultSetExtractor<ExternalUser>() {
	 
	        @Override
	        public ExternalUser extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	ExternalUser exUser = new ExternalUser();
		            exUser.setEmail(rs.getString("email"));
		            return exUser;
	            }	 
	            return null;
	        }
	 
	    });		
	}
	
	@Override	
	public ExternalUser get(String username) {
		// TODO Auto-generated method stub
		
		String sql="SELECT * FROM external_users WHERE username='"+username+"';";
		
		return getJdbcTemplate().query(sql, new ResultSetExtractor<ExternalUser>() {
			 
	        @Override
	        public ExternalUser extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	ExternalUser extUser = new ExternalUser();
	            	extUser.setExUserId(rs.getInt("exUserId"));
	            	extUser.setLastName(rs.getString("lastName"));
	            	extUser.setFirstName(rs.getString("firstName"));
	            	extUser.setsSN(rs.getString("sSN"));
	            	extUser.setdOB(rs.getDate("dOB"));
	            	extUser.setEmail(rs.getString("email"));
	            	extUser.setAddress(rs.getString("address"));
	            	extUser.setCity(rs.getString("city"));
	            	extUser.setPhone(rs.getString("phone"));
	            	extUser.setUserName(rs.getString("userName"));
	            	extUser.setPassword(rs.getString("password"));
	            	extUser.setIsMerchant(rs.getInt("isMerchant"));
	            	extUser.setCurrentUser(rs.getInt("currentUser"));
	                return extUser;
	            }
	 
	            return null;
	        }
	 
	    });
		
	}
	
	@Override
	public void saveOrUpdate(ExternalUser externalUser,String userID) {
		// TODO Auto-generated method stub
		// implementation details goes here...
	
		
		
		
		if (userID!=null) {
			// update
			String sql = "UPDATE external_users SET email = '"+externalUser.getEmail()+"', address = '"+externalUser.getAddress()
					+"', city = '"+externalUser.getCity()+"', phone = '"+externalUser.getPhone()+"' WHERE userName = '"+userID+"';";
			
			
			
			getJdbcTemplate().execute(sql);
			
		} else {
			
			
			if(externalUser.getIsMerchant()==0)
			{
				String sqlroles="INSERT INTO user_roles (username,role) VALUES ('"+externalUser.getUserName()+"','"+"ROLE_CUSTOMER"+"') ;";
				getJdbcTemplate().execute(sqlroles);
			}
			else
			{
				String sqlroles="INSERT INTO user_roles (username,role) VALUES ('"+externalUser.getUserName()+"','"+"ROLE_MERCHANT"+"') ;";
				getJdbcTemplate().execute(sqlroles);
			}
			
			// insert
			String sql = "INSERT INTO external_users (lastName, firstName,sSN,dOB,email,address,city, phone,userName,PASSWORD,isMerchant,currentUser)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?, ?);";
			getJdbcTemplate().update(sql, externalUser.getLastName(),
					externalUser.getFirstName(),
					externalUser.getsSN(),
					externalUser.getdOB(),
					externalUser.getEmail(),
					externalUser.getAddress(),
					externalUser.getCity(),
					externalUser.getPhone(),
					externalUser.getUserName(),
					externalUser.getPassword(),
					externalUser.getIsMerchant(),
					1);
			
			String sqlUsersTable="INSERT INTO `users` (username,PASSWORD,enabled,Email) VALUES ('"+externalUser.getUserName()+"','"+externalUser.getPassword()+"',1,'"+externalUser.getEmail()+"');";
			getJdbcTemplate().execute(sqlUsersTable);
		}
	}
	
	@Override
	public List<String> ifnewRegisteredUserExists(String mail){
			
			String sql = "SELECT email FROM external_users WHERE email='"+mail+"'";
			System.out.println(mail+"initial"+sql);
			
		        //String sql = "SELECT MAX(accNo) AS last_updatedacc FROM accounts WHERE exUserId=(SELECT exUserId FROM external_users WHERE userName='"+userID+"') ";
		        List<String> pkeys = getJdbcTemplate().query(sql, new RowMapper<String>() {
		        @Override
		            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		                String pkey = rs.getString("email");	     
		                return pkey;
		            }
		     
		        });
		     
		        return pkeys; 
		       	
		}
	
	@Override
    public void update_password(String password, String email) {
        // TODO Auto-generated method stub
        String sql = "UPDATE external_users SET password=? WHERE email=?";
        String sql1 = "UPDATE users SET password=? WHERE email=?";
        String interUser="UPDATE internal_users SET PASSWORD=? WHERE email=?;";
        getJdbcTemplate().update(sql, password,email);
        getJdbcTemplate().update(sql1, password,email);
        getJdbcTemplate().update(interUser, password,email);
    }
	@Override
	public  String getexternID(String username)
	{
		String sql = "SELECT MAX(exUserId) AS exuserid FROM external_users WHERE userName='"+username+"';";
	    List<String> pkeys = jdbcTemplate.query(sql, new RowMapper<String>() {
	 
	        @Override
	        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
	            String pkey = rs.getInt("exuserid")+"";        
	 
	            return pkey;
	        }
	 
	    });
	 
	    return pkeys.get(0);
	}
	
}

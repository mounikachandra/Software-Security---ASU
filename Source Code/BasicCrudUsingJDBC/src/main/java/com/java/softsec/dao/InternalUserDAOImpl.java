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
import com.java.softsec.models.InternalUser;
import com.java.softsec.models.InternalUser2;

public class InternalUserDAOImpl implements InternalUserDAO {
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
	public void saveOrUpdate(InternalUser internalUser) {
		
		
		
		
		
		// TODO Auto-generated method stub
		// implementation details goes here...
		if (internalUser.getInUserId() > 0) {
			// update
			String sql = "UPDATE internal_users SET lastName = ?, firstName = ?,  sSN = ?, dOB = ?, email = ?, address = ?, city = ?, phone = ?, userName = ?,"+
					 " password = ?, isAdmin =? , currentEmployee = ? WHERE inUserId = ?";
			getJdbcTemplate().update(sql,
					internalUser.getLastName(),
					internalUser.getFirstName(),
					internalUser.getsSN(),
					internalUser.getdOB(),
					internalUser.getEmail(),
					internalUser.getAddress(),
					internalUser.getCity(),
					internalUser.getPhone(),
					internalUser.getUserName(),
					internalUser.getPassword(),
					internalUser.getIsAdmin(),
					internalUser.getCurrentEmployee(),
					internalUser.getInUserId()
					);
		} else {
			
			
			if(internalUser.getIsAdmin()==0)
			{
				String sqlroles="INSERT INTO user_roles (username,role) VALUES ('"+internalUser.getUserName()+"','"+"ROLE_EMPLOYEE"+"') ;";
				getJdbcTemplate().execute(sqlroles);
			}
			else
			{
				String sqlroles="INSERT INTO user_roles (username,role) VALUES ('"+internalUser.getUserName()+"','"+"ROLE_ADMIN"+"') ;";
				getJdbcTemplate().execute(sqlroles);
			}
			
			String sqlUsersTable="INSERT INTO `users` (username,PASSWORD,enabled,Email) VALUES ('"+internalUser.getUserName()+"','"+internalUser.getPassword()+"',1,'"+internalUser.getEmail()+"');";
			getJdbcTemplate().execute(sqlUsersTable);
			
			// insert
			String sql = "INSERT INTO internal_users (lastName,firstName,sSN,dOB,email,address,city,phone,userName,password,isAdmin,currentEmployee)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?, ?);";
			getJdbcTemplate().update(sql, 
					internalUser.getLastName(),
					internalUser.getFirstName(),
					internalUser.getsSN(),
					internalUser.getdOB(),
					internalUser.getEmail(),
					internalUser.getAddress(),
					internalUser.getCity(),
					internalUser.getPhone(),
					internalUser.getUserName(),
					internalUser.getPassword(),
					internalUser.getIsAdmin(),
					internalUser.getCurrentEmployee()
					);
		}
	}

	@Override
	public void delete(int inUserId) {
		// TODO Auto-generated method stub
		String sql = "UPDATE internal_users SET currentEmployee = 0 WHERE inUserId=?";
		getJdbcTemplate().update(sql,inUserId);
	}
	
	@Override
	public void activate(int inUserId) {
		// TODO Auto-generated method stub
		String sql = "UPDATE internal_users SET currentEmployee = 1 WHERE inUserId=?";
		getJdbcTemplate().update(sql,inUserId);
	}

	@Override
	public List<InternalUser> list()  {
		String sql = "SELECT * FROM internal_users";
		List<InternalUser> listInUser = getJdbcTemplate().query(sql, new RowMapper<InternalUser>() {
			 
	        @Override
	        public InternalUser mapRow(ResultSet rs, int rowNum) throws SQLException{
	            	InternalUser int_user = new InternalUser();
		            int_user.setInUserId(rs.getInt("inUserId"));
		            int_user.setLastName(rs.getString("lastName"));
		            int_user.setFirstName(rs.getString("firstName"));
		            int_user.setsSN(rs.getString("sSN"));
		            int_user.setdOB(rs.getDate("dOB"));
		            int_user.setEmail(rs.getString("email"));
		            int_user.setAddress(rs.getString("address"));
		            int_user.setCity(rs.getString("city"));
		            int_user.setPhone(rs.getInt("phone"));
		            int_user.setUserName(rs.getString("userName"));
		            int_user.setPassword(rs.getString("password"));
		            int_user.setIsAdmin(rs.getInt("isAdmin"));
		            int_user.setCurrentEmployee(rs.getInt("currentEmployee"));
		            
		            return int_user;
	            //return null;
	        }
	    });
		return listInUser;
	}

	@Override
	public InternalUser get(int inUserId) {
		// TODO Auto-generated method stub
				String sql = "SELECT * FROM internal_users WHERE inUserId=" + inUserId;
			    return getJdbcTemplate().query(sql, new ResultSetExtractor<InternalUser>() {
			 
			        @Override
			        public InternalUser extractData(ResultSet rs) throws SQLException, DataAccessException {
			            if (rs.next()) {
			                InternalUser internalUser = new InternalUser();
			                
			                internalUser.setInUserId(rs.getInt("inUserId"));
			                internalUser.setLastName(rs.getString("lastName"));
			                internalUser.setFirstName(rs.getString("firstName"));
							internalUser.setsSN(rs.getString("sSN"));
							internalUser.setdOB(rs.getDate("dOB"));
							internalUser.setEmail(rs.getString("email"));
							internalUser.setAddress(rs.getString("address"));
							internalUser.setCity(rs.getString("city"));
							internalUser.setPhone(rs.getInt("phone"));
							internalUser.setUserName(rs.getString("userName"));
							internalUser.setPassword(rs.getString("password"));
							internalUser.setIsAdmin(rs.getInt("isAdmin"));
							internalUser.setCurrentEmployee(rs.getInt("currentEmployee"));
			                
							return internalUser;
			            }
			 
			            return null;
			        }
			 
			    });
			}


	@Override
	public InternalUser getInUser(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM internal_users WHERE UserName= '"+username+"'";
	    return getJdbcTemplate().query(sql, new ResultSetExtractor<InternalUser>() {
	 
	        @Override
	        public InternalUser extractData(ResultSet rs) throws SQLException, DataAccessException {
	            if (rs.next()) {
	                InternalUser internalUser = new InternalUser();
	                
	                internalUser.setInUserId(rs.getInt("inUserId"));
	                internalUser.setLastName(rs.getString("lastName"));
	                internalUser.setFirstName(rs.getString("firstName"));
					internalUser.setsSN(rs.getString("sSN"));
					internalUser.setdOB(rs.getDate("dOB"));
					internalUser.setEmail(rs.getString("email"));
					internalUser.setAddress(rs.getString("address"));
					internalUser.setCity(rs.getString("city"));
					internalUser.setPhone(rs.getInt("phone"));
					internalUser.setUserName(rs.getString("userName"));
					internalUser.setPassword(rs.getString("password"));
					internalUser.setIsAdmin(rs.getInt("isAdmin"));
					internalUser.setCurrentEmployee(rs.getInt("currentEmployee"));
	                
					return internalUser;
	            }
	 
	            return null;
	        }
	 
	    });
	}

	@Override
    public List<String> ifnewRegisteredUserExists(String mail){
            
            String sql = "SELECT email FROM internal_users WHERE email='"+mail+"'";
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
	public void saveOrUpdate2(InternalUser2 internalUser) {
		// TODO Auto-generated method stub
				// implementation details goes here...
				if (internalUser.getInUserId() > 0) {
					// update
					String sql = "UPDATE internal_users SET lastName = ?, firstName = ?,  sSN = ?, dOB = ?, email = ?, address = ?, city = ?, phone = ?, userName = ?,"+
							 " password = ?, isAdmin =? , currentEmployee = ? WHERE inUserId = ?";
					getJdbcTemplate().update(sql,
							internalUser.getLastName(),
							internalUser.getFirstName(),
							internalUser.getsSN(),
							internalUser.getdOB(),
							internalUser.getEmail(),
							internalUser.getAddress(),
							internalUser.getCity(),
							internalUser.getPhone(),
							internalUser.getUserName(),
							internalUser.getPassword(),
							internalUser.getIsAdmin(),
							internalUser.getCurrentEmployee(),
							internalUser.getInUserId()
							);
				} else {
					
					
					if(internalUser.getIsAdmin()==0)
					{
						String sqlroles="INSERT INTO user_roles (username,role) VALUES ('"+internalUser.getUserName()+"','"+"ROLE_EMPLOYEE"+"') ;";
						getJdbcTemplate().execute(sqlroles);
					}
					else
					{
						String sqlroles="INSERT INTO user_roles (username,role) VALUES ('"+internalUser.getUserName()+"','"+"ROLE_ADMIN"+"') ;";
						getJdbcTemplate().execute(sqlroles);
					}
					
					String sqlUsersTable="INSERT INTO `users` (username,PASSWORD,enabled,Email) VALUES ('"+internalUser.getUserName()+"','"+internalUser.getPassword()+"',1,'"+internalUser.getEmail()+"');";
					getJdbcTemplate().execute(sqlUsersTable);
					
					// insert
					String sql = "INSERT INTO internal_users (lastName,firstName,sSN,dOB,email,address,city,phone,userName,password,isAdmin,currentEmployee)"
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?, ?);";
					getJdbcTemplate().update(sql, 
							internalUser.getLastName(),
							internalUser.getFirstName(),
							internalUser.getsSN(),
							internalUser.getdOB(),
							internalUser.getEmail(),
							internalUser.getAddress(),
							internalUser.getCity(),
							internalUser.getPhone(),
							internalUser.getUserName(),
							internalUser.getPassword(),
							internalUser.getIsAdmin(),
							internalUser.getCurrentEmployee()
							);
				}
		
	}
}

package com.java.softsec.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*import com.java.softsec.dao.AccountDAO;
import com.java.softsec.dao.AccountDAOImpl;
import com.java.softsec.dao.ActivitiesLogDAO;
import com.java.softsec.dao.ActivitiesLogDAOImpl;
import com.java.softsec.dao.ContactDAO;
import com.java.softsec.dao.ContactDAOImpl;
import com.java.softsec.dao.EmployeeTaskDAO;
import com.java.softsec.dao.EmployeeTaskDAOImpl;
import com.java.softsec.dao.InternalUserDAO;
import com.java.softsec.dao.InternalUserDAOImpl;
import com.java.softsec.dao.PaymentDAO;
import com.java.softsec.dao.PaymentDAOImpl;
import com.java.softsec.dao.ExternalUserDAO;
import com.java.softsec.dao.ExternalUserDAOImpl;*/


@Configuration
@ComponentScan(basePackages="com.java.softsec")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {
//	@Bean
//	public ViewResolver getViewResolver(){
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/views/");
//		resolver.setSuffix(".jsp");
//		return resolver;
//	}
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

//	@Bean
//	public DataSource getDataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://localhost:3306/software_sec1");
//		dataSource.setUsername("root");
//		dataSource.setPassword("root");
//
//		return dataSource;
//	}
//
//	@Bean
//	public ContactDAO getContactDAO() {
//		return new ContactDAOImpl(getDataSource());
//	}
//	
//	@Bean
//	public AccountDAO getAccountDAO() {
//		return new AccountDAOImpl(getDataSource());
//	}
//	
//	@Bean
//	public PaymentDAO getPaymentDAO(){
//		return new PaymentDAOImpl(getDataSource());
//	}
//	
//	@Bean
//	public ExternalUserDAO getExternalUserDAO(){
//		return new ExternalUserDAOImpl(getDataSource());
//	}
//	
//	@Bean
//	public InternalUserDAO getInternalUserDAO() {
//		return new InternalUserDAOImpl(getDataSource());
//	}
//	
//	@Bean
//	public EmployeeTaskDAO getEmployeeTaskDAO() {
//		return new EmployeeTaskDAOImpl(getDataSource());
//	}
//	
//	@Bean
//	public ActivitiesLogDAO getActivitiesLogDAO() {
//		return new ActivitiesLogDAOImpl(getDataSource());
//	}
//	
//	@Bean
//	public AccountSummaryDAO getAccountSummaryDAO() {
//		return new AccountSummaryDAOImpl(getDataSource());
//	}
}

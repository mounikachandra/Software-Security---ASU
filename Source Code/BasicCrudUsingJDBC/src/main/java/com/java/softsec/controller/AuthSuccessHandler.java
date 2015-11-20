package com.java.softsec.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.mysql.jdbc.log.LogFactory;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {
		
	//protected Log logger = Logger.getLog(this.getClass());
	 
  //  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	 HttpSession session;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
    	HttpSession session=request.getSession(false);
    	String userid=request.getParameter("username");
    	session.setAttribute("userid", userid);
    	handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }
 
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
      //      logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        
        response.sendRedirect(targetUrl);
    }
 
    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(Authentication authentication) {
        boolean isEmployee = false;
        boolean isAdmin = false;
        boolean isCustomer = false;
        boolean isMerchant=false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_EMPLOYEE")) {
                isEmployee = true;
                //session.setAttribute("isAdmin", 0);
                return "employee/emp_profile";
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
            	//session.setAttribute("isAdmin", 1);
            	isAdmin = true;
            	return"admin/emp_profile";            
            } else if (grantedAuthority.getAuthority().equals("ROLE_CUSTOMER")) {
            	//session.setAttribute("isMerchant", 0);
            	return "customer/Account";
            }
            else if (grantedAuthority.getAuthority().equals("ROLE_MERCHANT")) {
            	//session.setAttribute("isMerchant", 1);
            	return "merchant/Account";
            }
        }
 
        if (isEmployee) {
            return "/employee";
        } else if (isAdmin) {
            return "admin";
        }else if (isCustomer){
        	return "customer";
        } else {
            throw new IllegalStateException();
        }
    }
 
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}

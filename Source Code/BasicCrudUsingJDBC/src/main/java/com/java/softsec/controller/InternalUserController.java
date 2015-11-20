package com.java.softsec.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.softsec.dao.InternalUserDAO;
import com.java.softsec.models.ExternalUser;
import com.java.softsec.models.InternalUser;

//import com.java.softsec.dao.InternalUserDAO;
//import com.java.softsec.models.InternalUser;


@Controller
public class InternalUserController {
	@Autowired
    private InternalUserDAO inUserDAO;
	
	public void setInternalUserDAO(InternalUserDAO internalUserDAO){
		this.inUserDAO = internalUserDAO;
	}
	
	@RequestMapping(value={"/employee/emp_profile","/admin/emp_profile"})	
	public ModelAndView listInUser(ModelAndView model) throws IOException{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		String username = authentication.getName();
		System.out.println(username);
	    InternalUser listInUser = inUserDAO.getInUser(username);
	    model.addObject("listInUser", listInUser);
	    model.setViewName("emp_profile");	 
	    return model;
	}
	
	@RequestMapping(value="/ViewTransactionLog.html")	
	public ModelAndView showLog(ModelAndView model) throws IOException{
	    model.setViewName("trans_log");
	    return model;
	}
}

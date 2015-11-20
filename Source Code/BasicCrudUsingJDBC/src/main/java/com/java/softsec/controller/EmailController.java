package com.java.softsec.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.softsec.dao.ExternalUserDAO;
import com.java.softsec.dao.PaymentDAO;
import com.java.softsec.models.Account;
import com.java.softsec.models.ExternalUser;

@Controller
public class EmailController {
	@Autowired
    private ExternalUserDAO externalDAO;
	
	@RequestMapping(value="/ConfirmEmail")	
	public ModelAndView verifyEmail(ModelAndView model) throws IOException{
	    //List<Account> listAccount = accountDAO.list();
	    //model.addObject("listAccount", listAccount);
	    model.setViewName("confirmEmail");	 
	    return model;
	}
	
	@RequestMapping(value = "/emailService", method = RequestMethod.POST)
	public ModelAndView confirmMail(HttpServletRequest request) {
		String mail=request.getParameter("mail");
		System.out.println(mail);
		ExternalUser exUser = externalDAO.ifExists(mail);
		System.out.println("Hellooo"+exUser);
		if(exUser != null){
			String message = "EmailID Exists";
			ModelAndView model =new ModelAndView("transfersuccess");
			model.addObject("message",message);
			return model;
		}	
		
		ModelAndView model =new ModelAndView("sendMail");
	    return model;
	}
	

}

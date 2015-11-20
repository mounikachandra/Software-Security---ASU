package com.java.softsec.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.java.softsec.dao.AccountDAO;
import com.java.softsec.dao.ExternalUserDAO;
import com.java.softsec.models.Account;
import com.java.softsec.models.AccountManagement;
import com.java.softsec.models.Contact;
import com.java.softsec.models.ExternalUser;
import com.java.softsec.models.SSDSAKeyPairGenerator;
import com.java.softsec.models.Utility;

@Controller
public class ExtUserController {
	@Autowired
	private ExternalUserDAO externalUserDAO;
	
	public void setExternalUserDAO(ExternalUserDAO externalUserDAO){
		this.externalUserDAO = externalUserDAO;
	}
	@Autowired
	private AccountDAO accountDAO;
	public void setAccountDAO(AccountDAO accountDAO){
		this.accountDAO = accountDAO;
	}
	
	@RequestMapping(value={"/customer/extProfile", "/merchant/extProfile"}, method = RequestMethod.GET)
	public ModelAndView editProfile(HttpSession session) {		
		String userid = (String)session.getAttribute("userid");  
	    
	    ExternalUser extUser=externalUserDAO.get(userid);
	    
	    ModelAndView model = new ModelAndView("extProfile");
	    model.addObject("extUser", extUser);
	    model.addObject("statusmsg", "");
	
	    return model;
	}
	@RequestMapping(value="/saveProfile",method=RequestMethod.POST)
	public ModelAndView saveProfile(@ModelAttribute ExternalUser extUser, HttpSession session) {
		
		String userid = (String)session.getAttribute("userid");  
		
		
		if(extUser.getAddress().length() >0 && extUser.getPhone().length() >0 && extUser.getCity().length() >0 && extUser.getEmail().length() >0 )
		{
		
			externalUserDAO.saveOrUpdate(extUser,userid);
		 
		}
		 ModelAndView model = new ModelAndView("extProfile");
		    model.addObject("extUser", extUser);
		    model.addObject("statusmsg", "changes saved..");
		
		    return model;
	}
	
	@RequestMapping(value="/sign_up",method=RequestMethod.GET)
	public ModelAndView saveProfile(ModelAndView model,HttpSession session) throws IOException{
		
		
		model.setViewName("sign_up");
		model.addObject("form", new ExternalUser());
		
		return model;
	}
	@RequestMapping(value="/sign_up",method=RequestMethod.POST)
	public ModelAndView saveProfi(ModelAndView model,HttpSession session, ExternalUser extUser, HttpServletRequest request) throws IOException{
		
		String errormsg="";
		boolean validcaptcha=false;
		 String remoteAddr = request.getRemoteAddr();
	       ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
	       reCaptcha.setPrivateKey("6LdI7vwSAAAAAM3b02ji1eSr-jHPp2FgHP1l_-RU");
	       String challenge = request.getParameter("recaptcha_challenge_field");
	       String uresponse = request.getParameter("recaptcha_response_field");
	       ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
	    
	       if (reCaptchaResponse.isValid()) {
	    	
	        validcaptcha=true;
	       } else {
	           
	        errormsg="wrong captcha";    }
			
		System.out.println();

		extUser.setsSN("123456789");
		extUser.setEmail(session.getAttribute("email_sign_up")+"");
		if(validcaptcha)
		{
			if(Utility.validateFirstName(extUser.getFirstName()) && validcaptcha)
			{
				if(Utility.validateLastName(extUser.getLastName()))
				{
					if(Utility.validateUserName(extUser.getUserName()))
					{
						String[] pass=new String[2];
						pass=extUser.getPassword().split(",");
						extUser.setPassword(pass[0]);
	//					if(Utility.validatePassword(pass[0]) && pass[0].equals(pass[1]))
	//					{
							if(Utility.isPhoneNumberValid(extUser.getPhone()))
							{
								if(Utility.validateAddress(extUser.getAddress()))
								{
									if(Utility.validateFirstName(extUser.getCity()))
									{
										externalUserDAO.saveOrUpdate(extUser, null);
										errormsg="registered successfully. Please logout and login again";
										String temprorayr= externalUserDAO.getexternID(extUser.getUserName());						
										
										SSDSAKeyPairGenerator gen=new SSDSAKeyPairGenerator();
										gen.generatePair(temprorayr, temprorayr);	
										
										
										
										
										
									}
									else
									{
										errormsg="invalid city";
									}
								}
								else
								{
									errormsg="invalid address";
								}
							}
							else
							{
								errormsg="invalid phone number";
							}
	//					}
	//					else
	//					{
	//						errormsg="invalid password";
	//					}
					}
					else
					{
						errormsg="invalid username";
					}
				}
				else
				{
					errormsg="invalid last name";
				}
					
			}
			else
			{
				errormsg="invalid first name";
			}
		}
		else
		{
			 errormsg="wrong captcha";
		}
		
		model.addObject("errormsg", errormsg);
		
		return model;
	}
	
	
  
}




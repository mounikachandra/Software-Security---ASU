package com.java.softsec.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.softsec.core.Myencoder;
import com.java.softsec.core.Otp;
import com.java.softsec.models.ExternalUser;
import com.java.softsec.models.Contact;
import com.java.softsec.dao.*;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
		private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
		
		@Autowired
		private ExternalUserDAO externalUserDAO;
		
		@Autowired
		private InternalUserDAO internalUserDAO;
		
		public void setExternalUserDAO(ExternalUserDAO externalUserDAO){
			this.externalUserDAO = externalUserDAO;
		}
		
		public void setInternalUserDAO(InternalUserDAO internalUserDAO){
			this.internalUserDAO = internalUserDAO;
		}
		
		
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String home() {
			//System.out.println(request.getRemoteAddr());
			//return "home";
			return "login";
			}
						
			@RequestMapping(value = "/newcontactemailandssn", method = RequestMethod.GET)
			public String newcontactemailandssn() {			
			return "register1";
			}
			
			@RequestMapping(value = "/admin", method = RequestMethod.GET)
			public String newcontactemailandssn11() {			
			return "admin";
			}
			@RequestMapping(value = "/newcontactemailandssn2", method = RequestMethod.GET)
			public String newcontactemailandssn2() {			
				return "register2";
			}
			
			
			
			
			
			
			
			
			
			@RequestMapping(value = "/finalsignup", method = RequestMethod.POST)
			public ModelAndView finalscreen(HttpServletRequest request,HttpSession session,ModelAndView model) {
				
				String otp = request.getParameter("OTP");
				String email=request.getParameter("email");
				
				
				
				String otpStringgen=session.getAttribute("otp-valid-str")+"";	
				session.setAttribute("email_sign_up",email)	;	
				
				System.out.println(otpStringgen);
				System.out.println(otp);
				if(otp.equals(otpStringgen))
				{
					return new ModelAndView("redirect:/sign_up");
				}
				else{
					//model.addObject("form", new ExternalUser());
					model.setViewName("register1");
				}
				return model;
			}		
			
			@RequestMapping(value = "/admin/newContact")
			public ModelAndView newContac(ModelAndView model,HttpSession session,@ModelAttribute Contact cnt) throws IOException{
							
				String otpStringgen=session.getAttribute("otp-valid-str")+"";
				
				if(otpStringgen.equals(cnt.getAddress()))
				{
				
			    Contact newContact = new Contact();
			    System.out.println("newcontact");
			    model.addObject("contact", newContact);
			    model.setViewName("Register");
			    return model;
				}
				else
				{
					model.setViewName("register1");
					return model;
				}
			}		
			
			@RequestMapping(value = "/forgotpassword1", method = RequestMethod.GET)
			public String forgotpassword1() {
			return "forgotpassword1";
			}
			
			@RequestMapping(value = "/forgotpassword2", method = RequestMethod.GET)
			public String forgotpassword2() {
			return "forgotpassword2";
			}
			
			@RequestMapping(value = "/sendtoemail", method = RequestMethod.POST)
			public ModelAndView sendemail(ModelAndView model,HttpServletRequest request,HttpSession session) {
			    String email=request.getParameter("email");
			    String ssninput = request.getParameter("ssn1");
			    session.setAttribute("ssn_entered", ssninput);
			    //System.out.println("inside email");
			    
			    List<String> retruneduserexists= externalUserDAO.ifnewRegisteredUserExists(email);
			   //System.out.println("ext"+retruneduserexists); 
			  
				   
			   if(retruneduserexists.size() >0)
			   {
				  // System.out.println("userexists");
			    return new ModelAndView("redirect:/newcontactemailandssn");
			   }
			   
			   else{
				   
				   //System.out.println("send email");
				    String otpString=Otp.generatePIN();
				    session.setAttribute("otp-valid-str", otpString);

				    com.java.softsec.core.email.senemail(email,otpString);
				   return new ModelAndView("redirect:/newcontactemailandssn2");	
			   }
			   
			}
			
			@RequestMapping(value = "/sendtoemailforgotpassword", method = RequestMethod.POST)
			public ModelAndView sendemailforgotpassword(ModelAndView model,HttpServletRequest request,HttpSession session) {
			    String email=request.getParameter("email");
			    //System.out.println("email :"+email);
			    List<String> retruneduserexists= externalUserDAO.ifnewRegisteredUserExists(email);
			    
			   if(retruneduserexists.size()==0){
				   return new ModelAndView("redirect:/forgotpassword1");
			   }
			   else{
			    String otpString=Otp.generatePIN();
			    session.setAttribute("otp-valid-str", otpString);
			    session.setAttribute("Email", email);
			    com.java.softsec.core.email.senemail(email,otpString);
			   return new ModelAndView("redirect:/forgotpassword2"); 
			   }
			}
			
			@RequestMapping(value = "/login", method = RequestMethod.GET)
			public ModelAndView login(@RequestParam(value = "error", required = false) String error,
				@RequestParam(value = "logout", required = false) String logout) {
		 
			  ModelAndView model = new ModelAndView();
			 
			  model.setViewName("login");
		 
			  return model;	 
			}
			
			@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
			public String loginfalied(ModelMap model) {		
				model.addAttribute("error", "true");
				return "login";
			}		
			
			@RequestMapping(value = "/forgotpasswordfinal", method = RequestMethod.POST)
            public String forgotpasswordfinal(HttpServletRequest request,HttpSession session) {            
                
                String otp = request.getParameter("OTP");
                String otpStringgen=session.getAttribute("otp-valid-str")+"";
                String emailentered = request.getParameter("email");
                String passwordentered = request.getParameter("Password");
                
                String emailinsession = session.getAttribute("Email")+"";
                
                //System.out.println("otp "+otp);
                //System.out.println("otpsession "+otpStringgen);
                //System.out.println("emailent "+emailentered);
                //System.out.println("emailinsession "+emailinsession);
                if(otp.equals(otpStringgen) && emailentered.equals(emailinsession))
                {                
                    //String password = Myencoder.hashedpassword(passwordentered); 
                    //System.out.println("pass"+password);
                    externalUserDAO.update_password(passwordentered, emailentered);
                    
                    
                    
                    return "login";
                }
                else{
                    return "forgotpassword1";
                }            
            }
				
	}

	
	
	
	
	


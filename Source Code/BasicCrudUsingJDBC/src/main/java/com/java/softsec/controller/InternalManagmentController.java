package com.java.softsec.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import javax.validation.ConstraintValidatorContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.softsec.dao.ExternalUserDAO;
import com.java.softsec.dao.ExternalUserDAOImpl;
import com.java.softsec.dao.InternalUserDAO;
import com.java.softsec.dao.InternalUserDAOImpl;
import com.java.softsec.models.Account;
import com.java.softsec.models.ExternalUser;
import com.java.softsec.models.InternalUser;
import com.java.softsec.models.InternalUser2;
import com.java.softsec.models.InternalUserManagmentForm;

@Controller
public class InternalManagmentController {
	@Autowired
	private InternalUserDAO internalUserDOA;
	public void setInternalUserDAO(InternalUserDAO internalUserDAO){
		this.internalUserDOA = internalUserDAO;
	}

	@RequestMapping(value = "/admin/InternalAccountManagement.html", method = RequestMethod.GET)
	public ModelAndView initForm(ModelAndView model){
		System.out.println("GET is executed");
		InternalUserManagmentForm form = new InternalUserManagmentForm();
		model.addObject("form", form);
		List<InternalUser> listOfUsers = internalUserDOA.list();
//	    System.out.println(listOfUsers.get(0));
	    model.addObject("listOfUsers", listOfUsers);
		String message = "";
		model.addObject("message", message);
	    model.setViewName("mng_in_users");
		return model;
	}
	
	private int chosen_user_id = -1;
	
	@RequestMapping(value = "/admin/InternalAccountManagement.html", method = RequestMethod.POST)
	public ModelAndView submitForm(ModelAndView model, /*@Validated*/ InternalUserManagmentForm form, BindingResult result){
//		System.out.println("POST is executed");
		List<InternalUser> listOfUsers = internalUserDOA.list();
//		System.out.println(listOfUsers.get(0));
			String message = "Completed transaction: <br>" +
					"chosen user: " + form.getUser() + "\tchosen operation: " + form.getChoice();
//			System.out.println("chosen user: " + form.getUser() + "\tchosen operation: " + form.getChoice());
			if(form.getUser()!=null){
			Matcher matcher = Pattern.compile("\\d+").matcher(form.getUser());
			matcher.find();
			chosen_user_id = Integer.valueOf(matcher.group());
//			System.out.println("debug::chosen_use_id: " + chosen_user_id);
			int index = 0;
			for(int i = 0 ; i < listOfUsers.size() ; i++){
				if(chosen_user_id == listOfUsers.get(i).getInUserId()){
					index = i;
				}
			}
			if(form.getChoice().equalsIgnoreCase("delete")){
				// perform deletion:
				if(listOfUsers.get(index).getCurrentEmployee() == 1){
//					System.out.println("chosen_user_id = " + chosen_user_id);
					internalUserDOA.delete(chosen_user_id);
				}
				else
					message += "<br> this user is already deleted.";
				model.setViewName("mng_in_users"); //mng_accounts
			}
			else if(form.getChoice().equalsIgnoreCase("edit")){
//				Matcher matcher = Pattern.compile("\\d+").matcher(form.getUser());
//				matcher.find();
//				chosen_user_id = Integer.valueOf(matcher.group());
				// perform edit -> send to edit 
				return new ModelAndView("redirect:/admin/InternalAccountManagement/edit.html");
			}
			else if(form.getChoice().equalsIgnoreCase("activate")){
//				Matcher matcher = Pattern.compile("\\d+").matcher(form.getUser());
//				matcher.find();
//				chosen_user_id = Integer.valueOf(matcher.group());
				if(listOfUsers.get(index).getCurrentEmployee() == 0){
					internalUserDOA.activate(listOfUsers.get(index).getInUserId());
				}
				else
					message += "<br> this account is already active.";
				model.setViewName("mng_in_users");
			}
			
			}else{

				model.setViewName("notallowed");
			}
			 if(form.getChoice().equalsIgnoreCase("add")){
				return new ModelAndView("redirect:/admin/InternalAccountManagement/add.html");
			}
			listOfUsers = internalUserDOA.list();
		    model.addObject("listOfUsers", listOfUsers);
			model.addObject("message", message);
			model.addObject("form", form);
			
//		}
		return model;
	}
	
	
	InternalUser internalUser;
	@RequestMapping(value = "/admin/InternalAccountManagement/edit.html", method = RequestMethod.GET)
	private ModelAndView edit_user_get(ModelAndView model){
//		System.out.println("to edited.");
//		System.out.println("chosen user id = " + chosen_user_id);
		internalUser = internalUserDOA.get(chosen_user_id);
		model.addObject("data", internalUser);
		model.addObject("form", new InternalUser());
//		System.out.println(internalUser.getInUserId());
		String message = "";
		model.addObject("message", message);
	    model.setViewName("edit_in_user");
		return model;
	}
	
	private String isValid_edit(String ln, String fn){
		if(ln.matches("[a-zA-z]+([ '-][a-zA-Z]+)*")){
			if(fn.matches("[a-zA-Z][a-zA-Z]*")){
				return "";
			}
			else{
				return "\'" + fn + "\' is not a valid first name.<br>";
			}
		}
		else{
			return "\'" + ln + "\' is not a valid last name.<br>";
		}
	}
	
	@RequestMapping(value = "/admin/InternalAccountManagement/edit.html", method = RequestMethod.POST)
	private ModelAndView edit_user_post(ModelAndView model, InternalUser form){
		System.out.println("chosen user id = " + chosen_user_id);
		String message = isValid_edit(form.getLastName(), form.getFirstName());
		if(message.equalsIgnoreCase("")){
			internalUser.setLastName(form.getLastName());
			internalUser.setFirstName(form.getFirstName());
			internalUser.setCurrentEmployee(form.getCurrentEmployee());
			internalUser.setIsAdmin(form.getIsAdmin());
			if(internalUser.getInUserId() > 0) internalUserDOA.saveOrUpdate(internalUser);
			else message += "form id: " + form.getInUserId() + "cannot edit!!!"; 
			model.addObject("form", internalUser);
//			System.out.println(form.getInUserId());
			message += "User information has been edited";
			model.addObject("message", message);
		    model.setViewName("edit_in_user");
		}
		else{
			model.addObject("data", internalUser);
			model.addObject("form", new InternalUser());
			model.addObject("message", message);
			model.setViewName("edit_in_user");
		}
	    System.out.println("edited.");
		return model;
	}
	
public boolean isValid_username(String str) {
		
	System.out.println("username = " + str + "\n");
		if(str == null) return true;
		//if (!str.matches("(?![<>script]).*")){
			//DriverManagerDataSource dataSource = new DriverManagerDataSource();
//			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//			dataSource.setUrl("jdbc:mysql://localhost:3306/software_sec1");
//			dataSource.setUsername("root");
//			dataSource.setPassword("root");
			
			//InternalUserDAO inUsrDoa = new InternalUserDAOImpl(dataSource);
			
			List<InternalUser> inUsrList = internalUserDOA.list();
			
			for(int i = 0 ; i < inUsrList.size() ; i++){
				if(inUsrList.get(i).getUserName().equalsIgnoreCase(str))
					return false;
			}
			
			return true;
		//}
	}

private String isValid_add(InternalUser2 inUsr, BindingResult br){
	
	System.out.println("*******************************");
	System.out.println(inUsr.getAddress());
	System.out.println(inUsr.getdOB());
	
	
	
	if(inUsr.getLastName().matches("[a-zA-z]+([ '-][a-zA-Z]+)*")){
		if(inUsr.getFirstName().matches("[a-zA-Z][a-zA-Z]*")){
			if(inUsr.getsSN().matches("\\d{9}")){
				if(inUsr.getdOB() != null){ if(!br.hasErrors()){
					if(inUsr.getEmail().matches("[1-9a-zA-Z-_]+@[1-9a-zA-Z-_]+\\.(org|com|edu|net|co)")){
						if(inUsr.getAddress()!=null){
							if(inUsr.getCity().matches("[A-Za-z][A-Za-z]+")){
								if(((inUsr.getPhone()+"").matches("\\d{10}")) ||
								   ((inUsr.getPhone()+"").matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) ||
								   ((inUsr.getPhone()+"").matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) ||
								   ((inUsr.getPhone()+"").matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))){
									
									if(isValid_username(inUsr.getUserName())){
										if(!inUsr.getPassword().matches(".*(<|>|script).*") && inUsr.getPassword().length() >= 8 && inUsr.getPassword().length() <= 30){
											return "";
										}
										else{
											return "Password invalid. must be at least 8 chars long and at most 30 chars long";
										}
									}
									else{
										return "Username exists, please change.";
									}
								}
								else{
									return "Not a valid phone number.";
								}
							}
							else{
								return "Not a valid city. ex: tempe or Tempe";
							}
						}
						else{
							return "Not a valid address. valid  address is 1234 S main st";
						}
					}
					else{
						return "Not a valid email.";
					}
				}
				else{
					System.out.println("data:" + inUsr.getdOB().toString() + "\n"
									   + "binding result = " + br.hasErrors() + "\n");
					return "Not a valid date, must be in the format yyyy-mm-dd<br>";
				} }else return "Not a valid date, must be in the format yyyy-mm-dd "+"DOB cannot be empty.<br>";
			}
			else{
				return "SSN must be 10 digits ex: 0123456789.<br>";
			}
		}
		else{
			return "Not a valid first name.<br>";
		}
	}
	else{
		return "Not a valid last name.<br>";
	}
}
	
/*	private String isValid_add(InternalUser2 form, BindingResult br){
		if(form.getLastName().matches("[a-zA-z]+([ '-][a-zA-Z]+)*")){
			if(form.getFirstName().matches("[a-zA-Z][a-zA-Z]*")){
				if(form.getsSN().matches("\\d{09}")){
					if(form.getdOB() != null){ 
						//System.out.println(br.hasErrors());
					//	if(!br.hasErrors()){
							if(form.getEmail().matches("[1-9a-zA-Z-_]+@[1-9a-zA-Z-_]+\\.(org|com|edu|net|co)")){
							if(form.getAddress()!=null){
								if(form.getCity().matches("[A-Za-z][A-Za-z]+")){
									if((form.getPhone()+"").matches("/d{10}/")){
										
										if(isValid_username(form.getUserName())){
											if(!form.getPassword().matches(".*(<|>|script).*") && form.getPassword().length() >= 8 && form.getPassword().length() <= 30){
												return "";
											}
											else{
												return "Password invalid. must be at least 8 chars long and at most 30 chars long";
											}
										}
										else{
											return "Username exists, please change.";
										}
									}else{
										return "Not a valid phone number.";
									}
								}
								else{
									return "Not a valid city. ex: tempe or Tempe";
								}
							}
							else{
								return "Not a valid address. valid  address is 1234 S main st";
							}
						}
						else{
							return "Not a valid email.";
						}
					//}
//					else{
//						System.out.println("data:" + inUsr.getdOB().toString() + "\n"
//											   + "binding result = " + br.hasErrors() + "\n");
//							return "Not a valid date, must be in the format yyyy-mm-dd<br>";
//						} 
					}else return "Not a valid date, must be in the format yyyy-mm-dd "+"DOB cannot be empty.<br>";
				}
				else{
					return "SSN must be 10 digits ex: 0123456789.<br>";
				}
			}
			else{
				return "Not a valid first name.<br>";
			}
		}
		else{
			return "Not a valid last name.<br>";
		}
	}*/
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value = "/admin/InternalAccountManagement/add.html", method = RequestMethod.GET)
	private ModelAndView add_user_get(ModelAndView model){
//		System.out.println("to add.");
//		System.out.println("chosen user id = " + chosen_user_id);
		model.addObject("form", new InternalUser());
		String message = "";
		model.addObject("message", message);
	    model.setViewName("add_in_user");
		return model;
	}
	
	@RequestMapping(value = "/admin/InternalAccountManagement/add.html", method = RequestMethod.POST)
	private ModelAndView add_user_post(ModelAndView model, InternalUser2 form, BindingResult br){
//		System.out.println("chosen user id = " + chosen_user_id);
		String message = isValid_add(form, br);
		if(message.equalsIgnoreCase("")){
			internalUserDOA.saveOrUpdate2(form);
//			System.out.println(form.getInUserId());
			message += "User information has been added";
		}
		model.addObject("form", form);
		model.addObject("message", message);
	    model.setViewName("add_in_user");
//	    System.out.println("added.");
		return model;
	}
}

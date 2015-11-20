package com.java.softsec.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.softsec.dao.AccountDAO;
import com.java.softsec.dao.PaymentDAO;
import com.java.softsec.models.Account;
import com.java.softsec.models.TransferPayment;

@Controller
public class TransferController {
	@Autowired
	private AccountDAO accountDAO;
	public void setAccountDAO(AccountDAO accountDAO){
		this.accountDAO = accountDAO;
	}
	
	@Autowired
	private PaymentDAO paymentDAO;
	public void setPaymentDAO(PaymentDAO paymentDAO){
		this.paymentDAO = paymentDAO;
	}
	
	@RequestMapping(value="/customer/TranPayment")	
	public ModelAndView custAccess(ModelAndView model) throws IOException{
		//String userid = (String)session.getAttribute("userid"); 
		//List<Account> listAccount = accountDAO.list(userid);
		//TransferPayment tp= new TransferPayment();			
		//model.addObject("form",tp);
		//model.addObject("listOfAccounts", listAccount);
		model.setViewName("accessDenied");
		return model;				
	}
	
	@RequestMapping(value="/merchant/TranPayment")	
	public ModelAndView createTransfREq(ModelAndView model,HttpSession session) throws IOException{
		String userid = (String)session.getAttribute("userid"); 
		List<Account> listAccount = accountDAO.list(userid);
		TransferPayment tp= new TransferPayment();			
		model.addObject("form",tp);
		model.addObject("listOfAccounts", listAccount);
		model.setViewName("TranPayment");
		return model;			
		
		
	}
	@RequestMapping(value="/merchant/TranPayment", method = RequestMethod.POST  )	
	public ModelAndView createTransfREq(ModelAndView model,HttpSession session, TransferPayment form) throws IOException{		
		int accountnumber=0;
		double amount;
		String errorText="";
		
		boolean validamount=false;
		
		boolean validaccnumber=false;
		boolean validaccexist=false;
		try
		{
			amount=  Double.parseDouble(form.getAmount());
			validamount=true;
		}
		catch(NumberFormatException e)
		{
		  errorText="Invalid amount entered";
		}
		
		try
		{
			accountnumber=  Integer.parseInt(form.getCustomeracc());							
			validaccnumber=true;
		}
		catch(NumberFormatException e)
		{
		  errorText="Invalid account entered";
		}	
		
		Account isCustAcc=accountDAO.get(accountnumber);
		
		if(isCustAcc != null)
		{
			validaccexist=true;
			
		}
		else
		{
			errorText="customer account does not exists";
		}
		
		if(validaccexist && validaccnumber && validamount)
		{
			
			paymentDAO.insertPayment( Integer.parseInt(form.getAccount()), 
					Integer.parseInt(form.getCustomeracc()),
					Double.parseDouble(form.getAmount()));
			
			errorText="request successful";
		}
		else
		{
			
		}
		
		
		String userid = (String)session.getAttribute("userid"); 
		List<Account> listAccount = accountDAO.list(userid);
		model.addObject("listOfAccounts", listAccount);
	
		
		model.addObject("errormsg", errorText);
		model.addObject("form",form);
		model.setViewName("TranPayment");
		
		
		return model;
	
	}
	
	
}

package com.java.softsec.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.softsec.dao.AccountDAO;
import com.java.softsec.dao.EmployeeTaskDAO;
import com.java.softsec.dao.ExternalUserDAO;
import com.java.softsec.models.Account;
import com.java.softsec.models.AccountManagement;
import com.java.softsec.models.AccountSummary;
import com.java.softsec.models.ExternalUser;
@Controller
public class AccountController {
	@Autowired
    private AccountDAO accountDAO;
	
	public void AccountDAO(AccountDAO accountDAO){
		this.accountDAO = accountDAO;
	}
	
	@Autowired
	private EmployeeTaskDAO empDAO;
	public void setEmployeeTaskDAO(EmployeeTaskDAO employeeTaskDAO){
		this.empDAO = employeeTaskDAO;
	}
	
	@Autowired	
	private ExternalUserDAO externalUser;	
	public void setExternalUserDAO(ExternalUserDAO externalUserDAO){
		this.externalUser = externalUserDAO;
	}
	
	//hello
	@RequestMapping(value={"/customer/Account","/merchant/Account"})	
	public ModelAndView listAccount(ModelAndView model,HttpServletRequest request,HttpSession session) throws IOException{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		String usrname = authentication.getName();
		//System.out.println(usrname);
		session.setAttribute("userid", usrname);
	    List<Account> listAccount = accountDAO.list(usrname);
	    ExternalUser user=externalUser.get(usrname);
	    //System.out.println(user.getExUserId());	 
	    session.setAttribute("externid", user.getExUserId());
	    //System.out.println(user.getExUserId());
	    model.addObject("listAccount", listAccount);
	    model.setViewName("account");	 	    	    
		return model;
	}
	
	@RequestMapping(value={"/customer/accmgmt","/merchant/accmgmt"})
	public ModelAndView changeAccount(ModelAndView model,HttpSession session) throws IOException{
		String userid = (String)session.getAttribute("userid"); 
		List<Account> listAccount = accountDAO.list(userid);
		model.addObject("listOfAccounts", listAccount);
		model.addObject("form", new AccountManagement());
		model.setViewName("accmgmt");
		return model;		
	}
	
	@RequestMapping(value={"/customer/accmgmt", "/merchant/accmgmt"}, method = RequestMethod.POST )
	public ModelAndView rndom(ModelAndView model,HttpSession session, AccountManagement form) throws IOException{	
		String userid = (String)session.getAttribute("userid"); 
		String statusText="";
		if(form.getAction().equals("create"))
		{
			statusText="request for account creation submitted";
			empDAO.createEmpTaskCreateAcc(userid);
		}
		else if(form.getAction().equals("delete"))
		{
			statusText="request for account deletion submitted";
			empDAO.deleteAccountReq(Integer.parseInt(form.getAccount()));
		}
	
		List<Account> listAccount = accountDAO.list(userid);
		model.addObject("listOfAccounts", listAccount);
		model.addObject("form", new AccountManagement());
		model.addObject("statustext",statusText );
		model.setViewName("accmgmt");
		return model;
	
		
	}
	
	@RequestMapping(value={"/customer/viewTransactions","/merchant/viewTransactions"})	
	public ModelAndView Transaction(ModelAndView model,HttpSession session) throws IOException{
		
		String externid = session.getAttribute("externid")+"";
		//System.out.println("userid"+externid);
	   List<Account> lstAccount = accountDAO.getAllAccounts(Integer.parseInt(externid));
	    model.addObject("lstAccount", lstAccount);
	    model.setViewName("viewTransactions");	 
	    return model;
	}
	
	
	@RequestMapping(value ={"/customer/transferService","/merchant/transferService"}, method = RequestMethod.POST)
	public ModelAndView TransferRequest(HttpServletRequest request) {
		//access UI parameters
	//	String accountNumber = request.getParameter("vandita");
	//	System.out.println(accountNumber+" Hi Somu");	
		boolean validfromAccount=false;
		boolean toAccount=false;
		boolean validamount=false;
		ModelAndView model;
		model =new ModelAndView("transfersuccess");
		int fromAccountNumber,toAccountNumber;
		double amount;
		try
		{
			fromAccountNumber=Integer.parseInt(request.getParameter("fromAcc"));
			validfromAccount=true;
		}
		catch(NumberFormatException e){
			model.addObject("message","Invalid Account Number");
			return model;
		}
		
		try{
			toAccountNumber=Integer.parseInt(request.getParameter("toAcc"));
			toAccount=true;
		}
		catch(NumberFormatException e){
			model.addObject("message","Invalid Account Number");
			return model;
		}
		try{
		amount=Double.parseDouble(request.getParameter("amount"));
		validamount=true;
		}
		catch(NumberFormatException e){
			model.addObject("message","Invalid Amount");
			return model;
		}
//		System.out.println(fromAccountNumber+" to Account");
		//System.out.println(toAccountNumber+" Hi Somu");
	//	System.out.println(amount+"amount");
		if(validfromAccount && toAccount && validamount){
		String message=accountDAO.setTransfer(fromAccountNumber, toAccountNumber,amount);
		model =new ModelAndView("transfersuccess");
		model.addObject("message",message);
	    return model;
		}
		return model;
	}
	
	@RequestMapping(value={"/customer/Transfer","/merchant/Transfer"})	
	public ModelAndView transfers(ModelAndView model, HttpSession session) throws IOException{		
		String externid = session.getAttribute("externid")+"";
		List<Account> lstAccount = accountDAO.getAllAccounts(Integer.parseInt(externid));
		model.addObject("lstAccount", lstAccount);
	    model.setViewName("Transfer");	 
	    return model;
	}
	
	/*@RequestMapping(value="/Authorize")	
	public ModelAndView authorizeRequests(ModelAndView model) throws IOException{
		List<Account> lstAccount = accountDAO.getAllAccounts(1000001);
		model.addObject("lstAccount", lstAccount);
	    model.setViewName("authorize");	 
	    return model;
	}*/
	
	@RequestMapping(value={"/customer/creditDebit","/merchant/creditDebit"})	
	public ModelAndView creditDebit(ModelAndView model, HttpSession session) throws IOException{ 
		
		String externid = session.getAttribute("externid")+"";
		 List<Account> listAccount = accountDAO.getAllAccounts(Integer.parseInt(externid));
		    
		    model.addObject("listAccount1", listAccount);
		    model.setViewName("creditDebit");	 
		
	    return model;
	}
	
	
	@RequestMapping(value = {"/customer/Credit","/merchant/Credit"}, method = RequestMethod.POST)
	public ModelAndView Credit(HttpServletRequest request) throws IOException{
		boolean fAcc=false;
		boolean chkAmount =false;
		ModelAndView model;
		String AccNo="";
		String amount="";
		double amt;
		int accNo;
		model =new ModelAndView("transfersuccess");	
		try{
		AccNo=request.getParameter("fromAcc");
		accNo=Integer.parseInt(AccNo);
		fAcc=true;
		}catch(NumberFormatException e){
			model.addObject("message","Invalid Account Number");
			return model;
		}
		
		try{
			amount=request.getParameter("amount");
			amt=Double.parseDouble(amount);
			chkAmount=true;
			}catch(NumberFormatException e){
				model.addObject("message","Invalid Amount");
				return model;
			}		
		//String amount=request.getParameter("amount");
		if(fAcc&&chkAmount){
		String message=accountDAO.Credit(accNo, amt);			    
	//	ModelAndView model =new ModelAndView("transfersuccess");
		model.addObject("message",message);
	    return model;
		}
		return model;
	}
	
	
	@RequestMapping(value = {"/customer/Debit","/merchant/Debit"}, method = RequestMethod.POST)
	public ModelAndView Debit(HttpServletRequest request) throws IOException{
		boolean fAcc=false;
		boolean chkAmount =false;
		ModelAndView model;
		String AccNo="";
		String amount="";
		double amt;
		int accNo;
		model =new ModelAndView("transfersuccess");	
		try{
		AccNo=request.getParameter("fromAcc");
		accNo=Integer.parseInt(AccNo);
		fAcc=true;
		}catch(NumberFormatException e){
			model.addObject("message","Invalid Account Number");
			return model;
		}
		
		try{
			amount=request.getParameter("amount");
			amt=Double.parseDouble(amount);
			chkAmount=true;
			}catch(NumberFormatException e){
				model.addObject("message","Invalid Amount");
				return model;
			}		
		//String amount=request.getParameter("amount");
		if(fAcc&&chkAmount){
			String message=accountDAO.Debit(accNo, amt);			    
	//	ModelAndView model =new ModelAndView("transfersuccess");
		model.addObject("message",message);
	    return model;
		}
		return model;
	}
	
		@RequestMapping(value = {"/customer/viewService","/merchant/viewService"}, method = RequestMethod.POST)
		public ModelAndView getTransact(ModelAndView model,HttpServletRequest request) {
			//access UI parameters
		//int accNo = 1000000002;//Integer.parseInt(request.getParameter("account"));
		int accNo=Integer.parseInt(request.getParameter("accSelected"));
		Account account = accountDAO.get(accNo);
		
		double balance = account.getBalance();
	    List<AccountSummary> listSummary = accountDAO.ViewSummary(accNo, 1);
	    List<AccountSummary> listPendingSummary = accountDAO.ViewSummary(accNo, 0);
	    model.addObject("listSummary", listSummary);
	    model.addObject("listPendingSummary", listPendingSummary);
	    model.addObject("accno", accNo);
	    model.addObject("balance", balance);
	    model.setViewName("accountsummary");	 
	    return model;
	}
}

package com.java.softsec.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.softsec.dao.AccountDAO;
import com.java.softsec.dao.InternalUserDAO;
import com.java.softsec.dao.PaymentDAO;
import com.java.softsec.models.Payment;

@Controller
public class PaymentController {
	
	@Autowired
    private PaymentDAO paymentDAO;
	
	public void setPaymentDAO(PaymentDAO paymentDAO){
		this.paymentDAO = paymentDAO;
	}
	
	@Autowired
	private AccountDAO accountDAO;
	public void setAccountDAO(AccountDAO accountDAO){
		this.accountDAO = accountDAO;
	}
	
	
	@RequestMapping(value={"/merchant/authorize","/employee/InternalAccountManagement.html"})	
	public ModelAndView merchantAccess(ModelAndView model) throws IOException{		
		//System.out.println(accountDAO);
		//String externid = session.getAttribute("externid")+"";
	    //List<Payment> lstPayment = paymentDAO.getPendingPayments(Integer.parseInt(externid));
	    //model.addObject("lstPayment", lstPayment);
	    model.setViewName("accessDenied");	 
	    return model;
	}
	
	@RequestMapping(value="/customer/authorize")	
	public ModelAndView makePayment(ModelAndView model, HttpSession session) throws IOException{		
		//System.out.println(accountDAO);
		String externid = session.getAttribute("externid")+"";
	    List<Payment> lstPayment = paymentDAO.getPendingPayments(Integer.parseInt(externid));
	    model.addObject("lstPayment", lstPayment);
	    model.setViewName("authorize");	 
	    return model;
	}
	
	@RequestMapping(value = "/customer/paymentService", method = RequestMethod.POST)
	public ModelAndView TransferRequest(HttpServletRequest request) {				
		int fromAccountNumber=Integer.parseInt(request.getParameter("fromAcc"));
		int toAccountNumber=Integer.parseInt(request.getParameter("toAcc"));
		double amount=Double.parseDouble(request.getParameter("amount"));
		int reqid=Integer.parseInt(request.getParameter("reqid"));
		String message=accountDAO.setTransfer(fromAccountNumber, toAccountNumber,amount);
		if(message.equals("Success"))
		{
		String msg=paymentDAO.updateRequest(fromAccountNumber,toAccountNumber,amount,reqid);
		ModelAndView model =new ModelAndView("paymentSuccess");
		model.addObject("msg",msg);
	    return model;
		}
		else{
		ModelAndView model =new ModelAndView("transfersuccess");
		model.addObject("message",message);
	    return model;
		}
	}	
}

package com.java.softsec.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.softsec.dao.AccountSummaryDAO;
import com.java.softsec.dao.ExternalUserDAO;
import com.java.softsec.dao.PaymentDAO;
import com.java.softsec.models.AccountSummary;

@Controller
public class AccountSummaryController {

	@Autowired
    private AccountSummaryDAO summaryDAO;
	
	public void setAccountSummaryDAO(AccountSummaryDAO accountSummaryDAO){
		this.summaryDAO = accountSummaryDAO;
	}
	
	/*
	@RequestMapping(value = {"/customer/viewService","/merchant/viewservice"}, method = RequestMethod.POST)
	public ModelAndView getTransact(HttpServletRequest request) {
		//access UI parameters				
		int fromAccount=Integer.parseInt(request.getParameter("accSelected"));
		//System.out.println(fromAccount);
		List<AccountSummary> lstAcc=summaryDAO.getTransact(fromAccount);
		
		System.out.println("size is "+lstAcc.size());
		
		ModelAndView model =new ModelAndView("transactions_user");
		model.addObject("lstAcc",lstAcc);
	    return model;
	}*/
}

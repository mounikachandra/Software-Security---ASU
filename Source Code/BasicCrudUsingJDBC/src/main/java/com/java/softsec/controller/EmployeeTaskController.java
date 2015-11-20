package com.java.softsec.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.softsec.dao.AccountDAO;
import com.java.softsec.dao.ActivitiesLogDAO;
import com.java.softsec.dao.EmployeeTaskDAO;
import com.java.softsec.dao.ExternalUserDAO;
import com.java.softsec.models.Account;
import com.java.softsec.models.AccountSummary;
import com.java.softsec.models.EmployeeTask;
import com.java.softsec.models.ExternalUser;

@Controller
public class EmployeeTaskController {
	@Autowired
    private EmployeeTaskDAO empTaskDAO;
	public void setEmployeeTaskDAO(EmployeeTaskDAO employeeTaskDAO){
		this.empTaskDAO = employeeTaskDAO;
	}
	
	@Autowired
	private ActivitiesLogDAO activitiesLogDAO;
	public void setActivitiesLogDAO(ActivitiesLogDAO activitiesLogDAO){
		this.activitiesLogDAO = activitiesLogDAO;
	}
	
	@Autowired
	private AccountDAO accDAO;
	public void setAccountDAO(AccountDAO accountDAO){
		this.accDAO = accountDAO;
	}
	
	@Autowired
	private ExternalUserDAO exuserDAO;
	public void setExternalUserDAO(ExternalUserDAO externalUserDAO){
		this.exuserDAO=externalUserDAO;
	}
	
	
	EmployeeTask task;
	int curr_tranid = 0;
	int action = 0;
	int accNo = 0;
	
	@RequestMapping(value={"/employee/tasks_home","/admin/tasks_home"})
	public ModelAndView taskHome(ModelAndView model) throws IOException{
		model.setViewName("tasks_home");
	    
		return model;
	}
	
	@RequestMapping(value="/employee/taskdetails", method=RequestMethod.POST)
	public ModelAndView listTask(ModelAndView model) throws IOException{
		
		task = empTaskDAO.getInEmployeeTask();
		
	    if(task != null) { 
	    	curr_tranid = task.getTransId();
	    	action = task.getActionId();
	    	
	    	if(action == 1){ // Authorize Transaction
		    	AccountSummary transaction = empTaskDAO.getTransaction(curr_tranid);
		    	model.addObject("transaction", transaction);
		    } else if (action == 2){ // Delete Account
		    	accNo = task.getCustomerAccNo();
		    	Account account = empTaskDAO.getAccount(accNo);
		    	model.addObject("account", account);
		    }
	    }
	    int isAdmin = 0;
	    model.addObject("isAdmin", isAdmin);
	    model.addObject("task", task);
	    model.setViewName("emp_tasks");
	    return model;
	}
	
	@RequestMapping(value="/admin/taskdetails", method=RequestMethod.POST)
	public ModelAndView listAdminTask(ModelAndView model) throws IOException{
		
		task = empTaskDAO.getInAdminEmployeeTask();
		
	    if(task != null) {
	    	curr_tranid = task.getTransId();
	    	action = task.getActionId();
	    	
	    	if(action == 1){ 			// Authorize Transaction
		    	AccountSummary transaction = empTaskDAO.getTransaction(curr_tranid);
		    	model.addObject("transaction", transaction);
		    } else if (action == 2){ 	// Delete Account
		    	accNo = task.getCustomerAccNo();
		    	Account account = empTaskDAO.getAccount(accNo);
		    	model.addObject("account", account);
		    } else if (action == 3){	// Create New Account
		    	accNo = task.getCustomerAccNo();
				Account acc = accDAO.get(accNo);
				int exuserid = acc.getExUserId();
				ExternalUser exUser = exuserDAO.get(exuserid);
				model.addObject("exUserDetails", exUser);
		    }
	    }
	    
	    int isAdmin = 1;
	    model.addObject("isAdmin", isAdmin);
	    model.addObject("task", task);
	    model.setViewName("emp_tasks");
	    return model;
	}
	
	@RequestMapping(value={"/employee/authorize","/admin/authorize"}, method=RequestMethod.POST)
	public ModelAndView authorize(ModelAndView model) throws IOException{
		int tranid = task.getTransId();
		System.out.println(tranid);
		int taskid = task.getTid();
		
		AccountSummary transaction = empTaskDAO.getTransaction(tranid);
		
		double amount = transaction.getDebitAmount();
		
		Account custAcc = accDAO.get(transaction.getFromAccNo());
		custAcc.setBalance(custAcc.getBalance() - amount);
		accDAO.saveOrUpdate(custAcc);
		
		Account mercAcc = accDAO.get(transaction.getToAccNo());
		mercAcc.setBalance(mercAcc.getBalance() + amount);
		accDAO.saveOrUpdate(mercAcc);
		
		empTaskDAO.updateTransaction(tranid, taskid);
		
		return new ModelAndView("redirect:/employee/tasks_home");
	}
	
	@RequestMapping(value="/admin/authcreateaccount", method=RequestMethod.POST)
	public ModelAndView authorizecreateaccount(ModelAndView model) throws IOException{
		int newaccno = task.getCustomerAccNo();
		
		empTaskDAO.activateNewAccount(newaccno);
		
		return new ModelAndView("redirect:/admin/tasks_home");
	}
	
	@RequestMapping(value="/employee/escalate", method=RequestMethod.POST)
	public ModelAndView escalate(ModelAndView model) throws IOException{
		int taskid = task.getTid();
		empTaskDAO.escalateToAdmin(taskid);
		
		return new ModelAndView("redirect:/employee/tasks_home");
	}

	@RequestMapping(value="/admin/delete_account", method=RequestMethod.POST)
	public ModelAndView deleteAccount(ModelAndView model) throws IOException{
		accNo = task.getCustomerAccNo();
		int taskid = task.getTid();
		empTaskDAO.deleteAccount(accNo, taskid);
		
		return new ModelAndView("redirect:/admin/tasks_home");
	}
	
	@RequestMapping(value="/employee/cancel", method=RequestMethod.POST)
	public ModelAndView cancel(ModelAndView model) throws IOException{
		if(task!=null){
			int taskid = task.getTid();
			empTaskDAO.resetAcknowledged(taskid);
		}
		
		return new ModelAndView("redirect:/employee/tasks_home");
	}
	
	@RequestMapping(value="/admin/cancel", method=RequestMethod.POST)
	public ModelAndView adminCancel(ModelAndView model) throws IOException{
		if(task!=null){
			int taskid = task.getTid();
			empTaskDAO.resetAcknowledged(taskid);
		}
		return new ModelAndView("redirect:/admin/tasks_home");
	}
}

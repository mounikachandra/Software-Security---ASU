
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="../resources/tabs.css" rel="stylesheet" type="text/css" />
<title>View/Authorize Transactions</title>
</head>
<body>
<ol id="toc">
    <li><a href="emp_profile"><span>Profile</span></a></li>
    <li class="current"><a href="tasks_home"><span>View/Authorize</span></a></li>
    <li><a href="InternalAccountManagement.html"><span>Account Management</span></a></li>
    <li><a href="../j_spring_security_logout"><span>Logout</span></a></li>   
</ol>
<div class="content">
<h2>Tasks</h2>
<c:if test="${task == null}">
	NO TASKS FOUND
</c:if>
<c:if test="${task != null}">
	<table border="0" cellpadding="10" cellspacing="5">
	  <tr>
	    <td><b>Task Id</b></td>
	    <td>: ${task.tid}</td>
	  </tr>
	  <tr>
	    <td><b>Customer Account</b></td>
	    <td>: ${task.customerAccNo}</td>
	  </tr>
	  <c:if test="${task.actionId == 1}">
		  <tr>
		    <td><b>Action</b></td>
		    <td>: Authorize Transaction</td>
		  </tr>
	  </c:if>
	  <c:if test="${task.actionId == 2}">
		  <tr>
		    <td><b>Action</b></td>
		    <td>: Delete Account</td>
		  </tr>
	  </c:if>
	   <c:if test="${task.actionId == 3}">
		  <tr>
		    <td><b>Action</b></td>
		    <td>: Create New Account</td>
		  </tr>
	  </c:if>
	  <c:if test="${task.actionId == 1}">
		  <tr>
		    <td><b>Transaction Id</b></td>
		    <td>: ${task.transId}</td>
		  </tr>
	  </c:if>
	</table>
	
	<c:if test="${task.actionId == 1}">
		<h2>Transaction Details</h2>
		<table border="0" cellpadding="10" cellspacing="5">
		<tr>
		    <th>From Account</th>
		    <th>To Account</th>
		    <th>Amount</th>
		    <th>Created</th>
		   </tr>
		  	<tr>
		   	<td>${transaction.fromAccNo}</td>
		   	<td>${transaction.toAccNo}</td>
		   	<td>${transaction.debitAmount}</td>
		   	<td>${transaction.created}</td>
		   	</tr>
		</table>
		<form action="authorize" method="post" style="margin-top:10px;">
			<input type="submit" name="authorize" value="Authorize">
		</form>
	</c:if>
	<c:if test="${task.actionId == 2}">
		<h2>Account Details</h2>
		 <table border="0" cellpadding="10" cellspacing="5">
			<tr>
			    <th>Account No</th>
			    <th>User Id</th>
			    <th>Current Balance</th>
		    </tr>
	    	<tr>
		    	<td>${account.accNo}</td>
		    	<td>${account.exUserId}</td>
		    	<td>${account.balance}</td>
	    	</tr>
		</table>
		<c:if test="${isAdmin == 1}">
			<form action="delete_account" method="post" style="margin-top:10px;">
				<input type="submit" name="delete" value="Delete Account">
			</form>
		</c:if>
	</c:if>
	<c:if test="${task.actionId == 3}">
		
		<c:if test="${isAdmin == 1}">
			<form action="authcreateaccount" method="post" style="margin-top:10px;">
				<input type="submit" name="create" value="Create Account">
			</form>
		</c:if>
	</c:if>
	<c:if test="${isAdmin == 0}">
		<form action="escalate" method="post" style="margin-top:10px;">
			<input type="submit" name="escalate" value="Escalate">
		</form>
	</c:if>
</c:if>
<form action="cancel" method="post" style="margin-top:10px;">
	<input type="submit" name="cancel" value="Cancel">
</form>
</div>
</body>
</html>
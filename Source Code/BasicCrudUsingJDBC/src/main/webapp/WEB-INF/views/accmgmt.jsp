<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Management</title>
          <link href="../resources/tabs.css" rel="stylesheet" type="text/css" /> 
    </head>
<body>
<ol id="toc">
    <li><a href="Account"><span>Account</span></a></li>
    <li><a href="TranPayment"><span>Payment</span></a></li>
    <li><a href="Transfer"><span>Transfer</span></a></li>
    <li><a href="creditDebit"><span>Credit/Debit</span></a></li>
    <li><a href="authorize"><span>Make Payment</span></a></li>
    <li><a href="viewTransactions"><span>View Transactions</span></a></li>
    <li><a href="extProfile"><span>Profile</span></a></li>
    <li class="current"><a href="accmgmt"><span>Account Management</span></a></li>
    <li><a href="<c:url value='/j_spring_security_logout'/>"><span>Logout</span></a></li>
     
</ol>
    <div class="content">

	<div align="center">
	<h2>Select type of change</h2>
	 <form:form  method="post" commandName="form" >
			<table>	
			<tr>
			<td colspan="2">
			<label color ="Red"><font color="red">${statustext}<font></label>
			</td>
			</tr>
			<tr>
				<td>
					<form:radiobutton path="action" value="create"/>Create a new account
					
				</td>
				<td>
					<form:radiobutton path="action" value="delete"/>Select an account to delete 
					
				</td>
			</tr>
			
			<tr>
				<td>
				  <form:select name="selectedAccount" path="account">
						<form:options items="${listOfAccounts}"></form:options>
            		</form:select>
				</td>
				<td>
					<input type="submit" value="Submit Modification Request">
				</td>
			</tr>
			</table>
	  </form:form>
	</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transfer and Request Payment</title>
  <link href="../resources/tabs.css" rel="stylesheet" type="text/css" /> 
</head>
<body>

<ol id="toc">
    <li><a href="Account"><span>Account</span></a></li>
    <li  class="current"><a href="TranPayment"><span>Payment</span></a></li>
    <li><a href="Transfer"><span>Transfer</span></a></li>
    <li><a href="creditDebit"><span>Credit/Debit</span></a></li>
    <li><a href="authorize"><span>Make Payment</span></a></li>
    <li><a href="viewTransactions"><span>View Transactions</span></a></li>
    <li><a href="extProfile"><span>Profile</span></a></li>
    <li><a href="accmgmt"><span>Account Management</span></a></li> 
    <li><a href="<c:url value='/j_spring_security_logout'/>"><span>Logout</span></a></li>   
</ol>
    <div class="content">
    <h3>Enter the Account Number of the Customer 
    you wish to Request Payment from</h3>
    <hr>
	<div align="center">
	
	<form:form  method="post" commandName="form" >
	<table>
	<tr>
	<td colspan="4" >
		<label color="Red"><font color="red">${errormsg}</font></label>
		
		
		
	</td>
	</tr>
	<tr>
		<td>From Account
		</td>
		<td>To: 
		</td>
		<td>Amount:
		</td>
		<td>Type of Request:
		</td>
	</tr>
		<tr>
			<td>
				<form:select name="selectedAccount" path="account">
					<form:options items="${listOfAccounts}"></form:options>
            	</form:select>
			</td>
			<td>
				<table>
					<tr>
						<td>
						Customer Account
						</td>
						<td>
							<form:input path="customeracc"  />
						</td>
					</tr>
				</table>	
			
			
			</td>
			<td>
				<table>
					<tr>
						<td>
						
						</td>
						<td>
							<form:input path="amount"  />
						</td>
					</tr>
				</table>	
			</td>
			<td>
		</tr>
		<tr>
			<td>
			</td>
			<td>
			</td>
			<td>
			</td>
			<td>
			<input type="submit" value="Submit request">
			</td>
		</tr>
	</table>
	  </form:form>
	</div>
	</div>
</body>
</html>
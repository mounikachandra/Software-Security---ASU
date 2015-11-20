<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Home</title>
          <link href="../resources/tabs.css" rel="stylesheet" type="text/css" /> 
    </head>
    <body>
<ol id="toc">
    <li class="current"><a href="Account"><span>Account</span></a></li>
    <li><a href="TranPayment"><span>Payment</span></a></li>
    <li><a href="Transfer"><span>Transfer</span></a></li>
    <li><a href="creditDebit"><span>Credit/Debit</span></a></li>
    <li><a href="authorize"><span>Make Payment</span></a></li>
    <li><a href="viewTransactions"><span>View Transactions</span></a></li>
    <li><a href="extProfile"><span>Profile</span></a></li>
    <li><a href="accmgmt"><span>Account Management</span></a></li> 
    <li><a href="<c:url value='/j_spring_security_logout'/>"><span>Logout</span></a></li>
   
</ol>
    <div class="content">
        <div align="center">
            <h2>Account List</h2>
     
            <table border="1">
                <th>Account No</th>
                <th>user id</th>
                <th>balance</th>
              
                 	
                <c:forEach var="account" items="${listAccount}" varStatus="status">
                <tr>
                
                    
                    <td>${account.accNo}</td>
                    <td>${account.exUserId}</td>
                    <td>${account.balance}</td>
                 
                             
                </tr>
                </c:forEach>             
            </table>
        </div>
        </div>
    </body>
</html>
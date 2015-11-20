<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>External User</title>
      <link href="../resources/tabs.css" rel="stylesheet" type="text/css" /> 
   <script>
		function validateForm()
		{
			
		    if(document.frm.phone.value=="" || document.frm.address.value=="" || document.frm.city.value=="" || document.frm.email.value=="")
		    {
		      alert("fields cannot be left blank");
		    
		      return false;
		    }
		    var phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;  
		    if(document.frm.phone.value.match(phoneno))  
		    {    
		    }  
		    else  
		    {  
		        alert("invalid mobile number");  
		        return false;  
		    } 
		    var email=/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		    if(!document.frm.email.value.match(email))
		    {
		    	alert("invalid email address");  
		   		return false;
		    }
		   return true;
		}
	</script>
   
</head>
<body>
<ol id="toc">
    <li><a href="Account"><span>Account</span></a></li>
    <li><a href="TranPayment"><span>Payment</span></a></li>
    <li><a href="Transfer"><span>Transfer</span></a></li>
    <li><a href="creditDebit"><span>Credit/Debit</span></a></li>
    <li><a href="authorize"><span>Make Payment</span></a></li>
    <li><a href="viewTransactions"><span>View Transactions</span></a></li>
    <li  class="current"><a href="extProfile"><span>Profile</span></a></li>
    <li><a href="accmgmt"><span>Account Management</span></a></li>  
    <li><a href="<c:url value='/j_spring_security_logout'/>"><span>Logout</span></a></li>  
</ol>
    <div class="content">
 <div align="center">
    <h2>Edit Profile</h2>
	
 
     <form:form name="frm" action="saveProfile" method="post" modelAttribute="extUser" onSubmit="return validateForm()">
   
     
      <table>
      <tr>
      <td colspan="2"><label color="Red"><font color="red">${statusmsg}</font></label><td>
      </tr>
      <tr>
      	<td>Phone: </td>
      		<td> <form:input path="phone"  /></td>
      </tr>
        <tr>
        	<td>Address:</td>
        		<td>  <form:input path="address"  /></td>
      </tr>
        <tr>
        	<td> City: </td>
        		<td> <form:input path="city" /></td>
      </tr>
        <tr>
        	<td>	Email:</td>
        		<td><form:input path="email" /></td>
      </tr>
       
      </table>
            
		<br/>    
			<input id="Submit1" type="submit" value="Make Changes" />
	  </form:form>	  
</div>
</div>
</body>
</html>
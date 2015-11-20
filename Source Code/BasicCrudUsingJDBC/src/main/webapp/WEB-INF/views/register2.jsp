<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>
<script>
        function validateForm()
        {
        	
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
    <div align="center">
       
        <form:form name="frm" action="finalsignup"  method="post" onSubmit="return validateForm()">
        
        <table>            
            <tr>
                <td>Email:</td>
                <td><input type='text' id='email' name='email'></td>
            </tr>
            <tr>
                <td>OTP:</td>
                <td><input type='text' id='OTP' name='OTP'></td>
            </tr>                       
            <tr>
                <td colspan="2" align="center"> <!-- <a href=""> -->
    				<input type="submit" value="Submit" />
				</td>
                
               
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>
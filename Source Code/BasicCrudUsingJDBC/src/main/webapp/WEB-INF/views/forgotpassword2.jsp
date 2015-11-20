<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reset Password</title>
<script>
        function validateForm()
        {
        	
            
            if(!document.frm.Password || !document.frm.confirmpassword)
            {
                alert("Please enter the Password and Confirm");  
                   return false;
            }
            
            if(document.frm.Password.value != document.frm.confirmpassword.value)
           	{
           		alert("Value of Password and Confirm Password do not match");
           		return false;
           	}
            
           return true;
        }
    </script>
</head>
<body>
    <div align="center">
       
        <form:form name="frm" action="forgotpasswordfinal"  method="post" onSubmit="return validateForm()">
        
        <table>   
        
        	<tr>
                <td>OTP:</td>
                <td><input type='text' id='OTP' name='OTP'></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type='text' id='email' name='email'></td>
            </tr>          
            <tr>
                <td>Password:</td>
                <td><input type='Password' id='Password' name='Password'></td>
            </tr>
            <tr>
                <td>Confirm Password:</td>
                <td><input type='Password' id='confirmpassword' name='confirmpassword'></td>
            </tr>
            
            <tr>
                <td colspan="2" align="center"> 
    				<input type="submit" value="Submit" />
				</td>              
               
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>
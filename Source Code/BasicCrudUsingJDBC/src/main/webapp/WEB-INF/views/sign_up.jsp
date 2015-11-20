<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaImpl" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 


</head>
<body>
	<form  name="frm" method="post"  commandName="form">
		<table width="450px">
		
		<tr>
		<td colspan="2">
			<label color="Red"><font color="red">${errormsg}</font></label>
		</td>
		</tr>
			<tr>
				<td valign="top">
					<label for="first_name">First Name *</label>
				</td>
				<td valign="top">
				
				
				
					<input  type="text" name="firstName" size="30"/>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<label for="last_name">Last Name *</label>
				</td>
				<td valign="top">
					<input  type="text" name="lastName" size="30"/>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<label for="user_name">User Name *</label>
				</td>
				<td valign="top">
					<input type="text" name="userName" size="30"/>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<label for="password">Password *</label>
				</td>
				<td valign="top">
					<input  type="password" name="password" size="30"/>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<label for="confirm">Confirm Password *</label>
				</td>
				<td valign="top">
					<input type="password" name="password" size="30"/>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<label for="merchant">Is Merchant? *</label>
				</td>
				<td valign="top">
					Yes: <input type="radio" name="isMerchant" value="1">
					No: <input type="radio" name="isMerchant" value="0" checked="checked">
				</td>
			</tr>
			<tr>
				<td valign="top">
					<label for="phone">Phone *</label>
				</td>
				<td valign="top">
					<input type="text" name="phone" maxlength="10" size="30">
				</td>
			</tr>
			<tr>
				<td valign="top">
					<label for="address">Address *</label>
				</td>
				<td valign="top">
					<input type="text" name="address" size="30">
				</td>
			</tr>
			<tr>
				<td valign="top">
					<label for="city">City *</label>
				</td>
				<td valign="top">
					<input type="text" name="city" size="30">
				</td>
			</tr>
			
			<tr>
			<td colspan="2">
<%
			ReCaptcha c;
			if(request.isSecure()){ 
				c = ReCaptchaFactory.newSecureReCaptcha("6LdI7vwSAAAAALftWKKV0RKgcq8svBvgpLsZXdmq", "6LdI7vwSAAAAAM3b02ji1eSr-jHPp2FgHP1l_-RU", true);
			 	((ReCaptchaImpl) c).setRecaptchaServer("https://www.google.com/recaptcha/api");
         		out.print(c.createRecaptchaHtml(null, null));
			}else{
				   c = ReCaptchaFactory.newReCaptcha("6LdI7vwSAAAAALftWKKV0RKgcq8svBvgpLsZXdmq", "6LdI7vwSAAAAAM3b02ji1eSr-jHPp2FgHP1l_-RU", true);
				   ((ReCaptchaImpl) c).setRecaptchaServer("http://www.google.com/recaptcha/api");
			}
			out.print(c.createRecaptchaHtml(null, null));
        	%>
			
 			</td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align:center">
					<input type="submit" value="Submit">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>

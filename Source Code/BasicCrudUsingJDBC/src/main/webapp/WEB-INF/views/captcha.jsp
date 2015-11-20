<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Captcha</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
<center>
<form method="post" action="verifyCaptcha">
<table cellspacing="15" border="1">
<tr>
<td>Name</td>
<td><input type="text" name="name" id="name"></td>
</tr>
<tr>
<td>Message</td>
<td> <textarea type="text" cols="25" rows="8" name="message" id="message"></textarea></td>
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
</table>
<br>
<br><br>
<input type="submit" value="submit">
</form>
<br><br>
</center>
</body>
</ht
ml>
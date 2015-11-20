<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../../resources/tabs.css" rel="stylesheet" type="text/css" />
<title>Admin Edit</title>
</head>
<body>
<ol id="toc">
    <li><a href="../emp_profile"><span>Profile</span></a></li>
    <li><a href="../tasks_home"><span>View/Authorize</span></a></li>
    <li class="current"><a href="../InternalAccountManagement.html"><span>Account Management</span></a></li>
    <li><a href="../../j_spring_security_logout"><span>Logout</span></a></li>   
</ol>
<div class="content">
<div style="text-align: center;">
		<h3><a href="/java">Back Home</a></h3>
	</div>
	<form:form method="POST" commandName="form">
		<table>
			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName"/></td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName"/></td>
			</tr>
			<tr>
				<td>SSN:</td>
				<td><form:input path="sSN"/></td>
			</tr>
			<tr>
				<td>DOB:</td>
				<td><form:input path="dOB" type="text" placeholder="yyyy-mm-dd" /></td>
			</tr>
			<tr>
				<td>email:</td>
				<td><form:input path="email"/></td>
			</tr>
			<tr>
				<td>address:</td>
				<td><form:input path="address"/></td>
			</tr>
			<tr>
				<td>city:</td>
				<td><form:input path="city"/></td>
			</tr>
			<tr>
				<td>phone:</td>
				<td><form:input path="phone" maxlength="10"/></td>
			</tr>
			<tr>
				<td>userName:</td>
				<td><form:input path="userName"/></td>
			</tr>
			<tr>
				<td>password:</td>
				<td><form:input path="password"/></td>
			</tr>
			<tr>
				<td>chose whether this user is a merchant(1:admin, 0:employee):&nbsp; </td>
<%-- 				<td><form:input path="isAdmin" maxlength="1" placeholder="0" /></td> --%>
<%-- 				<td><form:errors path="isAdmin" cssStyle="color: #ff0000;"/></td> --%>
					<td><form:radiobutton checked="checked" path="isAdmin" value="0" label="Employee"/></td>
					<td><form:radiobutton path="isAdmin" value="1" label="Admin"/></td>
				 
			</tr>
			<tr>
				<td>chose whether this user is active (1:active, 0:deleted):&nbsp; </td>
<%-- 				<td><form:input path="currentEmployee" maxlength="1" placeholder="1" /></td> --%>
<%-- 				<td><form:errors path="currentEmployee" cssStyle="color: #ff0000;"/></td>  --%>
					<td><form:radiobutton path="currentEmployee" value="0" label="Deleted"/></td>
					<td><form:radiobutton checked="checked" path="currentEmployee" value="1" label="Active"/></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Submit"></td>
			</tr>
			<tr>
		</table>
	</form:form>
	<br>
	<div style="text-align: center;">
		<span style="color: rgb(215, 0, 0);">${message}</span>
 	</div>
</div>
</body>
</html>
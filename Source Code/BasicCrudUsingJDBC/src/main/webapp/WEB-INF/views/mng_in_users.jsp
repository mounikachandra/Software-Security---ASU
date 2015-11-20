<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<!-- <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type"> -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../resources/tabs.css" rel="stylesheet" type="text/css" />
<title>Manage Internal Users</title>
</head>
<body>
<ol id="toc">
    <li><a href="emp_profile"><span>Profile</span></a></li>
    <li><a href="tasks_home"><span>View/Authorize</span></a></li>
    <li  class="current"><a href="InternalAccountManagement.html"><span>Account Management</span></a></li>
    <li><a href="../j_spring_security_logout"><span>Logout</span></a></li>   
</ol>
<div class="content">
	<div style="text-align: center;">
		<h3><a href="/java">Back Home</a></h3>
	</div>
	<form:form method="post" commandName="form">
		<div style="text-align: center;">Account List:
			<br>
		</div>
		<div style="text-align: center;">
			<form:select name = "selection_field" path="user">
					<form:options items="${listOfUsers}" />
			</form:select>
			<br> <br> <br>
			<form:radiobutton checked="checked" path="choice" value="edit"/>edit &nbsp; &nbsp; 
			<form:radiobutton path="choice" value="delete"/>delete &nbsp; &nbsp;
			<form:radiobutton path="choice" value="add"/>add &nbsp; &nbsp;
			<form:radiobutton path="choice" value="activate"/>activate
			<br> <br>
			<tr>
                <td colspan="2" align="center"><input type="submit" value="Perform"></td>
            </tr>
			<br>
		</div>
	</form:form>
	<br>
	<div style="text-align: center;">
		<span style="color: rgb(215, 0, 0);">${message}</span>
 	</div>

	</div>
</body>
</html>
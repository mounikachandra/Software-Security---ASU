<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="../resources/tabs.css" rel="stylesheet" type="text/css" />
<title>Employee Tasks</title>
</head>
<body>
<ol id="toc">
    <li><a href="emp_profile"><span>Profile</span></a></li>
    <li class="current"><a href="tasks_home"><span>View/Authorize</span></a></li>
    <li><a href="InternalAccountManagement.html"><span>Account Management</span></a></li>
    <li><a href="../j_spring_security_logout"><span>Logout</span></a></li>   
</ol>
<div class="content">
	<form action="taskdetails" method="post" style="margin-top:10px;">
		Get your task:
		<input type="submit" name="cancel" value="Get Task">
	</form>
	</div>
</body>
</html>
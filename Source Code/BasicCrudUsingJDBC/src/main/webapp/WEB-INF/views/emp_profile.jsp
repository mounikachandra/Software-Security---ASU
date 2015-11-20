<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Internal User</title>
    <link href="../resources/tabs.css" rel="stylesheet" type="text/css" />
</head>
<body>
<ol id="toc">
    <li class="current"><a href="emp_profile"><span>Profile</span></a></li>
    <li><a href="tasks_home"><span>View/Authorize</span></a></li>
    <li><a href="InternalAccountManagement.html"><span>Account Management</span></a></li>
    <li><a href="../j_spring_security_logout"><span>Logout</span></a></li>   
</ol>
<div class="content">

<h2>Employee Profile</h2>
<table border="0" cellpadding="10" cellspacing="5">
	  <tr>
	    <td><b>First Name</b></td>
	    <td>: ${listInUser.firstName}</td>
	    <td><b>Email</b></td>
	    <td>: ${listInUser.email}</td>
	  </tr>
	  <tr>
	    <td><b>Last Name</b></td>
	    <td>: ${listInUser.lastName}</td>
	    <td><b>Address</b></td>
	    <td>: ${listInUser.address}</td>
	  </tr>
	  <tr>
	    <td><b>Date of Birth</b></td>
	    <td>: ${listInUser.dOB}</td>
	    <td><b>City</b></td>
	    <td>: ${listInUser.city}</td>
	  </tr>
	  <tr>
	    <td><b>SSN</b></td>
	    <td>: ${listInUser.sSN}</td>
	    <td><b>Contact No.</b></td>
	    <td>: ${listInUser.phone}</td>
	  </tr>
</table>
<!-- 
    <br />
    <br />
    <strong>Name :&nbsp;</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input id="Text1" type="text" readonly="readonly" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <br />
    <br />
    <strong>EmployeeID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input id="Text6" type="text" readonly="readonly" /><br />
    <br />
    SSN:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input id="Text7" type="text" readonly="readonly" /><br />
    <br />
    DOB:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input id="Text8" type="text" readonly="readonly" /><br />
    <br />
    <br />
    <br />
    Address:&nbsp;&nbsp;&nbsp;&nbsp;
    <input id="Text2" type="text" /><br /><br />
    City:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input id="Text4" type="text" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    State:&nbsp;&nbsp;<select>
	  <option value="Select">Select</option>
	  <option value="AZ">AZ</option>
	  <option value="CA">CA</option>
	  <option value="NY">NY</option>
	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    ZipCode:&nbsp;&nbsp;
    <input id="Text5" type="text" /><br />
    <br />
    <br />
    </strong>
    -->
   </div>
</body>
</html>
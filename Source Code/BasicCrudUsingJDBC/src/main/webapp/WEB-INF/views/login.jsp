<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}
 
.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}
 
#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script src="resources/JS/jquery.keyboard.extension-all.min.js"></script>
<script src="resources/JS/jquery.keyboard.extension-autocomplete.min.js"></script>
<script src="resources/JS/jquery.keyboard.extension-mobile.min.js"></script>
<script src="resources/JS/jquery.keyboard.extension-navigation.min.js"></script>
<script src="resources/JS/jquery.keyboard.extension-scramble.min.js"></script>
<script src="resources/JS/jquery.keyboard.extension-typing.min.js"></script>
<script src="resources/JS/jquery.keyboard.min.js"></script>
<script src="resources/JS/jquery.mousewheel.min.js"></script>
<script src="resources/JS/virtualkeyboard.js"></script>

<link rel="stylesheet" href="resources/css/keyboard.css" />


</head>
<body onload='document.loginForm.username.focus();'>
 
	<center><h1>Welcome to Sun devil Security Bank</h1></center>
 
	<div id="login-box">
 
		<h3>Login with Username and Password</h3>
 
		<c:if test="${not empty error}">
			<div class="error">Invalid Credentials</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
 
		<form name='loginForm'
		  action="<c:url value='/j_spring_security_check' />" method='POST'>
 
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' id="keyboard" name='password' />
				<img id="icon" src="http://mottie.github.com/Keyboard/demo/keyboard.png"></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
				  value="Submit" /></td>
			</tr>
			<tr>
									
		  </table>
 
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
 
		</form>
		<a href="newcontactemailandssn">
    			<button>Sign up</button>
			</a>
		<a href="forgotpassword1"><button>Forgot password</button></a>
	</div> 
	
	<div id="wrap">
    <!-- <input id="keyboard" type="text">
    <img id="icon" src="http://mottie.github.com/Keyboard/demo/keyboard.png"> -->
</div>
	
</body>
</html>
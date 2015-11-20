<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
   
     function Verify(){
    	var email=$("#email").val();
	    	 $.post( "emailService", {mail:email})
	    	  .done(function( data ) {
	    	    alert( data );
	    	   // document.location.reload(true);
	    	  });  	  
     }
     </script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm Email</title>
</head>
<body>
<p>
        SSN :
        <input id="Text1" type="text" /></p>
    <p>
        Email:
        <input id="Text2" type="text" id="email"/></p>
    <p>
        Confirm Email:
        <input id="Text3" type="text" /></p>
    <p style="margin-left: 120px">
        <input type="submit" value="Sign Up" onclick="Verify()"/></p>
</body>
</html>
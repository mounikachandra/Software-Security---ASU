<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>External User</title>
    <link href="resources/tabs.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
     function transferMoney(){
    	 var customerAcc = $("#customerA").val();
    	 //var requestURL = "transferService?customerA="+customerAcc;
    	 /*$.get(requestURL,function(data){
    		    alert(data);
    		  });*/
    	 $.post( "transferService", { vandita: customerAcc })
    	  .done(function( data ) {
    	    alert( data );
    	  });
    		  
    		  
     }
    </script>
</head>
<body style="width:980px;margin:auto;">
 <h1>Payment</h1>
    <ol id="toc">
        <li><a href="Account.html"><span>Account</span></a></li>
        <li class="current"><a href="payment.html"><span>Payment</span></a></li>
        <li><a href="Transfer.html"><span>Transfer</span></a></li>
        <li><a href="creditdebit.html"><span>Credit/Debit</span></a></li>
        <li><a href="authorize.html"><span>View/Authorize</span></a></li>
        <li><a href="profile.html"><span>Profile</span></a></li>
        <li><a href="accountManagement.html"><span>Account Management</span></a></li>    
    </ol>
    <div class="content" style="width:980px;height:300px">
     <div id="mainDiv" style="width:100%">
        <div id="Div1" style="float:left;width:33%;">
            <label for="accountSelect" style="clear:both;">For Account:</label>
            <br /><br /><br />
            <select id="accountSelect" style="clear:both;display:block">
            <c:forEach var="acc" items="${lstAccount}" varStatus="status">
                <option>${acc.accNo}</option>
                </c:forEach>
            </select>
        </div>
        <div id="Div2" style="float:left;width:33%">
            <label style="clear:both;">To:</label>
            <br /><br /><br />
            <input type="radio" name="UserType" value="Customer:"/><label for:="Text1">Customer: </label><input  name="customerA" type="text" id="customerA"/><br/>
            <input type="radio" name="UserType" value="Merchant:"/><label for:="Text2">Merchant: </label><input  name="merchantA" type="text" />
        </div>
        <div id="Div3" style="float:left;width:32%; height: 48px;">Type of Request</div>
         <input type="radio" name="TypeOfRequest" value="Transfer" /> Transfer<br />
        <input type="radio" name="TypeOfRequest" value="Request Payment" /> Request Payment<br />
         <br />
         <input id="Submit1" type="submit" value="submit Request" onclick="transferMoney()" /></div>
    </div>
</body>
</html>
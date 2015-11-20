<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>External User</title>
    <link href="../resources/tabs.css" rel="stylesheet" type="text/css"/>
</head>
<script type="text/javascript">
function handlePayment(merchantAccountNumber,requestAmount, customerAccountNumber,reqID){
	// var fromAccount=$("#fromAccountSelect").val();
	// var toUser = $("#toAccount").val();
	// var amt=$("#amount").val();
	 //var requestURL = "transferService?customerA="+customerAcc;
	 /*$.get(requestURL,function(data){
		    alert(data);
		  });*/
	 $.post( "paymentService", { toAcc: merchantAccountNumber,fromAcc:customerAccountNumber, amount: requestAmount, reqid:reqID})
	  .done(function( data ) {
	    alert( data );
	    document.location.reload(true);
	  });  	  
}
</script>
<body>
<ol id="toc">
    <li><a href="Account.html"><span>Account</span></a></li>
    <li><a href="payment.html"><span>Payment</span></a></li>
    <li><a href="Transfer"><span>Transfer</span></a></li>
    <li><a href="creditDebit"><span>Credit/Debit</span></a></li>
    <li class="current"><a href="authorize"><span>Make Payment</span></a></li>
    <li><a href="viewTransactions"><span>View Transactions</span></a></li>
    <li><a href="profile.html"><span>Profile</span></a></li>
    <li><a href="accountManagement.html"><span>Account Management</span></a></li>    
</ol>
    <div class="content" style="width:980px;height:300px">
    <center><h2>View Payment Requests</h2></center>
              <div>
             <table style="width: 100%;" border="1">
                
                <tr>
                <th>Requested from Merchant</th>
                <th>Amount Requested</th>
                <th>To be Paid from Account</th>
                <th></th></tr> 
               
               <c:forEach var="payment" items="${lstPayment}" varStatus="status">
                <tr>
                     <td>
                        ${payment.merchantName}
                     </td>
                     <td>
                        ${payment.requestAmount}
                     </td>
                     <td>
                        ${payment.customerAccountNumber}
                     </td>
                     <td><input type=button value="Make Payment" onclick="handlePayment(${payment.merchantAccountNumber},${payment.requestAmount},${payment.customerAccountNumber},${payment.reqID})"/></td>
                 </tr>
                 </c:forEach>
                 </table>
   </div>
   </div>
</body>
</html>
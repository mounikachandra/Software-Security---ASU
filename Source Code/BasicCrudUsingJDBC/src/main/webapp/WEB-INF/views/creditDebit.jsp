<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>External User</title>
     <link href="../resources/tabs.css" rel="stylesheet" type="text/css" /> 
    <style type="text/css">
        #Submit2
        {
            width: 94px;
        }
        #Submit1
        {
            width: 91px;
        }
    </style>
       <script type="text/javascript">
   
     function Credit(){
    	 var fromAccount=$("#accountSelect").val();
    	 var amt=$("#amount").val();
     		 
    	 $.post( "Credit", { fromAcc:fromAccount, amount: amt})
    	  .done(function( data ) {
    	    alert( data );
    	  });  		  
     }
     
     function Debit(){
    	 var fromAccount=$("#accountSelect").val();
    	 var amt=$("#amount").val();
     		  
    	 $.post( "Debit", { fromAcc:fromAccount, amount: amt})
    	  .done(function( data ) {
    	    alert( data );
    	  });  		  
     }
         
     
     
     
     $(document).ready(function () {
    	  //called when key is pressed in textbox
    	  $("#toAccount").keypress(function (e) {
    	     //if the letter is not digit then display error and don't type anything
    	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
    	        //display error message
    	        $("#errmsg").html("Digits Only").show().fadeOut("");
    	               return false;
    	    }
    	   });
    	});
     
     $(document).ready(function () {
     $("#amount").keypress(function (event) {
         //this.value = this.value.replace(/[^0-9\.]/g,'');
  $(this).val($(this).val().replace(/[^0-9\.]/g,''));
         if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     	});
     });
 

    </script>   
</head>
<body>
 
<ol id="toc">
    <li><a href="Account"><span>Account</span></a></li>
    <li><a href="TranPayment"><span>Payment</span></a></li>
    <li><a href="Transfer"><span>Transfer</span></a></li>
    <li  class="current"><a href="creditDebit"><span>Credit/Debit</span></a></li>
    <li><a href="authorize"><span>Make Payment</span></a></li>
    <li><a href="viewTransactions"><span>View Transactions</span></a></li>
    <li><a href="extProfile"><span>Profile</span></a></li>
    <li><a href="accmgmt"><span>Account Management</span></a></li> 
    <li><a href="<c:url value='/j_spring_security_logout'/>"><span>Logout</span></a></li>   
</ol>
    <div class="content" style="width:980px;height:300px">
     <center><h2>Credit/Debit</h2></center>
     <div id="mainDiv" style="width:100%">
        <div id="Div1" style="float:left;width:33%;">
            <label for="accountSelect" style="clear:both;display:block">From Account:</label>
            <br /><br /><br /> 
            
                <select id="accountSelect" style="clear:both;display:block">
            <c:forEach var="acc" items="${listAccount1}" varStatus="status">
            	
                <option>${acc.accNo}</option>
                </c:forEach>
           </select>
          
        </div>
        <div id="Div2" style="float:left;width:33%">
             Amount: <input type="text" id="amount" class='decimal' maxlength="15"><br>
            <br /><br/>
        </div>
         <input id="Submit1" type="submit" value="Credit" onclick="Credit()" /><br />
         <br />
         <input id="Submit2" type="submit" value="Debit" onclick="Debit()"/><br />
         </div>
    </div>
</body>
</html>
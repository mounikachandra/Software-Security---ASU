<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>External User</title>
    <link href="../resources/tabs.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        #Submit1
        {
            height: 25px;
            width: 137px;
        }
    </style>
    <script type="text/javascript">

        function ViewTransactions() {
            var selectedAcc = $("#accountSelected").val();
            //var requestURL = "transferService?customerA="+customerAcc;
            /*$.get(requestURL,function(data){
            alert(data);
            });*/
            $.post("viewService", { accSelected: selectedAcc})
	    	  .done(function (data) {
	    	    //  alert(data);
	    	    //  document.location.reload(true);
	    	    document.getElementById("showTransactions").innerHTML = data;
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
                $(this).val($(this).val().replace(/[^0-9\.]/g, ''));
                if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
                    event.preventDefault();
                }
            });
        });

       /* document.onmousedown = disableclick;
        status = "Right Click Disabled";
        function disableclick(event) {
            if (event.button == 2) {
                alert(status);
                return false;
            }
        }*/
    </script>    
</head>
<body>

<ol id="toc">
    <li><a href="Account"><span>Account</span></a></li>
    <li><a href="TranPayment"><span>Payment</span></a></li>
    <li><a href="Transfer"><span>Transfer</span></a></li>
    <li><a href="creditDebit"><span>Credit/Debit</span></a></li>
    <li><a href="authorize"><span>Make Payment</span></a></li>
    <li  class="current"><a href="viewTransactions"><span>View Transactions</span></a></li>
    <li><a href="extProfile"><span>Profile</span></a></li>
    <li><a href="accmgmt"><span>Account Management</span></a></li> 
    <li><a href="<c:url value='/j_spring_security_logout'/>"><span>Logout</span></a></li>   
</ol>
 <div class="content">
 <center><h2>View Transactions</h2></center>
      <div id="mainDiv" style="width:100%">
        <div id="Div1" style="float:left;width:20%;">
            <strong><label for="accountSelect" style="clear:both;">Select an Account:</label></strong>
            <br />
           <select id="accountSelected" style="clear:both;display:block">
            <c:forEach var="acc" items="${lstAccount}" varStatus="status">
                <option>${acc.accNo}</option>
                </c:forEach>
            </select>
        </div>
        <div id="Div2" style="float:left;width:80%">
        <br />
       
        
           <input id="Submit1" type="submit" value="View Transactions" style="right: 50%" onclick="ViewTransactions()" /></div>
            <br /><br /><br />
        </div>
        <br />
        <br />
       <div id="showTransactions"></div>
              </div>
</body>
</html>
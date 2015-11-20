<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Summary</title>
<style type="text/css">
	table{
		border: 1px solid #000;
	}
	tr:nth-child(even) {
	    background-color: #7F7F84;
	    color: #ffffff;
	}
</style>
</head>
<body>
	<div>
		<h3>Account No: ${accno}</h3>
		<h3>Balance: USD ${balance}</h3>
	</div>
	<h4>Completed Transactions</h4>
	<table cellpadding="7">
		<tr>
			<th>Transaction ID</th>
			<th>Transaction Details</th>
			<th>Amount</th>
			<th>Type</th>
			<th>Date</th>
		</tr>
		<c:forEach var="summary" items="${listSummary}" varStatus="status">
		<tr>
			<td>${summary.tid}</td>
			<c:choose>
				<c:when test="${(summary.fromAccNo == accno) && (summary.toAccNo == accno)}">
					<td>Self Transaction</td>
					<c:choose>
						<c:when test="${summary.debitAmount != 0.00}">
							<td>&#45; ${summary.debitAmount}</td>
							<td>Debit</td>
						</c:when>
						<c:otherwise>
							<td>${summary.creditAmount}</td>
							<td>Credit</td>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${summary.fromAccNo == accno}">
					<td>Transferred to account no: ${summary.toAccNo}</td>
					<td>&#45; ${summary.debitAmount}</td>
					<td>Debit</td>
				</c:when>
				<c:when test="${summary.toAccNo == accno}">
					<td>Received from account no: ${summary.fromAccNo}</td>
					<td>${summary.debitAmount}</td>
					<td>Credit</td>
				</c:when>
			</c:choose>
			<td>${summary.created}</td>
		</tr>
		</c:forEach>
	</table>
	<h4>Pending Transactions</h4>
	<table cellpadding="7">
		<tr>
			<th>Transaction ID</th>
			<th>Transaction Details</th>
			<th>Amount</th>
			<th>Type</th>
			<th>Date</th>
		</tr>
		<c:forEach var="pndsummary" items="${listPendingSummary}" varStatus="status">
		<tr>
			<c:choose>
				<c:when test="${(pndsummary.fromAccNo == accno) && (pndsummary.toAccNo == accno)}">
					<td>${pndsummary.tid}</td>
					<td>Self Transaction</td>
					<c:choose>
						<c:when test="${pndsummary.debitAmount != 0.00}">
							<td>&#45; ${pndsummary.debitAmount}</td>
							<td>Debit</td>
							<td>${pndsummary.created}</td>
						</c:when>
						<c:otherwise>
							<td>${pndsummary.creditAmount}</td>
							<td>Credit</td>
							<td>${pndsummary.created}</td>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${pndsummary.fromAccNo == accno}">
					<td>${pndsummary.tid}</td>
					<td>Transfer to account no: ${pndsummary.toAccNo}</td>
					<td>&#45; ${pndsummary.debitAmount}</td>
					<td>Debit</td>
					<td>${pndsummary.created}</td>
				</c:when>
			</c:choose>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
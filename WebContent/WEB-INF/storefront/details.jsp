<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cs3220" uri="http://github.com/xv435/SimpleStorefront"%>
<fmt:setLocale value="en_US" />
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Item Details</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">	
</head>
<body>

<div class="container">
	<div class="page-header">
		<h2>${ item.name } <small>Storefront</small></h2>
		<p><a href="Store">Back to Store</a> | <a href="ShoppingCart">View Cart</a></p>
	</div>		
	
	<br />
	
	<c:if test="${ empty items }">
		<div class="jumbotron">
			<h2>Sorry, there are no items.</h2>
		</div>
	</c:if>
	<c:if test="${ not empty items }">
		<table class="table table-bordered table-hover">
		<tr>
			<th>Name</th>
			<th>Price per Item</th>
			<th>Quantity</th>
			<th>Quantity in Cart</th>
			<th>Image</th>
			<th>Add Items to Cart</th>	
			<th>View Details</th>
		</tr>
		
		<c:forEach items="${ items }" var="item">
			<c:if test="${ item.quantity != 0 }">
			<tr>
				<td>${ item.name }</td>
				<td><fmt:formatNumber value="${ item.price }" type="currency" /></td>
				<td>${ item.quantity }</td>
				<td>
				<c:forEach items="${ cart }" var="potential">
					<c:if test="${ potential.id eq item.id }" >
						${ potential.quantity }
					</c:if>
				</c:forEach>
				</td>
				<td><img src="${ item.imagePath }" alt="No Image" /></td>
				<td>
						<form action="Transfer" method="POST" >
						<input type="text" name="quantity" placeholder="Enter an Amount" />
						<input type="hidden" name="id" value="${ item.id }" />
						<input type="submit" class="btn btn-info" value="Transfer Items" />
						</form>
				</td>		
				<td>
					<a href="Details?id=${ item.id }">View Details</a>
				</td>
			</tr>
			</c:if>
		</c:forEach>
		</table>
	</c:if>
	
	<br />
	
	<h3>Details of ${ item.name }</h3>
	
	<p>${ item.details }</p>
	
	
</div>

</body>
</html>

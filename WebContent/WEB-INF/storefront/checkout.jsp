<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cs3220" uri="http://github.com/xv435/SimpleStorefront"%>
<fmt:setLocale value="en_US" />
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Storefront</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">	
</head>
<body>

<div class="container">
	<div class="page-header">
		<h1>Checkout <small>Storefront</small></h1>
		<a href="Store">Back to Store</a>
	</div>
	
	<h4>Your Order</h4>
	<cs3220:itemlist list="${ cart }" cartOn="true" />
	
	<hr />
	<p>Please verify your order details above, then fill out you details and submit your order.</p>
	<form method="POST" action="Checkout">
		<label>Email <input type="text" name="email" class="form-control" placeholder="Email" /></label>
		<label>First Name <input type="text" name="firstname" class="form-control" placeholder="First Name" /></label>
		<label>Last Name <input type="text" name="lastname" class="form-control" placeholder="Last Name" /></label>
		<br />
		<input type="submit" value="Submit Order" class="btn btn-success" />
	</form>

</div>

</body>
</html>
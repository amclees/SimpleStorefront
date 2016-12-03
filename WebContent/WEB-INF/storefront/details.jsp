<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cs3220" uri="http://github.com/xv435/SimpleStorefront"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Item Details</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">	
</head>
<body>

<div class="container">
	<div class="page-header">
		<h2>${ item.name } <small><a href="Store">Back to Store</a></small></h2>
		<p><a href="Cart">View Cart</a></p>
	</div>		
	
	<br />
	
	<cs3220:itemlist list="${ items }" cartOn="false" />
	
	<br />
	
	<h3>Details of ${ item.name }</h3>
	
	<p>${ item.details }</p>
	
	
</div>

</body>
</html>

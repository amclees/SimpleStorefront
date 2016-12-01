<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
	<title>Storefront</title>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">	
	<%-- <link rel="stylesheet" href="https://bootswatch.com/paper/bootstrap.min.css"> --%>
</head>
<body>

<div class="container">

	<!-- Page Header -->
	<div class="page-header">
		<h1 style = 'color: #00ccff'>The SimpleStorefront Group <small style = 'color: #00ccff'>Storefront</small></h1>
	</div>
	
	<a class="btn btn-primary btn-lg" href="ShoppingCart" role="button">Shopping Cart</a>
	
	<br />
	<br />
	
	<table class="table table-hover table-striped table-bordered">
		<thead>
			<tr>
				<th>Name</th>
				<th>Item Detail</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Amount to Cart</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${items}" var="item">
				<tr>	
					<c:choose>	
						<c:when test="${empty item}">
								<h3>There are no items for sale</h3>
						</c:when>		
						<c:otherwise>
							<td>
								${item.name}
							</td>
							<td>
								<a target = "_blank" href = "">Detail</a>
							</td>
							<td>
								${item.price}
							</td>
							<td>
								${item.quantity}
							</td>
						<form action="Storefront">
							<td>
								<input type = "hidden" value = "${item.id}" name="id" />
									<select name="quantity">
								    	<option value="1">1</option>
								    	<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
									</select>
									
								
							</td>
							<td>
								<%-- <c:url value="Storefront" var="updateUrl">
								   <c:param name="id" value="${item.id}"/>
								   <c:param name="quantity" value="quantity"/>
								</c:url> --%>
								
								<input type="submit" value="Add to Cart">
								
								<%-- <a class="btn btn-primary btn-xs" href="${updateUrl}">Add to Cart</a> --%>
							</td>
						</form>
						
						</c:otherwise>	
					</c:choose>		
									
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	
	
	
	<table class="table table-hover table-striped table-bordered">
		<thead>
			<tr>
				<th>Name</th>
				<th>Item Detail</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Amount to Cart</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cart}" var="cartItem">
				<tr>	
					<c:choose>	
						<c:when test="${empty cartItem}">
								<h3>There are no items for sale</h3>
						</c:when>		
						<c:otherwise>
							<td>
								${cartItem.name}
							</td>
							<td>
								<a target = "_blank" href = "">Detail</a>
							</td>
							<td>
								${cartItem.price}
							</td>
							<td>
								${cartItem.quantity}
							</td>
						<form action="Storefront">
							<td>
								<input type = "hidden" value = "${cartItem.id}" name="id" />
									<select name="quantity">
								    	<option value="1">1</option>
								    	<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
									</select>
									
								
							</td>
							<td>
								<%-- <c:url value="Storefront" var="updateUrl">
								   <c:param name="id" value="${item.id}"/>
								   <c:param name="quantity" value="quantity"/>
								</c:url> --%>
								
								<input type="submit" value="Add to Cart">
								
								<%-- <a class="btn btn-primary btn-xs" href="${updateUrl}">Add to Cart</a> --%>
							</td>
						</form>
						
						</c:otherwise>	
					</c:choose>		
									
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	
	
</div>

</body>
</html>

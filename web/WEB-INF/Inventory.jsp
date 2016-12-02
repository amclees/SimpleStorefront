<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Add Item</title>
</head>
<body>
<div class="container">
	<div class="page-header text-center">
		<h1>Add Item</h1>
	</div>

	<form action="Inventory" method="post">
		<table class="table table-striped table-bordered table-hover text-center" border="1">
			<tr>
				<th class="text-center">Name</th>
				<th class="text-center">Detail</th>
				<th class="text-center">Price</th>
				<th class="text-center">Amount of Quantity</th>
				<th class="text-center">Action</th>
			</tr>

			<tr>
				<td>
					<input type="text" name="addName" placeholder="Name" value="">
				</td>
				
				<td>
					<textarea name="addDetail" placeholder="Description" rows="1"></textarea>
				</td>
				
				<td>
					<input type="text" name="addPrice" placeholder="Price" value="">
				</td>
				
				<td>
					<input type="text" name="addQuantity" placeholder="Quantity" value="">
				</td>
				
				<td>
					<input class="btn btn-primary" type="submit" value="Add Item">
				</td>
			</tr>
		</table>
	</form>
	
	<h1>Remove Item</h1>
	
	<table class="table table-hover table-striped table-bordered">
		<thead>
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Remove from Cart</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${items}" var="item">
				<tr>	
					<c:choose>	
						<c:when test="${empty item}">
								<h3>There are no items</h3>
						</c:when>		
						<c:otherwise>
							<td>
								${item.name}
							</td>
							<td>
								${item.price}
							</td>
							<td>
								${item.quantity}
							</td>
						<form action="Inventory">
							<td>
								<input type = "hidden" value = "${item.id}" name="id" />
									<select name="removeQuantity">
								    	<option value="${item.quantity}">${item.quantity}</option>
								    	<option value="5">5</option>
										<option value="4">4</option>
										<option value="3">3</option>
										<option value="2">2</option>
										<option value="1">1</option>
									</select>
									
							</td>
							<td>
								
								<input type="submit" value="Remove from Cart">
								
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

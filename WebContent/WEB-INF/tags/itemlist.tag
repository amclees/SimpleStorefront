<%@ tag body-content="scriptless" %>
<%@ attribute name="list" type="java.util.List" required="true" %>
<%@ attribute name="cart" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:doBody />

<c:if test="${ empty list }">
	<div class="jumbotron">
		<h2>Sorry, there are no items.</h2>
	</div>
</c:if>

<c:if test="${ not empty list }">
<table class="table table-bordered table-hover">
<tr>
	<th>Name</th>
	<th>Price</th>
	<th>Quantity</th>
	<th>Image</th>
</tr>

<c:forEach items="${ list }" var="item">
	<tr>
		<td>${ item.name }</td>
		<td>${ item.price }</td>
		<td>${ item.quantity }</td>
		<td><img src="${ item.imagePath }" alt="No Image" /></td>
		<form action="Storefront" method="POST" >
		<td>
				<input type="text" name="quantity" placeholder="Enter an Amount" value="${ item.quantity }" />
		</td>
		<td>
				<input type="hidden" name="id" value="${ item.id }" />
				<c:if test="${ cart eq true }"><input type="hidden" name="cart" value="true" /></c:if>
				<input type="submit" class="btn btn-info" />
		</td>
		</form>
	</tr>
</c:forEach>
</table>
</c:if>
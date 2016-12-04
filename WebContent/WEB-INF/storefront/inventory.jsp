<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Inventory Admin</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<div class="container">
  <fmt:setLocale value="en_US"/>
  <div class="page-header">
    <h1>Inventory Manager
      <small>Storefront</small>
    </h1>
    <a href="Store">Back to Store</a>
  </div>
  <h3>Create a New Item</h3>
  <form action="Inventory" method="POST">
    <input type="text" name="name" placeholder="Name of Item" class="form-control"/>
    <br/>
    <input type="text" name="price" placeholder="Price" class="form-control"/>
    <br/>
    <input type="text" name="quantity" placeholder="Quantity" class="form-control"/>
    <br/>
    <label>Image Input Could Go Here</label>
    <br/>
    <label>Details <br/><textarea name="details" class="form-control"></textarea></label>
    <br/>
    <input type="submit" class="btn btn-success" value="Submit New Item"/>
  </form>

  <h3>Modify Existing Item Stocks</h3>
  <c:if test="${ empty list }">
    <div class="jumbotron">
      <h2>Sorry, there are no items.</h2>
    </div>
  </c:if>
  <c:if test="${ not empty list }">
    <table class="table table-bordered table-hover">
      <tr>
        <th>Name</th>
        <th>Price per Item</th>
        <th>Quantity</th>
        <th>Image</th>
        <th>Add/Remove Items</th>
        <th>Delete Type</th>
        <th>Details</th>
      </tr>

      <c:forEach items="${ list }" var="item">
        <tr>
          <td>${ item.name }</td>
          <td><fmt:formatNumber value="${ item.price }" type="currency"/></td>
          <td>${ item.quantity }</td>
          <td><img src="${ item.imagePath }" alt="No Image"/></td>
          <td>
            <form action="Inventory" method="POST">
              <input type="text" name="quantity" placeholder="Positive or Negative" value="${ -1 * item.quantity }"/>
              <input type="hidden" name="id" value="${ item.id }"/>
              <input type="submit" class="btn btn-success" value="Modify Stock"/>
            </form>
          </td>
          <td>
            <form action="Inventory" method="POST">
              <input type="hidden" name="id" value="${ item.id }"/>
              <input type="hidden" name="delete" value="true"/>
              <input type="submit" class="btn btn-danger" value="Delete Item"/>
            </form>
          </td>
          <td>
              ${ item.details }
          </td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
</div>
</body>
</html>
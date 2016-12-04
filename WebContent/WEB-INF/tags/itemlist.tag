<%@ tag body-content="scriptless" %>
<%@ attribute name="list" type="java.util.List" required="true" %>
<%@ attribute name="cartOn" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:doBody/>
<fmt:setLocale value="en_US"/>

<c:if test="${ empty list }">
  <div class="jumbotron">
    <h2>Sorry, there are no items.</h2>
  </div>
</c:if>

<c:if test="${ not empty list }">
  <c:if test="${ cartOn eq true }">
    <p>The total cost of all your items is <fmt:formatNumber value="${ totalPrice }" type="currency"/></p>
  </c:if>
  <table class="table table-bordered table-hover">
    <tr>
      <th>Name</th>
      <th>Price per Item</th>
      <th>Quantity</th>
      <th>Image</th>

      <c:if test="${ not cartOn eq true }">
        <th>Add Items to Cart</th>
      </c:if>
      <c:if test="${ cartOn eq true }">
        <th>Remove Items</th>
        <th>
          Total Cost
        </th>
      </c:if>
      <th>View Details</th>
    </tr>

    <c:forEach items="${ list }" var="item">
      <c:if test="${ item.quantity != 0 }">
        <tr>
          <td>${ item.name }</td>
          <td><fmt:formatNumber value="${ item.price }" type="currency"/></td>
          <td>${ item.quantity }</td>
          <td><img src="${ item.imagePath }" alt="No Image"/></td>
          <td>
            <form action="Transfer" method="POST">
              <input type="text" name="quantity" placeholder="Enter an Amount"/>
              <input type="hidden" name="id" value="${ item.id }"/>
              <c:if test="${ cartOn eq true }"><input type="hidden" name="cart" value="true"/></c:if>
              <input type="submit" class="btn btn-info" value="Transfer Items"/>
            </form>
          </td>

          <c:if test="${ cartOn eq true }">
            <td>
              <fmt:formatNumber value="${ item.price * item.quantity }" type="currency"/>
            </td>
          </c:if>

          <td>
            <a href="Details?id=${ item.id }">View Details</a>
          </td>
        </tr>
      </c:if>
    </c:forEach>
  </table>
</c:if>
<%@ tag body-content="scriptless" %>
<%@ attribute name="list" type="java.util.List" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:doBody />


<c:if test="${ empty list }">
	<div class="jumbotron">
		<h2>There are no logs.</h2>
	</div>
</c:if>


<c:if test="${ not empty list }">
<table class="table table-bordered table-hover">
<tr>
	<th>Timestamp</th>
	<th>Name</th>
	<th>Description</th>
</tr>
<c:forEach items="${ list }" var="item">
	<tr>
		<td><fmt:formatDate value="${ item.timeStamp }" pattern="GG yyyy EEEE MMMM dd, HH:mm:ss" /></td>
		<td>${ item.name }</td>
		<td>${ item.description }</td>
	</tr>
</c:forEach>
</table>
</c:if>
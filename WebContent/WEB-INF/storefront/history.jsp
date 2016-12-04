<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cs3220" uri="http://github.com/xv435/SimpleStorefront" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Storefront</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<div class="container">
  <div class="page-header">
    <h1>History
      <small>Storefront</small>
    </h1>
    <a href="Store">Back to Storefront</a>
  </div>

  <br/>

  <cs3220:loglist list="${ items }"/>

</div>
</body>
</html>

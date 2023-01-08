<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Profile" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-25 p-3">
    <h2>Your change</h2>
    <form action="controller" method="POST">
        <label for="paramInput" class="form-label">${updateParam}</label>
        <input class="form-control" type="text" id="paramInput" name = "updateValue" value="${paramValue}">
        <input type="hidden" name="action" value="saveUpdate"/>
        <input type="submit" class="btn btn-success" value="Save"/>
    </form>

    <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>
  </body>

</html>
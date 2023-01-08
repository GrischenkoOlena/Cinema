<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Cinema" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-50 p-3">
    <h2>User main</h2>
    <jsp:include page="/WEB-INF/templates/menu_user.jsp"></jsp:include>

    <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>
  </body>

</html>
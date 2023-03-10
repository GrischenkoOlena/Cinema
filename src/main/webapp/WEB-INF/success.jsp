<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Cinema" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-50 p-3">
    <h2><fmt:message key="success.header"/></h2>
    <br>
    <form method="GET" action="controller?action=enter">
        <input type="hidden" name="page" value="login"/>
        <button class="btn btn-outline-success" type="submit"><fmt:message key="button.login"/></button>
    </form>

    <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>
  </body>

</html>
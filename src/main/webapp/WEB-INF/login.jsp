<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Cinema" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-25 p-3">
    <h2><fmt:message key="login.header"/></h2>

    <div class="container">
      <form action="controller" method="POST">
        <div class="mb-3">
          <label for="email" class="form-label"><fmt:message key="login.label.email"/></label>
          <input type="email" name="login" class="form-control" id="email" aria-describedby="emailHelp" value="${login}">
          <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div class="mb-3">
          <label for="password" class="form-label"><fmt:message key="login.label.password"/></label>
          <input type="password" name="password" class="form-control" id="password">
        </div>
        <button type="submit" name="action" value="login" class="btn btn-primary">
            <fmt:message key="button.login"/>
        </button>

        <input type="hidden" name="error"/>
        <div> <p class="text-danger"> ${error} </p></div>
      </form>
     </div>
     
     <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>
</body>
</html>
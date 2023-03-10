<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Cinema" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-25 p-3">
    <h2><fmt:message key="signUp.header"/></h2>
    <div class="container">
      <form action="controller" method="POST">
        <div class="mb-3">
          <label for="email" class="form-label"><fmt:message key="signUp.label.email"/></label>
          <input type="email" name="login" class="form-control" aria-describedby="emailHelp" required>
          <div id="emailHelp" class="form-text">We'll never share your email with anyone else.
        </div>
        <div class="mb-3">
          <label for="name" class="form-label"><fmt:message key="signUp.label.name"/></label>
          <input type="input" name="userName" class="form-control">
        </div>
        <div class="mb-3">
          <label for="inputPassword" class="form-label"><fmt:message key="signUp.label.password"/></label>
          <input type="password" name="inputPassword" class="form-control" required>
        </div>
        <div class="mb-3">
           <label for="repeatPassword" class="form-label"><fmt:message key="signUp.label.repeat"/></label>
           <input type="password" name="repeatPassword" class="form-control" required>
        </div>
        <button type="submit" name="action" value="signUp" class="btn btn-primary">
            <fmt:message key="button.signup"/>
        </button>
        <p class="p-3 mb-2 text-danger"> ${error} </p>
      </form>
     </div>
     
     <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>
</body>
</html>
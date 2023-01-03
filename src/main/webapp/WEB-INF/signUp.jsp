<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Cinema" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-25 p-3">
    <h2>Login</h2>

    <div class="container">
      <form action="controller" method="POST">
        <div class="mb-3">
          <label for="email" class="form-label">Email address</label>
          <input type="email" name="login" class="form-control" id="email" aria-describedby="emailHelp" required>
          <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div class="mb-3">
          <label for="inputPassword" class="form-label">Password</label>
          <input type="password" name="inputPassword" class="form-control">
        </div>
        <div class="mb-3">
           <label for="repeatPassword" class="form-label">Repeat password</label>
           <input type="password" name="repeatPassword" class="form-control">
        </div>
        <div class="mb-3 form-check">
          <input type="checkbox" class="form-check-input" id="checkMe">
          <label class="form-check-label" for="checkMe">Check me out</label>
        </div>
        <button type="submit" name="action" value="signUp" class="btn btn-primary">Submit</button>
      </form>
     </div>
     
     <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>
</body>
</html>
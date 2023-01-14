<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Administrator" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-50 p-3">
      <h2>Add new session</h2>
          <div class="container">
            <form action="controller" method="POST">
              <div class="mb-3">
                <label for="film" class="form-label">Film</label>
                <input type="input" id="film" name="film" class="form-control" required>
              </div>
              <div class="mb-3">
                <label for="date" class="form-label">Date movie</label>
                <input type="input" id="date" name="dateFilm" class="form-control" required>
              </div>
              <div class="mb-3">
                <label for="time" class="form-label">Time begin</label>
                <input type="input" id="time" name="timeBegin" class="form-control">
              </div>
              <div class="mb-3">
                  <label for="state" class="form-label">State session</label>
                  <input type="input" id="state" name="stateScreening" class="form-control">
              </div>

              <button type="submit" name="action" value="addScreening" class="btn btn-primary">Sign up</button>
              <p class="p-3 mb-2 text-danger"> ${error} </p>
            </form>
           </div>



    <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>
  </body>

</html>
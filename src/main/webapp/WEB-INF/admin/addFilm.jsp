<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Administrator" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-50 p-3">
    <h2>Add new movie</h2>
        <div class="container">
          <form action="controller" method="POST">
            <div class="mb-3">
              <label for="title" class="form-label">Title</label>
              <input type="input" id="title" name="title" class="form-control" required>
            </div>
            <div class="mb-3">
              <label for="director" class="form-label">Director</label>
              <input type="input" id="director" name="director" class="form-control">
            </div>
            <div class="mb-3">
              <label for="cast" class="form-label">Cast</label>
              <input type="input" id="cast" name="cast" class="form-control">
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <input type="input" id="description" name="description" class="form-control">
            </div>
            <div class="mb-3">
                <label for="genre" class="form-label">Genre</label>
                <input type="input" id="genre" name="genre" class="form-control">
            </div>
            <div class="mb-3">
                <label for="duration" class="form-label">Duration</label>
                <input type="input" id="duration" name="duration" class="form-control">
            </div>

            <button type="submit" name="action" value="empty" class="btn btn-primary">Sign up</button>
            <p class="p-3 mb-2 text-danger"> ${error} </p>
          </form>
         </div>

         <h1>${desc}</h1>



    <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>
  </body>

</html>
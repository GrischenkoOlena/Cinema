<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Administrator" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-50 p-3">
  <jsp:include page="/WEB-INF/templates/menu_admin.jsp"></jsp:include>

    <br>
    <h2>Movies</h2>
        <div class="container">
          <form class="row g-3" action="controller" method="POST">
            <div class="col-auto">
              <select class="form-select" name="order">
                <option selected>Sort by</option>
                <option value="nameAsc">name &#8593;</option>
                <option value="nameDesc">name &#8595;</option>
                <option value="durationAsc">duration &#8593;</option>
                <option value="durationDesc">duration &#8595;</option>
              </select>
            </div>

            <div class="dropdown col-auto">
              <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                Filter movies by genre
              </button>
              <ul class="dropdown-menu">
                <c:forEach var="genre" items="${genres}">
                  <li><a class="dropdown-item" href="#">${genre}</a></li>
                </c:forEach>
              </ul>
            </div>

            <div class="col-auto">
              <input type="hidden" name="action" value="films"/>
              <input type="submit" class="btn btn-success" value="Apply"/>
            </div>

            <nav>
              <ul class="pagination pagination-sm justify-content-center">
                <c:forEach var = "i" begin = "1" end = "${countPages}">
                  <c:set var="hrefPage" value="controller?action=films&page=${i}" />
                  <li class="page-item"><a class="page-link" href="${hrefPage}">${i}</a></li>
                </c:forEach>
              </ul>
            </nav>
          </form>
          
          <table class="table">
            <thead>
              <tr>
                <th scope="col">title</th>
                <th scope="col">director</th>
                <th scope="col">cast</th>
                <th scope="col">description</th>
                <th scope="col">genre</th>
                <th scope="col">duration</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="film" items="${films}">
                <tr>
                  <td>${film.title}</td>
                  <td>${film.director}</td>
                  <td>${film.cast}</td>
                  <td>${film.description}</td>
                  <td>${film.genre}</td>
                  <td>${film.duration}</td>
                  <td>
                    <form action="controller" method="POST">
                      <input type="hidden" name="action" value="updateFilm"/>
                      <input type="hidden" name="filmId" value=${film.id}/>
                      <input type="submit" class="btn btn-success" value="Update movie"/>
                    </form>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>

    <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>
  </body>

</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Cinema" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-50 p-3">
    <jsp:include page="/WEB-INF/templates/menu_user.jsp"></jsp:include>
    <br>
    <h2>Schedule</h2>
        <div class="container">
          <form class="row g-3" action="controller" method="POST">
            <div class="col-auto">
              <div class="input-group mb-3">
                <label class="input-group-text" for="inputOrder">Sort by</label>
                <select class="form-select" id="inputOrder" name="order">
                  <option selected style="display:none;"></option>
                  <option value="movieAsc">movie title &#8593;</option>
                  <option value="movieDesc">movie title &#8595;</option>
                  <option value="dataAsc">data/time &#8593;</option>
                  <option value="dataDesc">data/time &#8595;</option>
                  <option value="avaibleAsc">avaible seats &#8593;</option>
                  <option value="avaibleDesc">avaible seats &#8595;</option>
                </select>
                <button class="btn btn-outline-secondary" type="button">Apply</button>
              </div>
            </div>

            <div class="form-check col-auto">
              <input class="form-check-input" type="checkbox" value="" id="flexCheck">
              <label class="form-check-label" for="flexCheck">
                 the movies available for viewing
              </label>
            </div>
            <input type="hidden" name="action" value="schedule">
          </form>

          <nav>
            <ul class="pagination pagination-sm justify-content-center">
              <c:forEach var = "i" begin = "1" end = "${countPages}">
                <c:set var="hrefPage" value="controller?action=schedule&page=${i}" />
                <li class="page-item"><a class="page-link" href="${hrefPage}">${i}</a></li>
              </c:forEach>
            </ul>
          </nav>

          <table class="table">
            <thead>
              <tr>
                <th scope="col">movie</th>
                <th scope="col">data</th>
                <th scope="col">time</th>
                <th scope="col">state</th>
                <th scope="col">available seats</th>
              </tr>
            </thead>
              <tbody>
              <c:forEach var="screening" items="${screenings}">
                  <tr>
                      <td>${screening.film.title}</td>
                      <td>${screening.filmDate}</td>
                      <td>${screening.timeBegin}</td>
                      <td>${screening.state}</td>
                      <td>${screening.availableSeats}</td>
                      <td>
                          <form action="controller" method="POST">
                          <input type="hidden" name="action" value="freeSeats"/>
                          <input type="hidden" name="screeningId" value=${screening.id} />
                          <input type="submit" class="btn btn-success" value="View"/>
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
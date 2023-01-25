<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="${sessionScope.lang}">
  <c:set var="title" value="Administrator" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>
  <body class="w-75 p-3">
    <jsp:include page="/WEB-INF/templates/menu_admin.jsp"></jsp:include>

  <!-- Modal add new session -->
  <div class="modal fade" id="addNewSessionForm" aria-hidden="true"
              aria-labelledby="addNewSessionLabel" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="addNewSessionLabel"> <fmt:message key="admin.main.new.header"/></h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>

        <form action="controller?action=addScreening" method="POST">
          <div class="modal-body">
            <div class="mb-3">
              <label for="film" class="form-label"> <fmt:message key="admin.main.new.label.film"/></label>
              <select class="form-select" id="film" name="film">
                <option selected style="display:none;"></option>
                <c:forEach var="film" items="${films}">
                  <option value="${film.id}">${film.title}</option>
                </c:forEach>
              </select>
            </div>
            <div class="mb-3">
              <label for="date" class="form-label"> <fmt:message key="admin.main.new.label.date"/></label>
              <input type="date" id="date" name="date" class="form-control">
            </div>
            <div class="mb-3">
              <label for="time" class="form-label"> <fmt:message key="admin.main.new.label.time"/></label>
              <input type="input" id="time" name="time" class="form-control">
            </div>
            <div class="mb-3">
              <label for="state" class="form-label"> <fmt:message key="admin.main.new.label.state"/></label>
              <select class="form-select" id="state" name="state">
                <option selected style="display:none;"></option>
                <c:forEach var="state" items="${states}">
                  <option value="${state.id}">${state}</option>
                </c:forEach>
              </select>
            </div>
          </div>

          <div class="modal-footer">
            <button type="button" name="btnClose" class="btn btn-secondary" data-bs-dismiss="modal">
                <fmt:message key="button.close"/>
            </button>
            <button type="submit" name="btnAddSession" class="btn btn-dark">
                <fmt:message key="admin.main.new.button"/>
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>

        <br>
        <h2> <fmt:message key="admin.main.schedule"/> </h2>
        <div class="container">
          <form class="row g-3" action="controller" method="POST">
            <div class="col-auto">
              <div class="input-group mb-3">
                <c:set var="selectedOrder" value="${sessionScope.orderScreening}" />
                <label class="input-group-text" for="inputOrder"> <fmt:message key="admin.main.sort.label"/></label>
                <select class="form-select" id="inputOrder" name="order">
                  <option <c:if test="${selectedOrder == ''}"> selected </c:if> style="display:none;"></option>
                  <option value="movieAsc" <c:if test="${selectedOrder == 'movieAsc'}"> selected </c:if>>
                    movie title &#8593;</option>
                  <option value="movieDesc" <c:if test="${selectedOrder == 'movieDesc'}"> selected </c:if>>
                    movie title &#8595;</option>
                  <option value="dataAsc" <c:if test="${selectedOrder == 'dataAsc'}"> selected </c:if>>
                    data/time &#8593;</option>
                  <option value="dataDesc" <c:if test="${selectedOrder == 'dataDesc'}"> selected </c:if>>
                    data/time &#8595;</option>
                  <option value="availableAsc" <c:if test="${selectedOrder == 'availableAsc'}"> selected </c:if>>
                    available seats &#8593;</option>
                  <option value="availableDesc" <c:if test="${selectedOrder == 'availableDesc'}"> selected </c:if>>
                    available seats &#8595;</option>
                </select>
                <button class="btn btn-outline-secondary" type="submit" name="btnApplySort">
                     <fmt:message key="admin.main.sort.button"/>
                </button>
              </div>
            </div>

            <c:set var="checkedFilter" value="${sessionScope.filter}" />
            <div class="form-check col-auto">
              <label class="form-check-label">
                <input class="form-check-input" type="checkbox" value="" id="flexCheck"
                            name="filter" <c:if test="${filterScreening == 'checked'}" > checked </c:if>>
                  <fmt:message key="admin.main.filter.label"/>
              </label>
            </div>
            <input type="hidden" name="action" value="screenings">
          </form>

          <nav>
            <ul class="pagination pagination-sm justify-content-center">
              <c:forEach var = "i" begin = "1" end = "${countPages}">
                <c:set var="hrefPage" value="controller?action=screenings&page=${i}" />
                <li class="page-item"><a class="page-link" href="${hrefPage}">${i}</a></li>
              </c:forEach>
            </ul>
          </nav>

          <table class="table">
            <thead>
              <tr>
                <th scope="col"> <fmt:message key="admin.main.table.movie"/></th>
                <th scope="col"> <fmt:message key="admin.main.table.data"/></th>
                <th scope="col"> <fmt:message key="admin.main.table.time"/></th>
                <th scope="col"> <fmt:message key="admin.main.table.state"/></th>
                <th> </th>
                <th scope="col"> <fmt:message key="admin.main.table.freeSeat"/></th>
                <th> </th>
              </tr>
            </thead>
              <tbody>
              <c:forEach var="screening" items="${screenings}">
                  <tr>
                      <td>${screening.filmTitle}</td>
                      <td>${screening.filmDate}</td>
                      <td>${screening.timeBegin}</td>
                      <td>${screening.state}</td>
                      <td>
                          <form action="controller" method="POST">
                            <input type="hidden" name="action" value="updateScreening"/>
                            <input type="hidden" name="state" value=${screening.state} />
                            <input type="hidden" name="screeningId" value=${screening.id} />
                            <button class="btn btn-success" type="submit">
                              <fmt:message key="admin.main.table.button.update"/>
                            </button>
                          </form>
                      </td>
                      <td>${screening.freePlaces}</td>
                      <td>
                          <form action="controller" method="POST">
                          <input type="hidden" name="action" value="freeSeats"/>
                          <input type="hidden" name="screeningId" value=${screening.id} />
                          <button class="btn btn-warning" type="submit">
                            <fmt:message key="admin.main.table.button"/>
                          </button>
                          </form>
                      </td>
                  </tr>
              </c:forEach>
              </tbody>
          </table>

          <button type="button" class="btn btn-success" data-bs-toggle="modal"
                    data-bs-target="#addNewSessionForm">
             <fmt:message key="admin.main.button.add"/>
          </button>
          <div> <p class="text-danger"> ${errorAddScreening} </p></div>
          <c:set var="errorAddScreening" value="" scope="session"/>
        </div>

    <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>
  </body>
</html>
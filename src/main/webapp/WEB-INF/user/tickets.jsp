<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Cinema" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-50 p-3">
    <jsp:include page="/WEB-INF/templates/menu_user.jsp"></jsp:include>

    <br>
    <h2>Your tickets</h2>
    <div class="container">
      <form class="row g-3" action="controller" method="POST">
        <div class="col-auto">
          <select class="form-select" name="order">
            <option selected>Sort by</option>
            <option value="movieAsc">movie title &#8593;</option>
            <option value="movieDesc">movie title &#8595;</option>
            <option value="dataAsc">data/time &#8593;</option>
            <option value="dataDesc">data/time &#8595;</option>
          </select>
        </div>

        <div class="col-auto">
          <input type="hidden" name="action" value="tickets"/>
          <input type="submit" class="btn btn-success" value="Apply"/>
        </div>
      </form>

      <table class="table">
        <thead>
          <tr>
            <th scope="col">movie</th>
            <th scope="col">data</th>
            <th scope="col">time</th>
            <th scope="col">seat</th>
          </tr>
        </thead>
          <tbody>
          <c:forEach var="ticket" items="${tickets}">
              <tr>
                  <td>${ticket.screening.film}</td>
                  <td>${ticket.screening.filmDate}</td>
                  <td>${ticket.screening.timeBegin}</td>
                  <td>
                    <c:forEach var="seat" items="${ticket.seats}">
                      seat (${seat.line}, ${seat.place}), ${seat.category} <br>
                    </c:forEach>
                  </td>
                  <td>
                    <form action="controller" method="POST">
                      <input type="hidden" name="action" value="turnTicket"/>
                      <input type="hidden" name="ticket" value=${ticket.id} />
                      <input type="submit" class="btn btn-success" value="Turn ticket"/>
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
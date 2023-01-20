<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Cinema" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-75 p-3">
    <jsp:include page="/WEB-INF/templates/menu_user.jsp"></jsp:include>

    <br>
    <h2><fmt:message key="user.tickets.header"/></h2>
    <div class="container">
      <form class="row g-3" action="controller" method="POST">
        <div class="col-auto">
          <div class="input-group mb-3">
          <c:set var="selectedOrder" value="${sessionScope.orderTickets}" />
            <label class="input-group-text" for="inputOrder"><fmt:message key="user.tickets.label.sort"/></label>
            <select class="form-select" id="inputOrder" name="order">
              <option selected style="display:none;"></option>
              <option value="dataTicketAsc" <c:if test="${selectedOrder == 'dataTicketAsc'}"> selected </c:if>>
                data/time &#8593;</option>
              <option value="dataTicketDesc" <c:if test="${selectedOrder == 'dataTicketDesc'}"> selected </c:if>>
                data/time &#8595;</option>
            </select>
            <button class="btn btn-outline-secondary" type="submit" name="btnApplySort">
                <fmt:message key="user.tickets.button.sort"/>
            </button>
          </div>
        </div>

        <input type="hidden" name="action" value="tickets"/>
      </form>

           <nav>
             <ul class="pagination pagination-sm justify-content-center">
               <c:forEach var = "i" begin = "1" end = "${countPages}">
                 <c:set var="hrefPage" value="controller?action=tickets&page=${i}" />
                 <li class="page-item"><a class="page-link" href="${hrefPage}">${i}</a></li>
               </c:forEach>
             </ul>
           </nav>

      <table class="table">
        <thead>
          <tr>
            <th scope="col"><fmt:message key="user.tickets.table.movie"/></th>
            <th scope="col"><fmt:message key="user.tickets.table.data"/></th>
            <th scope="col"><fmt:message key="user.tickets.table.time"/></th>
            <th scope="col"><fmt:message key="user.tickets.table.seat"/></th>
            <th> </th>
          </tr>
        </thead>
          <tbody>
            <c:forEach var="ticket" items="${tickets}">
              <tr>
                  <td>${ticket.screening.film.title}</td>
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
                      <button class="btn btn-success" type="submit">
                        <fmt:message key="user.tickets.table.button"/>
                      </button>
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
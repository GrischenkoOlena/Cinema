<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Cinema" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-50 p-3">
    <jsp:include page="/WEB-INF/templates/menu_user.jsp"></jsp:include>

    <br>
    <h2><fmt:message key="user.basket.header"/></h2>
    <div class="container">
      <div> <p class="text-info">
        <c:out value = "${screening.film.title}, ${screening.filmDate}, ${screening.timeBegin}" />
      </p></div>

      <table class="table">
        <thead>
          <tr>
            <th scope="col"> <fmt:message key="user.basket.table.row"/></th>
            <th scope="col"> <fmt:message key="user.basket.table.place"/></th>
            <th scope="col"> <fmt:message key="user.basket.table.category"/></th>
            <th scope="col"> <fmt:message key="user.basket.table.price"/></th>
            <th></th>
          </tr>
        </thead>
          <tbody>
            <c:forEach var="seat" items="${seats}">
              <tr>
                  <td>${seat.line}</td>
                  <td>${seat.place}</td>
                  <td>${seat.category.category}</td>
                  <td>${seat.category.price}</td>
                  <td>
                    <form action="controller" method="POST">
                      <input type="hidden" name="action" value="deleteFromBasket"/>
                      <input type="hidden" name="seatId" value=${seat.id} />
                      <button class="btn btn-success" type="submit"/>
                        <fmt:message key="user.basket.table.button"/>
                    </button>
                    </form>
              </tr>
            </c:forEach>
          </tbody>
          <tfoot>
            <td colspan="3"> <fmt:message key="user.basket.table.cost"/></td>
            <td>${cost}</td>
          </tfoot>
      </table>

      <form action="controller" method="POST">
        <button type="button" name="btnNextSeat" class="btn btn-primary" >
            <c:set var="hrefNextSeat" value="controller?action=freeSeats&screeningId=${screening.id}"/>
            <a class="page-link" href="${hrefNextSeat}"> <fmt:message key="user.basket.button.next"/></a>
        </button>
        <button type="submit" name="btnBuy" class="btn btn-success">
            <fmt:message key="user.basket.button.buy"/>
        </button>
        <input type="hidden" name="action" value="purchase">
      </form>

      <div> <p class="text-danger"> ${errorBuyTicket} </p></div>
    </div>

    <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>
  </body>

</html>
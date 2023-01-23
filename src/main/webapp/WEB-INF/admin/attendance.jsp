<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="${sessionScope.lang}">
  <c:set var="title" value="Administrator" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-75 p-3">
    <jsp:include page="/WEB-INF/templates/menu_admin.jsp"></jsp:include>

    <br>
    <h2><fmt:message key="admin.attendance.header"/></h2>
    <div class="container">
      <nav>
        <ul class="pagination pagination-sm justify-content-center">
          <c:forEach var = "i" begin = "1" end = "${countPages}">
            <c:set var="hrefPage" value="controller?action=attendance&page=${i}" />
            <li class="page-item"><a class="page-link" href="${hrefPage}">${i}</a></li>
          </c:forEach>
        </ul>
      </nav>
    
    <table class="table">
      <thead>
        <tr>
          <th scope="col"><fmt:message key="admin.attendance.table.date"/></th>
          <th scope="col"><fmt:message key="admin.attendance.table.sessions"/></th>
          <th scope="col"><fmt:message key="admin.attendance.table.films"/></th>
          <th scope="col"><fmt:message key="admin.attendance.table.freeSeats"/></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="attendance" items="${attendances}">
          <tr>
            <td>${attendance.date}</td>
            <td>${attendance.countSessions}</td>
            <td>${attendance.countFilms}</td>
            <td>${attendance.countFreeSeats}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>

    <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>
  </body>

</html>
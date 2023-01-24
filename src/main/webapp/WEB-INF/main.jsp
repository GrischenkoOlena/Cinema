<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="${sessionScope.lang}">
  <c:set var="title" value="Schedule" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-50 p-3">
    <jsp:include page="/WEB-INF/templates/menu_main.jsp"></jsp:include>
    <h2><fmt:message key="main.schedule.header"/></h2>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous">
    </script>

    <div class="container">
        <p><c:if test="${not empty screenings}"><fmt:message key="main.schedule.message"/></c:if></p>
        <p><c:if test="${empty screenings}"><fmt:message key="main.schedule.message.without"/></c:if></p>
            <table class="table table-striped">
                <tbody>
                <c:forEach var="screening" items="${screenings}">
                    <tr>
                        <td>${screening.film.title}</td>
                        <td>${screening.filmDate}</td>
                        <td>${screening.timeBegin}</td>
                        <td>${screening.state}</td>
                        <td>
                            <form action="controller" method="POST">
                            <input type="hidden" name="action" value="freeSeats"/>
                            <input type="hidden" name="screeningId" value=${screening.id} />
                            <button type="submit" class="btn btn-success">
                                <fmt:message key="main.schedule.button"/>
                             </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
    </div>
</body>
</html>
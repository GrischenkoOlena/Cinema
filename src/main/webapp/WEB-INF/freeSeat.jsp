<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<html lang="en">
  <c:set var="title" value="Cinema" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-50 p-3">
    <jsp:include page="/WEB-INF/templates/menu_main.jsp"></jsp:include>

    <h2> <c:out value = "${screening.film.title}, ${screening.filmDate}, ${screening.timeBegin}" /> </h2>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous">
    </script>

    <div class="container">
        <p><fmt:message key="freeSeat.header"/></p>
        <div class="container text-center">
            <c:forEach var="row" items="${seats}" varStatus="theCount">
                <div class="row align-items-center">
                    <fmt:message key="freeSeat.row"/> ${theCount.index + 1}
                    <c:forEach var="place" items="${row}">
                        <div class="col-sm-1 p-3 border">
                            <tags:viewSeat placeAttr="${place}"/>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
        </div>
        <div class="col-sm-2 mb-2 bg-danger border text-white"> <fmt:message key="main.button.schedule"/>sold</div>
        <p><fmt:message key="freeSeat.price"/>
            <c:forEach var="category" items="${categories}">
                <c:choose>
                    <c:when test="${category.category == 'premium'}">
                        <p class="col-sm-1 mb-2 bg-primary border bg-gradient text-white"> ${category.price} </p>
                    </c:when>
                    <c:when test="${category.category == 'classic'}">
                        <p class="col-sm-1 mb-2 bg-info border bg-gradient text-white"> ${category.price} </p>
                    </c:when>
                </c:choose>
            </c:forEach>
        </p>
        <div> <p class="text-danger"> ${errorUnregister} </p></div>
    </div>
</body>
</html>
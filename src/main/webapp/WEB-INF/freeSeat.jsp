<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Cinema" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-50 p-3">
    <h2> <c:out value = "${screening.film}, ${screening.filmDate}, ${screening.timeBegin}" /> </h2>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous">
    </script>

    <div class="container">
        <p>Free places </p>
        <div class="container text-center">
            <c:forEach var="row" items="${seats}" varStatus="theCount">
                <div class="row align-items-center">
                    row ${theCount.index + 1}
                    <c:forEach var="place" items="${row}">
                        <div class="col-sm-1 p-3 border">
                            <c:choose>
                                <c:when test="${place.state.id == 1}">
                                    <c:choose>
                                        <c:when test="${place.category.category == 'premium'}">
                                            <p class="p-3 mb-2 bg-primary bg-gradient text-white"> ${place.place} </p>
                                        </c:when>
                                        <c:when test="${place.category.category == 'classic'}">
                                            <p class="p-3 mb-2 bg-info bg-gradient text-white"> ${place.place} </p>
                                        </c:when>
                                    </c:choose>
                                </c:when>
                                <c:when test="${place.state.id == 2}">
                                    <p class="p-3 mb-2 bg-danger bg-gradient text-white"> ${place.place} </p>
                                </c:when>
                            </c:choose>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
        </div>
        <div class="col-sm-2 mb-2 bg-danger border text-white"> sold</div>
        <p>Price 
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
    </div>
</body>
</html>
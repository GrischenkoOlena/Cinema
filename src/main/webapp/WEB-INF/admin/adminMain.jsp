<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Administrator" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>
  <body class="w-50 p-3">
    <jsp:include page="/WEB-INF/templates/menu_admin.jsp"></jsp:include>

        <div class="container">
            <table class="table table-striped">
                <tbody>
                <c:forEach var="screening" items="${screenings}">
                    <tr>
                        <td>${screening.film}</td>
                        <td>${screening.filmDate}</td>
                        <td>${screening.timeBegin}</td>
                        <td>${screening.state}</td>
                        <td>
                            <form action="controller" method="POST">
                            <input type="hidden" name="action" value="updateScreening"/>
                            <input type="hidden" name="screening" value=${screening.id} />
                            <input type="submit" class="btn btn-success" value="Update"/>
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
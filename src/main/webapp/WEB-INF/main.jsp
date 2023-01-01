<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
  <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title> Schedule </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  </head>

  <body class="w-50 p-3">
    <h2>Schedule</h2>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous">
    </script>

    <div class="container">
        <p>Choose film to view free place </p>
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
                            <input type="hidden" name="action" value="freeSeats"/>
                            <input type="hidden" name="screening" value=${screening.id} />
                            <input type="submit" class="btn btn-success" value="View"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
    </div>
</body>
</html>
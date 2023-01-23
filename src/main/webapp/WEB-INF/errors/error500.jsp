<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/scripts.jsp" %>

<html>
    <c:set var="title" value="Error" scope="session"/>
    <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>
<body>
<div class="page-container-responsive">
    <div class="row space-top-8 space-8">
        <div class="col-md-5 col-middle">
            <h1 class="text-jumbo text-ginormous hide-sm">Ooops!</h1>
            <h2>Схоже, трапилась якась помилка.</h2>
            <h6>Поверниться на <a href="index.jsp">Головну</a></h6>
        </div>
        <div>
            Your problem is
            <p class="text-danger"> ${error} </p>
        </div>
        <div class="col-md-5 col-middle text-center">
            <img src="static/error500.png" width="308" height="311" class="hide-sm" alt="Помилка">
        </div>
    </div>
</div>
</body>
</html>
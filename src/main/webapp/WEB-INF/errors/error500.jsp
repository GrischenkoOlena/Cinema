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
            You have problem with
            <p class="p-3 mb-2 bg-danger bg-gradient text-white"> ${error} </p>
        </div>
        <div class="col-md-5 col-middle text-center">
            <img src="https://a0.muscache.com/airbnb/static/error_pages/404-Airbnb_final-d652ff855b1335dd3eedc3baa8dc8b69.gif"
            width="313" height="428" class="hide-sm" alt="Девочка уронила свое мороженое.">
        </div>
    </div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/templates/taglib.jspf" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

  <div class="container">
  <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start border-bottom">
    <a class="navbar-brand" href="index.jsp">
      <img src="https://getbootstrap.com/docs/4.6/assets/brand/bootstrap-solid.svg" width="30" height="30" class="d-inline-block align-top" alt="">
        Cinema
    </a>

      <ul class="nav col-12 col-lg-auto me-lg-auto mb-5 justify-content-center mb-md-0">
        <li class="nav-item">
           <a class="nav-link active" aria-current="page" href="controller?action=schedule">
            <fmt:message key="user.menu.schedule"/>
           </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="controller?action=tickets"> <fmt:message key="user.menu.tickets"/></a>
        </li>
        <li class="nav-item">
           <a class="nav-link" href="controller?action=profile"> <fmt:message key="user.menu.profile"/></a>
        </li>
      </ul>

      <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
            <c:if test="${not empty sessionScope.lang}"> ${sessionScope.lang}</c:if>
            <c:if test="${empty sessionScope.lang}">en</c:if>
        </button>
        <div class="dropdown-menu">
           <form class="form-inline" method="post" action="?${pageContext.request.queryString}">
              <button type="submit" name="sessionLocale" class="dropdown-item" value="en">en</button>
              <button type="submit" name="sessionLocale" class="dropdown-item" value="uk">uk</button>
           </form>
        </div>
      </div>

      <form class="form-inline my-2 my-lg-0 text-end" method="post" action="controller?action=logout">
        <button class="btn btn-outline-success" type="submit"><fmt:message key="button.logout"/></button>
      </form>
  </div>
  <div class="col-md-auto text-end text-info"> <ctg:hello role="${user.name}"/></div>
  </div>
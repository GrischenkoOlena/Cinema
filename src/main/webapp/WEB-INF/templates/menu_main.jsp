<%@ include file="/WEB-INF/templates/taglib.jspf" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

  <div class="container">
    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between border-bottom">
      <a class="navbar-brand" href="index.jsp">
        <img src="https://getbootstrap.com/docs/4.6/assets/brand/bootstrap-solid.svg" width="30" height="30" class="d-inline-block align-top" alt="">
            Cinema
      </a>

      <ul class="nav col-12 col-lg-auto me-lg-auto mb-5 justify-content-center mb-md-0">
        <c:if test = "${not empty user}">
            <c:if test="${userRole.id == 1}">
              <c:set var="hrefAccount" value="controller?action=screenings"/>
            </c:if>
            <c:if test="${userRole.id == 2}">
              <c:set var="hrefAccount" value="controller?action=schedule"/>
            </c:if>
            <li><a href="${hrefAccount}" class="nav-link"><fmt:message key="main.menu.account"/></a></li>
        </c:if>
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

      <form class="form-inline my-2 my-lg-0 text-end" name="header" action="controller" method="GET">
            <c:choose>
              <c:when test="${not empty user}">
                <c:set var="actionName" value="logout"/>
                <button type="submit" class="btn btn-outline-primary me-2">
                   <fmt:message key="button.logout"/>
                </button>
              </c:when>
              <c:otherwise>
                <c:set var="actionName" value="enter"/>
                <button type="submit" name="page" value="login" class="btn btn-outline-primary me-2">
                    <fmt:message key="button.login"/>
                </button>
                <button type="submit" name="page" value="signUp" class="btn btn-outline-warning">
                  <fmt:message key="button.signup"/>
                </button>
              </c:otherwise>
            </c:choose>
          <input type="hidden" name="action" value="${actionName}"/>
      </form>
    </div>

    <div class="col-md-auto text-end text-info"> <ctg:hello role="${user.name}"/></div>
  </div>
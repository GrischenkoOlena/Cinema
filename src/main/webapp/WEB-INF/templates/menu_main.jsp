<%@ include file="/WEB-INF/templates/taglib.jspf" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
      <a class="navbar-brand" href="index.jsp">
        <img src="https://getbootstrap.com/docs/4.6/assets/brand/bootstrap-solid.svg" width="30" height="30" class="d-inline-block align-top" alt="">
            Cinema
      </a>

      <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><a href="#" class="nav-link px-2 link-secondary"><fmt:message key="main.menu.home"/></a></li>
        <li><a href="#" class="nav-link px-2 link-dark"><fmt:message key="main.menu.pricing"/></a></li>
        <li><a href="#" class="nav-link px-2 link-dark"><fmt:message key="main.menu.about"/></a></li>
        <c:if test = "${not empty user}">
            <c:if test="${userRole.id == 1}">
              <c:set var="hrefAccount" value="controller?action=screenings"/>
            </c:if>
            <c:if test="${userRole.id == 2}">
              <c:set var="hrefAccount" value="controller?action=schedule"/>
            </c:if>
            <li><a href="${hrefAccount}" class="nav-link px-2 link-dark"><fmt:message key="main.menu.account"/></a></li>
        </c:if>
      </ul>

      <div class="justify-content-end">
        <form class="form-inline col-md-auto mb-2 justify-content-end" method="post" action="controller?action=i18n">
          <select class="form-select" name="language" aria-label="Default select example">
             <option value="en">
                <a href="?sessionLocale=en">en</a>
             </option>
             <option value="ua">
                <a href="?sessionLocale=uk">uk</a>
             </option>
          </select>
        </form>
      </div>

      <div class="col-md-auto text-end">
        <form name="header" action="controller" method="POST">
          <c:set var="actionName" value="enter"/>
          <button type="submit" name="page" value="login" class="btn btn-outline-primary me-2">
            <c:choose>
              <c:when test="${not empty user}">
                <c:set var="actionName" value="logout"/>
                <fmt:message key="button.logout"/>
              </c:when>
              <c:otherwise>
                <fmt:message key="button.login"/>
              </c:otherwise>
            </c:choose>
          </button>
          <button type="submit" name="page" value="signUp" class="btn btn-outline-warning">
            <fmt:message key="button.signup"/>
          </button>
          <input type="hidden" name="action" value="${actionName}"/>
        </form>
      </div>

      <div class="col-md-auto text-end text-info"> <ctg:hello role="${user.name}"/></div>
    </header>
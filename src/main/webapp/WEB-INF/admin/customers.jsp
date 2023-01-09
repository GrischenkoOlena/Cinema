<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Administrator" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-50 p-3">
    <jsp:include page="/WEB-INF/templates/menu_admin.jsp"></jsp:include>

    <br>
    <h2>Customers</h2>
    <div class="container">
              <form class="row g-3" action="controller" method="POST">
                <div class="col-auto">
                  <select class="form-select" name="order">
                    <option selected>Sort by</option>
                    <option value="nameAsc">name &#8593;</option>
                    <option value="nameDesc">name &#8595;</option>
                    <option value="balanceAsc">balance &#8593;</option>
                    <option value="balanceDesc">balance &#8595;</option>
                  </select>
                </div>

                <div class="col-auto">
                  <input type="hidden" name="action" value="customers"/>
                  <input type="submit" class="btn btn-success" value="Apply"/>
                </div>

                <nav>
                  <ul class="pagination pagination-sm justify-content-center">
                    <c:forEach var = "i" begin = "1" end = "${countPages}">
                      <c:set var="hrefPage" value="controller?action=customers&page=${i}" />
                      <li class="page-item"><a class="page-link" href="${hrefPage}">${i}</a></li>
                    </c:forEach>
                  </ul>
                </nav>
              </form>

              <table class="table">
                <thead>
                  <tr>
                    <th scope="col">name</th>
                    <th scope="col">login</th>
                    <th scope="col">balance</th>
                    <th scope="col">role</th>
                  </tr>
                </thead>
                  <tbody>
                  <c:forEach var="user" items="${users}">
                      <tr>
                          <td>${user.name}</td>
                          <td>${user.login}</td>
                          <td>${user.balance}</td>
                          <td>${user.role}</td>
                          <td>
                              <form action="controller" method="POST">
                              <input type="hidden" name="action" value="changeBalance"/>
                              <input type="hidden" name="userLogin" value=${user.login}/>
                              <input type="hidden" name="userBalance" value=${user.balance}/>
                              <input type="submit" class="btn btn-success" value="Change balance"/>
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
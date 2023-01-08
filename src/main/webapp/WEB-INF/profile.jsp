<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Profile" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-50 p-3">
  <div class="container">

    <c:if test="${userRole.id == 1}">
        <jsp:include page="/WEB-INF/templates/menu_admin.jsp"></jsp:include>
    </c:if>
    <c:if test="${userRole.id == 2}">
        <jsp:include page="/WEB-INF/templates/menu_user.jsp"></jsp:include>
    </c:if>

    <h2>Your profile</h2>
    <table class="w-50 table table-primary table-sm">
      <tbody>
        <tr>
          <th scope="row" class="col-md-3">Login</th>
          <td class="col-md-6">${user.login}</td>
          <td>
            <form action="controller" method="POST">
               <input type="hidden" name="action" value="updateEntity"/>
               <input type="hidden" name="updateEntity" value="user"/>
               <input type="hidden" name="updateParam" value="login"/>
               <input type="submit" class="btn btn-success" value="Update"/>
            </form>
          </td>
        </tr>
        <tr>
          <th scope="row" class="col-md-3">Name</th>
          <td class="col-md-6">${user.name}</td>
          <td>
            <form action="controller" method="POST">
               <input type="hidden" name="action" value="updateEntity"/>
               <input type="hidden" name="updateEntity" value="user"/>
               <input type="hidden" name="updateParam" value="name"/>
               <input type="submit" class="btn btn-success" value="Update"/>
            </form>
          </td>
        </tr>
        <tr>
          <th scope="row" class="col-md-3">Balance</th>
          <td class="col-md-6">${user.balance}</td>
          <td>
            <form action="controller" method="POST">
               <input type="hidden" name="action" value="updateEntity"/>
               <input type="hidden" name="updateEntity" value="user"/>
               <input type="hidden" name="updateParam" value="balance"/>
               <input type="submit" class="btn btn-success" value="Update"/>
            </form>
          </td>
        </tr>
        <tr>
          <th scope="row" class="col-md-3">Role</th>
          <td class="col-md-6">${user.role}</td>
          <td>
          </td>
        </tr>
      </tbody>
    </table>
</div>
    <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>

  </body>

</html>
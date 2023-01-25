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

    <!-- Modal -->
    <div class="modal fade" id="editUserAttribute" aria-hidden="true"
          aria-labelledby="editUserModalLabel" tabindex="-1" role="dialog">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="editUserModalLabel"><fmt:message key="profile.update.header"/></h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>

          <form action="controller?action=updateProfile" method="POST">
            <div class="modal-body">
              <label for="updateName" class="form-label"><fmt:message key="profile.update.name"/></label>
              <input class="form-control" type="text" id="updateName" name="updateName">
              <label for="updateBalance" class="form-label"><fmt:message key="profile.update.balance"/></label>
              <input class="form-control" type="text" id="updateBalance" name="updateBalance">
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="submit" name="btnChangeUser" class="btn btn-dark">
                <fmt:message key="profile.update.button"/>
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <br>
    <h2><fmt:message key="profile.header"/></h2>
    <table class="w-50 table table-primary table-sm">
      <tbody>
        <tr>
          <th scope="row" class="col-md-3"><fmt:message key="profile.login"/></th>
          <td class="col-md-6">${user.login}</td>
        </tr>
        <tr>
          <th scope="row" class="col-md-3"><fmt:message key="profile.name"/></th>
          <td class="col-md-6">${user.name}</td>
        </tr>
        <tr>
          <th scope="row" class="col-md-3"><fmt:message key="profile.balance"/></th>
          <td class="col-md-6">${user.balance}</td>
        </tr>
        <tr>
          <th scope="row" class="col-md-3"><fmt:message key="profile.role"/></th>
          <td class="col-md-6">${user.role}</td>
        </tr>
      </tbody>
    </table>
    <button type="button" class="btn btn-info text-white" data-bs-toggle="modal"
            data-bs-target="#editUserAttribute"
            data-bs-updateName="${user.name}" data-bs-updateBalance="${user.balance}">
       <fmt:message key="profile.button"/>
    </button>
  </div>

  <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>

  <script>
    const exampleModal = document.getElementById('editUserAttribute');
    exampleModal.addEventListener('show.bs.modal', event => {
        const button = event.relatedTarget;
        const updateName = button.getAttribute('data-bs-updateName');
        const updateBalance = button.getAttribute('data-bs-updateBalance');
        document.getElementById('updateName').value = updateName;
        document.getElementById('updateBalance').value = updateBalance;
    })
  </script>

  </body>

</html>
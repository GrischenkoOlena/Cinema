<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Administrator" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-75 p-3">
    <jsp:include page="/WEB-INF/templates/menu_admin.jsp"></jsp:include>

        <!-- Modal -->
        <div class="modal fade" id="updateBalanceForm" aria-hidden="true"
              aria-labelledby="editUserModalLabel" tabindex="-1" role="dialog">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-header">
                <h1 class="modal-title fs-5" id="editUserModalLabel">Your change</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>

              <form action="controller?action=updateBalance" method="POST">
                <div class="modal-body">
                  <label for="paramInput" class="form-label">Balance</label>
                  <input class="form-control" type="text" id="paramInput" name="updateValue">
                  <input type="hidden" id="userLogin" name="userLogin">
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                  <button type="submit" name="btnChangeUser" class="btn btn-dark">Save change</button>
                </div>
              </form>
            </div>
          </div>
        </div>

    <br>
    <h2>Customers</h2>
    <div class="container">
      <form class="row g-3" action="controller" method="POST">
        <div class="col-auto">
          <div class="input-group mb-3">
          <c:set var="selectedOrder" value="${sessionScope.orderCustomers}" />
            <label class="input-group-text" for="inputOrder">Sort by</label>
            <select class="form-select" id="inputOrder" name="order">
              <option selected style="display:none;"></option>
              <option value="nameAsc" <c:if test="${selectedOrder == 'nameAsc'}"> selected </c:if>>
                name &#8593;</option>
              <option value="nameDesc" <c:if test="${selectedOrder == 'nameDesc'}"> selected </c:if>>
                name &#8595;</option>
              <option value="balanceAsc" <c:if test="${selectedOrder == 'balanceAsc'}"> selected </c:if>>
                balance &#8593;</option>
              <option value="balanceDesc" <c:if test="${selectedOrder == 'balanceDesc'}"> selected </c:if>>
                balance &#8595;</option>
            </select>
            <button class="btn btn-outline-secondary" type="submit" name="btnApplySort">Apply</button>
          </div>
        </div>
        <input type="hidden" name="action" value="customers"/>
      </form>

      <nav>
        <ul class="pagination pagination-sm justify-content-center">
          <c:forEach var = "i" begin = "1" end = "${countPages}">
            <c:set var="hrefPage" value="controller?action=customers&page=${i}" />
            <li class="page-item"><a class="page-link" href="${hrefPage}">${i}</a></li>
          </c:forEach>
        </ul>
      </nav>

              <table class="table">
                <thead>
                  <tr>
                    <th scope="col">name</th>
                    <th scope="col">login</th>
                    <th scope="col">balance</th>
                    <th scope="col">role</th>
                    <th> </th>
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
                              <button type="button" class="btn btn-info text-white" data-bs-toggle="modal"
                                          data-bs-target="#updateBalanceForm"
                                          data-bs-updateBalance="${user.balance}"
                                          data-bs-updateLogin="${user.login}">
                                   Change balance
                              </button>
                          </td>
                      </tr>
                  </c:forEach>
                  </tbody>
              </table>
            </div>

    <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>

  <script>
    const exampleModal = document.getElementById('updateBalanceForm');
    exampleModal.addEventListener('show.bs.modal', event => {
        const button = event.relatedTarget;
        const updateBalance = button.getAttribute('data-bs-updateBalance');
        const updateLogin = button.getAttribute('data-bs-updateLogin');
        document.getElementById('paramInput').value = updateBalance;
        document.getElementById('userLogin').value = updateLogin;
    })
  </script>

  </body>

</html>
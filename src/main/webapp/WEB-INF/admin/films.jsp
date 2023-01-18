<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ include file="/WEB-INF/templates/taglib.jspf" %>

<html lang="en">
  <c:set var="title" value="Administrator" scope="session"/>
  <jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>

  <body class="w-75 p-3">
  <jsp:include page="/WEB-INF/templates/menu_admin.jsp"></jsp:include>

  <!-- Modal add new film -->
  <div class="modal fade" id="addNewFilmForm" aria-hidden="true"
              aria-labelledby="addNewFilmLabel" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="addNewFilmLabel">New movie</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>

        <form action="controller?action=addFilm" method="POST">
          <div class="modal-body">
            <div class="mb-3">
              <label for="title" class="form-label">Title</label>
              <input type="input" id="title" name="title" class="form-control" required>
            </div>
            <div class="mb-3">
              <label for="director" class="form-label">Director</label>
              <input type="input" id="director" name="director" class="form-control">
            </div>
            <div class="mb-3">
              <label for="cast" class="form-label">Cast</label>
              <input type="input" id="cast" name="cast" class="form-control">
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <input type="input" id="description" name="description" class="form-control">
            </div>
            <div class="mb-3">
                <label for="genre" class="form-label">Genre</label>
                <select class="form-select" id="genre" name="genre">
                  <option selected style="display:none;"></option>
                  <c:forEach var="genre" items="${genres}">
                    <option value="${genre.id}"><a class="dropdown-item" href="#">${genre.genre}</a></option>
                  </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="duration" class="form-label">Duration</label>
                <input type="input" id="duration" name="duration" class="form-control">
            </div>
          </div>

          <div class="modal-footer">
            <button type="button" name="btnClose" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="submit" name="btnAddFilm" class="btn btn-dark">Add new movie</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <!-- Modal update film -->
  <div class="modal fade" id="updateFilmForm" aria-hidden="true"
              aria-labelledby="updateFilmLabel" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="updateFilmLabel">Update movie</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>

        <form action="controller?action=updateFilm" method="POST">
          <div class="modal-body">
            <div class="mb-3">
               <label for="titleUpdate" class="form-label">Director</label>
               <input type="input" id="titleUpdate" name="title" class="form-control">
            </div>
            <div class="mb-3">
              <label for="directorUpdate" class="form-label">Director</label>
              <input type="input" id="directorUpdate" name="director" class="form-control">
            </div>
            <div class="mb-3">
              <label for="castUpdate" class="form-label">Cast</label>
              <input type="input" id="castUpdate" name="cast" class="form-control">
            </div>
            <div class="mb-3">
                <label for="descriptionUpdate" class="form-label">Description</label>
                <input type="input" id="descriptionUpdate" name="description" class="form-control">
            </div>
            <div class="mb-3">
                <label for="durationUpdate" class="form-label">Duration</label>
                <input type="input" id="durationUpdate" name="duration" class="form-control">
            </div>
            <input type="hidden" id="hiddenFilmId" name="filmId">
          </div>

          <div class="modal-footer">
            <button type="button" name="btnClose" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="submit" name="btnUpdateFilm" class="btn btn-dark">Update movie</button>
          </div>
        </form>
      </div>
    </div>
  </div>

    <br>
    <h2>Movies</h2>
        <div class="container">
          <form class="row g-3" action="controller" method="POST">
            <div class="col-auto">
              <div class="input-group mb-3">
                <c:set var="selectedOrder" value="${sessionScope.orderFilms}" />
                <label class="input-group-text" for="inputOrder">Sort by</label>
                <select class="form-select" id="inputOrder" name="order">
                  <option selected style="display:none;"></option>
                  <option value="titleAsc" <c:if test="${selectedOrder == 'titleAsc'}"> selected </c:if>>
                    name &#8593;</option>
                  <option value="titleDesc" <c:if test="${selectedOrder == 'titleDesc'}"> selected </c:if>>
                    name &#8595;</option>
                  <option value="durationAsc" <c:if test="${selectedOrder == 'durationAsc'}"> selected </c:if>>
                   duration &#8593;</option>
                  <option value="durationDesc"<c:if test="${selectedOrder == 'durationDesc'}"> selected </c:if>>
                    duration &#8595;</option>
                </select>
                <button class="btn btn-outline-secondary" type="submit" name="btnApplySort">Apply</button>
              </div>
            </div>

            <div class="col-auto">
              <div class="input-group mb-3">
                <label class="input-group-text" for="inputGenre">Genre</label>
                <c:set var="selectedFilter" value="${sessionScope.genreFilter}" />
                <select class="form-select" id="inputGenre" name="filter">
                  <option selected></option>
                  <c:forEach var="genre" items="${genres}">
                    <option value="${genre.id}" <c:if test="${selectedFilter == genre.id}"> selected </c:if>>
                        ${genre.genre}</option>
                  </c:forEach>
                </select> 
                <button class="btn btn-outline-secondary" type="submit" name="btnFilter">Filter</button>
              </div>
            </div>
            <input type="hidden" name="action" value="films">
          </form>

          <nav aria-label="Page navigation">
            <ul class="pagination pagination-sm justify-content-center">
              <c:forEach var = "i" begin = "1" end = "${countPages}">
                <c:set var="hrefPage" value="controller?action=films&page=${i}" />
                <li class="page-item"><a class="page-link" href="${hrefPage}">${i}</a></li>
              </c:forEach>
            </ul>
          </nav>
          
          <table class="table">
            <thead>
              <tr>
                <th scope="col">title</th>
                <th scope="col">director</th>
                <th scope="col">cast</th>
                <th scope="col">description</th>
                <th scope="col">genre</th>
                <th scope="col">duration</th>
                <th> </th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="film" items="${films}">
                <tr>
                  <td>${film.title}</td>
                  <td>${film.director}</td>
                  <td>${film.cast}</td>
                  <td>${film.description}</td>
                  <td>${film.genre.genre}</td>
                  <td>${film.duration}</td>
                  <td>
                     <button type="button" class="btn btn-info text-white" data-bs-toggle="modal"
                                          data-bs-target="#updateFilmForm"
                                          data-bs-updateFilmId="${film.id}"
                                          data-bs-titleUpdate="${film.title}"
                                          data-bs-directorUpdate="${film.director}"
                                          data-bs-castUpdate="${film.cast}"
                                          data-bs-descriptionUpdate="${film.description}"
                                          data-bs-durationUpdate="${film.duration}">
                         Update movie
                     </button>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <br>
          <button type="button" class="btn btn-success" data-bs-toggle="modal"
                    data-bs-target="#addNewFilmForm">
            Add new movie
          </button>
        </div>

    <jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>

   <script>
     const exampleModal = document.getElementById('updateFilmForm');
     exampleModal.addEventListener('show.bs.modal', event => {
         const button = event.relatedTarget;
         document.getElementById('hiddenFilmId').value = button.getAttribute('data-bs-updateFilmId');
         document.getElementById('titleUpdate').value = button.getAttribute('data-bs-titleUpdate');
         document.getElementById('directorUpdate').value = button.getAttribute('data-bs-directorUpdate');
         document.getElementById('castUpdate').value = button.getAttribute('data-bs-castUpdate');
         document.getElementById('descriptionUpdate').value = button.getAttribute('data-bs-descriptionUpdate');
         document.getElementById('durationUpdate').value = button.getAttribute('data-bs-durationUpdate');
     });     
   </script>

  </body>

</html>
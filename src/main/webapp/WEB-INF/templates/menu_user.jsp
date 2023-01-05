<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

  <div class="container">
  <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start border-bottom">
    <a class="navbar-brand" href="#">
      <img src="https://getbootstrap.com/docs/4.6/assets/brand/bootstrap-solid.svg" width="30" height="30" class="d-inline-block align-top" alt="">
        Cinema
    </a>

      <ul class="nav col-12 col-lg-auto me-lg-auto mb-5 justify-content-center mb-md-0">
        <li class="nav-item">
          <a class="nav-link" href="index.jsp">Home</a>
        </li>
        <li class="nav-item">
           <a class="nav-link active" aria-current="page" href="main.jsp">Schedule</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="controller?action=tickets">Tickets</a>
        </li>
      </ul>

      <form class="form-inline col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" method="post" action="controller?action=i18n">
         <select class="form-select" name="language" aria-label="Default select example">
            <option value="en" selected>en</option>
            <option value="ua">ua</option>
         </select>
      </form>

      <form class="form-inline my-2 my-lg-0 text-end" method="post" action="controller?action=logout">
        <button class="btn btn-outline-success" type="submit">Log out</button>
      </form>
  </div>
  </div>
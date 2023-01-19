<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
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
          <a class="nav-link active" aria-current="page" href="controller?action=screenings">Sessions</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="controller?action=films">Movies</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="controller?action=customers">Customers</a>
        </li>
        <li class="nav-item">
           <a class="nav-link" href="controller?action=attendance">Attendance</a>
        </li>
        <li class="nav-item">
           <a class="nav-link" href="controller?action=profile">Profile</a>
        </li>
      </ul>

      <form class="form-inline col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" method="post" action="controller?action=i18n">
         <select class="form-select" name="language" aria-label="Default select example">
            <option value="en" selected>en</option>
            <option value="uk">uk</option>
         </select>
      </form>

      <form class="form-inline my-2 my-lg-0 text-end" method="post" action="controller?action=logout">
        <button class="btn btn-outline-success" type="submit">Log out</button>
      </form>
  </div>
  <div class="col-md-auto text-end text-info"> <ctg:hello role="${user.name}"/></div>
  </div>
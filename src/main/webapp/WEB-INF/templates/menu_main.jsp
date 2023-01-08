    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
      <a class="navbar-brand" href="index.jsp">
        <img src="https://getbootstrap.com/docs/4.6/assets/brand/bootstrap-solid.svg" width="30" height="30" class="d-inline-block align-top" alt="">
            Cinema
      </a>

      <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><a href="#" class="nav-link px-2 link-secondary">Home</a></li>
        <li><a href="#" class="nav-link px-2 link-dark">Pricing</a></li>
        <li><a href="#" class="nav-link px-2 link-dark">FAQs</a></li>
        <li><a href="#" class="nav-link px-2 link-dark">About</a></li>
      </ul>

      <div class="justify-content-end">
        <form class="form-inline col-md-auto mb-2 justify-content-end" method="post" action="controller?action=i18n">
          <select class="form-select" name="language" aria-label="Default select example">
             <option value="en" selected>en</option>
              <option value="ua">ua</option>
          </select>
        </form>
      </div>

      <div class="col-md-auto text-end">
        <form name="header" action="controller" method="POST">
          <input type="hidden" name="action" value="enter"/>
          <button type="submit" name="page" value="login" class="btn btn-outline-primary me-2">Login</button>
          <button type="submit" name="page" value="signUp" class="btn btn-outline-warning">Sign-up</button>
        </form>
      </div>
    </header>
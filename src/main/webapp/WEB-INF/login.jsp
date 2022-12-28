<!doctype html>
<html lang="en">
  <jsp:include page="/WEB-INF/templates/header.jsp"></jsp:include>

  <body class="w-50 p-3">
    <h2>Login</h2>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous">
    </script>

    <div class="container">
      <form>
        <div class="mb-3">
          <label for="email" class="form-label">Email address</label>
          <input type="email" class="form-control" id="email" aria-describedby="emailHelp">
          <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div class="mb-3">
          <label for="inputPassword" class="form-label">Password</label>
          <input type="password" class="form-control" id="inputPassword">
        </div>
        <div class="mb-3 form-check">
          <input type="checkbox" class="form-check-input" id="checkMe">
          <label class="form-check-label" for="checkMe">Check me out</label>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>
     </div>
</body>
</html>
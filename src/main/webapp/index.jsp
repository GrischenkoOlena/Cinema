<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="en">
  <jsp:include page="/WEB-INF/templates/header.jsp"></jsp:include>

  <body class="w-50 p-3">
    <h2>Welcome to cinema</h2>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous">
    </script>
    
    <div class="container">

      <nav class="navbar navbar-expand-lg bg-light">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="#">In cinema</a>
          </div>
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" href="#"><p id="date"></p></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><p id="dateNext"></p></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><p id="dateNext2"></p></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><p id="dateNext3"></p></a>
            </li>
          </ul>
        </div>
      </nav>

      <form name="Simple" action="controller" method="POST">
      <input type="hidden" name="action" value="main"/>
      <input type="submit" class="btn btn-primary" value="Schedule"/>
      </form>
    </div>

    <script>
      let textDate = function(date){
        y = date.getFullYear();
        m = date.getMonth() + 1;
        d = date.getDate();
        return d + "/" + m + "/" + y;
      }

      Date.prototype.addDays = function(days){
        return new Date(this.valueOf() + 24*60*60*1000*days);
      }

      n =  new Date();
      document.getElementById("date").innerHTML = textDate(n);
      document.getElementById("dateNext").innerHTML = textDate(n.addDays(1));
      document.getElementById("dateNext2").innerHTML = textDate(n.addDays(2));
      document.getElementById("dateNext3").innerHTML = textDate(n.addDays(3));

    </script>

  </body>

</html>

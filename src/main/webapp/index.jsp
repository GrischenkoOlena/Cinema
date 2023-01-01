<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
    <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title> Cinema </title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    </head>

  <body class="w-50 p-3">
    <h2>Welcome to cinema</h2>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous">
    </script>
    
    <div class="container">

      <nav class="navbar navbar-expand-lg bg-light">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="#">In our cinema</a>
          </div>
        </div>
      </nav>

      <form name="Simple" action="controller" method="POST">
      <div class="btn-group" role="group" aria-label="Basic radio toggle button group" onclick = "sendRadioValue()">
        <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
        <label class="btn btn-outline-primary" for="btnradio1"><p id="date"></p></label>

        <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
        <label class="btn btn-outline-primary" for="btnradio2"><p id="dateNext"></p></label>

        <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off">
        <label class="btn btn-outline-primary" for="btnradio3"><p id="dateNext2"></p></label>

        <input type="radio" class="btn-check" name="btnradio" id="btnradio4" autocomplete="off">
        <label class="btn btn-outline-primary" for="btnradio4"><p id="dateNext3"></p></label>
      </div>
      <br><br>

      <input type="hidden" name="action" value="main"/>
      <input type="hidden" name="scheduleDate" id="sendDate" value=""/>
      <input type="submit" class="btn btn-success" value="Schedule"/>
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
      document.getElementById("btnradio1").setAttribute("value", textDate(n));

      document.getElementById("dateNext").innerHTML = textDate(n.addDays(1));
      document.getElementById("btnradio2").setAttribute("value", textDate(n.addDays(1)));

      document.getElementById("dateNext2").innerHTML = textDate(n.addDays(2));
      document.getElementById("btnradio3").setAttribute("value", textDate(n.addDays(2)));

      document.getElementById("dateNext3").innerHTML = textDate(n.addDays(3));
      document.getElementById("btnradio4").setAttribute("value", textDate(n.addDays(3)));

      document.getElementById("sendDate").setAttribute("value", textDate(n));

      function sendRadioValue(){
        var ele = document.getElementsByName("btnradio");
        for(i = 0; i < ele.length; i++){
            if (ele[i].checked){
              document.getElementById("sendDate").setAttribute("value", ele[i].value);
            }
        }
      }

    </script>

  </body>

</html>

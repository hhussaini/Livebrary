<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Good Looks Books</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/custom_css/stylesheet.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="js/bootstrap.js"></script>
</head>
<body>
    <mytags:customerNavbar/>
  <br><br><br>
  <div class="glb-page">
    <div class="container">
      <div id="mycarousel" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
          <div class="item active">
            <img src="assets/small-header-blur.png" alt="" class="img-responsive">
            <div class="small-carousel-text">
              <div class="boxed">
                <h1> <b>Good Looks Books <br> </b> </h1>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <br>
    <br>
    <div class="container">
      <h1> Welcome ${user.username} !<br></h1>
      <div class="col-sm-2"></div>
    </div>
  </div>
</div>
</body>
</html>

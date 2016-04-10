<%-- 
    Document   : signUp
    Author     : sonor
--%>

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
  <link rel="stylesheet" href="css/custom_css/bookPhotoGrid.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="js/bootstrap.js"></script>

</head>
<body>
    <mytags:guestNavbar/>
  <br><br><br>
  <div class="glb-page">
    <div class="container">
      <div id="mycarousel" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
          <div class="item active">
            <img src="assets/jumbo-header-blur.png" alt="" class="img-responsive">
            <div class="carousel-text">
              <div class="boxed">
                <h1> <b>Good Looks Books <br> </b> </h1>
              </div>
              <h3>For all of your literary needs!</h3>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      &nbsp;<br>
      &nbsp;<br>
      &nbsp;<br>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-sm-4">
          <div class="heading-box">
            Need a library card?
          </div>
          &nbsp;<br>
          <p class="center">Your favorite literature is right at your finger tips, only a library card is needed from your local library
          </p>
        </div>
        <div class="col-sm-4">
          <div class="heading-box">
            Catalog Access
          </div>
          &nbsp;<br>
          <p class="center">With or without and account you can  begin searching for a book, an author, or a genre</p>
          &nbsp;<br>
          &nbsp;<br>
    </div>
    <div class="col-sm-4">
      <div class="heading-box">
        Advertisement
      </div>
      &nbsp;<br>
      <p class="center">Sick and tired of no one buying your stuff?
        Advertise here!!
      </p>
    </div>
  </div>
</div>
</glb-page>
</body>
</html>

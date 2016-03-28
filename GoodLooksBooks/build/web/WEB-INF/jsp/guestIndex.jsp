<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Good Looks Books</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Latest compiled and minified CSS -->
  
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/custom_css/stylesheet.css">
  <link rel="stylesheet" href="css/custom_css/bookPhotoGrid.css">
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <!-- Latest compiled JavaScript -->
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  

</head>
<body>
  <nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <a href="guestIndex.jsp" class="pull-left">
          <img alt="brand" src="assets/brand-icon.png">
        </a>
      </div>
      <ul class="nav navbar-nav" style="font-weight: bold;">
        <li class="active"><a href="#">Home <span class="sr-only">(current)</span></a></li>
        <li><a href="about.jsp">About</a></li>
        <li><a href="fullCatalog.jsp">Full Catalog</a></li>
        <li><a href="help.jsp">Help</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right" style="font-weight: bold;">
        <li><a href="signUp.jsp">Sign Up</a></li>
        <li><a href="signIn.jsp">Sign In</a></li>
      </ul>
    </div>
  </nav>
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
            <a href="card-info.jsp">Find out more!</a>
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
          <form class="navbar-form navbar-left" role="search" action="fullCatalog.jsp">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Title, Author, Genre, etc...">
            </div>
            <button type="submit" class="btn btn-default">Search</button>
          </form>
          <!-- <form class="navbar-form navbar-left" role="search" action="fullCatalog.jsp">
          <div class="form-group">
          <input type="text" class="form-control" placeholder="Title, Author, Genre, etc...">
        </div>
        <button type="submit" class="btn btn-default">Search</button>
      </form> -->
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

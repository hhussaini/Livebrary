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
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="index.jsp" class="pull-left">
                    <img alt="brand" src="assets/brand-icon.png"></a>
                </div>
                <ul class="nav navbar-nav" style="font-weight: bold;">
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="userAbout.jsp">About</a></li>
                    <li><a href="fullCatalog.jsp">Full Catalog</a></li>
                    <li><a href="help.jsp">Help</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right" style="font-weight: bold;">
                    <li><a href="signUp.jsp">Sign Up</a></li>
                    <li class="active"><a href="#">Sign In<span class="sr-only">(current)</span></a></li>
                </ul>
            </div>
        </nav>
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
        <div class="container" styl="padding: 100px;">
            &nbsp;<br>
        </div>
        <div class="container">
            <div class="container" style="max-width: 75%">
                <hr class="fancy">
            </div>
            <div class="heading-box">
                Select the user type:
                <li><a href="userIndex.jsp">Customer (regular user)</a></li>
                <li><a href="adminIndex.jsp">Admin</a></li>
                <li><a href="librarianIndex.jsp">Librarian</a></li>
                <li><a href="publisherIndex.jsp">Publisher</a></li>
                <li><a href="index.jsp">Go as a guest (no account used)</a></li>
            </div>
        </div>
</div>
    </body>
    </html>

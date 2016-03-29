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
              <li class="active"><a href="#">Sign Up<span class="sr-only">(current)</span></a></li>
              <li><a href="signIn.jsp">Sign In</a></li>
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
    <div class="container">
        &nbsp;<br>
    </div>
    <div class="container">
      <div class="container" style="max-width: 75%">
          <hr class="fancy">
      </div>
      <form name="myForm" id = "signUpForm" class="form-horizontal signUpBody" action="" method="post">
        <input type="hidden" name="target" value="customer">
        <div class="form-group">
          <label class = "control-label regLabel" for="barcode">Barcode:</label>
          <div class = "controls regControl">
              <input type="text" class="form-control" name = "barcode">
          </div>
        </div>
        <div class="form-group">
          <label class = "control-label regLabel" for="password">Password:</label>
          <div class = "controls regControl">
              <input type="password" class="form-control" name = "password">
          </div>
        </div>
        <div class="form-group">
          <label class = "control-label regLabel" for="reenterPassword">Reenter Password:</label>
          <div class = "controls regControl">
              <input type="password" class="form-control" name = "reenterPassword">
          </div>
        </div>
        <input id="Button1" class="btn btn-info" type="submit" value="Register">
        <br>
    </div>
  </div>
</body>
</html>
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
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="publisherIndex.html" class="pull-left">
                    <img alt="brand" src="assets/brand-icon.png">
                </a>
            </div>
            <ul class="nav navbar-nav" style="font-weight: bold;">
                <li class="active"><a href="#">Publisher Home<span class="sr-only">(current)</span></a></li>
                <li><a href="publisherAbout.html">About</a></li>
                <li><a href="publisherAccount.html">Account</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right" style="font-weight: bold;">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Options<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="index.html">Sign Out</a></li>
                        <!-- <li role="separator" class="divider"></li> -->
                    </ul>
                </li>
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
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Publisher Options</h3>
            </div>
            <div class="panel-body">
                <div class="list-group">
                    <a href="editItems.html"><img alt="brand" width = "100" height = "100" src="assets/edit-books-icon.jpg"></a>
                    Edit Item Data
                </div>
            </div>
        </div>
    </div>
   </div>
</body>
</html>

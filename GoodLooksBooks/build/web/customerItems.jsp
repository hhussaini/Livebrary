<%-- 
    Document   : userItems
    Created on : Apr 12, 2016, 11:16:13 PM
    Author     : Kevin_Setayesh
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
  
   <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/custom_css/stylesheet.css">
  <link rel="stylesheet" href="css/custom_css/bookPhotoGrid.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="js/bootstrap.js"></script>
  <script src="js/userBookDescriptionJS.js" type="text/javascript"></script>

</head>
<body>
  <nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <a href="userIndex.html" class="pull-left">
          <img alt="brand" src="assets/brand-icon.png">
        </a>
      </div>
      <ul class="nav navbar-nav" style="font-weight: bold;">
        <li class="active"><a href="#">Home <span class="sr-only">(current)</span></a></li>
        <li><a href="userAbout.html">About</a></li>
        <li><a href="userFullCatalog.html">Full Catalog</a></li>
        <li><a href="userAccount.html">Account</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Options<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">My Items</a></li>
            <li><a href="#">My Wishlist</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="index.html">Sign Out</a></li>
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
          <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Your Owned Items </h3>
        </div>
        <div class="panel-body">
             
            
            <div class="col-xs-12 col-sm-9">
            <div class="row search-results"> 
                
            <table>
                 <tr>
               <c:forEach var="item" items="${userItemsList}" varStatus="status">
                   <c:if test="${status.index != 0 && status.index % 4 == 0}">
                                </tr>
                                <tr>
                            </c:if>
                   <td>
                    <div class="col-xs-6 col-md-3"> 
                        <td> 
                            <a href = "#" id = "${item.isbn}" class="thumbnail" onclick = "selectedBook(this.id)">
                            <img src = "${item.imageUrl}" alt="${item.title}">
                            <c:out value="${item.title}"/> 
                            </a>
                        </td>
                    </div>
                    </td> 
                   
              </c:forEach>
                 </tr>
            </table>
            </div>
             </div>
<!--          <div id="myCarousel" class="carousel slide" data-ride="carousel">
             Indicators 
            <ol class="carousel-indicators">
              <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
              <li data-target="#myCarousel" data-slide-to="1"></li>
              <li data-target="#myCarousel" data-slide-to="2"></li>
              <li data-target="#myCarousel" data-slide-to="3"></li>
            </ol>
             Wrapper for slides 
            <div class="carousel-inner" role="listbox">
               <div class="item active"> <div class="item active">
                <a href="userBookDescription.html">
                  <img src="https://www.gutenberg.org/cache/epub/43368/pg43368.cover.medium.jpg" alt="Book 2">
                  <div class="carousel-caption">
                    <h3>lol book</h3>
                    <p>decsription of stuff lol</p>
                  </div>
              </a>
              </div>
            

             <div class="item">
                <a href="userBookDescription.html">
                  <img src="https://www.gutenberg.org/cache/epub/43368/pg43368.cover.medium.jpg" alt="Book 2">
                    <div class="carousel-caption">
                      <h3>zyzz compilations</h3>
                      <p>pics of shreddedness</p>
                    </div>
                </a>
              </div>
 
              <div class="item">
                <a href="userBookDescription.html">
                  <img src="https://www.gutenberg.org/cache/epub/32200/pg32200.cover.medium.jpg" alt="Book 3">
                  <div class="carousel-caption">
                    <h3>more stuff</h3>
                    <p>Beatiful flowers in Kolymbari, Crete.</p>
                  </div>
                </a>
              </div>

              <div class="item">
                <a href="userBookDescription.html">
                  <img src="https://www.gutenberg.org/cache/epub/10723/pg10723.cover.medium.jpg" alt="Book 4">
                    <div class="carousel-caption">
                      <h3>gsdffsaasd</h3>
                      <p>decripsitonasdadfda</p>
                    </div>
                </a>
              </div>
            </div> 

             Left and right controls 
            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
              <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
              <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>-->
            
        </div>
      </div>
    </div>
  </div>
</body>
</html>

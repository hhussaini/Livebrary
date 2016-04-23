<%-- 
    Document   : customerFullCatalog
    Created on : Apr 11, 2016, 12:58:21 AM
    Author     : Kevin_Setayesh
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 
<html lang="en">

<head>
  <title>Full Catalog for Good Looks Books</title>
  <meta charset="utf-8">
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
                  <img alt="brand" src="assets/brand-icon.png"></a>
          </div>
          <ul class="nav navbar-nav" style="font-weight: bold;">
              <li><a href="userIndex.html">Home </a></li>
              <li><a href="userAbout.html">About</a></li>
              <li class="active"><a href="#">Full Catalog<span class="sr-only">(current)</span></a></li>
              <li><a href="userAccount.html">Account</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
             <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Options<span class="caret"></span></a>
                <ul class="dropdown-menu">
                   <li><a href="customerItems.jsp">My Items</a></li>
                   <li><a href="#">My Wishlist</a></li>
                   <li role="separator" class="divider"></li>
                   <li><a href="index.html">Sign Out</a></li>
                </ul>
             </li>
          </ul>
      </div>
  </nav>
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
    <div class="container">
      <div class="row row-offcanvas row-offcanvas-left">
          <!-- <form class="form" role="search">
            <div class="input-group">
              <input type="text" class="form-control" placeholder="Title, Author, Genre, etc..." name="srch-term" id="srch-term">
              <div class="input-group-btn">
                <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>
              </div>
            </div>
          </form> -->
        <!-- sidebar -->
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
          <div class="panel panel-default">
            <form class="form" name = "searchForm" id = "searchForm" action = "SearchForItemServlet" method = "post" role="search">
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Title, Author, Genre, etc..." name="srch-term" id="srch-term">
                <div class="input-group-btn">
                  <button class="btn btn-default"type="submit"><span class="glyphicon glyphicon-search"></span></button>
                </div>
              </div>
            </form>
            <div class="panel-heading">Active Filters</div> <br>
            <div class="panel-heading">Literature Medium</div>
            <div class="panel-body">
              <div class="form-group">
                <div class="checkbox-inline">
                  <label>
                    <input name="book" type="checkbox">Book</a>
                  </label>
                </div> <br>
                <div class="checkbox-inline">
                  <label>
                    <input name="magazine" type="checkbox">Magazine</a>
                  </label>
                </div> <br>
                <div class="checkbox-inline">
                  <label>
                    <input name="audio-book" type="checkbox">Audio Books</a>
                  </label>
                </div>
              </div>
            </div>
            <div class="panel-heading">Category</div>
            <div class="panel-body">
              <div class="form-group">
                <div class="checkbox-inline">
                  <label>
                    <input name="book" type="checkbox">Horror</a>
                  </label>
                </div> <br>
                <div class="checkbox-inline">
                  <label>
                    <input name="magazine" type="checkbox">Mystery</a>
                  </label>
                </div> <br>
                <div class="checkbox-inline">
                  <label>
                    <input name="audio-book" type="checkbox">War Novel</a>
                  </label>
                </div>
              </div>
            </div>
            <div class="panel-heading">Rating</div> <br>
            <div class="panel-heading">Publish Date</div>
            <div class="panel-body">
              <div class="form-group">
                <div class="checkbox-inline">
                  <label>
                    <input name="book" type="checkbox">Ancient Mythology</a>
                  </label>
                </div> <br>
                <div class="checkbox-inline">
                  <label>
                    <input name="magazine" type="checkbox">Renaissance Literature</a>
                  </label>
                </div> <br>
                <div class="checkbox-inline">
                  <label>
                    <input name="audio-book" type="checkbox">19th Century</a>
                  </label>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- main area -->
        <br><br><br>
          <div class="col-xs-12 col-sm-9">
            <div class="row search-results">
<!--              <div class="col-xs-6 col-md-3">
                  <a href="userBookDescription.jsp" class="thumbnail">
                      <img src="https://www.gutenberg.org/cache/epub/32101/pg32101.cover.medium.jpg" alt="Product Name">
                  </a>
                </a>
              </div>--> 
                
            <table>
                 <tr>
               <c:forEach var="item" items="${itemsList}" varStatus="status">
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
 
                    
               
         <div class = "col-xs-6 col-md-3">
                <a href="#" class="thumbnail">
                  <img src="//placehold.it/200x200" alt="...">
                </a>
              </div>
              <div class="col-xs-6 col-md-3">
                <a href="#" class="thumbnail">
                  <img src="//placehold.it/200x200" alt="...">
                </a>
              </div>
              <div class="col-xs-6 col-md-3">
                <a href="#" class="thumbnail">
                  <img src="//placehold.it/200x200" alt="...">
                </a>
              </div>
              <div class="col-xs-6 col-md-3">
                <a href="#" class="thumbnail">
                  <img src="//placehold.it/200x200" alt="...">
                </a>
              </div>
              <div class="col-xs-6 col-md-3">
                <a href="#" class="thumbnail">
                  <img src="//placehold.it/200x200" alt="...">
                </a>
              </div>
              <div class="col-xs-6 col-md-3">
                <a href="#" class="thumbnail">
                  <img src="//placehold.it/200x200" alt="...">
                </a>
              </div> 
            </div>
          </div><!-- /.col-xs-12 main -->
      </div><!--/.row-->
    </div><!--/.container-->
  </div>
</body>
</html>

<jsp:include page="/navbarForms.jsp" />
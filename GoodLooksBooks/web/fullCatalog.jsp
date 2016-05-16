<%-- 
    Document   : customerFullCatalog
    Author     : Kevin_Setayesh & Paul M.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script src="js/bookDescription.js" type="text/javascript"></script>
        <script src="js/fullCatalog.js" type="text/javascript"></script>
        <script src="js/reviews.js" type="text/javascript"></script>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty user}">
                <jsp:include page="/guestNavbar.jsp" />
            </c:when>
            <c:otherwise>
                <jsp:include page="/customerNavbar.jsp" />
            </c:otherwise>
        </c:choose>
        <div class="glb-page">
            <jsp:include page="/logo.jsp" />
            <c:set var="results" scope="session" value="${resultSet}"/>
            <br>
            <div class="container">
                <form class="form" name="searchForm" id="searchForm" action="SearchServlet" method = "get" role="search">
                    <div class="input-group">
                        <table class="padded">
                            <tr>
                                <td>
                                    <label for="title">Title</label>
                                    <input name="title" type="text" class="form-control" value="${results.title}" placeholder="Title" id="title">
                                </td>
                                <td>
                                    <label for="author">Author</label>
                                    <input name="author" type="text" class="form-control" value="${results.author}" placeholder="Author" id="author">
                                </td>
                                <td>
                                    <label for="publisher">Publisher</label>
                                    <input name="publisher" type="text" class="form-control" value="${results.publisher}" placeholder="Publisher" id="publisher">
                                </td>
                                <td>
                                    <label for="isbn-term">ISBN</label>
                                    <input name="isbn" type="text" class="form-control" value="${results.isbn}" placeholder="Isbn" id="isbn-term">
                                </td>
                                <td>
                                    <div class="input-group-btn">
                                        <button name="searchbtn" class="btn btn-default" type="submit">search</button>  
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                    
                    <div id="cats-view" class="dropdown">
                        <jsp:include page="/categoryForm.jsp" />    
                    </div>  
                </form>
                <div class="col-md-2">
                    <form id="sortRadioButtons" name="sortRadioButtons" action="SortItemsServlet" method = "post">
                        <div>
                            <jsp:include page="/sortForm.jsp" /> 
                        </div>  
                    </form>
                </div>
                <div class="col-md-10">
                    <ul class="nav nav-pills">
                        
                        <li class="active"><a href="#grid-view" data-toggle="tab"><img id="grid-tab" src="assets/glyphicons-157-show-thumbnails.png"></a></li>
                        <li><a href="#list-view" data-toggle="tab"><img id="list-tab" src="assets/glyphicons-530-list-alt.png"></a></li>
                        
                    </ul>
                    <div class="tab-content">
                        <div id="grid-view" class="tab-pane fade in active">
                            <jsp:include page="/searchGridView.jsp" />
                        </div>
                        <div id="list-view" class="tab-pane fade">
                            <jsp:include page="/searchListView.jsp" />
                        </div>
                    </div>
                </div>
            </div><!-- /.col-xs-12 main -->
        </div>
    </body>
</html>

<!--Form for clicking on an "item" (book)-->
<form id = "itemSelectionForm" name = "itemSelectionForm" action = "BookDescriptionServlet" method = "get">
    <input type = "hidden" id = "isbn" name = "isbn" value = "null">
</form>

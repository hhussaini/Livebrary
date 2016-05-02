<%-- 
    Document   : customerFullCatalog
    Author     : Kevin_Setayesh & Paul M.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <br>
            <div class="container">
                <div class="row row-offcanvas row-offcanvas-left">
                    <!-- sidebar -->
                    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
                        <div class="panel panel-default">
                            <form class="form" name="searchForm" id="searchForm" action="SearchServlet" method = "get" role="search">
                                <div class="input-group">
                                    <input name="searchTerm" type="text" class="form-control" value="${lastTermSearched}" placeholder="Title, Author, Genre, etc..." id="srch-term">
                                    <div class="input-group-btn">
                                        <button name="searchbtn" class="btn btn-default" type="submit">search</button>  
                                    </div>
                                </div>
                            </form>
                            <!-- <p> ${resultSize} results</p>  -->
                            <div class="panel-heading">Active Filters</div> <br>
                            <div class="panel-heading">Literature Medium</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="checkbox-inline">
                                        <label>
                                            <input name="book" type="checkbox">Book
                                        </label>
                                    </div> <br>
                                    <div class="checkbox-inline">
                                        <label>
                                            <input name="magazine" type="checkbox">Magazine
                                        </label>
                                    </div> <br>
                                    <div class="checkbox-inline">
                                        <label>
                                            <input name="audio-book" type="checkbox">Audio Books
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-heading">Category</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="checkbox-inline">
                                        <label>
                                            <input name="book" type="checkbox">Horror
                                        </label>
                                    </div> <br>
                                    <div class="checkbox-inline">
                                        <label>
                                            <input name="magazine" type="checkbox">Mystery
                                        </label>
                                    </div> <br>
                                    <div class="checkbox-inline">
                                        <label>
                                            <input name="audio-book" type="checkbox">War Novel
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
                                            <input name="book" type="checkbox">Ancient Mythology
                                        </label>
                                    </div> <br>
                                    <div class="checkbox-inline">
                                        <label>
                                            <input name="magazine" type="checkbox">Renaissance Literature
                                        </label>
                                    </div> <br>
                                    <div class="checkbox-inline">
                                        <label>
                                            <input name="audio-book" type="checkbox">19th Century
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
                            <table>
                                <tr>
                                    <c:set var="count" value="0" scope="page" />
                                    <c:forEach var="item" items="${searchResults}" varStatus="status">
                                        <c:if test="${status.index != 0 && status.index % 3 == 0}">
                                        </tr>
                                        <tr>
                                        </c:if>
                                        <td>
                                            <div class="col-xs-6 col-md-3"> 
                                                <td> 
                                                    
                                                    <a href = "#" id = "${item.isbn}" class="thumbnail" onclick = "selectedBook(this.id)">
                                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book${count}" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 200px;">
                                                        <c:out value="${item.title}"/> <br> <br>
                                                        <c:out value="by ${item.author}"/>
                                                    </a>
                                                </td>
                                            </div>
                                        </td>
                                        <c:set var="count" value="${count + 1}" scope="page"/>
                                    </c:forEach>
                                </tr>
                            </table>
                        </div>
                        <%--For displaying Page numbers. The when condition does not display a link for the current page--%>
                        <table border="1" cellpadding="5" cellspacing="5" align="center">
                            <tr>
                                <%--For displaying Previous link except for the 1st page --%>
                                <td><a href="SearchServlet?page=1&searchTerm=${lastTermSearched}"><<</a></td>
                                <c:if test="${currentPage != 1}">
                                    <td><a href="SearchServlet?page=${currentPage - 1}&searchTerm=${lastTermSearched}">Previous</a></td>
                                </c:if>
                                <c:forEach begin="${firstPage}" end="${lastPage}" var="i">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <td>${i}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><a href="SearchServlet?page=${i}&searchTerm=${lastTermSearched}">${i}</a>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <%--For displaying Next link --%>
                                <c:if test="${currentPage lt numOfPages}">
                                    <td><a href="SearchServlet?page=${currentPage + 1}&searchTerm=${lastTermSearched}">Next</a>
                                    </td>
                                </c:if> 
                                <td><a href="SearchServlet?page=${numOfPages}&searchTerm=${lastTermSearched}">>></a></td>
                            </tr>
                        </table>
                    </div>
                </div><!-- /.col-xs-12 main -->
            </div><!--/.row-->
        </div>
    </body>
</html>
    
<!--<script>
    window.onload=validateImgUrl();
</script>
                            -->
<!--Form for clicking on an "item" (book)-->
<form id = "itemSelectionForm" name = "itemSelectionForm" action = "BookDescriptionServlet" method = "post">
    <input type = "hidden" id = "isbn" name = "isbn" value = "null">
</form>
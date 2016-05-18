<%-- 
    Document   : customerIndex
    Author     : Kevin Young
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/header.jsp" />
        <script src="js/bootstrap-tabcollapse.js"></script>
        <script src="js/customerScript.js"></script>
    </head>
    <body>
        <jsp:include page="/customerNavbar.jsp" />
        <div class="glb-page">
            <jsp:include page="/logo.jsp" />
            <div class="container">
                <h1 class="jumbotron">Welcome ${user.username}!<br></h1>
                <ul id="tabs" class="nav nav-tabs">
                    <li class="active"><a href="#acct-sum" data-toggle="tab">Account at a Glance</a></li>
                    <li><a href="#checked-out" data-toggle="tab">Current Collection</a></li>
                    <li><a href="#on-hold" data-toggle="tab">On Hold</a></li>
                    <li><a href="#rated" data-toggle="tab">Items You've Rated</a></li>
                    <li><a href="#wishlist" data-toggle="tab">Your Wishlist</a></li>
                    <li><a href="#settings" data-toggle="tab">Item Settings</a></li>
                    <li><a href="#recommended" data-toggle="tab">Your recommended Items</a></li>
                </ul>
                <div id="tabContent" class="tab-content">
                    <div class="tab-pane fade in active" id="acct-sum">
                        <table style="width:50%">
                            <tr style="border-bottom: 3px dotted silver;">
                                <td><h3>Wishlist size</h3></td>
                                <td>${fn:length(fullWishlist)}</td>
                            </tr>
                            <tr style="border-bottom: 3px dotted silver;">
                                <td><h3>Checked out</h3></td>
                                <td>${fn:length(checkedOutItems)}</td>
                            </tr>
                            <tr style="border-bottom: 3px dotted silver;">
                                <td><h3>On hold</h3></td>
                                <td>${fn:length(onHoldItems)}</td>
                            </tr>
                        </table>
                        
                    </div>
                    <link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
                    <script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
                    <link href="https://cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet" />
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
                    <script src="js/data-tables.js"></script>
                    <script src="js/data-table-plugin.js"></script>
                    <div class="tab-pane fade" id="checked-out">
                        <c:choose>
                            <c:when test="${fn:length(checkedOutItems) > 0}">
                                <jsp:include page="/checkedOutItems.jsp" />
                            </c:when>
                            <c:otherwise>
                                <div class="heading-box">
                                    <h2>You currently have no books checked out.</h2>
                                    <a href="SearchServlet"><h3>Browse some books!</h3></a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="tab-pane fade" id="on-hold">
                        <c:choose>
                            <c:when test="${fn:length(onHoldItems) > 0}">
                                <jsp:include page="/heldItems.jsp" />
                            </c:when>
                            <c:otherwise>
                                <div class="heading-box">
                                    <h2>You currently have no books on hold.</h2>
                                    <a href="SearchServlet"><h3>Browse some books!</h3></a>                                
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="tab-pane fade" id="rated">
                        <c:choose>
                            <c:when test="${fn:length(ratedItems) > 0}">
                                <jsp:include page="/ratedItems.jsp" />
                            </c:when>
                            <c:otherwise>
                                <div class="heading-box">
                                    <h2>You currently have not rated any items.</h2>
                                    <a href="SearchServlet"><h3>Browse some books!</h3></a>                                
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="tab-pane fade" id="wishlist">
                        <c:choose>
                            <c:when test="${fn:length(fullWishlist) > 0}">
                                <jsp:include page="/wishlist.jsp" />
                            </c:when>
                            <c:otherwise>
                                <div class="heading-box">
                                    <h2>You wishlist is empty.</h2>
                                    <a href="SearchServlet"><h3>Browse some books!</h3></a>                                
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="tab-pane fade" id="settings">
                        <jsp:include page="/itemSettings.jsp" />
                    </div>
                    <div class="tab-pane fade" id="recommended">
                        <c:choose>
                            <c:when test="${fn:length(recommendedMap) > 0}">
                                <jsp:include page="/userRecommended.jsp" />
                            </c:when>
                            <c:otherwise>
                                <div class="heading-box">
                                    <h2>You did not recommend any books</h2>
                                    <a href="SearchServlet"><h3>Browse some books!</h3></a>                                
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div> <!-- glb page -->
    </body>
</html>
<!--            <div class="container container-small">
                <div class="row">
                    <h2>Custom Small Container</h2>
                    <div class="col-sm-4">
                        <a href="#" class="btn btn-block btn-primary">Account Summary<i class="fa fa-github"></i></a>
                    </div>
                    <div class="col-sm-4">
                        <a href="#" class="btn btn-block btn-primary">Current Collection<i class="fa fa-github"></i></a>
                    </div>
                    <div class="col-sm-4">
                        <a href="#" class="btn btn-block btn-primary">Rated Titles<i class="fa fa-github"></i></a>
                    </div>
                </div>
            </div>            -->

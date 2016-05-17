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
                    <li class="active"><a href="#acct-sum" data-toggle="tab">Account at a glance</a></li>
                    <li><a href="#checked-out" data-toggle="tab">Current Collection</a></li>
                    <li><a href="#on-hold" data-toggle="tab">On hold</a></li>
                    <li><a href="#rated" data-toggle="tab">Items you've rated</a></li>
                    <li><a href="#wishlist" data-toggle="tab">Your wishlist</a></li>
                    <li><a href="#settings" data-toggle="tab">Settings</a></li>
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

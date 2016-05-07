<%-- 
    Document   : wishlist
    Author     : mobile-mann
--%> 
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/header.jsp" />
    </head>
    <body>
        <jsp:include page="/customerNavbar.jsp" />
        <div class="glb-page">
            <jsp:include page="/logo.jsp" />
            <div class="container">
                <h1> ${user.username}'s Wishlist: ${wishlistSize} items</h1>
                <ul id="tabs" class="nav nav-tabs">
                    <li class="active"><a href="#all-wish" data-toggle="tab">All wishlist items</a></li>
                    <li ><a href="#avail-wish" data-toggle="tab">Available wishlist items</a></li>
                </ul>
                <div id="tabContent" class="tab-content">
                    <div class="tab-pane fade in active" id="all-wish">
                        <c:choose>
                            <c:when test="${fn:length(allWishlist) > 0}">
                                <table>
                                    <c:forEach var="wishlist" items="${allWishlist}">
                                        <tr>
                                            <td><h3><u>${wishlist.title}</u></h3> <h4>: ${wishlist.copiesLeft} Left</h4>
                                                <form action="BookDescriptionServlet" method = "post">
                                                    <input type="image" src="${wishlist.imageUrl}" id="${wishlist.isbn}" style="width: 200px;">
                                                    <input type="hidden" name="isbn" value="${wishlist.isbn}">
                                                </form>
                                            </td>
                                            <td>
                                                <form action="WishlistServlet" method="delete">
                                                    <button name="isbn" value="${wishlist.isbn}" class="btn btn-danger">Remove</button>
                                                    <input type="hidden" name="method" value="delete">
                                                </form>
                                            </td>
                                        </tr>
                                        <hr class="fancy">
                                    </c:forEach>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <div class="heading-box">
                                    <h2>You currently have no books on your wishlist.</h2>
                                    <a href="SearchServlet"><h3>Browse some books!</h3></a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="tab-pane fade" id="avail-wish">
                        <c:choose>
                            <c:when test="${fn:length(availWishlist) > 0}">
                                <table>
                                    <c:forEach var="wishlist" items="${availWishlist}">
                                        <tr>
                                            <td><h3><u>${wishlist.title}</u></h3> <h4>: ${wishlist.copiesLeft} Left</h4>
                                                <form action="BookDescriptionServlet" method = "post">
                                                    <input type="image" src="${wishlist.imageUrl}" id="${wishlist.isbn}" style="width: 200px;">
                                                    <input type="hidden" name="isbn" value="${wishlist.isbn}">
                                                </form>
                                            </td>
                                            <td>
                                                <form action="WishlistServlet" method="delete">
                                                    <button name="isbn" value="${wishlist.isbn}" class="btn btn-danger">Remove</button>
                                                    <input type="hidden" name="method" value="delete">
                                                </form>
                                            </td>
                                        </tr>
                                        <hr class="fancy">
                                    </c:forEach>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <div class="heading-box">
                                    <h2>You currently have no books on your wishlist, or none you've added are available.</h2>
                                    <a href="SearchServlet"><h3>Browse some books!</h3></a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                    
                <br>
            </div>
        </div>
    </body>
</html> 
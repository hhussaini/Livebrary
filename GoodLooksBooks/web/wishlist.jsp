<%-- 
    Document   : wishlist
    Author     : mobile-mann
--%> 
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>    
<div class="container">
    
    <br>
    <ul id="tabs" class="nav nav-tabs">
        <li class="active"><a href="#all-wish" data-toggle="tab">All wishlist items</a></li>
        <li ><a href="#avail-wish" data-toggle="tab">Available wishlist items</a></li>
    </ul>
    <div id="tabContent" class="tab-content">
        <div class="tab-pane fade in active" id="all-wish">
            <c:choose>
                <c:when test="${fn:length(fullWishlist) > 0}">
                    <table class="results-table">
                        <tr>
                            <c:forEach var="item" items="${fullWishlist}" varStatus="status">
                                <c:if test="${status.index != 0 && status.index % 2 == 0}">
                                </tr>
                                <tr>
                                </c:if>
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="item.isbn" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 200px;">
                                                <p>${item.title}</p>
                                                <h6>by</h6>
                                                <h5>${item.author}</h5>
                                            </a>
                                        </td>
                                        <td>
                                            <form action="WishlistServlet" method="delete">
                                                <button name="isbn" value="${item.isbn}" class="btn btn-danger">Remove</button>
                                                <input type="hidden" name="method" value="delete">
                                            </form>
                                        </td>
                                            
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tr>
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
                <c:when test="${fn:length(inStockWishlist) > 0}">
                    <table class="results-table">
                        <tr>
                            <c:forEach var="item" items="${inStockWishlist}" varStatus="status">
                                <c:if test="${status.index != 0 && status.index % 2 == 0}">
                                </tr>
                                <tr>
                                </c:if>
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="item.isbn" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 200px;">
                                                <p>${item.title}</p>
                                                <h6>by</h6>
                                                <h5>${item.author}</h5>
                                            </a>
                                        </td>
                                        <td>
                                            <form action="WishlistServlet" method="delete">
                                                <button name="isbn" value="${item.isbn}" class="btn btn-danger">Remove</button>
                                                <input type="hidden" name="method" value="delete">
                                            </form>
                                        </td>
                                            
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tr>
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
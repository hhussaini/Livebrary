<%-- 
    Document   : bestSellerList
    Created on : May 18, 2016, 3:49:59 AM
    Author     : Hamza
--%>

    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>    

    <head>
        <jsp:include page="/header.jsp" />
        <script src="js/bootstrap-tabcollapse.js"></script>
        <script src="js/customerScript.js"></script>
    </head>
    
    <jsp:include page="/adminNavbar.jsp" />
    <div class="glb-page">
        <jsp:include page="/logo.jsp" />
        <div class="container">
    <br>
    <ul id="tabs" class="nav nav-tabs">
        <li class="active"><a href="#all-wish" data-toggle="tab">Recommended Books</a></li>
    </ul>
    <div id="tabContent" class="tab-content">
        <div class="tab-pane fade in active" id="all-wish">
            <c:choose>
                <c:when test="${fn:length(recommendedMap) > 0}">
                    <table class="results-table">
                        <tr>
                            <c:forEach var="item" items="${recommendedMap}" varStatus="status">
                                <c:if test="${status.index != 0 && status.index % 2 == 0}">
                                </tr>
                                <tr>
                                </c:if>
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=${item.key}" id="${item.key}" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="item.key" class="bookImage" src="${item.value.imageUrl}" alt="${item.value.title}" style="width: 200px;">
                                                <p>${item.value.title}</p>
                                                <h6>by</h6>
                                                <h5>${item.value.author}</h5>
                                            </a>
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
                        <h2>There are no bestsellers</h2>
                        <a href="SearchServlet"><h3>Browse some books!</h3></a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        
    </div>
        </div>
    <br>
</div>
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
   <link rel="stylesheet" href="css/custom_css/high_contrast.css">
   <link rel="stylesheet" href="css/custom_css/dyslexic_font.css">
   </head>
   <body>
       <jsp:include page="/customerNavbar.jsp" />
       <div class="glb-page">
           <jsp:include page="/logo.jsp" />
           <div class="container">
               <h1 class="jumbotron">Welcome ${user.username}!<br></h1>
               <ul id="tabs" class="nav nav-tabs">
                   <li class="active"><a href="#acct-sum" data-toggle="tab">Account at a glance</a></li>
                   <!--<li><a href="#checked-out" data-toggle="tab">Current Collection</a></li>-->
                   <li><a href="#on-hold" data-toggle="tab">On hold</a></li>
               </ul>
               <div id="tabContent" class="tab-content">
                   <div class="tab-pane fade in active" id="acct-sum">
                       <table padding="">
                           <tr>
                               <td>
                                   <h3><a href="WishlistServlet">Wishlist</a> size</h3>
                               </td>
                               <td>
                                   <h2><c:out value="${fn:length(customerWishlist)}"></c:out></h2>
                                   </td>
                               </tr>
<!--                               <tr>
                                   <td>
                                       <h3>Checked out</h3>
                                   </td>
                                   <td>
                                       <h2><c:out value="${fn:length(checkedOut)}"></c:out></h2>
                                   </td>
                               </tr>-->
                               <tr>
                                   <td>
                                       <h3>On hold</h3>
                                   </td>
                                   <td>
                                       <h2><c:out value="${fn:length(onHoldItems)}"></c:out></h2>
                                   </td>
                               </tr>
                           </table>
                       </div>
<!--                       <div class="tab-pane fade" id="checked-out">
                       <%--<c:choose>--%>
                           <%--<c:when test="${fn:length(checkedOutItems) > 0}">--%>
                              <%--<jsp:include page="/checkedOutItems.jsp" />--%>
                           <%--</c:when>--%>
                           <%--<c:otherwise>--%>
                               <div class="heading-box">
                                   <h2>You currently have no books checked out.</h2>
                                   <a href="SearchServlet"><h3>Browse some books!</h3></a>
                               </div>
                           <%--</c:otherwise>--%>
                       <%--</c:choose>--%>
                   </div>-->
                   <div class="tab-pane fade" id="on-hold">
                       <c:choose>
                           <c:when test="${fn:length(onHoldItems) > 0}">
                               <table>
                                   <tr>
                                       <c:forEach var="item" items="${onHoldItems}" varStatus="status">
                                           <c:if test="${status.index != 0 && status.index % 3 == 0}">
                                           </tr>
                                           <tr>
                                           </c:if>
                                           <td>
                                               <div class="col-xs-6 col-md-3"> 
                                                   <td> 
                                                       <a href = "BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail">
                                                           <img onload="validateImgUrl(this.id)" name="bookImage" id="book${count}" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 200px;">
                                                           <c:out value="${item.title}"/> <br> <br>
                                                           <c:out value="by ${item.author}"/>
                                                       </a>
                                                   </td>
                                               </div>
                                           </td>  
                                       </c:forEach>
                                   </tr>
                               </table>
                           </c:when>
                           <c:otherwise>
                               <div class="heading-box">
                                   <h2>You currently have no books on hold.</h2>
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
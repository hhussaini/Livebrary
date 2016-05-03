<%-- 
    Document   : customerIndex
    Author     : Kevin Young
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/header.jsp" />
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
            </ul>
            <div id="tabContent" class="tab-content">
                <div class="tab-pane fade in active" id="acct-sum">
                    <table>
                        <tr>
                            <td>
                                <a href="WishlistServlet"><h3>Wishlist size</h3></a>
                            </td>
                            <td>
                                <h2><c:out value="${fn:length(customerWishlist)}">No name</c:out></h2>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h3>Items checkout out</h3>
                                </td>
                                <td>
                                    <h2><c:out value="${fn:length(checkedOut)}">No name</c:out></h2>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h3>On hold</h3>
                                </td>
                                <td>
                                    <h2><c:out value="${fn:length(onHoldItems)}">No name</c:out></h2>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="tab-pane fade" id="checked-out">
                    <c:choose>
                        <c:when test="${fn:length(checkedOutItems) > 0}">
                            
                            
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
                            <c:forEach var="item" items="${onHoldItems}" varStatus="status">
                                <c:if test="${status.index != 0 && status.index % 3 == 0}">
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
        </div>
    </div>
</body>
</html>

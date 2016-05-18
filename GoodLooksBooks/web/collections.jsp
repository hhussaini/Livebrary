<%-- 
    Document   : collections
    Author     : Kevin Young
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <jsp:include page="/header.jsp" />
</head>
<body>
    <c:choose>
        <c:when test="${empty user}">
            <jsp:include page="/guestNavbar.jsp" />
        </c:when>
        <c:when test="${user.type == 'customer'}">
            <jsp:include page="/customerNavbar.jsp" />
        </c:when>
    </c:choose>
    <div class="glb-page">
        <jsp:include page="/logo.jsp" />
        <div class="container">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Collections</h3>
            </div>
            <div class = "container">
               <h3><b>Most borrowed</b></h3>
            </div>
            <div class="panel-body">
            <table class="results-table" id="mostBorrowed">
               <tr>
                 <c:forEach var="item" items="${mostBorrowedItems}" varStatus="status">
                    <c:if test="${status.index != 0 && status.index % 5 == 0}">
                    </tr>
                    <tr>
                    </c:if>
                    <td>
                        <div> 
                            <td> 
                                <a href = "BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail" style="width: 200px;">
                                    <img onload="validateImgUrl(this.id)" name="bookImage" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 200px;">
                                    <p>${item.title}</p>
                                    <h6>by</h6>
                                    <h5>${item.author}</h5>
                                </a>
                            </td>
                        </div>
                    </td>  
                 </c:forEach>
               </tr>
            </table>
          </div>
            <div class = "container">
               <h3><b>Recently added</b></h3>
            </div>
            <div class="panel-body">
            <table class="results-table" id="recentlyAdded">
               <tr>
                 <c:forEach var="item" items="${recentlyAddedItems}" varStatus="status">
                    <c:if test="${status.index != 0 && status.index % 5 == 0}">
                    </tr>
                    <tr>
                    </c:if>
                    <td>
                        <div> 
                            <td> 
                                <a href = "BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail" style="width: 200px;">
                                    <img onload="validateImgUrl(this.id)" name="bookImage" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 200px;">
                                    <p>${item.title}</p>
                                    <h6>by</h6>
                                    <h5>${item.author}</h5>
                                </a>
                            </td>
                        </div>
                    </td>  
                 </c:forEach>
               </tr>
            </table>
          </div>
        </div>
           <div class = "container">
               <h3><b>Don't miss these</b></h3>
            </div>
            <div class="panel-body">
            <table class="results-table" id="unpopularItems">
               <tr>
                 <c:forEach var="item" items="${unpopularItems}" varStatus="status">
                    <c:if test="${status.index != 0 && status.index % 5 == 0}">
                    </tr>
                    <tr>
                    </c:if>
                    <td>
                        <div> 
                            <td> 
                                <a href = "BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail" style="width: 200px;">
                                    <img onload="validateImgUrl(this.id)" name="bookImage" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 200px;">
                                    <p>${item.title}</p>
                                    <h6>by</h6>
                                    <h5>${item.author}</h5>
                                </a>
                            </td>
                        </div>
                    </td>  
                 </c:forEach>
               </tr>
            </table>
          </div>
        </div>
    </div>
</body>
</html>
<%-- 
    Document   : userItems
    Created on : Apr 12, 2016, 11:16:13 PM
    Author     : Kevin_Setayesh
--%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
               
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="/header.jsp" />
    </head>
    <body>
        <jsp:include page="/customerNavbar.jsp" />
        <div class="glb-page">
            <jsp:include page="/logo.jsp" />
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Your Owned Items </h3>
                    </div>
                    <table>
                        <tr>
                            <c:forEach var="item" items="${userItemsList}" varStatus="status">
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
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
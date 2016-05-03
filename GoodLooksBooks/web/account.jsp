<%-- 
    Document   : account
    Author     : Kevin Young
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
       <jsp:include page="/header.jsp" />
   </head>
   <body>
       <c:choose>
           <c:when test="${empty user}">
               <jsp:include page="/guestNavbar.jsp" />
           </c:when>
           <c:when test="${user.type == 'admin'}">
               <jsp:include page="/adminNavbar.jsp" />
           </c:when>
           <c:when test="${user.type == 'librarian'}">
               <jsp:include page="/librarianNavbar.jsp" />
           </c:when>
           <c:when test="${user.type == 'publisher'}">
               <jsp:include page="/publisherNavbar.jsp" />
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
                       <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Account Options</h3>
                   </div>
                   <div class="panel-body">
                       <div class="list-group">
                           <a href="accountSettings.jsp"><img alt="brand" width = "100" height = "100" src="assets/settings-icon.png"></a>
                           Change Account Settings (name, address, etc.)
                       </div>
                   </div>
               </div>
           </div>
       </div>
   </body>
</html>
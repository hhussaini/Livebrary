<%-- 
    Document   : applicationsHelp
    Author     : Kevin Young
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
              <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Applications Help</h3>
            </div>
            <div class="panel-body form-horizontal">
               We have partnered with another website, Bad Looks Books (BLB), so users can download and return checked out items.
               <h3><b>How to Download an item from BLB</b></h3>
                  <ul>
                     <li>Sign in to your account.</li>
                     <li>Click the Currently Owned Items link.</li>
                     <li>Click the Options button.</li>
                     <li>Select the Download option.</li>
                     <li>Sign in to your BLB account.</li>
                     <li>Click Confirm Download on BLB.</li>
                  </ul>
                  <hr>
               <h3><b>How to Return an item from BLB</b></h3>
               <ul>
                  <li>Sign in to your account.</li>
                  <li>Click the Currently Owned Items link.</li>
                  <li>Click the Options button.</li>
                  <li>Select the Return option.</li>
                  <li>Sign in to your BLB account.</li>
                  <li>Click Confirm Return on BLB.</li>
               </ul>
            </div>
            </div>
         </div>
     </body>
</html>
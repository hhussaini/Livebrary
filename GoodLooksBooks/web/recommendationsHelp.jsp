<%-- 
    Document   : lendingPolicies
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
              <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Recommendations Help</h3>
            </div>
            <div class="panel-body form-horizontal">
               <h3><b>Recommending</b></h3>
                  A book not licensed to the site will display a “Recommend” button in response to a search. 
                  When the user clicks on this button, the system will prompt user for e­mail address, 
                  along with an option to check out book when the license is purchased or send an e­mail
                  when license is purchased.        
            </div>
           </div>
         </div>
       </div>
     </body>
</html>

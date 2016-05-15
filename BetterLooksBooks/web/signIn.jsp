<%-- 
    Document   : signIn
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
     <jsp:include page="/guestNavbar.jsp" />
       <div class="blb-page">
         <jsp:include page="/logo.jsp" />
         <div class="container">
           <div class="login">
             <h1>Login to Bad Looks Books</h1>
             <jsp:include page="/signInForm.jsp" />
           </div>
         </div>
       </div>
     </body>
</html>

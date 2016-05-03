<%-- 
    Document   : signIn
    Author     : Kevin Young
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="/header.jsp" />
   </head>
   <body>
     <jsp:include page="/navbar.jsp" />
       <div class="blb-page">
         <jsp:include page="/logo.jsp" />
         <div class="container">
           <div class="login">
             <h1>Login to Good Looks Books</h1>
             <jsp:include page="/signInForm.jsp" />
           </div>
         </div>
       </div>
     </body>
</html>

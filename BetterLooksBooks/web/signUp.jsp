<%-- 
    Document   : signUp
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
         <form name = "customerForm" action = "SignUpServlet" method = "post" class="form-horizontal signUpBody">
           <a href="publisherSignUp.jsp" class="btn btn-info">Publisher Sign Up</a></li>
           <a href="librarianSignUp.jsp" class="btn btn-info">Librarian Sign Up</a></li>
           <input type="hidden" name="userType" value="customer">
           <jsp:include page="/signUpForm.jsp" />
         </form>
       </div>
     </div>
   </body>
</html>

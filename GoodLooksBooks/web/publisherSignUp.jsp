<%-- 
    Document   : publisherSignUp
    Author     : Kevin Young
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="/header.jsp" />
   </head>
   <body>
     <jsp:include page="/guestNavbar.jsp" />
     <div class="glb-page">
       <jsp:include page="/logo.jsp" />
       <div class="container">
         <form name = "publisherSignUpForm" action = "SignUpServlet" method = "post" class="form-horizontal signUpBody">
           <a href="signUp.jsp" class="btn btn-info">Customer Sign Up</a></li>
           <a href="librarianSignUp.jsp" class="btn btn-info">Librarian Sign Up</a></li>
           <a href="adminSignUp.jsp" class="btn btn-info">Administrator Sign Up</a></li>
           <input type="hidden" name="userType" value="publisher">
           <div class="form-group">
             <label class = "control-label regLabel" for="company">Publisher Name:</label>
             <div class = "controls regControl">
                 <input type="text" class="form-control" name = "company">
             </div>
           </div>
           <jsp:include page="/signUpForm.jsp" />
         </form>
       </div>
     </div>
   </body>
</html>

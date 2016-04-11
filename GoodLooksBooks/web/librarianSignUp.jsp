<%-- 
    Document   : librarianSignUp
    Author     : Kevin Young
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Good Looks Books</title>
    <mytags:header/>
</head>
<body>
  <mytags:guestNavbar/>
  <div class="glb-page">
    <mytags:logo/>
    <div class="container">
      <form name = "librarianSignUpForm" action = "SignUpServlet" method = "post" class="form-horizontal signUpBody">
        <a href="signUp.jsp" class="btn btn-info">Customer Sign Up</a></li>
        <a href="publisherSignUp.jsp" class="btn btn-info">Publisher Sign Up</a></li>
        <input type="hidden" name="userType" value="librarian">
        <div class="form-group">
          <label class = "control-label regLabel" for="accessCode">Librarian Access Code:</label>
          <div class = "controls regControl">
              <input type="text" class="form-control" name = "accessCode">
          </div>
        </div>
        <mytags:signUpForm/>
      </form>
    </div>
  </div>
</body>
</html>

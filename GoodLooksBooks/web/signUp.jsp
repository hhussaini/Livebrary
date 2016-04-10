<%-- 
    Document   : signUp
    Author     : sonor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Good Looks Books</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/custom_css/stylesheet.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>
  <mytags:guestNavbar/>
  <div class="glb-page">
    <mytags:logo/>
    <div class="container">
      <div class="container" style="max-width: 75%">
          <hr class="fancy">
      </div>
      <form name = "customerForm" action = "SignUpServlet" method = "post" class="form-horizontal signUpBody">
        <a href="publisherSignUp.jsp" class="btn btn-info">Publisher Sign Up</a></li>
        <a href="librarianSignUp.jsp" class="btn btn-info">Librarian Sign Up</a></li>
        <input type="hidden" name="userType" value="customer">
        <mytags:signUpForm/>
      </form>
    </div>
  </div>
</body>
</html>

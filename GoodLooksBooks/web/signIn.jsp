<%-- 
    Document   : signIn
    Author     : Kevin Young
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Good Looks Books</title>
  <jsp:include page="/header.jsp" />
</head>
<body>
  <jsp:include page="/guestNavbar.jsp" />
    <div class="glb-page">
      <jsp:include page="/logo.jsp" />
      <div class="container">
        <div class="login">
          <h1>Login to Good Looks Books</h1>
          <jsp:include page="/signInForm.jsp" />
        </div>
<!--        <p class="homework-comment">
          For grading purposes, here are links to different user homepages<br>
          Will be removed in final increment
        </p>
        <div class="heading-box">
          Select the user type:
          <li><a href="customerIndex.jsp">Customer (regular user)</a></li>
          <li><a href="adminIndex.jsp">Admin</a></li>
          <li><a href="librarianIndex.jsp">Librarian</a></li>
          <li><a href="publisherIndex.jsp">Publisher</a></li>
          <li><a href="index.jsp">Go as a guest (no account used)</a></li>
        </div>-->
      </div>
    </div>
  </body>
  </html>

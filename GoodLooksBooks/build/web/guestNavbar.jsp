<%-- 
    Document   : guestNavbar
    Author     : Kevin Young
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <a href="index.jsp" class="pull-left">
          <img alt="brand" src="assets/brand-icon.png">
        </a>
      </div>
      <ul class="nav navbar-nav" style="font-weight: bold;">
        <li><a href="index.jsp">Home</a></li>
        <li><a href="#" onclick="aboutForm.submit();">About</a></li>
        <li><a href="fullCatalog.html">Full Catalog</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right" style="font-weight: bold;">
        <li><a href="signUp.jsp">Sign Up</a></li>
        <li><a href="signIn.jsp">Sign In</a></li>
      </ul>
    </div>
  </nav>
<br><br><br>

<form name = "aboutForm" action = "AboutServlet" method = "post"></form>
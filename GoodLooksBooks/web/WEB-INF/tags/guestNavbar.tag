<%-- 
    Document   : guestNavbar
    Author     : sonor
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <a href="index.jsp" class="pull-left">
          <img alt="brand" src="assets/brand-icon.png">
        </a>
      </div>
      <ul class="nav navbar-nav" style="font-weight: bold;">
        <li><a href="index.jsp">Home</a></li>
        <li><a href="about.html">About</a></li>
        <li><a href="fullCatalog.html">Full Catalog</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right" style="font-weight: bold;">
        <li><a href="signUp.jsp">Sign Up</a></li>
        <li><a href="signIn.html">Sign In</a></li>
      </ul>
    </div>
  </nav>
<%-- 
    Document   : librarianNavbar
    Author     : Kevin Young
--%>

<%-- any content can be specified here e.g.: --%>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <a href="librarianIndex.jsp" class="pull-left">
          <img alt="brand" src="assets/brand-icon.png">
        </a>
      </div>
      <ul class="nav navbar-nav" style="font-weight: bold;">
        <li><a href="librarianIndex.jsp">Librarian Home</a></li>
        <li><a href="about.jsp">About</a></li>
        <li><a href="account.jsp">Account</a></li>
        <li><a href="help.jsp">Help</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right" style="font-weight: bold;">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Options<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="SignOutServlet">Sign Out</a></li> 
          </ul>
        </li>
      </ul>
    </div>
  </nav>
<br><br><br>
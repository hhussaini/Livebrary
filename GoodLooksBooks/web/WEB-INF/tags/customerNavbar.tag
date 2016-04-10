<%-- 
    Document   : customerNavbar
    Created on : Apr 10, 2016, 5:59:43 PM
    Author     : sonor
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <a href="customerIndex.jsp" class="pull-left">
          <img alt="brand" src="assets/brand-icon.png">
        </a>
      </div>
      <ul class="nav navbar-nav" style="font-weight: bold;">
        <li><a href="customerIndex.jsp">Home</a></li>
        <li><a href="customerAbout.jsp">About</a></li>
        <li><a href="customerFullCatalog.jsp">Full Catalog</a></li>
        <li><a href="customerAccount.jsp">Account</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Options<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="customerOwnedItems.jsp">My Items</a></li>
            <li><a href="#">My Wishlist</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="guestIndex.jsp">Sign Out</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
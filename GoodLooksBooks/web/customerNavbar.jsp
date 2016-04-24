<%-- 
    Document   : customerNavbar
    Author     : Kevin Young
--%>

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
        <li><a href="#" onclick="aboutForm.submit();">About</a></li>
        <li><a href="search.do">Full Catalog</a></li>
        <li><a href="#" onclick="accountForm.submit();">Account</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Options<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="customerOwnedItems.jsp">My Items</a></li>
            <li><a href="WishlistServlet">My Wishlist</a></li>
            <li role="separator" class="divider"></li>
            <li onclick="signOutForm.submit();"><a href="#">Sign Out</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
<br><br><br>

<jsp:include page="/navbarForms.jsp" />

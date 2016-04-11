<%-- 
    Document   : adminIndex
    Author     : Kevin Young
--%>

<jsp:include page="/header.jsp" />
<body>
    <jsp:include page="/adminNavbar.jsp" />
  <div class="glb-page">
    <jsp:include page="/logo.jsp" />
    <div class="container">
      <div class="panel panel-default">
        <h1 class = "jumbotron">Welcome ${user.username}!<br></h1>
        <div class="panel-heading">
          <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Administration Options</h3>
        </div>
        <div class="panel-body">
          <div class="list-group">
            <a href="#" onclick="siteStatisticsForm.submit();"><img alt="brand" width = "100" height = "100" src="assets/statistics-icon.jpg"></a>
            Site Statistics          
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>

<form name = "siteStatisticsForm" action = "SiteStatisticsServlet" method = "post"></form>

<%-- 
    Document   : adminIndex
    Author     : Kevin Young
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<head>
  <title>Good Looks Books</title>
  <jsp:include page="/header.jsp" />
</head>
<body>
    <jsp:include page="/adminNavbar.jsp" />
  <div class="glb-page">
    <jsp:include page="/logo.jsp" />
    <div class="container">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Administration Options</h3>
        </div>
        <div class="panel-body">
          <div class="list-group">
            <a href="siteStatistics.html"><img alt="brand" width = "100" height = "100" src="assets/statistics-icon.jpg"></a>
            Site Statistics
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>

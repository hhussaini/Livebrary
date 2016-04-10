<%-- 
    Document   : librarianIndex
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
    <mytags:librarianNavbar/>
  <div class="glb-page">
      <mytags:logo/>
    <div class="container">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Librarian Options</h3>
        </div>
        <div class="panel-body">
          <div class="list-group">
            <a href="checkoutInfo.html"><img alt="brand" width = "100" height = "100" src="assets/edit-icon.png"></a>
            Checkout Information
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>

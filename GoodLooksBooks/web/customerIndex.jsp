<%-- 
    Document   : customerIndex
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
    <mytags:customerNavbar/>
  <div class="glb-page">
      <mytags:logo/>
    <div class="container">
      <h1> Welcome ${user.username} !<br></h1>
      <div class="col-sm-2"></div>
    </div>
  </div>
</div>
</body>
</html>

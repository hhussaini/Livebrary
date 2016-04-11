<%-- 
    Document   : customerIndex
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
    <jsp:include page="/customerNavbar.jsp" />
  <div class="glb-page">
      <jsp:include page="/logo.jsp" />
    <div class="container">
      <h1> Welcome ${user.username} !<br></h1>
      <div class="col-sm-2"></div>
    </div>
  </div>
</div>
</body>
</html>

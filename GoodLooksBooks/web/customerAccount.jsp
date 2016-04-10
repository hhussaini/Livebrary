<%-- 
    Document   : customerAccount
    Author     : Kevin Young
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
 <title>Your Good Looks Books Account</title>
 <mytags:header/>
</head>

<body>
 <mytags:customerNavbar/>
 <div class="glb-page">
   <mytags:logo/>
  <div class="container">
      <div class="panel panel-default">
          <div class="panel-heading">
              <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Account Options</h3>
          </div>
          <div class="panel-body">
              <div class="list-group">
                  <a href="customerAccountSettings.jsp"><img alt="brand" width = "100" height = "100" src="assets/settings-icon.png"></a>
                  Change Account Settings (name, address, etc.)
              </div>
          </div>
      </div>
  </div>
 </div>
</body>
</html>

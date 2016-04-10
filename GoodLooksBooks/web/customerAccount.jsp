<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
 <title>Your Good Looks Books Account</title>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="css/bootstrap.css">
 <link rel="stylesheet" href="css/custom_css/stylesheet.css">
 <link rel="stylesheet" href="css/custom_css/bookPhotoGrid.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
 <script src="js/bootstrap.js"></script>
</head>

<body>
 <mytags:customerNavbar/>
 <br><br><br>
 <div class="glb-page">
   <mytags:logo/>
  <div class="container" styl="padding: 100px;">
   &nbsp;<br>
   &nbsp;<br>
   &nbsp;<br>
  </div>
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

  <div>
  </body>
  </html>

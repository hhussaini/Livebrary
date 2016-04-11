<%-- 
    Document   : account
    Author     : Kevin Young
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <div class="glb-page">
   <jsp:include page="/customerNavbar.jsp" />
  <div class="container">
      <div class="panel panel-default">
          <div class="panel-heading">
              <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Account Options</h3>
          </div>
          <div class="panel-body">
              <div class="list-group">
                  <form name = "accountSettingsForm" action = "AccountSettingsServlet" method = "post">
                  <a href="#" onclick="accountSettingsForm.submit();"><img alt="brand" width = "100" height = "100" src="assets/settings-icon.png"></a>
                  Change Account Settings (name, address, etc.)
              </div>
          </div>
      </div>
  </div>
 </div>
</body>
</html>
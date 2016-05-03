<%-- 
    Document   : adminSignUp
    Created on : Apr 29, 2016, 4:39:39 AM
    Author     : Hamza
--%>

<jsp:include page="/header.jsp" />
<body>
  <jsp:include page="/guestNavbar.jsp" />
  <div class="glb-page">
    <jsp:include page="/logo.jsp" />
    <div class="container">
      <form name = "adminSignUpForm" action = "SignUpServlet" method = "post" class="form-horizontal signUpBody">
        <a href="signUp.jsp" class="btn btn-info">Customer Sign Up</a></li>
        <a href="publisherSignUp.jsp" class="btn btn-info">Publisher Sign Up</a></li>
        <a href="adminSignUp.jsp" class="btn btn-info">Administrator Sign Up</a></li>
        <input type="hidden" name="userType" value="admin">
        <div class="form-group">
          <label class = "control-label regLabel" for="accessCode">Administrator Access Code:</label>
          <div class = "controls regControl">
              <input type="text" class="form-control" name = "accessCode">
          </div>
        </div>
        <jsp:include page="/signUpForm.jsp" />
      </form>
    </div>
  </div>
</body>
</html>

<%-- 
    Document   : publisherSignUp
    Author     : Kevin Young
--%>

<jsp:include page="/header.jsp" />
<body>
  <jsp:include page="/guestNavbar.jsp" />
  <div class="glb-page">
    <jsp:include page="/logo.jsp" />
    <div class="container">
      <form name = "publisherSignUpForm" action = "SignUpServlet" method = "post" class="form-horizontal signUpBody">
        <a href="signUp.jsp" class="btn btn-info">Customer Sign Up</a></li>
        <a href="librarianSignUp.jsp" class="btn btn-info">Librarian Sign Up</a></li>
        <input type="hidden" name="userType" value="publisher">
        <div class="form-group">
          <label class = "control-label regLabel" for="accessCode">Publisher Access Code:</label>
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

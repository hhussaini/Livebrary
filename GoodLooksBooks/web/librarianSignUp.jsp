<%-- 
    Document   : librarianSignUp
    Author     : Kevin Young
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
  <div class="glb-page">
    <jsp:include page="/logo.jsp" />
    <div class="container">
      <form name = "librarianSignUpForm" action = "SignUpServlet" method = "post" class="form-horizontal signUpBody">
        <a href="signUp.jsp" class="btn btn-info">Customer Sign Up</a></li>
        <a href="publisherSignUp.jsp" class="btn btn-info">Publisher Sign Up</a></li>
        <input type="hidden" name="userType" value="librarian">
        <div class="form-group">
          <label class = "control-label regLabel" for="accessCode">Librarian Access Code:</label>
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

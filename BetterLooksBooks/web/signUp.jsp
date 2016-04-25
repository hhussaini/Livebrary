<%-- 
    Document   : signUp
    Author     : Kevin Young
--%>

<jsp:include page="/header.jsp" />
<body>
  <jsp:include page="/navbar.jsp" />
  <div class="blb-page">
    <jsp:include page="/logo.jsp" />
    <div class="container">
      <form name = "customerForm" action = "SignUpServlet" method = "post" class="form-horizontal signUpBody">
        <input type="hidden" name="userType" value="customer">
        <jsp:include page="/signUpForm.jsp" />
      </form>
    </div>
  </div>
</body>
</html>

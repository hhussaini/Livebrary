<%-- 
    Document   : signInForm
    Author     : Kevin Young
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<form name = "customerForm" action = "SignInServlet" method = "post" class="form-horizontal signUpBody">
    <div class="form-group">
      <label class = "control-label regLabel" for="username">Username:</label>
      <div class = "controls regControl">
          <input type="text" class="form-control" name="username">
      </div>
    </div>
    <div class="form-group">
      <label class = "control-label regLabel" for="password">Password:</label>
      <div class = "controls regControl">
          <input type="password" class="form-control" name="password">
      </div>
    </div>
    <input id="signInBtn" class="btn btn-info" type="submit" value="Sign In">
</form>
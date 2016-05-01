<%-- 
    Document   : editItem
    Author     : Kevin Young
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/header.jsp" />
<body>
    <jsp:include page="/publisherNavbar.jsp" />
    <div class="glb-page">
        <jsp:include page="/logo.jsp" />
        <div class="container">
            <h1> Editing "${itemClicked.title}"</h1>
            <form name = "customerForm" action = "SignInServlet" method = "post" class="form-horizontal signUpBody">
                <div class="form-group">
                  <label class = "control-label regLabel" for="isbn">ISBN: ${itemClicked.isbn}</label>
                  <div class = "controls regControl">
                      <input type="text" class="form-control" name="username">
                  </div>
                </div>
                <div class="form-group">
                  <label class = "control-label regLabel" for="title">Title: ${itemClicked.title}</label>
                  <div class = "controls regControl">
                      <input type="text" class="form-control" name="password">
                  </div>
                </div>
                <div class="form-group">
                  <label class = "control-label regLabel" for="author">Author: ${itemClicked.author}</label>
                  <div class = "controls regControl">
                      <input type="text" class="form-control" name="author">
                  </div>
                </div>
                <div class="form-group">
                  <label class = "control-label regLabel" for="description">Description: ${itemClicked.description}</label>
                  <div class = "controls regControl">
                      <input type="text" class="form-control" name="description">
                  </div>
                </div>
                <input id="editItemButton" class="btn btn-info" type="submit" value="Submit">
            </form>
        </div>
    </div>
</body>
</html> 

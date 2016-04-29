<%-- 
    Document   : help
    Author     : Kevin Young
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <jsp:include page="/header.jsp" />
    <script src="js/support.js" type="text/javascript"></script>
</head>
<body>
    <c:choose>
        <c:when test="${empty user}">
            <jsp:include page="/guestNavbar.jsp" />
        </c:when>
        <c:when test="${user.type == 'admin'}">
            <jsp:include page="/adminNavbar.jsp" />
        </c:when>
        <c:when test="${user.type == 'librarian'}">
            <jsp:include page="/librarianNavbar.jsp" />
        </c:when>
        <c:when test="${user.type == 'publisher'}">
            <jsp:include page="/publisherNavbar.jsp" />
        </c:when>
        <c:when test="${user.type == 'customer'}">
            <jsp:include page="/customerNavbar.jsp" />
        </c:when>
    </c:choose>
    <div class="glb-page">
        <jsp:include page="/logo.jsp" />
        <div class="container">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Help</h3>
            </div>
            <div class="panel-body">
                <div class="list-group">
                  <a href="#" onclick="showContactModal()">
                      <img alt="brand" width = "100" height = "100" src="assets/support-icon.jpg">
                  </a>
                  Contact Support
                </div>
                <div class="list-group">
                    <a href="lendingPolicies.jsp">
                        <img alt="brand" width = "100" height = "100" src="assets/book.png">
                    </a>
                    Lending Policies
                </div>
                <div class="list-group">
                    <a href="navigationHelp.jsp">
                        <img alt="brand" width = "100" height = "100" src="assets/question-icon.jpg">
                    </a>
                    Navigation Help
                </div>
              </div>
            </div>
          </div>
        </div>
    </div>
    <div class="modal fade" id="contactModal" tabindex="-1" role="dialog" aria-labelledby="contactModal" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
              <h4 class="modal-title" id="contactModal">Contact Support</h4>
            </div>
            <form id="contactForm" name="contactForm" action="ContactSupportServlet" method="post">  
              <div class="modal-body">
                <input type="text" class="form-control" placeholder="Message" name="message" autofocus />
                <c:choose>
                    <c:when test="${empty user}">
                        <br>
                        <input type="text" class="form-control" placeholder="Your Email" name="from" required autofocus />
                    </c:when>
                </c:choose>
              </div>
              <div class="modal-footer">
                <button type="submit" class="btn btn-primary">Send</button>
              </div>
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </form>
          </div>
        </div>
    </div>
</body>
</html>
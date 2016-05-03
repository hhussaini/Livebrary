<%-- 
    Document   : about
    Author     : Kevin Young
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/header.jsp" />
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
            <p class = "fontHeader"> The Developers </p>
            <table>
            <tr>
              <td>
                <img class="developer_images" src="assets/aboutme_images/Hamza.jpg" height="300" width="250">
              </td>
              <td>
                <div class="aboutDiv">
                    <font color="black">Hi my name is Hamza!!!</font>
                </div>
                <br><br><br>
              </td>
            </tr>
            <tr>
              <td>
                <img class="developer_images" src="assets/aboutme_images/KYoung.jpg" height="300" width="250">
              </td>
              <td>
                <div class="aboutDiv">
                    <font color="black">Hi my name is Kevin!!!</font>
                </div>
                <br><br><br>
              </td>
            </tr>
            <tr>
              <td>
                <img class="developer_images" src="assets/aboutme_images/KSetayesh.jpg" height="300" width="250">
              </td>
              <td>
                <div class="aboutDiv">
                    <font color="black">Hi my name is Chestbrahhh!!!</font>
                </div>
                <br><br><br>
              </td>
            </tr>
            <tr>
              <td>
                <img class="developer_images" src="assets/aboutme_images/Paul.jpg" height="300" width="250">
              </td>
              <td>
                <div class="aboutDiv">
                    <font color="black">Hi my name is Paul!!!</font>
                </div>
                <br><br><br>
              </td>
            </tr>
          </table>
        </div>
    </body>
</html>

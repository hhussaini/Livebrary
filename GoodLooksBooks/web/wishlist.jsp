<%-- 
    Document   : wishlist
    Author     : mobile-mann
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Good Looks Books</title>
        <jsp:include page="/header.jsp" />
    </head>
    <body>
        <jsp:include page="/customerNavbar.jsp" />
        <div class="glb-page">
            <jsp:include page="/logo.jsp" />
            <div class="container">
                <h1> ${user.username}'s Wishlist<br></h1>
            <h2> My Wishlist </h2>
            <table>
                <c:forEach var="wishlist" items="${customerWishlist}">
                    <tr>
                        <td>${wishlist.name}</td>
                        <td><img src="${wishlist.imageUrl}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <br>
        </div>
    </div>
</body>
</html>
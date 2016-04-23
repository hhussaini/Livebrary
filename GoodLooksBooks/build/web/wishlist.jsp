<%-- 
    Document   : wishlist
    Author     : mobile-mann
--%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<jsp:include page="/header.jsp" />
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
                        <td><c:out value="${wishlist.name}"/></td>
                    <td><img src="${wishlist.imageUrl}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <br>
        </div>
    </div>
</body>
</html>
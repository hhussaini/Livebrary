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
            <form action="WishlistServlet" method="remove">
                <button class="btn btn-danger">Remove</button>
            </form>
            <h1> ${user.username}'s Wishlist<br></h1>
            <table>
                <c:forEach var="wishlist" items="${customerWishlist}">
                    <tr>
                        <td><h3><u>${wishlist.name}</u></h3>
                            <img src="${wishlist.imageUrl}"/></td>
                        <td></td>
                        <td>
                            <form action="WishlistServlet" method="post">
                                <button name="bookName" value="${wishlist.name}" class="btn btn-danger">Remove</button>
                            </form>
                        </td>
                    </tr>
                    <hr class="fancy">
                </c:forEach>
            </table>
            <br>
        </div>
    </div>
</body>
</html>
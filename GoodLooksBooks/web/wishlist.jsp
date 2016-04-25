<%-- 
    Document   : wishlist
    Author     : mobile-mann
--%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/header.jsp" />
<body>
    <jsp:include page="/customerNavbar.jsp" />
    <div class="glb-page">
        <jsp:include page="/logo.jsp" />
        <div class="container">
            <h1> ${user.username}'s Wishlist: ${wishlistSize} items</h1>
            <table>
                <c:forEach var="wishlist" items="${customerWishlist}">
                    <tr>
                        <td><h3><u>${wishlist.title}</u></h3>
                            
                            <form action="UserBookDescriptionServlet" method = "post">
                                <input type="image" src="${wishlist.imageUrl}" id="${wishlist.isbn}" style="width: 200px;">
                                <input type="hidden" name="hiddenFormID" value="${wishlist.isbn}">
                            </form> 
                            
<!--                            <a href = "#" id="${wishlist.isbn}" onclick="selectedBook(this.id)">
                                <img src = "${wishlist.imageUrl}" alt="${wishlist.title}" style="width: 200px;"> 
                            </a>-->
                        </td>
                        <td>
                            <form action="WishlistServlet" method="delete">
                                <button name="isbn" value="${wishlist.isbn}" class="btn btn-danger">Remove</button>
                                <input type="hidden" name="method" value="delete">
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


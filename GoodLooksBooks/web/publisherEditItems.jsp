<%-- 
    Document   : publisherEditItems
    Author     : Kevin Young
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/header.jsp" />
<body>
    <jsp:include page="/publisherNavbar.jsp" />
    <div class="glb-page">
        <jsp:include page="/logo.jsp" />
        <div class="container">
            <h1> ${user.company}'s Items: ${publisherItemsSize} items</h1>
            <table>
                <c:forEach var="item" items="${publisherItems}">
                    <tr>
                        <td><h3><u>${item.title}</u></h3> 
                            <form action="BookDescriptionServlet" method = "post">
                                <input type="image" src="${item.imageUrl}" id="${item.isbn}" style="width: 200px;">
                                <input type="hidden" name="isbn" value="${item.isbn}">
                            </form> 
                        </td>
                        <td>
                            <form action="PublisherEditItemsServlet" method="post">
                                <button name="isbn" value="${item.isbn}" class="btn btn-danger">Edit</button>
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
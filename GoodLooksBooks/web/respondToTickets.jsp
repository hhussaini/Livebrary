<%-- 
    Document   : respondToTickets
    Author     : Kevin Young
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/header.jsp" />
<body>
    <jsp:include page="/adminNavbar.jsp" />
    <div class="glb-page">
        <jsp:include page="/logo.jsp" />
        <div class="container">
            <h1> Tickets: ${publisherTicketsSize} tickets</h1>
            <table>
                <c:forEach var="ticket" items="${publisherTickets}">
                    <tr>
                        <td><h3><u>${ticket.type} request</u></h3>
                        </td>
                        <td>
                            <form action="AdminRespondToTciektsServlet" method="post">
                                <button name="view" class="btn btn-danger">View</button>
                                <button name="accept" class="btn btn-danger">Accept</button>
                                <button name="deny" class="btn btn-danger">Deny</button>
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
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
                <c:forEach var="ticket" items="${publisherTickets}" varStatus="loop">
                    <tr>
                        <td><h3><u>${ticket.type} request</u></h3>
                        </td>
                        <td>
                            <form action="AdminRespondToTicketsServlet" method="viewTicket">
                                <button name="viewTicket" class="btn btn-danger">View</button>
                                <input type="hidden" name="ticketIndex" value="${loop.index}">
                                <input type="hidden" name="method" value="viewTicket">
                            </form>
                            <form action="AdminRespondToTicketsServlet" method="acceptTicket">
                                <button name="acceptTicket" class="btn btn-danger">Accept</button>
                                <input type="hidden" name="ticketIndex" value="${loop.index}">
                                <input type="hidden" name="method" value="acceptTicket">
                            </form>
                            <form action="AdminRespondToTicketsServlet" method="denyTicket">
                                <button name="denyTicket" class="btn btn-danger">Deny</button>
                                <input type="hidden" name="ticketIndex" value="${loop.index}">
                                <input type="hidden" name="method" value="denyTicket">
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
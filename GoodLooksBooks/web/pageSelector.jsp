<%-- 
    Document   : pageSelector
    Created on : May 6, 2016, 2:18:49 AM
    Author     : PaulMan
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<%--For displaying Page numbers. The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5" align="center" class="padded">
    <tr>
        <%--For displaying Previous link except for the 1st page --%>
        <td><a href="SearchServlet?page=1&searchTerm=${lastTermSearched}"><<</a></td>
        <c:if test="${currentPage != 1}">
            <td><a href="SearchServlet?page=${currentPage - 1}&searchTerm=${lastTermSearched}">Previous</a></td>
        </c:if>
        <c:forEach begin="${firstPage}" end="${lastPage}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="SearchServlet?page=${i}&searchTerm=${lastTermSearched}">${i}</a>
                    </td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <%--For displaying Next link --%>
        <c:if test="${currentPage lt numOfPages}">
            <td><a href="SearchServlet?page=${currentPage + 1}&searchTerm=${lastTermSearched}">Next</a>
            </td>
        </c:if> 
        <td><a href="SearchServlet?page=${numOfPages}&searchTerm=${lastTermSearched}">>></a></td>
    </tr>
</table>
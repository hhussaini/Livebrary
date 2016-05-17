<%-- 
    Document   : pageSelector
    Created on : May 6, 2016, 2:18:49 AM
    Author     : PaulMan
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<%--For displaying Page numbers. The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5" align="center" class="padded">
    <form action="SearchServlet" method="jump">
        <tr>
            <%--For displaying Previous link except for the 1st page --%>
            <td>
                <button type="submit" class="link" name="page" id="page" value="1"><span>|<</span></button>
            </td>
            <c:if test="${results.currentPage != 1}">
                <td>
                    <button type="submit" class="link" name="page" id="page" value="${results.currentPage - 1}"><span>Prev</span></button>
                </td>
            </c:if>
            <c:forEach begin="${results.firstPage}" end="${results.lastPage}" var="i">
                <c:choose>
                    <c:when test="${results.currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <button type="submit" class="link" name="page" id="page" value="${i}"><span>${i}</span></button>
                        </td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <%--For displaying Next link --%>
            <c:if test="${results.currentPage lt results.numPages}">
                <td>
                     <button type="submit" class="link" name="page" id="page" value="${results.currentPage + 1}"><span>Next</span></button>
                </td>
            </c:if> 
            <td>
                <button type="submit" class="link" name="page" id="page" value="${results.numPages}"><span>>|</span></button>
            </td>
        </tr>
        <input type="hidden" name="method" value="jump">
    </form>
</table>
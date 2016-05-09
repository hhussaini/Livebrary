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
        <td><a onclick="goToPage(1)"><<</a></td>
        <c:if test="${results.currentPage != 1}">
            <td><a onclick="goToPage(this.id)" id="${results.currentPage - 1}">Previous</a></td>
        </c:if>
        <c:forEach begin="${results.firstPage}" end="${results.lastPage}" var="i">
            <c:choose>
                <c:when test="${results.currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a onclick="goToPage(this.id)" id="${i}">${i}</a>
                    </td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <%--For displaying Next link --%>
        <c:if test="${results.currentPage lt results.numPages}">
            <td><a onclick="goToPage(this.id)" id="${results.currentPage + 1}">Next</a>
            </td>
        </c:if> 
        <td><a onclick="goToPage(this.id)" id="${results.lastPage}">>></a></td>
    </tr>
</table>
<script type="text/javascript">
    function goToPage(page)
    {
        var str = window.location.href;
        var url = str.split("/");
        var parameters = url[url.length - 1];
        console.log(parameters);
        
        document.searchForm.submit();
    }
</script>
<%-- 
    Document   : searchListView
    Created on : May 6, 2016, 2:02:48 AM
    Author     : PaulMan
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:choose>
    <c:when test="${fn:length(results.books) > 0}">        
        <table class="results-table" id="list">
            <c:forEach var="item" items="${results.books}" varStatus="status">
                <tr>
                    <td>
                        <a href="BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail">
                            <img onload="validateImgUrl(this.id)" name="bookImage" id="book${count}" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 100px;">
                        </a>
                    </td>
                    <td><p><a id="${item.isbn}" href="BookDescriptionServlet?isbn=${item.isbn}">${item.title}</a></p> <p>by ${item.author}</p> <br>
                        <h4>Left : ${item.copiesLeft} | Published : ${item.date} </h4>                       
                    </td>
                </tr>
                <!--<hr class="fancy">-->
            </c:forEach>
        </table>
        <jsp:include page="/pageSelector.jsp" />
    </c:when>
    <c:otherwise>
        <div class="heading-box">
            <h2>No results.</h2>    
        </div>
    </c:otherwise>
</c:choose>

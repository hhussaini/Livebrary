<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>         
<c:choose>
    <c:when test="${fn:length(searchResults) > 0}">
        <table>
            <tr>
                <c:forEach var="item" items="${searchResults}" varStatus="status">
                    <c:if test="${status.index != 0 && status.index % 3 == 0}">
                    </tr>
                    <tr>
                    </c:if>
                    <td>
                        <div class="col-xs-6 col-md-3"> 
                            <td> 
                                <a href = "BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail">
                                    <img onload="validateImgUrl(this.id)" name="bookImage" id="book${count}" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 200px;">
                                    <p>${item.title}"</p> <br> <br>
                                    <p>by ${item.author}"</p>
                                </a>
                            </td>
                        </div>
                    </td>  
                </c:forEach>
            </tr>
        </table>
        <jsp:include page="/pageSelector.jsp" />
    </c:when>
    <c:otherwise>
        <div class="heading-box">
            <h2>No results.</h2>    
        </div>
    </c:otherwise>
</c:choose>
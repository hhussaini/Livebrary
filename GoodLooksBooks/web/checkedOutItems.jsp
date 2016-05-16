<%-- 
    Document   : checkedOutItems
    Author     : Kevin Young
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <table class="results-table" id="checked-out">
       <tr>
         <c:forEach var="item" items="${checkedOutItems}" varStatus="status">
            <c:if test="${status.index != 0 && status.index % 3 == 0}">
            </tr>
            <tr>
            </c:if>
            <td>
                <div class="col-xs-6 col-md-3"> 
                    <td> 
                        <a href = "BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail">
                            <img onload="validateImgUrl(this.id)" name="bookImage" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 200px;">
                            <p>${item.title}</p>
                            <h6>by</h6>
                            <h5>${item.author}</h5>
                        </a>
                        <button type="button" onclick="downloadBook(${item.isbn})" class="btn btn-primary">Download</button>
                        <button type="button" onclick="returnBook(${item.isbn})" class="btn btn-primary">Return</button> <br>
                    </td>
                </div>
            </td>  
         </c:forEach>
       </tr>
    </table>
</div>

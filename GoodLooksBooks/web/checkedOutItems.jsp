<%-- 
    Document   : checkedOutItems
    Author     : Kevin Young
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                    <a href = "BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail" style="width: 200px;">
                        <img onload="validateImgUrl(this.id)" name="bookImage" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 200px;">
                        <p>${item.title}</p>
                        <h6>by</h6>
                        <h5>${item.author}</h5>
                    </a>
                    <div class="btn-group">
                       <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                         Options
                       </button>
                       <div class="dropdown-menu">
                          <a class="dropdown-item" href="#" onclick="downloadBook(${item.isbn})">Download</a><br>
                          <a class="dropdown-item" href="#" onclick="returnBook(${item.isbn})">Return</a>
                          <form id="renewItemForm" action="ItemAccessServlet" method="doRenew">
                             <input type="hidden" name="isbn" value="${item.isbn}">
                             <input type="hidden" name="method" value="doRenew">
                             <input type="submit" value="Renew">
                          </form>
                       </div>
                    </div>
                </td>
            </div>
        </td>  
     </c:forEach>
   </tr>
</table>
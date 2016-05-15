<%-- 
    Document   : checkedOutItems
    Author     : Kevin Young
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table class="results-table" id="checked-out">
   <tr>
      <c:choose>
         <c:when test="${fn:length(checkedOutItems) > 0}">
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
                           <button type="button" onclick="downloadBook(${item.isbn})" class="btn btn-primary">Download</button> <br>
                           <button type="button" onclick="returnBook(${item.isbn})" class="btn btn-primary">Return</button> <br>
                       </td>
                   </div>
               </td>  
            </c:forEach>
         </c:when>
         <c:otherwise>
             <div class="heading-box">
                 <h2>You currently have no books checked out.</h2>
                 <a href="SearchServlet"><h3>Browse some books!</h3></a>
             </div>
         </c:otherwise>
     </c:choose>
   </tr>
</table>

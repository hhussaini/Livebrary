<%-- 
    Document   : heldItems
    Author     : Kevin Young
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<table>
   <tr>
       <c:forEach var="item" items="${onHoldItems}" varStatus="status">
           <c:if test="${status.index != 0 && status.index % 3 == 0}">
           </tr>
           <tr>
           </c:if>
           <td>
               <div class="col-xs-6 col-md-3"> 
                   <td> 
                       <a href = "BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail">
                           <img onload="validateImgUrl(this.id)" name="bookImage" id="book${count}" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 200px;">
                           <c:out value="${item.title}"/> <br> <br>
                           <c:out value="by ${item.author}"/>
                       </a>
                   </td>
               </div>
           </td>  
       </c:forEach>
   </tr>
</table>
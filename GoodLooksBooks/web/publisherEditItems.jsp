<%-- 
    Document   : publisherEditItems
    Author     : Kevin Young
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="/header.jsp" />
   </head>
   <body>
       <jsp:include page="/publisherNavbar.jsp" />
       <div class="glb-page">
           <jsp:include page="/logo.jsp" />
           <div class="container">
               <h1> ${user.company}'s Items: ${publisherItemsSize} items</h1>
               <table>
                   <c:forEach var="item" items="${publisherItems}">
                       <tr>
                           <td><h3><u>${item.title}</u></h3> 
                               <img src="${item.imageUrl}" alt="Item" style="width: 200px;">
                           </td>
                           <td>
                               <form action="PublisherEditItemsServlet" method="post">
                                   <button name="isbn" value="${item.isbn}" class="btn btn-danger">Edit</button>
                               </form>
                               <form action="PublisherEditItemsServlet" method="doDelete">
                                   <button name="isbn" value="${item.isbn}" class="btn btn-danger">Delete</button>
                                   <input type="hidden" name="method" value="doDelete">
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
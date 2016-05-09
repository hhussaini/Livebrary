<%-- 
    Document   : allSubjectsPage
    Author     : Kevin Young
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="/header.jsp" />
   </head>
   <body>
     <jsp:include page="/guestNavbar.jsp" />
     <div class="glb-page">
       <jsp:include page="/logo.jsp" />
       <div class="container">
         <c:forEach var="element" items="${categories}">
            <td>
               <div class="col-sm-12 col-md-3"> 
                   <td> 
                       <a href = "SearchServlet?categories=${element.value}" class="thumbnail">
                          ${element.key}
                       </a>
                   </td>
               </div>
            </td>
         </c:forEach>
       </div>
     </div>
     </div>
   </body>
</html>

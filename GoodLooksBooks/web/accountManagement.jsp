<%-- 
    Document   : accountManagement
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
       <jsp:include page="/adminNavbar.jsp" />
       <div class="glb-page">
           <jsp:include page="/logo.jsp" />
           <div class="container">
               <h1>Good Looks Books Users</h1>
               <table>
                   <form action="AdminAccountManagementServlet" method="doAdd">
                       <button name="addUserBtn" value="${user.username}" class="btn btn-danger">Add New User</button>
                       <input type="hidden" name="method" value="doAdd">
                   </form>
                   <c:forEach var="user" items="${allUsers}">
                       <tr>
                        <td>
                           <h3><u>${user.username}</u></h3>  
                           <form action="AdminAccountManagementServlet" method="doEdit">
                              <button name="editUserBtn" value="${user.username}" class="btn btn-danger">Edit</button>
                              <input type="hidden" name="method" value="doEdit">
                           </form>
                           <br>
                           <form action="AdminAccountManagementServlet" method="doDelete">
                               <button name="deleteUserBtn" value="${user.username}" class="btn btn-danger">Delete</button>
                               <input type="hidden" name="method" value="doDelete">
                           </form>
                           <br>
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
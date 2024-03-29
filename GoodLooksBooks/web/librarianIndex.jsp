<%-- 
    Document   : librarianIndex
    Author     : Kevin Young
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="/header.jsp" />
   </head>
   <body>
     <jsp:include page="/librarianNavbar.jsp" />
     <div class="glb-page">
         <jsp:include page="/logo.jsp" />
       <div class="container">
         <div class="panel panel-default">
           <h1 class = "jumbotron">Welcome ${user.username}!<br></h1>
           <div class="panel-heading">
             <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Librarian Options</h3>
           </div>
           <div class="panel-body">
             <div class="list-group">
               <a href="checkoutInfo.html"><img alt="brand" width = "100" height = "100" src="assets/edit-icon.png"></a>
               Checkout Information
             </div>
           </div>
         </div>
       </div>
     </div>
   </body>
</html>

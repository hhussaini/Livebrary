<%-- 
    Document   : adminIndex
    Author     : Kevin Young
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="/header.jsp" />
   </html>
   <body>
     <jsp:include page="/adminNavbar.jsp" />
     <div class="glb-page">
       <jsp:include page="/logo.jsp" />
       <div class="container">
         <div class="panel panel-default">
           <h1 class = "jumbotron">Welcome ${user.username}!<br></h1>
           <div class="panel-heading">
             <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Administration Options</h3>
           </div>
           <div class="panel-body">
             <div class="list-group">
               <a href="AdminRespondToTicketsServlet"><img alt="brand" width = "100" height = "100" src="assets/service-request-icon.jpeg"></a>
               Respond to Tickets         
             </div>
             <div class="list-group">
               <a href="AdminAccountManagementServlet"><img alt="brand" width = "100" height = "100" src="assets/account-management-icon.gif"></a>
               Account Management        
             </div>
             <div class="list-group">
               <a href="RecommendedBooksServlet"><img alt="brand" width = "100" height = "100" src="assets/recommendedBooks.png"></a>
               Recommended Books     
             </div>
             <div class="list-group">
               <a href="BestSellersListServlet"><img alt="brand" width = "100" height = "100" src="assets/bestseller.png"></a>
               Best Sellers List     
             </div>
           </div>
         </div>
       </div>
     </div>
   </body>
</html>

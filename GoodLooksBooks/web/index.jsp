<%-- 
    Document   : index
    Author     : Kevin Young
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="/header.jsp" />
      <script src="js/script.js" type="text/javascript"></script>
   </head>
   <body>
     <jsp:include page="/guestNavbar.jsp" />
     <div class="glb-page">
       <jsp:include page="/logo.jsp" />
       <div class="container">
         <div class="row">
           <div class="col-sm-6">
             <div class="heading-box">
               Need an ebook?
             </div>
             &nbsp;<br>
             <p class="center">Your favorite literature is right at your finger tips, all you need is an account with Good Looks Books.
             </p>
           </div>
           <div class="col-sm-6">
             <div class="heading-box">
               Catalog Access
             </div>
             &nbsp;<br>
             <p class="center">With or without and account you can begin <a href="SearchServlet">searching</a> for a book, an author, or a genre</p>
             &nbsp;<br>
             &nbsp;<br>
           </div>
         </div><div>
           <div class="col-sm-15">
             <div class="heading-box">
               Advertisement
             </div>
             <div id="sidebar">
               <p class="center">
                  <script>
                     displayAds();
                  </script>
               </p>
            </div>
           </div>
         </div>
       </div>
     </div>
   </body>
</html>

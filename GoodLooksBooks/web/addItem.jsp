<%-- 
    Document   : addItem
    Author     : Kevin Young
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
               <h1> Adding New Item</h1>
               <form action = "PublisherEditItemsServlet" method = "doAdd" class="form-horizontal signUpBody">
                   <div class="form-group">
                     <label class = "control-label regLabel" for="isbn">ISBN:</label>
                     <div class = "controls regControl">
                         <input type="text" class="form-control" name="isbn" required autofocus>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class = "control-label regLabel" for="title">ISBN10:</label>
                     <div class = "controls regControl">
                         <input type="text" class="form-control" name="isbn10" required autofocus>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class = "control-label regLabel" for="title">Title:</label>
                     <div class = "controls regControl">
                         <input type="text" class="form-control" name="title" required autofocus>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class = "control-label regLabel" for="author">Author:</label>
                     <div class = "controls regControl">
                         <input type="text" class="form-control" name="author" required autofocus>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class = "control-label regLabel" for="description">Description:</label>
                     <div class = "controls regControl">
                         <input type="text" class="form-control" name="description" required autofocus>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class = "control-label regLabel" for="category">Category:</label>
                     <div class = "controls regControl">
                         <input type="text" class="form-control" name="category" required autofocus>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class = "control-label regLabel" for="title">Binding:</label>
                     <div class = "controls regControl">
                         <input type="text" class="form-control" name="binding" required autofocus>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class = "control-label regLabel" for="title">Image URL:</label>
                     <div class = "controls regControl">
                         <input type="text" class="form-control" name="imageUrl" required autofocus>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class = "control-label regLabel" for="title">Pages:</label>
                     <div class = "controls regControl">
                         <input type="text" class="form-control" name="pages" required autofocus>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class = "control-label regLabel" for="title">Language:</label>
                     <div class = "controls regControl">
                         <input type="text" class="form-control" name="language" required autofocus>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class = "control-label regLabel" for="title">Currency:</label>
                     <div class = "controls regControl">
                         <input type="text" class="form-control" name="currency" required autofocus>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class = "control-label regLabel" for="title">List Price:</label>
                     <div class = "controls regControl">
                         <input type="text" class="form-control" name="listPrice" required autofocus>
                     </div>
                   </div>
                   <input type="hidden" name="method" value="doAdd">
                   <input id="addItemButton" class="btn btn-info" type="submit" value="Submit">
               </form>
           </div>
       </div>
   </body>
</html> 

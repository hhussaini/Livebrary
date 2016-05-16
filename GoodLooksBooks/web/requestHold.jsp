<%-- 
    Document   : requestHold
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
        <jsp:include page="/customerNavbar.jsp" />
        <div class="glb-page">
            <jsp:include page="/logo.jsp" />
            <div class="container">
                 <h5>Fill out the form below to join the holds list for this title. 
                    Once the title is available, you'll be notified that it's 
                    ready for you to borrow from your Holds page, or to download
                    from your Checkouts page (if you have the hold automatically
                    borrowed for you).</h5>
            </div>
            <div class="container">
               <form name = "holdItemForm" action = "ItemAccessServlet" method = "doHold" class="form-horizontal signUpBody">
                  <div class="form-group">
                     <label class = "control-label regLabel" for="title">Title:</label>
                     <div class = "controls regControl">
                        <p>${itemHeld.title}</p>
                     </div>
                  </div>
                  <div class="form-group">
                     <label class = "control-label regLabel" for="author">Author:</label>
                     <div class = "controls regControl">
                         <p>${itemHeld.author}</p>
                     </div>
                  </div>
                  <div class="form-group">
                     <label class = "control-label regLabel" for="email">Email:</label>
                     <div class = "controls regControl">
                         <input type="text" class="form-control" id="email" name="email">
                     </div>
                  </div>
                  <div class="form-group">
                     <label class = "control-label regLabel" for="automaticCheckout">Automatic Checkout:</label>
                     <div class = "controls regControl">
                         <select name="automaticCheckout" class="form-control" id="automaticCheckout">
                         <option value="y">Yes</option>
                         <option value="n">No</option>
                       </select>
                     </div>
                   </div>
                  <input type="hidden" name="method" value="doHold">
                  <input type="hidden" name="isbn" value="${itemHeld.isbn}">
                  <button class="btn btn-primary" type="submit">Submit</button>  
               </form>
            </div>
        </div>
    </body>
</html>

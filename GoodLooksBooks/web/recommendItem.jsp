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
                 <h5>This is so yolo.</h5>
            </div>
            <div class="container">
               <form name = "recommendItemForm" action = "ItemAccessServlet" method = "doRecommend" class="form-horizontal signUpBody">        
                  <div class="form-group">
                     <label class = "control-label regLabel" for="email">Email:</label>
                     <div class = "controls regControl">
                         <input type="text" class="form-control" id="inputEmail" name="email" required>
                     </div>
                  </div>
                  <div class="form-group">
                     <label class = "control-label regLabel" for="automaticCheckout">Automatic Checkout:</label>
                     <div class = "controls regControl">
                         <select name="automaticCheckout" class="form-control" id="automaticCheckout">
                         <option value="s">Select</option>
                         <option value="y">Yes</option>
                         <option value="n">No</option>
                       </select>
                     </div>
                   </div>
               </form>
               <button class="btn btn-primary" onclick="recommendItem()">Submit</button>  
            </div>
        </div>
    </body>


<form id="recommendItemForm" action="ItemAccessServlet" method="doRecommend">
   <input type="hidden" name="isbn" value="${itemClicked.isbn}">
   <input type="hidden" name="method" value="doRecommend">
   <input type="hidden" id="emailRecommend" name="email" value="">
   <input type="hidden" id="checkOutRecommend" name="checkOut" value="">
</form>
   
</html>
 
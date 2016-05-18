<%-- 
    Document   : itemButtons
    Author     : Kevin Young
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<button type="button" name = "button1" onclick="sampleItem('${itemClicked.sampleUrl}')" class="btn btn-primary">Sample</button>
<br><br>
<c:choose>
   <c:when test="${itemAccess == 'Recommend'}"> 
<!--      <button type="button" name = "button1" onclick="recommendItem()" class="btn btn-primary">Recommend</button> <br>-->
   </c:when>
   <c:when test="${itemAccess == 'Borrow'}">
      <button type="button" name = "button2" onclick="borrowItem()" class="btn btn-primary">Borrow</button> <br>
   </c:when>
   <c:when test="${itemAccess == 'Hold'}">
      <button type="button" name = "button3" onclick="holdItem()" class="btn btn-primary">Hold</button> <br><br>
      <button type="button" name = "button1" onclick="recommendItemPage()" class="btn btn-primary">Recommend</button> <br>
   </c:when> 
   <c:when test="${itemAccess == 'isCheckedOut'}">
      <form action="http://localhost:8080/GoodLooksBooks/CustomerServlet">
         <input type="submit" class="btn btn-primary" value="Go to Checkouts">
      </form>
   </c:when>
   <c:otherwise>
      <button type="button" name = "button4" onclick="borrowItem()" class="btn btn-primary">Borrow</button> <br>
   </c:otherwise>
</c:choose>
      
<form id="borrowItemForm" action="ItemAccessServlet" method="doBorrow">
   <input type="hidden" name="isbn" value="${itemClicked.isbn}">
   <input type="hidden" name="method" value="doBorrow">
</form>

<form id="holdItemForm" action="ItemAccessServlet" method="requestHold">
   <input type="hidden" name="isbn" value="${itemClicked.isbn}">
   <input type="hidden" name="method" value="requestHold">
</form>
   

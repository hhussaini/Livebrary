<%-- 
    Document   : itemButtons
    Author     : Kevin Young
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<button type="button" name = "button1" onclick="sampleItem('${itemClicked.sampleUrl}')" class="btn btn-primary">Sample</button>
<c:choose>
   <c:when test="${itemAccess == 'reserve'}">
      <button type="button" name = "button1" onclick="reserveItem()" class="btn btn-primary">Reserve</button> <br>
   </c:when>
   <c:when test="${itemAccess == 'borrow'}">
      <button type="button" name = "button2" onclick="borrowItem()" class="btn btn-primary">Borrow</button> <br>
   </c:when>
   <c:when test="${itemAccess == 'hold'}">
      <button type="button" name = "button3" onclick="holdItem()" class="btn btn-primary">Hold</button> <br>
   </c:when>
   <c:when test="${itemAccess == 'isCheckedOut'}">
      <button type="button" name = "button3" onclick="" class="btn btn-primary">Is Checked Out</button> <br>
   </c:when>
   <c:otherwise>
      <button type="button" name = "button4" onclick="borrowFunction()" class="btn btn-primary">Borrow</button> <br>
   </c:otherwise>
</c:choose>
      
<form id="borrowItemForm" action="ItemAccessServlet" method="doBorrow">
   <input type="hidden" name="isbn" value="${itemClicked.isbn}">
   <input type="hidden" name="method" value="doBorrow">
</form>
   
<form id="reserveItemForm" action="ItemAccessServlet" method="doReserve">
   <input type="hidden" name="isbn" value="${itemClicked.isbn}">
   <input type="hidden" name="method" value="doReserve">
</form>

<form id="holdItemForm" action="ItemAccessServlet" method="doHold">
   <input type="hidden" name="isbn" value="${itemClicked.isbn}">
   <input type="hidden" name="method" value="doHold">
</form>
<%-- 
    Document   : itemButtons
    Author     : Kevin Young
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<button type="button" name = "button1" onclick="sampleFunction('${itemClicked.sampleUrl}')" class="btn btn-primary">Sample</button>
<c:choose>
   <c:when test="${itemAccess == 'reserve'}">
      <button type="button" name = "button1" onclick="reserveFunction()" class="btn btn-primary">Reserve</button> <br>
   </c:when>
   <c:when test="${itemAccess == 'borrow'}">
      <button type="button" name = "button2" onclick="borrowFunction()" class="btn btn-primary">Borrow</button> <br>
   </c:when>
   <c:when test="${itemAccess == 'hold'}">
      <button type="button" name = "button3" onclick="holdFunction()" class="btn btn-primary">Hold</button> <br>
   </c:when>
   <c:when test="${itemAccess == 'isCheckedOut'}">
      <button type="button" name = "button3" onclick="holdFunction()" class="btn btn-primary">Is Checked Out</button> <br>
   </c:when>
   <c:otherwise>
      <button type="button" name = "button4" onclick="borrowFunction()" class="btn btn-primary">Borrow</button> <br>
   </c:otherwise>
</c:choose>
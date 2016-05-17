<%-- 
    Document   : lendingPolicies
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
     <c:choose>
            <c:when test="${empty user}">
                <jsp:include page="/guestNavbar.jsp" />
            </c:when>
            <c:when test="${user.type == 'admin'}">
                <jsp:include page="/adminNavbar.jsp" />
            </c:when>
            <c:when test="${user.type == 'librarian'}">
                <jsp:include page="/librarianNavbar.jsp" />
            </c:when>
            <c:when test="${user.type == 'publisher'}">
                <jsp:include page="/publisherNavbar.jsp" />
            </c:when>
            <c:when test="${user.type == 'customer'}">
                <jsp:include page="/customerNavbar.jsp" />
            </c:when>
      </c:choose>
       <div class="glb-page">
         <jsp:include page="/logo.jsp" />
         <div class="container">
           <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Lending Policies</h3>
            </div>
            <div class="panel-body form-horizontal " >
               <h3><b>Borrowing</b></h3>
                  You can borrow up to 7 titles at a time by default. The lending period may vary from title to title. You will need an active account to borrow titles from this library.
                  <hr>
               <h3><b>How to change your lending periods</b></h3>
                  <ul>
                    <li>Sign in to your account.</li>
                    <li>Click the Settings link.</li>
                    <li>Select the desired lending period and Submit</li>
                  </ul>
                  <hr>
               <h3><b>Returning and renewing</b></h3>
                  Some titles can be returned before they expire if the title expires in less than 3 days and the title is not on any other user's Holds list.
                  <hr>
               <h3><b>Holds</b></h3>
               You can place any amount of titles on hold at a time. You’ll receive an email notification when a title you placed on hold becomes available. Once the email is sent, you’ll have 3 days (72 hours) to borrow the title before your hold is cancelled. Or, you can turn on automatic borrowing to make sure you never miss a hold.
                  <hr>
               <h3><b>Suspending holds</b></h3>
                  If you’re not ready for a hold to become available yet, you can temporarily suspend it by following these steps. You can suspend each hold for a minimum of 7 days and a maximum of 21 days.
                  <hr>
               <h3><b>How to cancel a hold</b></h3>
                  <ul>
                    <li>Sign in to your account.</li>
                    <li>Click the Holds link.</li>
                    <li>Click the Options button.</li>
                    <li>Select the Remove hold option.</li>
                  </ul>  
                  <hr>
               <h3><b>How to change your holds email address</b></h3>
                  <ul>
                     <li>Sign in to your account.</li>
                     <li>Click the Holds link.</li>
                     <li>Click the Options button.</li>
                     <li>Select the Edit email address option.</li>
                     <li>Enter your new email address.</li>
                     <li>Click Submit to update your email address for the selected title.</li>
                  </ul> 
            </div>
           </div>
         </div>
       </div>
     </body>
</html>
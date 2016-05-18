<%-- 
    Document   : privacyPolicy
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
              <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Privacy Policy</h3>
            </div>
            <div class="panel-body form-horizontal">
               <h3><b>Our services</b></h3>               
               Good Looks Books, Inc. and its affiliates (“GLB”, “we”, “us” or “our”) provides digital content, software applications, 
               technology services and hosted websites for third parties. This Privacy Policy applies to all GLB-hosted websites, 
               applications, technologies and services (collectively referred to as “Services”).
               GLB respects your privacy. This Privacy Policy discloses the privacy practices for GLB and all related Services. 
               The purpose of this Privacy Policy is to make you aware of how GLB collects, manages, protects, uses, and/or shares information 
               and what choices are available to you regarding the collection, use and distribution of your Personal Information.
               We will continue to evaluate this Privacy Policy against new technologies, business practices, and our users’ needs, 
               and may make changes to the Privacy Policy accordingly. Please check this page periodically for updates. 
               You acknowledge that your continued use of the Services after the posting of any changes to this Privacy 
               Policy means that you agree to be bound by such changes.
               <hr>
               <h3><b>How does Good Looks Books protect your Personal Information?</b></h3>
               Good Looks Books takes information security very seriously. We have implemented measures to protect against the loss, 
               misuse, and alteration of your Personal Information. Any Personal Information that we collect is protected by physical, 
               electronic, and procedural safeguards to prevent unauthorized disclosure. We encrypt the transmission of sensitive
               data we collect from visitors of our Services using secure sockets layer (SSL) technology. We use computer safeguards
               such as firewalls and data encryption and physical access controls to our buildings and files. We authorize access
               to Personal Information only for those employees who require it to fulfill their job responsibilities.
               GLB does not guarantee error-free performance under this Privacy Policy. GLB will use commercially reasonable
               efforts to comply with this Privacy Policy and will take prompt corrective action if GLB learns of any failure to 
               comply with it. GLB shall not be liable for any incidental, indirect, consequential or punitive damages or for loss 
               of profit or opportunity, loss of use or other financial loss arising out of or relating to this Privacy Policy. 
            </div>
          </div>
         </div>
     </body>
</html>

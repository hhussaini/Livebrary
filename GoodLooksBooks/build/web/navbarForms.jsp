<%-- 
    Document   : navbarForms
    Author     : Kevin Young
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form name = "aboutForm" action = "AboutServlet" method = "post"></form>
<form name = "accountForm" action = "AccountServlet" method = "post"></form>
<form name = "signOutForm" action = "SignOutServlet" method = "post"></form>
<form name = "customerFullCatalogForm" action = "CustomerFullCatalogServlet" method = "post"></form>

<!--Form for clicking on an "item" (book)-->
<form id = "itemSelectionForm" name = "itemSelectionForm" action = "UserBookDescriptionServlet" method = "post">
    <input type = "hidden" id = "hiddenFormID" name = "hiddenFormID" value = "null">
</form> 
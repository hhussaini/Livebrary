<%-- 
    Document   : userBookDescription
    Author     : Kevin Young
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">  
<head>
  <title>Better Looks Books</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/custom_css/stylesheet.css">
  <link rel="stylesheet" href="css/custom_css/bookPhotoGrid.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="js/bootstrap.js"></script>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.js"></script>
  <!--link to custom javascript page-->
  <script src="js/bookDescription.js" type="text/javascript"></script>
</head>
  <body>
    <jsp:include page="/navbar.jsp" />
    <div class="blb-page">
      <jsp:include page="/logo.jsp" />
      <div class="bookDescription container">
        <div class="col-xs-6 col-sm-7" style="border-style: groove;">       
            <h5 id="bookTitleID">Book Title:  ${itemClicked.title}</h5>
            <br> <h5 id="bookAuthorID">Book Author: ${itemClicked.author}</h5>
            <br> <h5 id="bookDescriptionID">Book Description: ${itemClicked.description}</h5>
            <br> <h5 id="dateCreatedID">Date Created: ${itemClicked.date}</h5>
            <br> <h5 id="availableFormatsID">Available Formats: Kindle</h5>
            <br> <h5 id="languageID" value = "${itemClicked.language}">Language: ${itemClicked.language}</h5>
            <br> <h5>Subjects: "Military prisons", "United States -- History -- Civil War, 1861-1865 -- Prisoners and prisons", "Andersonville Prison", "Confederate States of America. Army -- Prisons" </h5>
        </div>
        <div class="col-xs-6 col-sm-5 bookDescriptionPictures">
          <img src = ${itemClicked.imageUrl} class="img-rounded" alt="Andersonville: A Story of Rebel Military Prisons" width="250" height="250">
          <br>
          <button type="button" onclick="confirmDownload('${user.username}', '${itemClicked.downloadUrl}')" class="btn btn-primary">Confirm Download</button> <br>
          <button type="button" onclick="confirmReturn('${user.username}' ${itemClicked.isbn})" class="btn btn-primary">Confirm Return</button> <br>
        </div>
      </div> <!-- bookDescription-->
      <hr class="fancy">
      <br>
      <br><br><br>
      <hr class="fancy">
      <br>
    </div> <!-- BLBPage -->
  </body>
 </html>
 
 <form id = "confirmReturnForm" name = "confirmReturnForm" action = "ConfirmActionServlet" method = "post">
   <input type="hidden" name = "action" value="download" </h5> 
 </form>
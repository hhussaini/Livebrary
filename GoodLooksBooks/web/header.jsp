<%-- 
    Document   : newjsp
    Author     : Kevin Young
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<title>Good Looks Books</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom_css/stylesheet.css">
<link rel="stylesheet" href="css/custom_css/bookPhotoGrid.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/bookDescription.js" type="text/javascript"></script>
    
<c:if test="${user.dyslexic == 'true'}">
    <link rel="stylesheet" href="css/custom_css/dyslexic_font.css" type="text/css">
</c:if>
    
<c:if test="${user.contrast == 'dark'}">
    <link rel="stylesheet" href="css/custom_css/dark_contrast.css">
</c:if>
    
<%-- 
    Document   : publisherIndex
    Author     : Kevin Young
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Good Looks Books</title>
    <mytags:header/>
</head>
<body>
    <mytags:publisherNavbar/>
    <div class="glb-page">
        <mytags:logo/>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Publisher Options</h3>
            </div>
            <div class="panel-body">
                <div class="list-group">
                    <a href="editItems.html"><img alt="brand" width = "100" height = "100" src="assets/edit-books-icon.jpg"></a>
                    Edit Item Data
                </div>
            </div>
        </div>
    </div>
   </div>
</body>
</html>

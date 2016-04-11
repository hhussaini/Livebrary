<%-- 
    Document   : publisherIndex
    Author     : Kevin Young
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Good Looks Books</title>
    <jsp:include page="/header.jsp" />
</head>
<body>
    <jsp:include page="/publisherNavbar.jsp" />
    <div class="glb-page">
        <jsp:include page="/logo.jsp" />
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

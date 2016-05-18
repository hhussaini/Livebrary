<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
<script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
<link href="https://cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="js/data-tables.js"></script>
<script src="js/data-table-plugin.js"></script>
<br>
<table class="results-table results-list table table-striped" id="rated-list" style="top: 800px;">
    <thead>
        <tr>
            <th></th>
            <th>Title</th>
            <th>Author</th>
            <th>Published</th>
            <th>Copies</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${ratedItems}" varStatus="status">
            <tr>
                <td>
                    <a href="BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail">
                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book${count}" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 100px;">
                    </a>
                </td>
                <td><a id="${item.isbn}" href="BookDescriptionServlet?isbn=${item.isbn}">${item.title}</a></td>
                <td>${item.author}</td>
                <td>${item.date}</td>
                <td>${item.copiesLeft}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
    

<script>$(document).ready(function() {
    $('#rated-list').dataTable();
});</script>
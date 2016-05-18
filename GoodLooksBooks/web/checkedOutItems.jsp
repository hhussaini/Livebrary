<%-- 
    Document   : checkedOutItems
    Author     : Kevin Young
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
    
<br>
<table class="results-table results-list table table-striped" id="checked-out-table" style="top: 800px;">
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
        <c:forEach var="item" items="${checkedOutItems}" varStatus="status">
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
                <td>
                    <div class="btn-group">
                        <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Options
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#" onclick="downloadBook(${item.isbn})">Download</a><br>
                            <a class="dropdown-item" href="#" onclick="returnBook(${item.isbn})">Return</a><br>
                            <a class="dropdown-item" href="#" onclick="readBook('${item.downloadUrl}')">Read (in your browser)</a>
                            <form id="renewItemForm" action="ItemAccessServlet" method="doRenew">
                                <input type="hidden" name="isbn" value="${item.isbn}">
                                <input type="hidden" name="method" value="doRenew">
                                <input type="submit" value="Renew">
                            </form>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
    
    
<script>$(document).ready(function() {
    $('#checked-out-table').dataTable();
});</script>
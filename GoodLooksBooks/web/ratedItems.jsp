<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
<script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
<link href="https://cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="js/data-tables.js"></script>
<script src="js/data-table-plugin.js"></script>
<br>
<table class="results-table tablesorter" id="cutomerList">
    <tr>
        <c:forEach var="item" items="${ratedItems}" varStatus="status">
            <c:if test="${status.index != 0 && status.index % 4 == 0}">
            <tr>
            </tr>
            </c:if>
            <td>
                <div class="col-xs-6 col-md-3"> 
                    <td> 
                        <a href = "BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail" style = "width: 200px;">
                            <img onload="validateImgUrl(this.id)" name="bookImage" id="book${count}" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 200px;">
                            <p>${item.title}</p>
                            <h6>by</h6>
                            <h5>${item.author}</h5>
                        </a>
                    </td>
                </div>
            </td>  
        </c:forEach>
    </tbody>
</table>
    

<script>$(document).ready(function() {
    $('#rated-list').dataTable();
});</script>

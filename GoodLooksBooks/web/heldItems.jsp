<%-- 
    Document   : heldItems
    Author     : Kevin Young
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
   <script src="js/holdsScript.js" type="text/javascript"></script>
</head>
<table>
   <tr>
       <c:forEach var="item" items="${onHoldItems}" varStatus="status">
           <c:if test="${status.index != 0 && status.index % 3 == 0}">
           </tr>
           <tr>
           </c:if>
           <td>
               <div class="col-xs-6 col-md-3"> 
                   <td> 
                       <a href = "BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail">
                           <img onload="validateImgUrl(this.id)" name="bookImage" id="book${count}" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 200px;">
                           <p>${item.title}</p>
                           <h6>by</h6>
                           <h5>${item.author}</h5>
                        </a>
                        <div class="btn-group">
                           <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                             Options
                           </button>
                           <div class="dropdown-menu">
                              <a class="dropdown-item" href="#" onclick="showEditEmailModal()">Edit email</a>
                              <a class="dropdown-item" href="#" onclick="downloadBook(${item.isbn})">Suspend Hold</a>
                              <a class="dropdown-item" href="#" onclick="downloadBook(${item.isbn})">Auto checkout</a>
                              <a class="dropdown-item" href="#" onclick="downloadBook(${item.isbn})">Remove</a>
                           </div>
                        </div>
                   </td>
               </div>
               <div class="modal fade" id="editEmailModal" tabindex="-1" role="dialog" aria-labelledby="editEmailModal" aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="contactModal">Edit Email</h4>
                      </div>
                      <form id="holdsForm" name="holdsForm" action="HoldsServlet" method="editEmail">  
                        <div class="modal-body">
                          <input type="email" class="form-control" placeholder="Email" name="email" autofocus />
                        </div>
                        <div class="modal-footer">
                          <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <input type="hidden" name="isbn" value ="${item.isbn}" />
                        <input type="hidden" name="method" value ="editEmail" />
                      </form>
                    </div>
                  </div>
               </div>
           </td>  
       </c:forEach>
   </tr>
</table>


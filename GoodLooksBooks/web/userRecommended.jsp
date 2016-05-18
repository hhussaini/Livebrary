<%-- 
    Document   : userRecommended
    Created on : May 18, 2016, 8:27:58 AM
    Author     : Hamza
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<br>
                    <table class="results-table">
                        <tr>
                            <c:forEach var="item" items="${recommendedMap}" varStatus="status">
                                <c:if test="${status.index != 0 && status.index % 2 == 0}">
                                </tr>
                                <tr>
                                </c:if>
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=${item.key}" id="${item.key}" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="item.key" class="bookImage" src="${item.value.imageUrl}" alt="${item.value.title}" style="width: 200px;">
                                                <p>${item.value.title}</p>
                                                <h6>by</h6>
                                                <h5>${item.value.author}</h5>
                                            </a>
                                        </td> 
    
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tr>
                    </table>
              
     
  


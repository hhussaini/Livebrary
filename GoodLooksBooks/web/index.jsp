<%-- 
    Document   : index
    Author     : Kevin Young
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="/header.jsp" />
      <script src="js/script.js" type="text/javascript"></script>
   </head>
   <body>
     <jsp:include page="/guestNavbar.jsp" />
     <div class="glb-page">
       <jsp:include page="/logo.jsp" />
       <div class="container">
         <div class="row">
           <div class="col-sm-6">
             <div class="heading-box">
               Need an ebook?
             </div>
             &nbsp;<br>
             <p class="center">Your favorite literature is right at your finger tips, all you need is an account with Good Looks Books.
             </p>
           </div>
           <div class="col-sm-6">
             <div class="heading-box">
               Catalog Access
             </div>
             &nbsp;<br>
             <p class="center">With or without and account you can begin <a href="SearchServlet">searching</a> for a book, an author, or a genre</p>
             &nbsp;<br>
             &nbsp;<br>
           </div>
         </div>
           <ul id="tabs" class="nav nav-tabs">
            <li class="active"><a href="#all-wish" data-toggle="tab">BestSellers</a></li>
            </ul>
           <div id="tabContent" class="tab-content">
               <c:choose>
          <c:when test="${fn:length(bestSellerlist) > 0}">
                    <table class="results-table">
                    
                            <c:forEach var="item" items="${bestSellerlist}" varStatus="status">
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="item.isbn" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 100px;">
                                                <p>${item.title}</p>
                                                <h6>by</h6>
                                                <h5>${item.author}</h5>
                                            </a>
                                        </td>
    
                                    </div>
                                </td>
                           
                        </c:forEach>
                        </tr>
                    </table>
                </c:when>
           </c:choose>
           </div>
           
           
           <ul id="tabs" class="nav nav-tabs">
            <li class="active"><a href="#all-wish" data-toggle="tab">Most Recommended</a></li>
            </ul>
           <div id="tabContent" class="tab-content">
               <c:choose>
          <c:when test="${fn:length(recommendedMap) > 0}">
                    <table class="results-table">
                        
                            <c:forEach var="item" items="${recommendedMap}" varStatus="status">
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=${item.key}" id="${item.key}" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="item.key" class="bookImage" src="${item.value.imageUrl}" alt="${item.value.title}" style="width: 100px;">
                                                <p>${item.value.title}</p>
                                                <h6>by</h6>
                                                <h5>${item.value.author}</h5>
                                            </a>
                                        </td> 
    
                                    </div>
                                </td>
                            
                        </c:forEach>
                        </tr>
                    </table>
                </c:when>
           </c:choose>
           </div>
           
           
           <ul id="tabs" class="nav nav-tabs">
            <li class="active"><a href="#all-wish" data-toggle="tab">Newest Books</a></li>
            </ul>
           <div id="tabContent" class="tab-content">
               <c:choose>
          <c:when test="${fn:length(newBooklist) > 0}">
                    <table class="results-table">
                   
                            <c:forEach var="item" items="${newBooklist}" varStatus="status">
                                
                               
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=${item.isbn}" id="${item.isbn}" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="item.isbn" class="bookImage" src="${item.imageUrl}" alt="${item.title}" style="width: 100px;">
                                                <p>${item.title}</p>
                                                <h6>by</h6>
                                                <h5>${item.author}</h5>
                                            </a>
                                        </td>
    
                                    </div>
                                </td>
                               
                         
                        </c:forEach>
                        </tr>
                    </table>
                </c:when>
           </c:choose>
           </div>
           
           <div>
           <div class="col-sm-15">
             <div class="heading-box">
               Advertisement
             </div>
             <div id="sidebar">
               <p class="center">
                  <script>
                     displayAds();
                  </script>
               </p>
            </div>
           </div>
         </div>
       </div>
     </div>
   </body>
</html>

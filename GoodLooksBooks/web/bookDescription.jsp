<%-- 
   Document   : bookDescription
   Author     : Kevin_Setayesh
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/header.jsp" />   
        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css" rel="stylesheet"/>
        <link href="css/rating/star-rating.css" media="all" rel="stylesheet" type="text/css" />
        <link href="css/custom_css/star.css" media="all" rel="stylesheet" type="text/css" />
        <link href="css/rating/theme-krajee-svg.css" media="all" rel="stylesheet" type="text/css" />
        <script src="js/rating/star-rating.js" type="text/javascript"></script> 
        <!-- star rating-->
        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css" rel="stylesheet"/>
        <link href="css/rating/star-rating.css" media="all" rel="stylesheet" type="text/css" />
        <link href="css/custom_css/star.css" media="all" rel="stylesheet" type="text/css" />
        <!-- optionally if you need to use a theme, then include the theme file as mentioned below -->
        <link href="css/rating/theme-krajee-svg.css" media="all" rel="stylesheet" type="text/css" />
        <script src="js/rating/star-rating.js" type="text/javascript"></script>
        <!--<script src="jquery-1.12.2.min.js"></script>-->
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>-->
    </head>
    <body>
        <c:choose>
            <c:when test="${empty user}">
                <jsp:include page="/guestNavbar.jsp" />
            </c:when>
            <c:when test="${user.type == 'admin'}">
                <jsp:include page="/adminNavbar.jsp" />
            </c:when>
            <c:when test="${user.type == 'librarian'}">
                <jsp:include page="/librarianNavbar.jsp" />
            </c:when>
            <c:when test="${user.type == 'publisher'}">
                <jsp:include page="/publisherNavbar.jsp" />
            </c:when>
            <c:when test="${user.type == 'customer'}">
                <jsp:include page="/customerNavbar.jsp" />
            </c:when>
        </c:choose>
        <jsp:include page="/logo.jsp" />
        <div class="bookDescription container">
            <div>
                <div class="col-xs-6 col-sm-7" style="border-style: groove;">
                   <div class="btn-group">
                       <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                         Share
                       </button>
                     <div class="dropdown-menu">
                       <a class="dropdown-item" href="#" 
                          onclick="fbShare('http://openisbn.com/isbn/${itemClicked.isbn}', 520, 350)">
                            <img border="0" width ="50px" height = "50px" src="assets/facebook-icon.jpg" title="Facebook" />
                       </a>
                       <a class="dropdown-item" 
                           href="https://twitter.com/intent/tweet?text=Check%20out%20${itemClicked.title}%20from%20Good%20Looks%20Books!%20http://openisbn.com/isbn/${itemClicked.isbn}"
                           data-size="large">
                           <img border="0" width ="50px" height = "50px" src="assets/twitter-icon.png" title="Twitter" />
                       </a>
                       <a 
                           href="http://pinterest.com/pin/create/button/?url=http://openisbn.com/isbn/${itemClicked.isbn}&media=${itemClicked.imageUrl}&description=${itemClicked.title}" class="pin-it-button" count-layout="horizontal">
                           <img border="0" src="//assets.pinterest.com/images/PinExt.png" title="Pin It" />
                       </a>
                    </div>
                   </div>
                    <button id="emailButton" type="button" class="btn btn-primary"
                            onclick="showEmailModal()">Email</button>
                    <h5 id="bookTitleID">Title:  ${itemClicked.title}</h5>
                    <br> 
                    <h5 id="bookAuthorID">Author: ${itemClicked.author}</h5>
                    <br> 
                    <h5 id="bookDescriptionID">Description: ${itemClicked.description}</h5>
                    <br> 
                    <h5 id="dateCreatedID">Published: ${itemClicked.date}</h5>
                    <br> 
                    <h5 id="copiesAvailableID">Copies Available: ${itemClicked.copiesLeft}</h5>
                    <br> 
                    <h5 id="availableFormatsID">Available Formats: ${itemClicked.type}</h5>
                    <br> 
                    <h5 id="genresID">Genre: ${itemClicked.genres}</h5>
                    <br>
                    <h5 id="languageID" value = "${itemClicked.language}">Language: ${itemClicked.language}</h5>
                    <br>
                    <h5 id="rightsID">Rights Information: This item was gathered from scraping the Openisbn.com website</h5>
                    <br> 
                </div>
                <div class="col-xs-6 col-sm-5 bookDescriptionPictures">
                    <img src = ${itemClicked.imageUrl} class="img-rounded" alt="Andersonville: A Story of Rebel Military Prisons" height="250">
                    <div>
                        Avg. Rating:&nbsp;&nbsp;&nbsp; 
                        <label id="avgRatingText" for="input-3" class="control-label">
                            <c:out value="${itemClicked.avgRating}"/>
                            /5
                        </label>
                        <br>
                        <div id="avgStarID" value="${itemClicked.avgRating}">
                            <!-- <img id="clip" src="assets/yellowStar.png" />--> 
                        </div>
                        <br><br>
                        <br> 
                    </div>
                    <jsp:include page="/itemButtons.jsp" />
                    <c:if test="${user.type == 'admin'}">
                        <button type="button" id="banButtonID" action="BanItemServlet" name = "button5" onclick="banFunction(${itemClicked.isBanned ? 1 : 0})" class="btn btn-primary" value="">${itemClicked.isBanned ? 'Unban Book' : 'Ban Book'}</button>
                         <br>   
                        <select id="licenseNumbers">  
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                           
                        </select>
                            
                          <button type="button" id="buylicense" name = "button6" class="btn btn-primary" value="" onclick="numOfLicense()">Buy License</button>   
                    </c:if>
                    <br>
                    <c:choose>
                        <c:when test="${fullWishlist.contains(itemClicked.isbn)}">
                            <form action="WishlistServlet" method = "delete">
                                <button type="submit" class="btn btn-danger">Remove from Wishlist</button>
                                <input type="hidden" name="isbn" value="${itemClicked.isbn}">
                                <input type="hidden" name="method" value="delete">
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="WishlistServlet" method = "post">
                                <button type="submit" class="btn btn-primary">Add to Wishlist</button>
                                <input type="hidden" name="isbn" value="${itemClicked.isbn}">
                            </form>
                        </c:otherwise>
                    </c:choose> 
                </div>
        </div>
        <!--Review an item-->
        <!--        <div><font color="white" size="100">.</font>  Some random filler-->
        <!--Review Item--> 
        <!--            <div id="submittingReviewID" style = "width: 75%;">
           <div id="submittingReviewInnerID"  style="display: ${user.type == 'customer' && itemClicked.reviews[user.username] == null ? 'block;' : 'none;'}">
               <div id="userRatingID" style="display: none;"> <label>
                   <label for="input-2" class="control-label">Your Rating</label><br>
                       
                       <img onclick="starRating(1)" value = "0" id="star1" src="assets/star.png" /> 
                       <img onclick="starRating(2)" value = "0" id="star2" src="assets/star.png" /> 
                       <img onclick="starRating(3)" value = "0" id="star3" src="assets/star.png" /> 
                       <img onclick="starRating(4)" value = "0" id="star4" src="assets/star.png" /> 
                       <img onclick="starRating(5)" value = "0" id="star5" src="assets/star.png" /> 
                       &nbsp;&nbsp;&nbsp;
                       <font size ="4" id="starDescriptionID" style="float:right;"></font>
                   </label>
               </div>
               <textarea class="review collapse" id="reviewdetails" name="review" placeholder="Describe your review...">
               </textarea>
               <button class="btn btn-success" id="reviewBtn" onClick = "addReview()" style="display: none;">Submit</button>
               <br>
               <button type="button" onClick="showSubmit()" class="btn btn-info" data-toggle="collapse" data-target="#reviewdetails, #userRatingID">Review this item</button>
           </div>
           </div>
        -->
        <!--        </form>-->
        <br><br><br><br><br> 
        <!--        </div>-->
        <!--        <hr class="fancy">-->
        <!--All review-->
        <div  class="col-xs-12 col-sm-12" >
            <hr class="fancy">
            <div id="submittingReviewID" style = "width: 75%;">
                <div id="submittingReviewInnerID"  style="display: ${user.type == 'customer' && itemClicked.reviews[user.username] == null ? 'block;' : 'none;'}">
                    <div id="userRatingID" style="display: none;"> <label>
                            <label for="input-2" class="control-label">Your Rating</label><br>
                            <img onclick="starRating(1)" value = "0" id="star1" src="assets/star.png" /> 
                            <img onclick="starRating(2)" value = "0" id="star2" src="assets/star.png" /> 
                            <img onclick="starRating(3)" value = "0" id="star3" src="assets/star.png" /> 
                            <img onclick="starRating(4)" value = "0" id="star4" src="assets/star.png" /> 
                            <img onclick="starRating(5)" value = "0" id="star5" src="assets/star.png" /> 
                            &nbsp;&nbsp;&nbsp;
                            <font size ="4" id="starDescriptionID" style="float:right;"></font>
                        </label>
                    </div>
                    <textarea class="review collapse" id="reviewdetails" name="review" placeholder="Describe your review..."></textarea>
                    <button class="btn btn-success" id="reviewBtn" onClick = "addReview()" style="display: none;">Submit</button>
                    <br>
                    <button type="button" onClick="showSubmit()" class="btn btn-info" data-toggle="collapse" data-target="#reviewdetails, #userRatingID">Review this item</button>
                </div>
            </div>
            <h2>Reviews</h2>
            <div id="allReviews">
                <!--current user logged in review-->
                <div id= "reviewContainer_0" class="col-xs-12 col-sm-12" style="border-style: groove; display: none;">
                    <button type="button" id="edit-review-btn" onClick="editReview()" class="btn btn-info">Resubmit/edit review</button>
                    &nbsp;&nbsp;
                    <button class="btn btn-danger" id="remove-review" onClick="removeReview(this.id)">Remove review</button>
                    <br><br>                
                    <div id="topReviewContainerID_0">
                        <div id="loggedInRatingID_0" value = "">              
                        </div>
                        <br> 
                    </div>
                    <br>
                    <h4 id="userReviewName_0">By: </h4>
                    <h5 id="reviewText_0"></h5>
                    <br>
                </div>
                <!--All review besides current users review-->
                <c:set var="flag" scope="session" value="false"/>
                <c:forEach var="entry" items="${itemClicked.reviews}" varStatus="i">
                    <c:choose>
                        <c:when test="${entry.key == user.username}">
                            <p id="hiddenElement1" value="${entry.key}"></p>
                            <p id="hiddenElement2" value="${itemClicked.reviews[entry.key].rating}"></p>
                            <p id="hiddenElement3" value="${entry.value.reviewText}"></p>
                            <c:set var="flag" scope="session" value="true"/>
                        </c:when>
                        <c:otherwise>
                            <div id= "reviewContainer_${flag? i.index : i.index + 1}" class="col-xs-12 col-sm-12" style="border-style: groove; display: initial;">
                                <div id="topReviewContainerID_${flag? i.index : i.index + 1}">
                                    <div id="loggedInRatingID_${flag? i.index : i.index + 1}" value = "${itemClicked.reviews[entry.key].rating}">              
                                    </div>
                                    <br> 
                                </div>
                                <br>
                                <h4 id="userReviewName_${flag? i.index : i.index + 1}">By: ${entry.key}</h4>
                                <h5 id="reviewText_${flag? i.index : i.index + 1}">${entry.value.reviewText}</h5>
                                <br>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="modal fade" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="emailModal" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="emailModal">Email Item</h4>
                </div>
                <form id="emailForm" name="emailForm" action="EmailItemServlet" method="post">
                    <div class="modal-body">
                        <input type="text" class="form-control" placeholder="To" name="recipient" required autofocus /><br>
                        <input type="text" class="form-control" placeholder="From" name="from" required autofocus /><br>
                        <p>Subject: Check out this downloadable title at GoodLooksBooks</p>
                        <br>
                        <input type="text" class="form-control" placeholder="Message" name="message" autofocus />
                        <input type="hidden" name="subject" value="Check out this downloadable title at GoodLooksBooks">
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Send</button>
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>

<!--Form for communicating with the second server -->
<form id = "secondServerForm" name = "secondServerForm" action = "SecondServerServlet" method = "post"></form>
<form id = "facebookShareForm" name = "facebookShareForm" action = "SecondServerServlet" method = "post"></form>
<form id = "banButtomForm" name = "banButtomForm" action = "BanItemServlet" method = "post"></form>
<form id= "licenseForm" name="licenseForm" value="-1" action="BuyLicenseServlet" method = "post">
    <input id="numOfCopies" name="numOfCopies" value="-1" type="hidden">
</form>
<input type="hidden" name = "isbn" id = "isbn" value="${itemClicked.isbn}" </h5>
                       

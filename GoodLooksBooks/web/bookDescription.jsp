<%-- 
    Document   : bookDescription
    Author     : Kevin_Setayesh
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <title>Full Catalog for Good Looks Books</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/custom_css/stylesheet.css">
    <link rel="stylesheet" href="css/custom_css/bookPhotoGrid.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="js/bootstrap.js"></script>            
    <!-- star rating-->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css" rel="stylesheet"/>
    <link href="css/rating/star-rating.css" media="all" rel="stylesheet" type="text/css" />  
    <link href="css/custom_css/star.css" media="all" rel="stylesheet" type="text/css" />
    <!-- optionally if you need to use a theme, then include the theme file as mentioned below -->
    <link href="css/rating/theme-krajee-svg.css" media="all" rel="stylesheet" type="text/css" />
    <script src="js/rating/star-rating.js" type="text/javascript"></script>            
    <!-- optionally if you need translation for your language then include locale file as mentioned below -->
    <script src="js/rating/star-rating_locale_<lang>.js"></script>            
    <!--link to custom javascript page-->
    <script src="js/bookDescription.js" type="text/javascript"></script>
</head>
<body>
    <c:choose>
        <c:when test="${empty user}">
            <jsp:include page="/guestNavbar.jsp" />
        </c:when>
        <c:when test="${user.type == 'customer'}">
            <jsp:include page="/customerNavbar.jsp" />
        </c:when>
    </c:choose>
    <jsp:include page="/logo.jsp" />
    <div class="bookDescription container">
        <div class="col-xs-6 col-sm-7" style="border-style: groove;">
          <button type="button" class="btn btn-primary" 
                  onclick="fbShare('http://openisbn.com/${itemClicked.isbn}', 520, 350)">
              Share</button>
          <button id="emailButton" type="button" class="btn btn-primary">Email</button>
          <form id="bookDescriptionForm" action="ItemReserveServlet" method="get"> 
                <h5 id="bookTitleID">Book Title:  ${itemClicked.title}</h5>
                <br> <h5 id="bookAuthorID">Book Author: ${itemClicked.author}</h5>
                <br> <h5 id="bookDescriptionID">Book Description: ${itemClicked.description}</h5>
                <br> <h5 id="dateCreatedID">Date Created: ${itemClicked.date}</h5>
                <br> <h5 id="copiesAvailableID">Copies Available: ${itemClicked.numOfDownloads}</h5>
                <br> <h5 id="availableFormatsID">Available Formats: Kindle</h5>
                <br> <h5 id="languageID" value = "${itemClicked.language}">Language: ${itemClicked.language}</h5>
                <br> <input type="hidden" name = "isbn" id = "isbn" value="${itemClicked.isbn}" </h5>
          </form>
        </div>
        <div class="col-xs-6 col-sm-5 bookDescriptionPictures">
            <img src = ${itemClicked.imageUrl} class="img-rounded" alt="Andersonville: A Story of Rebel Military Prisons" width="250" height="250">
            <button type="submit" id="fav" onclick="setColor('fav','#FF0000');" class="btn-fav"><span class="glyphicon glyphicon-heart"></span> Fav!</button>
            <div> 
                Avg. Rating:&nbsp;&nbsp;&nbsp; <label id="avgRatingText" for="input-3" class="control-label"><c:out value="${itemClicked.avgRating}"/>/5</label>
                <br>
                <div id="avgStarID" value="${itemClicked.avgRating}">
<!--                    <img id="clip" src="assets/yellowStar.png" />--> 
                </div>
                <br><br>
             
                <br> 
            </div>
            <button type="button" name = "button1" onclick="sampleFunction()" class="btn btn-primary">Sample</button>
            <button type="button" name = "button2" onclick="buyItNowFunction(${itemClicked.isbn})"class="btn btn-primary">Buy It Now</button> <br>
            <button type="button" name = "button3" onclick="borrowFuntion()"class="btn btn-primary">Borrow</button>
            <button type="button" name = "button4" onclick="reserveFunction()"class="btn btn-primary">Reserve</button> <br>
            <br>
            <form action="WishlistServlet" method = "post">
                <button onclick="changeWishlist()" type="submit" class="btn btn-primary">Add to Wishlist</button>
                <input type="hidden" name="isbn" value="${itemClicked.isbn}">
            </form> 
    
        </div>
             
            <!--Review an item-->
            
            <div><font color="white" size="100">.</font>  <!--Some random filler-->
                <hr class="fancy">
        <br>
<!--        <form id="submitReviewForm" action="SubmitItemReviewServlet" method="post"> -->
             
            <c:if test="${user.type == 'customer' && itemClicked.reviews[user.username] == null}" >
            <div id="submittingReviewID" class="container" style = "width: 75%;">
                <div id="userRatingID" style="display: none;">
                <label for="input-2" class="control-label">Your Rating</label><br>
                <label>
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
                <button class="btn btn-success" id="reviewBtn" onClick = "submitReview()" style="display: none;">Submit</button>
                <br>
                <button type="button" onClick="showSubmit()" class="btn btn-info" data-toggle="collapse" data-target="#reviewdetails">Review this item</button>
            </div>
            </c:if>
<!--        </form>-->
        <br><br><br> 
        </div>
        <hr class="fancy">
        <br>
        
        
        <div class="col-xs-12 col-sm-12" style="border-style: groove;">
            <button type="button" id="edit-review-btn" class="btn btn-info">Resubmit/edit review</button>
            &nbsp;&nbsp;
            <button class="btn btn-danger" onClick="resetReview()" id="remove-review">Remove review</button>
            <br><br>
          <input id="input-3" value="3" class="rating-md" style="font-size: 1.0em">
          <label for="input-3" class="control-label"> It was OK </label>
          <h4>By: </h4><h4 id="usernameTextID"></h4>
            <h5 id="userReviewTextID"></h5>
            <br> 
        </div>

          <!--Item Reviews-->
          <c:forEach var="entry" items="${itemClicked.reviews}">
          <div class="col-xs-12 col-sm-12" style="border-style: groove;">
            <button type="button" id="edit-review-btn" class="btn btn-info">Resubmit/edit review</button>
            &nbsp;&nbsp;
            <button class="btn btn-danger" onClick="resetReview()" id="remove-review">Remove review</button>
            <br><br>
          <input id="input-3" value="3" class="rating-md" style="font-size: 1.0em">
          <label for="input-3" class="control-label"> It was OK </label>
            <h4>By: ${entry.key}</h4>
            <h5><c:out value="${entry.value.reviewText}"/></h5>
            <br>  
            </div> 
          <br>
          </c:forEach>
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
                <p>Subject: Check out this downloadable title at GoodLooksBooks</p><br>
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

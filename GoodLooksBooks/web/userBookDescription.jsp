<%-- 
    Document   : userBookDescription
    Author     : Kevin_Setayesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">  
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
        
        <!-- optionally if you need to use a theme, then include the theme file as mentioned below -->
        <link href="css/rating/theme-krajee-svg.css" media="all" rel="stylesheet" type="text/css" />
        
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.js"></script>
        <script src="js/rating/star-rating.js" type="text/javascript"></script>
        
        <!-- optionally if you need translation for your language then include locale file as mentioned below -->
        <script src="js/rating/star-rating_locale_<lang>.js"></script>
        
        <!--link to custom javascript page-->
        <script src="js/userBookDescription.js" type="text/javascript"></script>
    </head>    
    <body>
     <jsp:include page="/customerNavbar.jsp" />
     <jsp:include page="/logo.jsp" />
      <div class="bookDescription container">
        <div class="col-xs-6 col-sm-7" style="border-style: groove;">
          <button type="button" class="btn btn-primary">Share</button>
          <button type="button" class="btn btn-primary">Email</button>
         
          <form id="bookDescriptionForm" action="ItemReserveServlet" method="get"> 
                <h5 id="bookTitleID">Book Title:  ${itemClicked.title}</h5>
                <br> <h5 id="bookAuthorID">Book Author: ${itemClicked.author}</h5>
                <br> <h5 id="bookDescriptionID">Book Description: ${itemClicked.description}</h5>
                <br> <h5 id="dateCreatedID">Date Created: ${itemClicked.date}</h5>
                <br> <h5 id="copiesAvailableID">Copies Available: ${itemClicked.numOfDownloads}</h5>
                <br> <h5 id="availableFormatsID">Available Formats: Kindle</h5>
                <br> <h5 id="languageID" value = "${itemClicked.language}">Language: ${itemClicked.language}</h5>
                <br> <input type="hidden" name = "isbn" id = "isbn" value="${itemClicked.isbn}" </h5> 
                <br> <h5>Subjects: "Military prisons", "United States -- History -- Civil War, 1861-1865 -- Prisoners and prisons", "Andersonville Prison", "Confederate States of America. Army -- Prisons" </h5>
          </form>  
        </div>
        <div class="col-xs-6 col-sm-5 bookDescriptionPictures">
          <!--<img src="https://www.gutenberg.org/cache/epub/32101/pg32101.cover.medium.jpg" class="img-rounded" alt="Andersonville: A Story of Rebel Military Prisons" width="250" height="250">-->
          <img src = ${itemClicked.imageUrl} class="img-rounded" alt="Andersonville: A Story of Rebel Military Prisons" width="250" height="250">
          <script>
//          function setColor(btn,color){
//            var property = document.getElementById(btn);
//            if (window.getComputedStyle(property).backgroundColor === 'rgb(225, 225, 225)') {
//              property.style.backgroundColor=color;
//            }
//            else {
//              property.style.backgroundColor = "#E1E1E1";
//            }
//          }
          </script>
          <button type="submit" id="fav" onclick="setColor('fav','#FF0000');" class="btn-fav"><span class="glyphicon glyphicon-heart"></span> Fav!</button>
          <!-- <script>
          function toggleFav() {
            var fav = document.getElementById("fav").innerHTML,
            style = window.getComputedStyle(fav),
            color = style.getPropertyValue('background-color');
            if (color == "#FF0000") {
              document.getElementById("fav").style.backgroundColor = "#FFFFFF";
            } else {
              document.getElementById("fav").style.backgroundColor = "#FF0000";
            }
          }
          </script> -->
          <br>
          <label for="input-3" class="control-label">Avg. Rating</label>
          <input id="input-3" value="5" class="rating-md" style="font-size: 2.5em">
          <br> 
          <label for="input-2" class="control-label">Your Rating</label>
          <input id="input-2" class="rating rating-loading" value="4" data-min="0" data-max="5" data-step="1">
          <script>
//          $(document).on('ready', function(){
//            $('#input-3').rating({displayOnly: true, step: 0.5});
//          });
          </script>
          <br>
          <button type="button" name = "button1" onclick="sampleFunction()" class="btn btn-primary">Sample</button>
          <button type="button" name = "button2" onclick="buyItNowFunction(${itemClicked.isbn})"class="btn btn-primary">Buy It Now</button> <br>
          <button type="button" name = "button3" onclick="borrowFuntion()"class="btn btn-primary">Borrow</button>
          <button type="button" name = "button4" onclick="reserveFunction()"class="btn btn-primary">Reserve</button> <br>
          <br>
          <button onclick="changeWishlist()" id="wishlist" type="button" class="btn btn-primary">Add to Wishlist</button>
          <script>
//          function changeWishlist() {
//            var text = document.getElementById("wishlist").innerHTML;
//            if (text === "Add to Wishlist") {
//              document.getElementById("wishlist").innerHTML = "Remove from Wishlist";
//            } else if (text === "Remove from Wishlist") {
//              document.getElementById("wishlist").innerHTML = "Add to Wishlist";
//            }
//          }
          </script>
        </div>
      </div> <!-- bookDescription-->
      <hr class="fancy">
      <br>
      <form id="submitReviewForm" action="SubmitItemReviewServlet" method="post"> 
      <div class="container" style="width: 75%;">
        <textarea class="review collapse" id="reviewdetails" name="review" placeholder="Describe your review...">
        </textarea>
        <button class="btn btn-success" id="reviewBtn" onClick = "submitReview()" style="display: none;">Submit</button>
        <br>
        <button type="button" onClick="showSubmit()" class="btn btn-info" data-toggle="collapse" data-target="#reviewdetails">Review this item</button>
        <script>
//        function showSubmit() {
//          var element = document.getElementById('reviewBtn'),
//          style = window.getComputedStyle(element),
//          display = style.getPropertyValue('display');
//          if (display === "none") {
//            document.getElementById("reviewBtn").style.display = "block";
//          } else {
//            document.getElementById("reviewBtn").style.display = "none";
//          }
//        }
        </script>
      </div>
    </form>
      <br><br><br>
      <hr class="fancy">
      <br>
      <div class="container" id="review-div" style="width: 75%;">
        <p class="homework-comment">For grading purposes, here is an example of RemoveItemReview GUI representation</p>
        <br><br>
        <textarea class="review" id="reviewdetails2" name="review2">This book was awesome! I really really liked it and would totally recommend! 1 star reduction for such an ugly cover, though.
        </textarea>
        <br>
        <button type="button" id="edit-review-btn" class="btn btn-info">Resubmit/edit review</button>
        <br>
        <button class="btn btn-danger" onClick="resetReview()" id="remove-review">Remove review</button>
        <script>
//        function resetReview() {
//          var d = document.getElementById("review-div");
//          var t = document.getElementById("reviewdetails2");
//          d.removeChild(t);
//          document.getElementById("edit-review-btn").style.display = "none";
//          document.getElementById("remove-review").style.display = "none";
//        }
        </script>
      </div>
    </div> <!-- GLBPage -->
  </body>
</html>

<!--Form for communicating with the second server -->
<form id = "secondServerForm" name = "secondServerForm" action = "SecondServerServlet" method = "post"></form> 
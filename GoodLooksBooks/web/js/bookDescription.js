var starsSelected;

$(document).ready(function(){
   console.log("isbn: " + document.getElementById("isbn").value);
   var avgStarRating = document.getElementById("avgStarID").getAttribute("value");
   
    updateAverageStarRating(avgStarRating, "avgStarID", true);   
    updateEachRatingStars("loggedInRatingID");
    updateEachRatingStars("");
  
    $("#emailButton").click(function(){
        console.log("email button clicked");
        $('#emailModal').modal('show');
    });
    
});

function updateEachRatingStars(id){
    if(id !== ""){
       if(document.getElementById(id) !== null){
            var star = document.getElementById(id).outerHTML;
            var index = star.indexOf("value=") + "value=".length + 1;
            star = star.substring(index, index + 1); 
            updateAverageStarRating(parseInt(star), id, false);
        }
    }
    else{
        var i  = 0; 
        while(document.getElementById("eachRatingID_" + i) !== null){
            var numOfStars = document.getElementById("eachRatingID_" + i).value;
      
            var star = document.getElementById("eachRatingID_" + i).outerHTML;
            var index = star.indexOf("value=") + "value=".length + 1;
            star = star.substring(index, index + 1); 
            if(parseInt(star)>-1){
                updateAverageStarRating(parseInt(star), "eachRatingID_" + i, false);
            }
//            console.log("Star: " + parseInt(star));
   
        i++;
        }
    } 
}

function createYellowStar(i){
    var img = document.createElement("img");
    img.src = "assets/yellowStar.png";
    img.id = 'avgStar' + i.toString();
    return img;
}
function sampleFunction(){
    
} 

function buyItNowFunction(bookID){
    console.log("buyItNowFunction");
    console.log('bookID = ' + bookID);
    document.getElementById("secondServerForm").submit();
}

function borrowFunction(){
}

function reserveFunction(){
    console.log("reserveFunction");
    console.log("isbn: " + document.getElementById("isbn").value);
    document.getElementById("bookDescriptionForm").submit();
}

function selectedBook(bookID){ // the id of the form is the isbn number of the selected book
    console.log("selectedBook");
    console.log('bookID = ' + bookID);
    document.getElementById("isbn").value = bookID;
    document.getElementById("itemSelectionForm").submit(); 
}

function showSubmit() {
    var element = document.getElementById('reviewBtn'),
            style = window.getComputedStyle(element),
            display = style.getPropertyValue('display');
    
    var elementStar = document.getElementById('userRatingID'),
        styleStar = window.getComputedStyle(element),
        displayStr = style.getPropertyValue('display');
    
    if (display === "none") {
        document.getElementById("reviewBtn").style.display = "block";
        document.getElementById("userRatingID").style.display = "block";
    } else {
        document.getElementById("reviewBtn").style.display = "none";
        document.getElementById("userRatingID").style.display = "none";
    }
}

function resetReview() {
    var d = document.getElementById("review-div");
    var t = document.getElementById("reviewdetails2");
   // d.removeChild(t);
    document.getElementById("edit-review-btn").style.display = "none";
    document.getElementById("remove-review").style.display = "none";
}

function removeReview(id){
    if(id === "remove-review1"){
        deleteReviewAjax("topReviewContainerID_1");
        
    }
    else{
        deleteReviewAjax("topReviewContainerID_2");
      
    } 
}

function deleteReviewAjax(id){
    var type = 'POST';
    var url = 'ItemReviewServlet';
    var isbn = document.getElementById("isbn").value.toString();
    console.log("In deleteReviewAjax: " + isbn);
    
    var itemObject = { 
        isbn : isbn,
        method : "delete"
    };
    
    $.ajax({ 
        type: type,
        url:  url,
        data: itemObject,
        dataType: 'json',
        success: function(result){  
            console.log(result);
            $('#' + id).html(''); 
            updateAverageStarRating(result, "avgStarID", true);   
        },
        error: function(result){
            console.log("Error!");
            console.log(result);
        }
    });
}

function changeWishlist() {
    var text = document.getElementById("wishlist").innerHTML;
    if (text === "Add to Wishlist") {
        document.getElementById("wishlist").innerHTML = "Remove from Wishlist";
    } else if (text === "Remove from Wishlist") {
        document.getElementById("wishlist").innerHTML = "Add to Wishlist";
    }
}

function setColor(btn, color){
    var property = document.getElementById(btn);
    if (window.getComputedStyle(property).backgroundColor === 'rgb(225, 225, 225)') {
        property.style.backgroundColor=color;
    }
    else {
        property.style.backgroundColor = "#E1E1E1";
    }
}
 
function submitReview(){
    var text = document.getElementById("reviewdetails").value;
  //  console.log(text);
    addReviewAjax(text);
    document.getElementById("submittingReviewID").style.display = 'none';
   
  //  document.getElementById("submitReviewForm").submit();   
}

function addReviewAjax(text){
    var type = 'POST';
    var url = 'ItemReviewServlet';
    var isbn = document.getElementById("isbn").value.toString();
   
    var itemObject = {
        numOfStars : starsSelected,
        text : text,
        isbn : isbn,
        method : "add"
    };
    
    $.ajax({ 
        type: type,
        url:  url,
        data: itemObject,
        dataType: 'json',
        success: function(result){  
            console.log("Success!");  
            successCallFromAjax(result); 
        },
        error: function(result){
            console.log("Error!");
            console.log(result);
        }
    });
}

function successCallFromAjax(result){
    var currentUser = result.currentUser;
            var text = "";
            var jsonArray = result.reviews; 
            for (var i=0; i<jsonArray.length; i++){
                var starRating = jsonArray[i].rating;
                var username = jsonArray[i].username;
                var reviewText = jsonArray[i].reviewText; 
                if(currentUser === username){
                    console.log("In if statment: " + text);
                    $("#newReviewID").show();
                    console.log("Username: " + username);
                    console.log("Star Rating: " + starRating);
                    console.log("Review Text: " + reviewText);
                    document.getElementById("newReviewUsernameTextID").innerHTML = username;
                    document.getElementById("newReviewUserReviewTextID").innerHTML = reviewText;
                    document.getElementById("newReviewIDStars").value = starRating; 
                    updateAverageStarRating(starRating, "newReviewIDStars", false);
                    break;
                } 
            }
            
            console.log("Result.avgRating: " + result.avgRating);   
            updateAverageStarRating(result.avgRating, "avgStarID", true);   
}

$(document).on('ready', function(){ 
    if(window.location.href === "BookDescriptionServlet"){
        updateAverageRatingAjax(null);
        $('#input-3').rating({displayOnly: true, step: 0.5});
    }
    else{
        console.log("Not on page");
    }      
});

function updateAverageStarRating(avgStarRating, avgStarID, flag){
    avgStarRating = Math.round(avgStarRating * 10)/10;
    console.log("Avg rating: " + avgStarRating);
    var theDiv = document.getElementById(avgStarID); 
    while (theDiv.hasChildNodes()) {
        theDiv.removeChild(theDiv.lastChild);
    } 
    for(var i = 1; i <= avgStarRating; i++){ 
       theDiv.appendChild(createYellowStar(i));  
    }
    if(flag){
        var decimal = avgStarRating - Math.floor(avgStarRating);
        var widthOfImage = 36 * decimal;   
        var imgElement = document.createElement("img"); 
        imgElement.setAttribute("id", "clip");
        imgElement.setAttribute("src", "assets/yellowStar.png");
        theDiv.appendChild(imgElement); 
        document.getElementById("clip").style.clip =  "rect(0px " + widthOfImage + "px 200px 0px)";  
        document.getElementById("avgRatingText").innerHTML = avgStarRating;
    }
}
 

function validateImgUrl(id) {
    var book = document.getElementById(id);
        try {
            var img = document.createElement("img");
            img.src = book.src;
        } catch(err) {
            //
        }
        if(img.height > 0 && img.width > 0) {
            console.log("image exists");
            //image exists
        } else {
            console.log("image does not exists");
            book.src = "assets/no-media.png";
        }
}

function fbShare(url,winWidth, winHeight) {
        var winTop = (screen.height / 2) - (winHeight / 2);
        var winLeft = (screen.width / 2) - (winWidth / 2);
        window.open('http://www.facebook.com/sharer.php?s=100&p[url]=' + url, 'sharer','top=' + winTop + ',left=' + winLeft + ',toolbar=0,status=0,width='+winWidth+',height='+winHeight);
}


function starRating(num){ 
    var path = "";
    var flag = getStarColor(num); // means white star 
    for (var i = 1; i <= 5; i++){
        if(flag){
            if(i <= num){
                path = "assets/yellowStar.png";
                document.getElementById('star' + i.toString()).setAttribute("value", "1");
            }
            else{
                path = "assets/star.png";
                document.getElementById('star' + i.toString()).setAttribute("value", "0");
            } 
        }
        else{
            if(i >= num){
                path = "assets/star.png";
                document.getElementById('star' + i.toString()).setAttribute("value", "0");
            }
            else{
                path = "assets/yellowStar.png";
                document.getElementById('star' + i.toString()).setAttribute("value", "1");
            }
        }
         var id = 'star' + i.toString();
         document.getElementById(id).src = path;     
    }
    
    var comment = "";
    switch(num){ 
        case 1 : comment = "I hate it";              break;
        case 2 : comment = "I don't really like it"; break;
        case 3 : comment = "It's not bad";           break;
        case 4 : comment = "I like it";              break;
        case 5 : comment = "I love it";              break;
    }
    
    starsSelected = num;
    var element = document.getElementById("starDescriptionID").innerHTML = comment;
    
    
   // updateAverageStarRatingAjax(num);
}

function getStarColor(num){
    // returns true if star is white
    return document.getElementById('star' + num.toString()).getAttribute("value") === "0";  
}


 

    

    
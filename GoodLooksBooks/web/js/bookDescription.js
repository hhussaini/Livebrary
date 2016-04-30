var starsSelected;

$(document).ready(function(){
  console.log("isbn: " + document.getElementById("isbn").value);
   var avgStarRating = document.getElementById("avgStarID").getAttribute("value");
   updateAverageStarRating(avgStarRating);
   
   
   
    $("#emailButton").click(function(){
        console.log("email button clicked");
        $('#emailModal').modal('show');
    });

});

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
    d.removeChild(t);
    document.getElementById("edit-review-btn").style.display = "none";
    document.getElementById("remove-review").style.display = "none";
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
    console.log(text);
    updateReviewsAjax(text);
    document.getElementById("submittingReviewID").style.display = 'none';
   
  //  document.getElementById("submitReviewForm").submit();   
}

function updateReviewsAjax(text){
    var type = 'POST';
    var url = 'ItemReviewServlet';
    var isbn = document.getElementById("isbn").value.toString();
   
    var itemObject = {
        numOfStars : starsSelected,
        text : text,
        isbn : isbn
    };
    
    $.ajax({ 
        type: type,
        url:  url,
        data: itemObject,
        dataType: 'json',
        success: function(result){  
            console.log("Success!"); 
            console.log(JSON.stringify(result.reviews));
            var currentUser = result.currentUser;
            
            var jsonArray = result.reviews;
            console.log("Length of jsoArray: " + jsonArray.length);
            for (var i=0; i<jsonArray.length; i++){
                for (var jso in jsonArray[i]) {
                  //  var name = jso.userReview;
                    console.log(jso);
                    if(name === currentUser){
                        
                    }
                }
            }

            
            
            
           // var userName = document.getElementById("usernameTextID");
           
           var text = result.reviews[result.currentUser].reviewText;
        //   console.log("Review text: " + text);
           document.getElementById("userReviewTextID").innerHTML = text;
            
           updateAverageStarRating(result.avgRating);
           // updateAverageStarRating(result.avgRating);
       
        },
        error: function(result){
            console.log("Error!");
            console.log(result);
        }
    });
}

$(document).on('ready', function(){
    //  console.log("hey: " + window.location.href);
    if(window.location.href === "BookDescriptionServlet"){
        updateAverageRatingAjax(null);
        $('#input-3').rating({displayOnly: true, step: 0.5});
    }
    else{
        console.log("Not on page");
    }      
});

function updateAverageStarRating(avgStarRating){
   avgStarRating = Math.round(avgStarRating * 10)/10;
   console.log("Avg rating: " + avgStarRating);
   var theDiv = document.getElementById("avgStarID");
   for(var i = 1; i <= avgStarRating; i++){ 
       theDiv.appendChild(createYellowStar(i));  
   }
   var decimal = avgStarRating - Math.floor(avgStarRating);
   var widthOfImage = 36 * decimal;  
   document.getElementById("clip").style.clip =  "rect(0px " + widthOfImage + "px 200px 0px)"; 
   theDiv.appendChild(document.getElementById("clip")); 
}

//function updateAverageStarRatingAjax(numOfStarsSelected){
//    var type = 'POST';
//    var url = 'ItemReviewServlet';
//    var isbn = document.getElementById("isbn").value.toString();
//   
//    var itemObject = {
//        numOfStars : numOfStarsSelected.toString(), 
//        isbn : isbn
//    };
//    
//    $.ajax({ 
//        type: type,
//        url:  url,
//        data: itemObject,
//        dataType: 'json',
//        success: function(result){  
//            console.log("Success!"); 
//            updateAverageStarRating(result.avgRating);
//       
//        },
//        error: function(result){
//            console.log("Error!");
//            console.log(result);
//        }
//    });
//}

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


    

    
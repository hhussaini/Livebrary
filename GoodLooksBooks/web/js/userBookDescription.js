$(document).ready(function(){
    $("#emailButton").click(function(){
        console.log("email button clicked");
        $('#emailModal').modal('show');
    });
});

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
    if (display === "none") {
        document.getElementById("reviewBtn").style.display = "block";
    } else {
        document.getElementById("reviewBtn").style.display = "none";
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
    document.getElementById("submitReviewForm").submit();   
}

$(document).on('ready', function(){
    //  console.log("hey: " + window.location.href);
    if(window.location.href === "UserBookDescriptionServlet"){
        updateAverageRating(null);
        $('#input-3').rating({displayOnly: true, step: 0.5});
    }
    else{
        console.log("Not on page");
    }     
});

function updateAverageRating(numOfStarsSelected){
    var type = 'POST';
    var url = 'UpdateAverageRatingServlet';
    var isbn = document.getElementById("isbn").value.toString() + "";
    var itemObject = {
        numOfStars : numOfStarsSelected, 
        isbn : isbn
    };
    $.ajax({ 
        type: type,
        url: url,
        data: itemObject,
        dataType: 'json',
        success: function(result) {  
            console.log(result.avgNumOfStars);
            document.getElementById("input-3").value = result.avgNumOfStars;
            console.log("Average Rating: " + document.getElementById("input-3").value); 
            
        },
        error: function(result){
            console.log("Error!");
            console.log(result);
        }
    });
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
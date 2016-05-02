 

$(document).ready(function(){
   console.log("isbn: " + document.getElementById("isbn").value);
   
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
 
 

$(document).on('ready', function(){ 
    if(window.location.href === "BookDescriptionServlet"){
        
        $('#input-3').rating({displayOnly: true, step: 0.5});
    }
    else{
        console.log("Not on page");
    }      
});


 

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


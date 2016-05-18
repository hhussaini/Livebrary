$(document).ready(function(){
   console.log("isbn: " + document.getElementById("isbn").value);
//    $("#emailButton").click(function(){
//        console.log("email button clicked");
//        $('#emailModal').modal('show');
//    });
});

$(document).on('ready', function(){ 
    if (window.location.href === "BookDescriptionServlet"){
        $('#input-3').rating({displayOnly: true, step: 0.5});
    }
    else {
        console.log("Not on page");
    }      
}); 

function showEmailModal() {
   $('#emailModal').modal('show');
}
function sampleItem(sampleUrl) {
   console.log("sampleItem");
   if (!isEmpty(sampleUrl)) {
      window.open(sampleUrl);
   } else {
      alert('No sample is available for this item.');
   }
}

function borrowItem(){
   console.log("borrowItem");
   document.getElementById("borrowItemForm").submit();
}

function holdItem(){
   console.log("holdItem");
   document.getElementById("holdItemForm").submit();
}

function recommendItemPage(){
    console.log("recommendItemPage");
    window.location.href = "recommendItem.jsp";  
}

function recommendItem(){
    console.log("recommendItem");
    var e = document.getElementById("automaticCheckout");
    var value = e.options[e.selectedIndex].value;
    var email = document.getElementById("inputEmail").value;
    document.getElementById("checkOutRecommend").value = value; 
    document.getElementById("emailRecommend").value = email;
    
    console.log(document.getElementById("checkOutRecommend").value);
    console.log(document.getElementById("emailRecommend").value);
    
    document.getElementById("recommendItemForm").submit();
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

function banFunction(isBanned){
    if(document.getElementById("banButtonID").value === "" && isBanned === 0){
        document.getElementById("banButtonID").innerHTML = "Ban Book";
        document.getElementById("banButtonID").value = "Ban Book";
    }
    else if(document.getElementById("banButtonID").value === ""){
        document.getElementById("banButtonID").innerHTML = "Unban Book";
        document.getElementById("banButtonID").value = "Unban Book";
    }
    else if(document.getElementById("banButtonID").innerHTML === "Unban Book"){
        document.getElementById("banButtonID").innerHTML = "Ban Book";
    }
    else{
        document.getElementById("banButtonID").innerHTML = "Unban Book";
    }
  
    document.getElementById("banButtomForm").submit();
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}
function confirmBuyFunction(bookID){
    console.log("confirmBuyFunction");
    document.getElementById("confirmBuyItemForm").submit();
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


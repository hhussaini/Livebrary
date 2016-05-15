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

function confirmDownload(username, downloadUrl){
    console.log("confirmDownload.downloadUrl = " + downloadUrl);
    if (isEmpty(username)) {
       alert('Please login first.')
    } else {
      window.open(downloadUrl);
    }
}

function confirmReturn(bookID){
    console.log("confirmReturn");
    document.getElementById("confirmReturnForm").submit();
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}

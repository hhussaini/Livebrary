function downloadBook(bookID){
    console.log("downloadBook");
    console.log('bookID = ' + bookID);
    window.open("http://localhost:8080/GoodLooksBooks/SecondServerServlet?isbn="+bookID);
}

function returnBook(bookID){
    console.log("downloadBook");
    console.log('bookID = ' + bookID);
    window.open("http://localhost:8080/GoodLooksBooks/SecondServerServlet?isbn="+bookID);
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
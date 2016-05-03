var starsSelected;

$(document).ready(function(){
    var avgStarRating = document.getElementById("avgStarID").getAttribute("value");
    updateAverageStarRating(avgStarRating, "avgStarID", true); 
    updateEachReviewsStarRating("loggedInRatingID_");
    console.log("SubmittingReviewID(): " + document.getElementById("submittingReviewID"));
});

function updateEachReviewsStarRating(id){
    var i = 0;
    if(document.getElementById("hiddenElement1") !== null){
        var username = getValuesOfElements(document.getElementById("hiddenElement1").outerHTML, false);
        var numOfStars = getValuesOfElements(document.getElementById("hiddenElement2").outerHTML, true); 
        var text = getValuesOfElements(document.getElementById("hiddenElement3").outerHTML, false); 
        document.getElementById("reviewContainer_0").style.display = "initial";
        document.getElementById("userReviewName_0").innerHTML = username;
        document.getElementById("loggedInRatingID_0").innerHTML = numOfStars;
        document.getElementById("reviewText_0").innerHTML = text;
        
    }
    else{
        i = 1;
        console.log("its null"); 
    }
    
    
   
    var element;
    while(document.getElementById(id + i.toString()) !== null){
        var tempId = id + i.toString();
        element = document.getElementById(tempId).outerHTML; 
 
        
        var stars = parseInt(getValuesOfElements(element, true)); 
        updateAverageStarRating(stars, tempId, false);
        i++;
    } 
 
}

function getValuesOfElements(string, isStarFlag){
    var index = string.indexOf("value=") + "value=".length + 1;
    var stringTemp = string.substring(index, string.lastIndexOf("\"")); 
    
    if(isStarFlag && stringTemp.length === 0){
        index = string.indexOf(">") + 1;
        stringTemp = string.substring(index, index + 1); 
        console.log(stringTemp);
    }
  //  console.log("String: " + string);
    return stringTemp;
}

function updateAverageStarRating(avgStarRating, avgStarID, flag){
    avgStarRating = Math.round(avgStarRating * 10)/10; 
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

function createYellowStar(i){
    var img = document.createElement("img");
    img.src = "assets/yellowStar.png";
    img.id = 'avgStar' + i.toString();
    return img;
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

function showSubmit() {
    var element = document.getElementById('reviewBtn'),
    style = window.getComputedStyle(element),
    display = style.getPropertyValue('display');
    
    
    if (display === "none") {
        document.getElementById("reviewBtn").style.display = "block";   
        document.getElementById("userRatingID").style.display = "block"; 
    } else {
        document.getElementById("reviewBtn").style.display = "none";
        document.getElementById("userRatingID").style.display = "none";
    }
}



function submitReview(method){
    var type = 'POST';
    var url = 'ItemReviewServlet';
    var isbn = document.getElementById("isbn").value.toString();

    var itemObject = {
        numOfStars : starsSelected,
        text : document.getElementById("reviewdetails").value,
        isbn : isbn,
        method : method
    }; 

    $.ajax({ 
        type: type,
        url:  url,
        data: itemObject,
        dataType: 'json',
        success: function(result){
            console.log("Success!");
            var currentUser = result.currentUser;
            var jsonArray = result.reviews;
            for (var i = 0; i < jsonArray.length; i++){
                var starRating = jsonArray[i].rating;
                var username = jsonArray[i].username;
                var reviewText = jsonArray[i].reviewText;
                if(currentUser === username){
                   document.getElementById("userReviewName_0").innerHTML = username;
                   document.getElementById("loggedInRatingID_0").innerHTML = starRating;
                   document.getElementById("reviewText_0").innerHTML = reviewText;
                   document.getElementById("reviewContainer_0").style.display = "initial";
                   updateAverageStarRating(starRating, "loggedInRatingID_0", false);
                   updateAverageStarRating(result.avgRating, "avgStarID", true);
                   document.getElementById("submittingReviewInnerID").style.display = "none";
                    
                }
            }  
        },
        error: function(result){
            console.log("Error!");
            console.log(result);
        }
    }); 
}
 
function removeReview(id){
        
    var type = 'POST';
    var url = 'ItemReviewServlet';
    var isbn = document.getElementById("isbn").value.toString();
    
    //console.log(document.getElementById("reviewdetails"));

    var itemObject = {
        numOfStars : starsSelected, 
        isbn : isbn,
        method : "delete"
    }; 
    $.ajax({ 
        type: type,
        url:  url,
        data: itemObject,
        dataType: 'json',
        success: function(result){
            updateAverageStarRating(result, "avgStarID", true);
            document.getElementById("reviewContainer_0").style.display = "none";
            document.getElementById("submittingReviewInnerID").style.display = "block";
            document.getElementById("reviewdetails").value = "";
        },
        error: function(result){
            console.log("Error");
        }
    });    
       
} 

function addReview(){
    console.log("In addReview()");
    if(document.getElementById("reviewContainer_0").style.display === "none"){
        console.log("In if statement");
        submitReview("add");
    }
    else{
        console.log("In else statement");
        submitReview("edit");
    }
    
}

function editReview(){
    document.getElementById("submittingReviewInnerID").style.display = "block";
} 

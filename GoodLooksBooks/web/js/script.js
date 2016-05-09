function displayAds() {
   var width = '350';
   var height = '200';
   ads = new Array(4);
   ads[0] = "<a href='http://www.gamestop.com' rel='nofollow'>" + 
            "<img src='http://www.gamestop.com/gs/logos/files/GameStopLogo_BlackRed.jpg' border='0' width =" + width + "px' height= " + height + "px'/></a>";
   ads[1] = "<a href='http://www.magiccitycomics.com/' rel='nofollow'>" +
            "<img src='http://cdn.shopify.com/s/files/1/0642/2117/t/1/assets/logo.png?16671871016782250797' width =" + width + "px' height= " + height + "px'/></a>";
   ads[2] = "<a href='http://www.ltcds.com' rel='nofollow'>" + 
            "<img src='http://ltcds.com/wp-content/uploads/2016/01/LTCDS-45th-ANNIV-LOGO-HI-RES.png' width =" + width + "px' height= " + height + "px'/></a>";
   ads[3] = "<a href='/GoodLooksBooks/AllSubjectsServlet' rel='nofollow'>" +
            "<img src='assets/search-by-subject-ad.png' border='0' width =" + width + "px' height= " + height + "px'/></a>";
   index = Math.floor(Math.random() * ads.length);
   document.write(ads[index]);
}


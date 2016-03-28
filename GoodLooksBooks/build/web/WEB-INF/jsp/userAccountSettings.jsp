<!DOCTYPE html>
<html lang="en">

<head>
 <title>Change Account Settings</title>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="css/bootstrap.css">
 <link rel="stylesheet" href="css/custom_css/stylesheet.css">
 <link rel="stylesheet" href="css/custom_css/bookPhotoGrid.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
 <script src="js/bootstrap.js"></script>
</head>

<body>
 <nav class="navbar navbar-default navbar-fixed-top">
   <nav class="navbar navbar-default navbar-fixed-top">
       <div class="container-fluid">
           <div class="navbar-header">
               <a href="userIndex.jsp" class="pull-left">
                   <img alt="brand" src="assets/brand-icon.png"></a>
           </div>
           <ul class="nav navbar-nav" style="font-weight: bold;">
               <li><a href="userIndex.jsp">Home </a></li>
               <li><a href="userAbout.jsp">About</a></li>
               <li><a href="userFullCatalog.jsp">Full Catalog</a></li>
               <li><a href="userAccount.jsp">Account</a></li>
               <li><a href="userHelp.jsp">Help</a></li>
           </ul>
           <ul class="nav navbar-nav navbar-right">
              <li class="dropdown">
                 <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Options<span class="caret"></span></a>
                 <ul class="dropdown-menu">
                    <li><a href="#">My Cart</a></li>
                    <li><a href="#">Book Wishlist</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="index.jsp">Sign Out</a></li>
                 </ul>
              </li>
           </ul>
       </div>
   </nav>
 </nav>
 <br><br><br>
 <div class="glb-page">
   <div class="container">
     <div id="mycarousel" class="carousel slide" data-ride="carousel">
       <div class="carousel-inner">
         <div class="item active">
           <img src="assets/small-header-blur.png" alt="" class="img-responsive">
           <div class="small-carousel-text">
             <div class="boxed">
               <h1> <b>Good Looks Books <br> </b> </h1>
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>
  <div class="container" styl="padding: 100px;">
   &nbsp;<br>
   &nbsp;<br>
   &nbsp;<br>
  </div>
  <div class="container">
      <div class="panel panel-default">
          <div class="panel-heading">
              <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Change Account Settings</h3>
          </div>
          <div class="panel-body">
            <!-- edit form column -->
    <div class="col-md-9 personal-info">
      <form class="form-horizontal" role="form">
        <div class="form-group">
          <label class="col-lg-3 control-label">First name:</label>
          <div class="col-lg-8">
            <input class="form-control" value="Jane" type="text">
          </div>
        </div>
      </form>
    </div>
          </div>
      </div>
  </div>

  <div>
  </body>
  </html>

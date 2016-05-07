




<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Full Catalog for Good Looks Books</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/custom_css/stylesheet.css">
        <link rel="stylesheet" href="css/custom_css/bookPhotoGrid.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/bookDescription.js" type="text/javascript"></script>
        <script src="js/reviews.js" type="text/javascript"></script>
    </head>
    <body>
        
        
        
        
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="index.jsp" class="pull-left">
                        <img alt="brand" src="assets/brand-icon.png">
                    </a>
                </div>
                <ul class="nav navbar-nav" style="font-weight: bold;">
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="about.jsp">About</a></li>
                    <li><a href="SearchServlet">Full Catalog</a></li>
                    <li><a href="help.jsp">Help</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right" style="font-weight: bold;">
                    <li><a href="signUp.jsp">Sign Up</a></li>
                    <li><a href="signIn.jsp">Sign In</a></li>
                </ul>
            </div>
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
            </div>
            
            <br>
            <div class="container">
                <form class="form" name="searchForm" id="searchForm" action="SearchServlet" method = "get" role="search">
                    <div class="input-group">
                        <table>
                            <tr>
                                <td>
                                    <label for="keyword">Keyword</label>
                                    <input name="keyword" type="text" class="form-control" value="" placeholder="Title, Genre, Keyword, etc..." id="keyword">
                                </td>
                                <td>
                                    <label for="author">Author</label>
                                    <input name="author" type="text" class="form-control" value="" placeholder="Author" id="author">
                                </td>
                                <td>
                                    <label for="publisher">Publisher</label>
                                    <input name="publisher" type="text" class="form-control" value="" placeholder="Publisher" id="publisher">
                                </td>
                                <td>
                                    <label for="isbn-term">ISBN</label>
                                    <input name="isbn" type="text" class="form-control" value="" placeholder="Isbn" id="isbn-term">
                                </td>
                                <td>
                                    <div class="input-group-btn">
                                        <button name="searchbtn" class="btn btn-default" type="submit">search</button>  
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </form>
                
                <ul class="nav nav-pills">
                    <li class="active"><a href="#grid-view" data-toggle="tab"><img id="grid-tab" src="assets/glyphicons-157-show-thumbnails.png"></a></li>
                    <li><a href="#list-view" data-toggle="tab"><img id="list-tab" src="assets/glyphicons-530-list-alt.png"></a></li>
                </ul>
                
                <div class="tab-content">
                    <div id="grid-view" class="tab-pane fade in active">
                        
                        
                        
                        
                        <table>
                            <tr>
                                
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780007270583" id="9780007270583" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/519P%2BzrWpRL._SX220_.jpg" alt="The Crafting Of Narnia: The Art, Creatures, And Weapons From Weta Workshop" style="width: 200px;">
                                                <p>The Crafting Of Narnia: The Art, Creatures, And Weapons From Weta Workshop"</p> <br> <br>
                                                <p>by Weta Workshop"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780030638039" id="9780030638039" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/41BRDMFQ04L._SX220_.jpg" alt="The Dobe !Kung (Case Studies In Cultural Anthropology)" style="width: 200px;">
                                                <p>The Dobe !Kung (Case Studies In Cultural Anthropology)"</p> <br> <br>
                                                <p>by Richard B. Lee"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060006792" id="9780060006792" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/51rozKomPtL._SX220_.jpg" alt="The Agile Gene: How Nature Turns On Nurture" style="width: 200px;">
                                                <p>The Agile Gene: How Nature Turns On Nurture"</p> <br> <br>
                                                <p>by Matt Ridley"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                            </tr>
                            <tr>
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060007751" id="9780060007751" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/51ArRajmOoL._SX220_.jpg" alt="Easy To Love, Difficult To Discipline: The 7 Basic Skills For Turning Conflict Into Cooperation" style="width: 200px;">
                                                <p>Easy To Love, Difficult To Discipline: The 7 Basic Skills For Turning Conflict Into Cooperation"</p> <br> <br>
                                                <p>by Becky A. Bailey"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060088781" id="9780060088781" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/513%2BpCzrPlL._SX220_.jpg" alt="Evolution's Captain: The Story Of The Kidnapping That Led To Charles Darwin's Voyage Aboard The Beagle" style="width: 200px;">
                                                <p>Evolution's Captain: The Story Of The Kidnapping That Led To Charles Darwin's Voyage Aboard The Beagle"</p> <br> <br>
                                                <p>by Peter Nichols"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060175351" id="9780060175351" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://www.openisbn.com/cover/0060175354_220.jpg" alt="Nazi Gold: The Full Story Of The Fifty-Year Swiss-Nazi Conspiracy To Steal Billions From Europe's Jews And Holocaust Survivors" style="width: 200px;">
                                                <p>Nazi Gold: The Full Story Of The Fifty-Year Swiss-Nazi Conspiracy To Steal Billions From Europe's Jews And Holocaust Survivors"</p> <br> <br>
                                                <p>by Tom Bower"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                            </tr>
                            <tr>
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060510855" id="9780060510855" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/514QJS57HML._SX220_.jpg" alt="Caesar: A Novel" style="width: 200px;">
                                                <p>Caesar: A Novel"</p> <br> <br>
                                                <p>by Colleen Mccullough"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060517182" id="9780060517182" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/41duD7%2BS7CL._SX220_.jpg" alt="With Their Eyes: September 11th--The View From A High School At Ground Zero" style="width: 200px;">
                                                <p>With Their Eyes: September 11th--The View From A High School At Ground Zero"</p> <br> <br>
                                                <p>by Annie Thoms"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060539160" id="9780060539160" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/51V5KZ9mhZL._SX220_.jpg" alt="Rough Crossings: Britain, The Slaves And The American Revolution" style="width: 200px;">
                                                <p>Rough Crossings: Britain, The Slaves And The American Revolution"</p> <br> <br>
                                                <p>by Simon Schama"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                            </tr>
                            <tr>
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060539825" id="9780060539825" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/51ShKG3Z%2BdL._SX220_.jpg" alt="Burning Chrome" style="width: 200px;">
                                                <p>Burning Chrome"</p> <br> <br>
                                                <p>by William Gibson"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060575601" id="9780060575601" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/51uD96U7U8L._SX220_.jpg" alt="Caravaggio: Painter Of Miracles (Eminent Lives)" style="width: 200px;">
                                                <p>Caravaggio: Painter Of Miracles (Eminent Lives)"</p> <br> <br>
                                                <p>by Francine Prose"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060595371" id="9780060595371" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/51UmXO9zU2L._SX220_.jpg" alt="Couldn't Keep It To Myself:  Wally Lamb And The Women Of York Correctional Institution (Testimonies From Our Imprisoned Sisters)" style="width: 200px;">
                                                <p>Couldn't Keep It To Myself:  Wally Lamb And The Women Of York Correctional Institution (Testimonies From Our Imprisoned Sisters)"</p> <br> <br>
                                                <p>by Wally Lamb"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                            </tr>
                            <tr>
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060722005" id="9780060722005" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/518Ej2KUQlL._SX220_.jpg" alt="The Atkins Shopping Guide: Indispensable Tips And Guidelines For Successfully Stocking Your Low-carb Kitchen" style="width: 200px;">
                                                <p>The Atkins Shopping Guide: Indispensable Tips And Guidelines For Successfully Stocking Your Low-carb Kitchen"</p> <br> <br>
                                                <p>by Atkins Health & Medical Information Serv"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060740924" id="9780060740924" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/41eznIBvWTL._SX220_.jpg" alt="Why Do Clocks Run Clockwise?: An Imponderables Book (Imponderables Books)" style="width: 200px;">
                                                <p>Why Do Clocks Run Clockwise?: An Imponderables Book (Imponderables Books)"</p> <br> <br>
                                                <p>by David Feldman"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060787219" id="9780060787219" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/41WWYFS9A3L._SX220_.jpg" alt="Zorro LP: A Novel" style="width: 200px;">
                                                <p>Zorro LP: A Novel"</p> <br> <br>
                                                <p>by Isabel Allende"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                            </tr>
                            <tr>
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060823337" id="9780060823337" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/41XFZEGP5EL._SX220_.jpg" alt="Francis Crick: Discoverer Of The Genetic Code (Eminent Lives) (rough Edge)" style="width: 200px;">
                                                <p>Francis Crick: Discoverer Of The Genetic Code (Eminent Lives) (rough Edge)"</p> <br> <br>
                                                <p>by Matt Ridley"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060878801" id="9780060878801" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/41%2B3eoYrDFL._SX220_.jpg" alt="Buddha: A Story Of Enlightenment" style="width: 200px;">
                                                <p>Buddha: A Story Of Enlightenment"</p> <br> <br>
                                                <p>by Deepak Chopra"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                                
                                <td>
                                    <div class="col-xs-6 col-md-3"> 
                                        <td> 
                                            <a href = "BookDescriptionServlet?isbn=9780060894085" id="9780060894085" class="thumbnail">
                                                <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/517WA57C76L._SX220_.jpg" alt="Genome: The Autobiography Of A Species In 23 Chapters (P.S.)" style="width: 200px;">
                                                <p>Genome: The Autobiography Of A Species In 23 Chapters (P.S.)"</p> <br> <br>
                                                <p>by Matt Ridley"</p>
                                            </a>
                                        </td>
                                    </div>
                                </td>  
                                
                            </tr>
                        </table>
                        
                        
                        
                        
                        
                        <table border="1" cellpadding="5" cellspacing="5" align="center">
                            <tr>
                                
                                <td><a href="SearchServlet?page=1&searchTerm="><<</a></td>
                                
                                
                                
                                
                                <td>1</td>
                                
                                
                                
                                
                                
                                
                                
                                <td><a href="SearchServlet?page=2&searchTerm=">2</a>
                                </td>
                                
                                
                                
                                
                                
                                
                                <td><a href="SearchServlet?page=3&searchTerm=">3</a>
                                </td>
                                
                                
                                
                                
                                
                                
                                <td><a href="SearchServlet?page=4&searchTerm=">4</a>
                                </td>
                                
                                
                                
                                
                                
                                
                                <td><a href="SearchServlet?page=5&searchTerm=">5</a>
                                </td>
                                
                                
                                
                                
                                
                                <td><a href="SearchServlet?page=2&searchTerm=">Next</a>
                                </td>
                                
                                <td><a href="SearchServlet?page=62&searchTerm=">>></a></td>
                            </tr>
                        </table>
                        
                        
                        
                    </div>
                    <div id="list-view" class="tab-pane fade">
                        
                        
                        
                        
                        
                        <table>
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780007270583" id="9780007270583" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/519P%2BzrWpRL._SX220_.jpg" alt="The Crafting Of Narnia: The Art, Creatures, And Weapons From Weta Workshop" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780007270583" href="BookDescriptionServlet?isbn=9780007270583">The Crafting Of Narnia: The Art, Creatures, And Weapons From Weta Workshop</a></p> <p>by Weta Workshop</p> <br>
                                    <h4>Left : 10 | Published : 2008 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780030638039" id="9780030638039" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/41BRDMFQ04L._SX220_.jpg" alt="The Dobe !Kung (Case Studies In Cultural Anthropology)" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780030638039" href="BookDescriptionServlet?isbn=9780030638039">The Dobe !Kung (Case Studies In Cultural Anthropology)</a></p> <p>by Richard B. Lee</p> <br>
                                    <h4>Left : 10 | Published : 1984-01 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060006792" id="9780060006792" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/51rozKomPtL._SX220_.jpg" alt="The Agile Gene: How Nature Turns On Nurture" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060006792" href="BookDescriptionServlet?isbn=9780060006792">The Agile Gene: How Nature Turns On Nurture</a></p> <p>by Matt Ridley</p> <br>
                                    <h4>Left : 10 | Published : 2004-07-06 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060007751" id="9780060007751" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/51ArRajmOoL._SX220_.jpg" alt="Easy To Love, Difficult To Discipline: The 7 Basic Skills For Turning Conflict Into Cooperation" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060007751" href="BookDescriptionServlet?isbn=9780060007751">Easy To Love, Difficult To Discipline: The 7 Basic Skills For Turning Conflict Into Cooperation</a></p> <p>by Becky A. Bailey</p> <br>
                                    <h4>Left : 10 | Published : 2001-12-24 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060088781" id="9780060088781" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/513%2BpCzrPlL._SX220_.jpg" alt="Evolution's Captain: The Story Of The Kidnapping That Led To Charles Darwin's Voyage Aboard The Beagle" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060088781" href="BookDescriptionServlet?isbn=9780060088781">Evolution's Captain: The Story Of The Kidnapping That Led To Charles Darwin's Voyage Aboard The Beagle</a></p> <p>by Peter Nichols</p> <br>
                                    <h4>Left : 10 | Published : 2004-06-29 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060175351" id="9780060175351" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://www.openisbn.com/cover/0060175354_220.jpg" alt="Nazi Gold: The Full Story Of The Fifty-Year Swiss-Nazi Conspiracy To Steal Billions From Europe's Jews And Holocaust Survivors" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060175351" href="BookDescriptionServlet?isbn=9780060175351">Nazi Gold: The Full Story Of The Fifty-Year Swiss-Nazi Conspiracy To Steal Billions From Europe's Jews And Holocaust Survivors</a></p> <p>by Tom Bower</p> <br>
                                    <h4>Left : 10 | Published : 1997-04 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060510855" id="9780060510855" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/514QJS57HML._SX220_.jpg" alt="Caesar: A Novel" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060510855" href="BookDescriptionServlet?isbn=9780060510855">Caesar: A Novel</a></p> <p>by Colleen Mccullough</p> <br>
                                    <h4>Left : 10 | Published : 2003-02-01 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060517182" id="9780060517182" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/41duD7%2BS7CL._SX220_.jpg" alt="With Their Eyes: September 11th--The View From A High School At Ground Zero" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060517182" href="BookDescriptionServlet?isbn=9780060517182">With Their Eyes: September 11th--The View From A High School At Ground Zero</a></p> <p>by Annie Thoms</p> <br>
                                    <h4>Left : 10 | Published : 2002-08-20 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060539160" id="9780060539160" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/51V5KZ9mhZL._SX220_.jpg" alt="Rough Crossings: Britain, The Slaves And The American Revolution" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060539160" href="BookDescriptionServlet?isbn=9780060539160">Rough Crossings: Britain, The Slaves And The American Revolution</a></p> <p>by Simon Schama</p> <br>
                                    <h4>Left : 10 | Published : 2006-04-25 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060539825" id="9780060539825" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/51ShKG3Z%2BdL._SX220_.jpg" alt="Burning Chrome" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060539825" href="BookDescriptionServlet?isbn=9780060539825">Burning Chrome</a></p> <p>by William Gibson</p> <br>
                                    <h4>Left : 10 | Published : 2003-07-29 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060575601" id="9780060575601" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/51uD96U7U8L._SX220_.jpg" alt="Caravaggio: Painter Of Miracles (Eminent Lives)" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060575601" href="BookDescriptionServlet?isbn=9780060575601">Caravaggio: Painter Of Miracles (Eminent Lives)</a></p> <p>by Francine Prose</p> <br>
                                    <h4>Left : 10 | Published : 2005-10-01 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060595371" id="9780060595371" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/51UmXO9zU2L._SX220_.jpg" alt="Couldn't Keep It To Myself:  Wally Lamb And The Women Of York Correctional Institution (Testimonies From Our Imprisoned Sisters)" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060595371" href="BookDescriptionServlet?isbn=9780060595371">Couldn't Keep It To Myself:  Wally Lamb And The Women Of York Correctional Institution (Testimonies From Our Imprisoned Sisters)</a></p> <p>by Wally Lamb</p> <br>
                                    <h4>Left : 10 | Published : 2004-02-03 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060722005" id="9780060722005" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/518Ej2KUQlL._SX220_.jpg" alt="The Atkins Shopping Guide: Indispensable Tips And Guidelines For Successfully Stocking Your Low-carb Kitchen" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060722005" href="BookDescriptionServlet?isbn=9780060722005">The Atkins Shopping Guide: Indispensable Tips And Guidelines For Successfully Stocking Your Low-carb Kitchen</a></p> <p>by Atkins Health & Medical Information Serv</p> <br>
                                    <h4>Left : 10 | Published : 2004-04 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060740924" id="9780060740924" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/41eznIBvWTL._SX220_.jpg" alt="Why Do Clocks Run Clockwise?: An Imponderables Book (Imponderables Books)" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060740924" href="BookDescriptionServlet?isbn=9780060740924">Why Do Clocks Run Clockwise?: An Imponderables Book (Imponderables Books)</a></p> <p>by David Feldman</p> <br>
                                    <h4>Left : 10 | Published : 2005-03-01 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060787219" id="9780060787219" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/41WWYFS9A3L._SX220_.jpg" alt="Zorro LP: A Novel" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060787219" href="BookDescriptionServlet?isbn=9780060787219">Zorro LP: A Novel</a></p> <p>by Isabel Allende</p> <br>
                                    <h4>Left : 10 | Published : 2005-05-03 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060823337" id="9780060823337" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/41XFZEGP5EL._SX220_.jpg" alt="Francis Crick: Discoverer Of The Genetic Code (Eminent Lives) (rough Edge)" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060823337" href="BookDescriptionServlet?isbn=9780060823337">Francis Crick: Discoverer Of The Genetic Code (Eminent Lives) (rough Edge)</a></p> <p>by Matt Ridley</p> <br>
                                    <h4>Left : 10 | Published : 2006-06-13 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060878801" id="9780060878801" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/41%2B3eoYrDFL._SX220_.jpg" alt="Buddha: A Story Of Enlightenment" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060878801" href="BookDescriptionServlet?isbn=9780060878801">Buddha: A Story Of Enlightenment</a></p> <p>by Deepak Chopra</p> <br>
                                    <h4>Left : 10 | Published : 2007-05-01 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                            <tr>
                                <td>
                                    <a href="BookDescriptionServlet?isbn=9780060894085" id="9780060894085" class="thumbnail">
                                        <img onload="validateImgUrl(this.id)" name="bookImage" id="book" class="bookImage" src="http://ecx.images-amazon.com/images/I/517WA57C76L._SX220_.jpg" alt="Genome: The Autobiography Of A Species In 23 Chapters (P.S.)" style="width: 100px;">
                                    </a>
                                </td>
                                <td><p><a id="9780060894085" href="BookDescriptionServlet?isbn=9780060894085">Genome: The Autobiography Of A Species In 23 Chapters (P.S.)</a></p> <p>by Matt Ridley</p> <br>
                                    <h4>Left : 10 | Published : 2006-05-30 </h4>                       
                                </td>
                            </tr>
                            <!--<hr class="fancy">-->
                            
                        </table>
                        
                        
                        
                        
                        
                        <table border="1" cellpadding="5" cellspacing="5" align="center">
                            <tr>
                                
                                <td><a href="SearchServlet?page=1&searchTerm="><<</a></td>
                                
                                
                                
                                
                                <td>1</td>
                                
                                
                                
                                
                                
                                
                                
                                <td><a href="SearchServlet?page=2&searchTerm=">2</a>
                                </td>
                                
                                
                                
                                
                                
                                
                                <td><a href="SearchServlet?page=3&searchTerm=">3</a>
                                </td>
                                
                                
                                
                                
                                
                                
                                <td><a href="SearchServlet?page=4&searchTerm=">4</a>
                                </td>
                                
                                
                                
                                
                                
                                
                                <td><a href="SearchServlet?page=5&searchTerm=">5</a>
                                </td>
                                
                                
                                
                                
                                
                                <td><a href="SearchServlet?page=2&searchTerm=">Next</a>
                                </td>
                                
                                <td><a href="SearchServlet?page=62&searchTerm=">>></a></td>
                            </tr>
                        </table>
                        
                        
                        
                        
                    </div>
                    <div class="tab-pane fade dropdown">
                        
                        
                        <button class="btn btn-primary dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">Tutorials
                            <span class="caret"></span></button>
                        <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                            
                            <form action="demo_form.asp" method="get">
                                
                                
                                
                                <li role="presentation" class="divider"></li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="#">About Us</a></li>
                            </form>
                        </ul>
                    </div>
                </div>
            </div><!-- /.col-xs-12 main -->
        </div>
    </body>
</html>

<!--Form for clicking on an "item" (book)-->
<form id = "itemSelectionForm" name = "itemSelectionForm" action = "BookDescriptionServlet" method = "get">
    <input type = "hidden" id = "isbn" name = "isbn" value = "null">
</form>


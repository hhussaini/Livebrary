<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <a class="panel-default" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
            <div class="panel-heading">
                <h4 class="panel-title">
                    Sort By
                </h4>
            </div>
        </a>                            
        <div id="collapseTwo" class="panel-collapse collapse">
            <div class="panel-body">
                <ul class="scrollable-menu" role="menu" aria-labelledby="menu1" style="max-height:500px; overflow:auto;">
                    
                    <fieldset>
                        <li>
                            <button type="submit" class="link" name="rButton" id="rButton1" onclick="sortItems(1)" value="title"><span>Title</span></button>
                        </li>
                        <li>
                            <button type="submit" class="link" name="rButton" id="rButton1" onclick="sortItems(1)" value="author"><span>Author</span></button>
                        </li>
                        <li>
                            <button type="submit" class="link" name="rButton" id="rButton1" onclick="sortItems(1)" value="releaseDate"><span>Release Date</span></button>
                        </li>
                        <li>
                            <button type="submit" class="link" name="rButton" id="rButton1" onclick="sortItems(1)" value="addedToSite"><span>Added to Site</span></button>
                        </li>
                        <li>
                            <button type="submit" class="link" name="rButton" id="rButton1" onclick="sortItems(1)" value="mostPopular"><span>Most popular</span></button>
                        </li>                        
                    </fieldset>
                </ul>
            </div>
        </div>
    </div>
</div>

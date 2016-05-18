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
                        <input type="radio" name="sort" id="rButton1" value="title"> Title<br>
                        <input type="radio" name="sort" id="rButton2" value="author"> Author<br>
                        <input type="radio" name="sort" id="rButton3" value="releaseDate"> Release Date<br>
                        <input type="radio" name="sort" id="rButton4" value="addedToSite"> Added to Site<br>
                        <input type="radio" name="sort" id="rButton5" value="mostPopular"> Most popular<br>
                    </fieldset>
                </ul>
            </div>
        </div>
    </div>
</div>

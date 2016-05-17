<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="panel-group">
    <div class="panel panel-default">
        <a class="panel-default" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne">
            <div class="panel-heading">
                <h2 class="panel-title">
                    Categories
                </h2>
            </div>
        </a>
        <div id="collapseOne" class="panel-collapse collapse">
            <div class="panel-body" width="100%">
                <ul class="scrollable-menu" role="menu" aria-labelledby="menu1" style="max-height:500px; overflow:auto;">
                <c:forEach var="element" items="${results.categories}">
                     <li role="presentation">
                    <c:choose>
                        <c:when test="${results.selectedCategories.contains(element.value)}">
                            <input type="checkbox" role="menuitem" tabindex="-1" id="${element.key}" name="categories" value="${element.value}" checked="checked">
                        </c:when>    
                        <c:otherwise>
                            <input type="checkbox" role="menuitem" tabindex="-1" id="${element.key}" name="categories" value="${element.value}">
                        </c:otherwise>
                    </c:choose>
                    <label for="${element.key}">${element.key}</label> <br>
                     </li>
                </c:forEach>
                     </ul>
            </div>
        </div>
    </div>
</div>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<button class="btn btn-primary dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">Categories
    <span class="caret"></span>
</button>
<ul class="dropdown-menu scrollable-menu" role="menu" aria-labelledby="menu1" style="max-height:750px; overflow:auto;">
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
    <li role="presentation" class="divider"></li>
    <li role="presentation"><input role="menuitem" tabindex="-1" type="submit" value="Filter"></li>
    <br>
</ul>
<script>
    $('.dropdown-menu').click(function(e) {
        e.stopPropagation();
    });
</script>
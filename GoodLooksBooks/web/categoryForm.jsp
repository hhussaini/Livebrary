<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<button class="btn btn-primary dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">Categories
    <span class="caret"></span>
</button>
<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
    <c:forEach var="element" items="${results.categories}">
        <li role="presentation">
            <input type="checkbox" role="menuitem" tabindex="-1" id="${element.key}" name="categories" value="${element.value}">
            <label for="${element.key}">${element.key}</label> <br>
        </li>
    </c:forEach>
    <li role="presentation" class="divider"></li>
    <li role="presentation"><input role="menuitem" tabindex="-1" type="submit" value="Filter"></li>
</ul>
<script>
    $('.dropdown-menu').click(function(e) {
        e.stopPropagation();
    });
</script>
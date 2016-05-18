<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <a class="panel-default" data-toggle="collapse" data-parent="#accordion4" href="#collapseFour">
            <div class="panel-heading">
                <h4 class="panel-title">
                    Language
                </h4>
            </div>
        </a>                            
        <div id="collapseFour" class="panel-collapse collapse">
            <div class="panel-body">
                <ul class="scrollable-menu" role="menu" aria-labelledby="menu1" style="max-height:500px; overflow:auto;">
                    <li role="presentation">
                        <c:choose>
                            <c:when test="${results.selectedLanguages.contains('English')}">
                                <input type="checkbox" role="menuitem" tabindex="-1" id="${'English'}" name="English" value="${'English'}" checked="checked">
                            </c:when>    
                            <c:otherwise>
                                <input type="checkbox" role="menuitem" tabindex="-1" id="${'English'}" name="English" value="${'English'}">
                            </c:otherwise>
                        </c:choose>
                        <label for="${'English'}">English</label> 
                        <br>
                        <c:choose>
                            <c:when test="${results.selectedLanguages.contains('International')}">
                                <input type="checkbox" role="menuitem" tabindex="-1" id="${'International'}" name="International" value="${'International'}" checked="checked">
                            </c:when>    
                            <c:otherwise>
                                <input type="checkbox" role="menuitem" tabindex="-1" id="${'International'}" name="International" value="${'International'}">
                            </c:otherwise>
                        </c:choose>
                        <label for="${'International'}">International</label> <br>                        
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>


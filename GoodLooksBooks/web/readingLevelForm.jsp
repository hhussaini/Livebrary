<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <a class="panel-default" data-toggle="collapse" data-parent="#accordion5" href="#collapseFive">
            <div class="panel-heading">
                <h4 class="panel-title">
                    Reading Level
                </h4>
            </div>
        </a>                            
        <div id="collapseFive" class="panel-collapse collapse">
            <div class="panel-body">
                <ul class="scrollable-menu" role="menu" aria-labelledby="menu1" style="max-height:500px; overflow:auto;">
                    <li role="presentation">
                        <c:choose>
                            <c:when test="${results.readingLevels.contains('range1')}">
                                <input type="checkbox" role="menuitem" tabindex="-1" id="${'range1'}" name="range1" value="${'100'}" checked="checked">
                            </c:when>    
                            <c:otherwise>
                                <input type="checkbox" role="menuitem" tabindex="-1" id="${'range1'}" name="range1" value="${'100'}">
                            </c:otherwise>
                        </c:choose>
                        <label for="${'range1'}">100 pages & lower</label> 
                        <br>
                        
                        <c:choose>
                            <c:when test="${results.readingLevels.contains('range2')}">
                                <input type="checkbox" role="menuitem" tabindex="-1" id="${'range2'}" name="range2" value="${'500'}" checked="checked">
                            </c:when>    
                            <c:otherwise>
                                <input type="checkbox" role="menuitem" tabindex="-1" id="${'range2'}" name="range2" value="${'500'}">
                            </c:otherwise>
                        </c:choose>
                        <label for="${'range2'}">100 - 500 pages</label> 
                        <br>
                        
                        <c:choose>
                            <c:when test="${results.readingLevels.contains('range3')}">
                                <input type="checkbox" role="menuitem" tabindex="-1" id="${'range3'}" name="range3" value="${'1000'}" checked="checked">
                            </c:when>    
                            <c:otherwise>
                                <input type="checkbox" role="menuitem" tabindex="-1" id="${'range3'}" name="range3" value="${'1000'}">
                            </c:otherwise>
                        </c:choose>
                        <label for="${'range3'}">1,000 pages & up</label> <br>                             
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <a class="panel-default" data-toggle="collapse" data-parent="#accordion3" href="#collapseThree">
            <div class="panel-heading">
                <h4 class="panel-title">
                    Format
                </h4>
            </div>
        </a>                            
        <div id="collapseThree" class="panel-collapse collapse">
            <div class="panel-body">
                <ul class="scrollable-menu" role="menu" aria-labelledby="menu1" style="max-height:500px; overflow:auto;">
                    <li role="presentation">
                        <c:choose>
                            <c:when test="${results.selectedFormats.contains('paperback')}">
                                <input type="checkbox" role="menuitem" tabindex="-1" id="${'paperback'}" name="paperback" value="${'paperback'}" checked="checked">
                            </c:when>    
                            <c:otherwise>
                                <input type="checkbox" role="menuitem" tabindex="-1" id="${'paperback'}" name="paperback" value="${'paperback'}">
                            </c:otherwise>
                        </c:choose>
                        <label for="${'paperback'}">Paper back</label> 
                        <br>
                        <c:choose>
                            <c:when test="${results.selectedFormats.contains('ebook')}">
                                <input type="checkbox" role="menuitem" tabindex="-1" id="${'ebook'}" name="ebook" value="${'ebook'}" checked="checked">
                            </c:when>    
                            <c:otherwise>
                                <input type="checkbox" role="menuitem" tabindex="-1" id="${'ebook'}" name="ebook" value="${'ebook'}">
                            </c:otherwise>
                        </c:choose>
                        <label for="${'ebook'}">eBook</label> <br>                        
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>


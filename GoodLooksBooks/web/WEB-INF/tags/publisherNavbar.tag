<%-- 
    Document   : publisherNavbar
    Author     : Kevin Young
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="publisherIndex.jsp" class="pull-left">
                    <img alt="brand" src="assets/brand-icon.png">
                </a>
            </div>
            <ul class="nav navbar-nav" style="font-weight: bold;">
                <li><a href="publisherIndex.jsp">Publisher Home</a></li>
                <li><a href="publisherAbout.jsp">About</a></li>
                <li><a href="publisherAccount.jsp">Account</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right" style="font-weight: bold;">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Options<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="index.jsp">Sign Out</a></li>
                        <!-- <li role="separator" class="divider"></li> -->
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
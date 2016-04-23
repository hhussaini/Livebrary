<%-- 
    Document   : customerIndex
    Author     : Kevin Young
--%>

<jsp:include page="/header.jsp" />
<body>
    <jsp:include page="/customerNavbar.jsp" />
    <div class="glb-page">
        <jsp:include page="/logo.jsp" />
        <div class="container">
            <h1 class = "jumbotron">Welcome ${user.username}!<br></h1>
            <div class="col-sm-2"></div>
        </div>
    </div>
</div>
</body>
</html>

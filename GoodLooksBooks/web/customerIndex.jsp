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
            <h1 class="jumbotron">Welcome ${user.username}!<br></h1>
            <div class="col-xs-6 col-sm-5">
                <form action="AccountSummaryServlet" method="get">
                    <button type="input" class="btn btn-primary">
                        Your account summary
                    </button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>

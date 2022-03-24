<div class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="indexServlet">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarsToggle">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarsToggle">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="indexServlet">Cards</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Threads</a></li>
                <c:if test="${userName == null}">
                    <li class="nav-item"><a class="nav-link" href="logIn">Sign Up/In</a></li>
                </c:if>
                <c:if test="${userName != null}">
                    <li class="nav-item"><a class="nav-link" href="logOut">Log Out</a></li>
                </c:if>
            </ul>
            <ul class="navbar-nav ms-auto">
                <li>
                <form class="d-flex" action="searchEntity" method="GET">
                    <input class="form-control me-2" type="text" placeholder="Search..." id="searchTerm" name="searchTerm">
                    <button class="btn btn-light" type="submit">Search</button>
                </form>
                </li>
            </ul>
        </div>
    </div>
</div>

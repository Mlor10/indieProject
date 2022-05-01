<%@ include file="template/taglib.jsp" %>
<c:import url="template/head.jsp" />
<script type="text/javascript" class="init">
    $(document).ready( function () {
        $('.searchTable').DataTable();
    } );
</script>
<html>
<body>
    <div class="container">
        <div class="row">
            <c:import url="template/header.jsp" />
        </div>
        <div class="row">
            <c:import url="template/navigation.jsp" />
        </div>
        <c:if test="${(empty users) and (empty cards) and (empty threads)}">
            <h3 class="text-center pt-3">No Results Found</h3>
        </c:if>
        <c:if test="${not empty users}">
            <h3>Users</h3>
            <table class="searchTable display table table-hover">
                <thead>
                    <th>Username</th>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <th><a class="nav-link" href="profile?userName=${user.userName}">${user.userName}</a></th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty cards}">
            <h3>Cards</h3>
            <table class="searchTable display table table-hover">
                <thead>
                <th>Card Name</th>
                <th>Card's User</th>
                </thead>
                <tbody>
                <c:forEach var="card" items="${cards}">
                    <tr>
                        <th>${card.cardName}</th>
                        <th>${card.user.userName}</th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty threads}">
            <h3>Threads</h3>
            <table class="searchTable display table table-hover">
                <thead>
                <th>Thread Title</th>
                <th>Thread's Author</th>
                </thead>
                <tbody>
                <c:forEach var="thread" items="${threads}">
                    <tr>
                        <th>${thread.threadTitle}</th>
                        <th>${thread.user.userName}</th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
    <c:import url="template/bs-js.jsp" />

    <c:remove var="users" />
    <c:remove var="cards" />
    <c:remove var="threads" />
</body>
</html>

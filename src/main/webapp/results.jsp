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
        <c:if test="${users != null}">
            <h3>Users</h3>
            <table class="searchTable display table table-hover">
                <thead>
                    <th>Username</th>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <th>${user.userName}</th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${cards != null}">
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
                        <th>${card.user}</th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${threads != null}">
            <h3>Threads</h3>
            <table class="searchTable display table table-hover">
                <thead>
                <th>Thread Title</th>
                <th>Thread User</th>
                </thead>
                <tbody>
                <c:forEach var="thread" items="${threads}">
                    <tr>
                        <th>${thread.threadTitle}</th>
                        <th>${thread.user}</th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
    <c:import url="template/bs-js.jsp" />
</body>
</html>

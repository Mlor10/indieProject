<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/head.jsp"%>
<script type="text/javascript" class="init">
    $(document).ready( function () {
        $('.searchTable').DataTable();
    } );
</script>
<html>
<body>
    <div class="container">
        <div class="row">
            <%@include file="template/header.jsp"%>
        </div>
        <div class="row">
            <%@include file="template/navigation.jsp"%>
        </div>
        <h3>Users</h3>
        <c:if test="${users != null}">
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
        <h3>Cards</h3>
        <c:if test="${cards != null}">
            <table class="searchTable display table table-hover">
                <thead>
                <th>Card Name</th>
                </thead>
                <tbody>
                <c:forEach var="card" items="${cards}">
                    <tr>
                        <th>${card.cardName}</th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
    <%@include file="template/bs-js.jsp" %>
</body>
</html>

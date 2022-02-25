<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<script type="text/javascript" class="init">
    $(document).ready( function () {
        $('#searchTable').DataTable();
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
        <h2>Search Results: </h2>
        <c:if test="${users != null}">
            <table id="searchTable" class="display table table-hover">
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
    </div>

</body>
</html>

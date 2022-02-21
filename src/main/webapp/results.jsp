<%@include file="template/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><body>

<div class="container-fluid">
    <h2>Search Results: </h2>
    <c:if test="${users != null}">
        <table class="table table-dark table-hover">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Date of Birth</th>
                <th>Username</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <th>${user.id}</th>
                    <th>${user.firstName}</th>
                    <th>${user.lastName}</th>
                    <th>${user.dateOfBirth}</th>
                    <th>${user.userName}</th>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <h3 class="text-center"><a href="index.jsp">Go Back</a></h3>
</div>

</body>
</html>

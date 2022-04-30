<%@ include file="template/taglib.jsp" %>
<c:import url="template/head.jsp" />
<html>
<body>
<div class="container">
    <div class="row">
        <c:import url="template/header.jsp" />
    </div>

    <div class="row">
        <c:import url="template/navigation.jsp" />
    </div>
        <h3 class="text-center">User Profile</h3>
        <table class="table table-hover">
            <tr>
                <th>Username</th>
                <th>${userProfile.userName}</th>
            </tr>
            <tr>
                <th>First Name</th>
                <th>${userProfile.firstName}</th>
            </tr>
            <tr>
                <th>Last Name</th>
                <th>${userProfile.lastName}</th>
            </tr>
            <tr>
                <th>Email</th>
                <th>${userProfile.userEmail}</th>
            </tr>
            <tr>
                <th>Date of Birth</th>
                <th>${userProfile.dateOfBirth}</th>
            </tr>
        </table>
</div>

<c:import url="template/bs-js.jsp" />
</body>
</html>
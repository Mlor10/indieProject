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
        <h3 class="text-center mt-3">User Profile</h3>
    <div class="row align-items-center border border-dark">
        <div class="col-3">
            <c:import url="template/components/userprofile/userprofile-image.jsp" />
        </div>
        <div class="col-9">
            <table class="table table-hover">
                <tr>
                    <c:import url="template/components/userprofile/userprofile-username.jsp" />
                </tr>
                <tr>
                    <c:import url="template/components/userprofile/userprofile-firstname.jsp" />
                </tr>
                <tr>
                    <c:import url="template/components/userprofile/userprofile-lastname.jsp" />
                </tr>
                <tr>
                    <c:import url="template/components/userprofile/userprofile-email.jsp" />
                </tr>
                <tr>
                    <c:import url="template/components/userprofile/userprofile-dateofbirth.jsp" />
                </tr>
            </table>
        </div>
    </div>
</div>

<c:import url="template/bs-js.jsp" />
</body>
</html>
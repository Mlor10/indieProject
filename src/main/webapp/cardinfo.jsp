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
        <h3 class="text-center mt-3">Card Info</h3>
    <div class="row align-items-center border border-dark">
        <div class="col-4">
            <c:import url="template/components/cardinfo/cardinfo-image.jsp" />
        </div>
        <div class="col-8">
            <table class="table table-hover">
                <tr>
                    <c:import url="template/components/cardinfo/cardinfo-name.jsp" />
                </tr>
                <tr>
                    <c:import url="template/components/cardinfo/cardinfo-price.jsp" />
                </tr>
                <tr>
                    <c:import url="template/components/cardinfo/cardinfo-description.jsp" />
                </tr>
                <tr>
                    <c:import url="template/components/cardinfo/cardinfo-user.jsp" />
                </tr>
                <c:import url="template/components/cardinfo/cardinfo-delete.jsp" />
            </table>
        </div>
    </div>
</div>

<c:import url="template/bs-js.jsp" />
</body>
</html>
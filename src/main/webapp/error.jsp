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
    <c:if test="${errorMessage != null}">
        <h1 class="text-center">${errorMessage}</h1>
    </c:if>
    <c:if test="${errorMessage = null}">
        <h1 class="text-center">ERROR</h1>
    </c:if>
</div>
</body>
<c:import url="template/bs-js.jsp" />
</html>


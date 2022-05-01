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
    <c:if test="${empty userName}">
        <h2 class="text-center">Welcome</h2>
    </c:if>
    <c:if test="${not empty userName}">
        <h2 class="text-center">Welcome ${userName}</h2>
    </c:if>
</div>

<c:import url="template/bs-js.jsp" />
</body>
</html>
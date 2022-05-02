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

    <div class="row">
    <c:if test="${userName == null}">
        <h2 class="text-center">Welcome</h2>
    </c:if>
    <c:if test="${userName != null}">
        <h2 class="text-center">Welcome ${userName}</h2>
    </c:if>
    </div>
</div>

<c:import url="template/bs-js.jsp" />
</body>
</html>
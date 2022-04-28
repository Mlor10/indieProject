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
    <c:if test="${userName == null}">
        <h2 class="text-center">Threads</h2>
    </c:if>
    <c:if test="${userName != null}">
        <h2 class="text-center">Threads ${userName}</h2>
    </c:if>
</div>

<c:import url="template/bs-js.jsp" />
</body>
</html>
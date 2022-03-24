<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/head.jsp"%>
<html>
<body>
<div class="container">
    <div class="row">
        <%@include file="template/header.jsp"%>
    </div>

    <div class="row">
        <%@include file="template/navigation.jsp"%>
    </div>
    <c:if test="${userName == null}">
        <h2 class="text-center">Welcome</h2>
    </c:if>
    <c:if test="${userName != null}">
        <h2 class="text-center">Welcome ${userName}</h2>
    </c:if>
</div>

<%@include file="template/bs-js.jsp" %>
</body>
</html>
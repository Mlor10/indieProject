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
        <h3 class="text-center mt-3">${currentThread.threadTitle}</h3>

    <c:import url="template/components/userthread/userthread-editcontent.jsp" />

    <c:import url="template/components/userthread/userthread-createreply.jsp" />

    <c:import url="template/components/userthread/userthread-content.jsp" />

    <c:import url="template/components/userthread/userthread-reply.jsp" />
</div>

<c:import url="template/bs-js.jsp" />
</body>
</html>
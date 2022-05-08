<%@ include file="/template/taglib.jsp" %>
<div class="row d-flex border border-dark">
    <div class="col-2">
        <div class="row py-2 text-center bg-dark text-white">
            <a class="link-info text-decoration-none" href="profile?userName=${currentThread.user.userName}">${currentThread.user.userName}</a>
        </div>
        <div class="row">
            <c:if test="${empty currentThread.user.userImage}">
                <div class="text-center py-5"><i class="bi bi-person-bounding-box display-1"></i></div>
            </c:if>
            <c:if test="${not empty currentThread.user.userImage}">
                <img src="${currentThread.user.userImage}" alt="${currentThread.user.userName}-profile-image" class="img-fluid">
            </c:if>
        </div>
    </div>
    <div class="col-10">
        <div class="row py-2 justify-content-end bg-dark text-warning">
            <div class="col-9"></div>
            <div class="col-3">
                <fmt:parseDate value="${currentThread.threadDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedThreadDateTime" type="both" />
                <fmt:formatDate pattern="MM.dd.yyyy HH:mm" value="${parsedThreadDateTime}" />
            </div>
        </div>
        <div class="row text-wrap">
            ${currentThread.threadContent}
        </div>
    </div>
</div>
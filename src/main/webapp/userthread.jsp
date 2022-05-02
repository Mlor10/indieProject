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

    <c:if test="${userName != null}">
        <div class="row mb-2">
            <div class="col-auto">
                <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#createReplyCollapse">
                    <p><i class="bi bi-plus-square"></i> Post a Reply</p>
                </button>
            </div>
            <div class="col-10">
                <div class="collapse" id="createReplyCollapse">
                    <div class="card card-body">
                        <form class="row form-inline" action="create" method="POST">
                            <input type="hidden" name="createObject" value="reply">
                            <input type="hidden" name="threadId" value="${currentThread.id}">
                            <div class="col-10">
                                <label for="title">Title<span class="text-danger">*</span></label>
                                <input type="text" class="form-control mb-2" id="title" name="replyTitle" required>
                                <textarea class="form-control mb-2" id="content" rows="3" name="replyContent" required></textarea>
                            </div>
                            <div class="col-2 align-self-end">
                                <button type="submit" class="btn btn-primary mb-2">Post Reply</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

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

    <c:forEach var="currentThreadReply" items="${currentThread.sortReply(currentThread.replies)}">
        <div class="row d-flex border border-dark">
            <div class="col-2">
                <div class="row py-2 text-center bg-dark text-white">
                    <a class="link-info text-decoration-none" href="profile?userName=${currentThreadReply.user.userName}">${currentThreadReply.user.userName}</a>
                </div>
                <div class="row">
                    <c:if test="${empty currentThreadReply.user.userImage}">
                        <div class="text-center py-5"><i class="bi bi-person-bounding-box display-1"></i></div>
                    </c:if>
                    <c:if test="${not empty currentThreadReply.user.userImage}">
                        <img src="${currentThreadReply.user.userImage}" alt="${currentThreadReply.user.userName}-profile-image" class="img-fluid">
                    </c:if>
                </div>
            </div>
            <div class="col-10">
                <div class="row py-2 bg-dark text-white">
                    <div class="col-9 text-light">
                            ${currentThreadReply.replyTitle}
                    </div>
                    <div class="col-3 bg-dark text-warning">
                        <fmt:parseDate value="${currentThreadReply.replyDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedReplyDateTime" type="both" />
                        <fmt:formatDate pattern="MM.dd.yyyy HH:mm" value="${parsedReplyDateTime}" />
                    </div>
                </div>
                <div class="row text-wrap">
                    ${currentThreadReply.replyContent}
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<c:import url="template/bs-js.jsp" />
</body>
</html>
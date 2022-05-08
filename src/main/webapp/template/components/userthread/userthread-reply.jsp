<%@ include file="/template/taglib.jsp" %>
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
            <div class="row h-75 d-flex-inline text-wrap">
                <div class="col-12">
                        ${currentThreadReply.replyContent}
                </div>
                <c:if test="${userName != null and (currentThreadReply.user.userName == userName or adminPermission == 'true')}">
                    <div class="col-12 align-self-end">
                        <div class="row mb-2">
                            <div class="col-auto">
                                <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#editReplyCollapse${currentThreadReply.id}">
                                    <p><i class="bi bi-pencil"></i> Edit this Reply</p>
                                </button>
                            </div>
                            <div class="col-10">
                                <div class="collapse" id="editReplyCollapse${currentThreadReply.id}">
                                    <div class="card card-body">
                                        <form class="row form-inline" action="update" method="POST">
                                            <input type="hidden" name="updateObject" value="reply">
                                            <input type="hidden" name="threadId" value="${currentThread.id}">
                                            <input type="hidden" name="updateObjectId" value="${currentThreadReply.id}">
                                            <div class="col-10">
                                                <label for="editReplyTitle">Title</label>
                                                <input type="text" class="form-control mb-2" id="editReplyTitle" name="replyTitle">
                                                <textarea class="form-control mb-2" id="editReplyContent" rows="3" name="replyContent"></textarea>
                                            </div>
                                            <div class="col-2 align-self-end">
                                                <button type="submit" class="btn btn-primary mb-2">Submit Changes</button>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card card-body">
                                        <form class="row form-inline" action="delete" method="POST">
                                            <input type="hidden" name="deleteObject" value="reply">
                                            <input type="hidden" name="threadId" value="${currentThread.id}">
                                            <input type="hidden" name="deleteObjectId" value="${currentThreadReply.id}">
                                            <div class="col-2 align-self-end">
                                                <button type="submit" class="btn btn-warning mb-2"><p><i class="bi bi-trash"></i> Delete Reply</p></button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</c:forEach>
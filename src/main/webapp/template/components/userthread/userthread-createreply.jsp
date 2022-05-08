<%@ include file="/template/taglib.jsp" %>
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
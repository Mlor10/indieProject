<%@ include file="/template/taglib.jsp" %>
<c:if test="${userName != null and (currentThread.user.userName == userName or adminPermission == 'true')}">
    <div class="row mb-2">
        <div class="col-auto">
            <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#editThreadCollapse">
                <p><i class="bi bi-pencil"></i> Edit this Thread</p>
            </button>
        </div>
        <div class="col-10">
            <div class="collapse" id="editThreadCollapse">
                <div class="card card-body">
                    <form class="row form-inline" action="update" method="POST">
                        <input type="hidden" name="updateObject" value="thread">
                        <input type="hidden" name="updateObjectId" value="${currentThread.id}">
                        <div class="col-10">
                            <label for="threadTitle">Title</label>
                            <input type="text" class="form-control mb-2" id="threadTitle" name="threadTitle">
                            <textarea class="form-control mb-2" id="threadContent" rows="5" name="threadContent"></textarea>
                        </div>
                        <div class="col-2 align-self-end">
                            <button type="submit" class="btn btn-primary mb-2">Submit Changes</button>
                        </div>
                    </form>
                </div>
                <div class="card card-body">
                    <form class="row form-inline" action="delete" method="POST">
                        <input type="hidden" name="deleteObject" value="thread">
                        <input type="hidden" name="deleteObjectId" value="${currentThread.id}">
                        <div class="col-2 align-self-end">
                            <button type="submit" class="btn btn-warning mb-2"><p><i class="bi bi-trash"></i> Delete Thread</p></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</c:if>
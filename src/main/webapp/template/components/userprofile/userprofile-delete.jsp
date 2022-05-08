<%@ include file="../../taglib.jsp" %>
<c:if test="${(userName != null) and ((userName == userProfile.userName) or adminPermission == 'true')}">
    <tr>
        <div class="card card-body">
            <form class="row form-inline" action="delete" method="POST">
                <input type="hidden" name="deleteObject" value="user">
                <input type="hidden" name="deleteObjectId" value="${userProfile.id}">
                <div class="col-2 align-self-end">
                    <button type="submit" class="btn btn-warning mb-2"><p><i class="bi bi-trash"></i> Delete User</p></button>
                </div>
            </form>
        </div>
    </tr>
</c:if>
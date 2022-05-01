<%@ include file="/template/taglib.jsp" %>
<th>First Name</th>
<td>${userProfile.firstName}</td>
<td>
    <c:if test="${(not empty userName) and (userName == userProfile.userName)}">
        <div class="row">
            <div class="col-auto">
                <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#firstNameCollapse">
                    <i class="bi bi-pencil"></i>
                </button>
            </div>
            <div class="col">
                <div class="collapse" id="firstNameCollapse">
                    <div class="card card-body">
                        <form class="row form-inline" action="update" method="GET">
                            <input type="hidden" name="updateObject" value="user">
                            <input type="hidden" name="updateType" value="firstName">
                            <div class="col-auto">
                                <input type="text" class="form-control mb-2" id="firstName" name="updateValue" placeholder="First Name">
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-primary mb-2">Submit Change</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</td>

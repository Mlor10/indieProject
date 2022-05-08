<%@ include file="/template/taglib.jsp" %>
<th>Card Name</th>
<td>${currentCard.cardName}</td>
<td>
    <c:if test="${adminPermission == 'true'}">
        <div class="row">
            <div class="col-auto">
                <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#cardNameCollapse">
                    <i class="bi bi-pencil"></i>
                </button>
            </div>
            <div class="col">
                <div class="collapse" id="cardNameCollapse">
                    <div class="card card-body">
                        <form class="row form-inline" action="update" method="POST">
                            <input type="hidden" name="updateObject" value="card">
                            <input type="hidden" name="updateObjectId" value="${currentCard.id}">
                            <input type="hidden" name="updateType" value="cardName">
                            <div class="col-auto">
                                <input type="text" class="form-control mb-2" id="cardName" name="updateValue" placeholder="Card Name">
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

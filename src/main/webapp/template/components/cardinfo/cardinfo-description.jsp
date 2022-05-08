<%@ include file="/template/taglib.jsp" %>
<th>Card Description</th>
<td>${currentCard.cardDescription}</td>
<td>
    <c:if test="${(userName != null) and ((userName == card.user.userName) or adminPermission == 'true')}">
        <div class="row">
            <div class="col-auto">
                <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#cardDescriptionCollapse">
                    <i class="bi bi-pencil"></i>
                </button>
            </div>
            <div class="col">
                <div class="collapse" id="cardDescriptionCollapse">
                    <div class="card card-body">
                        <form class="row form-inline" action="update" method="POST">
                            <input type="hidden" name="updateObject" value="card">
                            <input type="hidden" name="updateObjectId" value="${currentCard.id}">
                            <input type="hidden" name="updateType" value="cardDescription">
                            <div class="col-auto">
                                <textarea type="text" class="form-control mb-2" id="cardDescription" name="updateValue" rows="3" placeholder="Card Description"></textarea>
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

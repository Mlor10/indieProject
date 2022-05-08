<%@ include file="/template/taglib.jsp" %>
<fmt:setLocale value = "en_US"/>
<th>Card Price</th>
<td><fmt:formatNumber type="currency" value="${currentCard.cardPrice}" /></td>
<td>
    <c:if test="${(userName != null) and ((userName == currentCard.user.userName) or adminPermission == 'true')}">
        <div class="row">
            <div class="col-auto">
                <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#cardPriceCollapse">
                    <i class="bi bi-pencil"></i>
                </button>
            </div>
            <div class="col">
                <div class="collapse" id="cardPriceCollapse">
                    <div class="card card-body">
                        <form class="row form-inline" action="update" method="POST">
                            <input type="hidden" name="updateObject" value="card">
                            <input type="hidden" name="updateObjectId" value="${currentCard.id}">
                            <input type="hidden" name="updateType" value="cardPrice">
                            <div class="col-auto">
                                <input type="number" class="form-control mb-2" id="cardPrice" name="updateValue" min="0" step=".01">
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

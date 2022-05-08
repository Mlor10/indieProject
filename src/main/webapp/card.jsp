<%@ include file="template/taglib.jsp" %>
<c:import url="template/head.jsp" />
<fmt:setLocale value = "en_US"/>
<script type="text/javascript" class="init">
    $(document).ready( function () {
        $('.searchTable').DataTable();
    } );
</script>
<html>
<body>
<div class="container">
    <div class="row">
        <c:import url="template/header.jsp" />
    </div>

    <div class="row">
        <c:import url="template/navigation.jsp" />
    </div>

    <div class="row">
        <h2 class="text-center mt-3">Cards</h2>
    </div>

    <c:if test="${userName != null}">
        <div class="row mb-2">
            <form class="col" action="cardselector" method="GET">
                <input type="hidden" name="createObject" value="card">
                <button type="submit" class="btn btn-primary mb-2">
                    <p><i class="bi bi-plus-square"></i> Post a Card</p>
                </button>
            </form>
        </div>
    </c:if>

    <div class="row">
        <c:if test="${not empty cards}">
            <table class="searchTable display table table-hover">
                <thead>
                <tr>
                    <th>Card Name</th>
                    <th>Card Description</th>
                    <th>Card Price</th>
                    <th>Card's User</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="card" items="${cards}">
                    <tr>
                        <td>
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <div class="row">
                                        <c:if test="${empty card.cardImage}">
                                            <a class="text-decoration-none" href="card?cardId=${card.id}"><i class="bi bi-question-square"></i></a>
                                        </c:if>
                                        <c:if test="${not empty card.cardImage}">
                                            <a class="text-decoration-none" href="card?cardId=${card.id}"><img src="${card.cardImage}" alt="${card.cardName}-card-image"></a>
                                        </c:if>
                                    </div>
                                    <div class="row text-center mt-1 fs-5">
                                        <a class="text-decoration-none" href="card?cardId=${card.id}">${card.cardName}</a>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td>${card.cardDescription}</td>
                        <td><fmt:formatNumber type="currency" value="${card.cardPrice}" /></td>
                        <td><a class="text-decoration-none" href="profile?userName=${card.user.userName}">${card.user.userName}</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>

<c:import url="template/bs-js.jsp" />
</body>
</html>
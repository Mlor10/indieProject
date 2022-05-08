<%@ include file="../../taglib.jsp" %>
<c:if test="${empty currentCard.cardImage}">
    <div class="text-center"><i class="bi bi-question-square"></i></div>
</c:if>
<c:if test="${not empty currentCard.cardImage}">
    <img src="${currentCard.cardImage}" alt="${currentCard.cardName}-card-image">
</c:if>
<%@ include file="template/taglib.jsp" %>
<c:import url="template/head.jsp" />
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
                        <td>${card.cardName}</td>
                        <td>${card.cardDescription}</td>
                        <td>${card.cardPrice}</td>
                        <td><a href="profile?userName=${card.user.userName}">${card.user.userName}</a></td>
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
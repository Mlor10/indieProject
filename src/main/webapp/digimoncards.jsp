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
        <h3 class="text-center pt-3">Select your card to put up for sale/trade</h3>
        <c:if test="${not empty digimonCards}">
            <table class="searchTable display table table-hover">
                <thead>
                    <tr>
                        <th class="text-center">Digimon Cards</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="digimonCard" items="${digimonCards}">
                    <tr>
                        <td class=row">
                            <div class="col">
                                <c:if test="${digimonCard.image_url == null}">
                                    <div class="text-center"><i class="bi bi-question-square"></i></div>
                                </c:if>
                                <c:if test="${digimonCard.image_url != null}">
                                    <img src="${digimonCard.image_url}" alt="${digimonCard.name}-image" style="height: 182px" class="img-fluid">
                                </c:if>
                            </div>
                            <div class="col">
                                <div class="row">
                                    <h3 class="col-3">${digimonCard.name}</h3>
                                    <form class="col-3" action="create" method="POST">
                                        <input type="hidden" name="createObject" value="card">
                                        <input type="hidden" name="cardName" value="${digimonCard.name}">
                                        <input type="hidden" name="cardImage" value="${digimonCard.image_url}">
                                        <div class="col-auto">
                                            <button type="submit" class="btn btn-primary mb-2">Select Card</button>
                                        </div>
                                    </form>
                                    <div class="col-6"></div>
                                </div>
                            </div>
                        </td>
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
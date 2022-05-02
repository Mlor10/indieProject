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
        <h3 class="text-center pt-3">Select an image for your profile</h3>
        <c:if test="${not empty digimons}">
            <table class="searchTable display table table-hover">
                <thead>
                    <tr>
                        <th class="text-center">Digimon Images</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="digimon" items="${digimons}">
                    <tr>
                        <td class=row">
                            <div class="col">
                                <img src="${digimon.img}" alt="${digimon.name} image" style="height: 160px" class="img-fluid">
                            </div>
                            <div class="col">
                                <div class="row">
                                    <h3 class="col-3">${digimon.name}</h3>
                                    <form class="col-3" action="update" method="POST">
                                        <input type="hidden" name="updateObject" value="user">
                                        <input type="hidden" name="updateType" value="profileImage">
                                        <input type="hidden" name="updateValue" value="${digimon.img}">
                                        <div class="col-auto">
                                            <button type="submit" class="btn btn-primary mb-2">Select Image</button>
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
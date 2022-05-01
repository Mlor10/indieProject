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
    <h3 class="text-center pt-3">Select an image for your profile</h3>
    <c:if test="${not empty digimons}">
        <table class="searchTable display table table-hover mt-3">
            <thead>
                <th class="text-center">Digimon Images</th>
            </thead>
            <tbody>
            <c:forEach var="digimon" items="${digimons}">
                <tr>
                    <th class=row">
                        <div class="col">
                            <img src="${digimon.img}" alt="${digimon.name} image">
                        </div>
                        <div class="col">
                            <div class="row">
                                <h2 class="col">${digimon.name}</h2>
                                <form class="col" action="update" method="GET">
                                    <input type="hidden" name="updateObject" value="user">
                                    <input type="hidden" name="updateType" value="profileImage">
                                    <input type="hidden" name="updateValue" value="${digimon.img}">
                                    <div class="col-auto">
                                        <button type="submit" class="btn btn-primary mb-2">Select Image</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<c:import url="template/bs-js.jsp" />
</body>
</html>
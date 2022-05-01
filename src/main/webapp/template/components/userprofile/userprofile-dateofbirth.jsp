<%@ include file="../../taglib.jsp" %>
<th>Date of Birth</th>
<th>
    <fmt:parseDate type="date" pattern="yyyy-MM-dd" var="dateOfBirthParsed" value="${userProfile.dateOfBirth}"/>
    <fmt:formatDate pattern="MM-dd-yyyy" value="${dateOfBirthParsed}" />
</th>
<th>
    <c:if test="${(not empty userName) and (userName == userProfile.userName)}">
        <div class="row">
            <div class="col-auto">
                <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#dateOfBirthCollapse">
                    <i class="bi bi-pencil"></i>
                </button>
            </div>
            <div class="col">
                <div class="collapse" id="dateOfBirthCollapse">
                    <div class="card card-body">
                        <form class="row form-inline" action="update" method="GET">
                            <input type="hidden" name="updateObject" value="user">
                            <input type="hidden" name="updateType" value="dateOfBirth">
                            <div class="col-auto">
                                <input type="date" class="form-control mb-2" id="dateOfBirth" name="updateValue">
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
</th>

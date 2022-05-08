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
            <c:if test="${(empty users) and (empty cards) and (empty threads)}">
                <h3 class="text-center mt-3">No Results Found</h3>
            </c:if>
            <c:if test="${not empty users}">
                <h3>Users</h3>
                <table class="searchTable display table table-hover">
                    <thead>
                        <tr>
                            <th>Username</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>
                                <div class="row align-items-center">
                                    <div class="col-1">
                                        <c:if test="${empty user.userImage}">
                                            <i class="bi bi-person-bounding-box display-1"></i>
                                        </c:if>
                                        <c:if test="${not empty user.userImage}">
                                            <img src="${user.userImage}" alt="${user.userName}-profile-image" style="height: 80px" class="img-fluid">
                                        </c:if>
                                    </div>
                                    <div class="col-auto">
                                        <a class="text-decoration-none" href="profile?userName=${user.userName}">${user.userName}</a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
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
            <c:if test="${not empty threads}">
                <h3>Threads</h3>
                <table class="searchTable display table table-hover">
                    <thead>
                        <tr>
                            <th>Thread Title</th>
                            <th>Thread Replies</th>
                            <th>Thread Views</th>
                            <th>Thread Date</th>
                            <th>Thread's Author</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="thread" items="${threads}">
                        <tr>
                            <td>
                                <a class="text-decoration-none" href="thread?threadId=${thread.id}">${thread.threadTitle}</a>
                            </td>
                            <td>${thread.replies.size()}</td>
                            <td>${thread.threadViews}</td>
                            <td>
                                <fmt:parseDate value="${thread.threadDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedThreadDate" type="both" />
                                <fmt:formatDate pattern="MM.dd.yyyy HH:mm" value="${parsedThreadDate}" />
                            </td>
                            <td><a class="text-decoration-none" href="profile?userName=${thread.user.userName}">${thread.user.userName}</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
    <c:import url="template/bs-js.jsp" />

    <c:remove var="users" />
    <c:remove var="cards" />
    <c:remove var="threads" />
</body>
</html>

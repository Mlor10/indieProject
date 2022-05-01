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
            <c:if test="${(empty users) and (empty cards) and (empty threads)}">
                <h3 class="text-center pt-3">No Results Found</h3>
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
                                        <a class="nav-link" href="profile?userName=${user.userName}">${user.userName}</a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${not empty cards}">
                <h3>Cards</h3>
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
                            <td><a class="nav-link" href="profile?userName=${card.user.userName}">${card.user.userName}</a></td>
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
                            <td>${thread.threadTitle}</td>
                            <td>${thread.replies.size()}</td>
                            <td>${thread.threadViews}</td>
                            <td>${thread.threadDate}</td>
                            <td><a class="nav-link" href="profile?userName=${thread.user.userName}">${thread.user.userName}</a></td>
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

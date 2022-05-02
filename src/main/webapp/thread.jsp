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
        <h2 class="text-center mt-3">Threads</h2>
    </div>

    <c:if test="${userName != null}">
        <div class="row mb-2">
            <div class="col-auto">
                <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#createThreadCollapse">
                    <p><i class="bi bi-plus-square"></i> Post a Thread</p>
                </button>
            </div>
            <div class="col-10">
                <div class="collapse" id="createThreadCollapse">
                    <div class="card card-body">
                        <form class="row form-inline" action="create" method="POST">
                            <input type="hidden" name="createObject" value="thread">
                            <div class="col-10">
                                <label for="title">Title<span class="text-danger">*</span></label>
                                <input type="text" class="form-control mb-2" id="title" name="threadTitle" required>
                                <textarea class="form-control mb-2" id="content" rows="5" name="threadContent" required></textarea>
                            </div>
                            <div class="col-2 align-self-end">
                                <button type="submit" class="btn btn-primary mb-2">Post Thread</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <div class="row">
        <c:if test="${not empty threads}">
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
</body>
</html>
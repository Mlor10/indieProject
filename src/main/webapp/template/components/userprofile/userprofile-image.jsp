<%@ include file="../../taglib.jsp" %>
<c:if test="${(empty userProfile.userImage) and ((userName != userProfile.userName) and adminPermission != 'true')}">
    <div class="text-center"><i class="bi bi-person-bounding-box display-1"></i></div>
</c:if>
<c:if test="${(empty userProfile.userImage) and ((userName == userProfile.userName) or adminPermission == 'true')}">
    <div class="text-center"><a href="profileimages?userId=${userProfile.id}"><i class="bi bi-person-bounding-box display-1"></i></a></div>
</c:if>
<c:if test="${(not empty userProfile.userImage) and ((userName != userProfile.userName) and adminPermission != 'true')}">
    <img src="${userProfile.userImage}" alt="${userProfile.userName}-profile-image" class="img-fluid">
</c:if>
<c:if test="${(not empty userProfile.userImage) and ((userName == userProfile.userName) or adminPermission == 'true')}">
    <a href="profileimages?userId=${userProfile.id}"><img src="${userProfile.userImage}" alt="${userProfile.userName}-profile-image" class="img-fluid"></a>
</c:if>
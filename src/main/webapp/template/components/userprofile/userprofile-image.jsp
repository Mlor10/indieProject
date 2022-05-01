<%@ include file="../../taglib.jsp" %>
<c:if test="${(empty userProfile.userImage) and (userName != userProfile.userName)}">
    <div class="text-center"><i class="bi bi-person-bounding-box display-1"></i></div>
</c:if>
<c:if test="${(empty userProfile.userImage) and (userName == userProfile.userName)}">
    <div class="text-center"><a href="profileimages"><i class="bi bi-person-bounding-box display-1"></i></a></div>
</c:if>
<c:if test="${(not empty userProfile.userImage) and (userName != userProfile.userName)}">
    <img src="${userProfile.userImage}" alt="${userProfile.userName}-profile-image" class="img-fluid">
</c:if>
<c:if test="${(not empty userProfile.userImage) and (userName == userProfile.userName)}">
    <a href="profileimages"><img src="${userProfile.userImage}" alt="${userProfile.userName}-profile-image" class="img-fluid"></a>
</c:if>
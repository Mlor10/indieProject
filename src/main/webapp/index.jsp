<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp" />
<html>
<body class="container-fluid">

<h2 class="text-center">Search</h2>

<form action="searchUser" method="GET">
    <div class="form-group">
        <label for="searchTerm" class="fw-bold fs-6 mb-2">Search Term - Last Name</label>
        <input type="text" class="form-control" id="searchTerm" name="searchTerm" value="">
    </div>
    <button type="submit" class="bg-success text-white m-3">Submit</button>
    <button type="reset" class="m-3">Clear</button>
</form>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
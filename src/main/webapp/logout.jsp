
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<form class="container" action="logout" method="POST">

    Are you sure to logout?
    <div class="row">
        <div class="col-3">
            <button type="submit" class="btn btn-primary mt-3">YES</button>
            <a href="login">NO</a>
        </div>
    </div>

</form>

</body>
</html>

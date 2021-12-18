<%--
  Created by IntelliJ IDEA.
  User: Odessa_Bear
  Date: 14.12.2021
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TAKOGONET</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/app.css" rel="stylesheet">
</head>

<body>
<header>
    <jsp:include page="fragment/header.jsp"/>
</header>
<div class="container-fluid">
    <div class="row">
        <aside class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
            <jsp:include page="fragment/aside.jsp"/>
        </aside>
        <main class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
            <jsp:include page="${currentPage}"/>
        </main>
    </div>
</div>
<br>

<footer class="footer">
    <jsp:include page="fragment/footer.jsp"/>
</footer>
<script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/static/js/app.js"></script>
</body>

</html>

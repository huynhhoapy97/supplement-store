<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="utf-8">
	<title>Trang chủ</title>

	<link href="resources/bootstrap/bootstrap.min.css" rel="stylesheet">
	<link href="resources/css/template.css" rel="stylesheet">
</head>
<body>
    <div class="main-view container-fluid">
        <div class="header bg-dark">
        </div>
        <div class="body position-relative">
            <jsp:include page="${pageName}"></jsp:include>
        </div>
        <div class="footer bg-dark">
        </div>
    </div>
</body>
</html>
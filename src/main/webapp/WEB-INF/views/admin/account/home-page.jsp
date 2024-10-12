<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/admin/">
	<meta charset="utf-8">
	<title>Trang chủ</title>
</head>
<body>
    <div class="mainview">
        <div class="header">

        </div>
        <div class="body">
            <jsp:include page="${pageName}"></jsp:include>
        </div>
        <div class="footer">
        </div>
    </div>
</body>
</html>
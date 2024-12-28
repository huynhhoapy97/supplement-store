<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="utf-8">
	<title>File Browser</title>
	<style type="text/css">
        body {
            font-family: 'Segoe UI', Verdana, Helvetica, sans-serif;
            font-size: 80%;
        }

        #form {
            width: 600px;
        }

        #folderExplorer {
            float: left;
            width: 100px;
        }

        #fileExplorer {
            float: left;
            width: 680px;
            border-left: 1px solid #dff0ff;
        }

        .thumbnail {
            float: left;
            margin: 3px;
            padding: 3px;
            border: 1px solid #dff0ff;
            width: 150px;
            height: 150px;
            align-content: end;
        }

        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        ul {
            padding: 0;
        }

        img {
            width: 75%;
            height: auto;
        }
	</style>
</head>
<body>
    <div id="fileExplorer">
        <c:forEach var="property" items="${imageProperties}">
            <div class="thumbnail">
                <img src="uploads/category/${property.name}" alt="thumb"
                     title="${property.name}"
                     width="${property.width}"
                     height="${property.height}" />
                <br />
                ${file.name}
            </div>
        </c:forEach>
    </div>

    <!-- jQuery -->
    <script src="resources/adminlte/plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#fileExplorer").hover(function(){
                $(this).css('cursor', 'pointer');
            });
        });
    </script>
</body>
</html>
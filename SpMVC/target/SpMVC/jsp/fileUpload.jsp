<%--
  Created by IntelliJ IDEA.
  User: 荣耀
  Date: 2022/4/7
  Time: 8:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/jsp/fileUpload" method="post" enctype="multipart/form-data">
        <p>选择文件：<input type="file" name="myFile"></p>
        <p>文件描述：<input type="text" name="description"></p>
        <input type="submit">
    </form>
</body>
</html>

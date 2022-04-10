<%--
  Created by IntelliJ IDEA.
  User: 荣耀
  Date: 2022/4/6
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/jsp/login" method="post">
        <input type="text" name="name"/><p></p>
        <input type="password" name="pwd"/><p></p>
        <input type="submit">
    </form>
</body>
</html>

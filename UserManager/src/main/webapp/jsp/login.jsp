<%--
  Created by IntelliJ IDEA.
  User: 荣耀
  Date: 2022/4/4
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/jsp/successGet">
    UserName: <input type="text" name="username"> <input type="submit">
</form>
<form action="${pageContext.request.contextPath}/jsp/successPost" method="post">
    UserName: <input type="text" name="username"> <input type="submit">
</form>
</body>
</html>

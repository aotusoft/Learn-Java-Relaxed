<%--
  Created by IntelliJ IDEA.
  User: 荣耀
  Date: 2021/12/9
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String name;
        String city;
        name = request.getParameter("name");
        city = request.getParameter("city");
        response.getWriter().write("name："+name+"\ncity："+city);
    %>
</body>
</html>

<%@ page import="sql.InitDatabase" %>
<%@ page import="sql.inputTable" %><%--
  Created by IntelliJ IDEA.
  User: 荣耀
  Date: 2021/12/9
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
    String path=request.getContextPath();//获取站点名称
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <base href="./">
    <title>Title</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String a = request.getParameter("bh");//取ID
    String b = request.getParameter("name");
    int c = Integer.parseInt(request.getParameter("num"));
    String d = request.getParameter("send");
    String e = request.getParameter("receive");

    InitDatabase idb = new InitDatabase();
    idb.con = idb.getConnection();
    inputTable it = new inputTable();
    it.add(idb.con, a, b, c, d, e);
%>
</body>
</html>

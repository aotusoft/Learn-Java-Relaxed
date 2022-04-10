<%--
  Created by IntelliJ IDEA.
  User: 荣耀
  Date: 2022/4/7
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>被下载的文件名</td>
    </tr>
    <c:forEach items="${files}" var="filename">
        <tr>
            <td><a href="${pageContext.request.contextPath}/jsp/down?filename=${filename}">${filename}</a></td>
        </tr>
    </c:forEach>
</table>
<p></p>
文件描述：${fileDomain.myFile.originalFilename}
</body>
</html>

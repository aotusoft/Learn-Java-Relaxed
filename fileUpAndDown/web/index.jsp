<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: szkj
  Date: 2022-02-16
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>文件上传和下载</title>
  </head>
  <body>
       <div align="center" style="border: deepskyblue 1px solid">
            <s:form action="UpFile" method="post" enctype="multipart/form-data">
                  <s:file name="file"  label="上传文件" />
                  <s:submit value="上传" />
                  <s:reset value="重置" />
            </s:form>
       </div>
       <div align="center" style="border: red 1px solid">
             <a href="DownLoadFileTest">点击下载1.txt</a>
       </div>

  </body>
</html>

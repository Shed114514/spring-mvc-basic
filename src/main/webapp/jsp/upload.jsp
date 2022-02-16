<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/2/16
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Uploading Test</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user/test16" method="post" enctype="multipart/form-data">
        <h3>文件上传测试</h3>
        文件1：<input type="file" name="files"/><br/>
        文件2：<input type="file" name="files"/><br/>
        文件3：<input type="file" name="files"/><br/>
        <input type="submit" value="上传">
    </form>
</body>
</html>

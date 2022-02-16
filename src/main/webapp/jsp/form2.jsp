<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/2/15
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user/test12" method="post">
        <label>
            Name: <input type="text" name="username"><br/>
            Age: <input type="text" name="userage"><br/>
            <input type="submit" value="submit">
        </label>
    </form>
</body>
</html>

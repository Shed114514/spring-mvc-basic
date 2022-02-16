<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user/test11" method="post">
        <label>
            <input type="text" name="userList[0].name"/><br/>
            <input type="text" name="userList[0].age"/><br/>
            <input type="text" name="userList[1].name"/><br/>
            <input type="text" name="userList[1].age"/><br/>
            <input type="submit" value="submit">
        </label>
    </form>
</body>
</html>

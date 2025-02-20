<%--
  Created by IntelliJ IDEA.
  User: ape07
  Date: 25. 1. 30.
  Time: 오후 8:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <h1>Register Page</h1>
</head>
<body>
<form method="POST" action="/todo/register">
    <div>
        <input type="text" name="title" placeholder="제목 입력"/>
    </div>
    <div>
        <input type="date" name="dueDate" placeholder="날짜"/>
    </div>

    <button type="reset">리셋</button>
    <button type="submit">등록</button>

</form>
</body>
</html>

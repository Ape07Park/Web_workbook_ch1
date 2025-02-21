<%--
  Created by IntelliJ IDEA.
  User: ape07
  Date: 25. 1. 30.
  Time: 오후 8:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>View Page</h1>
<div>
    <input type="text" name="tno" value="${todo.tno}" readonly>
</div>
<div>
    <input type="text" name="title" value="${todo.title}" readonly>
</div>
<div>
    <input type="date" name="dueDate" value="${todo.dueDate}">
</div>
<div>
    <input type="checkbox" name="finished" ${todo.finished ? "checked": ""} readonly>
</div>
<div>
    <a href="/todo/update?tno=${todo.tno}">수정</a>
    <a href="/todo/list">목록</a>
</div>

</body>
</html>

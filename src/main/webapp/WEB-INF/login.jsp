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
    <h1>login Page</h1>
</head>
<body>
<%--쿼리 파라미터 중 result의 값이 error면 보여줌--%>
<c:if test="${param.result == 'error'}">
    <h1>Error</h1>
</c:if>

<form method="post" action="/login">
    <div>
        <input type="text" name="mid" placeholder="아이디 입력"/>
    </div>
    <div>
        <input type="text" name="pwd" placeholder="비밀번호 입력"/>
    </div>
    <div>
        <p>remember-me</p>
        <input type="checkbox" name="remember-me"/>
    </div>

    <button type="submit">LOGIN</button>

</form>
</body>
</html>

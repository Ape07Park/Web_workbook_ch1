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
    <h1>List Page</h1>
<%--EL은 HTTPServletRequest에서 저장된 객체를 못찾으면 HttpSession에서 저장된 객체를 찾음(스코프 범위 확장). 따라서 ${loginInfo}를 하면 세션에서 값을 찾아 출력함
 스코프: 변수나 객체가 접근 가능한 범위
 --%>
    <h2>${loginInfo}</h2>
    <h2>${loginInfo.name}</h2>

    <c:forEach var="dto" items="${todoList}">

        <li>
            <span><a href="/todo/view?tno=${dto.tno}">${dto.tno}</a></span>
            <span>${dto.title}</span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished == true ? "DONE" : "NOT YET"}</span>
        </li>
    </c:forEach>
    <form method="POST" action="/logout">

        <button type="submit">로그아웃</button>

    </form>


</head>
<body>

<script>

</script>


</body>
</html>

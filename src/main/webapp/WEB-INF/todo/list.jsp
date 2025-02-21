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
    <c:forEach var="dto" items="${todoList}">

        <li>
            <span><a href="/todo/view?tno=${dto.tno}">${dto.tno}</a></span>
            <span>${dto.title}</span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished == true ? "DONE" : "NOT YET"}</span>
        </li>
    </c:forEach>

</head>
<body>

<script>

</script>


</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
    <ul>
        <c:forEach items="${users}" var="user">
            <li>姓名: ${user.name}, 年龄: ${user.age}, 地址: ${user.address}</li>
        </c:forEach>
    </ul>
</body>
</html>
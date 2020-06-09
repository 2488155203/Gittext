<%--
  Created by IntelliJ IDEA.
  User: 葵花妹妹
  Date: 2020/6/5
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>
<c:forEach items="${map}" var="m">
   商品名称: ${m.key.fid}<br>
    商品编号:${m.key.fname}<br>
    <a href="user.do?p=cutcarnum&fid=${m.key.fid}">-</a>${m.value}<a href="user.do?p=addcarnum&fid=${m.key.fid}">+</a><br>
    <a href="user.do?p=delfromcar&fid=${m.key.fid}">删除</a>
</c:forEach>
</body>
</html>

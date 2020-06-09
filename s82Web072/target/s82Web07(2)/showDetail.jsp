<%--
  Created by IntelliJ IDEA.
  User: 葵花妹妹
  Date: 2020/6/4
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>>
<html>
<head>
    <title>商品详情</title>
</head>

<style>
    .goodspic{
      width: 430px;
      height: 350px;
    }
</style>

<body>
${goods.fid}
${goods.fname}
${goods.type}
${goods.area}
<br>
<img src="img/${goods.img}">

<c:forEach items="${goods.set}" var="pic">
    <img class="goodspic" src="img/${pic.pname}">
</c:forEach>

<input type="text" value="1" id="num">
<a href="javascript:void(0)" onclick="addCar()">加入购物车</a>
<a href="user1.do?p=addlover&fid=${goods.fid}">收藏</a>

</body>
</html>

<script>
    function addCar() {
        var num=document.getElementById("num").value;
        location="user.do?p=addCar&num="+num+"&fid=${goods.fid}";
    }
</script>

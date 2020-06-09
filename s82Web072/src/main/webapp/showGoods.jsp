<%--
  Created by IntelliJ IDEA.
  User: 葵花妹妹
  Date: 2020/6/4
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>首页</title>
</head>

<style>
    #bigDiv{
        width:1200px;
        height: 700px;
        border: 1px solid white;
        /*text-align水平对齐*/
        text-align: center;
        /*margin边缘 auto自动的*/
        margin: auto;
    }
    .smalldiv{
        width: 400px;
        height: 300px;
        border: 1px solid white;
        float: left;
        /*间隔*/
        margin-left: 12px;
        margin-top: 10px;
    }
    .goodspic{
        width: 400px;
        height: 300px;
    }
</style>

<body>
<div id="bigDiv">
    <c:forEach items="${map.list}" var="goods">
            <div class="smalldiv">
           ${goods.fid}${goods.fname}
       <a href="user.do?p=findbyid&fid=${goods.fid}" title="查看详情"><img class="goodspic" src="img/${goods.img}"></a>
            </div>
    </c:forEach>
</div>

<a href="user.do?p=fenye&page=1&size=${map.size}">首页</a>
<a href="user.do?p=fenye&page=${map.page-1}&size=${map.size}">上一页</a>
<a href="user.do?p=fenye&page=${map.page+1}&size=${map.size}">下一页</a>
<a href="user.do?p=fenye&page=${map.pagecount}&size=${map.size}">尾页</a>

</body>
</html>

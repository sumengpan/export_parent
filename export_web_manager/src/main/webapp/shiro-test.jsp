<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/11/12
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--        标签会查询权限，如果没有权限不报错，将页面内容进行隐藏，反之显示页面内容--%>

        <shiro:hasPermission name="企业管理">
            <a href="">企业管理</a>
        </shiro:hasPermission>

        <shiro:hasPermission name="用户管理">
            <a href="">用户管理</a>
        </shiro:hasPermission>

        <shiro:hasPermission name="日志管理">
            <a href="">日志管理</a>
        </shiro:hasPermission>
</body>
</html>

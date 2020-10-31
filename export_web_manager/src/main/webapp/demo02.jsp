<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/10/29
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--字标签param，将k-v值传给被include界面--%>
<jsp:include page="demo02_common.jsp ">
    <jsp:param name="jack" value="rose"/>
    <jsp:param name="url" value="http://ww.baidu.com"/>
</jsp:include>
我是主要内容
<jsp:include page="demo02_common.jsp"></jsp:include>
</body>
</html>

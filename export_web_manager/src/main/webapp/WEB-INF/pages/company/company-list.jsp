<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
&lt;%&ndash;${list}&ndash;%&gt;
<a href="${path}/company/add.do">新增</a>
<table border="1px" width="100%">
    <tr>
        <td>id</td>
        <td>公司名称</td>
        <td>到期时间</td>
        <td>公司地址</td>
        <td>营业执照</td>
        <td>法人代表</td>
        <td>公司电话</td>
        <td>公司规模</td>
        <td>所属行业</td>
        <td>备注</td>
        <td>状态</td>
        <td>当前余额</td>
        <td>管理</td>
    </tr>
<c:forEach items="${list}" var="company">
    <tr>
        <td>${company.id}</td>
        <td>${company.name}</td>
        <td>${company.expirationDate}</td>
        <td>${company.address}</td>
        <td>${company.licenseId}</td>
        <td>${company.representative}</td>
        <td>${company.phone}</td>
        <td>${company.companySize}</td>
        <td>${company.industry}</td>
        <td>${company.remarks}</td>
        <td>${company.state}</td>
        <td>${company.balance}</td>
        <td><a href="#">删除</a>
            <a>|</a>
            <a href="#">修改</a>
        </td>
    </tr>
</c:forEach>
</table>
</body>
</html>
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
    <script src="${path}/plugins/jQuery/jquery-2.2.3.min.js"></script>
</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${path}/company/delete.do?id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            企业管理
            <small>企业列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">

                    <!--工具栏-->
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default" title="新建" onclick='location.href="${path}/company/toAdd.do"'><i class="fa fa-file-o"></i> 新建</button>
                                <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                                <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                            </div>
                        </div>
                    </div>
                    <div class="box-tools pull-right">
                        <div class="has-feedback">
                            <input type="text" class="form-control input-sm" placeholder="搜索">
                            <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        </div>
                    </div>
                    <!--工具栏/-->

                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <th class="" style="padding-right:0px;">

                            </th>
                            <th class="sorting">企业名称</th>
                            <th class="sorting">所在地</th>
                            <th class="sorting">地址</th>
                            <th class="sorting">企业法人</th>
                            <th class="sorting">联系方式</th>
                            <th class="sorting">所属行业</th>
                            <th class="sorting">状态</th>
                            <th class="sorting">余额</th>
                            <th class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="item">
                            <tr>
                                <td><input name="ids" value="${item.id}" type="checkbox"></td>
                                <td>${item.name}</td>
                                <td>${item.city}</td>
                                <td>${item.address}</td>
                                <td>${item.representative}</td>
                                <td>${item.phone}</td>
                                <td>${item.industry}</td>
                                <td>${item.state ==0?'未审核':'已审核'}</td>
                                <td class="text-center">${item.balance}</td>
                                <td class="text-center">
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${path}/company/toEdit.do?id=${item.id}"'>编辑</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- /.box-body -->

            <%--这里使用了分页--common，所以下面可以注释了--%>
            <!-- .box-footer-->
            <div class="box-footer">
                <jsp:include page="../common/page.jsp">
                    <jsp:param value="${path}/company/toList.do" name="pageUrl"/>
                </jsp:include>
            </div>

                <%--分页查询--%>
                <%--<div class="box-tools pull-right">
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">首页</a>
                        </li>
                        <c:if test="${pi.hasPreviousPage}">
                            <li><a href="#">上一页</a></li>
                        </c:if>

                        <c:forEach begin="1" end="${pi.pages}" step="1" varStatus="vs">
                           <c:if test="${vs.index==pi.pageNum}">
                               <li class="active"><a href="#">${vs.index}</a> </li>
                           </c:if>
                           <c:if test="${vs.index!=pi.pageNum}">
                               <li><a href="#">${vs.index}</a> </li>
                           </c:if>
                       </c:forEach>

                        <c:if test="${pi.hasNextPage}">
                            <li><a href="#">下一页</a></li>
                        </c:if>

                        <li>
                            <a href="#" aria-label="Next">尾页</a>
                        </li>
                    </ul>
                </div>--%>

            </div>


        </div>
    </section>
</div>
</body>

</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../base.jsp" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${path}/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <!--登录成功时将 user使用loginUser保存到sessesion中 -->
                <p> ${loginUser.userName}</p>
                <a href="#">${loginUser.companyName}</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>


            <c:forEach items="${sessionScope.menus}" var="item">
                <c:if test="${item.ctype==0}">
                    <li class="treeview">
                            <%--一级菜单 --%>
                        <a href="#">
                            <i class="fa fa-cube"></i> <span>${item.name}</span>
                            <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                        </a>
                        <ul class="treeview-menu">
                            <c:forEach items="${sessionScope.menus}" var="item2">
                                <%--           一级菜单的ctype==0 二级菜单的ctype==1--%>
                                <%--           二级的parentId肯定与一级的moduleId是相等，才能显示在该菜单下面--%>
                                <c:if test="${item2.ctype==1 && item2.parentId == item.moduleId}">
                                    <%--  二级菜单 --%>
                                    <li id="${item2.moduleId}">
                                        <!-- 此处的链接 先加项目地址再加数据库中的curl-->
                                        <a onclick="setSidebarActive(this)" href="${path}/${item2.curl}"
                                           target="iframe">
                                            <i class="fa fa-circle-o"></i>${item2.name}
                                        </a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </li>
                </c:if>
            </c:forEach>
            <!--Ctrl+Alt+L整理代码格式的作用 -->


        </ul>

    </section>
    <!-- /.sidebar -->
</aside>


<%--<li class="treeview">
    <a href="#">
        <i class="fa fa-cube"></i> <span>Saas管理</span>
        <span class="pull-right-container">
            <i class="fa fa-angle-left pull-right"></i>
        </span>
    </a>
    <ul class="treeview-menu">
        <li id="company-manager">
            <a href="${path}/company/toList.do" onclick="setSidebarActive(this)"  target="iframe">
                <i class="fa fa-circle-o"></i>企业管理
            </a>
        </li>
        <li id="module-manager">
            <a href="/system/module/list.do" onclick="setSidebarActive(this)" target="iframe">
                <i class="fa fa-circle-o"></i>模块管理
            </a>
        </li>
    </ul>
</li>

<li class="treeview">
  <a href="#">
      <i class="fa fa-cube"></i> <span>货运管理</span>
      <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
      </span>
  </a>
  <ul class="treeview-menu">
      <li id="cargo-contract">
          <a href="cargo/contract/list.do" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>购销合同
          </a>
      </li>
      <li id="cargo-out">
          <a href="/cargo/contract/print.do" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>出货表
          </a>
      </li>
      <li id="cargo-contractlist">
          <a href="/cargo/export/contractList.do" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>合同管理
          </a>
      </li>
      <li id="cargo-export">
          <a href="/cargo/export/list.do" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>出口报运
          </a>
      </li>
      <li id="cargo-pack">
          <a href="/cargo/packing/list.do" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>装箱管理
          </a>
      </li>
      <li id="cargo-ship">
          <a href="/cargo/shipping/list.do" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>委托管理
          </a>
      </li>
      <li id="cargo-invoice">
          <a href="/cargo/invoice/list.do" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>发票管理
          </a>
      </li>
      <li id="cargo-finance">
          <a href="/cargo/finance/list.do" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>财务管理
          </a>
      </li>
  </ul>
</li>

<li class="treeview">
  <a href="#">
      <i class="fa fa-cube"></i> <span>统计分析</span>
      <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
      </span>
  </a>
  <ul class="treeview-menu">
      <li id="stat-factory">
          <a href="/stat/toCharts.do?chartsType=factory" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>厂家销量统计
          </a>
      </li>
      <li id="stat-sell">
          <a href="/stat/toCharts.do?chartsType=sell" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>产品销量排行
          </a>
      </li>
      <li id="stat-online">
          <a href="/stat/toCharts.do?chartsType=online" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>在线人数统计
          </a>
      </li>
  </ul>
</li>

       <li class="treeview">
  <a href="#">
      <i class="fa fa-cube"></i> <span>基础信息</span>
      <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
      </span>
  </a>
  <ul class="treeview-menu">
      <li id="system-code">
          <a href="" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>系统代码
          </a>
      </li>
      <li id="base-factory">
          <a href="" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>厂家信息
          </a>
      </li>
  </ul>
</li>

<li class="treeview">
  <a href="#">
      <i class="fa fa-cube"></i> <span>系统管理</span>
      <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
      </span>
  </a>
  <ul class="treeview-menu">
      <li id="sys-dept">
          <a href="${path}/system/dept/toList.do" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>部门管理
          </a>
      </li>
      <li id="sys-user">
          <a href="${path}/system/user/toList.do" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>用户管理
          </a>
      </li>
      <li id="sys-role">
          <a href="${path}/system/role/toList.do" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>角色管理
          </a>
      </li>
      <li id="sys-log">
          <a href="${path}/system/log/toList.do" onclick="setSidebarActive(this)"  target="iframe">
              <i class="fa fa-circle-o"></i>日志管理
          </a>
      </li>
  </ul>
</li>

        </ul>

    </section>--%>
<!-- /.sidebar -->

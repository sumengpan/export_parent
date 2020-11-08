<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
<div class="pull-left">
    <div class="form-group form-inline">
        总共${pi.pages} 页，共${pi.total} 条数据。
    </div>
</div>

<div class="box-tools pull-right">
    <ul class="pagination" style="margin: 0px;">
        <li >
            <a href="javascript:goPage(1)" aria-label="Previous">首页</a>
        </li>
        <c:if test="${pi.pageNum != 1 }">
            <li><a href="javascript:goPage(${pi.prePage})">上一页</a></li>
        </c:if>
        <c:forEach begin="${pi.pageNum-5 <= 0 ? 1:pi.pageNum-5 }" end="${pi.pageNum+5>pi.pages?pi.pages:pi.pageNum+5}"
                   var="i">
            <li class="paginate_button ${pi.pageNum==i ? 'active':''}"><a href="javascript:goPage(${i})">${i}</a></li>
        </c:forEach>
        <c:if test="${pi.pageNum != pi.pages }">
            <li><a href="javascript:goPage(${pi.nextPage})">下一页</a></li>
        </c:if>

        <li>
            <a href="javascript:goPage(${pi.pages})" aria-label="Next">尾页</a>
        </li>
    </ul>
</div>
<%--<form id="pageForm" action="${param.pageUrl}" method="post">--%>
<%--    <input type="hidden" name="curr" id="curr">--%>
<%--    <input type="hidden" name="pageSize" id="pageSize">--%>
<%--</form>--%>
<script>
    function goPage(page) {
        <%--document.getElementById("curr").value = page //curr=2--%>
        <%--document.getElementById("pageSize").value = ${pi.pageSize} //pageSize=3--%>
        <%--document.getElementById("pageForm").submit()--%>
        //get 修改地址的数据
        window.location='${param.pageUrl}?curr='+page+'&pageSize='+ ${pi.pageSize}
    }
</script>
</body>
</html>

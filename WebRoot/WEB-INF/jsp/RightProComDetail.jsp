<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/hotline.js"></script>
<!--<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />-->
<script type="text/javascript">

var status = 0;
//使用jquery加载事件
$(document).ready(function() {
    $("#selall").click(function() {
        if (status == 0) {
            $("input[name=checkbox]").prop("checked", "checked");
            $("input[name=selall]").prop("checked", "checked");
            status = 1;
        }

        else {
            $("input[name=checkbox]").prop("checked", false);
            $("input[name=selall]").prop("checked", false);
            status = 0;
        }
    });
});

</script>


<script>
function hotlineadd(){
	rightFrame.location="${pageContext.request.contextPath}/jsp/hotlineaddJsp.action";
}
function toPage(){
	var page = $("input[name='number']").val();
	window.location.href="${pageContext.request.contextPath}/hotline/shownext.action?nextPage="+page+"&totalPage=${page.getTotalPage()}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}&condition4=${page.condition4}&condition5=${page.condition5}";
}
function showBusinessAndOthersByLike(){
	var business = $("input[name='business']").val().trim();
	var cusTelephone = $("input[name='hotline']").val().trim();
	var telephone = $("input[name='localnum']").val().trim();
	var bigType = $("input[name='probigclass']").val().trim();
	window.location.href="${pageContext.request.contextPath}/hotline/shownext.action?nextPage=${page.getTotalPage()}&totalPage=${page.getTotalPage()}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}&condition4=${page.condition4}&condition5=${page.condition5}";
}
/* //批量删除 */
function batchDel() {
    //判断至少写了一项  
    var checkedNum = $("input[name='checkbox']:checked").length;
    if (checkedNum == 0) {
        alert("请至少选择一项!");
        return false;
    }
    if (confirm("确定删除所选记录?")) {
        var checkedList = new Array();
        $("input[name='checkbox']:checked").each(function() {
            checkedList.push($(this).val());
        });
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/hotline/batchDel.action",
            data: {
                "delitems": checkedList.toString()
            },
            datatype: "html",
            success: function(data) {
                $("[name='selall']:checkbox").attr("checked", false);
                location.reload(); //页面刷新  
            },
            error: function(data) {
               art.dialog.tips('删除失败!');
            }
        });
    }
}


</script>
</head>

<body>
<div class="rightstyle">
<div class="grayStripe">热线号落地号</div>

        <div class = "reportAlign floatright">
            <form action="${pageContext.request.contextPath}/hotline/show.action" method="get">
                查询条件：
                业务：<input class="inputj" name="condition1" type="text" value="${page.condition1}" />
                热线号：<input class="inputj" name="condition2" type="text" value="${page.condition2}" />
                落地号：<input  class="inputj" name="condition3" type="text" value="${page.condition3}" />
                产品大类：<input class="inputj" name="condition4" type="text" value="${page.condition4}" />
               计时 所属公司：
                
                <select class="inputj" name="condition5">
					                <option value="" 
					                	<c:if test="${page.condition5 eq '' }">
				             				selected="selected"
				             			</c:if>
					                >
					                	请选择公司
					                </option>
					                <c:forEach items="${customerVoList }" var="ct" >
					             		<option value="${ct.cusname }"
					             			<c:if test="${ct.cusname eq page.condition5 }">
					             				selected="selected"
					             			</c:if>
					             		>
					               			${ct.cusname }
					                	</option>
					                </c:forEach>
					                
				</select>
                
                <input name="bt" type="submit" class="report" value="查询" />
                <input name="bt" type="button" class="report" value="导入" onclick="location.href='${pageContext.request.contextPath}/jsp/hotlineimportJsp.action'" />
                <input name="dc" type="button"  class="report" value="导出" onClick="location.href='${pageContext.request.contextPath}/hotline/exportHotLine.action'"/>
               <%--  <input name="dc" type="button"  class="report" value="添加" onClick="location.href='${pageContext.request.contextPath}/jsp/hotlineaddJsp.action'" /> --%>
                <input name="dc" type="button"  class="report" value="添加" onClick="window.open(' ${pageContext.request.contextPath}/jsp/hotlineaddJsp.action','_blank','height=600,width=600,top=200,left=450')" />
                
                <input name="dc" type="button"  class="report" value="批量删除" onClick="batchDel()" />
            </form>
        </div>

<div class="clearfloat">显示条件：</div>



<table class="righttable" cellspacing="0px" cellpadding="2px">
<thead>
<tr><th id="selall" rowspan="2"><input name="selall" type="checkbox" value="全选" /></th><th colspan="7" style="background-color:#FFD966;">IT维护信息</th><th colspan="5" style="background-color:#F8CBAD;">计费系统</th><th colspan="2" rowspan="2">操作</th></tr>
<tr><th style="background-color:#FFD966;">序号</th><th style="background-color:#FFD966;">业务</th><th style="background-color:#FFD966;">热线号</th><th style="background-color:#FFD966;">IVR</th><th style="background-color:#FFD966;">落地号</th><th style="background-color:#FFD966;">所属系统</th><th style="background-color:#FFD966;">备注</th><th style="background-color:#F8CBAD;">计时产品大类</th><th style="background-color:#F8CBAD;">计时所属公司</th><th style="background-color:#F8CBAD;">话务系统</th><th style="background-color:#F8CBAD;">热线号</th><th style="background-color:#F8CBAD;">落地号</th></tr>
</thead>
<tbody>
<c:if test="${hotlines.size() eq 0 }">
<tr class="righttabletr"><td colspan="14">没有查询到记录</td></tr>
</c:if>
<%-- <tr class="righttabletr"><td ><input name="checkbox" type="checkbox" value="1" /></td ><td >1</td><td>TCL彩电</td><td>4008123456</td><td>按1</td><td>0752-2366999</td><td>全媒体系统</td><td>非客音落地号</td><td>彩电</td><td>TCL王牌电器（惠州）有限公司</td><td >全媒体</td><td>4008123456</td><td>0752-2366999</td><td><a href="${pageContext.request.contextPath}/hotline/showUpdateHotLine.action?id=1&cusId=1434666736" target="rightFrame"  class="green">更改</a></td><td><a href="#" class="warn">删除</a></td></tr> --%>
<c:forEach var="hotline" items="${hotlines}" varStatus="idx">
<tr class="righttabletr"><td ><input name="checkbox" type="checkbox" value="${hotline.id}" /></td ><td > <c:out value='${idx.index+1}'/></td><td>${empty hotline.business ?"无":hotline.business}</td>
<td>${empty hotline.cusTelephone ?"无":hotline.cusTelephone}</td><td>${empty hotline.ivr ?"无":hotline.ivr}</td><td>${empty hotline.telephone ?"无":hotline.telephone}</td>
<td>${empty hotline.itSystem ?"无":hotline.itSystem}</td><td>${empty hotline.itRemark ?"无":hotline.itRemark}</td><td>${empty hotline.bigType ?"无":hotline.bigType}</td>
<td>${empty hotline.cusName ?"无":hotline.cusName}</td><td >${empty hotline.chaSystem ?"无":hotline.chaSystem}</td><td>${empty hotline.cusTelephone ?"无":hotline.cusTelephone}</td>
<td>${empty hotline.telephone ?"无":hotline.telephone}</td><td><a href="${pageContext.request.contextPath}/hotline/showUpdateHotLine.action?id=${hotline.id}&cusId=${hotline.cusId}&currentPage=${page.getCurrentPage()}&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}&condition4=${page.condition4}&condition5=${page.condition5}" target="rightFrame"  class="green">更改</a></td>
<td><a onclick="delRecord('${pageContext.request.contextPath}/hotline/deleteHotLine.action?id=${hotline.id}&nextPage=${page.getCurrentPage()}&totalPage=${page.getTotalPage()}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}&condition4=${page.condition4}&condition5=${page.condition5}','${empty hotline.telephone ?"无":hotline.telephone}')"  class="warn">删除</a></td></tr>
</c:forEach>

</tbody>
</table>


<div class="tbfooter">
<div class="floatleft"><span>记录条数：<c:out value='${page.totalRecord}'/>&nbsp;总页数：<c:out value='${page.getTotalPage()}'/>&nbsp;当前页：<c:out value='${page.getCurrentPage()}'/></span></div>
            <div class="floatright">
                <form action="" method="get" class="formfloat" >
                    到第&nbsp;<input name="number"  value="1" type="text" class="number" />&nbsp;页
                    <input name="GO" type="button" value="GO" onClick="toPage()" />
                </form>
            </div>
            
<div class="floatright">
  <c:choose>
	           		<c:when test="${page.getCurrentPage() eq 1 }">
	           			<span class="disabled"><< 首页 </span>
	           		</c:when>
	           		<c:otherwise>
	           			<span class="disabled"><a href="${pageContext.request.contextPath}/hotline/shownext.action?nextPage=1&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}&condition4=${page.condition4}&condition5=${page.condition5}"><< 首页</a> </span>
	           		</c:otherwise>
	           	</c:choose>
	               
	               <c:choose>
	               	<c:when test="${page.getCurrentPage() > 1 }">
	               		<span class="disabled"><a href="${pageContext.request.contextPath}/hotline/shownext.action?nextPage=${page.getCurrentPage()-1}&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}&condition4=${page.condition4}&condition5=${page.condition5}">< 上一页</a> </span>
	               	</c:when>
	               	<c:otherwise>
	               		<span class="disabled">< 上一页 </span>
	               	</c:otherwise>
	               </c:choose>
	               
	               <c:choose>
	               	<c:when test="${page.getCurrentPage() < page.getTotalPage()}">
	               		<span class="disabled"> <a href="${pageContext.request.contextPath}/hotline/shownext.action?nextPage=${page.getCurrentPage()+1}&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}&condition4=${page.condition4}&condition5=${page.condition5}">下一页 ></a> </span>
	               	</c:when>
	               	<c:otherwise>
	               		<span class="disabled"> 下一页 > </span>
	               	</c:otherwise>
	               </c:choose>
	               
	               <c:choose>
	               	<c:when test="${page.getCurrentPage() eq page.getTotalPage()}">
	               		<span class="disabled"> 尾页>> </span>
	               	</c:when>
	               	<c:otherwise>
	               		<span class="disabled"> <a href="${pageContext.request.contextPath}/hotline/shownext.action?nextPage=${page.getTotalPage()}&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}&condition4=${page.condition4}&condition5=${page.condition5}">尾页>></a> </span>
	               	</c:otherwise>
	               </c:choose>
            </div>   
            
</div>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath}/js/multimd981.js"></script>
<!-- <link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />  -->
</head>
<script>
function toPage(){
	var page = $("input[name='number']").val();
	window.location.href="${pageContext.request.contextPath}/multi981/shownext.action?nextPage="+page+"&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}";
}
</script>
<body>
<div class="rightstyle">
<div class="grayStripe">多媒体租户分机号</div>

        <div class = "reportAlign floatright">
                    
                        <form action="${pageContext.request.contextPath}/multi981/showAllMTulti981.action" method="get">
                           查询条件：
                            分机号：<input class="inputj" name="condition1" type="text"  value="${page.condition1}"/>
                            落地号：<input class="inputj" name="condition2" type="text" value="${page.condition2}"/>
                            计时产品大类：<input class="inputj" name="condition3" type="text" value="${page.condition3}"/>                
                            <input name="bt" type="submit" class="report" value="查询" />
                            <input name="dc" type="button"  class="report" value="导入" onClick="location.href='${pageContext.request.contextPath}/jsp/multi981ImportJsp.action'" />
                            <input name="dc" type="button"  class="report" value="导出" onClick="location.href='${pageContext.request.contextPath}/multi981/exportAllTulti981.action'" />
                            <%-- <input name="dc" type="button"  class="report" value="添加" onClick="location.href='${pageContext.request.contextPath}/jsp/multi981Jsp.action'"  /> --%>
                            
                            <input name="dc" type="button"  class="report" value="添加" onClick="window.open('${pageContext.request.contextPath}/jsp/multi981Jsp.action','_blank','height=450,width=800,top=200,left=450')"  />
                        </form>
                    
        </div>

<div class="clearfloat">显示条件：</div>

<table class="righttable" cellspacing="0px" cellpadding="2px">
<thead>
<tr><th>序号</th><th>租户</th><th>分机号</th><th>落地号</th><th>计时产品大类</th><th>计时所属公司</th><th>修改日期</th><th colspan="2">操作</th></tr>
</thead>
<tbody>

<!-- <tr ><td >1</td><td >981</td><td>61097 </td><td>2569501</td><td>速必达V1</td><td>速必达希杰物流有限公司</td><td>2017/1/30</td><td><a href="#" class="green">修改</a></td></tr> -->
<c:forEach var="mutilMedia981" items="${mutilMedia981s}" varStatus="idx">
<tr ><td ><c:out value='${idx.index+1}'/></td><td >${empty mutilMedia981.itenantId  ?"无":mutilMedia981.itenantId }</td><td>${empty mutilMedia981.extNumber  ?"无":mutilMedia981.extNumber } </td><td>${empty mutilMedia981.telephone  ?"无":mutilMedia981.telephone }</td><td>${empty mutilMedia981.bigType  ?"无":mutilMedia981.bigType }</td><td>${empty mutilMedia981.cusName  ?"无":mutilMedia981.cusName }</td><td><fmt:formatDate value="${mutilMedia981.updateTime }" type="both" pattern="yyyy/MM/dd "/></td>
<td><a href="#" class="green" onclick="location.href='${pageContext.request.contextPath}/multi981/showUpdateMutilMedia981.action?id=${mutilMedia981.id}&telephone=${mutilMedia981.telephone}&itenantId=${mutilMedia981.itenantId}&extNumber=${mutilMedia981.extNumber}&bigType=${mutilMedia981.bigType}&cusName=${mutilMedia981.cusName}&nextPage=${page.getCurrentPage()}&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}'">修改</a></td>
<td><a  class="warn" style="cursor:pointer" onclick="del981Record('${pageContext.request.contextPath}/multi981/delMultiMedia981.action?id=${mutilMedia981.id}&nextPage=${page.getCurrentPage()}&totalPage=${page.getTotalPage()}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}','${empty mutilMedia981.extNumber  ?"无":mutilMedia981.extNumber }')">删除</a></td></tr>
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
	           			<span class="disabled"><a href="${pageContext.request.contextPath}/multi981/shownext.action?nextPage=1&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}"><< 首页</a> </span>
	           		</c:otherwise>
	           	</c:choose>
	               
	               <c:choose>
	               	<c:when test="${page.getCurrentPage() > 1 }">
	               		<span class="disabled"><a href="${pageContext.request.contextPath}/multi981/shownext.action?nextPage=${page.getCurrentPage()-1}&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}">< 上一页</a> </span>
	               	</c:when>
	               	<c:otherwise>
	               		<span class="disabled">< 上一页 </span>
	               	</c:otherwise>
	               </c:choose>
	               
	               <c:choose>
	               	<c:when test="${page.getCurrentPage() < page.getTotalPage()}">
	               		<span class="disabled"> <a href="${pageContext.request.contextPath}/multi981/shownext.action?nextPage=${page.getCurrentPage()+1}&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}">下一页 ></a> </span>
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
	               		<span class="disabled"> <a href="${pageContext.request.contextPath}/multi981/shownext.action?nextPage=${page.getTotalPage()}&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}">尾页>></a> </span>
	               	</c:otherwise>
	               </c:choose>
            </div>   
</div>
</div>
</body>
</html>

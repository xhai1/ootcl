<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>


</head>

<body style="background:url(${pageContext.request.contextPath}/images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="main.html" target="_parent"><img src="${pageContext.request.contextPath}/images/logo.png" title="系统首页" /></a>
    </div>
    
   	<c:if test="${sessionScope.user.isroot eq 2 }">
   		<ul class="nav">
		    <li><a href="${pageContext.request.contextPath}/monthReport/showALlMonthReport.action?page=1" target="rightFrame" class="selected"><img src="${pageContext.request.contextPath}/images/icon01.png" title="结费报表" /><h2>结费报表</h2></a></li>
		    
		    <li><a href="${pageContext.request.contextPath}/ComputeMoney/staForCompany.action.action" target="_blank"><img src="${pageContext.request.contextPath}/images/icon02.png" title="计费总量" /><h2>计费总量</h2></a></li>
		    <li><a href="${pageContext.request.contextPath}/product/showProductClass.action?page=1" target="rightFrame"><img src="${pageContext.request.contextPath}/images/product.png" title="产品大类" /><h2>产品大类</h2></a></li>
		    <li><a href="${pageContext.request.contextPath}/hotline/show.action" target="rightFrame"><img src="${pageContext.request.contextPath}/images/tele.png" title="热线号" /><h2>热线号</h2></a></li>
		    <li><a href="${pageContext.request.contextPath}/user/showUser.action?page=1" target="rightFrame"><img src="${pageContext.request.contextPath}/images/i07.png" title="用户管理" /><h2>用户管理</h2></a></li>
		    
		    <li><a href="${pageContext.request.contextPath}/customer/showCustomer.action?page=1" target="rightFrame"><img src="${pageContext.request.contextPath}/images/Client.png" title="客户管理" /><h2>客户管理</h2></a></li>
		    <%-- <li><a href="${pageContext.request.contextPath}/permission/showAllUserInfo.action?page=1"  target="rightFrame"><img src="${pageContext.request.contextPath}/images/icon06.png" title="权限管理" /><h2>权限管理</h2></a></li> --%>
		    <li><a href="${pageContext.request.contextPath}/permission/acl.action?page=1"  target="rightFrame"><img src="${pageContext.request.contextPath}/images/icon06.png" title="权限管理" /><h2>权限管理</h2></a></li>
		</ul>
   	</c:if>
   	<c:if test="${sessionScope.user.isroot eq 0}">
   		<ul class="nav">
		    <li><a href="${pageContext.request.contextPath}/monthReport/showALlMonthReport.action?page=1&cusid=${sessionScope.user.cusid}" target="rightFrame" class="selected"><img src="${pageContext.request.contextPath}/images/icon01.png" title="结费报表" /><h2>结费报表</h2></a></li>
		    <li><a href="${pageContext.request.contextPath}/ComputeMoney/staForCompany.action?cusid=${sessionScope.user.cusid}" target="rightFrame"><img src="${pageContext.request.contextPath}/images/icon02.png" title="计费总量" /><h2>计费总量</h2></a></li>
		</ul>
   	</c:if>
    		
    <c:if test="${sessionScope.user.isroot eq 1 }">
    	<ul class="nav">
		    <li><a href="${pageContext.request.contextPath}/monthReport/showALlMonthReport.action?page=1" target="rightFrame" class="selected"><img src="${pageContext.request.contextPath}/images/icon01.png" title="结费报表" /><h2>结费报表</h2></a></li>
		    <li><a href="${pageContext.request.contextPath}/ComputeMoney/staForCompany.action.action" target="_blank"><img src="${pageContext.request.contextPath}/images/icon02.png" title="计费总量" /><h2>计费总量</h2></a></li>
		    <li><a href="${pageContext.request.contextPath}/product/showProductClass.action?page=1" target="rightFrame"><img src="${pageContext.request.contextPath}/images/product.png" title="产品大类" /><h2>产品大类</h2></a></li>
		    <li><a href="${pageContext.request.contextPath}/hotline/show.action" target="rightFrame"><img src="${pageContext.request.contextPath}/images/tele.png" title="热线号" /><h2>热线号</h2></a></li>
		    <li><a href="${pageContext.request.contextPath}/user/showUser.action?page=1" target="rightFrame"><img src="${pageContext.request.contextPath}/images/i07.png" title="用户管理" /><h2>用户管理</h2></a></li>
		    <li><a href="${pageContext.request.contextPath}/customer/showCustomer.action?page=1" target="rightFrame"><img src="${pageContext.request.contextPath}/images/Client.png" title="客户管理" /><h2>客户管理</h2></a></li>
		</ul>
    </c:if>
    
    
    <div class="topright">    
    <ul>
    <li><a href="${pageContext.request.contextPath}/logout.action" target="_parent">退出</a></li>
    </ul>
     
    <div class="user">
    <span>${sessionScope.user.username}</span>
    <!-- <i>消息</i> -->
    <!-- <b>5</b> -->
    </div>    
    
    </div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		/* $('dd').find('ul').stop().slideUp(); */
		if($ul.is(':visible')){
			$(this).next('ul').stop().slideUp();
		}else{
			$(this).next('ul').stop().slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;" onbeforeunload="window.location='${pageContext.request.contextPath}/logout.action'">
	<div class="lefttop"><span></span>菜单</div>
    
    <dl class="leftmenu">
        
    <!-- <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>管理信息
    </div>
    	<ul class="menuson">
        <li><cite></cite><a href="index.html" target="rightFrame">首页</a><i></i></li>
        <li class="active"><cite></cite><a href="right.html" target="rightFrame">数据报表</a><i></i></li>
        <li><cite></cite><a href="imgtable.html" target="rightFrame">用户管理</a><i></i></li>
        <li><cite></cite><a href="form.html" target="rightFrame">报表管理</a><i></i></li>
        <li><cite></cite><a href="imglist.html" target="rightFrame">产品类别</a><i></i></li>
        <li><cite></cite><a href="imglist1.html" target="rightFrame">计费管理</a><i></i></li>
        <li><cite></cite><a href="tools.html" target="rightFrame">受理明细</a><i></i></li>
        <li><cite></cite><a href="filelist.html" target="rightFrame">客户账单</a><i></i></li>
        <li><cite></cite><a href="tab.html" target="rightFrame">客户信息</a><i></i></li>
        <li><cite></cite><a href="error.html" target="rightFrame">404页面</a><i></i></li>
        </ul>    
    </dd> -->

    <c:if test="${sessionScope.user.isroot eq 2 }">
    	<dd>
	    <div class="title">
	    <span><img src="${pageContext.request.contextPath}/images/leftico01.png"/></span>计费汇总
	    </div>
	   	<ul class="menuson">
	       <%-- <li class="active"><cite></cite><a href="${pageContext.request.contextPath}/jsp/indexJsp.action" target="rightFrame">首页</a><i></i></li> --%>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/monthReport/showMonthReportDetail.action?page=1" target="rightFrame">汇总明细表</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/monthReport/showALlMonthReport.action?page=1" target="rightFrame">结费报表</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/ComputeMoney/staForCompany.action" target="_blank">计费总量</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/tlAccount/show.action" target="_blank">计费总量月统计表</a><i></i></li>
	    </ul>    
	    </dd>
	    
	    <dd>
	    <div class="title">
	    <span><img src="${pageContext.request.contextPath}/images/leftico01.png"/></span>信息基础
	    </div>
	   	<ul class="menuson">
	       <li><cite></cite><a href="${pageContext.request.contextPath}/moneyStandard/showCusStandard.action" target="rightFrame">计费标准</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/product/showProductClass.action?page=1" target="rightFrame">产品大类</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/hotline/show.action" target="rightFrame">热线号落地号</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/multi981/showAllMTulti981.action" target="rightFrame">多媒体租户分机号</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/SmsPort/searchSmsPort.action" target="rightFrame">短信端口</a><i></i></li>
	    </ul>    
	    </dd>
	    
	    <dd>
	    <div class="title">
	    <span><img src="${pageContext.request.contextPath}/images/leftico01.png"/></span>计费明细
	    </div>
	   	<ul class="menuson">
	       <li><cite></cite><a href="${pageContext.request.contextPath}/dtvoice/showDtVoice.action?page=1" target="rightFrame">语音明细</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/multiVoice/showMultiVoice.action?page=1" target="rightFrame">语音明细(多媒体)</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/dtOnline/showOnlineService.action?page=1" target="rightFrame">非语音明细</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/addOrder/showAddOrder.action?page=1" target="rightFrame">补单明细</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/changeOrder/showChangeOrder.action?page=1" target="rightFrame">改单明细</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/dtMessage/showDtMessage.action?page=1" target="rightFrame">短信明细</a><i></i></li>
	    </ul>    
	    </dd>
	    
	    <dd>
	    <div class="title">
	    <span><img src="${pageContext.request.contextPath}/images/leftico02.png" /></span>系统设置
	    </div>
	    <ul class="menuson">
		 	<li><cite></cite><a href="${pageContext.request.contextPath}/user/showUser.action?page=1" target="rightFrame" >用户管理</a><i></i></li>
		 	<li><cite></cite><a href="${pageContext.request.contextPath}/customer/showCustomer.action?page=1" target="rightFrame" >客户管理</a><i></i></li>
		    <li><cite></cite><a href="${pageContext.request.contextPath}/jsp/databackupJsp.action" target="rightFrame">数据备份</a><i></i></li>
		    <li><cite></cite><a href="${pageContext.request.contextPath}/syspParam/showSysParam.action" target="rightFrame">系统参数</a><i></i></li>
		    <li><cite></cite><a href="${pageContext.request.contextPath}/permission/showAllUserInfo.action?page=1" target="rightFrame">权限管理</a><i></i></li>
	    </ul>     
	    </dd> 
	    
	    
	    <dd><div class="title"><span><img src="${pageContext.request.contextPath}/images/leftico03.png" /></span>其他设置</div>
	    <ul class="menuson">
	        <%-- <li><cite></cite><a href="#">日志查询</a><i></i></li> --%>
	        <%-- <li><cite></cite><a href="${pageContext.request.contextPath}/jsp/errorpageJsp.action" target="rightFrame">出错页面</a><i></i></li> --%>
	        <li><cite></cite><a href="${pageContext.request.contextPath}/jsp/othersetJsp.action" target="rightFrame" >其他说明</a><i></i></li>
	    </ul>    
	    </dd>
    </c:if>
    <c:if test="${sessionScope.user.isroot eq 1 }">
    	<dd>
	    <div class="title">
	    <span><img src="${pageContext.request.contextPath}/images/leftico01.png"/></span>计费汇总
	    </div>
	   	<ul class="menuson">
	       <li><cite></cite><a href="${pageContext.request.contextPath}/monthReport/showMonthReportDetail.action?page=1" target="rightFrame">汇总明细表</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/monthReport/showALlMonthReport.action?page=1" target="rightFrame">结费报表</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/ComputeMoney/staForCompany.action" target="_blank">计费总量</a><i></i></li>
	   	   <li><cite></cite><a href="${pageContext.request.contextPath}/tlAccount/show.action" target="_blank">计费总量月统计表</a><i></i></li>
	    </ul>    
	    </dd>
	    
	    <dd>
	    <div class="title">
	    <span><img src="${pageContext.request.contextPath}/images/leftico01.png"/></span>信息基础
	    </div>
	   	<ul class="menuson">
	       <li><cite></cite><a href="${pageContext.request.contextPath}/moneyStandard/showCusStandard.action" target="rightFrame">计费标准</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/product/showProductClass.action?page=1" target="rightFrame">产品大类</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/hotline/show.action" target="rightFrame">热线号落地号</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/multi981/showAllMTulti981.action" target="rightFrame">多媒体租户分机号</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/SmsPort/searchSmsPort.action" target="rightFrame">短信端口</a><i></i></li>
	    </ul>    
	    </dd>
	    
	    <dd>
	    <div class="title">
	    <span><img src="${pageContext.request.contextPath}/images/leftico01.png"/></span>计费明细
	    </div>
	   	<ul class="menuson">
	       <li><cite></cite><a href="${pageContext.request.contextPath}/dtvoice/showDtVoice.action?page=1" target="rightFrame">语音明细</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/multiVoice/showMultiVoice.action?page=1" target="rightFrame">语音明细(多媒体)</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/dtOnline/showOnlineService.action?page=1" target="rightFrame">非语音明细</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/addOrder/showAddOrder.action?page=1" target="rightFrame">补单明细</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/changeOrder/showChangeOrder.action?page=1" target="rightFrame">改单明细</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/dtMessage/showDtMessage.action?page=1" target="rightFrame">短信明细</a><i></i></li>
	    </ul>    
	    </dd>
	    
	    <dd>
	    <div class="title">
	    <span><img src="${pageContext.request.contextPath}/images/leftico02.png" /></span>系统设置
	    </div>
	    <ul class="menuson">
		 	<li><cite></cite><a href="${pageContext.request.contextPath}/user/showUser.action?page=1" target="rightFrame" >用户管理</a><i></i></li>
		 	<li><cite></cite><a href="${pageContext.request.contextPath}/customer/showCustomer.action?page=1" target="rightFrame" >客户管理</a><i></i></li>
		    <li><cite></cite><a href="${pageContext.request.contextPath}/jsp/databackupJsp.action" target="rightFrame">数据备份</a><i></i></li>
		    <li><cite></cite><a href="${pageContext.request.contextPath}/syspParam/showSysParam.action" target="rightFrame">系统参数</a><i></i></li>
	    </ul>     
	    </dd> 
	    
	    
	    <dd><div class="title"><span><img src="${pageContext.request.contextPath}/images/leftico03.png" /></span>其他设置</div>
	    <ul class="menuson">
	        <%-- <li><cite></cite><a href="#">日志查询</a><i></i></li> --%>
	        <%-- <li><cite></cite><a href="${pageContext.request.contextPath}/jsp/errorpageJsp.action" target="rightFrame">出错页面</a><i></i></li> --%>
	        <li><cite></cite><a href="${pageContext.request.contextPath}/jsp/othersetJsp.action" target="rightFrame" >其他说明</a><i></i></li>
	    </ul>    
	    </dd>
    </c:if>
    <c:if test="${sessionScope.user.isroot eq 0 }">
    	<dd>
	    <div class="title">
	    <span><img src="${pageContext.request.contextPath}/images/leftico01.png"/></span>计费汇总
	    </div>
	   	<ul class="menuson">
	       <%-- <li><cite></cite><a href="${pageContext.request.contextPath}/monthReport/showMonthReportDetail.action?page=1&cusid=${sessionScope.user.cusid}" target="rightFrame">汇总明细表</a><i></i></li> --%>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/monthReport/showALlMonthReport.action?page=1&cusid=${sessionScope.user.cusid}" target="rightFrame">结费报表</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/ComputeMoney/staForCompany.action?cusid=${sessionScope.user.cusid}" target="rightFrame">计费总量</a><i></i></li>
	    </ul>    
	    </dd>
	    
	    <dd>
	    <div class="title">
	    <span><img src="${pageContext.request.contextPath}/images/leftico01.png"/></span>计费明细
	    </div>
	   	<ul class="menuson">
	       <li><cite></cite><a href="${pageContext.request.contextPath}/dtvoice/showDtVoice.action?page=1&cusid=${sessionScope.user.cusid}" target="rightFrame">语音明细</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/multiVoice/showMultiVoice.action?page=1&cusid=${sessionScope.user.cusid}" target="rightFrame">语音明细(多媒体)</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/dtOnline/showOnlineService.action?page=1&cusid=${sessionScope.user.cusid}" target="rightFrame">非语音明细</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/addOrder/showAddOrder.action?page=1&cusid=${sessionScope.user.cusid}" target="rightFrame">补单明细</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/changeOrder/showChangeOrder.action?page=1&cusid=${sessionScope.user.cusid}" target="rightFrame">改单明细</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath}/dtMessage/showDtMessage.action?page=1&cusid=${sessionScope.user.cusid}" target="rightFrame">短信明细</a><i></i></li>
	    </ul>    
	    </dd>
    </c:if>
    
   </dl>
</body>
</html>

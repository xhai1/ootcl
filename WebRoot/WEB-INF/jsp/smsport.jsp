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
<!--<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />-->
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath}/js/smsport.js"></script>
<script>
	function toPage(){
		var page = $("input[name='number']").val();
		window.location.href="${pageContext.request.contextPath}/SmsPort/searchSmsPortByCondition.action?nextPage="+page+"&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}";
	}
	function toExport(){
		window.location.href="${pageContext.request.contextPath}/product/exportProductClass.action?type=${type}&cusname=${cusname}";
	}
</script>
</head>

<body>
<div class="rightstyle">
<div class="grayStripe">短信端口归属</div>

        <div class = "reportAlign floatright">
            <form action="${pageContext.request.contextPath}/SmsPort/searchSmsPort.action" method="get">
                查询条件：
                短信端口：<input class="inputj" name="condition1" type="text" value="${page.condition1}"/>
              计时所属公司：
              <%-- <input class="inputj" name="condition2" type="text" value="${page.condition2 }"/> --%>
              <select class="inputj" name="condition2">
					                <option value="" 
					                	<c:if test="${page.condition2 eq '' }">
				             				selected="selected"
				             			</c:if>
					                >
					                	请选择公司
					                </option>
					                <c:forEach items="${customerVoList }" var="ct" >
					             		<option value="${ct.cusid }"
					             			<c:if test="${ct.cusid eq page.condition2 }">
					             				selected="selected"
					             			</c:if>
					             		>
					               			${ct.cusname }
					                	</option>
					                </c:forEach>
					                
				</select>
              
              运营商：<input class="inputj" name="condition3" type="text" value="${page.condition3 }"/>
                <input name="bt" type="submit" class="report" value="查询" />
                <input name="dc" type="button"  class="report" value="导入" onClick="location.href='${pageContext.request.contextPath}/SmsPort/smsPortimportJsp.action'"/>
                <input name="dc" type="button"  class="report" value="导出" onClick="location.href='${pageContext.request.contextPath}/SmsPort/exportSmsPort.action'"/>
                 <%-- <input name="button" type="button" class="report" value="添加"   onClick="location.href='${pageContext.request.contextPath}/SmsPort/smsportadd.action'"/>  --%>                  
                 <input name="button" type="button" class="report" value="添加"   onClick="window.open('${pageContext.request.contextPath}/SmsPort/smsportadd.action','_blank','height=450,width=500,top=200,left=450')"/>
            </form>
        </div>

<div class="clearfloat">显示条件：</div>

<table class="righttable" cellspacing="0px" cellpadding="2px">
<thead>

<tr><th>短信端口</th><th>短信用途</th><th>计时所属公司</th><th>运营商</th><th>修改日期</th><th colspan="2">操作</th></tr>
</thead>
	<tbody>
	
	<c:if test="${smsPorts.size() eq 0 }">
		<tr style="text-align:center;height:100px;"><td colspan="7">没有查询到记录</td></tr>
	</c:if>
	
	
		 <c:forEach items="${smsPorts}" var="smsPort">
			<tr ><td >${smsPort.port}</td><td>${smsPort.purpose}</td><td>${smsPort.cusname}</td><td>${smsPort.caroprator}</td><td><fmt:formatDate value="${smsPort.updatetime}" type="both" pattern="yyyy/MM/dd "/></td>
			<td><a href="${pageContext.request.contextPath}/SmsPort/updateSearchSmsPort.action?id=${smsPort.id}&cusid=${smsPort.cusid}&cusname=${smsPort.cusname}&port=${smsPort.port}&purpose=${smsPort.purpose}&caroprator=${smsPort.caroprator}&nextPage=${page.getCurrentPage()}&totalPage=${page.getTotalPage()}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}" class="green">修改</a></td>
			<td><a style="cursor:pointer" onclick="delSmsRecord('${pageContext.request.contextPath}/SmsPort/delSmsport.action?id=${smsPort.id}&nextPage=${page.currentPage}&totalPage=${page.getTotalPage()}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}','${smsPort.port}')"  class="warn">删除</a></td></tr>
        </c:forEach>
       <!--  <tr ><td >106988123456311000</td><td>鹰眼平台-短信满意度调查</td><td>TCL多媒体中国业务中心</td><td>2017/1/30</td><td><a href="#">修改</a></td></tr>
        <tr ><td >106988123456311000</td><td>鹰眼平台-短信满意度调查</td><td>TCL多媒体中国业务中心</td><td>2017/1/30</td><td><a href="#">修改</a></td></tr>
		 -->
		<!--<c:forEach items="${pageBean.list }" var="product">
			<tr ><td >${product.business }</td><td>${product.brand }</td><td>${product.typeid }</td><td>${product.type }</td><td>${product.typetimeing }</td><td>${product.cusname }</td></tr> 
		</c:forEach>-->
	
	
	</tbody>
</table>
<div class="tbfooter">
<div class="floatleft"><span>记录条数：${page.totalRecord }&nbsp;总页数：${page.getTotalPage() }&nbsp;当前页：${page.getCurrentPage()}</span></div>
            <div class="floatright">
                <form action="" method="get" class="formfloat" >
                    到第&nbsp;<input name="number"  value="${pageBean.page }" type="text" class="number" />&nbsp;页
                    <input name="GO" type="button" value="GO" onClick="toPage()"/>
                </form>
            </div>
<div class="floatright">
	           	<c:choose>
	           		<c:when test="${page.getCurrentPage() eq 1 }">
	           			<span class="disabled"><< 首页 </span>
	           		</c:when>
	           		<c:otherwise>
	           			<span class="disabled"><a href="${pageContext.request.contextPath}/SmsPort/searchSmsPortByCondition.action?nextPage=1&totalPage=${page.getTotalPage()}&condition1=${page.condition1}&totalRecord=${page.totalRecord}&condition2=${page.condition2}&condition3=${page.condition3}"><< 首页</a> </span>
	           		</c:otherwise>
	           	</c:choose>
	               
	               <c:choose>
	               	<c:when test="${page.getCurrentPage() > 1 }">
	               		<span class="disabled"><a href="${pageContext.request.contextPath}/SmsPort/searchSmsPortByCondition.action?nextPage=${page.getCurrentPage()-1}&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}">< 上一页</a> </span>
	               	</c:when>
	               	<c:otherwise>
	               		<span class="disabled">< 上一页 </span>
	               	</c:otherwise>
	               </c:choose>
	               
	               <c:choose>
	               	<c:when test="${page.getCurrentPage() < page.getTotalPage()}">
	               		<span class="disabled"> <a href="${pageContext.request.contextPath}/SmsPort/searchSmsPortByCondition.action?nextPage=${page.getCurrentPage()+1}&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}">下一页 ></a> </span>
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
	               		<span class="disabled"> <a href="${pageContext.request.contextPath}/SmsPort/searchSmsPortByCondition.action?nextPage=${page.getTotalPage()}&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}">尾页>></a> </span>
	               	</c:otherwise>
	               </c:choose>
	               <!-- <a href="#" class="current"> 1</a> 
	                <span href="#" class="current crc" >2</span> 
	               <a href="#" class="current"> 3 </a>
	                <a href="#" class="current">4 </a>
	               <a href="#" class="current"> 5 </a>
	               <a href="#" class="current"> 6 </a>
	                <a href="#" class="current">7 </a>
	               <a href="#" class="current"> 8 </a>
	                ... 
	               <a href="#" class="current"> 9 </a>
	                <a href="#" class="current">10</a> -->
         </div>   
</div>
</div>
</body>
</html>

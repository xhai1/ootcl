<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath}/js/custype.js"></script>

<script>
	function toPage(){
		var page = $("input[name='number']").val();
		window.location.href="${pageContext.request.contextPath}/product/showProductClass.action?page="+page+"&typeid=${typeid}&cusid=${cusid}&business=${business}&brand=${brand}";
	}
	function toExport(){
		window.location.href="${pageContext.request.contextPath}/product/exportProductClass.action?type=${type}&cusname=${cusname}&business=${business}&brand=${brand}";
	}
</script>
</head>

<body>
<div class="rightstyle">
<div class="grayStripe">产品大类</div>

        <div class = "reportAlign floatright">
            <form name="cusType" action="${pageContext.request.contextPath}/product/showProductClass.action" method="get">
                查询条件：
                业务类别：<input class="inputj" name="business" type="text" value="${business }"/>
                品牌：<input class="inputj" name="brand" type="text" value="${brand }"/>                   
                所属公司：
                <select id="selectCusid" name="cusid" onchange="getCustype('${pageContext.request.contextPath}',this.value)">
					                <option value="" 
					                	<c:if test="${cusid eq '' }">
				             				selected="selected"
				             			</c:if>
					                >
					                	请选择公司
					                </option>
					                <c:forEach items="${customerVoList }" var="ct" >
					             		<option value="${ct.cusid }"
					             			<c:if test="${ct.cusid eq cusid }">
					             				selected="selected"
					             			</c:if>
					             		>
					               			${ct.cusname }
					                	</option>
					                </c:forEach>
					                
				           </select>  
                产品大类：
             <select name="typeid"  class="inputj">
					<option value="typeidall" 
				    	<c:if test="${typeid eq '' }">
								selected="selected"
							</c:if>
				    >
				    	请选择产品大类
				    </option>
					<c:forEach items="${cusTypeList }" var="ct" >
						
						<option value="${ct.typeid }"
						<c:if test="${ct.typeid eq typeid }">
								selected="selected"
							</c:if>
						>
									${ct.typetimeing }
						</option>
					
					</c:forEach>
				</select>  
				
                <input name="bt" type="button" onclick="cusTypeSearch()" class="report" value="查询" />
                <input name="dc" type="button"  class="report" value="导出" onClick="toExport()"/>
            </form>
        </div>

<div class="clearfloat">显示条件：</div>

<table class="righttable" cellspacing="0px" cellpadding="2px">
<thead>
<tr><th colspan="4" style="background-color:#FFD966">业务系统</th><th colspan="2" style="background-color:#F8CBAD">计费系统</th></tr>
<tr><th style="background-color:#FFD966">业务类别</th><th style="background-color:#FFD966">品牌</th><th style="background-color:#FFD966">产品大类码</th><th style="background-color:#FFD966">产品大类</th><th style="background-color:#F8CBAD">计时产品大类</th><th style="background-color:#F8CBAD">计时所属公司</th></tr>
</thead>
	<tbody>
	<c:if test="${pageBean.list.size() eq 0 }">
		<tr><td colspan="6">没有查询到记录</td></tr>
	</c:if>
	
		<!-- 
		<tr ><td >彩电</td><td>中讯通</td><td>2896</td><td>中讯通</td><td>中讯通</td><td>TCL王牌电器（惠州）有限公司</td></tr>
		<tr ><td >彩电</td><td>中讯通</td><td>2896</td><td>中讯通</td><td>中讯通</td><td>TCL王牌电器（惠州）有限公司</td></tr>
		<tr ><td >彩电</td><td>中讯通</td><td>2896</td><td>中讯通</td><td>中讯通</td><td>TCL王牌电器（惠州）有限公司</td></tr> 
		-->
		<c:forEach items="${pageBean.list }" var="product">
			<tr ><td >${product.business }</td><td>${product.brand }</td><td>${product.typeid }</td><td>${product.type }</td><td>${product.typetimeing }</td><td>${product.cusname }</td></tr> 
		</c:forEach>
	
	
	</tbody>
</table>
<div class="tbfooter">
<div class="floatleft"><span>记录条数：${pageBean.totalCount }&nbsp;总页数：${pageBean.totalPage }&nbsp;当前页：${pageBean.page }</span></div>
            <div class="floatright">
                <form action="" method="get" class="formfloat" >
                    到第&nbsp;<input name="number"  value="${pageBean.page }" type="text" class="number" />&nbsp;页
                    <input name="GO" type="button" value="GO" onClick="toPage()"/>
                </form>
            </div>
<div class="floatright">
	           	<c:choose>
	           		<c:when test="${pageBean.page eq 1 }">
	           			<span class="disabled"><< 首页 </span>
	           		</c:when>
	           		<c:otherwise>
	           			<span class="disabled"><a href="${pageContext.request.contextPath}/product/showProductClass.action?page=1&typeid=${typeid}&cusid=${cusid}&business=${business}&brand=${brand}"><< 首页</a> </span>
	           		</c:otherwise>
	           	</c:choose>
	               
	               <c:choose>
	               	<c:when test="${pageBean.page>1 }">
	               		<span class="disabled"><a href="${pageContext.request.contextPath}/product/showProductClass.action?page=${pageBean.page-1}&typeid=${typeid}&cusid=${cusid}&business=${business}&brand=${brand}">< 上一页</a> </span>
	               	</c:when>
	               	<c:otherwise>
	               		<span class="disabled">< 上一页 </span>
	               	</c:otherwise>
	               </c:choose>
	               
	               <c:choose>
	               	<c:when test="${pageBean.page<pageBean.totalPage }">
	               		<span class="disabled"> <a href="${pageContext.request.contextPath}/product/showProductClass.action?page=${pageBean.page+1}&typeid=${typeid}&cusid=${cusid}&business=${business}&brand=${brand}">下一页 ></a> </span>
	               	</c:when>
	               	<c:otherwise>
	               		<span class="disabled"> 下一页 > </span>
	               	</c:otherwise>
	               </c:choose>
	               
	               <c:choose>
	               	<c:when test="${pageBean.page eq pageBean.totalPage}">
	               		<span class="disabled"> 尾页>> </span>
	               	</c:when>
	               	<c:otherwise>
	               		<span class="disabled"> <a href="${pageContext.request.contextPath}/product/showProductClass.action?page=${pageBean.totalPage}&typeid=${typeid}&cusid=${cusid}&business=${business}&brand=${brand}">尾页>></a> </span>
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

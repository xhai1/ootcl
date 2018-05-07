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
<!--<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />-->
<script>
function updateHotLine(){
	var business = $("input[name='business']").val().trim();
	var ivr = $("input[name='ivr']").val().trim();
	var telephone = $("input[name='telephone']").val().trim();
	var itSystem = $("input[name='itSystem']").val().trim();
	
	var itRemark = $("input[name='itRemark']").val().trim();
	var bigType = $("input[name='bigType']").val().trim();
	var cusId = $("select[name='cusId']").val().trim();
	var cusTelephone = $("input[name='cusTelephone']").val().trim();
	var chaSystem = $("input[name='chaSystem']").val().trim();
	
	var id = $("input[name='id']").val().trim();
	
	var orderId = $("input[name='orderId']").val().trim();
	window.location.href="${pageContext.request.contextPath}/hotline/renewHotLine.action?business="+business+"&ivr="
	+ivr+"&telephone="+telephone+"&itSystem="+itSystem+"&itRemark="+itRemark+"&bigType="+bigType
	+"&chaSystem="+chaSystem+"&id="+id+"&cusId="+cusId+"&cusTelephone="+cusTelephone+"&orderId="+orderId+
	"&currentPage=${page.getCurrentPage()}&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}&condition4=${page.condition4}&condition5=${page.condition5}";
}

</script>

</head>

<body>
<div class="rightstyle">
<div class="grayStripe">更改热线号落地号</div>

<table class="hotlineadd" cellspacing="0px" cellpadding="2px">
<tbody>
<tr><th>业务</th><td ><input name="business" type="text" value="${hotline.business}" /></td ><th>热线号</th><td ><input name="cusTelephone" type="text" value="${hotline.cusTelephone}" /></td ></tr>
<tr><th>IVR</th><td ><input name="ivr" type="text" value="${hotline.ivr}" /></td ><th>落地号</th><td ><input name="telephone" type="text" value="${hotline.telephone}" /></td ></tr>
<tr><th>所属系统</th><td ><input name="itSystem" type="text" value="${hotline.itSystem}" /></td >
<th>备注</th><td ><input name="itRemark" type="text" value="${empty hotline.itRemark ? '无' :hotline.itRemark}" /></td ></tr>
<tr><th>计时产品大类</th><td ><input name="bigType" type="text" value="${hotline.bigType}" /></td >
<th>计时所属公司</th><td >
<select id="cusid" name="cusId">
					                <option value="" 
					                	<c:if test="${cusid eq '' }">
				             				selected="selected"
				             			</c:if>
					                >
					                	请选择公司
					                </option>
					                <c:forEach items="${customerVoList }" var="ct" >
					             		<option value="${ct.cusid }"
					             			<c:if test="${ct.cusid eq hotline.cusId }">
					             				selected="selected"
					             			</c:if>
					             		>
					               			${ct.cusname }
					                	</option>
					                </c:forEach>
					                
</select>  
<script >
$("#cusid").width($("input[name='business']").width());
</script>
</td ></tr>
<tr><th>话务系统</th><td ><input name="chaSystem" type="text" value="${hotline.chaSystem}" /></td >
<th><input name="id" type="hidden" value="${hotline.id}" /></th><td ><input name="display" type="${(flag == 1 || flag == 0) ? 'text' : 'hidden'}" value="${flag == 1 ? '更新成功' : '更新失败'}" disabled="disabled"/></td ></tr>
<tr><th>顺序号</th><td ><input name="orderId" type="text" value="${hotline.orderId}" /></td ><th></th><td ><input  class="dis" name="button" type="button" value="更改" onClick="updateHotLine()" /> <input  class="dis" name="button" type="button" value="返回" onclick="history.go(-1)" /></td ></tr>
</tbody>
</table>
</div>
</body>
</html>

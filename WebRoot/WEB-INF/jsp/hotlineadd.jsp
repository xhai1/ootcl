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
<!--<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />-->
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/hotline.js"></script>
<script>
function hotlineadd(){

	if( checkForm() == false)return;
	var business = $("input[name='business']").val().trim();
	var cusTelephone = $("input[name='cusTelephone']").val().trim();
	var ivr = $("input[name='ivr']").val().trim();
	var telephone = $("input[name='telephone']").val().trim();
	var itSystem = $("input[name='itSystem']").val().trim();
	var itRemark = $("input[name='itRemark']").val().trim();
	var bigType = $("input[name='bigType']").val().trim();
	var cusId = $("select[name='cusId']").val().trim();
	
	var chaSystem = $("input[name='chaSystem']").val().trim();
	var orderId = $("input[name='orderId']").val().trim();
	
	window.location.href="${pageContext.request.contextPath}/hotline/addHotLine.action?business="+business+"&cusTelephone="
	+cusTelephone+"&telephone="+telephone+"&bigType="+bigType+"&ivr="+ivr+"&itSystem="+itSystem+"&itRemark="+itRemark
	+"&cusId="+cusId+"&chaSystem="+chaSystem+"&orderId="+orderId;
}
</script>
</head>
<body>
<div class="rightstyle">
<div class="grayStripe">添加热线号落地号</div>

<table class="hotlineadd" cellspacing="0px" cellpadding="2px">
<tbody>
<tr><th>业务</th><td ><input name="business" type="text" value="" /></td ><th>热线号</th><td ><input name="cusTelephone" type="text" value="" /></td ></tr>
<tr><th>IVR</th><td ><input name="ivr" type="text" value="" /></td ><th>落地号</th><td ><input name="telephone" type="text" value="" /></td ></tr>
<tr><th>所属系统</th><td ><input name="itSystem" type="text" value="" /></td >
<th>备注</th><td ><input name="itRemark" type="text" value="" /></td ></tr>
<tr><th>计时产品大类</th><td ><input name="bigType" type="text" value="" /></td >
<th>计时所属公司</th><td >

<!-- <input name="cusName" type="text" value="" /> -->
<select class="inputj" name="cusId">
					                <option value="" 
					                	
					                >
					                	请选择公司
					                </option>
					                <c:forEach items="${customerVoList }" var="ct" >
					             		<option value="${ct.cusid }"					             			
					             		>
					               			${ct.cusname }
					                	</option>
					                </c:forEach>
					                
</select>

<script type="text/javascript">
$("select[name='cusId']").width( $("input[name='bigType']").width() );
</script>
</td ></tr>
<tr><th>话务系统</th><td ><input name="chaSystem" type="text" value="" /></td >
<th></th><td >${empty err ? '' : err}</td ></tr>
<tr><th>顺序号</th><td ><input name="orderId" type="text" value="100" /></td ><th></th><td ><input  class="dis" name="button" type="button" value="添加" onClick="hotlineadd()" /> <input class="dis" name="update" type="button" value="关闭" onclick="window.close()" /></td ></tr>
</tbody>
</table>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<!-- <link href="../../css/xstyle.css" rel="stylesheet" type="text/css" /> -->
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript">
	function updatePermission(){
		var flag=confirm("确认更改吗?")
		if(flag){
			permission.submit();
		}
		
		
	}
</script>
</head>

<body class="rightstyle">
<form action="${pageContext.request.contextPath}/permission/updatePermissionForUser.action" id="permission" name="permission">
	<div align="center" style="margin-top:15%;margin-left:1%">
		
		<input type="hidden" name="id" value="${userVo.id }"/>
		
		<img src="${pageContext.request.contextPath}/images/i07.png" alt="" />
		<input type="radio" name="isroot" value="2" 
			<c:if test="${userVo.isroot eq 2}">
				checked="checked"
			</c:if>
		/>超级管理员<br /><br /><br />
		<img src="${pageContext.request.contextPath}/images/i07.png" alt="" />
		<input type="radio" name="isroot" value="1" 
			<c:if test="${userVo.isroot eq 1}">
				checked="checked"
			</c:if>
		/>内部用户<br /><br /><br />
		<img src="${pageContext.request.contextPath}/images/i07.png" alt="" />
		<input type="radio" name="isroot" value="0" 
			<c:if test="${userVo.isroot eq 0}">
				checked="checked"
			</c:if>
		/>外部用户<br /><br /><br />
		
		<input type="button" name="提交" value="提交" onclick="updatePermission()"/>
		<input type="button" name="取消" value="取消" onclick="history.go(-1)"/>
	</div>
</form>
	
	
</body>
</html>



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<!-- <link href="../../css/xstyle.css" rel="stylesheet" type="text/css" /> -->
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script>
	function checkForm(){
		// 校验用户名:
		// 获得用户名文本框的值:
		var cusid = document.getElementById("cusid").value;
		if(cusid == null || cusid == ''){
			alert("客户ID不能为空!");
			return false;
		}
		// 校验密码:
		// 获得密码框的值:
		var cusname = document.getElementById("cusname").value;
		if(cusname == null || cusname == ''){
			alert("公司名称不能为空!");
			return false;
		}
		// 校验确认密码:
		var telephone = document.getElementById("telephone").value;
		if(telephone == null || telephone == ''){
			alert("电话号码不能为空");
			return false;
		}
	}

</script>
<style>
*{
	font-size:16px;
}
</style>
</head>

<body class="rightstyle">

<div>
<div class="grayStripe">修改客户信息</div>
<form action="${pageContext.request.contextPath}/customer/updateCustomer.action?page=${page}" method="post" onsubmit="return checkForm();">
	<input name="id" type="hidden" value="${customerVo.id }"/>
	<table class="userup"> 
	    <tr>
	        <th scope="col"><!-- 客户ID --></th> <td class="center"><input name="cusid" id="cusid" type="hidden" value="${customerVo.cusid }" /></td>     
	    </tr>  
	    <tr>
	     	<th scope="col">公司名称</th><td class="center"><input name="cusname" id="cusname" type="text" readonly="readonly" value="${customerVo.cusname }" /></td>
	     </tr> 
	     <tr>
	     	<th scope="col">电话号码</th> <td class="center"><input name="telephone" id="telephone" type="text" value="${customerVo.telephone }" /></td>
	     </tr> 
	     
	     <tr>
	    	 <th scope="col"></th><td class="center"><input class="dis" name="update" type="submit" value="修改"/> <input class="dis" name="update" type="button" value="返回" onclick="history.go(-1)" /></td>
	     </tr> 
	</table>
</form>

</div>
</body>
</html>



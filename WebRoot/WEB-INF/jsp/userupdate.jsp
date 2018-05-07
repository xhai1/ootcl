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
		var username = document.getElementById("username").value;
		if(username == null || username == ''){
			alert("用户名不能为空!");
			return false;
		}
		// 校验密码:
		// 获得密码框的值:
		var password = document.getElementById("password").value;
		if(password == null || password == ''){
			alert("密码不能为空!");
			return false;
		}
		// 校验确认密码:
		var truename = document.getElementById("truename").value;
		if(truename == null || truename == ''){
			alert("真实姓名不能为空");
			return false;
		}
		// 获得用户名文本框的值:
		var title = document.getElementById("title").value;
		if(title == null || title == ''){
			alert("职位不能为空!");
			return false;
		}
		var telephone = document.getElementById("telephone").value;
		if(telephone == null || telephone == ''){
			alert("电话号码不能为空!");
			return false;
		}
		var email = document.getElementById("email").value;
		if(email == null || email == ''){
			alert("邮箱不能为空!");
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
<div class="grayStripe">修改用户信息</div>
<form action="${pageContext.request.contextPath}/user/updateUser.action" method="post" onsubmit="return checkForm();">
	<input name="id" type="hidden" value="${userVo.id }"/>
	<table class="userup"> 
	    <tr>
	        <th scope="col">用户名</th> <td class="center">	<input name="username" id="username" type="text" value="${userVo.username }" /></td>     
	    </tr>  
	    <tr>
	    <!-- ${userVo.password } -->
	     	<th scope="col">用户密码</th><td class="center"><input name="password" id="password" type="text" value="请输入新密码" /></td>
	     </tr> 
	     
	     <tr>
	     	<th scope="col">真实姓名</th> <td class="center"><input name="truename" id="truename" type="text" value="${userVo.truename }" /></td>
	     </tr> 
	     <tr>
	    	  <th scope="col">职务</th><td class="center"><input name="title" id="title" type="text" value="${userVo.title }" /></td>
	     </tr> 
	     <tr>
	  		   <th scope="col">电话</th><td class="center"><input name="telephone" id="telephone" type="text" value="${userVo.telephone }" /></td>
	     </tr> 
	     <tr>
	    	 <th scope="col">邮箱</th><td class="center"><input name="email" id="email" type="text" value="${userVo.email }" /></td>
	     </tr>
	     <c:if test="${userVo.isroot eq 0 }">
	     	<tr>
	    	  <th scope="col">所属公司</th> <td class="center">
		          <select name="cusid">
		              <%-- <option value="${userVo.isroot }"
		              	<c:if test="${userVo.isroot eq 1}">
		              		selected="selected"
		              	</c:if>
		              >内部用户</option>
		              <option value="${userVo.isroot }"
		              	<c:if test="${userVo.isroot eq 0}">
		              		selected="selected"
		              	</c:if>
		              >外部用户</option> --%>
		              
		              <c:forEach items="${customerList }" var="customer">
		              	<option value="${customer.cusid }"
			              	<c:if test="${customer.cusid eq userVo.cusid}">
			              		selected="selected"
			              	</c:if>
			              >${customer.cusname }</option>
		              	
		              </c:forEach>
		          </select>          
		          </td>      
		     </tr> 
	     </c:if> 
	     
	     
	     <script>
	     	$(document).ready(function(){
					  $("#password").click(function(){
					    $("#password").val("");
					  });
			});
			$("select[name='cusid']").width($("input[name='username']").width());
	     </script>
	     <tr>
	    	 <th scope="col"></th><td class="center"><input class="dis" name="update" type="submit" value="修改"/> <input class="dis" name="update" type="button" value="返回" onclick="history.go(-1)" /></td>
	     </tr> 
	</table>
</form>

</div>
</body>
</html>



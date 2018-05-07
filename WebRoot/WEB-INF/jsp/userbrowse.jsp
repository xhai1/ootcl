<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<!-- <link href="../../css/xstyle.css" rel="stylesheet" type="text/css" /> -->
<style>
*{
	font-size:16px;
}
</style>
</head>

<body class="rightstyle">

<div>
<div class="grayStripe">查看用户信息</div>

<table class="userup"> 
    <tr>
        <th scope="col">用户名</th> <td class="center">${userVo.username }</td>     
    </tr>  
    <%-- <tr>
     	<th scope="col">用户密码</th><td class="center">${userVo.password }</td>
     </tr>  --%>
     <tr>
     	<th scope="col">真实姓名</th> <td class="center">${userVo.truename }</td>
     </tr> 
     <tr>
    	  <th scope="col">所属公司</th><td class="center">${userVo.cusname }</td>
     </tr> 
     <tr>
  		   <th scope="col">电话</th><td class="center">${userVo.telephone }</td>
     </tr> 
     <tr>
    	 <th scope="col">邮箱</th><td class="center">${userVo.email }</td>
     </tr> 
     <tr>
    	  <th scope="col">用户职务</th> <td class="center">${userVo.title }</td>      
     </tr> 
     <tr>
    	 <th scope="col"></th><td class="center"><input name="update" type="button" value="返回" onClick="javascript:history.go(-1)" /></td>
     </tr> 
</table>
</div>
</body>
</html>



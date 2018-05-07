<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<!--<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" /> -->
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
		var span1 = document.getElementById("span1").innerHTML;
		if(span1.trim().length>0){
			alert("用户名已存在");
			return false;
		}
	}
	
	function checkUsername(){
		// 获得文件框值:
		var username = document.getElementById("username").value;
		if(username.trim().length>0){
			// 1.创建异步交互对象
			var xhr = createXmlHttp();
			// 2.设置监听
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						document.getElementById("span1").innerHTML = xhr.responseText;
					}
				}
			}
			// 3.打开连接
			xhr.open("GET","${pageContext.request.contextPath}/user/findByUsername.action?time="+new Date().getTime()+"&username="+username,true);
			// 4.发送
			xhr.send(null);
		}
		
	}
	
	function createXmlHttp(){
		   var xmlHttp;
		   try{ // Firefox, Opera 8.0+, Safari
		        xmlHttp=new XMLHttpRequest();
		    }
		    catch (e){
			   try{// Internet Explorer
			         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
			      }
			    catch (e){
			      try{
			         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			      }
			      catch (e){}
			      }
		    }

			return xmlHttp;
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
<div class="grayStripe">添加用户</div>
<form action="${pageContext.request.contextPath}/user/addUser.action" method="post" onsubmit="return checkForm();">
	<table class="userup"> 
	    <tr>
	        <th scope="col">用户名</th> <td class="center">	<input name="username" id="username" type="text" onblur="checkUsername()"/><span id="span1"></span>  </td>
	    </tr>  
	    <tr>
	     	<th scope="col">用户密码</th><td class="center"><input name="password" id="password" type="text"/></td>
	     </tr> 
	     <tr>
	     	<th scope="col">真实姓名</th> <td class="center"><input name="truename" id="truename" type="text"/></td>
	     </tr> 
	     <tr>
	    	  <th scope="col">职务</th><td class="center"><input name="title" id="title" type="text"/></td>
	     </tr> 
	     <tr>
	  		   <th scope="col">电话</th><td class="center"><input name="telephone" id="telephone" type="text"/></td>
	     </tr> 
	     <tr>
	    	 <th scope="col">邮箱</th><td class="center"><input name="email" id="email" type="text"/></td>
	     </tr> 
	     <tr>
	    	  <th scope="col">所属公司</th> <td class="center">
	          <select name="cusid" id="cusid" >
	          		<c:if test="${sessionScope.user.isroot eq 2 }">
	          			<option
		              selected="selected" value=""
		              size="50">内部用户</option>
	          		</c:if>
		          	<!-- <option
		              selected="selected" value=""
		              >内部用户</option> -->
	          	<c:forEach items="${customerList }" var="customer">
	          		<option
		              value="${customer.cusid }"
		              >${customer.cusname }</option>
	          	</c:forEach>
	              
	              
	          </select>
	          </td>      
	     </tr> 
 <script >
		$("#cusid").width($("input[name='username']").width());
</script>
	     <tr>
	    	 <th scope="col"></th><td class="center"><input class="dis" name="update" type="submit" value="添加" /><input class="dis" name="update" type="button" value="关闭" onclick="window.close()" /></td>
	     </tr> 
	</table>
</form>

</div>
</body>
</html>



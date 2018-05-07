<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script>
	function checkForm(){
		// 校验用户名:
		// 获得用户名文本框的值:
		/* var cusid = document.getElementById("cusid").value;
		if(cusid == null || cusid == ''){
			alert("客户ID不能为空!");
			return false;
		} */
		// 校验密码:
		// 获得密码框的值:
		var cusname = document.getElementById("cusname").value;
		if(cusname == null || cusname == ''){
			alert("公司名称不能为空!");
			return false;
		}
		// 校验确认密码:
		/* var telephone = document.getElementById("telephone").value;
		if(telephone == null || telephone == ''){
			alert("电话号码不能为空");
			return false;
		} */
		var span1 = document.getElementById("span1").innerHTML;
		if(span1.trim().length>0){
			alert("客户名称已存在");
			return false;
		}
	}
	
	function checkCusName(){
		// 获得文件框值:
		var cusname = document.getElementById("cusname").value;
		if(cusname.trim().length>0){
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
			xhr.open("GET","${pageContext.request.contextPath}/customer/findByCusid.action?time="+new Date().getTime()+"&cusname="+cusname,true);
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
<div class="grayStripe">添加客户</div>
<form action="${pageContext.request.contextPath}/customer/addCustomer.action" method="post" onsubmit="return checkForm();">
	<table class="userup"> 
	    <!-- <tr>
	        <th scope="col">客户ID</th> <td class="center">	<input name="cusid" id="cusid" type="text" onblur="checkCusid()"/><span id="span1"></span></td>     
	    </tr>   -->
	    <tr>
	     	<th scope="col">公司名称</th><td class="center"><input name="cusname" id="cusname"  onblur="checkCusName()" type="text"/><span id="span1"></span></td>
	     </tr> 
	     <tr>
	     	<th scope="col">热线号</th> <td class="center"><input name="telephone" id="telephone" type="text"/></td>
	     </tr> 
	     <tr>
	    	 <th scope="col"></th><td class="center"><input class="dis" name="update" type="submit" value="添加" /><input class="dis" name="update" type="button" value="关闭" onclick="window.close()" /></td>
	     </tr> 
	</table>
</form>

</div>
</body>
</html>



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })
	});
	function tologin(){
		if (top != self){//强制反框架   
			 top.location = self.location;      
			}  
			if(parseInt(self.dialogWidth) > 0){//反模态窗口  
			 var parentWin=window.dialogArguments;  
			 window.close();  
			 parentWin.self.location = '';  
			}  
	}
	
</script> 

</head>

<body onload="tologin()" style="background-color:#1c77ac; background-image:url(${pageContext.request.contextPath}/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎使用本系统</span>    
    </div>
    <form action="${pageContext.request.contextPath}/login.action" method="post" onsubmit="">
	    <div class="loginbody">
	    
	    <span class="systemlogo"></span> 
	     
	    <div class="loginbox">
	    
	    <ul>
	    <li><input name="username" type="text" class="loginuser"/></li>
	    <li><input name="password" type="password" class="loginpwd"/></li>
	    <li><input type="submit" class="loginbtn" value="登录"/><label><!-- <input name="" type="checkbox" value="" checked="checked" />记住密码</label> --></li>
	    </ul>
	    
	    
	    </div>
	    
	    </div>
    </form>
    
    
    
    
    <div class="loginbm">
    		惠州客音商务服务有限公司&nbsp;&nbsp;&nbsp;&nbsp;
    		<!-- 版权所有(C)2017 粤ICP备XXXXXXXXXX号  -->
    		Copyright © 版权所有
    </div>
</body>
</html>

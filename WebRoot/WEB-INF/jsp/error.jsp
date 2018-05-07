<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	
<script language="javascript">
	$(function(){
    $('.error').css({'position':'absolute','left':($(window).width()-490)/2});
	$(window).resize(function(){  
    $('.error').css({'position':'absolute','left':($(window).width()-490)/2});
    })  
});  
</script> 


</head>


<body style="background:#edf6fa;">

	<!-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">404错误提示</a></li>
    </ul>
    </div> -->
    
    <div class="error">
    
    <h2>${msg }<br/></h2>
    <!-- <p>看到这个提示，就自认倒霉吧!</p> -->
    <div class="reindex"><a href="javascript:history.go(-1)">立即返回</a><span id="show" style="display:inline-block"></span></div>
     <script type = "text/javascript" >
      //<![CDATA[
  
      var t = 5; //设定跳转的时间
	  setInterval("refer()", 1000); //启动1秒定时
	  function refer()
	  {
	      if (t == 0)
	      {
	      	history.go(-1); //#设定跳转的链接地址
	      }
	      document.getElementById('show').innerHTML = "将在" + t + "秒后跳转..."; // 显示倒计时
	      t--; // 计数器递减
	      //本文转自：
	  }
	  document.getElementById('show').innerHTML = "将在" + t + "秒后跳转..."; // 显示倒计时
	  t--;
  //]]>
  </script>
    </div>
</body>
</html>

<!--<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
 <link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />
 <script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
 <style type="text/css">
 .moneystdadd {
	width: 30%;
	margin-top: 50px;
	margin-right: auto;
	margin-left: auto;
}
 </style>
 <script>
	function checkForm(){
		var clazz = document.getElementById("clazz").value;
		if(clazz == null || clazz == ''){
			alert("类别不能为空!");
			return false;
		}
		var value = document.getElementById("value").value;
		if(value == null || value == ''){
			alert("时长标准不能为空!");
			return false;
		}
		var number = document.getElementById("number").value;
		if(number == null || number == ''){
			alert("时长标准单数不能为空");
			return false;
		}
		var price = document.getElementById("price").value;
		if(price == null || price == ''){
			alert("计费标准单价不能为空!");
			return false;
		}
		var times = document.getElementById("times").value;
		if(times == null || times == ''){
			alert("计费标准单位不能为空!");
			return false;
		}
	}
</script>
</head>

<body>

<div class="grayStripe">增加类别</div>
<div class="moneystdadd">
		<form action="${pageContext.request.contextPath}/moneyStandard/addCusStandardClassDetail.action" method="post" onsubmit="return checkForm();">
		    <table class="moneysadd">
		        
		        <tr>
		            <td class="center">类别:</td>
		            <td><input name="clazz" id="clazz" type="text" value="" /></td>
		        </tr>
		        <tr>
		            <td class="center">时长标准(分钟/单)：</td>
		            <td><input name="value" id="value" type="text" value="" /></td>
		        </tr>
		         <tr>
		            <td class="center">时长标准单数：</td>
		            <td><input name="number" id="number" type="text" value="1" /></td>
		        </tr>
		        
		        <tr>
		            <td class="center">计费标准单价（元/分钟）：</td>
		            <td><input name="price" id="price" type="text" value="" /></td>
		        </tr>
		        <tr>
		            <td class="center">计费标准单位（分钟）：</td>
		            <td><input name="times" id="times" type="text" value="60" /></td>
		        </tr>
		         <tr>
		            <td class="center"><input name="mType" type="hidden" value="1" /></td>
		            <td><input name="ioType" type="hidden" value="1" />
		            	<input name="cusId" type="hidden" value="${moneyStandardadd.cusId}" />
		            	</td>
		        </tr>
		        
		          <tr>
		            <td class="center"></td>
		            <td class="alignr"><input name="update" type="submit" value="增加" />&nbsp;&nbsp;<input name="update" type="button" value="关闭" onclick="window.close()"/></td>
		        </tr>
		        
		    </table>
		</form>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<!--<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />-->
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script>
	function checkForm(){
	
		var port = document.getElementById("port").value;
		if(port == null || port == ''){
			alert("短信端口不能为空!");
			return false;
		}
		var purpose = document.getElementById("purpose").value;
		if(purpose == null || purpose == ''){
			alert("短信用途不能为空!");
			return false;
		}
		var cusname = document.getElementById("cusname").value;
		if(cusname == null || cusname == ''){
			alert("计时所属公司不能为空");
			return false;
		}
		var caroprator = document.getElementById("caroprator").value;
		if(caroprator == null || caroprator == ''){
			alert("运营商不能为空");
			return false;
		}
	}
</script>
</head>

<body>

<div class="grayStripe">添加短信端口归属</div>
<div >
    <form action="${pageContext.request.contextPath}/SmsPort/addSmsPort.action" method="post" onsubmit="return checkForm();">

<table class="smsutable">


    <tr>
        <th>短信端口:</th>
        <td><input name="port" id="port" type="text" value="" class="allwidth"/></td>
    </tr>
    <tr>
        <th>短信用途:</th>
        <td><input name="purpose" id="purpose" type="text" value="" class="allwidth" /></td>
    </tr>
    <tr>
        <th>计时所属公司:</th>
        <td>
        
        <!-- <input name="cusname" id="cusname" type="text" value="" class="allwidth"/> -->
        <select  name="cusid">
					                <option value="" 
					                	<c:if test="${page.condition2 eq '' }">
				             				selected="selected"
				             			</c:if>
					                >
					                	请选择公司
					                </option>
					                <c:forEach items="${customerVoList }" var="ct" >
					             		<option value="${ct.cusid }"
					             			<c:if test="${ct.cusid eq page.condition2 }">
					             				selected="selected"
					             			</c:if>
					             		>
					               			${ct.cusname }
					                	</option>
					                </c:forEach>
					                
			</select>
        </td>
    </tr>
    <tr>
        <th>运营商:</th>
        <td><input name="caroprator" id="caroprator" type="text" value="" class="allwidth"/></td>
    </tr>
        <tr>
        <th></th>
        <td><input name="submit" type="submit"  value="添加"/>&nbsp;<input name="button" type="button"  value="关闭" onclick="window.close()"/></td>
    </tr>
</table>

</form>


</div>
</body>
</html>

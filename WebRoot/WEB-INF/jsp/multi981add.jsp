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
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<!--<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />-->
<style>
body{
	background-color:#EDF6FA;
}
</style>
<!-- <script type="text/javascript">
function changeBigTypeAndCusName(){
		
	var telephone  =  $("input[name='telephone']").val().trim();
	   $.ajax({
        type: "post",
        dataType: 'json',
        //接受数据格式 
        cache: false,
        data: "telephone=" + telephone,
        url: "${pageContext.request.contextPath}/multi981/autoShowAddMultiMedia981.action",
        beforeSend: function(XMLHttpRequest) {},
        success: function(data) {
            var str = data.split(","); //接收返回的数据
             alert(data);
          	if (str.length > 0 && str[0].length > 0) {
				bigType.value = str[0];
				cusName.value = str[1];
			} else {
			
			}
        },
        error: function() {
            //请求出错处理
            alert(data);
        }
    });
}

</script> -->
<script language="javascript">
	

	function getXMLHTTPRequest() {
		var xRequest = null;
		if (window.XMLHttpRequest) {
			xRequest = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xRequest = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xRequest;
	}

	var xmlhttp;
	// 启动AJAX请求
	function searchSuggest() {
		xmlhttp = getXMLHTTPRequest();
		//判断XMLHttpRequest对象是否成功创建
		if (!xmlhttp) {
			alert("不能创建XMLHttpRequest对象实例");
			return false;
		}
		//创建请求结果处理程序
		xmlhttp.onreadystatechange = processReuqest;
		var telephone  =  $("input[name='telephone']").val().trim();

		xmlhttp.open("GET", "${pageContext.request.contextPath}/multi981/autoShowAddMultiMedia981.action?telephone=" + telephone, true);
		xmlhttp.send(null);
	}
	// 事件处理函数
	function processReuqest() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var str = xmlhttp.responseText.split(","); //接收返回的数据
			//alert(str);
          	if (str.length > 0 && str[0].length > 0) {
          /* 	var bigType = document.getElementByName("bigType");
          	var cusName = document.getElementByName("cusName");
          	bigType.innerHTML= str[0];
          	cusName.innerHTML=  str[1]; */
          	
          	// $("#result").html(data.msg) ;  
				/* bigType.value = str[0];
				cusName.value = str[1]; */
				$("#bigType ").empty();
				$("#cusName ").empty();
				$("#bigType").append("<option  selected>"+ str[0]+"</option>"); 
				$("#cusName").append("<option selected>"+ str[1]+"</option>"); 
			} else {
			
			}

	}
}
	
	function checkForm(){
	
		var itenantId = document.getElementById("itenantId").value;
		if(itenantId == null || itenantId == ''){
			alert("租户不能为空!");
			return false;
		}
		var extNumber = document.getElementById("extNumber").value;
		if(extNumber == null || extNumber == ''){
			alert("分机号不能为空!");
			return false;
		}
		var telephone = document.getElementById("telephone").value;
		if(telephone == null || telephone == ''){
			alert("落地号不能为空");
			return false;
		}
	}
</script>


</head>

<body>

<div class="grayStripe">
        多媒体租户分机号添加     
</div>
<div class="sysparam">
    <form action="${pageContext.request.contextPath}/multi981/AddMultiMedia981.action" method="post" onsubmit="return checkForm();">


<table>
 	 
    <tr>
        <td class="center">租户:</td>
        <td><input name="itenantId" id="itenantId" type="text" size="50" value=""/></td>
    </tr>
    <tr>
        <td class="center">分机号:</td>
        <td><input name="extNumber" id="extNumber" type="text" size="50" value=""/></td>
    </tr>
    <tr>
        <td class="center">落地号:</td>
       <!--  <td><input name="telephone" id="telephone" type="text" size="50" value=""  onkeyup="searchSuggest()" /></td> -->
       <td><input name="telephone" id="telephone" type="text" size="50" value=""  /></td> 
    </tr>
    <tr>
        <td class="center">计时产品大类:</td>
        <td class="center">
        <!-- <select name="bigType" size="" class="multi981select" id="bigType" >
        		<option>家电</option>
                <option>彩电</option>     
        </select> -->
       <input name="bigType" id="bigType" type="text" size="50" value=""  />
        </td>
    </tr>
    <tr>
        <td class="center">计时所属公司:</td>
        <td class="center ">
        	<!--  <select name="cusName" size="" class="multi981select" id="cusName" >                
                <option>TCL王牌电器（惠州）有限公司</option>
                <option>亿人合</option>
        	</select>   -->   
        	
        	  <select id="selectCusid" name="cusId">
					                <option value="" 
					                	<c:if test="${cusid eq '' }">
				             				selected="selected"
				             			</c:if>
					                >
					                	请选择公司
					                </option>
					                <c:forEach items="${customerVoList }" var="ct" >
					             		<option value="${ct.cusid }"
					             			<c:if test="${ct.cusid eq cusid }">
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
    	<td></td>
        <td colspan="2" class="alignr"><input name="display" type="hidden" value="" />&nbsp;&nbsp;<input name="update" type="submit" value="添加" />&nbsp;&nbsp;<input name="" type="button" value="关闭" onclick="window.close()"/></td>        
    </tr>

</table>

</form>

</div>
<script type="text/javascript">
	$("#selectCusid").width($("#bigType").width());
</script>
</body>
</html>

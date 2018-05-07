<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />-->

   <link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css">  
<style>
body{
	font-size:12px;
 	background-color:#ffff00;
 } 
 </style>
</head>
<script>

function save(){
		cusstandard.submit();
}

 function firstImort(){
  document.getElementById("cusstandard").action="${pageContext.request.contextPath}/moneyStandard/firstSaveCusStandard.action";
  cusstandard.submit();
  
  }

</script>


<body >
<div class="">
    <div class="comlist">
        
        
        
        <ul class="manstanul">
            <h3 class="glyphicon glyphicon-th-list" style="color: rgb(0, 0, 0); font-size: 16px;">公司列表</h3>
             <!--<li class="listnone sel"><span class="glyphicon glyphicon-ok-sign" style="color: rgb(255, 140, 130); "> </span><a href="#"  class="black">TCL王牌电器有限公司</a></li>
            <li class="listnone"><span class="glyphicon glyphicon-record" style="color: rgb(68, 68, 225);  "> </span>TCL王牌电器有限公司</li> -->
            
            <c:forEach var="customert" items="${customers}" varStatus="idx">
             <c:if test="${customert.cusid == customer.cusid }">
           		 <li class="listnone"><span class="glyphicon glyphicon-record" style="color: rgb(68, 68, 225);  "> </span>${customert.cusname}</li>
             </c:if>
             
             <c:if test="${customert.cusid != customer.cusid }">
           		 <li class="listnone sel"><span class="glyphicon glyphicon-ok-sign" style="color: rgb(255, 140, 130); "> </span><a href="${pageContext.request.contextPath}/moneyStandard/comListSearchCusStandard.action?cusid=${customert.cusid}"  class="black">${customert.cusname}</a></li>
             </c:if>
			</c:forEach>
            
        </ul>
        
        </div>
        
    <!--控制开关-->
   <!-- <div class="openswitch">
    	<span class="glyphicon glyphicon-step-backward" style="color: rgb(255, 140, 60);"> </span>
    </div>
   <div class="openswitch hidden">
  		 <span class="glyphicon glyphicon-step-forward" style="color: rgb(255, 140, 60);"></span>
   </div> -->
   
   
<div class="manstan">
        
        <div class="manstantitile">计费标准</div>
        
        
        <div class="floatright">
            <form method="post" name="main" action="${pageContext.request.contextPath}/moneyStandard/searchCusStandard.action">
            
        		    公司：
                <input name="cusname" type="text" maxlength="80" value="${customersea.cusname}" /><input name="" type="submit" value="查询" class="dis" /> <input name="" type="button" value="修改" class="dis" onclick="save()"/>
                
                 <input name="" type="button" value="初始化导入" class="dis"  onClick="location.href='${pageContext.request.contextPath}/moneyStandard/ImportCusStandard.action'"/>
                 
                 <input name="" type="button" value="横向导入" class="dis hidden" /><input name="" type="button" value="数字转换" class="dis hidden" />
                 
                 <input name="" type="button" value="增加类别" onclick="location.href='${pageContext.request.contextPath}/moneyStandard/addCusStandardClass.action?cusId=${customer.cusid}'" />
            </form>
        </div>
            
         
        <div class="clearfloat"></div>
        
        <form method="post" id="cusstandard" name="cusstandard"  action="${pageContext.request.contextPath}/moneyStandard/saveCusStandard.action">
    <table  class="manstantable">
        <tr>
            <td>客户名称</td>
            <td colspan="3"><input name="cusname" type="text" value="${customer.cusname}"  class="center" /></td>
            
            </tr>
        <tr>
            <td rowspan="2">语音计时标准</td>
            <td rowspan="2">单价（元/小时）</td>
            <td>呼入</td>
            <td>呼出</td>
            </tr>
        <tr>      
        
         <c:forEach var="moneyStandard" items="${moneyStandards}" varStatus="idx">
             <c:if test="${(moneyStandard.mType == 0) && (moneyStandard.ioType == '0')}">
           		 <td><input name="moneyStandard[0].value" type="text" value="${moneyStandard.value}"  class="center"/></td>
            	 <td>
            	 
            			 <input name="moneyStandard[0].price" type="text" value="${moneyStandard.price }" class="center" />
            	 		<input name="moneyStandard[0].id" type="hidden" value="${moneyStandard.id }" class="center" />
            	 		<!-- 初始化导入默认 -->
            	 		<input name="moneyStandard[0].mType" type="hidden" value="${moneyStandard.mType }" class="center" />
            	 		<input name="moneyStandard[0].ioType" type="hidden" value="${moneyStandard.ioType }" class="center" />
            	 		
            	 		<input name="moneyStandard[0].times" type="hidden" value="${moneyStandard.times}" class="center" />
            	 		<input name="moneyStandard[0].number" type="hidden" value="${moneyStandard.number}" class="center" />
            	 </td>
             </c:if>
            
			</c:forEach>
			 
           
            </tr>
        <tr>
            <td rowspan="${classNum}">非语音计时标准</td>        
            </tr>
        <tr>       
            <td >类别</td>
            <td>时长标准(分钟/单)</td>
            <td>计费标准单价（元/小时）</td>
            </tr>
       		 <c:forEach var="moneyStandard" items="${moneyStandards}" varStatus="idx" >
       			 <c:if test="${(moneyStandard.mType == 1) && (moneyStandard.ioType == '1')}">
		       		 <tr>
			            <td>${moneyStandard.clazz}</td>
			            <td><input name="moneyStandard[${ idx.index+2}].value" type="text" value="${moneyStandard.value}"  class="center"/></td>
			            <td><input name="moneyStandard[${ idx.index+2}].price" type="text" value="${moneyStandard.price}"  class="center"/>
			            	<input name="moneyStandard[${idx.index+2}].id" type="hidden" value="${moneyStandard.id}"  class="center"/>
			            	
				            	<!-- 初始化导入默认 -->
	            	 		<input name="moneyStandard[${idx.index+2}].mType" type="hidden" value="${moneyStandard.mType}" class="center" />
	            	 		<input name="moneyStandard[${idx.index+2}].ioType" type="hidden" value="${moneyStandard.ioType}" class="center" />
	            	 		
	            	 		<input name="moneyStandard[${idx.index+2}].times" type="hidden" value="${moneyStandard.times}" class="center" />
	            	 		<input name="moneyStandard[${idx.index+2}].number" type="hidden" value="${moneyStandard.number}" class="center" />
	            	 		<input name="moneyStandard[${idx.index+2}].clazz" type="hidden" value="${moneyStandard.clazz}" class="center" />
			            </td> 
		            </tr>
	            </c:if>
            </c:forEach>       
       <!--  <tr>
            <td>微信</td>
            <td><input name="" type="text" value="10"  class="center" /></td>
            <td><input name="" type="text" value="10"  class="center"/></td>        
            </tr>
        <tr>
            <td>京东</td>
            <td><input name="" type="text" value="10"  class="center"/></td>
            <td><input name="" type="text" value="10"  class="center"/></td>
            </tr>
        <tr>
            <td>改单</td>
            <td><input name="" type="text" value="5"  class="center"/></td>
            <td><input name="" type="text" value="5"  class="center"/></td>
            </tr>
        <tr>
            <td>补单</td>
            <td><input name="" type="text" value="4"  class="center" /></td>
            <td><input name="" type="text" value="4"  class="center"/></td>
            </tr>
        <tr>
            <td>邮件</td>
            <td><input name="" type="text" value="10"  class="center" /></td>
            <td><input name="" type="text" value="10"  class="center"/></td>
            </tr>
        <tr> -->
        <c:forEach var="moneyStandard" items="${moneyStandards}" varStatus="idx">
       		<c:if test="${moneyStandard.mType == 2}">
	       		 <tr>
		            <td>短信</td>
		            <td>单价（元/条）</td>
		            <td colspan="2">
		            
		            <input name="moneyStandard[1].value" type="text" value="${moneyStandard.value}"  class="center"/>
		            <input name="moneyStandard[1].id" type="hidden" value="${moneyStandard.id}"  class="center"/>
		             <input name="moneyStandard[1].mType" type="hidden" value="${moneyStandard.mType}"  class="center"/>
		            </td>
	            </tr>            
            </c:if>
            
			</c:forEach>
    </table>
    
    <!--横向导入表格-->
    
    <table class="horizontable  hidden">
    <tr>        
        <th scope="col">客户名称</th>
        <th scope="col">呼入</th>
        <th scope="col">呼出</th>
        <th scope="col">APP</th>
        <th scope="col">微信</th>
        <th scope="col">京东</th>
        <th scope="col">改单</th>
        <th scope="col">补单</th>
        <th scope="col">邮件</th>
        <th scope="col">短信&nbsp;单价（元/条）</th>
    </tr>
    <tr>        
        <td><input name="" type="text" value="TCL王牌电器有限公司" style="width:200px;" /></td>
        <td><input name="" type="text" value="54.09" /></td>
        <td><input name="" type="text" value="52.22" /></td>
        <td><input name="" type="text" value="10" /></td>
        <td><input name="" type="text" value="10" /></td>
        <td><input name="" type="text" value="10" /></td>
        <td><input name="" type="text" value="5" /></td>        
        <td><input name="" type="text" value="4" /></td>
        <td><input name="" type="text" value="10" /></td>
        <td><input name="" type="text" value="0.06" style="width:100%;" /></td>
    </tr>
</table>
</form>  <!-- 公司标准结束 -->
    
    </div>
    
    
    <div class="clearfloat"></div>
    
</div>
</body>
</html>

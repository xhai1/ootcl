<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />

<!--    <link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css">   -->
<style>
body{
	font-size:12px;
 	background-color:#FFEEF0;
 } 
 
 
 
 </style>
 
 <style type="text/css"> 
.window{ 
    width:250px; 
    background-color:#d0def0; 
    position:absolute; 
    padding:2px; 
    margin:5px; 
    display:none; 
    } 
.content{ 
    height:150px; 
    background-color:#FFF; 
    font-size:14px; 
    overflow:auto; 
    } 
    .title{ 
        padding:2px; 
        color:#0CF; 
        font-size:14px; 
        } 
.title img{ 
        float:right; 
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
        

   
   
<div class="manstan">
        
        <div class="manstantitile">计费标准</div>
        
        
        <div class="floatright">
            <form method="post" name="main" action="${pageContext.request.contextPath}/moneyStandard/searchCusStandard.action">
            
        		    公司：
                <input name="cusname" type="text" maxlength="80" value="${customersea.cusname}" /><input name="" type="submit" value="查询" class="dis" />
                
                 <input name="" type="button" value="保存" class="dis" onclick="save()"/> 
                
                <input name="" type="button" value="放弃修改" class="dis" onclick="history.go(-1)"/> 
                
                <input name="" type="button" value="初始化导入" class="dis" onClick="location.href='${pageContext.request.contextPath}/moneyStandard/ImportCusStandard.action'"    />
                
                <input name="" type="button" value="横向导入" class="dis hidden" /><input name="" type="button" value="数字转换" class="dis hidden" />
                
                <input name="" type="button" value="增加类别"  onClick="window.open('${pageContext.request.contextPath}/moneyStandard/addCusStandardClass.action?cusId=${customer.cusid}','_blank','height=300,width=900,top=200,left=450,location=no')" />
            </form>
        </div>
            
         
        <div class="clearfloat"></div>
        
        <form method="post" id="cusstandard" name="cusstandard"  action="${pageContext.request.contextPath}/moneyStandard/saveCusStandard.action">
    <table  class="manstantable">
        <tr>
            <td>客户名称</td>
            <td colspan="3"><span>${customer.cusname}</span></td>
            
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
		            	 		<input name="moneyStandard[0].cusId" type="hidden" value="${moneyStandard.cusId}" class="center" />
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
							            <input name="moneyStandard[${idx.index+2}].cusId" type="hidden" value="${moneyStandard.cusId}" class="center" />
				            	 		<input name="moneyStandard[${idx.index+2}].mType" type="hidden" value="${moneyStandard.mType}" class="center" />
				            	 		<input name="moneyStandard[${idx.index+2}].ioType" type="hidden" value="${moneyStandard.ioType}" class="center" />
				            	 		
				            	 		<input name="moneyStandard[${idx.index+2}].times" type="hidden" value="${moneyStandard.times}" class="center" />
				            	 		<input name="moneyStandard[${idx.index+2}].number" type="hidden" value="${moneyStandard.number}" class="center" />
				            	 		<input name="moneyStandard[${idx.index+2}].clazz" type="hidden" value="${moneyStandard.clazz}" class="center" />
						            </td> 
					            </tr>
				            </c:if>
         		</c:forEach>   
            
			            
            

             <tr>
		            <td>短信</td>
		            <td>单价（元/条）</td>
		            <td colspan="2">
		                <c:forEach var="moneyStandard" items="${moneyStandards}" varStatus="idx">
       					<c:if test="${moneyStandard.mType == 2}">
       						<input name="moneyStandard[1].cusId" type="hidden" value="${moneyStandard.cusId}" class="center" />
		           			<input name="moneyStandard[1].value" type="text" value="${moneyStandard.value}"  class="center"/>
				            <input name="moneyStandard[1].id" type="hidden" value="${moneyStandard.id}"  class="center"/>
				            <input name="moneyStandard[1].mType" type="hidden" value="${moneyStandard.mType}"  class="center"/>
		           		</c:if>
            
						</c:forEach>
		            </td>
	         </tr>          
            
    </table>    
    
</form>  <!-- 公司标准结束 -->
    
    </div>
    
    
    <div class="clearfloat"></div>
    
</div>


</body>
</html>

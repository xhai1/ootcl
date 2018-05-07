<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jQuery.print.js"></script>
<script type='text/javascript'>
    //<![CDATA[
    $(function() {
        $("#ele2").find('.print-link').on('click', function() {
            //Print ele2 with default options
            $.print("#ele2");
        });
        $("#ele4").find('button').on('click', function() {
            //Print ele4 with custom options
            $("#ele4").print({
                //Use Global styles
                globalStyles : false,
                //Add link with attrbute media=print
                mediaPrint : false,
                //Custom stylesheet
                stylesheet : "http://fonts.useso.com/css?family=Inconsolata",
                //Print in a hidden iframe
                iframe : false,
                //Don't print this
                noPrintSelector : ".avoid-this",
                //Add this at top
                prepend : "Hello World!!!<br/>",
                //Add this on bottom
                append : "<br/>Buh Bye!"
            });
        });
        // Fork https://github.com/sathvikp/jQuery.print for the full list of options
    });
    //]]>
</script>




</head>

<body>

<div class="monthrecord">结算报表</div>

<div class = "reportAlign floatright">
      
</div>


<div class="clearfloat"></div>
<c:forEach items="${list }" var="cusreport">
<input name="dc" type="button" class="no-print" value="打印" onclick="jQuery.print('#ele${cusreport.id }')"/>
	<div id="ele${cusreport.id }">
		<div class="monbackground">
		<table class="monthfee" >
		    <tr>
		        <th scope="col" class="center " colspan="6">TCL统一服务热线计费系统</th>        
		    </tr>
		    <tr>
		        <td class="center " colspan="6">运营服务计费总表</td>
		    </tr>
		    <tr>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td class="right">NO.${cusreport.month }01</td>
		    </tr>
		    <tr>
			    <td>&nbsp;</td>
		        <td colspan="2" >客户姓名： ${cusreport.cusname }</td>
		        <td colspan="2" >服务号码：</td>
		    </tr>
		    <tr>
		    	<td>&nbsp;</td>
		        <td colspan="2" >客户地址：</td>
		        <td colspan="2" >计费时段：${cusreport.month }</td>
		    </tr>
		    <tr>
		    	<td>&nbsp;</td>
		    	<c:forEach items="${paraList }" var="para">
		    		<c:if test="${para.pkey eq 'compopacbank' }">
		    			<td colspan="2" >开户银行：${para.pvalue }</td>
		    		</c:if>
		    		<c:if test="${para.pkey eq 'comacount' }">
		    			<td colspan="2" >开户帐户：${para.pvalue }</td>
		    		</c:if>
		    	</c:forEach>
		    </tr>
		    <tr class="topandbottom">
		        <td class="center ">收费</td>
		        <td rowspan="2" class="center ">项目明细</td>
		        <td rowspan="2" class="center ">数量</td>
		        <td class="center ">收费</td>
		        <td rowspan="2" class="center ">项目明细</td>
		        <td rowspan="2" class="center ">数量</td>
		    </tr>
		    <tr class="topandbottom">
		        <td class="center ">项目</td>
		        <td class="center ">项目</td>
		    </tr>
		      <tr class="topandbottom">
		        <td rowspan="4" class="center ">呼入</td>
		        <td>通话次数</td>
		        <td>${cusreport.incount }次</td>
		        <td rowspan="4" class="center ">呼出</td>
		        <td>通话次数</td>
		        <td>${cusreport.outcount }次</td>
		    </tr>
		    
		        <tr class="topandbottom">
		        <td>呼入话务时长</td>
		        <td>${cusreport.inhour }小时</td>
		        <td>呼出话务时长</td>
		        <td>${cusreport.outhour }小时</td>
		    </tr>
		    
		        <tr class="topandbottom">
		        <td>计费标准</td>
		        <td>${cusreport.invalue }元/小时</td>
		        <td>计费标准</td>
		        <td>${cusreport.outvalue }元/小时</td>
		    </tr>
		    
		        <tr class="topandbottom">
		        <td>呼入运营费用合计</td>
		        <td>${cusreport.totalin }元</td>
		        <td>呼出运营费用合计</td>
		        <td>${cusreport.totalout }元</td>
		    </tr>
		    
		      <tr class="topandbottom">
		        <td colspan="6" class="center">短信:${cusreport.msgcount }条，单价：${cusreport.msgvalue }元/条，费用：${cusreport.msgcount*cusreport.msgvalue }元</td>
		     
		    </tr>
		        <tr class="topandbottom">
		        <td colspan="6" class="center">应收金额：合计人民币（大写）：${cusreport.totalmoney }         （小写）：￥${cusreport.totalin+cusreport.totalout+cusreport.msgcount*cusreport.msgvalue }元</td>
		        
		    </tr>
		    
			    <tr class="topandbottom">
			    <td colspan="6" class="center">实收金额：合计人民币（大写）： ${cusreport.totalmoney }         （小写）：￥${cusreport.totalin+cusreport.totalout+cusreport.msgcount*cusreport.msgvalue }元</td>
		    </tr>
		    
		    <tr class="topandbottom">
		        <td colspan="6" >备注:</td>
		    </tr>
		
		    <tr class="monthrule" >
		    	
		        <td class="center" colspan="2" >收款员：
					<c:forEach items="${paraList }" var="para">
			    		<c:if test="${para.pkey eq 'chargeman' }">
			    			${para.pvalue }
			    		</c:if>
		    		</c:forEach>
				</td>
		        <td class="center" colspan="2" >收款单位：（盖章）</td>
		        <td class="center" colspan="2" >收款日期：</td>
		        
		    </tr>
		    
		    <tr class="monthrule">
		        <td class="center" colspan="6">一式四联，白色联为财务结算联，蓝色联为存根联，红色联为客户结算联，黄色联为客户对账联</td>        
		    </tr>    
		</table>
		</div>
	
	</div>
</c:forEach>
</body>
</html>


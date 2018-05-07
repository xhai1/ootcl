<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org
/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-53" data-genuitec-path="${pageContext.request.contextPath}/WebRoot/WEB-INF/jsp/tlaccount.jsp"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <meta http-equiv="pragma" content="no-cache">
 <meta http-equiv="cache-control" content="no-cache">
 <meta http-equiv="expires" content="0">   
<title>计费总量月统计表</title>

<script src="${pageContext.request.contextPath}/js/laydate-v5.0.9.js"></script><link rel="stylesheet" href="http://localhost${pageContext.request.contextPath}/js/theme/default/laydate.css?v=5.0.9" id="layuicss-laydate">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/tlaccount.js"></script>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jdt.css" rel="stylesheet" type="text/css">
 
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap-select-1.12.4/js/bootstrap-select.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap-select-1.12.4/dist/css/bootstrap-select.css">
 <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
 <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script> --%>
 
<script type="text/javascript">
        $(window).on('load', function () {

            $('.selectpicker').selectpicker({
                'selectedText': 'cat'
				
            });

            // $('.selectpicker').selectpicker('hide');
        });
    </script>
<body>
<div class="rightstyletotalacc" data-genuitec-path="${pageContext.request.contextPath}/WebRoot/WEB-INF/jsp/RightTotalAccount.jsp">


     <div class="grayStripe">计费总量月统计表</div>
    
     <div class="reportAlign floatright">

            <form action="${pageContext.request.contextPath}/tlAccount/show.action" name="myform" id="myform" method="get">
                查询条件：
                
              

                 <label for="bs3Select" > 客户名称:</label>
                    <select id="bs3Select" name="condition1" >
                    	<option value=""> 请选择公司</option>
                        <c:forEach items="${customerVoList }" var="ct" >
					             		<option value="${ct.cusid }"
					             			<c:if test="${ct.cusid eq paginationUtil.getCondition1() }">
					             				selected="selected"
					             			</c:if>
					             		>
					               			${ct.cusname }
					                	</option>
					                </c:forEach>                     
                       
                    </select>   
                
                开始时间：<input id="J-xl" class="inputj" name="condition2" type="text" value="${paginationUtil.getCondition2()}">
                结束时间：<input id="J-xl1" class="inputj" name="condition3" type="text" value="${paginationUtil.getCondition3()}">                  
                <input name="bt" type="submit" class="report" value="查询" >
               <%--  <input name="dc" type="button" class="report" value="导出" onclick="exportExcel('${pageContext.request.contextPath}/ComputeMoney/exportStaCustemer.action')"> --%>
                <input id="PageContext" type="hidden" value="${pageContext.request.contextPath}">
            </form>
        </div>
		<!-- <script>
			if( $("select[name='condition2'] option").length > 1)
			$("select[name='condition2']")[0].selectedIndex = 1;  //选中
		</script> -->
	<div class="clearfloat"><!-- 显示条件  ： -->
	  		
	  		
		  	
		  	
		  	
	</div>
    
    <div>
    <span>月份${ empty paginationUtil.getCondition2() ? '' : '['.concat(paginationUtil.getCondition2()).concat(']') }${ empty paginationUtil.getCondition3() ? '' : '['.concat(paginationUtil.getCondition3()).concat(']') }
    (时长单位：小时，费用单位：元)</span>
    
    <div class="jdt" style="display:none">
		        <div class="container">
		            <div class="row">
		                <div class="col-md-12">
		                    <div class="loader">
		                        <div class="loading-1"></div>
		                        <div class="loading-2">正在加载...</div>
		                    </div>
		                </div>
		            </div>
		        </div>
</div>

    <table class="totalacctable">
    
    <tbody><tr>
        <th scope="col">客户名称</th>
        <th scope="col">月份</th>
        
        <th scope="col">呼入次数</th>
        <th scope="col">呼入时长</th>
        <th scope="col">呼入费用</th>
        <th scope="col">呼出次数</th>
        <th scope="col">呼出时长</th>
        <th scope="col">呼出费用</th>
        <th scope="col">操作</th>
    </tr>
    
    
       
<c:if test="${computeMoneys.size() eq 0 }">
    <tr>
        <td colspan="9">没有查询到记录</td>
 	</tr>
 </c:if> 	
 
<c:forEach var="computeMoney" items="${computeMoneys}" varStatus="idx">
 <tr>
        <td>${computeMoney.getCusName()}<%-- ${computeMoney.getClazz()} --%></td>    
	 	<td>${computeMoney.getType()}</td>
	 	 <td>
					       	${computeMoney.getCallInCount()}  
		 </td>
					        <td>
					        
					        <c:if test="${not empty computeMoney.getCallInTmlen() and computeMoney.getCallInTmlen() != '' and computeMoney.getCallInTmlen() != 0 }">   
			 
						 		 <fmt:formatNumber value=" ${computeMoney.getCallInTmlen()}" pattern="###,###,##0.0000" >
								 
								 </fmt:formatNumber>
							 
							 </c:if>	
					        </td>
					        <td>					        
					       		
					       		<c:if test="${not empty computeMoney.getCallInTotal() and computeMoney.getCallInTotal() != '' and computeMoney.getCallInTotal() != 0 }">   
			 
							 		 <fmt:formatNumber value=" ${computeMoney.getCallInTotal()}" pattern="###,###,##0.00" >
									 
									 </fmt:formatNumber>
							 
							 	</c:if>
					        	
					        </td>
					        <td>
					        
					        ${computeMoney.getCallOutCount()}  
					        </td>
					        <td>
					        	
					        	 <c:if test="${not empty computeMoney.getCallOutTmlen() and computeMoney.getCallOutTmlen() != '' and computeMoney.getCallOutTmlen() != 0 }">   
			 
							 		 <fmt:formatNumber value=" ${computeMoney.getCallOutTmlen()}" pattern="###,###,##0.0000" >
									 
									 </fmt:formatNumber>
							 
								 </c:if>
					        
					        
					        </td>
					        <td>
					        	
					        	<c:if test="${not empty computeMoney.getCallOutTotal() and computeMoney.getCallOutTotal() != '' and computeMoney.getCallOutTotal() != 0 }">   
			 
							 		 <fmt:formatNumber value=" ${computeMoney.getCallOutTotal()}" pattern="###,###,##0.00" >
									 
									 </fmt:formatNumber>
							 
							 	</c:if>
					        	
					        </td>
					        <td>
					        				<button><a  href="${pageContext.request.contextPath}/tlAccount/dtShow.action?condition1=${computeMoney.getClazz()}&month=${computeMoney.getType()}" target="_blank">查看详细</a></button>
					        </td>
 </tr>
</c:forEach>			  	      
    
    
       
</tbody></table>

    
    </div>
</div>

</body>
</html>
<script type="text/javascript">
//执行一个laydate实例
laydate.render({
  elem: '#J-xl' //指定元素
  ,theme: 'grid',
  type: 'month'
});
//执行一个laydate实例
laydate.render({
    elem: '#J-xl1' //指定元素
    ,theme: 'grid',
     type: 'month'
    ,max: -2
});
</script>








<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import=" com.zeng.ocs.po.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script src="${pageContext.request.contextPath}/js/laydate-v5.0.9.js"></script>

<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<!--<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />-->

<script>
 function exportExcel(){
  document.getElementById("myform").action="${pageContext.request.contextPath}/ComputeMoney/exportByCondition.action";
  myform.submit();
  
  }
</script>
</head>

<body>
<div class="rightstyletotalacc">

	
	
     <div class="grayStripe">计 费 总 量 表</div>
    
     <div class = "reportAlign floatright">
            <form action="${pageContext.request.contextPath}/ComputeMoney/SearchByCondition.action" name="myform" id="myform" method="get">
                查询条件：
               
                客户名称:<select name="condition1">
                <c:forEach items="${customers }" var="ct" >
             			<c:if test="${ct.cusid eq sessionScope.user.cusid }">
             		<option value="${ct.cusid }"
             				selected="selected"
             		>
               			${ct.cusname }
                	</option>
             			</c:if>
                </c:forEach>
           </select>
                产品类别：<%-- <input class="inputj" name="condition2" type="text"  value="${paginationUtil.condition2}" />  --%>               
                 <select class="inputj" name="condition2">
	                 <c:forEach items="${selectCusType }" var="ty" >
             		<option value="${ty.getTypetimeing()}"
             				<c:if test="${ty.getTypetimeing() eq paginationUtil.condition2 }">
             				selected="selected"
             				</c:if>
             		>
               			${ty.getTypetimeing() }
                	</option>                
	                </c:forEach>  
                </select>         
                开始时间：<input id="J-xl" class="inputj" name="condition3" type="text" value="${paginationUtil.condition3}${currentMonth}" />
                结束时间：<input  id="J-xl1" class="inputj" name="condition4" type="text" value="${paginationUtil.condition4}${currentMonth}"  />
                <input   class="inputj" name="condition5" type="hidden" value="${sessionScope.user.cusid}"  />                   
                <input name="bt" type="submit" class="report" value="查询"   />
                <input name="dc" type="button"  class="report" value="导出" onclick="exportExcel()" />
            </form>
        </div>
        
	  <div class="clearfloat">显示条件  ：
	  		
	  		${paginationUtil.condition1}${empty paginationUtil.condition2 ? '' : ','}
		  	${paginationUtil.condition2}${empty paginationUtil.condition3 ? '' : ','}
		  	${paginationUtil.condition3}${empty paginationUtil.condition4 ? '' : ','}
		  	${paginationUtil.condition4}
	</div>
    
    <div>
    
    <span>月份
    	${year1}${empty year1 ? '' : '-'}${month1}
					        							${fn:substring(startTime, 0, 7)}
													 		${empty startTime ? '' : '/'}
													 	${fn:substring(endTime, 0, 7)}
    
    (时长单位：小时，费用单位：元)</span>
    
    <table class="totalacctable">
    
    <tr>
        <th scope="col">客户名称</th>
        <th scope="col">产品类别</th>
        <th scope="col">业务类别</th>
        <th scope="col">呼入次数</th>
        <th scope="col">呼入时长</th>
        <th scope="col">呼入费用</th>
        <th scope="col">呼出次数</th>
        <th scope="col">呼出时长</th>
        <th scope="col">呼出费用</th>
        <th scope="col">统计期间</th>
    </tr>
    
 	<c:if test="${cusTypes.size() != 0 }">
    <tr>
        <td rowspan="${2*cusTypes.size() + 3}">${customer.cusname}</td>
            <c:forEach var="type" items="${cusTypes}" varStatus="idx">

	 	<td rowspan="2">${type.getTypetimeing()}</td>
       <c:forEach var="cm" items="${map[type.getTypetimeing()]}" varStatus="idx2">
       
		       <c:choose>  
			        <c:when  test="${cm.getClazz() == '非语音小计'}">  
				            <td >${empty cm.getClazz() ?"":cm.getClazz()}</td>
					        <td><c:if test="${cm.getCallInCount() != 0}">${cm.getCallInCount()}</c:if></td>
					        <td>
					        
					        <c:if test="${not empty cm.getCallInTmlen() and cm.getCallInTmlen()!= '' and cm.getCallInTmlen() != 0 }"> <fmt:formatNumber value="${cm.getCallInTmlen()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if>
					        </td>
					        <td>					        
					       		<c:if test="${not empty cm.getCallInTotal() and cm.getCallInTotal() != '' and cm.getCallInTotal() != 0 }"> <fmt:formatNumber value=" ${cm.getCallInTotal()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if>
					        	
					        </td>
					        <td><c:if test="${cm.getCallOutCount() != 0}">${cm.getCallOutCount()}</c:if></td>
					        <td>
					        <c:if test="${not empty cm.getCallOutTmlen() and cm.getCallOutTmlen() != '' and cm.getCallOutTmlen() != 0 }"> <fmt:formatNumber value=" ${cm.getCallOutTmlen()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if>
					        </td>
					        <td>
					        	<c:if test="${not empty cm.getCallOutTotal() and cm.getCallOutTotal() != '' and cm.getCallOutTotal() != 0 }"><fmt:formatNumber value="${cm.getCallOutTotal()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> </c:if>
					        	
					        </td>
					        <td>${year1}${empty year1 ? '' : '/'}${month1}${empty year1 ? '' : '/'}${day1}
					        							${startTime}
													 		${empty startTime ? '' : '/'}
													 	${endTime}
					        </td>
			   			 </tr>
			  	    </c:when>  
				</c:choose>  
        </c:forEach>
        
        	<c:forEach var="hm" items="${map[type.getTypetimeing()]}" varStatus="idx3">   
        	
        		<c:if test="${hm.getClazz() == '合计'}" >
				     <tr>
					        <td >合计</td>
					        <td><c:if test="${hm.getCallInCount() != 0}">${hm.getCallInCount()}</c:if></td>
					        <td>
					        <c:if test="${not empty hm.getCallInTmlen() and hm.getCallInTmlen() != '' and hm.getCallInTmlen() != 0 }"> <fmt:formatNumber value="${hm.getCallInTmlen()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if>
					        </td>
					        <td>
					       		<c:if test="${not empty hm.getCallInTotal() and hm.getCallInTotal() != '' and hm.getCallInTotal() != 0 }"><fmt:formatNumber value=" ${hm.getCallInTotal()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> </c:if>
					        	
					        </td>
					        <td><c:if test="${hm.getCallOutCount() != 0}">${hm.getCallOutCount()}</c:if></td>
					        <td>
					        <c:if test="${not empty hm.getCallOutTmlen() and hm.getCallOutTmlen() != '' and hm.getCallOutTmlen() != 0 }"> <fmt:formatNumber value=" ${hm.getCallOutTmlen()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if>
					        </td>
					        <td>
					        	<c:if test="${not empty hm.getCallOutTotal() and hm.getCallOutTotal() != '' and hm.getCallOutTotal() != 0 }"> <fmt:formatNumber value="${hm.getCallOutTotal()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if>
					        	
					        </td>
					        <td>${year1}${empty year1 ? '' : '/'}${month1}${empty year1 ? '' : '/'}${day1}
					        							${startTime}
													 		${empty startTime ? '' : '/'}
													 	${endTime}
					        </td>
				    </tr>
				</c:if>
    	 	</c:forEach>   
 </c:forEach>   
 
 
    <tr>
        <td >合计</td>
           <c:forEach var="tm" items="${cmList}" varStatus="idx">
           	 	<c:choose>  
				        <c:when  test="${tm.getClazz() == '非语音小计'}">  
					            <td >${empty tm.getClazz() ?"":tm.getClazz()}</td>
						        <td><c:if test="${tm.getCallInCount() != 0}">${tm.getCallInCount()}</c:if></td>
						        <td>
						        <c:if test="${not empty tm.getCallInTmlen() and tm.getCallInTmlen() != '' and tm.getCallInTmlen() != 0 }"> <fmt:formatNumber value="${tm.getCallInTmlen()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if>
						        </td>
						        <td>
						        		<c:if test="${not empty tm.getCallInTotal() and tm.getCallInTotal() != '' and tm.getCallInTotal() != 0 }"> <fmt:formatNumber value="${tm.getCallInTotal()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if>
						        		
						        </td>
						        <td><c:if test="${tm.getCallOutCount() != 0}">${tm.getCallOutCount()}</c:if></td>
						        <td>
						        <c:if test="${not empty tm.getCallOutTmlen() and tm.getCallOutTmlen() != '' and tm.getCallOutTmlen() != 0 }"> <fmt:formatNumber value="${tm.getCallOutTmlen()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if>
						        </td>
						        <td>
						        	<c:if test="${not empty tm.getCallOutTotal() and tm.getCallOutTotal() != '' and tm.getCallOutTotal() != 0 }"><fmt:formatNumber value="${tm.getCallOutTotal()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> </c:if>
						        	
						        </td>
						        <td>${year1}${empty year1 ? '' : '/'}${month1}${empty year1 ? '' : '/'}${day1}
						        						${startTime}
													 		${empty startTime ? '' : '/'}
													 	${endTime}
						        </td>
				   			 </tr>
				  	    </c:when>  
				</c:choose>  
        </c:forEach>
    </tr>

      <tr>
         <td colspan="2">短信</td>
       	 <td><c:if test="${sms.getCallInCount() != 0}">${sms.getCallInCount()}</c:if></td>
		 <td>
		 <c:if test="${not empty sms.getCallInTmlen() and sms.getCallInTmlen() != '' and sms.getCallInTmlen() != 0 }"> <fmt:formatNumber value="${sms.getCallInTmlen()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if>
		 </td>
		 <td>
		 		<c:if test="${not empty sms.getCallInTotal() and sms.getCallInTotal() != '' and sms.getCallInTotal() != 0 }"> <fmt:formatNumber value="${sms.getCallInTotal()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if>
		 		
		 </td>
		 <td><c:if test="${sms.getCallOutCount() != 0}">${sms.getCallOutCount()}</c:if></td>
		 <td>
		 <c:if test="${not empty sms.getCallOutTmlen() and sms.getCallOutTmlen() != '' and sms.getCallOutTmlen() != 0 }"> <fmt:formatNumber value="${sms.getCallOutTmlen()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if>
		 </td>
		 <td>
		 		<c:if test="${not empty sms.getCallOutTotal() and sms.getCallOutTotal() != '' and sms.getCallOutTotal() != 0 }"><fmt:formatNumber value="${sms.getCallOutTotal()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> </c:if>
		 		
		 </td>
		 <td>${year1}${empty year1 ? '' : '/'}${month1}${empty year1 ? '' : '/'}${day1}
		 		
		 												${startTime}
													 		${empty startTime ? '' : '/'}
													 	${endTime}
		 </td>
    </tr>
        <tr>
        <td colspan="2">总计</td>
      	 <td><c:if test="${totalacc.getCallInCount() != 0}">${totalacc.getCallInCount()}</c:if></td>
		 <td>
		 <c:if test="${not empty totalacc.getCallInTmlen() and totalacc.getCallInTmlen() != '' and totalacc.getCallInTmlen() != 0 }"> <fmt:formatNumber value=" ${totalacc.getCallInTmlen()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if>
		 </td>
		 <td>
		 	<c:if test="${not empty totalacc.getCallInTotal() and totalacc.getCallInTotal() != '' and totalacc.getCallInTotal()!= 0 }"><fmt:formatNumber value="${totalacc.getCallInTotal()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> </c:if>
		 	
		 </td>
		 <td><c:if test="${totalacc.getCallOutCount() != 0}">${totalacc.getCallOutCount()}</c:if></td>
		 <td>
		 <c:if test="${not empty totalacc.getCallOutTmlen() and totalacc.getCallOutTmlen() != '' and totalacc.getCallOutTmlen() != 0 }"> <fmt:formatNumber value=" ${totalacc.getCallOutTmlen()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></c:if>
		 </td>
		 <td>
		 	<c:if test="${not empty totalacc.getCallOutTotal() and totalacc.getCallOutTotal() != '' and totalacc.getCallOutTotal() != 0 }"><fmt:formatNumber value="${totalacc.getCallOutTotal()}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> </c:if>
		 	
		 </td>
		 <td>
		 	${year1}${empty year1 ? '' : '/'}${month1}${empty year1 ? '' : '/'}${day1}   
		 	${startTime}
				${empty startTime ? '' : '/'}
			${endTime}
		 </td>
    </tr>
    </c:if>
</table>

    
    </div>
</div>
<script type="text/javascript">
 //执行一个laydate实例
laydate.render({
  elem: '#J-xl' //指定元素
   ,theme: 'grid'
});
//执行一个laydate实例
laydate.render({
    elem: '#J-xl1' //指定元素
     ,theme: 'grid'
});
</script>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="pragma" content="no-cache">
 <meta http-equiv="cache-control" content="no-cache">
 <meta http-equiv="expires" content="0">   
<title>${paginationUtil.condition1 eq '1'? '['.concat('全部').concat(']') : '['.concat(customer.cusname).concat(']')}计费总量表</title>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/laydate.dev.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/laydate-v5.0.9.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/commoney.js"></script>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/jdt.css" rel="stylesheet" type="text/css" />
<!--<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />-->



</head>
<script>$(window).on('beforeunload',function(){
$.post('${pageContext.request.contextPath}/logout.action');
}
</script>
<body <%-- onunload="function(){window.location='${pageContext.request.contextPath}/logout.action';}" --%>>
<div class="rightstyletotalacc">


     <div class="grayStripe">计 费 总 量 表</div>
    
     <div class = "reportAlign floatright">
            <form action="${pageContext.request.contextPath}/ComputeMoney/SearchByCondition.action" name="myform" id="myform" method="get">
                查询条件：
                <%-- 客户名称:<input class="inputj" name="condition1" type="text" value="${paginationUtil.condition1}" /> --%>
                客户名称:<select name="condition1" onchange="dynamicGetCusType()">
                 <option value="1">全部</option>
                <c:forEach items="${customers }" var="ct" >
             		<option value="${ct.cusid }"
             			<c:if test="${ct.cusname eq customer.cusname }">
             				selected="selected"
             			</c:if>
             		>
               			${ct.cusname }
                	</option>
                </c:forEach>
           </select>
                产品类别：<%-- <input class="inputj" name="condition2" type="text"  value="${paginationUtil.condition2}" />  --%>
                 <select class="inputj" name="condition2" >
                 <option value="1">全部</option>
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
                开始时间：<input id="J-xl" class="inputj" name="condition3" type="text" value="${paginationUtil.condition3}${yesterday}" />
                结束时间：<input  id="J-xl1" class="inputj" name="condition4" type="text" value="${paginationUtil.condition4}${yesterday}"  />                  
                <input name="bt" type="button" class="report" value="查询" onclick="search()"  /><span id="varbutton">
                <input name="dc" type="button"  class="report" value="导出" onclick="exportExcel('${pageContext.request.contextPath}/ComputeMoney/exportStaCustemer.action')" />
                </span><input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />
            </form>
        </div>
		<!-- <script>
			if( $("select[name='condition2'] option").length > 1)
			$("select[name='condition2']")[0].selectedIndex = 1;  //选中
		</script> -->
		<div class="clearfloat">显示条件  ：
	  		
	  		${paginationUtil.condition1 eq '1'? '全部' : customer.cusname}${empty paginationUtil.condition2 ?'':','}
		  	${paginationUtil.condition2 eq '1'? '全部' : paginationUtil.condition2}${empty paginationUtil.condition3 ? '' : ','}
		  	${paginationUtil.condition3}${empty paginationUtil.condition4 ? '' : ','}
		  	${paginationUtil.condition4}
	</div>
    
    <div>
    <span>月份
    	<%-- ${year1}${empty year1 ? '' : '-'}${month1} --%>
    	${fn:substring(currentMonth, 0, 7)}
					        							${fn:substring(startTime, 0, 7)}
													 		${empty startTime ? '' : '/'}
													 	${fn:substring(endTime, 0, 7)}
    
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
    <c:if test="${empty showStaAll }">
    
    <c:if test="${cusTypes.size() == 0 }">
       <tr> <td colspan="10">没有查询到记录</td> </tr>
     </c:if>
     
 	<c:if test="${cusTypes.size() != 0 }">
    <tr>
        <td rowspan="${cuslen}">${customer.cusname}</td>
            <c:forEach var="type" items="${cusTypes}" varStatus="idx">

	 	<td rowspan="${map[type.getTypetimeing()].size() - 1}">${type.getTypetimeing()}</td>
       <c:forEach var="cm" items="${map[type.getTypetimeing()]}" varStatus="idx2">
       
		       <c:choose>  
			        <c:when  test="${idx2.index == 0}">  
				            <td >${empty cm.getClazz() ?"":cm.getClazz()}</td>
					        <td>
					       <%--  <c:if test="${cm.getCallInCount() != 0}">${cm.getCallInCount()}</c:if> --%>
					        	<c:if test="${not empty cm.getCallInCount() and cm.getCallInCount()!= ''  and cm.getCallInCount()!= 0}"> <fmt:formatNumber value="${cm.getCallInCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
					        
					        </td>
					        <td>
					      		  <c:if test="${not empty cm.getCallInTmlen() and cm.getCallInTmlen() != '' and cm.getCallInTmlen() != 0 }">   <fmt:formatNumber value=" ${cm.getCallInTmlen()}" pattern="###,###,##0.0000" ></fmt:formatNumber></c:if>
					       		<%--  <fmt:formatNumber value=" ${cm.getCallInTmlen()}"  ></fmt:formatNumber> --%>
					        </td>
					        <td>					        
					       		<c:if test="${not empty cm.getCallInTotal() and cm.getCallInTotal()!= '' and  cm.getCallInTotal()!= 0}"> <fmt:formatNumber value=" ${cm.getCallInTotal()}"  pattern="###,###,##0.00"></fmt:formatNumber>  </c:if>
					        	
					        </td>
					        <td>
					        <%-- <c:if test="${cm.getCallOutCount() != 0}">${cm.getCallOutCount()}</c:if> --%>
					        <c:if test="${not empty cm.getCallOutCount() and cm.getCallOutCount()!= ''  and cm.getCallOutCount()!= 0}"> <fmt:formatNumber value="${cm.getCallOutCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
					        </td>
					        <td>
					        	<c:if test="${not empty cm.getCallOutTmlen() and cm.getCallOutTmlen()!= ''  and cm.getCallOutTmlen()!= 0}"> <fmt:formatNumber value="${cm.getCallOutTmlen()}" pattern="###,###,##0.0000" ></fmt:formatNumber>  </c:if>
					        
					        
					        </td>
					        <td>
					        	<c:if test="${not empty cm.getCallOutTotal() and cm.getCallOutTotal() != ''  and cm.getCallOutTotal()!= 0 }">  <fmt:formatNumber value="${cm.getCallOutTotal()}" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
					        	<%-- <fmt:formatNumber value="${cm.getCallOutTotal()}"  ></fmt:formatNumber> --%>
					        </td>
					        <td><%-- ${year1}${empty year1 ? '' : '/'}${month1}${empty year1 ? '' : '/'}${day1} --%>
					        				${empty currentMonth ? '' : currentMonth}
					        							${startTime}
													 		${empty startTime ? '' : '/'}
													 	${endTime}
					        </td>
			   			 </tr>
			  	    </c:when>  
				    <c:otherwise>  
				    			 <c:choose>  
							    	  <c:when  test="${cm.getClazz() != '合计'}">  
									         <tr>
											    <td >${empty cm.getClazz() ?"":cm.getClazz()}</td>
										        <td>
										        <%-- <c:if test="${cm.getCallInCount() != 0}">${cm.getCallInCount()}</c:if> --%>
					        	<c:if test="${not empty cm.getCallInCount() and cm.getCallInCount()!= ''  and cm.getCallInCount()!= 0}"> <fmt:formatNumber value="${cm.getCallInCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
										        
										        </td>
										        <td>
										        	<c:if test="${not empty cm.getCallInTmlen() and cm.getCallInTmlen() != ''  and cm.getCallInTmlen()!= 0 }">  <fmt:formatNumber value=" ${cm.getCallInTmlen()}" pattern="###,###,##0.0000" ></fmt:formatNumber></c:if>
										        	<%-- <fmt:formatNumber value=" ${cm.getCallInTmlen()}"  ></fmt:formatNumber> --%>
										        </td>
										        <td>
										        	<c:if test="${not empty cm.getCallInTotal() and cm.getCallInTotal() != ''   and cm.getCallInTotal()!= 0 }">  <fmt:formatNumber value="${cm.getCallInTotal()}" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
										        	<%-- <fmt:formatNumber value="${cm.getCallInTotal()}"  ></fmt:formatNumber> --%>
										        </td>
										        <td>
										        <%-- <c:if test="${cm.getCallOutCount() != 0}">${cm.getCallOutCount()}</c:if> --%>
										        <c:if test="${not empty cm.getCallOutCount() and cm.getCallOutCount()!= ''  and cm.getCallOutCount()!= 0}"> <fmt:formatNumber value="${cm.getCallOutCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
					        					</td>
										        <td><c:if test="${cm.getCallOutTmlen() != 0}">${cm.getCallOutTmlen()}</c:if></td>
										        <td>  
										        	<c:if test="${not empty cm.getCallOutTotal() and cm.getCallOutTotal() != ''  and cm.getCallOutTotal()!= 0  }">  <fmt:formatNumber value="${cm.getCallOutTotal()}"  pattern="###,###,##0.00"></fmt:formatNumber></c:if>
										        	<%-- <fmt:formatNumber value="${cm.getCallOutTotal()}"  ></fmt:formatNumber> --%>
										        </td>
										        <td><%-- ${year1}${empty year1 ? '' : '/'}${month1}${empty year1 ? '' : '/'}${day1} --%>
										        		${empty currentMonth ? '' : currentMonth}
										        		${startTime}
													 		${empty startTime ? '' : '/'}
													 	${endTime}
										        </td>
										    </tr>
								     </c:when>  
						    	 </c:choose>  
				    </c:otherwise>  
				</c:choose>  
        </c:forEach>
        
        	<c:forEach var="hm" items="${map[type.getTypetimeing()]}" varStatus="idx3">   
        	
        		<c:if test="${hm.getClazz() == '合计'}" >
				     <tr>
					        <td colspan="2">合计</td>
					        <td>
					        <%-- <c:if test="${hm.getCallInCount() != 0}">${hm.getCallInCount()}</c:if> --%>
					        	<c:if test="${not empty hm.getCallInCount() and hm.getCallInCount()!= ''  and hm.getCallInCount()!= 0}"> <fmt:formatNumber value="${hm.getCallInCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
					        
					        </td>
					        <td>
					        	<c:if test="${not empty hm.getCallInTmlen() and hm.getCallInTmlen() != ''  and hm.getCallInTmlen()!= 0 }">  <fmt:formatNumber value=" ${hm.getCallInTmlen()}" pattern="###,###,##0.0000" ></fmt:formatNumber></c:if>
					        <%-- 	<fmt:formatNumber value=" ${hm.getCallInTmlen()}"  ></fmt:formatNumber> --%>
					        </td>
					        <td>
					       		<c:if test="${not empty hm.getCallInTotal() and hm.getCallInTotal() !=''  and hm.getCallInTotal()!= 0 }">  <fmt:formatNumber value=" ${hm.getCallInTotal()}" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
					        	<%-- <fmt:formatNumber value=" ${hm.getCallInTotal()}"  ></fmt:formatNumber> --%>
					        </td>
					        <td>
					        <%-- <c:if test="${hm.getCallOutCount() != 0}">${hm.getCallOutCount()}</c:if> --%>
					        <c:if test="${not empty hm.getCallOutCount() and hm.getCallOutCount()!= ''  and hm.getCallOutCount()!= 0}"> <fmt:formatNumber value="${hm.getCallOutCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
					        </td>
					        <td>
					        <c:if test="${not empty hm.getCallOutTmlen() and hm.getCallOutTmlen() !=''  and hm.getCallOutTmlen()!= 0 }">  <fmt:formatNumber value=" ${hm.getCallOutTmlen()}"  pattern="###,###,##0.0000"></fmt:formatNumber></c:if>
					        </td>
					        <td>
					        	<c:if test="${not empty hm.getCallOutTotal() and hm.getCallOutTotal() != ''  and hm.getCallOutTotal()!= 0 }">  <fmt:formatNumber value="${hm.getCallOutTotal()}" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
					        	<%-- <fmt:formatNumber value="${hm.getCallOutTotal()}"  ></fmt:formatNumber> --%>
					        </td>
					        <td><%-- ${year1}${empty year1 ? '' : '/'}${month1}${empty year1 ? '' : '/'}${day1} --%>
					        							${empty currentMonth ? '' : currentMonth}
					        							${startTime}
													 		${empty startTime ? '' : '/'}
													 	${endTime}
					        </td>
				    </tr>
				</c:if>
    	 	</c:forEach>   
 </c:forEach>   
 
 
    <tr>
        <td rowspan="${cmList.size()}">合计</td>
           <c:forEach var="tm" items="${cmList}" varStatus="idx">
           	 	<c:choose>  
				        <c:when  test="${idx.index == 0}">  
					            <td >${empty tm.getClazz() ?"":tm.getClazz()}</td>
						        <td>
						        	<%-- <c:if test="${tm.getCallInCount() != 0}">${tm.getCallInCount()}</c:if> --%>
					        	<c:if test="${not empty tm.getCallInCount() and tm.getCallInCount()!= ''  and tm.getCallInCount()!= 0}"> <fmt:formatNumber value="${tm.getCallInCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
						        </td>
						        <td>
						        	<c:if test="${not empty tm.getCallInTmlen() and tm.getCallInTmlen() !=''  and tm.getCallInTmlen()!= 0  }">  <fmt:formatNumber value="${tm.getCallInTmlen()}" pattern="###,###,##0.0000" ></fmt:formatNumber></c:if>
						        	<%-- <fmt:formatNumber value="${tm.getCallInTmlen()}"  ></fmt:formatNumber> --%>
						        </td>
						        <td>
						        		<c:if test="${not empty tm.getCallInTotal() and tm.getCallInTotal() != ''  and tm.getCallInTotal()!= 0 }">  <fmt:formatNumber value="${tm.getCallInTotal()}" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
						        		<%-- <fmt:formatNumber value="${tm.getCallInTotal()}"  ></fmt:formatNumber> --%>
						        </td>
						        <td>
						        <%-- <c:if test="${tm.getCallOutCount() != 0}">${tm.getCallOutCount()}</c:if> --%>
						        <c:if test="${not empty tm.getCallOutCount() and tm.getCallOutCount()!= ''  and tm.getCallOutCount()!= 0}"> <fmt:formatNumber value="${tm.getCallOutCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
					        
						        </td>
						        <td>
						       		 <c:if test="${not empty tm.getCallOutTmlen() and tm.getCallOutTmlen() != ''  and tm.getCallOutTmlen()!= 0 }">  <fmt:formatNumber value=" ${tm.getCallOutTmlen()}" pattern="###,###,##0.0000" ></fmt:formatNumber></c:if>
						        </td>
						        <td>
						        	<c:if test="${not empty tm.getCallOutTotal() and tm.getCallOutTotal() != ''  and  tm.getCallOutTotal()!= 0 }">  <fmt:formatNumber value="${tm.getCallOutTotal()}" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
						        	<%-- <fmt:formatNumber value="${tm.getCallOutTotal()}"  ></fmt:formatNumber> --%>
						        </td>
						        <td><%-- ${year1}${empty year1 ? '' : '/'}${month1}${empty year1 ? '' : '/'}${day1} --%>
						        						${empty currentMonth ? '' : currentMonth}
						        						${startTime}
													 		${empty startTime ? '' : '/'}
													 	${endTime}
						        </td>
				   			 </tr>
				  	    </c:when>  
					    <c:otherwise>  
					         <tr>
							    <td >${empty tm.getClazz() ?"":tm.getClazz()}</td>
						        <td>
						       <%--  <c:if test="${tm.getCallInCount() != 0}">${tm.getCallInCount()}</c:if> --%>
						        	<c:if test="${not empty tm.getCallInCount() and tm.getCallInCount()!= ''  and tm.getCallInCount()!= 0}"> <fmt:formatNumber value="${tm.getCallInCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
						        
						        </td>
						        <td>
						        	<c:if test="${not empty tm.getCallInTmlen() and tm.getCallInTmlen() != '' and tm.getCallInTmlen() != 0 }">  <fmt:formatNumber value="${tm.getCallInTmlen()}" pattern="###,###,##0.0000" ></fmt:formatNumber></c:if>
						        	<%-- <fmt:formatNumber value="${tm.getCallInTmlen()}"  ></fmt:formatNumber> --%>
						        </td>
						        <td>
						        	<c:if test="${not empty tm.getCallInTotal() and tm.getCallInTotal() != '' and tm.getCallInTotal() != 0 }">  <fmt:formatNumber value="${tm.getCallInTotal()}" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
						        	<%-- <fmt:formatNumber value="${tm.getCallInTotal()}"  ></fmt:formatNumber> --%>
						        </td>
						        <td>
						        <%-- <c:if test="${tm.getCallOutCount() != 0}">${tm.getCallOutCount()}</c:if> --%>
						        <c:if test="${not empty tm.getCallOutCount() and tm.getCallOutCount()!= ''  and tm.getCallOutCount()!= 0}"> <fmt:formatNumber value="${tm.getCallOutCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
					        
						        </td>
						        <td>
						        	<c:if test="${not empty tm.getCallOutTmlen() and tm.getCallOutTmlen() != ''   and tm.getCallOutTmlen()!= 0 }">  <fmt:formatNumber value=" ${tm.getCallOutTmlen()}" pattern="###,###,##0.0000" ></fmt:formatNumber></c:if>
						       </td>
						        <td>
						        	<c:if test="${not empty tm.getCallOutTotal() and tm.getCallOutTotal() != ''  and tm.getCallOutTotal()!= 0 }">  <fmt:formatNumber value="${tm.getCallOutTotal()}" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
						        	<%-- <fmt:formatNumber value="${tm.getCallOutTotal()}"  ></fmt:formatNumber> --%>
						        </td>
						        <td><%-- ${year1}${empty year1 ? '' : '/'}${month1}${empty year1 ? '' : '/'}${day1} --%>
						        	${empty currentMonth ? '' : currentMonth}
						        	${startTime}
													 		${empty startTime ? '' : '/'}
									${endTime}
						        </td>
						    </tr>
					    </c:otherwise>  
				</c:choose>  
           
           
        </c:forEach>
    </tr>

      <tr>
         <td colspan="2">短信</td>
       	 <td>
       	 <c:if test="${not empty sms.getCallInCount() and sms.getCallInCount()!= ''  and sms.getCallInCount()!= 0}"> <fmt:formatNumber value="${sms.getCallInCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
       	 <%-- <c:if test="${sms.getCallInCount() != 0}">${sms.getCallInCount()}</c:if> --%>
       	 </td>
		 <td><c:if test="${sms.getCallInTmlen() != 0}">${sms.getCallInTmlen()}</c:if></td>
		 <td>
		 		<c:if test="${not empty sms.getCallInTotal() and sms.getCallInTotal() != ''  and sms.getCallInTotal()!= 0 }"> <fmt:formatNumber value="${sms.getCallInTotal()}" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
		 		<%-- <fmt:formatNumber value="${sms.getCallInTotal()}"  ></fmt:formatNumber> --%>
		 </td>
		 <td>
		 <%-- <c:if test="${sms.getCallOutCount() != 0}">${sms.getCallOutCount()}</c:if> --%>
		 <c:if test="${not empty sms.getCallOutCount() and sms.getCallOutCount()!= ''  and sms.getCallOutCount()!= 0}"> <fmt:formatNumber value="${sms.getCallOutCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
					        
		 </td>
		 <td><c:if test="${sms.getCallOutTmlen() != 0}">${sms.getCallOutTmlen()}</c:if></td>
		 <td>
		 		<c:if test="${not empty sms.getCallOutTotal() and sms.getCallOutTotal() != ''   and sms.getCallOutTotal()!= 0 }">  <fmt:formatNumber value="${sms.getCallOutTotal()}" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
		 		<%-- <fmt:formatNumber value="${sms.getCallOutTotal()}"  ></fmt:formatNumber> --%>
		 </td>
		 <td><%-- ${year1}${empty year1 ? '' : '/'}${month1}${empty year1 ? '' : '/'}${day1} --%>
		 												${empty currentMonth ? '' : currentMonth}
		 		
		 												${startTime}
													 		${empty startTime ? '' : '/'}
													 	${endTime}
		 </td>
    </tr>
        <tr  style="font-size:16px;font-weight:bold">
        <td colspan="2"  >总计</td>
      	 <td>
      	<%--  <c:if test="${totalacc.getCallInCount() != 0}">${totalacc.getCallInCount()}</c:if> --%>
      	 <c:if test="${not empty totalacc.getCallInCount() and totalacc.getCallInCount()!= ''  and totalacc.getCallInCount()!= 0}"> <fmt:formatNumber value="${totalacc.getCallInCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
      	 
      	 </td>
		 <td>
		 	<c:if test="${not empty totalacc.getCallInTmlen() and totalacc.getCallInTmlen() != ''  and totalacc.getCallInTmlen()!= 0 }"> <fmt:formatNumber value="${totalacc.getCallInTmlen()}" pattern="###,###,##0.0000" ></fmt:formatNumber></c:if>
		 	<%-- <fmt:formatNumber value="${totalacc.getCallInTmlen()}"  ></fmt:formatNumber> --%>
		 </td>
		 <td>
		 	<c:if test="${not empty totalacc.getCallInTotal() and totalacc.getCallInTotal() != '' and totalacc.getCallInTotal() != 0 }">  <fmt:formatNumber value="${totalacc.getCallInTotal()}" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
		 	<%-- <fmt:formatNumber value="${totalacc.getCallInTotal()}"  ></fmt:formatNumber> --%>
		 </td>
		 <td>
		 <%-- <c:if test="${totalacc.getCallOutCount() != 0}">${totalacc.getCallOutCount()}</c:if> --%>
		 <c:if test="${not empty totalacc.getCallOutCount() and totalacc.getCallOutCount()!= ''  and totalacc.getCallOutCount()!= 0}"> <fmt:formatNumber value="${totalacc.getCallOutCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
					        
		 </td>
		 <td>
		 <c:if test="${not empty totalacc.getCallOutTmlen() and totalacc.getCallOutTmlen() != ''  and totalacc.getCallOutTmlen()!= 0 }">  <fmt:formatNumber value="${totalacc.getCallOutTmlen()}" pattern="###,###,##0.0000" ></fmt:formatNumber></c:if>
		 </td>
		 <td>
		 	<c:if test="${not empty totalacc.getCallOutTotal() and totalacc.getCallOutTotal() != ''  and totalacc.getCallOutTotal()!= 0 }"> <fmt:formatNumber value="${totalacc.getCallOutTotal()}" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
		 	
		 </td>
		 <td>
		 	<%-- ${year1}${empty year1 ? '' : '/'}${month1}${empty year1 ? '' : '/'}${day1} --%>   
		 	${empty currentMonth ? '' : currentMonth}
		 	${startTime}
				${empty startTime ? '' : '/'}
			${endTime}
		 </td>
    </tr>
    </c:if>   
     </c:if>   
    
    
    <c:if test="${ not empty showStaAll }">
  <!--  统计所有公司 -->
   
    <tr>
        <td rowspan="${computeMoneys.size() }">总计</td>
      	 <td rowspan="${computeMoneys.size()-2 }">合计</td>
      	 <c:forEach var="cm" items="${computeMoneys}" varStatus="idx2">
       
		       <c:choose>  
			        <c:when  test="${idx2.index == 0}">  
					 <td>
					 	${cm.getClazz() }
					 </td>
					 <td>
					 	<%-- ${cm.getCallInCount() } --%>
					    <c:if test="${not empty cm.getCallInCount() and cm.getCallInCount()!= ''  and cm.getCallInCount()!= 0}"> <fmt:formatNumber value="${cm.getCallInCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
					 	
					 </td>
					 <td>
					 <c:if test="${not empty cm.getCallInTmlen() and cm.getCallInTmlen() != ''  and cm.getCallInTmlen()!= 0 }"> <fmt:formatNumber value="${cm.getCallInTmlen() }"  pattern="###,###,##0.0000"></fmt:formatNumber></c:if>
					 </td>
					 <td>
					 
					 <c:if test="${not empty cm.getCallInTotal()  and cm.getCallInTotal()  != ''  and cm.getCallInTotal() != 0 }"> <fmt:formatNumber value="${cm.getCallInTotal() }" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
					 </td>
					 <td> 	
					 	<%-- ${cm.getCallOutCount() } --%>
					 	<c:if test="${not empty cm.getCallOutCount() and cm.getCallOutCount()!= ''  and cm.getCallOutCount()!= 0}"> <fmt:formatNumber value="${cm.getCallOutCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
					        
					 </td>
					 <td>
					 <c:if test="${not empty cm.getCallOutTmlen() and cm.getCallOutTmlen() != ''  and cm.getCallOutTmlen()!= 0 }"> <fmt:formatNumber value="${cm.getCallOutTmlen() }	" pattern="###,###,##0.0000" ></fmt:formatNumber></c:if>	 	
					 </td>
			         <td>		
			         <c:if test="${not empty cm.getCallOutTotal() and cm.getCallOutTotal() != ''  and cm.getCallOutTotal()!= 0 }"> <fmt:formatNumber value="${cm.getCallOutTotal() }" pattern="###,###,##0.00"></fmt:formatNumber></c:if> 	
					 </td>
			         <td>	${startTime } / ${endTime }	 	
					 </td>
			    </tr>
    			</c:when>
   			
   			 <c:otherwise>  
		    	<c:choose>  
						<c:when  test="${cm.getClazz() ne '短信' and cm.getClazz() ne '总计'}">  
							    <tr>
							        
									 <td>
									 	${cm.getClazz() }
									 </td>
									 <td>
									 	<%-- ${cm.getCallInCount() } --%>
					        			<c:if test="${not empty cm.getCallInCount() and cm.getCallInCount()!= ''  and cm.getCallInCount()!= 0}"> <fmt:formatNumber value="${cm.getCallInCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
									 	
									 </td>
									 <td>
									 <c:if test="${not empty cm.getCallInTmlen() and cm.getCallInTmlen() != ''  and cm.getCallInTmlen() != 0 }"> <fmt:formatNumber value="${cm.getCallInTmlen() }" pattern="###,###,##0.0000" ></fmt:formatNumber></c:if>
									 </td>
									 <td>
										 
										 <c:if test="${not empty cm.getCallInTotal() and cm.getCallInTotal() != ''  and cm.getCallInTotal()!= 0 }"> <fmt:formatNumber value="${cm.getCallInTotal() }"  pattern="###,###,##0.00"></fmt:formatNumber></c:if>
									 </td>
									 <td> 	
									 	<%-- ${cm.getCallOutCount() } --%>
									 	<c:if test="${not empty cm.getCallOutCount() and cm.getCallOutCount()!= ''  and cm.getCallOutCount()!= 0}"> <fmt:formatNumber value="${cm.getCallOutCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
					        
									 </td>
									 <td>
									 <c:if test="${not empty cm.getCallOutTmlen() and cm.getCallOutTmlen() != ''  and cm.getCallOutTmlen()!= 0 }"> <fmt:formatNumber value="${cm.getCallOutTmlen() }	"  pattern="###,###,##0.0000"></fmt:formatNumber></c:if>	 	
									 </td>
							         <td>	
							         <c:if test="${not empty cm.getCallOutTotal() and cm.getCallOutTotal() != ''  and cm.getCallOutTotal()!= 0 }"> <fmt:formatNumber value="${cm.getCallOutTotal() }	"  pattern="###,###,##0.00"></fmt:formatNumber></c:if> 	
									 </td>
							         <td>	${startTime } / ${endTime }	 	
									 </td>
							    </tr>
				   		 </c:when>
			   		  </c:choose> 
    		 </c:otherwise> 
    	  </c:choose> 
     </c:forEach>
    <tr>
         
      	 <td colspan="2">短信</td>
      	 <c:forEach var="cm" items="${computeMoneys}" varStatus="idx2">
      	 <c:choose>  
				<c:when  test="${cm.getClazz() eq '短信' }">  
		 					<td>
							 	<%-- ${cm.getCallInCount() } --%>
							    <c:if test="${not empty cm.getCallInCount() and cm.getCallInCount()!= ''  and cm.getCallInCount()!= 0}"> <fmt:formatNumber value="${cm.getCallInCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
							 	
							 </td>
							 <td>
							 <c:if test="${not empty cm.getCallInTmlen() and cm.getCallInTmlen() != ''  and cm.getCallInTmlen() != 0 }"> <fmt:formatNumber value="${cm.getCallInTmlen() }"  pattern="###,###,##0.0000"></fmt:formatNumber></c:if>
							 </td>
							 <td>
								
								 <c:if test="${not empty cm.getCallInTotal() and cm.getCallInTotal() != ''  and cm.getCallInTotal()!= 0 }"> <fmt:formatNumber value=" ${cm.getCallInTotal() }"  pattern="###,###,##0.00"></fmt:formatNumber></c:if>
							 </td>
							 <td> 	
							 	<%-- ${cm.getCallOutCount() } --%>
							 	<c:if test="${not empty cm.getCallOutCount() and cm.getCallOutCount()!= ''  and cm.getCallOutCount()!= 0}"> <fmt:formatNumber value="${cm.getCallOutCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
					        
							 </td>
							 <td>
							 <c:if test="${not empty cm.getCallOutTmlen() and cm.getCallOutTmlen() != ''  and cm.getCallOutTmlen()!= 0 }"> <fmt:formatNumber value="${cm.getCallOutTmlen() }"  pattern="###,###,##0.0000"></fmt:formatNumber></c:if>		 	
							 </td>
					         <td>		
					         <c:if test="${not empty cm.getCallOutTotal() and cm.getCallOutTotal() != ''  and cm.getCallOutTotal()!= 0 }"> <fmt:formatNumber value="${cm.getCallOutTotal() }	 "  pattern="###,###,##0.00"></fmt:formatNumber></c:if>
							 </td>
					         <td>	${startTime } / ${endTime }	 	
							 </td>
          		 </c:when>
		    </c:choose>  
          </c:forEach>
    </tr>
    <tr id="total">
       <td colspan="2" style="font-size:16px;font-weight:bold">总计</td>
      	   <c:forEach var="cm" items="${computeMoneys}" varStatus="idx2">
      	 <c:choose>  
				<c:when  test="${cm.getClazz() eq '总计' }">  
		 					<td style="font-size:16px;font-weight:bold">
							 	<%-- ${cm.getCallInCount() } --%>
							   <c:if test="${not empty cm.getCallInCount() and cm.getCallInCount()!= ''  and cm.getCallInCount()!= 0}"> <fmt:formatNumber value="${cm.getCallInCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
							 	
							 </td>
							 <td style="font-size:16px;font-weight:bold">
							 <c:if test="${not empty cm.getCallInTmlen() and cm.getCallInTmlen() != ''  and cm.getCallInTmlen()!= 0 }"> <fmt:formatNumber value="${cm.getCallInTmlen() }" pattern="###,###,##0.0000" ></fmt:formatNumber></c:if>
							
							 </td>
							 <td style="font-size:16px;font-weight:bold">
								
								 <c:if test="${not empty cm.getCallInTotal() and cm.getCallInTotal() != ''  and cm.getCallInTotal()!= 0 }"> <fmt:formatNumber value=" ${cm.getCallInTotal() }" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
							 
							 </td>
							 <td style="font-size:16px;font-weight:bold"> 	
							 <%-- 	${cm.getCallOutCount() } --%>
							  <c:if test="${not empty cm.getCallOutCount() and cm.getCallOutCount()!= ''  and cm.getCallOutCount()!= 0}"> <fmt:formatNumber value="${cm.getCallOutCount()}" pattern="###,###,##0" ></fmt:formatNumber>  </c:if>
							 	
							 </td>
							 <td style="font-size:16px;font-weight:bold">	
							 <c:if test="${not empty cm.getCallOutTmlen() and cm.getCallOutTmlen() != ''  and cm.getCallOutTmlen()!= 0 }"> <fmt:formatNumber value="${cm.getCallOutTmlen() }" pattern="###,###,##0.0000" ></fmt:formatNumber></c:if>	 	
							
							 </td>
					         <td style="font-size:16px;font-weight:bold">
					         <c:if test="${not empty cm.getCallOutTotal() and cm.getCallOutTotal() != ''  and cm.getCallOutTotal()!= 0 }"> <fmt:formatNumber value="${cm.getCallOutTotal() }" pattern="###,###,##0.00" ></fmt:formatNumber></c:if>
							
							 </td>
					         <td style="font-size:16px;font-weight:bold">	
					         ${startTime } / ${endTime }	 	
					        
							 </td>
          		 </c:when>
		    </c:choose>  
          </c:forEach>
          
    </tr>   
      <!--  统计所有公司结束 -->
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


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<!-- <link href="../../css/xstyle.css" rel="stylesheet" type="text/css" /> -->
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/laydate-v5.0.9.js"></script>

<link href="${pageContext.request.contextPath}/css/jdt.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/uchgorder.js"></script>

<script>
	function toPage(){
		var page = $("input[name='number']").val();
		window.location.href="${pageContext.request.contextPath}/changeOrder/showChangeOrder.action?page="+page+"&cusname=${cusname}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&typeid=${typeid}&type=${type}";
	}
	function toExport(root){
		if(root==0){
			if(confirm("只能导出当前页的内容！")){
				window.location.href="${pageContext.request.contextPath}/changeOrder/exportChangeOrder.action?cusname=${cusname}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&typeid=${typeid}&type=${type}&page="+${pageBean.page};
			}
		}else{
			if(confirm("只能导出前30000条的内容！如果需要更多的内容请分次导出！")){
				window.location.href="${pageContext.request.contextPath}/changeOrder/exportChangeOrder.action?cusname=${cusname}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&typeid=${typeid}&type=${type}&page="+${pageBean.page};
			}
		}
	}
	
/* 
 * 动态获取某公司产品大类
 */
function chgCustype(){
	var cusid = $("select[name='cusid']").val(); 	
//	alert(path);
	
	$.ajax({  
                    type : "POST",  //提交方式  
                    url : "${pageContext.request.contextPath}/addOrder/dGetTypeID.action",//路径  
                    data : {  
                        "cusid" : cusid  
                    },//数据，这里使用的是Json格式进行传输  
                    success : function(response) {//返回数据根据结果进行相应的处理  
                        if ( response != null ) {  
                           $("select[name='typeid']").empty();
                           $("select[name='typeid']").append(response); 
                           /* if( $("select[name='condition2'] option").length > 1)
                          $("select[name='condition2']")[0].selectedIndex = 1; */
                        } else {  
                            $("select[name='typeid']").append("<option selected>此公司无产品大类记录</option>"); 
                        }  
                    }  
       });  
	
}	
</script>
</head>

<body>
<div class="rightstyle">
<div class="grayStripe">改单查询</div>

        <div class = "reportAlign floatright">
            <form name="uChgOrder" action="${pageContext.request.contextPath}/changeOrder/showChangeOrder.action" method="get">
               	    查询条件：
               	    <c:choose>
        	     	<c:when test="${sessionScope.user.isroot ne 0 }">
        	     		<%--  公司名称：<input class="inputj" name="cusname" type="text" value="${cusname }"/> --%>
	        	     	   公司名称:<select name="cusid" onchange="chgCustype()">
					                <option value="cusidall" 
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
        	     	</c:when>
        	     	<c:otherwise>
        	     		<input name="cusid" value="${cusid }" type="hidden"/>
        	     	</c:otherwise>
			     </c:choose>
             	    
             	   产品大类:<select name="typeid"  class="inputj">
					<option value="all" 
				    	<c:if test="${typeid eq '' }">
								selected="selected"
							</c:if>
				    >
				    	请选择产品大类
				    </option>
					<c:forEach items="${cusTypeList }" var="ct" >
						
						<option value="${ct.typeid }"
						<c:if test="${ct.typeid eq typeid }">
								selected="selected"
							</c:if>
						>
									${ct.typetimeing }
						</option>
					
					</c:forEach>
				</select>   
             	    
			               开始日期：<input id="J-xl" class="inputj" name="begintime" type="text" value="${begintime }"/>
			                结束日期：<input id="J-xl1" class="inputj" name="endtime" type="text" value="${endtime }"/>
			               
				   
                <input name="bt" type="button" onclick="uChgOrderSearch()" class="report" value="查询" />
                <c:choose>
                	<c:when test="${sessionScope.user.isroot eq 0 }">
                		<input name="dc" type="button"  class="report" value="导出" onclick="toExport(0)"/>
                	</c:when>
                	<c:otherwise>
                		<input name="dc" type="button"  class="report" value="导出" onclick="toExport(null)"/>
                	</c:otherwise>
                </c:choose>
            </form>
        </div>

<div class="clearfloat">显示条件：</div>


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

<table class="righttable" cellspacing="0px" cellpadding="2px">
<thead><tr><th>序号</th><th>公司名称</th><th>月份</th><th>受理日期</th><th>受理时间</th><th>来电备注</th><th>业务技能</th><th>受理编号</th><th>CASE单号</th><th>产品大类ID</th><th>产品大类</th><th>产品小类</th><th>需求大类</th><th>需求小类</th></tr></thead>
	
	<tbody>
		<!-- 
		<tr class="righttabletr"><td >1</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>改单</td><td>彩电</td><td>1710011947021094</td><td>CS1710010827000675</td><td>4960</td><td >东芝彩电</td><td>东芝液晶</td><td>座装</td><td>要求座装</td></tr>
		<tr class="righttabletr2"><td>2</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>改单</td><td>彩电</td><td>1710011947021094</td><td>CS1710010827000675</td><td>4960</td><td >东芝彩电</td><td>东芝液晶</td><td>座装</td><td>要求座装</td></tr>
		-->
		
		     <c:if test="${pageBean.list.size() eq 0 }">
				<tr style="text-align:center;height:100px;"><td colspan="14">没有查询到记录</td></tr>
			</c:if>	
		<c:forEach items="${pageBean.list }" var="addOrder" varStatus="ids">
			<tr class="righttabletr2"><td>${ids.index+1 }</td><td>${addOrder.cusname }</td><td>${addOrder.tmonth }</td>
			<td>
			${fn:substring(addOrder.accdate,0,11)}
			</td>
			
			<td>${addOrder.acc }</td><td>${addOrder.mode }</td><td>${addOrder.businesstype }</td>
			
			<td>
				<c:choose> 
				    <c:when test="${sessionScope.user.isroot eq 0}"> 
				    
				    	<c:set var="acceptidlen" scope="session" value="${fn:length(addOrder.acceptid)}"/>
				   		  <c:out value="${fn:substring(addOrder.acceptid, 0, acceptidlen-9)}" /> 
				   		  *****
				   		  <c:out value="${fn:substring(addOrder.acceptid, acceptidlen-4,acceptidlen )}" />
				    </c:when> 
				    <c:otherwise> 
				    	  <c:out value="${addOrder.acceptid }" /> 
				    </c:otherwise> 
			   </c:choose> 	
				</td>
				
				<td>
				<c:choose> 
				    <c:when test="${sessionScope.user.isroot eq 0}"> 
				    
				    	<c:set var="caseorderlen" scope="session" value="${fn:length(addOrder.caseorder)}"/>
				   		  <c:out value="${fn:substring(addOrder.caseorder, 0, caseorderlen-9)}" /> 
				   		  *****
				   		  <c:out value="${fn:substring(addOrder.caseorder, caseorderlen-4,caseorderlen )}" />
				    </c:when> 
				    <c:otherwise> 
				    	  <c:out value="${addOrder.caseorder }" /> 
				    </c:otherwise> 
			   </c:choose> 	
				</td>
			
			<td>${addOrder.typeid }</td><td >${addOrder.type }</td><td>${addOrder.dttype }</td><td>${addOrder.typedemand }</td><td>${addOrder.dtdemand }</td></tr>
		</c:forEach>
	</tbody>
	
</table>
<div class="tbfooter">
<div class="floatleft"><span>记录条数：${pageBean.totalCount }&nbsp;总页数：${pageBean.totalPage }&nbsp;当前页：${pageBean.page }</span></div>
            <div class="floatright">
                <form action="" method="get" class="formfloat" >
                    到第&nbsp;<input name="number"  value="${pageBean.page }" type="text" class="number" />&nbsp;页
                    <input name="GO" type="button" value="GO" onclick="toPage()"/>
                </form>
            </div>
<div class="floatright">
            	<c:choose>
            		<c:when test="${pageBean.page eq 1 }">
            			<span class="disabled"><< 首页 </span>
            		</c:when>
            		<c:otherwise>
            			<span class="disabled"><a style="cursor:pointer" onclick="uChgOrderToNextPage('${pageContext.request.contextPath}/changeOrder/showChangeOrder.action?page=1&cusname=${cusname}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&typeid=${typeid}&type=${type}')" href=""><< 首页</a> </span>
            		</c:otherwise>
            	</c:choose>
                
                <c:choose>
                	<c:when test="${pageBean.page>1 }">
                		<span class="disabled"><a style="cursor:pointer" onclick="uChgOrderToNextPage('${pageContext.request.contextPath}/changeOrder/showChangeOrder.action?page=${pageBean.page-1}&cusname=${cusname}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&typeid=${typeid}&type=${type}')" >< 上一页</a> </span>
                	</c:when>
                	<c:otherwise>
                		<span class="disabled">< 上一页 </span>
                	</c:otherwise>
                </c:choose>
                
                <c:choose>
                	<c:when test="${pageBean.page<pageBean.totalPage }">
                		<span class="disabled"> <a style="cursor:pointer" onclick="uChgOrderToNextPage('${pageContext.request.contextPath}/changeOrder/showChangeOrder.action?page=${pageBean.page+1}&cusname=${cusname}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&typeid=${typeid}&type=${type}')">下一页 ></a> </span>
                	</c:when>
                	<c:otherwise>
                		<span class="disabled"> 下一页 > </span>
                	</c:otherwise>
                </c:choose>
                
                <c:choose>
                	<c:when test="${pageBean.page eq pageBean.totalPage}">
                		<span class="disabled"> 尾页>> </span>
                	</c:when>
                	<c:otherwise>
                		<span class="disabled"> <a style="cursor:pointer" onclick="uChgOrderToNextPage('${pageContext.request.contextPath}/changeOrder/showChangeOrder.action?page=${pageBean.totalPage}&cusname=${cusname}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&typeid=${typeid}&type=${type}')" >尾页>></a> </span>
                	</c:otherwise>
                </c:choose>
                <!-- <a href="#" class="current"> 1</a> 
                 <span href="#" class="current crc" >2</span> 
                <a href="#" class="current"> 3 </a>
                 <a href="#" class="current">4 </a>
                <a href="#" class="current"> 5 </a>
                <a href="#" class="current"> 6 </a>
                 <a href="#" class="current">7 </a>
                <a href="#" class="current"> 8 </a>
                 ... 
                <a href="#" class="current"> 9 </a>
                 <a href="#" class="current">10</a> -->
                 
                                  
            </div>
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

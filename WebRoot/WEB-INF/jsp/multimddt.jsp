<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/css/jdt.css" rel="stylesheet" type="text/css" />

<!--<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />-->
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/laydate-v5.0.9.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath}/js/multidt.js"></script>

<script>
	function toPage(){
		var page = $("input[name='number']").val();
		window.location.href="${pageContext.request.contextPath}/multiVoice/showMultiVoice.action?page="+page+"&cusname=${cusname}&callingno=${callingno}&callno=${callno}&calledno=${calledno}&ntype=${ntype}&cusid=${cusid}";
	}
	function toExport(root){
		if(root==0){
			if(confirm("只能导出当前页的内容！")){
				window.location.href="${pageContext.request.contextPath}/multiVoice/exportMultiVoice.action?cusname=${cusname}&callingno=${callingno}&callno=${callno}&calledno=${calledno}&ntype=${ntype}&cusid=${cusid}&page="+${pageBean.page}+"&begintime=${begintime}&endtime=${endtime}";
			}
		}else{
			if(confirm("只能导出前30000条的内容！如果需要更多的内容请分次导出！")){
				window.location.href="${pageContext.request.contextPath}/multiVoice/exportMultiVoice.action?cusname=${cusname}&callingno=${callingno}&callno=${callno}&calledno=${calledno}&ntype=${ntype}&cusid=${cusid}&page="+${pageBean.page}+"&begintime=${begintime}&endtime=${endtime}";
			}
		}
	}
</script>
</head>

<body>
<div class="rightstyle">
<div class="grayStripe">多媒体语音明细</div>

        <div class = "reportAlign floatright">
        			
                    <div class="">
                        <form name="dtMultiVoice" action="${pageContext.request.contextPath}/multiVoice/showMultiVoice.action" method="get">
			          	  查询条件：
			          	  
		             <c:choose>
        	     	<c:when test="${sessionScope.user.isroot ne 0 }">
        	     		<%--  公司名称：<input class="inputj" name="cusname" type="text" value="${cusname }"/> --%>
	        	     	   公司名称:<select name="cusid">
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
        	     	</c:when>
        	     	<c:otherwise>
        	     		<input name="cusid" value="${cusid }" type="hidden"/>
        	     	</c:otherwise>
			     </c:choose> 
			     	开始日期：<input id="J-xl" class="inputj" name="begintime" type="text" value="${begintime }"/>
			                           结束日期：<input id="J-xl1" class="inputj" name="endtime" type="text" value="${endtime }"/>                
			                            主叫号：<input class="inputj" name="calledno" type="text" value="${calledno }"/>
			                            落地号：<input class="inputj" name="callno" type="text" value="${callno }"/>
			                            被叫号：<input class="inputj" name="callingno" type="text" value="${callingno }"/>
			                            通话类型：<input class="inputj" name="ntype" type="text" value="${ntype }"/>
			                           <%--  <input name="cusid" value="${cusid }" type="hidden"/> --%>
			                           <input name="bt" type="button" onclick="dtMultiVoiceSearch()" class="report" value="查询" />
			                           <!-- <input name="dc" type="button"  class="report" value="导入" />-->
					    <c:choose>
		                	<c:when test="${sessionScope.user.isroot eq 0 }">
		                		<input name="dc" type="button"  class="report" value="导出" onclick="toExport(0)"/>
		                	</c:when>
		                	<c:otherwise>
		                		<input name="dc" type="button"  class="report" value="导出" onclick="toExport(null)"/>
		                	</c:otherwise>
		                </c:choose>
			                           <!-- <input name="dc" type="button"  class="report" value="批量删除" />-->
                        </form>
                    </div>
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
<thead>
<tr><th>公司名称</th><th>话务id</th><th>主叫号</th><th>落地号</th><th>被叫号</th><th>时长</th><th>日期</th><th>通话类型</th><th>话务平台</th></tr>
</thead>
<tbody>

	<!-- 
	<tr ><td >TCL</td><td>859011171001000068 </td><td>61101</td><td></td><td>017861096263</td><td>8</td><td>2017/1/30</td><td>呼出</td><td>多媒体话务平台</td></tr>
	<tr ><td >TCL</td><td>859011171001000068 </td><td>61101</td><td></td><td>017861096263</td><td>8</td><td>2017/1/30</td><td>呼出</td><td>多媒体话务平台</td></tr>
	<tr ><td >TCL</td><td>859011171001000068 </td><td>61101</td><td></td><td>017861096263</td><td>8</td><td>2017/1/30</td><td>呼出</td><td>多媒体话务平台</td></tr> 
	-->
     <c:if test="${pageBean.list.size() eq 0 }">
			<tr style="text-align:center;height:100px;"><td colspan="9">没有查询到记录</td></tr>
	</c:if>	
	<c:forEach items="${pageBean.list }" var="dtMultiVoice" varStatus="ids">
		<tr ><td >${dtMultiVoice.cusName }</td><td>${dtMultiVoice.traid }</td><td>${dtMultiVoice.calledno }</td><td>${dtMultiVoice.callno }</td><td>${dtMultiVoice.callingno }</td><td>${dtMultiVoice.totalseconds }</td><td>${dtMultiVoice.date }</td><td>${dtMultiVoice.ntype }</td><td>${dtMultiVoice.systemsrc }</td></tr>
    </c:forEach>
     <c:if test="${pageBean.list.size() ne 0 }">
		    <tr><td>小计</td><td> </td><td></td><td></td><td></td><td style="width:120px"><fmt:formatNumber value=" ${pageBean.getSubtotal() }" pattern="###,###,##0.0000" ></fmt:formatNumber>小时</td><td></td><td></td><td></td></tr>
			<tr><td >总计</td><td> </td><td></td><td></td><td></td><td style="width:120px"><fmt:formatNumber value=" ${pageBean.getTotal() }" pattern="###,###,##0.0000" ></fmt:formatNumber>小时</td><td></td><td></td><td></td></tr>
	</c:if>	
</tbody>
</table>
<div class="tbfooter">
<div class="floatleft"><span>记录条数：${pageBean.totalCount }&nbsp;总页数：${pageBean.totalPage }&nbsp;当前页：${pageBean.page }</span></div>
            <div class="floatright">
                <form action="" method="get" class="formfloat" >
                    到第&nbsp;<input name="number" type="text" class="number" value="${pageBean.page }"/>&nbsp;页
                    <input name="GO" type="button" value="GO" onClick="toPage()"/>
                </form>
            </div>
<div class="floatright">
	           	<c:choose>
	           		<c:when test="${pageBean.page eq 1 }">
	           			<span class="disabled"><< 首页 </span>
	           		</c:when>
	           		<c:otherwise>
	           			<span class="disabled"><a style="cursor:pointer;" onclick="dtMultiVoiceToNextPage('${pageContext.request.contextPath}/multiVoice/showMultiVoice.action?page=1&cusname=${cusname}&callingno=${callingno}&callno=${callno}&calledno=${calledno}&ntype=${ntype}&cusid=${cusid}&begintime=${begintime}&endtime=${endtime}')"  ><< 首页</a> </span>
	           		</c:otherwise>
	           	</c:choose>
	               
	               <c:choose>
	               	<c:when test="${pageBean.page>1 }">
	               		<span class="disabled"><a style="cursor:pointer;" onclick="dtMultiVoiceToNextPage('${pageContext.request.contextPath}/multiVoice/showMultiVoice.action?page=${pageBean.page-1}&cusname=${cusname}&callingno=${callingno}&callno=${callno}&calledno=${calledno}&ntype=${ntype}&cusid=${cusid}&begintime=${begintime}&endtime=${endtime}')" >< 上一页</a> </span>
	               	</c:when>
	               	<c:otherwise>
	               		<span class="disabled">< 上一页 </span>
	               	</c:otherwise>
	               </c:choose>
	               
	               <c:choose>
	               	<c:when test="${pageBean.page<pageBean.totalPage }">
	               		<span class="disabled"> <a  style="cursor:pointer;" onclick="dtMultiVoiceToNextPage('${pageContext.request.contextPath}/multiVoice/showMultiVoice.action?page=${pageBean.page+1}&cusname=${cusname}&callingno=${callingno}&callno=${callno}&calledno=${calledno}&ntype=${ntype}&cusid=${cusid}&begintime=${begintime}&endtime=${endtime}')">下一页 ></a> </span>
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
	               		<span class="disabled"> <a style="cursor:pointer;" onclick="dtMultiVoiceToNextPage('${pageContext.request.contextPath}/multiVoice/showMultiVoice.action?page=${pageBean.totalPage}&cusname=${cusname}&callingno=${callingno}&callno=${callno}&calledno=${calledno}&ntype=${ntype}&cusid=${cusid}&begintime=${begintime}&endtime=${endtime}')" >尾页>></a> </span>
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

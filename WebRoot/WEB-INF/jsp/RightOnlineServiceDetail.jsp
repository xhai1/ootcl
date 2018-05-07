<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<!--<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />-->
<script src="${pageContext.request.contextPath}/js/laydate-v5.0.9.js"></script>

<link href="${pageContext.request.contextPath}/css/jdt.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/dtolservice.js"></script>

<script>
	function toPage(){
		var page = $("input[name='number']").val();
		window.location.href="${pageContext.request.contextPath}/dtOnline/showOnlineService.action?page="+page+"&mediatype=${mediatype}&businesstype=${businesstype}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&mediasrc=${mediasrc}";
	}
	function toExport(root){
		if(root==0){
			if(confirm("只能导出当前页的内容！")){
				window.location.href="${pageContext.request.contextPath}/dtOnline/exportOnlineService.action?mediatype=${mediatype}&businesstype=${businesstype}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&mediasrc=${mediasrc}&page="+${pageBean.page};
			}
		}else{
			if(confirm("只能导出前30000条的内容！如果需要更多的内容请分次导出！")){
				window.location.href="${pageContext.request.contextPath}/dtOnline/exportOnlineService.action?mediatype=${mediatype}&businesstype=${businesstype}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&mediasrc=${mediasrc}&page="+${pageBean.page};
			}
		}
	}
</script>
</head>

<body>
 <div class="rightstyle">
<div class="grayStripe">在线服务清单查询</div>

        <div class = "reportAlign floatright">
            <form name="dtOlService" action="${pageContext.request.contextPath}/dtOnline/showOnlineService.action" method="get">
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
                媒体类别：<input class="inputj" name="mediatype" type="text" value="${mediatype }"/>
                业务类别：<input class="inputj" name="businesstype" type="text" value="${businesstype }"/>
                媒体来源：<input class="inputj" name="mediasrc" type="text" value="${mediasrc }"/>
                <%-- <input name="cusid" value="${cusid }" type="hidden"/> --%>
                <input name="bt" type="button" onclick="dtOlServiceSearch()" class="report" value="查询" />
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
<thead><tr><th>序号</th><th>公司名称</th><th>月份</th><th>日期</th><th>id</th><th>媒体类别</th><th>媒体来源</th><th>业务类别</th><th>开始时间</th><th>结束时间</th><th>时长</th></tr></thead>
<tbody>

	<!-- 
	<tr class="righttabletr"><td >1</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>0.10.4;app-a2397a5a1099405da924ae1115af04fe</td><td>APP</td><td>十分到家官网</td><td>手机维修</td><td>2017/10/1&nbsp;8:18:27</td><td >2017/10/1&nbsp;8:18:27</td><td>6</td></tr>
	<tr class="righttabletr2"><td>2</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>0.10.4;app-a2397a5a1099405da924ae1115af04fe</td><td>APP</td><td>十分到家官网</td><td>手机维修</td><td>2017/10/1&nbsp;8:18:27</td><td >2017/10/1&nbsp;8:18:27</td><td>6</td></tr>
	<tr class="righttabletr"><td >3</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>0.10.4;app-a2397a5a1099405da924ae1115af04fe</td><td>APP</td><td>十分到家官网</td><td>手机维修</td><td>2017/10/1&nbsp;8:18:27</td><td >2017/10/1&nbsp;8:18:27</td><td>6</td></tr>
	<tr class="righttabletr2"><td>4</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>0.10.4;app-a2397a5a1099405da924ae1115af04fe</td><td>APP</td><td>十分到家官网</td><td>手机维修</td><td>2017/10/1&nbsp;8:18:27</td><td >2017/10/1&nbsp;8:18:27</td><td>6</td></tr>
	<tr class="righttabletr"><td >5</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>0.10.4;app-a2397a5a1099405da924ae1115af04fe</td><td>APP</td><td>十分到家官网</td><td>手机维修</td><td>2017/10/1&nbsp;8:18:27</td><td >2017/10/1&nbsp;8:18:27</td><td>6</td></tr>
	<tr class="righttabletr2"><td>6</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>0.10.4;app-a2397a5a1099405da924ae1115af04fe</td><td>APP</td><td>十分到家官网</td><td>手机维修</td><td>2017/10/1&nbsp;8:18:27</td><td >2017/10/1&nbsp;8:18:27</td><td>6</td></tr>
	<tr class="righttabletr"><td >7</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>0.10.4;app-a2397a5a1099405da924ae1115af04fe</td><td>APP</td><td>十分到家官网</td><td>手机维修</td><td>2017/10/1&nbsp;8:18:27</td><td >2017/10/1&nbsp;8:18:27</td><td>6</td></tr>
	<tr class="righttabletr2"><td>8</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>0.10.4;app-a2397a5a1099405da924ae1115af04fe</td><td>APP</td><td>十分到家官网</td><td>手机维修</td><td>2017/10/1&nbsp;8:18:27</td><td >2017/10/1&nbsp;8:18:27</td><td>6</td></tr>
	<tr class="righttabletr"><td >9</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>0.10.4;app-a2397a5a1099405da924ae1115af04fe</td><td>APP</td><td>十分到家官网</td><td>手机维修</td><td>2017/10/1&nbsp;8:18:27</td><td >2017/10/1&nbsp;8:18:27</td><td>6</td></tr>
	<tr class="righttabletr2"><td>10</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>0.10.4;app-a2397a5a1099405da924ae1115af04fe</td><td>APP</td><td>十分到家官网</td><td>手机维修</td><td>2017/10/1&nbsp;8:18:27</td><td >2017/10/1&nbsp;8:18:27</td><td>6</td></tr>
	-->
     <c:if test="${pageBean.list.size() eq 0 }">
			<tr style="text-align:center;height:100px;"><td colspan="11">没有查询到记录</td></tr>
	</c:if>		
	<c:forEach items="${pageBean.list }" var="olservice" varStatus="ids">
		<tr class="righttabletr2"><td>${ids.index+1 }</td><td>${olservice.cusName }</td><td>${olservice.tmonth }</td><td>${olservice.date }</td><td>${olservice.mediaid }</td><td>${olservice.mediatype }</td><td>${olservice.mediasrc }</td><td>${olservice.businesstype }</td><td>${olservice.begintime }</td><td >${olservice.endtime }</td><td>${olservice.totalseconds }</td></tr>
    </c:forEach>
</tbody>
</table>
<div class="tbfooter">
<div class="floatleft"><span>记录条数：${pageBean.totalCount }&nbsp;总页数：${pageBean.totalPage }&nbsp;当前页：${pageBean.page }</span></div>
            <div class="floatright">
                <form action="" method="get" class="formfloat" >
                    到第&nbsp;<input name="number" type="text" class="number" value="${pageBean.page }" />&nbsp;页
                    <input name="GO" type="button" value="GO" onClick="toPage()"/>
                </form>
            </div>
		<div class="floatright">
	           	<c:choose>
	           		<c:when test="${pageBean.page eq 1 }">
	           			<span class="disabled"><< 首页 </span>
	           		</c:when>
	           		<c:otherwise>
	           			<span class="disabled"><a style="cursor:pointer" onclick="dtOlServiceToNextPage('${pageContext.request.contextPath}/dtOnline/showOnlineService.action?page=1&mediatype=${mediatype}&businesstype=${businesstype}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&mediasrc=${mediasrc}')" ><< 首页</a> </span>
	           		</c:otherwise>
	           	</c:choose>
	               
	               <c:choose>
	               	<c:when test="${pageBean.page>1 }">
	               		<span class="disabled"><a  style="cursor:pointer" onclick="dtOlServiceToNextPage('${pageContext.request.contextPath}/dtOnline/showOnlineService.action?page=${pageBean.page-1}&mediatype=${mediatype}&businesstype=${businesstype}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&mediasrc=${mediasrc}')" >< 上一页</a> </span>
	               	</c:when>
	               	<c:otherwise>
	               		<span class="disabled">< 上一页 </span>
	               	</c:otherwise>
	               </c:choose>
	               
	               <c:choose>
	               	<c:when test="${pageBean.page<pageBean.totalPage }">
	               		<span class="disabled"> <a  style="cursor:pointer" onclick="dtOlServiceToNextPage('${pageContext.request.contextPath}/dtOnline/showOnlineService.action?page=${pageBean.page+1}&mediatype=${mediatype}&businesstype=${businesstype}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&mediasrc=${mediasrc}')" >下一页 ></a> </span>
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
	               		<span class="disabled"> <a style="cursor:pointer" onclick="dtOlServiceToNextPage('${pageContext.request.contextPath}/dtOnline/showOnlineService.action?page=${pageBean.totalPage}&mediatype=${mediatype}&businesstype=${businesstype}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&mediasrc=${mediasrc}')">尾页>></a> </span>
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>短信明细</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<!-- <link href="../../css/xstyle.css" rel="stylesheet" type="text/css" /> -->
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/laydate.dev.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/laydate-v5.0.9.js"></script>
<script src="${pageContext.request.contextPath}/js/loading.js"></script>

<link href="${pageContext.request.contextPath}/css/jdt.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/dtmessage.js"></script>

<script>
	function toPage(){
		var page = $("input[name='number']").val();
		window.location.href="${pageContext.request.contextPath}/dtMessage/showDtMessage.action?page="+page+"&cusname=${cusname}&begintime=${begintime}&endtime=${endtime}&phoneno=${phoneno}&operator=${operator}&cusid=${cusid}";
	}
	function toExport(root){
		if(root==0){
			if(confirm("只能导出当前页的内容！")){
				window.location.href="${pageContext.request.contextPath}/dtMessage/exportDtMessage.action?cusname=${cusname}&begintime=${begintime}&endtime=${endtime}&phoneno=${phoneno}&operator=${operator}&cusid=${cusid}&page="+${pageBean.page};
			}
			
		}else{
			if(confirm("只能导出前30000条的内容！如果需要更多的内容请分次导出！")){
				window.location.href="${pageContext.request.contextPath}/dtMessage/exportDtMessage.action?cusname=${cusname}&begintime=${begintime}&endtime=${endtime}&phoneno=${phoneno}&operator=${operator}&cusid=${cusid}&page="+${pageBean.page};
			}
		}
		
	}
</script>
</head>

<body>
<div class="rightstyle">
<div class="grayStripe">短信明细</div>

        <div class = "reportAlign floatright">
            <form name="dtMessage" action="${pageContext.request.contextPath}/dtMessage/showDtMessage.action" method="get">
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
			                手机号：<input class="inputj" name="phoneno" type="text" value="${phoneno }"/>
			                运营商：<input class="inputj" name="operator" type="text" value="${operator }"/>
                <input name="bt" type="button" onclick="dtMessageSearch()" class="report" value="查询" />
                
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
<thead><tr><th>公司名称</th><th>月份</th><th>日期</th><th>短信ID</th><th>手机号码</th><th>短信端口</th><th>短信用途</th><th>运营商</th></tr></thead>
	
	<tbody>
		<c:if test="${pageBean.list.size() eq 0 }">
					<tr style="text-align:center;height:100px;"><td colspan="8">没有查询到记录</td></tr>
		</c:if>	
		<c:forEach items="${pageBean.list }" var="message">
			<tr class="righttabletr2"><td>${message.cusname }</td><td>${message.tmonth }</td><td>${message.updatetime }</td><td>${message.messageid }</td><td>${message.phoneno }</td><td>${message.port }</td><td>${message.purpose }</td><td>${message.operator }</td></tr>
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
            			<span class="disabled"><a style="cursor:pointer;" onclick="dtMessageToNextPage('${pageContext.request.contextPath}/dtMessage/showDtMessage.action?page=1&cusname=${cusname}&begintime=${begintime}&endtime=${endtime}&phoneno=${phoneno}&operator=${operator}&cusid=${cusid}')"  ><< 首页</a> </span>
            		</c:otherwise>
            	</c:choose>
                
                <c:choose>
                	<c:when test="${pageBean.page>1 }">
                		<span class="disabled"><a style="cursor:pointer;" onclick="dtMessageToNextPage('${pageContext.request.contextPath}/dtMessage/showDtMessage.action?page=${pageBean.page-1}&cusname=${cusname}&begintime=${begintime}&endtime=${endtime}&phoneno=${phoneno}&operator=${operator}&cusid=${cusid}')" >< 上一页</a> </span>
                	</c:when>
                	<c:otherwise>
                		<span class="disabled">< 上一页 </span>
                	</c:otherwise>
                </c:choose>
                
                <c:choose>
                	<c:when test="${pageBean.page<pageBean.totalPage }">
                		<span class="disabled"> <a  style="cursor:pointer;" onclick="dtMessageToNextPage('${pageContext.request.contextPath}/dtMessage/showDtMessage.action?page=${pageBean.page+1}&cusname=${cusname}&begintime=${begintime}&endtime=${endtime}&phoneno=${phoneno}&operator=${operator}&cusid=${cusid}')" >下一页 ></a> </span>
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
                		<span class="disabled"> <a style="cursor:pointer;" onclick="dtMessageToNextPage('${pageContext.request.contextPath}/dtMessage/showDtMessage.action?page=${pageBean.totalPage}&cusname=${cusname}&begintime=${begintime}&endtime=${endtime}&phoneno=${phoneno}&operator=${operator}&cusid=${cusid}')" >尾页>></a> </span>
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

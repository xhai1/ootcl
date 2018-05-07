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
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/dtvoice.js"></script>
<!--<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />-->
<script src="${pageContext.request.contextPath}/js/laydate-v5.0.9.js"></script>

<link href="${pageContext.request.contextPath}/css/jdt.css" rel="stylesheet" type="text/css" />

<script>
	function toPage(){
		var page = $("input[name='number']").val();
		window.location.href="${pageContext.request.contextPath}/dtvoice/showDtVoice.action?page="+page+"&calltype=${calltype}&callingno=${callingno }&calledno=${calledno}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&typedemand=${typedemand}&typeid=${typeid}";
	}
	function toExport(root){
		if(root==0){
			if(confirm("只能导出当前页的内容！")){
				window.location.href="${pageContext.request.contextPath}/dtvoice/exportDtVoice.action?calltype=${calltype}&callingno=${callingno }&calledno=${calledno}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&typedemand=${typedemand}&typeid=${typeid}&page="+${pageBean.page};
			}
		}else{
			if(confirm("只能导出前30000条的内容！如果需要更多的内容请分次导出！")){
				window.location.href="${pageContext.request.contextPath}/dtvoice/exportDtVoice.action?calltype=${calltype}&callingno=${callingno }&calledno=${calledno}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&typedemand=${typedemand}&typeid=${typeid}&page="+${pageBean.page};
			}
		}
	}
	
	function selectCustype(){
		var cusid=document.getElementById("selectCusid").value;
		alert(cusid);
		$.ajax({
			   type: "GET",
			   url: "${pageContext.request.contextPath}/dtvoice/findCustypeByTypeId.action",
			   data: {
				   cusid:cusid,
			   },
			   success: function(data){
				   	//alert(data);
				   	$("#Custype").html(data);
		   	   }
		  });
	}
	
	
/* 
 * 动态获取某公司产品大类
 */
function dChgCustype(){
	var cusid = $("select[name='cusid']").val(); 	
//	alert(path);
	
	$.ajax({  
                    type : "POST",  //提交方式  
                    url : "${pageContext.request.contextPath}/dtvoice/dChgCustype.action",//路径  
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

<body >
	
	<!-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">管理信息</a></li>
    <li><a href="#">语音明细</a></li>
    </ul>
    </div> -->
    
    <div class="rightstyle">
        <div class="grayStripe ">语音清单查询</div>
        
        <div class = "reportAlign floatright">
            <form name="dtvoice" action="${pageContext.request.contextPath}/dtvoice/showDtVoice.action" method="get">
			                查询条件：呼叫类型:<select name="calltype" class="inputj">
			                	
			                    <option value="calltypeall"
				                     <c:if test="${calltype eq ''}">
				                		 selected="selected"
				                	 </c:if>
			                    >全部
			                    </option>
			                    <option value="呼入"
			                    	<c:if test="${calltype eq '呼入'}">
				                		 selected="selected"
				                	 </c:if>
			                    >呼入</option>
			                    <option value="呼出"
			                    	<c:if test="${calltype eq '呼出'}">
				                		 selected="selected"
				                	 </c:if>
			                    >呼出</option>
			                    </select>
			                <!-- 日期：<input  class="inputj" type="text" id="J-xl" name=""> -->
			       <c:choose>
        	     	<c:when test="${sessionScope.user.isroot ne 0 }">
        	     		<%--  公司名称：<input class="inputj" name="cusname" type="text" value="${cusname }"/> --%>
	        	     	   公司名称:<select id="selectCusid" name="cusid" onchange="dChgCustype()">
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
			     
			   产品类别: <select name="typeid"  class="inputj">
					<option value="typeidall" 
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
			    	
			    
	               	   	
               	   
               	     
			                主叫号码：<input class="inputj" name="callingno" type="text" value="${callingno }"/>
			                被叫号码：<input class="inputj" name="calledno" type="text" value="${calledno }"/>
			                开始日期：<input id="J-xl" class="inputj" name="begintime" type="text" value="${begintime }"/>
			                结束日期：<input id="J-xl1" class="inputj" name="endtime" type="text" value="${endtime }"/>
			                需求大类：<input class="inputj" name="typedemand" type="text" value="${typedemand }"/>
			               <%--  产品大类：<input class="inputj" name="type" type="text" value="${type }"/> --%>
			           
		                <input name="bt" type="button" onclick="dtVoiceSearch()" class="report" value="查询"/>
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
        
        <table class="righttable" cellspacing="0px" cellpadding="2px">
            <thead><tr><th>序号</th><th>公司名称</th><th>月份</th><th>日期</th><th>话务ID</th><th>主叫号</th><th>被叫号</th><th>通话开始时间</th><th>通话结束时间</th><th>时长(分)</th><th>计时产品大类</th><th>呼叫类型</th><th>需求大类</th><th>系统来源</th></tr></thead>
            
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
            
            
            <tbody>
                
                <!-- 
                <tr class="righttabletr"><td>1</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>11149816138224</td><td>15897883755</td><td>2569973</td><td>2017/10/1&nbsp;8:15:19</td><td>2017/10/1&nbsp;8:18:27</td><td >6</td><td>速必达</td><td>呼入</td><td>清洗</td><td>全媒体话务平台</td></tr>
                <tr class="righttabletr"><td>2</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>11149816138224</td><td>15897883755</td><td>2569973</td><td>2017/10/1&nbsp;8:15:19</td><td>2017/10/1&nbsp;8:18:27</td><td >6</td><td>速必达</td><td>呼入</td><td>清洗</td><td>全媒体话务平台</td></tr>
                <tr class="righttabletr"><td>3</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>11149816138224</td><td>15897883755</td><td>2569973</td><td>2017/10/1&nbsp;8:15:19</td><td>2017/10/1&nbsp;8:18:27</td><td >6</td><td>速必达</td><td>呼入</td><td>清洗</td><td>全媒体话务平台</td></tr>
                <tr class="righttabletr"><td>4</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>11149816138224</td><td>15897883755</td><td>2569973</td><td>2017/10/1&nbsp;8:15:19</td><td>2017/10/1&nbsp;8:18:27</td><td >6</td><td>速必达</td><td>呼入</td><td>清洗</td><td>全媒体话务平台</td></tr>
                <tr class="righttabletr"><td>5</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>11149816138224</td><td>15897883755</td><td>2569973</td><td>2017/10/1&nbsp;8:15:19</td><td>2017/10/1&nbsp;8:18:27</td><td >6</td><td>速必达</td><td>呼入</td><td>清洗</td><td>全媒体话务平台</td></tr>
                <tr class="righttabletr"><td>6</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>11149816138224</td><td>15897883755</td><td>2569973</td><td>2017/10/1&nbsp;8:15:19</td><td>2017/10/1&nbsp;8:18:27</td><td >6</td><td>速必达</td><td>呼入</td><td>清洗</td><td>全媒体话务平台</td></tr>
                <tr class="righttabletr"><td>7</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>11149816138224</td><td>15897883755</td><td>2569973</td><td>2017/10/1&nbsp;8:15:19</td><td>2017/10/1&nbsp;8:18:27</td><td >6</td><td>速必达</td><td>呼入</td><td>清洗</td><td>全媒体话务平台</td></tr>
                <tr class="righttabletr"><td>8</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>11149816138224</td><td>15897883755</td><td>2569973</td><td>2017/10/1&nbsp;8:15:19</td><td>2017/10/1&nbsp;8:18:27</td><td >6</td><td>速必达</td><td>呼入</td><td>清洗</td><td>全媒体话务平台</td></tr>
                <tr class="righttabletr"><td>9</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>11149816138224</td><td>15897883755</td><td>2569973</td><td>2017/10/1&nbsp;8:15:19</td><td>2017/10/1&nbsp;8:18:27</td><td >6</td><td>速必达</td><td>呼入</td><td>清洗</td><td>全媒体话务平台</td></tr>
                <tr class="righttabletr"><td>10</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>11149816138224</td><td>15897883755</td><td>2569973</td><td>2017/10/1&nbsp;8:15:19</td><td>2017/10/1&nbsp;8:18:27</td><td >6</td><td>速必达</td><td>呼入</td><td>清洗</td><td>全媒体话务平台</td></tr>
                <tr class="righttabletr"><td>11</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>11149816138224</td><td>15897883755</td><td>2569973</td><td>2017/10/1&nbsp;8:15:19</td><td>2017/10/1&nbsp;8:18:27</td><td >6</td><td>速必达</td><td>呼入</td><td>清洗</td><td>全媒体话务平台</td></tr>
                <tr class="righttabletr"><td>12</td><td>XXX公司</td><td>2017-10</td><td>2017-10-01</td><td>11149816138224</td><td>15897883755</td><td>2569973</td><td>2017/10/1&nbsp;8:15:19</td><td>2017/10/1&nbsp;8:18:27</td><td >6</td><td>速必达</td><td>呼入</td><td>清洗</td><td>全媒体话务平台</td></tr> 
                -->
                
                <c:if test="${pageBean.list.size() eq 0 }">
					<tr style="text-align:center;height:100px;"><td colspan="14">没有查询到记录</td></tr>
				</c:if>
                
                <c:forEach items="${pageBean.list }" var="voice" varStatus="ids">
                	<tr class="righttabletr"><td>${ids.index+1 }</td><td>${voice.cusName }</td><td>${voice.tmonth }</td><td>${voice.date }</td><td>${voice.traid }</td><td>${voice.callingno }</td><td>${voice.calledno }</td><td>${voice.begintime }</td><td>${voice.endtime }</td><td >${voice.totalseconds }</td><td>${voice.typetimeing }</td><td>${voice.ntype }</td><td>${voice.typedemand }</td><td>${voice.systemsrc }</td></tr>
                </c:forEach>
               
               <c:if test="${pageBean.list.size() ne 0 }">
	               <tr class="righttabletr"><td style="width:24px">小计</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td style="width:120px"><fmt:formatNumber value=" ${pageBean.getSubtotal() }" pattern="###,###,##0.0000" ></fmt:formatNumber>小时</td><td></td><td></td><td></td><td></td></tr>
	               <tr class="righttabletr"><td style="width:24px">总计</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td style="width:120px"><fmt:formatNumber value=" ${pageBean.getTotal() }" pattern="###,###,##0.0000" ></fmt:formatNumber>小时</td><td></td><td></td><td></td><td></td></tr>
				</c:if>
               
            </tbody>
        </table>
        <div class="tbfooter">
            <div class="floatleft"><span>记录条数：${pageBean.totalCount }&nbsp;&nbsp;总页数：${pageBean.totalPage }&nbsp;&nbsp;当前页：${pageBean.page }</span></div>
            <div class="floatright">
                <form action="" method="get" class="formfloat" >
                   	到第&nbsp;<input name="number" type="text" class="number" value="${pageBean.page}"/>&nbsp;页
                   	<input name="GO" type="button" value="GO" onClick="toPage()"/>
                </form>
            </div>
            <div class="floatright">
            	<c:choose>
            		<c:when test="${pageBean.page eq 1 }">
            			<span class="disabled"><< 首页 </span>
            		</c:when>
            		<c:otherwise>
            			<span class="disabled"><a style="cursor: pointer" onclick="dtVoiceToNextPage('${pageContext.request.contextPath}/dtvoice/showDtVoice.action?page=1&calltype=${calltype}&callingno=${callingno }&calledno=${calledno}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&typedemand=${typedemand}&typeid=${typeid}')" href="${pageContext.request.contextPath}/dtvoice/showDtVoice.action?page=1&calltype=${calltype}&callingno=${callingno }&calledno=${calledno}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&typedemand=${typedemand}&typeid=${typeid}')"><< 首页</a> </span>
            		</c:otherwise>
            	</c:choose>
                
                <c:choose>
                	<c:when test="${pageBean.page>1 }">
                		<span class="disabled"><a style="cursor: pointer" onclick="dtVoiceToNextPage('${pageContext.request.contextPath}/dtvoice/showDtVoice.action?page=${pageBean.page-1}&calltype=${calltype}&callingno=${callingno }&calledno=${calledno}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&typedemand=${typedemand}&typeid=${typeid}')" >< 上一页</a> </span>
                	</c:when>
                	<c:otherwise>
                		<span class="disabled">< 上一页 </span>
                	</c:otherwise>
                </c:choose>
                
                <c:choose>
                	<c:when test="${pageBean.page<pageBean.totalPage }">
                		<span class="disabled"> <a style="cursor: pointer" onclick="dtVoiceToNextPage('${pageContext.request.contextPath}/dtvoice/showDtVoice.action?page=${pageBean.page+1}&calltype=${calltype}&callingno=${callingno }&calledno=${calledno}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&typedemand=${typedemand}&typeid=${typeid}')" >下一页 ></a> </span>
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
                		<span class="disabled"> <a style="cursor: pointer" onclick="dtVoiceToNextPage('${pageContext.request.contextPath}/dtvoice/showDtVoice.action?page=${pageBean.totalPage}&calltype=${calltype}&callingno=${callingno }&calledno=${calledno}&begintime=${begintime}&endtime=${endtime}&cusid=${cusid}&cusname=${cusname}&typedemand=${typedemand}&typeid=${typeid}')" >尾页>></a> </span>
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

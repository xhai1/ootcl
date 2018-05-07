<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<!--  <link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />  -->
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jQuery.print.js"></script>
<script src="${pageContext.request.contextPath}/js/laydate-v5.0.9.js"></script>
<script type="text/javascript">

var status = 0;
//使用jquery加载事件
$(document).ready(function() {
    $("#selall").click(function() {
        if (status == 0) {
            $("input[name=usercode]").prop("checked", "checked");
            $("input[name=selall]").prop("checked", "checked");
            status = 1;
        }

        else {
            $("input[name=usercode]").prop("checked", false);
            $("input[name=selall]").prop("checked", false);
            status = 0;
        }
    });
});

</script>
<script type="text/javascript">
	function toPage(){
		var page = $("input[name='number']").val();
		window.location.href="${pageContext.request.contextPath}/monthReport/showMonthReportDetail.action?page="+page+"&cusname=${cusname}&begintime${begintime}&endtime=${endtime}&cusid=${cusid}";
	}
	function createReport(){
		window.location.href="${pageContext.request.contextPath}/monthReport/createMonthReport.action?cusname=${cusname}&begintime${begintime}&endtime=${endtime}&cusid=${cusid}";
	}
	/* 预览报表 */
	function createOneReport(id){
		/* window.location.href="${pageContext.request.contextPath}/monthReport/createMonthReport.action?id="+id; */
		window.open('${pageContext.request.contextPath}/monthReport/createMonthReport.action?id='+id);
	}
	
	function accountOneReport(id){
		if(window.confirm("确定结算该账单？")){
			/* window.location.href="${pageContext.request.contextPath}/monthReport/accountMonthReport.action?id="+id; */
			window.open("${pageContext.request.contextPath}/monthReport/accountMonthReport.action?id="+id);
		}
	}
	function createReportByIds(){
		//判断至少写了一项  
	    var checkedNum = $("input[name='usercode']:checked").length;
	    if (checkedNum == 0) {
	        alert("请至少选择一项!");
	        return false;
	    }
        var checkedList = new Array();
        $("input[name='usercode']:checked").each(function() {
            checkedList.push($(this).val());
        });
        
        if(window.confirm("确定要结算选中账单吗？")){
        	window.location.href="${pageContext.request.contextPath}/monthReport/accountMonthReportByIds.action?delitems="+checkedList.toString();
        }
        
        /* $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/monthReport/createMonthReportByIds.action",
            data: {
                "delitems": checkedList.toString()
            },
            datatype: "html",
            success: function(data) {
                $("[name='selall']:checkbox").attr("checked", false);
                location.reload(); //页面刷新  
            },
            error: function(data) {
               art.dialog.tips('删除失败!');
            }
        }); */
    }
	function exportWps(id){
		window.location.href="${pageContext.request.contextPath}/monthReport/exportMonthReportWps.action?id="+id;
	}
	function exportOffice(id){
		window.location.href="${pageContext.request.contextPath}/monthReport/exportMonthReportOffice.action?id="+id;
	}
</script>
</head>

<body class="rightstyle">

<div>
<div class="grayStripe">汇总明细表</div>
<form action="${pageContext.request.contextPath}/monthReport/showMonthReportDetail.action" method="get">
	<div class="reportAlign floatright">
		<%-- <input type="hidden" name="cusid" value="${cusid}"> --%>
	                <%-- 公司名称：<input class="inputj" name="cusname" type="text" value="${cusname }"/> --%>
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
	    <!-- <button onClick="x()" class="">查询</button> -->
	  	    开始日期：<input id="J-xl" class="inputj" name="begintime" type="text" value="${begintime }"/>
	                结束日期：<input id="J-xl1" class="inputj" name="endtime" type="text" value="${endtime }"/>
	    <input class="" name="" type="submit" value="查询"/>
	    <!-- <input class="" name="" type="button" onclick="createReport()" value="查询生成"/> -->	
	   <input class="" name="" type="button" onclick="createReportByIds()" value="选中结算"/>	   
	</div>
</form>

<div class="clearfloat"></div>
<table class="usertable">
    <tr>
        <td>全选</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>      
    </tr>
    <tr>
        <th scope="col"><input id="selall" name="userCode" type="checkbox" onClick="x()" value="" /></th>
        <th scope="col">计费时段</th>
        <th scope="col">客户名称</th>
        <th scope="col">结算</th>
        <th scope="col">预览</th>  
        <th scope="col">导出WPS</th>      
        <th scope="col">导出Office</th>
    </tr>
    <c:forEach items="${pageBean.list }" var="report">
    	<tr>
    		<c:choose>
    			<c:when test="${report.endflag eq 1}">
    				<td></td>
    			</c:when>
    			<c:otherwise>
    				<td><input name="usercode" type="checkbox" onClick="x()" value="${report.id }" /></td>
    			</c:otherwise>
    		</c:choose>
	        
	        <td>${report.month }</td>
	        <td>${report.cusname }</td>
	        <c:choose>
				<c:when test="${report.endflag eq 1}">
					<td><input name="usercode" type="button"  value="已结算" /></td>
				</c:when>
				<c:otherwise>
					<td><input name="usercode" type="button" onClick="accountOneReport(${report.id})" value="结算" /></td>
				</c:otherwise>	        
	        </c:choose>
	        <td><input name="usercode" type="button" onClick="createOneReport(${report.id})" value="预览" /></td>
	        <td><input name="usercode" type="button" onClick="exportWps(${report.id})" value="导出WPS" /></td>
	        <td><input name="usercode" type="button" onClick="exportOffice(${report.id})" value="导出Office" /></td>     
	    </tr>
    </c:forEach>
    
    <!-- <tr>
        <td><input name="usercode" type="checkbox" onClick="x()" value="" /></td>
        <td>2017年8月</td>
        <td>TCL王牌电器有限公司</td>
        <td><input name="usercode" type="button" onClick="x()" value="套打" /></td>
        <td><input name="usercode" type="button" onClick="x()" value="预览" /></td>
        <td><input name="usercode" type="button" onClick="x()" value="导出" /></td>        
    </tr>
    <tr>
        <td><input name="usercode" type="checkbox" onClick="x()" value="" /></td>
        <td>2017年8月</td>
        <td>TCL王牌电器有限公司</td>
        <td><input name="usercode" type="button" onClick="x()" value="套打" /></td>
        <td><input name="usercode" type="button" onClick="x()" value="预览" /></td>
        <td><input name="usercode" type="button" onClick="x()" value="导出" /></td>    
    </tr>
    <tr>
        <td><input name="usercode" type="checkbox" onClick="x()" value="" /></td>
        <td>2017年8月</td>
        <td>TCL王牌电器有限公司</td>
        <td><input name="usercode" type="button" onClick="x()" value="套打" /></td>
        <td><input name="usercode" type="button" onClick="x()" value="预览" /></td>
        <td><input name="usercode" type="button" onClick="x()" value="导出" /></td>  
    </tr>      -->
</table>
<div class="userpage">

    <div class="tbfooter">
    <div class="floatleft"><span>记录条数：${pageBean.totalCount }&nbsp;&nbsp;总页数：${pageBean.totalPage }&nbsp;&nbsp;当前页：${pageBean.page }</span></div>
    
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
	           			<span class="disabled"><a href="${pageContext.request.contextPath}/monthReport/showMonthReportDetail.action?page=1&cusname=${cusname}&begintime${begintime}&endtime=${endtime}&cusid=${cusid}"><< 首页</a> </span>
	           		</c:otherwise>
	           	</c:choose>
	               
	               <c:choose>
	               	<c:when test="${pageBean.page>1 }">
	               		<span class="disabled"><a href="${pageContext.request.contextPath}/monthReport/showMonthReportDetail.action?page=${pageBean.page-1}&cusname=${cusname}&begintime${begintime}&endtime=${endtime}&cusid=${cusid}">< 上一页</a> </span>
	               	</c:when>
	               	<c:otherwise>
	               		<span class="disabled">< 上一页 </span>
	               	</c:otherwise>
	               </c:choose>
	               
	               <c:choose>
	               	<c:when test="${pageBean.page<pageBean.totalPage }">
	               		<span class="disabled"> <a href="${pageContext.request.contextPath}/monthReport/showMonthReportDetail.action?page=${pageBean.page+1}&cusname=${cusname}&begintime${begintime}&endtime=${endtime}&cusid=${cusid}">下一页 ></a> </span>
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
	               		<span class="disabled"> <a href="${pageContext.request.contextPath}/monthReport/showMonthReportDetail.action?page=${pageBean.totalPage}&cusname=${cusname}&begintime${begintime}&endtime=${endtime}&cusid=${cusid}">尾页>></a> </span>
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
</script>
</body>
</html>

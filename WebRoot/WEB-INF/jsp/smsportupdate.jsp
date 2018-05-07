<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<!--<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />-->
</head>

<body>

<div class="grayStripe">修改短信端口归属</div>
<div >
    <form action="${pageContext.request.contextPath}/SmsPort/updateSmsPort.action" method="post">

<table class="smsutable">


    <tr>
        <th>短信端口:</th>
        <td><input name="port" type="text" value="${smsPort.port}"/></td>
    </tr>
    <tr>
        <th>短信用途:</th>
        <td><input name="purpose" type="text" value="${smsPort.purpose}"/></td>
    </tr>
    <tr>
        <th>计时所属公司:</th>
        <td><%-- <input name="cusname" type="text" value="${smsPort.cusname}"/> --%>        				
					<select class="inputj" name="cusid">
					                <option value="" 
					                	<c:if test="${smsPort.cusname eq '' }">
				             				selected="selected"
				             			</c:if>
					                >
					                	请选择公司
					                </option>
					                <c:forEach items="${customerVoList }" var="ct" >
					             		<option value="${ct.cusid }"
					             			<c:if test="${ct.cusname eq smsPort.cusname }">
					             				selected="selected"
					             			</c:if>
					             		>
					               			${ct.cusname }
					                	</option>
					                </c:forEach>
					                
				</select>
        </td>
    </tr>
    <tr>
        <th>运营商:</th>
        <td><input name="caroprator" type="text" value="${smsPort.caroprator}"/></td>
    </tr>
        <tr>
        <th></th>
        <td><input name="submit" type="submit"  value="修改"/>&nbsp;<input name="button" type="button"  value="返回" onclick="history.go(-1)"/></td>
    </tr>
    
    <tr>
        <th></th>
        <td><input name="id" type="hidden"  value="${smsPort.id}"/>&nbsp;<%-- <input name="cusid" type="hidden"  value="${smsPort.cusid}"/> --%></td>
        <!-- 便于回到修改起始界面  -->
        <input name="nextPage" type="hidden"  value="${page.getNextPage()}"/>
        <input name="totalPage" type="hidden"  value="${page.getTotalPage()}"/>
        <input name="condition1" type="hidden"  value="${page.condition1}"/>
        <input name="condition2" type="hidden"  value="${page.condition2}"/>
         <input name="condition3" type="hidden"  value="${page.condition3}"/>
         
    </tr>
</table>

</form>


</div>
<script>
	$("select[name='cusid']").width($("input[name='caroprator']").width());
</script>
</body>
</html>

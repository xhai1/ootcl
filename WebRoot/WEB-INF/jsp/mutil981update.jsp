<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<!--<link href="../../css/xstyle.css" rel="stylesheet" type="text/css" />-->
<style>
body{
	background-color:#EDF6FA;
}
</style>
</head>

<body>

<div class="grayStripe">
        多媒体981租户分机号修改      
</div>
<div class="sysparam">
    <form action="${pageContext.request.contextPath}/multi981/UpdateMutilMedia981.action?nextPage=${page.getNextPage()}&totalPage=${page.getTotalPage()}&totalRecord=${page.totalRecord}&condition1=${page.condition1}&condition2=${page.condition2}&condition3=${page.condition3}" method="post">


<table>
 	 
    <tr>
        <td class="center">租户:</td>
        <td><input name="itenantId" type="text" size="50" value="${mutilMedia.itenantId}"/></td>
    </tr>
    <tr>
        <td class="center">分机号:</td>
        <td><input name="extNumber" type="text" size="50" value="${mutilMedia.extNumber}" readonly=readonly/></td>
    </tr>
    <tr>
        <td class="center">落地号:</td>
        <td><input name="telephone" type="text" size="50" value="${mutilMedia.telephone}" /></td>
    </tr>
    <tr>
        <td class="center">计时产品大类:</td>
        <td><input name="bigType" id="bigType" type="text" size="50" value="${mutilMedia.bigType}"/></td>
    </tr>
    <tr>
        <td class="center">计时所属公司:</td>
        <td><%-- <input name="cusName" type="text" size="50" value="${mutilMedia.cusName}"/> --%>
        
	        	<select id="selectCusid" name="cusId">
						                <option value="" 
						                	<c:if test="${mutilMedia.cusName eq '' }">
					             				selected="selected"
					             			</c:if>
						                >
						                	请选择公司
						                </option>
						                <c:forEach items="${customerVoList }" var="ct" >
						             		<option value="${ct.cusid }"
						             			<c:if test="${mutilMedia.cusName eq ct.cusname }">
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
    	<td><input name="id" type="hidden" size="50" value="${mutilMedia.id}"/></td>
        <td colspan="2" class="alignr"><input name="update" type="submit" value="修改" /></td>        
    </tr>
    <tr>
    	<td></td>
        <td colspan="2" class="alignr"><input name="" type="button" value="返回" onclick="history.go(-1)"/></td>        
    </tr>
</table>

</form>

</div>
<script type="text/javascript">
	$("#selectCusid").width($("#bigType").width());
</script>
</body>
</html>

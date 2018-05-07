<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<!-- <link href="../../css/xstyle.css" rel="stylesheet" type="text/css" /> -->
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script>
	function toPage(){
		var page = $("input[name='number']").val();
		window.location.href="${pageContext.request.contextPath}/permission/showAllUserInfo.action?page="+page+"&username=${username}";
	}
</script>
</head>

<body class="rightstyle">

<div>
<div class="usermanage">用户权限信息</div>
<form action="${pageContext.request.contextPath}/permission/showAllUserInfo.action" method="post">
	<div class="usersearch">
		用户名称:<input class="userinput" name="username" value="${username }" type="text"/>
		<input type="submit" value="查询" name="bt"/>
	</div>
</form>


<table class="usertable"> 
    <tr>
        <th scope="col">用户名</th>
        <th scope="col">用户密码</th>
        <th scope="col">所属公司</th>
        <th scope="col">用户类型</th>
        <th scope="col">&nbsp;</th>
    </tr>
    <c:forEach items="${pageBean.list }" var="userinfo">
    	<tr>
	        <td>${userinfo.username }</td>
	        <td>${userinfo.password }</td>
	        <td>${userinfo.cusname }</td>
	        <c:if test="${userinfo.isroot eq 2 }"><td>超级管理员</td></c:if>
	        <c:if test="${userinfo.isroot eq 1 }"><td>内部用户</td></c:if>
	        <c:if test="${userinfo.isroot eq 0 }"><td>外部用户</td></c:if>
	        <c:if test="${empty userinfo.isroot }"><td>""</td></c:if>
	        <td><a href="${pageContext.request.contextPath}/permission/getUserForUpdatePermission.action?id=${userinfo.id }" class="green" target="rightFrame">修改权限</a></td>
	    </tr>
    </c:forEach>
    
</table>
<div class="userpage">

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
	           			<span class="disabled"><a href="${pageContext.request.contextPath}/permission/showAllUserInfo.action?page=1&username=${username}"><< 首页</a> </span>
	           		</c:otherwise>
	           	</c:choose>
	               
	               <c:choose>
	               	<c:when test="${pageBean.page>1 }">
	               		<span class="disabled"><a href="${pageContext.request.contextPath}/permission/showAllUserInfo.action?page=${pageBean.page-1}&username=${username}">< 上一页</a> </span>
	               	</c:when>
	               	<c:otherwise>
	               		<span class="disabled">< 上一页 </span>
	               	</c:otherwise>
	               </c:choose>
	               
	               <c:choose>
	               	<c:when test="${pageBean.page<pageBean.totalPage }">
	               		<span class="disabled"> <a href="${pageContext.request.contextPath}/permission/showAllUserInfo.action?page=${pageBean.page+1}&username=${username}">下一页 ></a> </span>
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
	               		<span class="disabled"> <a href="${pageContext.request.contextPath}/permission/showAllUserInfo.action?page=${pageBean.totalPage}&username=${username}">尾页>></a> </span>
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
</body>
</html>



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
<script>
	function toPage(){
		var page = $("input[name='number']").val();
		window.location.href="${pageContext.request.contextPath}/user/showUser.action?page="+page+"&username=${username}";
	}
	function deleteUserById(id){
		var flag=confirm("确认删除?");
		if(flag){
			window.location.href="${pageContext.request.contextPath}/user/deleteUser.action?id="+id;
		}
	}
	function deleteUserByIds(){
		//判断至少写了一项  
	    var checkedNum = $("input[name='usercode']:checked").length;
	    if (checkedNum == 0) {
	        alert("请至少选择一项!");
	        return false;
	    }
	    if (confirm("确定删除所选记录?")) {
	        var checkedList = new Array();
	        $("input[name='usercode']:checked").each(function() {
	            checkedList.push($(this).val());
	        });
	        $.ajax({
	            type: "POST",
	            url: "${pageContext.request.contextPath}/user/deleteUsers.action",
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
	        });
	    }
	}
	
</script>
</head>

<body class="rightstyle">

<div>
<div class="usermanage">用户管理</div>
<form action="${pageContext.request.contextPath}/user/showUser.action" method="get">
	<div class="usersearch">
		用户名:<input class="userinput" name="username" value="${username }" type="text"/>
		真实姓名:<input class="userinput" name="truename" value="${truename }" type="text"/>
		<input type="submit" value="查询" name="bt"/>
		<input type="button" value="添加" name="bt" onClick="window.open('${pageContext.request.contextPath}/user/useraddJsp.action','_blank','height=550,width=680,top=200,left=450')"/>	
		
		<input type="button" value="批量删除" name="bt" onclick="deleteUserByIds()"/>
	</div>
</form>


<table class="usertable"> 
    <tr>
        <th scope="col"><input id="selall" name="selall" type="checkbox"/></th>
        <th scope="col">用户名</th>
        <th scope="col">用户密码</th>
        <th scope="col">真实姓名</th>
        <th scope="col">所属公司</th>
        <th scope="col">电话</th>
        <th scope="col">邮箱</th>
        <th scope="col">&nbsp;</th>
        <th scope="col">&nbsp;</th>
        <th scope="col">&nbsp;</th>
    </tr>
    <c:if test="${pageBean.list.size() eq 0 }">
	    <tr>
		        <td colspan="10" style="text-align:center;height:100px;">没有查询到记录	</td>
		</tr>
	</c:if>
    <c:forEach items="${pageBean.list }" var="user">
    	<tr>
	        <td><input name="usercode" type="checkbox" value="${user.id }" /></td>
	        <td>${user.username }	</td>
	        <td>******</td>
	        <td>${user.truename }</td>
	        <td>${user.cusname }</td>
	        <td>${user.telephone }</td>
	        <td>${user.email }</td>
	        <td><a href="${pageContext.request.contextPath}/user/getUserById.action?id=${user.id }" class="black" target="rightFrame">查看</a></td>
	        <td><a href="${pageContext.request.contextPath}/user/getUserForUpdate.action?id=${user.id }" class="green" target="rightFrame">修改</a></td>
	        <td><a href="javascript:deleteUserById(${user.id})" class="red">删除</a></td>
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
	           			<span class="disabled"><a href="${pageContext.request.contextPath}/user/showUser.action?page=1&username=${username}"><< 首页</a> </span>
	           		</c:otherwise>
	           	</c:choose>
	               
	               <c:choose>
	               	<c:when test="${pageBean.page>1 }">
	               		<span class="disabled"><a href="${pageContext.request.contextPath}/user/showUser.action?page=${pageBean.page-1}&username=${username}">< 上一页</a> </span>
	               	</c:when>
	               	<c:otherwise>
	               		<span class="disabled">< 上一页 </span>
	               	</c:otherwise>
	               </c:choose>
	               
	               <c:choose>
	               	<c:when test="${pageBean.page<pageBean.totalPage }">
	               		<span class="disabled"> <a href="${pageContext.request.contextPath}/user/showUser.action?page=${pageBean.page+1}&username=${username}">下一页 ></a> </span>
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
	               		<span class="disabled"> <a href="${pageContext.request.contextPath}/user/showUser.action?page=${pageBean.totalPage}&username=${username}">尾页>></a> </span>
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



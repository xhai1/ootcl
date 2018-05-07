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
<style> 
body{
	background-color:#EDF6FA;
}
</style>
</head>

<body style="color">
 
<div class="databackup">
<script type="text/javascript">
function opdata(op){
	if(op == 1){
		if(confirm("确定要备份吗？"))
		window.location = '${pageContext.request.contextPath}/db/backup.action';
	}else if( op == 2){
		if(confirm("确定要恢复吗？"))
		window.location ='${pageContext.request.contextPath}/db/recovery.action';
	}else if( op == 3){
		if(confirm("确定要下载吗？"))
		window.location ='${pageContext.request.contextPath}/db/download.action';
	}
}

</script>
<form action="" method="get">
数据备份：&nbsp;&nbsp;&nbsp;&nbsp;<input name="update" type="button" value="备份" onClick="opdata(1)"/>&nbsp;&nbsp;<input name="display" type="text" value="${empty back ? null : back}" style="display:${empty back ? 'none' : ''};" disabled = disabled/><br/><br/>
数据恢复：&nbsp;&nbsp;&nbsp;&nbsp;<input name="recover" type="button" value="恢复" onClick="opdata(2)"/>&nbsp;&nbsp;<input name="display" type="text" value="${empty rec ? null : rec}" style="display:${empty rec ? 'none' : ''};" disabled = disabled/><br/><br/>
数据下载：&nbsp;&nbsp;&nbsp;&nbsp;<input name="download" type="button" value="下载" onClick="opdata(3)"/><br/>
</form>

</div>

</body>
</html>

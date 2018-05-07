<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
 <link href="../../css/xstyle.css" rel="stylesheet" type="text/css" /> 
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<style> 
body{
	background-color:#EDF6FA;
}
</style>

<script type="text/javascript">
		function checkM(form1){
			if(form1.file.value==""){
				alert("请选择文件!");form1.file.focus();return false;
			}
		}
	</script>
	<script type="text/javascript">
		function getFilePath(file){
			var filePath;
			file.select();
			try{
				filePath = document.selection.createRange().text;//获得文件的本地路径
			} 
			finally{
				document.selection.empty();
			}
			return filePath;
		}
		function upload(){
			var file = document.getElementById("filePath");
			var str =  getFilePath(file);
			document.getElementById("pathStr").value =encodeURI(str);
		}
		function download(){
			window.location.href="${pageContext.request.contextPath}/download/downloadMoneyStandard.action"
		}
	</script>


</head>

<body style="color">
 <div class="grayStripe">计费标准导入</div>
<div class="addclass">

<form action="${pageContext.request.contextPath}/moneyStandard/FirstImportCusStandard.action" method="post" enctype="multipart/form-data" name="form1" onsubmit="return checkM(form1)">
<table>
    <tr>
         <th scope="col">请按导入模板导入</th>
        <th scope="col"></th>
         <th scope="col">&nbsp;</th>
    </tr>
    <tr>
        
        <td><input name="file" type="file"  id="filePath" /></td>
        <td>&nbsp;</td>
        <td><input type="submit" name="Submit" onclick="upload()" value="导入" /></td>
    </tr>
    <tr>
        <td>&nbsp; </td>
        <td><input type="button" value="下载模板" name="bt" onclick="download()"/> </td>
        <td><input type="button" name="button" onclick="history.go(-1)" value="返回" /></td>
    </tr>
</table>
<input type="hidden" name="pathStr" id="pathStr">
</form>

</div>

</body>
</html>

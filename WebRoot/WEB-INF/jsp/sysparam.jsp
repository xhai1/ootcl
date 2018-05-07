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
<style>
body{
	background-color:#EDF6FA;
}

</style>
</head>

<body>

<div class="sysparamtitle">公司系统参数设置</div>
<div class="sysparam">
    <form action="${pageContext.request.contextPath}/syspParam/updateSysParam.action" method="post">


<table>
 	 <tr class="alignc" >
        <th colspan="2"></th>        
    </tr>
    <tr>
        <td>公司名称:</td>
        <td><input name="companyname" type="text" size="50" value="${companyname}" /></td>
    </tr>
    <tr>
        <td>公司账户:</td>
        <td><input name="comacount" type="text" size="50" value="${comacount}" /></td>
    </tr>
    <tr>
        <td>公司开户行:</td>
        <td><input name="compopacbank" type="text" size="50" value="${compopacbank}" /></td>
    </tr>
    <tr>
        <td>公司地址:</td>
        <td><input name="compaddress" type="text" size="50" value="${compaddress}" /></td>
    </tr>
    <tr>
        <td>同步时长:</td>
        <td><input name="asynctime" type="text" size="50" value="${asynctime}" /></td>
    </tr>
    <tr>
        <td>同步接口:</td>
        <td><input name="asyncinterface" type="text" size="50" value="${asyncinterface}" /></td>
    </tr>
    <tr>
        <td>收款人:</td>
        <td><input name="chargeman" type="text" size="50" value="${chargeman}" /></td>
    </tr>
    
    <tr>
    	<td><input name="display" type="${empty flag ? 'hidden' : 'text'}" value="更新成功！"  disabled=disabled/></td>
        <td colspan="2" class="alignr"><input name="update" type="submit" value="更新" /></td>        
    </tr>
</table>

</form>

</div>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>其它设置</title>
<link href="${pageContext.request.contextPath}/css/xstyle.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script>
	function toRepair(){
		
		
	}
</script>
</head>

<body>
	<div class="rightstyle">
		<div class="grayStripe">异常情况设置</div>
		
	    <div class = "reportAlign floatright"  style="display:none;">
	            <form action="" method="get" class="formfloat" >
	               <input name="extrepair" type="button" value="已手工处理，点击修复" onClick="toRepair()"/>
	            </form>
	    </div>
		
		<div class="clearfloat"></div>
		一切正常！

		<div class="tbfooter" style="display:none;">
			<b>系统异常情况如下：</b><br/>
			<ul>
				<li>客户信息出现重复为【TCL其它】出现2项；</li>
				<li>产品大类表出现重复计时产品大类为【智能家居】出现2项；</li>
				<li>热线号/落地号表中不存在的产品大类有【智能家居3946】;</li>
				<li>（虚拟）落地号不存在的有：25699706，25699009共2项；</li>
				<li>短信端口不存的有：12423142134,3344321共2项；</li>
				<li>多媒体缺少（租户+电话号码）：0+2569501,1041+2569987共2项；</li>		
			</ul>
		</div>
	</div>
</body>
</html>

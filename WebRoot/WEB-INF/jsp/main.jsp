<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客音计费系统</title>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0" onunload="$.post('${pageContext.request.contextPath}/logout.action');">
  <frame src="${pageContext.request.contextPath}/jsp/topJsp.action?isroot=${isroot}&username=${username}&cusid=${cusid}" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
    <frame src="${pageContext.request.contextPath}/jsp/leftJsp.action?isroot=${isroot}&cusid=${cusid}" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frameset  cols="*" frameborder="no" border="0" framespacing="0">
    	<c:choose>
    		<c:when test="${sessionScope.user.isroot eq 2 || sessionScope.user.isroot eq 1 }">
    			<frame src="${pageContext.request.contextPath}/monthReport/showMonthReportDetail.action?page=1" name="rightFrame" id="rightFrame" title="rightFrame" />
    		</c:when>
    		<c:otherwise>
    			<frame src="${pageContext.request.contextPath}/monthReport/showALlMonthReport.action?page=1&cusid=${ sessionScope.user.cusid }" name="rightFrame" id="rightFrame" title="rightFrame" />
    		</c:otherwise>
    	</c:choose>
    	
    	<!-- <frame src="/ocs/ReportServer?reportlet=voice.cpt" name="rightFrame" id="rightFrame" title="rightFrame" /> -->
    </frameset>
    
  </frameset>
</frameset>

<noframes>
<body>
</body>
</noframes>
</html>

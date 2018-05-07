<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select name="typeid" >
	<option value="" 
    	<c:if test="${cusid eq '' }">
				selected="selected"
			</c:if>
    >
    	请选择大类
    </option>
<c:forEach items="${cusTypeList }" var="ct" >
	
	<option value="${ct.typeid }"
	<c:if test="${ct.typeid eq typeid }">
			selected="selected"
		</c:if>
	>
				${ct.type }
	</option>

</c:forEach>
</select>
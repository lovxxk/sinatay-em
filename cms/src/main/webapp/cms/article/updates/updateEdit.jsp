<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
String message = (String)request.getAttribute("message") == null ? "" : (String)request.getAttribute("message");
%>
<c:if test="${message eq 'updateFailed' }">
	<script type="text/javascript">
	alert('更新失败!');
	window.top.close();
	</script>
</c:if>
<c:if test="${message eq 'updateDone' }">
	<script type="text/javascript">
	alert('更新成功!');
	window.top.close();
	</script>
</c:if>

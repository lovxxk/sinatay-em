<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
String message = (String)request.getAttribute("message") == null ? "" : (String)request.getAttribute("message");
%>
<c:if test="${message eq 'createFailed' }">
	<script type="text/javascript">
	alert('新建失败!');
	window.top.close();
	</script>
</c:if>
<c:if test="${message eq 'createDone' }">
	<script type="text/javascript">
	alert('新建成功!');
	window.top.close();
	</script>
</c:if>

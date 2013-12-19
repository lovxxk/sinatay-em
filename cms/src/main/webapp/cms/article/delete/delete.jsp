<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
String message = (String)request.getAttribute("message") == null ? "" : (String)request.getAttribute("message");
%>
<c:if test="${message eq 'deleteFailed' }">
	<script type="text/javascript">
	alert('删除失败!');
	</script>
</c:if>
<c:if test="${message eq 'deleteDone' }">
	<script type="text/javascript">
	alert('删除成功!');
	window.parent.fraSearchForm.doSearch();
	</script>
</c:if>

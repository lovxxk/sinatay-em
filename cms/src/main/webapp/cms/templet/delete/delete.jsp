<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
String result = (String)request.getAttribute("result") == null ? "" : (String)request.getAttribute("result");
%>
<c:if test="${result eq 'binded' }">
	<script type="text/javascript">
	alert('删除的样式中已经有栏目绑定!');
	</script>
</c:if>
<c:if test="${result eq 'deleteFailed' }">
	<script type="text/javascript">
	alert('删除失败!');
	</script>
</c:if>
<c:if test="${result eq 'deleteDone' }">
	<script type="text/javascript">
	alert('删除成功!');
	window.parent.fraSearchForm.doSearch();
	</script>
</c:if>

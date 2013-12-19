<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<%
String message = (String)request.getAttribute("message") == null ? "" : (String)request.getAttribute("message");
String nodeID = (String)request.getAttribute("nodeID") == null ? "" : (String)request.getAttribute("nodeID");
%>
<c:if test="${message eq 'deleteFailed' }">
	<script type="text/javascript">
	alert('删除失败!');
	</script>
</c:if>
<c:if test="${message eq 'deleteDone' }">
	<script type="text/javascript">
	alert('删除成功！');
	window.parent.parent.parent.parent.location.href="${ctx}/cms/index.jsp?ChannelID=<%=nodeID%>";
	</script>
</c:if>

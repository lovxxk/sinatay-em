<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when  test="${message == 'success'}">
	<script language="javascript">
		alert("ÐÞ¸Ä³É¹¦");
		window.parent.parent.close();
		window.parent.parent.opener.parent.frames[0].document.getElementById("frmInput").submit();
	</script>
</c:when>
<c:otherwise>
	<script language="javascript">
		alert("${message}");
	</script>
</c:otherwise>
</c:choose>
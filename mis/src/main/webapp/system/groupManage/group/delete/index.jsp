<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${requestScope.message == 'success'}">
	<script language="javascript">
		alert("ɾ���ɹ�");
		window.parent.frames[0].document.getElementById("frmInput").submit();
	</script>
</c:when>
<c:otherwise>
	<script language="javascript">
		var message = ${requestScope.message};
		alert(message);
	</script>
</c:otherwise>
</c:choose>
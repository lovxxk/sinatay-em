<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${message == 'success'}">
	<script language="javascript">
		alert("新建机构邮箱配置成功");
		window.parent.parent.close();
		window.parent.parent.opener.parent.frames[0].document.getElementById("frmInput").submit();
	</script>
</c:when>
<c:otherwise>
	<script language="javascript">
		alert("${message}");
		window.parent.close();
	</script>
</c:otherwise>
</c:choose>
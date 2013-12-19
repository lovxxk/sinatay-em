<%@ page language="java" contentType="text/html;charset=GBK" isELIgnored="false"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${message == 'success'}">
	<script language="javascript">
	    alert("机构邮箱配置删除成功");
	    window.parent.frames[0].document.getElementById("frmInput").submit();
	</script>
</c:when>
<c:otherwise>
	<script language="javascript">
		alert("${message}");
	</script>
</c:otherwise>
</c:choose>
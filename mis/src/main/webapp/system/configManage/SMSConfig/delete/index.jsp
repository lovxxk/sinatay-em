<%@ page language="java" contentType="text/html;charset=GBK" isELIgnored="false"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${message == 'success'}">
	<script language="javascript">
	    alert("¶ÌĞÅÅäÖÃÉ¾³ı³É¹¦");
	    window.parent.frames[0].document.getElementById("frmInput").submit();
	</script>
</c:when>
<c:otherwise>
	<script language="javascript">
		alert("${message}");
	</script>
</c:otherwise>
</c:choose>
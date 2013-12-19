<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${flag eq 1}">
	<script language="javascript">
		alert("${message}");
		var parentId = "${geAuthority.parentID}";
		if(parentId=="ROOT"){
			window.parent.parent.flushNode("${oldSystype}");
			window.parent.parent.flushNode("${geAuthority.systype}");
			window.parent.parent.onTreeClick('${geAuthority.authorityID}');
		}else{
			window.parent.parent.flushNode(parentId);
			window.parent.parent.onTreeClick('${geAuthority.authorityID}');
		}
	</script>
</c:when>
<c:otherwise>
	<script language="javascript">
		alert("${message}");
	</script>
</c:otherwise>
</c:choose>

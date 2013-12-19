<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${flag eq 1}">
	<script language="javascript">
		alert("${message}");
		var parentId = "${geDepartment.parentid}";
		if(parentId == "1"){
			parentId = "area"+"${geDepartment.businessarea}";
		}
		window.parent.parent.flushNode(parentId);
		window.parent.parent.onTreeClick('${geDepartment.deptid}');
	</script>
</c:when>
<c:otherwise>
	<script language="javascript">
		alert("${message}");
	</script>
</c:otherwise>
</c:choose>

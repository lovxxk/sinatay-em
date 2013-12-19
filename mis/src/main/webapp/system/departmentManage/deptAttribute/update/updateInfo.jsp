<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${flag eq 1}">
	<script language="javascript">
		alert("${message}");
		var obid = '${geDeptAttribute.deptattrid}';
		top.frames[0].doSearch();
		top.document.getElementById("operate").src = "${ctx}/system/deptAttribute/findDeptAttributeForDetail.do?geDeptAttribute.deptattrid=" + obid;
  </script>
</c:when>
<c:otherwise>
	<script language="javascript">
		alert("${message}");
	</script>
</c:otherwise>
</c:choose>

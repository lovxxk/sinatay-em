<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${flag eq 1}">
	<script language="javascript">
		alert("${message}");
		top.frames[0].doSearch();
		top.document.getElementById("operate").src="${ctx}/system/departmentManage/deptAttribute/create/frmCreate.jsp?deptname=${geDeptAttribute.geDepartment.deptname}&deptid=${geDeptAttribute.geDepartment.deptid}";
	</script>
</c:when>
<c:otherwise>
	<script language="javascript">
		alert("${message}");
	</script>
</c:otherwise>
</c:choose>

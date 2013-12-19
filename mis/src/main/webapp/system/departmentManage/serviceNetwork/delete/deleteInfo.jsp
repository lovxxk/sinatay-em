<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${flag eq 1}">
	<script language="javascript">
		alert("${message}");
		var deptid = '${param.deptid}';
		var city='${param.city}';
		var cityName='${param.cityName}';
		var province='${param.province}';
		var provinceName='${param.provinceName}';
		top.frames[0].doSearch();
		top.document.getElementById("operate").src="${ctx}/system/departmentManage/serviceNetwork/create/frmCreate.jsp?deptid="+deptid+"&city="+city+"&cityName="+cityName+"&province="+province+"&provinceName="+provinceName;
	</script>
</c:when>
<c:otherwise>
	<script language="javascript">
		alert("${message}");
	</script>
</c:otherwise>
</c:choose>

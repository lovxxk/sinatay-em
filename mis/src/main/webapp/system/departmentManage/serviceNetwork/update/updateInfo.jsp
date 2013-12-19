<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${flag eq 1}">
	<script language="javascript">
		alert("${message}");
		var obid = '${geStationInfo.obid}';
		top.frames[0].doSearch();
		top.document.getElementById("operate").src = "${ctx}/business/businessManage/serviceNetwork/findServiceNetworkForDetail.do?geStationInfo.obid=" + obid;
  </script>
</c:when>
<c:otherwise>
	<script language="javascript">
		alert("${message}");
	</script>
</c:otherwise>
</c:choose>

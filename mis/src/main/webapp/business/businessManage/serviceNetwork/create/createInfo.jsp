<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:choose>
<c:when test="${flag eq 1}">
	<script language="javascript">
		alert("${message}");
		var town = '${geStationInfo.town}';
		var city = '${geStationInfo.city}';
		var province = '${geStationInfo.province}';
		var parentId;
		if(town != null && town != ''){
			parentId = "lastCity"+town;
		}else if(city != null && city != ''){
			parentId = "lastCity"+city;
		}else{
			parentId = "lastCity"+province;
		}
		window.parent.parent.serviceNetworkTree.flushNode(parentId);
		window.parent.parent.serviceNetworkTree.onTreeClick('serviceNetWork'+'${geStationInfo.obid}');
	</script>
</c:when>
<c:otherwise>
	<script language="javascript">
		alert("${message}");
	</script>
</c:otherwise>
</c:choose>

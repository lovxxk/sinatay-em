<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="<%=request.getContextPath()%>/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" width="60px" nowrap id="idNum">序号</td>
		<td class="search_head" width="160px" nowrap>功能开关ID</td>
		<td class="search_head" width="160px" nowrap>功能开关状态</td>
		<td class="search_head" nowrap>功能开关描述</td>
	</tr>
	<c:forEach items="${geFunctionSwitchList}" var="geFunctionSwitch" begin="0" end="${totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="60px" >${status.index+1+pageSize*(pageNo-1)}</td>
			<td class="search_body" width="160px" >
				<a href="javascript:doEdit('id_${status.index}')">${geFunctionSwitch.functiontId}</a>
				<input type="hidden" id="id_${status.index}" name="id_${status.index}" value="${geFunctionSwitch.functiontId}">
			</td>
			<td class="search_body" width="160px" >
			<c:if test="${geFunctionSwitch.status=='0'}">未开通</c:if>
			<c:if test="${geFunctionSwitch.status=='1'}">开通</c:if>
			<td class="search_body" >${geFunctionSwitch.functionInfo}</td>
		</tr>
	</c:forEach>
	<c:if test="${empty geFunctionSwitchList || totalCount == 0}">
		<tr>
			<td colspan="4">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
	</c:if>
	</table>
</body>
<script type="text/javascript">
	//重新加载page.jsp页面
	window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${pageNo}&pageSize="
											+"${pageSize}&totalPage=${totalPage}&totalCount=${totalCount}" ;
	function doEdit(obj){
		var idStr = document.getElementById(obj).value;
		window.open("${ctx}/business/businessManage/feature/queryForShow.do?geFunctionSwitch.functiontId=" + idStr,"功能开关详细信息" ,"top=200, left=300, width=700,height=300,toolbar=no");		
	}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>

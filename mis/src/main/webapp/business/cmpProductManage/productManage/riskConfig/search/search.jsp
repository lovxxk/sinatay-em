<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" width="38" nowrap id="idNum">序号</td>
		<td class="search_head" width="70" nowrap>险种代码</td>
		<td class="search_head" width="100" nowrap>险种名称</td>
		<td class="search_head" width="70" nowrap>险种简称 </td>
		<td class="search_head" width="100" nowrap>核心险种代码</td>
		<td class="search_head" width="180" nowrap>销售渠道</td>
		<td class="search_head" width="70" nowrap>主附险标志</td>
		<td class="search_head" nowrap>业务领域</td>
	</tr>
	<c:forEach items="${page.result}" var="geRiskConfig" begin="0" end="${page.totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="38" >${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body" width="70" ><a href="javascript:productDetialOpenWindow('${ctx}/productManage/riskDetail.do?riskCode=${geRiskConfig.riskCode}');">${geRiskConfig.riskCode}</a></td>
			<td class="search_body" width="100" >${geRiskConfig.riskName}</td>
			<td class="search_body" width="70" ="200px" style="white-space: normal;word-wrap:break-word;" nowrap>${geRiskConfig.riskSimpleName}</td>
			<td class="search_body" width="100" style="white-space: normal;word-wrap:break-word;" nowrap>${geRiskConfig.coreRiskCode}</td>
			<td class="search_body" width="180px" style="white-space: normal;word-wrap:break-word;" nowrap>${geRiskConfig.saleChannel}</td>
			<td class="search_body_center" width="70" >
				<c:if test="${geRiskConfig.subRiskFlag=='01'}">是</c:if>
				<c:if test="${geRiskConfig.subRiskFlag=='02'}">否</c:if>
			</td>
			<td class="search_body_center"  >
				<c:forEach items="${businessAreaList }" var="businessArea">
					<c:if test="${geRiskConfig.businessArea == businessArea.id.codeCode}">${businessArea.codeCName}</c:if>
				</c:forEach>
			</td>
		</tr>
	</c:forEach>
	<c:if test="${page.totalCount == 0}">
		<tr>
			<td colspan="8">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
	</c:if>
	</table>
	
</body>
<script type="text/javascript">
	window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
	
	function productDetialOpenWindow(obj) {
		window.open(obj, "险种责任详细", "top=100, left=100, width=900, height=600, scrollbars, resizable=yes");
		
}
	
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>

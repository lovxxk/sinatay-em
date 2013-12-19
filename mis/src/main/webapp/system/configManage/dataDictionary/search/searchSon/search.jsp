<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<body topmargin="2" leftmargin="2">
	<table width="90%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" width="40px" nowrap id="idNum">序号</td>
		<td class="search_head" width="40px" nowrap>代码</td>
		<td class="search_head" width="90px" nowrap>代码类型</td>
		<td class="search_head" width="50px" nowrap>显示序号</td>
		<td class="search_head" width="90px" nowrap>代码中文名</td>
		<td class="search_head" width="140px" nowrap>代码英文名</td>
		<td class="search_head" width="90px" nowrap>代码繁体名</td>
		<!--<td class="search_head" nowrap>是否有效</td>-->
	</tr>
	<c:forEach items="${geCodeList}" var="geCode" begin="0" end="${totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}"  class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="40px" nowrap>${status.index+1+pageSize*(pageNo-1)}</td>
			<td class="search_body" width="40px" style="white-space: normal;word-wrap:break-word; ">
				${geCode.id.codeCode}
				<input type="hidden" id="id_${status.index}" value="${geCode.id.codeCode},${geCode.id.codeType}">
			</td>
			<td class="search_body" width="90px" >${codeAndDescMap[geCode.id.codeType]}</td>
			<td class="search_body_center" width="50px" >${geCode.orderNo}</td>
			<td class="search_body" width="90px" ><a href="javascript:doEdit('id_${status.index}')">${geCode.codeCName}</a></td>
			<td class="search_body" width="140px" >${geCode.codeEName}</td>
			<td class="search_body" width="90px" >${geCode.codeTName}</td>
			<!--<td class="search_body_center" nowrap>
			<c:if test="${geCode.validInd=='0'}">无效</c:if>
			<c:if test="${geCode.validInd=='1'}">有效</c:if>
			</td>
			-->
		</tr>
	</c:forEach>
	<c:if test="${empty geCodeList || totalCount == 0}">
		<tr>
			<td colspan="7">
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
		window.open("${ctx}/business/businessManage/dataDictionary/queryForShow.do?geCode.id.codeType=" + idStr,"数据字典详细信息" ,"top=100, left=100, width=900,height=400,toolbar=no");		
	}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>

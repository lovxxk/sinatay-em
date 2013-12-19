<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="<%=request.getContextPath()%>/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" nowrap>序号</td>
		<td class="search_head" nowrap>保单号</td>
		<td class="search_head" nowrap>业务领域</td>
		<td class="search_head" nowrap>操作</td>
	</tr>
	<c:if test="${oper == 1 }">
	<c:forEach items="${page.result }" var="summaryPolicy" varStatus="status">
		<tr id="tr_${status.index}"  class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" nowrap>${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body_center" nowrap>${summaryPolicy.policyNo}</td>
			<td class="search_body_center" nowrap>寿险</td>
			<td id="handle" class="search_body_center" nowrap>&nbsp;</td>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${oper == 2 }">
	<c:forEach items="${page.result }" var="geUserPolicy" varStatus="status">
		<tr id="tr_${status.index}"  class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" nowrap>${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body_center" nowrap>${geUserPolicy.policyNo}</td>
			<td class="search_body_center" nowrap>${geUserPolicy.businessArea == '3'?'财险':(geUserPolicy.businessArea == '4'?'养老险':'')}</td>
			<td id="handle" class="search_body_center" nowrap><a href="javascript:doUnbind('${geUserPolicy.serialNo},${geUserPolicy.businessArea}')">解绑</a></td>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${empty page || page.totalCount == 0}">
		<tr>
			<td colspan="8">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
	</c:if>
	</table>
</body>
<script type="text/javascript">
	//重新加载page.jsp页面
	window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}" ;
	function doUnbind(obj){
		window.parent.frames[4].location.href = "${ctx }/business/customerManage/personalUser/unBoundPolicy.do?idStr="+obj;
	}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>

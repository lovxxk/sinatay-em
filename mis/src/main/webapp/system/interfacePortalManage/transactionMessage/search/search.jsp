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
			<td class="search_head" nowrap id="idNum">序号</td>
			<td class="search_head" nowrap >交易流水号</td>
			<td class="search_head" nowrap>交易代码</td>
			<td class="search_head" nowrap>交易时间 </td>
			<td class="search_head" nowrap>请求时间</td>
		</tr>
		<c:if test="${empty page || page.totalCount == 0}">
		<tr>
			<td colspan="7">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
		</c:if>
		<s:if test="page.result!=null">
		<s:iterator value="page.result" var="transactionMessage" status="status">	
			<tr id="tr_${status.index}">
				<td class="search_body" nowrap>${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
				<td class="search_body" nowrap>
					<a href="javascript:viewTransactionMessageDetail('<s:property value="#transactionMessage.transRefGuid"/>');">
						<s:property value="#transactionMessage.transRefGuid" />
					</a>
				</td>
				<td class="search_body" nowrap><s:property value="#transactionMessage.transCode" /></td>
				<td class="search_body" nowrap><s:date name="#transactionMessage.transTime" format="yyyy-MM-dd HH:mm:ss" /></td>
			    <td class="search_body" nowrap><s:property value="#transactionMessage.requestTime" /></td>
			</tr>
		</s:iterator>
		</s:if>
	</table>
</body>
<script type="text/javascript">
	//重新加载page.jsp页面
	window.parent.frames[3].document.location.href = "${ctx }/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
	function viewTransactionMessageDetail(transRefGuid){
		window.open("${ctx}/interfacePortal/viewTransactionMessageDetail.do?transactionMessage.transRefGuid=" + transRefGuid ,"交易报文信息详细","top=100, left=100,width=1000,,height=550,toolbar=no,scrollbars=yes");
	}	
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>

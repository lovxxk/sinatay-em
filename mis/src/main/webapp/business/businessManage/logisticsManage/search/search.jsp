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
		<td class="search_head" width="40px" nowrap id="idNum">序号</td>
		<td class="search_head" width="125px" nowrap>订单号</td>
		<td class="search_head" width="140px" nowrap>产品名称</td>
		<td class="search_head" width="130px"nowrap>支付成功时间</td>
		<td class="search_head" width="130px"nowrap>订单提交时间</td>
		<td class="search_head" width="90px" nowrap>配送状态</td>
		<td class="search_head" width="90px" nowrap>接收人姓名</td>
		<td class="search_head" style="min-width:120px;" nowrap>
		<div style="width:140">
		订单金额（单位：元）
		</div>
		</td>
	</tr>
	<c:forEach items="${page.result}" var="geOrder" varStatus="status">
		<tr id="tr_${status.index}"  class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="40px" >${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body_center" width="220px" ><a href="javascript:doShow('${geOrder.orderNo}')">${geOrder.orderNo}</a></td>
			<td class="search_body" width="190px" style="white-space: normal;word-wrap:break-word;" >${geOrder.productName}</td>
			<td class="search_body_center" width="130px"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${geOrder.payTime}"/></td>
			<td class="search_body_center" width="130px"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${geOrder.submitTime}"/></td>
			<td class="search_body" width="100px" >${geOrder.flag=="0"?"未配送":(geOrder.flag=="1"?"已配送":"")}</td>
			<td class="search_body" width="90px" >${geOrder.recipientName}</td>
			<td class="search_body" style="min-width:120px;">${geOrder.orderAmount}</td>
		</tr>
	</c:forEach>
	<c:if test="${empty page || page.totalCount == 0}">
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
	window.parent.frames[3].document.location.href = "${ctx }/global/inc/page.jsp?pageNo=${pageNo}&pageSize="
											+"${pageSize}&totalPage=${page.totalPageCount}&totalCount=${page.totalCount}" ;
	function doShow(orderNo){
		window.open("${ctx }/business/businessManage/onlineOrderManage/queryGeOrderShowDetail.do?waybillFlag=1&geOrder.orderNo=" + orderNo,"网销保单详细展示" ,"top=150, left=100, width=900,height=500,toolbar=no,scrollbars=yes");
	}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>
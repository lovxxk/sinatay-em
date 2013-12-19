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
		<td class="search_head"  width="119" nowrap>支付方式代码</td>
		<td class="search_head"  width="184" nowrap>支付方式名称</td>
		<td class="search_head" width="49"  nowrap>网关</td>
		<td class="search_head"  width="169" nowrap>创建时间 </td>
		<td class="search_head" nowrap style="min-width:100">更新时间 </td>
	</tr>
	<c:forEach items="${page.result}" var="gePayment" begin="0" end="${page.totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td style="text-align: center;" width="38px;" class="search_body_center">${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td style="text-align: center;" class="search_body" width="119" n><a href="javascript:paymentDetialOpenWindow('${ctx}/productManage/paymentDetail.do?paymentId=${gePayment.paymentId}');">${gePayment.paymentCode}</a></td>
			<td style="text-align: center;" class="search_body" width="184" >${gePayment.paymentName}</td>
			<td style="text-align: center;" class="search_body" width="49" >${gePayment.gateId}</td>
			<td style="text-align: center;" class="search_body"  width="169" >
				<fmt:formatDate value="${gePayment.createTime}" pattern="yyyy-MM-dd HH:mm"/>
			</td>
			<td style="text-align: center;" class="search_body" nowrap >
			<div style="width:140">
				<fmt:formatDate value="${gePayment.updateTime}" pattern="yyyy-MM-dd HH:mm"/>
			</div>
			</td>
		</tr>
	</c:forEach>
	<c:if test="${page.totalCount == 0}">
		<tr>
			<td colspan="4">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
	</c:if>
	</table>
	
</body>
<script type="text/javascript">
	window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
	
	function paymentDetialOpenWindow(obj) {
		window.open(obj, "支付方式详细", "top=50, left=50, width=900, height=600, scrollbars, resizable=yes");
		
}
	
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>

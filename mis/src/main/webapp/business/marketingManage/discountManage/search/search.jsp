<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/mis_basic.css" rel="stylesheet" type="text/css">
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="td_head" width="30" nowrap><input type="checkbox" onclick="checkAll(this)"></td>
		<td class="td_head" nowrap id="idNum">序号</td>
		<td class="td_head" nowrap>活动代码</td>
		<td class="td_head" nowrap>电子商务产品ID</td>
		<td class="td_head" nowrap>折扣</td>
		<td class="td_head" nowrap>打折起止日期</td>
		<td class="td_head" nowrap>打折终止日期</td>
	</tr>
	<c:forEach items="${page.result}" var="geDiscountManage" begin="0" end="${page.totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}">
			<td class="td_body_center" nowrap>
				<input type="checkbox" name="checkChild" id="check${status.index}" onclick="checkSingleRow();" value="${geDiscountManage.discountid}">
			</td>
			<td class="td_body_center" nowrap>${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="td_body" nowrap>${geDiscountManage.discountid}</td>
			<td class="td_body" nowrap>${geDiscountManage.eid}</td>
			<td class="td_body" nowrap>${geDiscountManage.discount}</td>
			<td class="td_body_center" nowrap>${geDiscountManage.discountstartdate}</td>
			<td class="td_body_center" nowrap>${geDiscountManage.discountenddate}</td>
		</tr>
	</c:forEach>
	</table>
	
</body>
<script type="text/javascript">
	window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>

<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" width="30" nowrap>&nbsp;</td>
		<td class="search_head" nowrap id="idNum">���</td>
		<td class="search_head" nowrap>���������ƷID</td>
		<td class="search_head" nowrap>��Ʒ����/���ִ���</td>
		<td class="search_head" nowrap>��Ʒ����</td>
		<td class="search_head" nowrap>ҵ������</td>
		<td class="search_head" nowrap>�Ƿ�����</td>
		<td class="search_head" nowrap>��������</td>
		<td class="search_head" nowrap>��Ʒ���¼�</td>
		<td class="search_head" nowrap>��Ʒ��Ŀ</td>
		<td class="search_head" nowrap>��������</td>
		<td class="search_head" nowrap>ͣ������</td>
	</tr>
	<c:forEach items="${page.result}" var="geDirectory" begin="0" end="${page.totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" nowrap>
				<input type="radio" name="checkChild" id="check${status.index}" onclick="checkSingleRow();" value="${geDirectory.eid},${geDirectory.productName},${xrule},${yaddShopping}">
			</td>
			<td class="search_body_center" nowrap>${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body" nowrap>${geDirectory.eid}</td>
			<td class="search_body" nowrap>${geDirectory.coreProductCode}</td>
			<td class="search_body" width="200px" style="white-space: normal;word-wrap:break-word;" nowrap>${geDirectory.productName}</td>
			<td class="search_body" nowrap>
				<c:if test="${geDirectory.businessArea=='1'}">����</c:if>
				<c:if test="${geDirectory.businessArea=='2'}">����</c:if>
				<c:if test="${geDirectory.businessArea=='3'}">����</c:if>
				<c:if test="${geDirectory.businessArea=='4'}">������</c:if>
				<c:if test="${geDirectory.businessArea=='9'}">����</c:if>
			</td>
			<td id="td_${geDirectory.eid}_isNetSale" class="search_body" nowrap>
				<c:if test="${geDirectory.isNetSale=='01'}">����</c:if>
				<c:if test="${geDirectory.isNetSale=='02'}">������</c:if>
			</td>
			<td class="search_body" nowrap>
			<c:if test="${geDirectory.saleChannel=='01'}">����</c:if>
			<c:if test="${geDirectory.saleChannel=='02'}">��ͳ</c:if>
			<c:if test="${geDirectory.saleChannel=='03'}">����</c:if>
			<c:if test="${geDirectory.saleChannel=='04'}">������</c:if>
			</td>
			<td class="search_body" nowrap>
				<c:if test="${geDirectory.isProductShelf=='01'}">�ϼ�</c:if>
				<c:if test="${geDirectory.isProductShelf=='02'}">�¼�</c:if>
			</td>
			<td id="td_${geDirectory.eid}_productSection" class="search_body" nowrap>
				<c:if test="${geDirectory.productSection=='01'}">����</c:if>
				<c:if test="${geDirectory.productSection=='02'}">��ҵ</c:if>
			</td>
			<td class="search_body" nowrap>${geDirectory.publishDate}</td>
			<td class="search_body" nowrap>${geDirectory.stopDate}</td>
		</tr>
	</c:forEach>
	</table>
	
</body>
<script type="text/javascript">
	window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>

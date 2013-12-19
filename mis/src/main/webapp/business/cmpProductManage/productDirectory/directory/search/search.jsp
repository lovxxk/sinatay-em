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
		<td class="search_head" width="38" nowrap id="idNum">���</td>
		<td class="search_head" width="123" nowrap>���������ƷID</td>
		<td class="search_head" width="106" nowrap>��Ʒ����/���ִ���</td>
		<td class="search_head" width="100" nowrap>��Ʒ����</td>
		<td class="search_head" width="100" nowrap>��Ʒ��� </td>
		<td class="search_head" width="52" nowrap>ҵ������</td>
		<td class="search_head" width="52" nowrap>�Ƿ�����</td>
		<td class="search_head" width="52" nowrap>��������</td>
		<td class="search_head" width="64" nowrap>��Ʒ���¼�</td>
		<td class="search_head" width="52" nowrap>��Ʒ��Ŀ</td>
		<td class="search_head" width="64" nowrap>��������</td>
		<td class="search_head"nowrap>ͣ������</td>
	</tr>
	<c:forEach items="${page.result}" var="geDirectory" begin="0" end="${page.totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" width="38">${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body" width="123"><a href="javascript:productDetialOpenWindow('${ctx}/productDirectory/productDetail.do?geDirectory.eid=${geDirectory.eid}');">${geDirectory.eid}</a></td>
			<td class="search_body" width="106">${geDirectory.coreProductCode}</td>
			<td class="search_body" width="100" style="white-space: normal;word-wrap:break-word;">${geDirectory.productName}</td>
			<td class="search_body" width="100" style="white-space: normal;word-wrap:break-word;">${geDirectory.coreProductSimpleName}</td>
			<td class="search_body" width="52">
				<c:forEach items="${businessAreaList }" var="businessArea">
					<c:if test="${geDirectory.businessArea==businessArea.id.codeCode}">${businessArea.codeCName }</c:if>
				</c:forEach>
			</td>
			<td id="search_${geDirectory.eid}_isNetSale" class="search_body" width="52">
				<c:if test="${geDirectory.isNetSale=='01'}">����</c:if>
				<c:if test="${geDirectory.isNetSale=='02'}">������</c:if>
			</td>
			<td class="search_body" width="52">
				<c:forEach items="${saleChannelList }" var="saleChannel">
					<c:if test="${geDirectory.saleChannel==saleChannel.id.codeCode}">${saleChannel.codeCName }</c:if>
				</c:forEach>
			</td>
			<td class="search_body_center" width="64">
				<c:if test="${geDirectory.isProductShelf=='01'}">�ϼ�</c:if>
				<c:if test="${geDirectory.isProductShelf=='02'}">�¼�</c:if>
			</td>
			<td id="search_${geDirectory.eid}_productSection" class="search_body_center" width="52">
				<c:forEach items="${productSectionList }" var="productSection">
					<c:if test="${geDirectory.productSection==productSection.id.codeCode}">${productSection.codeCName }</c:if>
				</c:forEach>
			</td>
			<td class="search_body_center" width="64">${geDirectory.publishDate}</td>
			
			<td class="search_body_center">
			<div style="width:70">${geDirectory.stopDate}
			</div>
			</td>
			
		</tr>
	</c:forEach>
	</table>
	
</body>
<script type="text/javascript">
	window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
	
	function productDetialOpenWindow(obj) {
		window.open(obj, "��Ʒ��ϸ", " width=900, height=600, scrollbars, resizable=yes");
	}
	
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>

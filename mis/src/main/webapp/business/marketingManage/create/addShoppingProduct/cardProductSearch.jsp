<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" width="30" nowrap>&nbsp;</td>
		<td class="search_head" nowrap id="idNum">序号</td>
		<td class="search_head" nowrap>卡产品代码</td>
		<td class="search_head" nowrap>卡产品名称</td>
		<td class="search_head" nowrap>卡类型</td>
		<td class="search_head" nowrap>业务领域</td>
	</tr>
	 
	<c:forEach items="${page.result}" var="cardProduct" begin="0" end="${page.totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}" class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" nowrap>
				<input type="radio"" name="checkChild" id="check${status.index}" onclick="checkSingleRow('${status.index}');" value="${cardProduct.eid},${cardProduct.cardProductName},${xrule},${yaddShopping}">
			</td>
			<td class="search_body_center" nowrap>${status.index+1+page.pageSize*(page.currentPageNo-1)}</td>
			<td class="search_body_center" nowrap>${cardProduct.id.cardProductCode}</td>
			<td class="search_body_center" nowrap>${cardProduct.cardProductName}</td>
			<td class="search_body_center" nowrap>${cardProduct.id.cardType}</td>
			<td class="search_body_center" nowrap>
				<c:forEach items="${geCodeBusinessAreaList }" var="businessArea">
					<c:if test="${cardProduct.businessArea==businessArea.id.codeCode}">${businessArea.codeCName }</c:if>
				</c:forEach>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
<script type="text/javascript">
	window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>

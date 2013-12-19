<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
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
			<td class="search_head" width="30" nowrap>&nbsp;</td>
			<td class="search_head" nowrap id="idNum">���</td>
			<td class="search_head" nowrap>���״���</td>
			<td class="search_head" nowrap>��������</td>
			<td class="search_head" nowrap>�ͻ����û�ID </td>
			<td class="search_head" nowrap>��������</td>
			<td class="search_head" nowrap>ҵ��������</td>
			<td class="search_head" nowrap>����ʱ��</td>
			<td class="search_head" nowrap>����ʱ��</td>
		</tr>
		<c:if test="${page.totalCount == 0}">
		<tr>
			<td colspan="8">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
		</c:if>

		<c:forEach items="${page.result}" var="interfaceInfo" begin="0" end="${page.totalCount}" step="1" varStatus="st">
			<tr id="tr_${st.index}" class="${st.index%2 == 0?'':'search_tr_ou'}">
				<td class="search_body_center" nowrap>
					<input type="radio"" name="checkChild" id="check${st.index}" onclick="checkSingleRow('${st.index}');" value="${interfaceInfo.transCode}">
				</td>
				<td class="search_body_center" nowrap>${st.index+1+page.pageSize*(page.currentPageNo-1)}</td>
				<td class="search_body_center" nowrap>${interfaceInfo.transCode}</td>
				<td class="search_body_center" nowrap>${interfaceInfo.transName}</td>
				<td class="search_body_center" nowrap>${#interfaceInfo.clientUser.id}</td>
				<td class="search_body_center" nowrap>
					<c:if test="${interfaceInfo.transType=='1'}">�ͻ��ˣ������ⲿ����</c:if>
					<c:if test="${interfaceInfo.transType=='2'}">����ˣ�Ϊ�ⲿ�ṩ����</c:if>
				</td>
				<td class="search_body_center" nowrap>${interfaceInfo.businessArea}</td>
				<td class="search_body_center" nowrap>${interfaceInfo.createDate}</td>
				<td class="search_body_center" nowrap>${interfaceInfo.updateDate}</td>
			</tr>
		</c:forEach>		
	</table>
</body>
<script type="text/javascript">
//��ϸ
window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${page.currentPageNo}&pageSize=${page.pageSize}&totalCount=${page.totalCount}&totalPage=${page.totalPageCount}";
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
<script type="text/javascript">
</script>
</html>
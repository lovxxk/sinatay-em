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
		<td class="search_head" width="30px;" nowrap>���</td>
		<td class="search_head" width="151px;" nowrap>��������</td>
		<!--<td class="search_head" nowrap>���ͱ�־</td>-->
		<td class="search_head"  width="125px;" nowrap>��������</td>
		<td class="search_head"  width="241px;" nowrap>Ӣ������</td>
		<td class="search_head"   width="125px;" nowrap>��������</td>
		<td class="search_head"  nowrap><div style="width:50">ҵ������</div></td>
		<!--<td class="search_head" nowrap>�Ƿ���Ч</td>-->
	</tr>
	<c:forEach items="${geCodeTypeList}" var="geCodeType" begin="0" end="${totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}"  class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center"  width="30px;" >${status.index+1+pg.pageSize*(pageNo-1)}</td>
			<td class="search_body"  width="151px;" >
				<input type="hidden"  id="id_${status.index}" name="id_${status.index}" value="${geCodeType.codeType}">
				<input type="hidden"  id="idName_${status.index}" name="idName_${status.index}" value="${geCodeType.codeTypeCDesc}">
				${geCodeType.codeType}
			</td>
			<!--
			<td class="search_body_center" nowrap>
			<c:if test="${geCodeType.typeInd=='0'}">��ͨ�������ͣ�������л����������ã�</c:if>
			<c:if test="${geCodeType.typeInd=='1'}">����������ͣ���������л����������ã�</c:if>
			</td>
			-->
			<td class="search_body"  width="125px;" ><a href="javascript:doEdit('id_${status.index}')">${geCodeType.codeTypeCDesc}</a></td>
			<td class="search_body"  width="200px;" >${geCodeType.codeTypeEDesc}</td>
			<td class="search_body" width="125px;"  >${geCodeType.codeTypeTDesc}</td>
			<td class="search_body"   ><c:forEach items="${bussList}" var="bussArea" step="1" varStatus="status">
					<c:if test="${geCodeType.businessArea==bussArea.id.codeCode}">
						${bussArea.codeCName}
					</c:if>
				</c:forEach></td>
			
			<!--<td class="search_body_center" nowrap>
			<c:if test="${geCodeType.validInd=='0'}">��Ч</c:if>
			<c:if test="${geCodeType.validInd=='1'}">��Ч</c:if>
			</td>
			-->
		</tr>
	</c:forEach>
	<c:if test="${empty geCodeTypeList || totalCount == 0}">
		<tr>
			<td colspan="7">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
	</c:if>
	</table>
</body>
<script type="text/javascript">
	//���¼���page.jspҳ��
	window.parent.frames[3].document.location.href = "${ctx}/global/inc/page.jsp?pageNo=${pageNo}&pageSize="
											+"${pageSize}&totalPage=${totalPage}&totalCount=${totalCount}" ;
	function doEdit(obj){
		var idStr = document.getElementById(obj).value;
		window.open("${ctx}/business/businessManage/dataDictionary/queryForShowType.do?geCodeType.codeType=" + idStr,"�����ֵ�������ϸ��Ϣ" ,"top=100, left=100, width=900,height=400,toolbar=no");		
	}
</script>
<script type="text/javascript" src="${ctx}/global/js/checkRows.js"></script>
</html>

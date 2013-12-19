<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="geCode" value="${requestScope.geCodeForShow}" />
<c:set var="geCodeType" value="${requestScope.geCodeType}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>���������̨����ϵͳ-�����ֵ�������ϸ</title>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			<span id="title">�����ֵ�������ϸ</span>
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<table class="table_style" align="center" width="90%" id="geCodeTable">
	<tr>
		<td class="td_head" width="200px" nowrap>�������ͣ�</td>
		<td class="td_body" width="200px">${geCodeType.codeType}</td>
		<td class="td_head" width="100px" nowrap>����������</td>
		<td class="td_body" width="200px">${geCodeType.codeTypeCDesc}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>Ӣ��������</td>
		<td class="td_body" width="200px">
			${geCodeType.codeTypeEDesc}
		</td>
		<td class="td_head" width="100px" nowrap>����������</td>
		<td class="td_body" width="200px">
			${geCodeType.codeTypeTDesc}
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>ҵ������</td>
		<td class="td_body" width="200px">
			${businessArea}
		</td>
		<td class="td_head" width="100px" nowrap>&nbsp;</td>
		<td class="td_body" width="200px">
			&nbsp;
		</td>
	</tr>
	<tr height="10px"><td colspan=4>&nbsp;</td></tr>
	</table>
	<div style="overflow-y:auto;height:365px;width:90% ">
	<table width="100%" class="table_Show" id="data_table">
	<tr>
		<td class="search_head" width="40px" nowrap id="idNum">���</td>
		<td class="search_head" width="140px" nowrap>����</td>
		<td class="search_head" width="50px" nowrap>��ʾ���</td>
		<td class="search_head" width="160px" nowrap>����������</td>
		<td class="search_head" width="160px" nowrap>����Ӣ����</td>
		<td class="search_head" width="160px" nowrap>���뷱����</td>
		<td class="search_head" width="50px" nowrap>�Ƿ���Ч</td>
	</tr>
	<c:forEach items="${geCodeList}" var="geCode" begin="0" end="${totalCount}" step="1" varStatus="status">
		<tr id="tr_${status.index}"  class="${status.index%2 == 0?'':'search_tr_ou'}">
			<td class="search_body_center" nowrap>${status.index+1+pageSize*(pageNo-1)}</td>
			<td class="search_body" style="white-space: normal;word-wrap:break-word; ">
				${geCode.id.codeCode}
				<input type="hidden" id="id_${status.index}" value="${geCode.id.codeCode},${geCode.id.codeType}">
			</td>
			<td class="search_body_center" >${geCode.orderNo}</td>
			<td class="search_body"><a href="javascript:doEdit('id_${status.index}')">${geCode.codeCName}</a></td>
			<td class="search_body">${geCode.codeEName}</td>
			<td class="search_body">${geCode.codeTName}</td>
			<td class="search_body_center" nowrap>
			<c:if test="${geCode.validInd=='0'}">��Ч</c:if>
			<c:if test="${geCode.validInd=='1'}">��Ч</c:if>
			</td>
			
		</tr>
	</c:forEach>
	<c:if test="${empty geCodeList || totalCount == 0}">
		<tr>
			<td colspan="7">
				<jsp:include page="/global/ui/noResult.jsp"></jsp:include>
			</td>
		</tr>
	</c:if>
	
	</table>
	</div>
	<table>
	<tr>
		<td colspan=4>
			<table align="center">
			<tr>
			<acc:showView source="ROLE_S_DDIC_I">
			<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'"   onclick="doCreate();" nowrap>�½����� </td>
			</acc:showView>	
			<td id="updateButton" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClose();" nowrap>�ر�</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</div>
<script type="text/javascript">
function doClose(){
	window.close();
}
function doCreate(){
	window.open("${ctx}/system/configManage/dataDictionary/search/searchSon/frmCreate.jsp?geCodeType.codeType=${geCodeType.codeType}&geCodeType.codeTypeCDesc=${geCodeType.codeTypeCDesc}","�½��������ֵ������µ������ֵ�" ,"top=100, left=100, width=800,height=600,toolbar=no");		
	
}
function doEdit(obj){
	var idStr = document.getElementById(obj).value;
	window.open("${ctx}/business/businessManage/dataDictionary/queryForShow.do?geCode.id.codeType=" + idStr,"�����ֵ���ϸ��Ϣ" ,"top=100, left=100, width=900,height=400,toolbar=no");		
}
</script>
</body>
</html>

<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="geCode" value="${requestScope.geCodeForShow}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>���������̨����ϵͳ-�����ֵ���ϸ</title>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			<span id="title">�����ֵ���ϸ</span>
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<table class="table_style" align="center" width="90%" id="geCodeTable">
	<tr>
		<td class="td_head" width="200px" nowrap>���룺</td>
		<td class="td_body" width="200px">${geCode.id.codeCode}</td>
		<td class="td_head" width="100px" nowrap>�������ͣ�</td>
		<td class="td_body" width="200px">${codeAndDescMap[geCode.id.codeType]}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>��ʾ��ţ�</td>
		<td class="td_body" width="200px">
			${geCode.orderNo}
		</td>
		<td class="td_head" width="100px" nowrap>������������</td>
		<td class="td_body" width="200px">
			${geCode.codeCName}
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>����Ӣ������</td>
		<td class="td_body" width="200px">
			${geCode.codeEName}
		</td>
		<td class="td_head" width="100px" nowrap>���뷱������</td>
		<td class="td_body" width="200px">
			${geCode.codeTName}
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�Ƿ���Ч��</td>
		<td class="td_body" width="200px">
			<c:if test="${geCode.validInd eq '1'}">��Ч</c:if>
			<c:if test="${geCode.validInd eq '0'}">��Ч</c:if>
		</td>
		<td class="td_head" width="100px" nowrap>&nbsp;</td>
		<td class="td_body" width="200px">&nbsp;</td>
	</tr>
	<tr height="10px"><td colspan=4>&nbsp;</td></tr>
	<tr>
		<td colspan=4>
			<table width=200 align="center">
			<tr>
				<acc:showView source="ROLE_S_DDIC_U">
					<td id="updateButton" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="toUpdate();" nowrap>�༭</td>
					<input type="hidden" id="updateFlag" value="1">
				</acc:showView>
				<acc:showView source="ROLE_S_DDIC_D">
					<td id="updateButton" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doDelete();" nowrap>ɾ��</td>
				</acc:showView>
				<td id="updateButton" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClose();" nowrap>�ر�</td>
				<td>&nbsp;</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</div>
<script type="text/javascript">
function doDelete(){
	if(confirm("ȷ��ɾ���������ֵ���")){
	window.location = "${ctx}/business/businessManage/dataDictionary/deleteGeCodeById.do?geCode.id.codeCode=${geCode.id.codeCode}&geCode.id.codeType=${geCode.id.codeType}";
	}
}
function toUpdate(){
	window.location = "${ctx}/business/businessManage/dataDictionary/queryForUpdate.do?geCode.id.codeType=${geCode.id.codeCode},${geCode.id.codeType}";
}

function doClose(){
	window.close();
}
</script>
</body>
</html>

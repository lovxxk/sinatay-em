<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"></link>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css"></link>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>���������̨����ϵͳ-�ͻ����û���ϸ��Ϣ</title>
<style type="text/css">
 td{
 	vertical-align:top;
 }
 input, select {
 	width:200px;
 }
 #submitTable {
 	margin-left: 200px;
 	margin-top: 20px;
 }
 #submitTable td {
 	width:85px;
 	vertical-align:middle;
 }
 #frmInput {
 	padding-top:10px;
 }
 textarea {
 	margin-bottom:4px;
 }
</style>
</head>
<body>
	<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				�ͻ����û���ϸ��Ϣ
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<table class="table_style" cellpadding="0" cellspacing="0" align="center" id="geFunctionSwitchTable">
			<tr>
				<td class="td_head">�ⲿϵͳ���ƣ� </td>
				<td class="td_body">${clientUser.externalSysInfo.externalSysName}</td>
			</tr>
			<tr>
				<td class="td_head">�˺ţ� </td>
				<td class="td_body">${clientUser.loginName}</td>
			</tr>
			<tr>
				<td class="td_head">���룺 </td>
				<td class="td_body">${clientUser.password}</td>
			</tr>
			<tr>
				<td class="td_head">�˻�״̬��</td>
				<td class="td_body">
					<c:choose>
						<c:when test="${clientUser.status == '0'}">
							����
						</c:when>
						<c:when test="${clientUser.status == '1'}">
							ͣ��
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="td_head">����Ա���������� </td>
				<td class="td_body">${clientUser.esbauthbranchno}</td>
			</tr>
			<tr>
				<td class="td_head">����ǩ���� </td>
				<td class="td_body">${clientUser.esbsignature}</td>
			</tr>
			<tr>
				<td class="td_head">����ʱ�䣺 </td>
				<td class="td_body">${clientUser.createDate}</td>
			</tr>			
			<tr>
				<td class="td_head">����ʱ�䣺 </td>
				<td class="td_body">${clientUser.updateDate}</td>
			</tr>			
			<tr>
				<td class="td_head">��ע��</td>
				<td class="td_body"><textarea readonly id="remark" name="clientUser.remark" cols="46" rows="6" >${clientUser.remark}</textarea></td>
			</tr>
			<tr>
				<td colspan="2" >
					<table id="submitTable">
						<tr>
							<acc:showView source="ROLE_PYAMENT_EDIT">
								<td onclick="doEdit('${clientUser.id}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">�༭</td>
								<td onclick="doDelete();" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">ɾ��</td>
								<td id="closeButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap>�ر�</td>
							</acc:showView>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<form id="frmInput2" action="${ctx }/interfacePortal/deleteClientUser.do" target="myFrame" method="post">
			<input type="hidden" id="id" name="id" value="${clientUser.id}">
		</form>	
		<iframe id="myFrame" name="myFrame" style="display: none"></iframe>	
	</div>
<script type="text/javascript">
//�༭
function doEdit(id){
	location.href = "${ctx}/interfacePortal/searchUniqueClientUser.do?clientUser.id=" + id ;
}
function doDelete(){
	document.getElementById("frmInput2").submit();
}
</script>
</body>
</html>

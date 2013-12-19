<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"></link>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css"></link>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>���������̨����ϵͳ-�ͻ����û��ͽӿڼ��ϵ��Ϣά��</title>
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
 .open_title_c_new{
 	height:41px;
 	width:280px;
 	background:url(${ctx}/global/images/face/open_title_word_bg.jpg) repeat-x;
 	float:left;
}
</style>
</head>
<body>
	<div id="open_titleDIV">
		<div class="open_title_c_new">
			<div class="open_title">
				�ͻ����û��ͽӿڼ��ϵ��Ϣά��
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<form name="frmInput" id="frmInput" action="${ctx}/interfacePortal/updateInterfaceInfo.do" method="post" enctype="multipart/form-data" target="myFrame">
			<table class="table_style" cellpadding="0" cellspacing="0" align="center" id="geFunctionSwitchTable">
				<tr>
					<td class="td_head" width="200px" nowrap>���״��룺</td>
					<td class="td_body" width="350px">${interfaceInfo.transCode}</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>�������ƣ�</td>
					<td class="td_body" width="350px">${interfaceInfo.transName}
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>�������ͣ�</td>
					<td class="td_body">
						<select id="transType" name="interfaceInfo.transType" style="width: 170px;" onchange="displayTransType()">
							<option value="K" selected>�ͻ��ˣ������ⲿ����</option>
							<option value="F">����ˣ�Ϊ�ⲿ�ṩ����</option>
						</select>
					</td>
				</tr>
				<tr id="tr_clientUserid">
					<td class="td_head" width="200px" nowrap>�ͻ����û�ID��</td>
					<td class="td_body" width="350px">
						<input type="text" id="id" name="interfaceInfo.clientUser.id" value="${interfaceInfo.clientUser.id}" maxlength="50" />
						<span id='id_msg'><font color='red' style='padding-left:2mm;'>˫�����ⲿϵͳID</font></span>
					</td>
				</tr>				
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>ҵ��������</td>
					<td class="td_body" width="350px">
						<input type="text" id="businessArea" name="interfaceInfo.businessArea" value="${interfaceInfo.businessArea}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>�ӿ�״̬��</td>
					<td class="td_body">
						<select id="status" name="interfaceInfo.status" style="width: 170px;">
							<option value="" selected>--��ѡ��--</option>
								<option value="1" <c:if test="${interfaceInfo.status=='1'}">selected</c:if>>����</option>
								<option value="2" <c:if test="${interfaceInfo.status=='2'}">selected</c:if>>ͣ��</option>
						</select>
					</td>
				</tr>
				<tr  style="display:none;">
					<td class="td_head" width="200px" nowrap>���¼��־��</td>
					<td class="td_body">
						<select id="isRecordMessage" name="interfaceInfo.isRecordMessage" style="width: 170px;">
							<option value="" selected>--��ѡ��--</option>
								<option value="1" <c:if test="${interfaceInfo.isRecordMessage=='1'}">selected</c:if>>��</option>
								<option value="2" <c:if test="${interfaceInfo.isRecordMessage=='2'}">selected</c:if>>��</option>
						</select>
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>���Ĵ������ͣ�</td>
					<td class="td_body" width="350px">
						<input type="text" id="handleMessageType" name="interfaceInfo.handleMessageType" value="${interfaceInfo.handleMessageType}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>�Ǽ������ƣ�</td>
					<td class="td_body" width="350px">
						<input type="text" id="className" name="interfaceInfo.className" value="${interfaceInfo.className}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>����˷�������ַ��</td>
					<td class="td_body" width="350px">
						<input type="text" id="requestURL" name="interfaceInfo.requestURL" value="${interfaceInfo.requestURL}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>�����ռ�URL��</td>
					<td class="td_body" width="350px">
						<input type="text" id="namespaceURL" name="interfaceInfo.namespaceURL" value="${interfaceInfo.namespaceURL}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>���񷽷�����</td>
					<td class="td_body" width="350px">
						<input type="text" id="localPart" name="interfaceInfo.localPart" value="${interfaceInfo.localPart}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>���񷽷�����������</td>
					<td class="td_body" width="350px">
						<input type="text" id="localPartParameterNumber" name="interfaceInfo.localPartParameterNumber" value="${interfaceInfo.localPartParameterNumber}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>���֧�ּ��ܣ�</td>
					<td class="td_body">
						<select id="isEncryption" name="isEncryption" style="width: 88px;" onchange="javascript:displayEncryption();">
							<option value="0" selected>��</option>
							<option value="1">��</option>
						</select>		
					</td>
				</tr>				
				<tr id="tr_encryptionMethod" style="display:none;">
					<td class="td_head" width="200px" nowrap>���ܷ�ʽ ��</td>
					<td class="td_body" width="350px">
						<input type="text" id="encryptionMethod" name="interfaceInfo.encryptionMethod" value="${interfaceInfo.encryptionMethod}" maxlength="50" />
					</td>
				</tr>
				<tr id="tr_encryptionKey" style="display:none;">
					<td class="td_head" width="200px" nowrap>��Կ��</td>
					<td class="td_body" width="350px">
						<input type="text" id="encryptionKey" name="interfaceInfo.encryptionKey" value="${interfaceInfo.encryptionKey}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>����ʱ�䣺</td>
					<td class="td_body" width="350px">${interfaceInfo.createDate}</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>����ʱ�䣺</td>
					<td class="td_body" width="350px">${interfaceInfo.updateDate}</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>��ע��</td>
					<td class="td_body">
						<textarea id="remark" name="interfaceInfo.remark" cols="46" rows="6" >${interfaceInfo.remark}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table id="submitTable">
							<tr>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/interfacePortal/viewInterfaceInfoDetailNew.do?interfaceInfo.transCode=${interfaceInfo.transCode}';">����</td>
								<td id="addButtonSubmit" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" nowrap>�޸�</td>
								<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" id="transCode" name="interfaceInfo.transCode" value="${interfaceInfo.transCode}"/>
			<input type="hidden" id=createDate name="interfaceInfo.createDate" value="${interfaceInfo.createDate}"/>
		</form>
		<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</div>
<script type="text/javascript">
function doClear(){
	document.getElementById("frmInput").reset();
}
</script>
<script type="text/javascript">
//У��
//��Բ�ͬ�����������ʽ��֤
$(document).ready(function(){
	$("#addButtonSubmit").click(function(){
		$("#frmInput").submit();
	});
	//˫���󶨿ͻ����û�ID
	$("#id").dblclick(function(){
		window.open("${ctx}/system/interfacePortalManage/interfaceInfo/create/selectClientUserIdList/index.jsp", "��ѯ�ͻ����û���Ϣ", "top=100, left=100, width=900,height=600,toolbar=no");
	});
});
</script>
</body>
</html>

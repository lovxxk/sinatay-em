<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"></link>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css"></link>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>���������̨����ϵͳ-�ӿ���ϸ��Ϣ</title>
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
				�ӿ���ϸ��Ϣ
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<table class="table_style" cellpadding="0" cellspacing="0" align="center" id="geFunctionSwitchTable">

	<tr>
		<td class="td_head" width="200px" nowrap>���״��룺</td>
		<td class="td_body" width="350px">${interfaceInfo.transCode}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�������ƣ�</td>
		<td class="td_body" width="350px">${interfaceInfo.transName}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�������ͣ�</td>
		<td class="td_body">
			<c:choose>
				<c:when test='${interfaceInfo.transType == "K"}'>�ͻ���</c:when>
				<c:when test='${interfaceInfo.transType == "F"}'>�����</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>			
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>ҵ������</td>
		<td class="td_body" width="350px">${interfaceInfo.businessArea}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�ӿ�״̬��</td>
		<td class="td_body">
			<c:choose>
				<c:when test="${interfaceInfo.status == '1'}">
					����
				</c:when>
				<c:when test="${interfaceInfo.status == '2'}">
					ͣ��
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>			
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�Ƿ��¼��־��</td>
		<td class="td_body">
			<c:choose>
				<c:when test="${interfaceInfo.isRecordMessage == 'Y'}">
					��
				</c:when>
				<c:when test="${interfaceInfo.isRecordMessage != 'Y'}">
					��
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>			
		</td>
	</tr>
	<%--
	<tr>
		<td class="td_head" width="200px" nowrap>���Ĵ������ͣ�</td>
		<td class="td_body" width="350px">${interfaceInfo.handleMessageType}</td>
	</tr>
	 --%>
	<tr>
		<td class="td_head" width="200px" nowrap>�Ǽ������ƣ�</td>
		<td class="td_body" width="350px">${interfaceInfo.className}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>����˷�������ַ��</td>
		<td class="td_body" width="350px">${interfaceInfo.requestURL}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�����ռ�URL��</td>
		<td class="td_body" width="350px">${interfaceInfo.namespaceURL}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>���񷽷�����</td>
		<td class="td_body" width="350px">${interfaceInfo.localPart}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>���񷽷�����������</td>
		<td class="td_body" width="350px">${interfaceInfo.localPartParameterNumber}</td>
	</tr>
	<c:if test="${not empty interfaceInfo.encryptionMethod}">
	<tr id="tr_encryptionMethod">
		<td class="td_head" width="200px" nowrap>���ܷ�ʽ ��</td>
		<td class="td_body" width="350px">${interfaceInfo.encryptionMethod}</td>
	</tr>
	<tr id="tr_encryptionKey">
		<td class="td_head" width="200px" nowrap>��Կ��</td>
		<td class="td_body" width="350px">${interfaceInfo.encryptionKey}</td>
	</tr>
	</c:if>
	<tr>
		<td class="td_head" width="200px" nowrap>�ӿڰ汾��</td>
		<td class="td_body" width="350px">${interfaceInfo.esbserviceversion}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>esb�ӿڷ������ƣ�</td>
		<td class="td_body" width="350px">${interfaceInfo.esbSvcName}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>��Դϵͳ���룺</td>
		<td class="td_body" width="350px">${interfaceInfo.esborisys}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>·�ɻ������룺</td>
		<td class="td_body" width="350px">${interfaceInfo.esbroutebranchno}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>·��Ŀ��ϵͳ��ʶ��</td>
		<td class="td_body" width="350px">${interfaceInfo.esbroutedestsys}</td>
	</tr>
	
	<tr>
		<td class="td_head" width="200px" nowrap>��ע��</td>
		<td class="td_body"><textarea readonly id="remark" name="clientUser.remark" cols="46" rows="6" >${interfaceInfo.remark}</textarea></td>
	</tr>
			<tr>
				<td colspan="2" >
					<table id="submitTable">
						<tr>
							<acc:showView source="ROLE_PYAMENT_EDIT">
								<td onclick="doEdit('${interfaceInfo.transCode}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
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
		<form id="frmInput2" action="${ctx }/interfacePortal/deleteInterfaceInfo.do" target="myFrame" method="post">
			<input type="hidden" id="transCode" name="interfaceInfo.transCode" value="${interfaceInfo.transCode}">
		</form>	
		<iframe id="myFrame" name="myFrame" style="display: none"></iframe>	
	</div>
<script type="text/javascript">
//�༭
function doEdit(transCode){
	location.href = "${ctx}/interfacePortal/searchInterfaceInfo.do?interfaceInfo.transCode=" + transCode ;
}
function doDelete(){
	document.getElementById("frmInput2").submit();
}
</script>
</body>
</html>

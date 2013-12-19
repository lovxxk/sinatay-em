<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"></link>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css"></link>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>���������̨����ϵͳ-���ױ�����ϸ��Ϣ</title>
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
				���ױ�����Ϣ
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<table class="table_style" cellpadding="0" cellspacing="0" align="center" id="geFunctionSwitchTable">
			<tr>
				<td class="td_head">������ˮ�ţ� </td>
				<td class="td_body">${transactionMessage.transRefGuid}</td>
			</tr>
			<tr>
				<td class="td_head">�ⲿϵͳID�� </td>
				<td class="td_body">${transactionMessage.externalSysInfo.externalSysId}</td>
			</tr>
			<tr>
				<td class="td_head">���״��룺 </td>
				<td class="td_body">${transactionMessage.transCode}</td>
			</tr>
			<tr>
				<td class="td_head">����ʱ�䣺 </td>
				<td class="td_body">${transactionMessage.transTime}</td>
			</tr>			
			<tr>
				<td class="td_head">����ʱ�䣺 </td>
				<td class="td_body">${transactionMessage.requestTime}</td>
			</tr>			
			<tr>
				<td class="td_head">�����ģ�</td>
				<td class="td_body"><textarea readonly id="remark" name="transactionMessage.requestMessage" cols="46" rows="6" >${transactionMessage.requestMessage}</textarea></td>
			</tr>
			<tr>
				<td class="td_head">Ӧ��ʱ�䣺 </td>
				<td class="td_body">${transactionMessage.responseTime}</td>
			</tr>			
			<tr>
				<td class="td_head">Ӧ���ģ�</td>
				<td class="td_body"><textarea readonly id="remark" name="transactionMessage.responseMessage" cols="46" rows="6" >${transactionMessage.responseMessage}</textarea></td>
			</tr>
			<tr>
				<td class="td_head">�����ⲿϵͳ����ʱ�䣺 </td>
				<td class="td_body">${transactionMessage.frontRequestTime}</td>
			</tr>			
			<tr>
				<td class="td_head">�ⲿϵͳ�����ģ�</td>
				<td class="td_body"><textarea readonly id="remark" name="transactionMessage.frontRequestMessage" cols="46" rows="6" >${transactionMessage.frontRequestMessage}</textarea></td>
			</tr>
									<tr>
				<td class="td_head">�ⲿϵͳӦ��ʱ�䣺 </td>
				<td class="td_body">${transactionMessage.frontResponeTime}</td>
			</tr>			
			<tr>
				<td class="td_head">�ⲿϵͳӦ���ģ�</td>
				<td class="td_body"><textarea readonly id="remark" name="transactionMessage.frontResponseMessage" cols="46" rows="6" >${transactionMessage.frontResponseMessage}</textarea></td>
			</tr>
			<tr>
				<td colspan="2" >
					<table id="submitTable">
						<tr>
							<acc:showView source="ROLE_PYAMENT_EDIT">
								<td id="closeButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap>�ر�</td>
							</acc:showView>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<iframe id="myFrame" name="myFrame" style="display: none"></iframe>	
	</div>
<script type="text/javascript">
</script>
</body>
</html>

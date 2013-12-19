<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>���������̨����ϵͳ-�½��ӿ���Ϣ</title>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<link href="${ctx }/business/cmpProductManage/productManage/update/css/product.css" rel="stylesheet" type="text/css" >
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link href="${ctx }/global/css/stpess.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#transCode").blur(function(){
		var transCode = $("#transCode").val();
		$.ajax({
			url : "${ctx}/interfacePortal/isHaveTransCode.do",
			data : {
				"transCode" : transCode
			},//�������
			type : 'POST',
			dataType : 'json',
			error : function() {
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#transCode").val("");
					$("#transCodeContent").remove();
					$("#transCode").parent().append("<span id='transCodeContent'><span class='talentErrMsg'>�ý��״����Ѿ����ڣ����޸�!</span></span>")
				} else {
					$("#transCodeContent").remove();
				}
			}
		});
	});
	$("#transCode").focus(function(){
		$("#transCodeContent").remove();
	});
	//�������ֵ��ȡ���Ĵ�������
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalGeCodeAjax.do",
		type : 'POST',
		dataType : 'json',
		data : {"codeType":"HandleMessageType"},
		error : function() {
		},
		success : function(data) {
			$("#handleMessageType").empty();
			$("#handleMessageType").append("<option value='' selected='selected'>--��ѡ��--</option>");
			for(var i = 0; i < data.length ; i++){
				$("#handleMessageType").append("<option value='" + data[i][0]+ "' >" + data[i][1] + "</option>");
			}
		}
	});
	//�������ֵ��ȡ���Ĵ�������
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalGeCodeAjax.do",
		type : 'POST',
		dataType : 'json',
		data : {"codeType":"EncryptionMethod"},
		error : function() {
		},
		success : function(data) {
			$("#encryptionMethod").empty();
			$("#encryptionMethod").append("<option value='' selected='selected'>--��ѡ��--</option>");
			for(var i = 0; i < data.length ; i++){
				$("#encryptionMethod").append("<option value='" + data[i][0]+ "' >" + data[i][1] + "</option>");
			}
		}
	});
});
function interfaceInfoFmSubmit(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}
function interfaceInfoFmReset(){
	document.getElementById("frmInput").reset();
}
$(function (){
	var addResult = "${addResult}";
	if (addResult == "success") {
		alert("�ӿ���Ϣ�����ɹ���");
		parent.document.location.href = "${ctx}/system/interfacePortalManage/interfaceInfo/create/index.jsp";
	} else if (addResult == "failure") {
		alert("�ӿ���Ϣ����ʧ�ܣ�");
	}	
});
</script>
</head>
<body>
<div class="public_fun_title">
	�½��ӿ���Ϣ
</div>
<div style="height: 10px"></div>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/interfacePortal/saveInterfaceInfo.do" target="myFrame">
<table align="center" style="width:650px;" id="productTable" >
	<tr>
		<td class="td_head" width="200px" nowrap>���״��룺</td>
		<td class="td_body" width="400px">
			<input type="text" id="transCode" name="interfaceInfo.transCode" value="" maxlength="32" />
			<span id="transCode_msg"></span><br>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�������ƣ�</td>
		<td class="td_body" width="400px">
			<input type="text" id="transName" name="interfaceInfo.transName" value="" maxlength="200" />
			<span id='transName_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�������ͣ�</td>
		<td class="td_body">
			<select id="transType" name="interfaceInfo.transType" style="width: 88px;">
				<option value="K" selected>�ͻ���</option>
				<option value="F">�����</option>
			</select>		
		</td>
	</tr>		
	<tr>
		<td class="td_head" width="200px" nowrap>ҵ������</td>
		<td class="td_body" width="400px">
			<input type="text" id="businessArea" name="interfaceInfo.businessArea" value="" maxlength="255" />
			<span id='businessArea_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�ӿ�״̬��</td>
		<td class="td_body">
			<select id="status" name="interfaceInfo.status" style="width: 88px;">
				<option value="1" selected>����</option>
				<option value="2">ͣ��</option>
			</select>		
		</td>
	</tr>		
	<tr>
		<td class="td_head" width="200px" nowrap>�Ƿ��¼��־��</td>
		<td class="td_body">
			<select id="isRecordMessage" name="interfaceInfo.isRecordMessage" style="width: 88px;">
				<option value="N" >��</option>
				<option selected value="Y">��</option>
			</select>		
		</td>
	</tr>
	<%-- 
	<tr>
		<td class="td_head" width="200px" nowrap>���Ĵ������ͣ�</td>
		<td class="td_body">
			<select id="handleMessageType" name="interfaceInfo.handleMessageType" style="width: 88px;">
			</select>
		</td>
	</tr>
	--%>
	<tr>
		<td class="td_head" width="200px" nowrap>�Ǽ������ƣ�</td>
		<td class="td_body" width="420px">
			<input type="text" id="className" name="interfaceInfo.className" value="" maxlength="255" />
			<span id='className_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>����˷�������ַ��</td>
		<td class="td_body" width="350px">
			<input type="text" id="requestURL" name="interfaceInfo.requestURL" value="" maxlength="255" />
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�����ռ�URL��</td>
		<td class="td_body" width="350px">
			<input type="text" id="namespaceURL" name="interfaceInfo.namespaceURL" value="" maxlength="255" />
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>���񷽷�����</td>
		<td class="td_body" width="400px">
			<input type="text" id="localPart" name="interfaceInfo.localPart" value="" maxlength="255" />
			<span id='localPart_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>���񷽷�����������</td>
		<td class="td_body" width="350px">
			<input type="text" id="localPartParameterNumber" name="interfaceInfo.localPartParameterNumber" value="" maxlength="8" />
			<span id='localPartParameterNumber_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�Ƿ�֧�ּ��ܣ�</td>
		<td class="td_body">
			<select id="isEncryption" name="isEncryption" style="width: 88px;" onchange="javascript:displayEncryption();">
				<option value="0" selected>��</option>
				<option value="1">��</option>
			</select>		
		</td>
	</tr>
	<tr id="tr_encryptionMethod">
		<td class="td_head" width="200px" nowrap>���ܷ�ʽ ��</td>
		<td class="td_body">
			<select id="encryptionMethod" name="interfaceInfo.encryptionMethod" style="width: 88px;">
			</select>
	</tr>
	<tr id="tr_encryptionKey">
		<td class="td_head" width="200px" nowrap>��Կ��</td>
		<td class="td_body" width="350px">
			<input type="text" id="encryptionKey" name="interfaceInfo.encryptionKey" value="" maxlength="255" />
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�ӿڰ汾��</td>
		<td class="td_body" width="400px">
			<input type="text" id="esbserviceversion" name="interfaceInfo.esbserviceversion" value="" maxlength="32" />
			<span id='esbserviceversion_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>esb�ӿڷ������ƣ�</td>
		<td class="td_body" width="400px">
			<input type="text" id="esbSvcName" name="interfaceInfo.esbSvcName" value="" maxlength="80" />
			<span id='esbSvcName_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>��Դϵͳ���룺</td>
		<td class="td_body" width="400px">
			<input type="text" id="esborisys" name="interfaceInfo.esborisys" value="GROUP-EBP" maxlength="80" />
			<span id='esborisys_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>·�ɻ������룺</td>
		<td class="td_body" width="400px">
			<input type="text" id="esbroutebranchno" name="interfaceInfo.esbroutebranchno" value="" maxlength="80" />
			<span id='esbroutebranchno_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>·��Ŀ��ϵͳ��ʶ��</td>
		<td class="td_body" width="400px">
			<input type="text" id="esbroutedestsys" name="interfaceInfo.esbroutedestsys" value="" maxlength="80" />
			<span id='esbroutedestsys_msg'></span>
		</td>
	</tr>	
	<tr>
		<td class="td_head" width="200px" nowrap>��ע��</td>
		<td class="td_body">
			<textarea id="remark" name="interfaceInfo.remark" cols="46" rows="6" >${interfaceInfo.remark}</textarea>
		</td>
	</tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table align="center">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="interfaceInfoFmSubmit();" nowrap>�½�</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="interfaceInfoFmReset();" nowrap>����</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<input type="hidden" id="id" name="interfaceInfo.clientUser.id" value=""/>
</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
<script type="text/javascript">
//У��
var transName =new tt.Field("","","transName").setMsgId("transName_msg");
//new tt.RV().set(new RegExp(/^[\w-]{6,16}$/),"��6-16λ����.��ĸ.'_'��'-'���").add(transName);
var transCode =new tt.Field("","","transCode").setMsgId("transCode_msg");
var businessArea =new tt.Field("","","businessArea").setMsgId("businessArea_msg");
var localPartParameterNumber =new tt.Field("","","localPartParameterNumber").setMsgId("localPartParameterNumber_msg");
var localPart =new tt.Field("","","localPart").setMsgId("localPart_msg");
var className =new tt.Field("","","className").setMsgId("className_msg");
var esbserviceversion=new tt.Field("","","esbserviceversion").setMsgId("esbserviceversion_msg");
var esbSvcName =new tt.Field("","","esbSvcName").setMsgId("esbSvcName_msg");
var esborisys =new tt.Field("","","esborisys").setMsgId("esborisys_msg");
//��Բ�ͬ�����������ʽ��֤
tt.vf.req.add(transCode,transName,esbserviceversion,esbSvcName,esborisys);
//new tt.RV().set(new RegExp(/^[A-Za-z0-9_]+$/),"ֻ���������֡���ĸ���»���!").add(transCode,esbserviceversion);
new tt.RV().set(new RegExp(/^[0-9]+$/),"ֻ����������!").add(localPartParameterNumber);
new tt.RV().set(new RegExp(/^[A-Za-z0-9_\u0391-\uFFE5]+$/),"ֻ���������֡���ĸ���»��ߺͺ���!").add(businessArea,transName,localPart,esbSvcName);
new tt.RV().set(new RegExp(/^[A-Za-z0-9_.\u0391-\uFFE5]+$/),"ֻ���������֡���ĸ���»��ߡ�.�ͺ���!").add(className);
displayEncryption();
function displayEncryption(){
	var checkedVal = $("#isEncryption").val(); 
	if(checkedVal == '0'){
		$("#tr_encryptionMethod").hide();
		$("#tr_encryptionKey").hide();
		$("#encryptionMethod").val("");//�������ֵ
		$("#encryptionKey").val("");//�������ֵ
		tt.vf.req.rm("interfaceInfo.encryptionMethod");//����Ҫ ��������ܷ�ʽ����У��ǿ��Լ������Կ��ֵ
	}else{
		$("#tr_encryptionMethod").show();
		$("#tr_encryptionKey").show();
		tt.vf.req.add("interfaceInfo.encryptionMethod");//����Ҫ���ܣ�����ܷ�ʽΪ������
	}
}
</script>
</body>
</html>
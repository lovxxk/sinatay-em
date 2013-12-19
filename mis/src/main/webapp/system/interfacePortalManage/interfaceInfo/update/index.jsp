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
<title>���������̨����ϵͳ-�ӿ���Ϣά��</title>
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
<script type="text/javascript">
$(document).ready(function(){
	$("#addButtonSubmit").click(function(){
		if(tt.validate()){
			$("#frmInput").submit();	
		}
	});
	//˫���󶨿ͻ����û�ID
	$("#id").dblclick(function(){
		window.open("${ctx}/system/interfacePortalManage/interfaceInfo/create/selectClientUserIdList/index.jsp", "��ѯ�ͻ����û���Ϣ", "top=100, left=100, width=900,height=600,toolbar=no");
	});
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
			$("#handleMessageType").append("<option value=''>--��ѡ��--</option>");
			for(var i = 0; i < data.length ; i++){
				var handleMessageType = "${interfaceInfo.handleMessageType}";
				if(handleMessageType  == data[i][0]){
					$("#handleMessageType").append("<option value='" + data[i][0]+ "' selected='selected'>" + data[i][1] + "</option>");
				}else{
					$("#handleMessageType").append("<option value='" + data[i][0]+ "' >" + data[i][1] + "</option>");	
				}
			}
		}
	});
	//�������ֵ��ȡ���ܷ�ʽ
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalGeCodeAjax.do",
		type : 'POST',
		dataType : 'json',
		data : {"codeType":"EncryptionMethod"},
		error : function() {
		},
		success : function(data) {
			$("#encryptionMethod").empty();
			$("#encryptionMethod").append("<option value=''>--��ѡ��--</option>");
			for(var i = 0; i < data.length ; i++){
				var encryptionMethod = "${interfaceInfo.encryptionMethod}";
				if(encryptionMethod  == data[i][0]){
					$("#encryptionMethod").append("<option value='" + data[i][0]+ "' selected='selected'>" + data[i][1] + "</option>");
				}else{
					$("#encryptionMethod").append("<option value='" + data[i][0]+ "' >" + data[i][1] + "</option>");	
				}
			}
		}
	});
});
</script>
</head>
<body>
	<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				�ӿ���Ϣά��
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
					<td class="td_body" width="350px">
						<input type="text" id="transName" name="interfaceInfo.transName" value="${interfaceInfo.transName}" maxlength="200" />
						<span id='transName_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>�������ͣ�</td>
					<td class="td_body">
						<select id="transType" name="interfaceInfo.transType" style="width: 88px;">
							<option value="">--��ѡ��--</option>
								<option value="K" <c:if test='${interfaceInfo.transType=="K"}'>selected</c:if>>�ͻ���</option>
								<option value="F" <c:if test='${interfaceInfo.transType=="F"}'>selected</c:if>>�����</option>
						</select>
						<span id='transType_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>ҵ������</td>
					<td class="td_body" width="350px">
						<input type="text" id="businessArea" name="interfaceInfo.businessArea" value="${interfaceInfo.businessArea}" maxlength="255" />
						<span id='businessArea_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>�ӿ�״̬��</td>
					<td class="td_body">
						<select id="status" name="interfaceInfo.status" style="width: 88px;">
							<option value="">--��ѡ��--</option>
								<option value="1" <c:if test="${interfaceInfo.status=='1'}">selected</c:if>>����</option>
								<option value="2" <c:if test="${interfaceInfo.status=='2'}">selected</c:if>>ͣ��</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>�Ƿ��¼��־��</td>
					<td class="td_body">
						<select id="isRecordMessage" name="interfaceInfo.isRecordMessage" style="width: 88px;">
							<option value="">--��ѡ��--</option>
								<option value="Y" <c:if test="${interfaceInfo.isRecordMessage=='Y'}">selected</c:if>>��</option>
								<option value="N" <c:if test="${interfaceInfo.isRecordMessage!='Y'}">selected</c:if>>��</option>
						</select>
					</td>
				</tr>
				<%--
				<tr>
					<td class="td_head" width="200px" nowrap>���Ĵ������ͣ�</td>
					<td class="td_body" width="350px">
						<select id="handleMessageType" name="interfaceInfo.handleMessageType" style="width: 88px;">
						</select>
					</td>
				</tr>
				 --%>
				<tr>
					<td class="td_head" width="200px" nowrap>�Ǽ������ƣ�</td>
					<td class="td_body" width="350px">
						<input type="text" id="className" name="interfaceInfo.className" value="${interfaceInfo.className}" maxlength="255" />
						<span id='className_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>����˷�������ַ��</td>
					<td class="td_body" width="350px">
						<input type="text" id="requestURL" name="interfaceInfo.requestURL" value="${interfaceInfo.requestURL}" maxlength="255" />
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>�����ռ�URL��</td>
					<td class="td_body" width="350px">
						<input type="text" id="namespaceURL" name="interfaceInfo.namespaceURL" value="${interfaceInfo.namespaceURL}" maxlength="255" />
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>���񷽷�����</td>
					<td class="td_body" width="350px">
						<input type="text" id="localPart" name="interfaceInfo.localPart" value="${interfaceInfo.localPart}" maxlength="255" />
						<span id='localPart_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>���񷽷�����������</td>
					<td class="td_body" width="350px">
						<input type="text" id="localPartParameterNumber" name="interfaceInfo.localPartParameterNumber" value="${interfaceInfo.localPartParameterNumber}" maxlength="8" />
						<span id='localPartParameterNumber_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>�Ƿ�֧�ּ��ܣ�</td>
					<td class="td_body">
						<select id="isEncryption" name="isEncryption" style="width: 88px;" onchange="javascript:displayEncryption();">
							<option value="0" <c:if test='${interfaceInfo.encryptionMethod == "" || interfaceInfo.encryptionMethod == null}'>selected</c:if>>��</option>
							<option value="1" <c:if test='${interfaceInfo.encryptionMethod != "" && interfaceInfo.encryptionMethod != nill}'>selected</c:if>>��</option>
						</select>		
					</td>
				</tr>				
				<tr id="tr_encryptionMethod">
					<td class="td_head" width="200px" nowrap>���ܷ�ʽ ��</td>
					<td class="td_body" width="350px">
						<select id="encryptionMethod" name="interfaceInfo.encryptionMethod" style="width: 88px;">
						</select>
					</td>
				</tr>
				<tr id="tr_encryptionKey">
					<td class="td_head" width="200px" nowrap>��Կ��</td>
					<td class="td_body" width="350px">
						<input type="text" id="encryptionKey" name="interfaceInfo.encryptionKey" value="${interfaceInfo.encryptionKey}" maxlength="255" />
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>�ӿڰ汾��</td>
					<td class="td_body" width="350px">
						<input type="text" id="esbserviceversion" name="interfaceInfo.esbserviceversion" value="${interfaceInfo.esbserviceversion}" maxlength="60" />
						<span id='esbserviceversion_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>esb�ӿڷ������ƣ�</td>
					<td class="td_body" width="350px">
						<input type="text" id="esbSvcName" name="interfaceInfo.esbSvcName" value="${interfaceInfo.esbSvcName}" maxlength="80" />
						<span id='esbSvcName_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>��Դϵͳ���룺</td>
					<td class="td_body" width="350px">
						<input type="text" id="esborisys" name="interfaceInfo.esborisys" value="${interfaceInfo.esborisys}" maxlength="80" />
						<span id='esborisys_msg'></span>
					</td>
				</tr>		
				<tr>
					<td class="td_head" width="200px" nowrap>·�ɻ������룺</td>
					<td class="td_body" width="350px">
						<input type="text" id="esbroutebranchno" name="interfaceInfo.esbroutebranchno" value="${interfaceInfo.esbroutebranchno}" maxlength="80" />
						<span id='esbroutebranchno_msg'></span>
					</td>
				</tr>	
				<tr>
					<td class="td_head" width="200px" nowrap>·��Ŀ��ϵͳ��ʶ��</td>
					<td class="td_body" width="350px">
						<input type="text" id="esbroutedestsys" name="interfaceInfo.esbroutedestsys" value="${interfaceInfo.esbroutedestsys}" maxlength="80" />
						<span id='esbroutedestsys_msg'></span>
					</td>
				</tr>													
								
				
				<tr>
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
								onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/interfacePortal/viewInterfaceInfoDetail.do?interfaceInfo.transCode=${interfaceInfo.transCode}';">����</td>
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
	displayEncryption();
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
			$("#handleMessageType").append("<option value=''>--��ѡ��--</option>");
			for(var i = 0; i < data.length ; i++){
				var handleMessageType = "${interfaceInfo.handleMessageType}";
				if(handleMessageType  == data[i][0]){
					$("#handleMessageType").append("<option value='" + data[i][0]+ "' selected='selected'>" + data[i][1] + "</option>");
				}else{
					$("#handleMessageType").append("<option value='" + data[i][0]+ "' >" + data[i][1] + "</option>");	
				}
			}
		}
	});
	//�������ֵ��ȡ���ܷ�ʽ
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalGeCodeAjax.do",
		type : 'POST',
		dataType : 'json',
		data : {"codeType":"EncryptionMethod"},
		error : function() {
		},
		success : function(data) {
			$("#encryptionMethod").empty();
			$("#encryptionMethod").append("<option value=''>--��ѡ��--</option>");
			for(var i = 0; i < data.length ; i++){
				var encryptionMethod = "${interfaceInfo.encryptionMethod}";
				if(encryptionMethod  == data[i][0]){
					$("#encryptionMethod").append("<option value='" + data[i][0]+ "' selected='selected'>" + data[i][1] + "</option>");
				}else{
					$("#encryptionMethod").append("<option value='" + data[i][0]+ "' >" + data[i][1] + "</option>");	
				}
			}
		}
	});
}
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
tt.vf.req.add(transName,esbserviceversion,esbSvcName,esborisys);
//��Բ�ͬ�����������ʽ��֤
//new tt.RV().set(new RegExp(/^[A-Za-z0-9_]+$/),"ֻ���������֡���ĸ���»���!").add(transCode);
new tt.RV().set(new RegExp(/^[0-9]+$/),"ֻ����������!").add(localPartParameterNumber);
new tt.RV().set(new RegExp(/^[A-Za-z0-9_\u0391-\uFFE5]+$/),"ֻ���������֡���ĸ���»��ߺͺ���!").add(businessArea,transName,localPart);
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

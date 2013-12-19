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
<script type="text/javascript">
$(document).ready(function(){
	//�����ⲿϵͳ��Ϣ
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalSysInfoList.do",
		type : 'POST',
		dataType : 'json',
		error : function() {
		},
		success : function(data) {
			var externalSysId = '${clientUser.externalSysInfo.externalSysId}';
			$("#externalSysId").empty();
			$("#externalSysId").append("<option value='' selected='selected'>--��ѡ��--</option>");
			for(var i = 0; i < data.length ; i++){
				if(externalSysId == data[i].externalSysId){
					$("#externalSysId").append("<option value='" + data[i].externalSysId+ "'  selected='selected'>" + data[i].externalSysName + "</option>");	
				}else{
					$("#externalSysId").append("<option value='" + data[i].externalSysId+ "' >" + data[i].externalSysName + "</option>");	
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
				�ͻ����û���ϸ��Ϣ
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<form name="frmInput" id="frmInput" action="${ctx}/interfacePortal/updateClientUser.do" method="post" enctype="multipart/form-data" target="myFrame">
			<table class="table_style" cellpadding="0" cellspacing="0" align="center" id="geFunctionSwitchTable">
				<tr>
					<td class="td_head">�ⲿϵͳ���ƣ� </td>
					<td class="td_body">
						<select id="externalSysId" name="clientUser.externalSysInfo.externalSysId" style="width: 170px;">
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_head">�˺ţ�</td>
					<td class="td_body">
						<input id="loginName" name="clientUser.loginName" type="text" style="width:170px;" value="${clientUser.loginName}" maxlength="32" />
						<span id="loginName_msg"></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>���룺</td>
					<td  class="td_body">
						<input id="password" name="clientUser.password" type="password" style="width:170px;" value="${clientUser.password}" class=required maxlength=16>
						<span id='pwd_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" nowrap>ȷ�����룺</td>
					<td  class="td_body">
						<input id="pwdAgain" name="pwdAgain"  type="password" style="width:170px;" value="${clientUser.password}" class=required maxlength=16>
						<span id='pwdAgain_msg'></span>
					</td>
				</tr>				
				<tr>
					<td class="td_head">�˻�״̬��</td>
					<td class="td_body">
						<select id="externalSysType" name="clientUser.status" style="width:170px;">
							<option value="">--��ѡ��--</option>
							<option value="0" <c:if test="${clientUser.status=='0'}">selected</c:if>>����</option>
							<option value="1" <c:if test="${clientUser.status=='1'}">selected</c:if>>ͣ��</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_head">����Ա����������</td>
					<td class="td_body">
						<input id="ffff" name="clientUser.esbauthbranchno" type="text" style="width:170px;" value="${clientUser.esbauthbranchno}" maxlength="60" />
					</td>
				</tr>
				<tr>
					<td class="td_head">����ǩ����</td>
					<td class="td_body">
						<input id="gggg" name="clientUser.esbsignature" type="text" style="width:170px;" value="${clientUser.esbsignature}" maxlength="200" />
					</td>
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
					<td class="td_body"><textarea id="remark" name="clientUser.remark" cols="46" rows="6" maxlength="320">${clientUser.remark}</textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<table id="submitTable">
							<tr>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/interfacePortal/viewClientUserDetail.do?clientUser.id=${clientUser.id}';">����</td>
								<td id="addButtonSubmit" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" nowrap>�޸�</td>
								<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" id="id" name="clientUser.id" value="${clientUser.id}"/>
			<input type="hidden" id="createDate" name="clientUser.createDate" value="${clientUser.createDate}"/>
			<input type="hidden" id="createDate" name="clientUser.createDate" value="${clientUser.createDate}"/>
		</form>
		<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</div>
<script type="text/javascript">
function doClear(){
	document.getElementById("frmInput").reset();
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalSysInfoList.do",
		type : 'POST',
		dataType : 'json',
		error : function() {
		},
		success : function(data) {
			var externalSysId = '${clientUser.externalSysInfo.externalSysId}';
			$("#externalSysId").empty();
			$("#externalSysId").append("<option value='' selected='selected'>--��ѡ��--</option>");
			for(var i = 0; i < data.length ; i++){
				if(externalSysId == data[i].externalSysId){
					$("#externalSysId").append("<option value='" + data[i].externalSysId+ "'  selected='selected'>" + data[i].externalSysName + "</option>");	
				}else{
					$("#externalSysId").append("<option value='" + data[i].externalSysId+ "' >" + data[i].externalSysName + "</option>");	
				}
			}
		}
	});
}

</script>
<script type="text/javascript">
//У��
var password =new tt.Field("","","password").setMsgId("pwd_msg");
var pwdAgain =new tt.Field("","","pwdAgain").setMsgId("pwdAgain_msg");
var loginName =new tt.Field("","","loginName").setMsgId("loginName_msg");
var externalSysId =new tt.Field("","","externalSysId").setMsgId("externalSysId_msg");
//�ǿ���֤
tt.vf.req.add(loginName,externalSysId);
//��Բ�ͬ�����������ʽ��֤
new tt.RV().set(new RegExp(/^[A-Za-z0-9_\u0391-\uFFE5]+$/),"ֻ���������֡���ĸ���»��ߺͺ���!").add(loginName);
new tt.CV().add(pwdAgain).set('v', "==", password,false);
new tt.RV().set(new RegExp(/^[\w-]{6,16}$/),"��6-16λ����.��ĸ.'_'��'-'���").add(password);

$(document).ready(function(){
	$("#addButtonSubmit").click(function(){
		if(tt.validate()==false){
			return;
		}else{
			$("#frmInput").submit();
		}
	});
	$("#loginName,#externalSysId").blur(function(){
		var loginName = $("#loginName").val();
		var externalSysId = $("#externalSysId").val();
		//У������Ψһ��
		$.ajax({
			url : "${ctx}/interfacePortal/isHaveExIdAndName.do",
			data : {
				"loginName" : loginName,
				"externalSysId" : externalSysId
			},//�������
			type : 'POST',
			dataType : 'json',
			error : function() {
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#loginName").val("");
					$("#loginNameContent").remove();
					$("#loginName").parent().append("<span id='loginNameContent'><span class='talentErrMsg'>�˺ź��ⲿϵͳ�����ظ�!</span></span>")
				} else {
					$("#loginNameContent").remove();
				}
			}
		});
	});
	$("#loginName").focus(function(){
		$("#loginNameContent").remove();
	});
});
</script>

</body>
</html>

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
<title>���������̨����ϵͳ-������û���Ϣά��</title>
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
	width:345px;
	background:url(${ctx}/global/images/face/open_title_word_bg.jpg) repeat-x;
	float:left;
 
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
			var externalSysId = '${externalSystemsUser.externalSysInfo.externalSysId}';
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
		<div class="open_title_c_new">
			<div class="open_title">
				������û���Ϣά��
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<form name="frmInput" id="frmInput" action="${ctx}/interfacePortal/updateExternalSystemsUser.do" method="post" enctype="multipart/form-data" target="myFrame">
			<table class="table_style" cellpadding="0" cellspacing="0" align="center" id="geFunctionSwitchTable">
				<tr>
					<td class="td_head">�ⲿϵͳ���ƣ� </td>
					<td class="td_body">
						<select id="externalSysId" name="externalSystemsUser.externalSysInfo.externalSysId" style="width: 170px;">
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_head">�˺ţ�</td>
					<td class="td_body">
						<input id="loginName" name="externalSystemsUser.loginName" type="text" style="width:170px;" value="${externalSystemsUser.loginName}" maxlength="32" />
						<span id="loginName_msg"></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>���룺</td>
					<td  class="td_body">
						<input id="password" name="externalSystemsUser.password" type="password" style="width:170px;" value="${externalSystemsUser.password}" class=required maxlength=16>
						<span id='pwd_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" nowrap>ȷ�����룺</td>
					<td  class="td_body">
						<input id="pwdAgain" name="pwdAgain"  type="password" style="width:170px;" value="${externalSystemsUser.password}" class=required maxlength=16>
						<span id='pwdAgain_msg'></span>
					</td>
				</tr>				
				<tr>
					<td class="td_head">�û�IP��ַ��</td>
					<td class="td_body">
						<input id="ipAddress" name="externalSystemsUser.ipAddress" type="text" style="width:170px;" value="${externalSystemsUser.ipAddress}" maxlength="32" />
						<span id="ipAddress_msg"></span>
					</td>
				</tr>				
				<tr>
					<td class="td_head">�˻�״̬��</td>
					<td class="td_body">
						<select id="externalSysType" name="externalSystemsUser.status" style="width:170px;">
							<option value="">--��ѡ��--</option>
							<option value="0" <c:if test="${externalSystemsUser.status=='0'}">selected</c:if>>����</option>
							<option value="1" <c:if test="${externalSystemsUser.status=='1'}">selected</c:if>>ͣ��</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_head">����ʱ�䣺 </td>
					<td class="td_body">${externalSystemsUser.createDate}</td>
				</tr>			
				<tr>
					<td class="td_head">����ʱ�䣺 </td>
					<td class="td_body">${externalSystemsUser.updateDate}</td>
				</tr>			
				<tr>
					<td class="td_head">��ע��</td>
					<td class="td_body"><textarea id="remark" name="externalSystemsUser.remark" cols="46" rows="6" >${externalSystemsUser.remark}</textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<table id="submitTable">
							<tr>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/interfacePortal/viewExternalSystemsUserDetail.do?externalSystemsUser.loginName=${externalSystemsUser.loginName}';">����</td>
								<td id="addButtonSubmit" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" nowrap>�޸�</td>
								<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" id="id" name="externalSystemsUser.id" value="${externalSystemsUser.id}"/>
			<input type="hidden" id="createDate" name="externalSystemsUser.createDate" value="${externalSystemsUser.createDate}"/>
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
			var externalSysId = '${externalSystemsUser.externalSysInfo.externalSysId}';
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
var loginName =new tt.Field("","","loginName").setMsgId("loginName_msg");
var externalSysId =new tt.Field("","","externalSysId").setMsgId("externalSysId_msg");
var password =new tt.Field("","","password").setMsgId("pwd_msg");
var pwdAgain =new tt.Field("","","pwdAgain").setMsgId("pwdAgain_msg");
var ipAddress =new tt.Field("","","ipAddress").setMsgId("ipAddress_msg");
//�ǿ���֤
tt.vf.req.add(loginName,externalSysId);
//��Բ�ͬ�����������ʽ��֤
new tt.RV().set(new RegExp(/^[A-Za-z0-9_]+$/),"ֻ�������֡���ĸ���»���!").add(loginName);
new tt.CV().add(pwdAgain).set('v', "==", password,false);
new tt.RV().set(new RegExp(/^[\w-]{6,16}$/),"��6-16λ����.��ĸ.'_'��'-'���").add(password);
new tt.RV().set(new RegExp(/^[A-Za-z0-9.]+$/),"ֻ�������֡���ĸ�͵�!").add(ipAddress);
$(document).ready(function(){
	$("#addButtonSubmit").click(function(){
		if(tt.validate()==false){
			return;
		}else{
			$("#frmInput").submit();
		}
	});
	$("#loginName").blur(function(){
		var loginName = $("#loginName").val();
		var ipAddress = $("#ipAddress").val();
		//У������Ψһ��
		$.ajax({
			url : "${ctx}/interfacePortal/isHaveIpAddressAndName.do",
			data : {
				"loginName" : loginName,
				"ipAddress" : ipAddress
			},//�������
			type : 'POST',
			dataType : 'json',
			error : function() {
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#loginName").val("");
					$("#loginNameContent").remove();
					$("#loginName").parent().append("<span id='loginNameContent'><span class='talentErrMsg'>ͬһIP������ͬһ��¼��!</span></span>")
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

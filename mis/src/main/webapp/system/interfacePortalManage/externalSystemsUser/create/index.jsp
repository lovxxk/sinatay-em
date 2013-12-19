<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>���������̨����ϵͳ-�½����������û���Ϣ</title>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<link href="${ctx }/business/cmpProductManage/productManage/update/css/product.css" rel="stylesheet" type="text/css" >
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link href="${ctx }/global/css/stpess.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#submit").click(function(){
		if(tt.validate()==false){
			return;
		}else{
			$("#frmInput").submit();
		}
	});
	//�����ⲿϵͳ��Ϣ
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalSysInfoList.do",
		type : 'POST',
		dataType : 'json',
		error : function() {
		},
		success : function(data) {
			$("#externalSysId").empty();
			$("#externalSysId").append("<option value='' selected='selected'>--��ѡ��--</option>");
			for(var i = 0; i < data.length ; i++){
				$("#externalSysId").append("<option value='" + data[i].externalSysId+ "' >" + data[i].externalSysName + "</option>");
			}
		}
	});
	$("#ipAddress").blur(function(){
		checkUnique();
	});
	$("#loginName").blur(function(){
		checkUnique();
	});
	$("#loginName").focus(function(){
		$("#loginNameContent").remove();
	});
});

function checkUnique(){
	var loginName = $("#loginName").val();
	var ipAddress = $("#ipAddress").val();
	//У������Ψһ��
	$.ajax({
		url : "${ctx}/interfacePortal/isHaveIpAndName.do",
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
}
function externalSysInfoUserFmSubmit(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}
function externalSysInfoFmReset(){
	document.getElementById("frmInput").reset();
}
$(function (){
	externalSysInfoFmReset();
	var addResult = "${addResult}";
	if (addResult == "success") {
		alert("���������û������ɹ���");
		parent.document.location.href = "${ctx}/system/interfacePortalManage/externalSystemsUser/create/index.jsp";
	} else if (addResult == "failure") {
		alert("���������û�����ʧ�ܣ�");
	}	
});

</script>
</head>
<body>
<div class="public_fun_title">
	�½����������û���Ϣ
</div>
<div style="height: 10px"></div>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/interfacePortal/saveExternalSystemsUser.do" target="myFrame">
<table align="center" style="width:650px;" id="productTable" >
	<tr>
		<td class="td_head" width="200px" nowrap>�ⲿϵͳ���ƣ�</td>
		<td class="td_body">
			<select id="externalSysId" name="externalSystemsUser.externalSysInfo.externalSysId" style="width:170px;">
			</select>
			<span id='externalSysId_msg'></span>
		</td>
	</tr>	
	<tr>
		<td class="td_head" width="200px" nowrap>�˺ţ�</td>
		<td class="td_body" width="400px">
			<input type="text" id="loginName" name="externalSystemsUser.loginName" value="" maxlength="32" />
			<span id="loginName_msg"></span></br>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>���룺</td>
		<td  class="td_body">
			<input id="password" name="externalSystemsUser.password" type="password" style="width:170px;" value="" class=required maxlength=16>
			<span id='pwd_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>ȷ�����룺</td>
		<td  class="td_body">
			<input id="pwdAgain" name="pwdAgain"  type="password" style="width:170px;" value="" class=required maxlength=16>
			<span id='pwdAgain_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�û�IP��ַ��</td>
		<td class="td_body" width="350px">
			<input type="text" id="ipAddress" name="externalSystemsUser.ipAddress" value="" maxlength="32" />
			<span id='ipAddress_msg'></span>
		</td>
	</tr>		
	<tr>
		<td class="td_head" width="200px" nowrap>�˻�״̬��</td>
		<td class="td_body">
			<select id="status" name="externalSystemsUser.status" style="width: 88px;">
				<option value="0" selected>����</option>
				<option value="1">ͣ��</option>
			</select>		
		</td>
	</tr>	
	<tr>
		<td class="td_head" width="200px" nowrap>��ע��</td>
		<td class="td_body">
			<textarea id="remark" name="externalSystemsUser.remark" cols="46" rows="6" ></textarea>
		</td>
	</tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table align="center">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="externalSysInfoUserFmSubmit();" nowrap>�½�</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="externalSysInfoFmReset();" nowrap>����</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
<script type="text/javascript">
//У��
var password =new tt.Field("","","password").setMsgId("pwd_msg");
var pwdAgain =new tt.Field("","","pwdAgain").setMsgId("pwdAgain_msg");
var loginName =new tt.Field("","","loginName").setMsgId("loginName_msg");
var externalSysId =new tt.Field("","","externalSysId").setMsgId("externalSysId_msg");
var ipAddress =new tt.Field("","","ipAddress").setMsgId("ipAddress_msg");
//�ǿ���֤
tt.vf.req.add(loginName,externalSysId);
//��Բ�ͬ�����������ʽ��֤
new tt.CV().add(pwdAgain).set('v', "==", password,false);
new tt.RV().set(new RegExp(/^[\w-]{6,16}$/),"��6-16λ����.��ĸ.'_'��'-'���").add(password);
new tt.RV().set(new RegExp(/^[A-Za-z0-9_]+$/),"ֻ���������֡���ĸ���»���!").add(loginName);
new tt.RV().set(new RegExp(/^[A-Za-z0-9.]+$/),"ֻ���������֡���ĸ�͵�!").add(ipAddress);
</script>
</body>
</html>
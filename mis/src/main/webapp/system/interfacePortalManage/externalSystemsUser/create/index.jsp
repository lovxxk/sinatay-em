<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>电子商务后台管理系统-新建服务器端用户信息</title>
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
	//读入外部系统信息
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalSysInfoList.do",
		type : 'POST',
		dataType : 'json',
		error : function() {
		},
		success : function(data) {
			$("#externalSysId").empty();
			$("#externalSysId").append("<option value='' selected='selected'>--请选择--</option>");
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
	//校验联合唯一性
	$.ajax({
		url : "${ctx}/interfacePortal/isHaveIpAndName.do",
		data : {
			"loginName" : loginName,
			"ipAddress" : ipAddress
		},//传入参数
		type : 'POST',
		dataType : 'json',
		error : function() {
		},
		success : function(data) {
			if (data.resultFlag == "success") {
				$("#loginName").val("");
				$("#loginNameContent").remove();
				$("#loginName").parent().append("<span id='loginNameContent'><span class='talentErrMsg'>同一IP不能有同一登录名!</span></span>")
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
		alert("服务器端用户创建成功！");
		parent.document.location.href = "${ctx}/system/interfacePortalManage/externalSystemsUser/create/index.jsp";
	} else if (addResult == "failure") {
		alert("服务器端用户创建失败！");
	}	
});

</script>
</head>
<body>
<div class="public_fun_title">
	新建服务器端用户信息
</div>
<div style="height: 10px"></div>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/interfacePortal/saveExternalSystemsUser.do" target="myFrame">
<table align="center" style="width:650px;" id="productTable" >
	<tr>
		<td class="td_head" width="200px" nowrap>外部系统名称：</td>
		<td class="td_body">
			<select id="externalSysId" name="externalSystemsUser.externalSysInfo.externalSysId" style="width:170px;">
			</select>
			<span id='externalSysId_msg'></span>
		</td>
	</tr>	
	<tr>
		<td class="td_head" width="200px" nowrap>账号：</td>
		<td class="td_body" width="400px">
			<input type="text" id="loginName" name="externalSystemsUser.loginName" value="" maxlength="32" />
			<span id="loginName_msg"></span></br>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>密码：</td>
		<td  class="td_body">
			<input id="password" name="externalSystemsUser.password" type="password" style="width:170px;" value="" class=required maxlength=16>
			<span id='pwd_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>确认密码：</td>
		<td  class="td_body">
			<input id="pwdAgain" name="pwdAgain"  type="password" style="width:170px;" value="" class=required maxlength=16>
			<span id='pwdAgain_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>用户IP地址：</td>
		<td class="td_body" width="350px">
			<input type="text" id="ipAddress" name="externalSystemsUser.ipAddress" value="" maxlength="32" />
			<span id='ipAddress_msg'></span>
		</td>
	</tr>		
	<tr>
		<td class="td_head" width="200px" nowrap>账户状态：</td>
		<td class="td_body">
			<select id="status" name="externalSystemsUser.status" style="width: 88px;">
				<option value="0" selected>启用</option>
				<option value="1">停用</option>
			</select>		
		</td>
	</tr>	
	<tr>
		<td class="td_head" width="200px" nowrap>备注：</td>
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
						onmouseout="this.className='btnBigOnBlur'" onclick="externalSysInfoUserFmSubmit();" nowrap>新建</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="externalSysInfoFmReset();" nowrap>重置</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
<script type="text/javascript">
//校验
var password =new tt.Field("","","password").setMsgId("pwd_msg");
var pwdAgain =new tt.Field("","","pwdAgain").setMsgId("pwdAgain_msg");
var loginName =new tt.Field("","","loginName").setMsgId("loginName_msg");
var externalSysId =new tt.Field("","","externalSysId").setMsgId("externalSysId_msg");
var ipAddress =new tt.Field("","","ipAddress").setMsgId("ipAddress_msg");
//非空验证
tt.vf.req.add(loginName,externalSysId);
//针对不同需求的正则表达式验证
new tt.CV().add(pwdAgain).set('v', "==", password,false);
new tt.RV().set(new RegExp(/^[\w-]{6,16}$/),"由6-16位数字.字母.'_'或'-'组成").add(password);
new tt.RV().set(new RegExp(/^[A-Za-z0-9_]+$/),"只能输入数字、字母和下划线!").add(loginName);
new tt.RV().set(new RegExp(/^[A-Za-z0-9.]+$/),"只能输入数字、字母和点!").add(ipAddress);
</script>
</body>
</html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<title>CMS管理系统-修改密码</title>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
</head>
<body topmargin="0" leftmargin="0" onload="pageValidate();">
<div id="open_titleDIV">
	<div class="open_title_c" style="width:163px;">
		<div class="open_title">
			修改密码
		</div>
	</div>
	<div class="open_title_gap1">&nbsp;</div>
</div>
<form id="frmInput" action="${ctx}/system/userManage/updatePassword.do" method="post" target="myFrame">
<div style="padding-left:120px;">
<table align="center" width="550px" style="padding-top:40px;">
<tr>
	<td class="td_head" width="120" nowrap>原密码：</td>
	<td class="td_body"><input type="password" style="width:170px;" id="oldPassword" name="oldPassword" maxlength="20"></td>
</tr>
<tr>
	<td class="td_head" nowrap>新密码：</td>
	<td class="td_body"><input type="password" style="width:170px;" id="newPassword" name="newPassword" maxlength="20"></td>
</tr>
<tr>
	<td class="td_head" nowrap>确认新密码：</td>
	<td class="td_body"><input type="password" style="width:170px;" id="newPasswordAgain" name="newPasswordAgain" maxlength="20"></td>
</tr>
<tr height="60px;">
	<td colspan="2" style="padding-right:100px;">
		<table align="center" style="width:200px;">
		<tr>
			<td class="btnBigOnFocus" id="modifyPasswordButton"  onclick="doUpdate();" nowrap>确认</td>
			<td class="btnBigOnFocus" nowrap>取消</td>
		</tr>
		</table>
	</td>
</tr>
</table>
</div>

<input type="hidden" name="operatorid" value="${geOperator.operatorid}"/>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
function doUpdate(){
	if(tt.validate()){
		var newPassword = document.getElementById("newPassword").value;
		var pwdAgain = document.getElementById("newPasswordAgain").value;
		if(newPassword != pwdAgain){
			alert("新密码输入不一致");
			return false;
		}
		document.getElementById("frmInput").submit();
	}
}

function pageValidate(){
	tt.vf.req.add("oldPassword","newPassword","newPasswordAgain");
	//var newPassword = document.getElementById("newPassword");
	new tt.RV().set(new RegExp(/^[\w-]{6,16}$/),"由6-16位数字、字母、'_'或'-'组成").add("newPassword");
}
</script>
</html>

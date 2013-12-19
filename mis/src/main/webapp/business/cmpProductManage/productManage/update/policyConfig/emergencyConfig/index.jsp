<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=GBK">
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<title>电子商务管理系统-紧急联系人配置项</title>
<script type="text/javascript">
$(document).ready(function(){
	  // 在这里写你的代码...
	  init();
	  $("input[type='checkbox']").addClass("checkbox_border");
});

function init(){
	// 紧急联系人配置
	// 姓名
	var emerName = "<s:property value='geProductEmergencyConfig.emerName' />"
	if(emerName==2){
		$("input[id='geProductEmergencyConfig.emerName']").attr("checked",true);
		document.getElementById("geProductEmergencyConfig.emerName.value").disabled = false;
		$("input[id='geProductEmergencyConfig.emerName.value']").attr("checked",true);
		$("input[id='geProductEmergencyConfig.emerName']").attr("value","2");
	}else if(emerName==1){
		$("input[id='geProductEmergencyConfig.emerName']").attr("checked", true);
		document.getElementById("geProductEmergencyConfig.emerName.value").disabled = false;
		$("input[id='geProductEmergencyConfig.emerName']").attr("value","1");
	}
	// 手机
	var mobile = "<s:property value='geProductEmergencyConfig.mobile' />"
	if(mobile==2){
		$("input[id='geProductEmergencyConfig.mobile']").attr("checked",true);
		document.getElementById("geProductEmergencyConfig.mobile.value").disabled = false;
		$("input[id='geProductEmergencyConfig.mobile.value']").attr("checked",true);
		$("input[id='geProductEmergencyConfig.mobile']").attr("value","2");
	}else if(mobile==1){
		$("input[id='geProductEmergencyConfig.mobile']").attr("checked", true);
		document.getElementById("geProductEmergencyConfig.mobile.value").disabled = false;
		$("input[id='geProductEmergencyConfig.mobile']").attr("value","1");
	}
	// 电子邮箱
	var emerEmail = "<s:property value='geProductEmergencyConfig.emerEmail' />"
	if(emerEmail==2){
		$("input[id='geProductEmergencyConfig.emerEmail']").attr("checked",true);
		document.getElementById("geProductEmergencyConfig.emerEmail.value").disabled = false;
		$("input[id='geProductEmergencyConfig.emerEmail.value']").attr("checked",true);
		$("input[id='geProductEmergencyConfig.emerEmail']").attr("value","2");
	}else if(emerEmail==1){
		$("input[id='geProductEmergencyConfig.emerEmail']").attr("checked", true);
		document.getElementById("geProductEmergencyConfig.emerEmail.value").disabled = false;
		$("input[id='geProductEmergencyConfig.emerEmail']").attr("value","1");
	}
	
}

function showRequeriedIterm(obj){
	if(obj.checked == true){
		obj.value = "1";
		document.getElementById(obj.id+".value").disabled = false;
	}else{
		obj.value = "0";
		document.getElementById(obj.id+".value").checked = false;
		document.getElementById(obj.id+".value").disabled = true;
	}
}

function changeValue(obj){
	if(obj.checked == true){
		document.getElementById(obj.id.replace(".value","")).value = "2";
	}else{
		document.getElementById(obj.id.replace(".value","")).value = "1";
	}
}
</script>
</head>
<body>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/productManage/saveOrUpdateEmergencyConfig.do" target="myFrame">
<input type="hidden" id="geProductMain.coreProductCode" name="geProductMain.coreProductCode" value="<s:property value='geProductMain.coreProductCode' />">
	<div style=" width: 550px;margin: 0 auto;padding-top: 20px;">
		<!--  紧急联系人配置项  -->
		<c:if test="${geProductMain.isSupportEmergency eq '01'}">
			<table>
				<tr>
					<td width="130px"><input type=checkbox id="geProductEmergencyConfig.emerName"name="geProductEmergencyConfig.emerName" value="0" onclick="showRequeriedIterm(this);">姓名&nbsp;&nbsp;</td>
					<td width="130px">必填： <input type=checkbox id="geProductEmergencyConfig.emerName.value" onclick="changeValue(this);" disabled></td>
					<td width="30">&nbsp;</td>
					<td width="130px"><input type=checkbox id="geProductEmergencyConfig.mobile"name="geProductEmergencyConfig.mobile" value="0"onclick="showRequeriedIterm(this);">移动电话&nbsp;&nbsp;</td>
					<td width="130px">必填： <input type=checkbox id="geProductEmergencyConfig.mobile.value" onclick="changeValue(this);" disabled></td>
				</tr>
				<tr>
					<td><input type=checkbox id="geProductEmergencyConfig.emerEmail" name="geProductEmergencyConfig.emerEmail" value="0" onclick="showRequeriedIterm(this);">电子邮箱&nbsp;&nbsp;</td>
					<td>必填： <input type=checkbox id="geProductEmergencyConfig.emerEmail.value" onclick="changeValue(this);" disabled></td>
				</tr>
			</table>
		</c:if>
		<div>
			<table style="align:center;margin: 0 auto;margin-top: 15px;">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>保存</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.parent.parent.close();" nowrap >关闭</td>
				</tr>
			</table>
		</div>
	</div>
</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">
	function doCreate(){
		document.getElementById("frmInput").submit();
	}
	function doClear(){
		document.getElementById("frmInput").reset();
	}
</script>
</html>

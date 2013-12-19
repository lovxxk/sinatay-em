<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>电子商务后台管理系统-新建外部系统信息</title>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<link href="${ctx }/business/cmpProductManage/productManage/update/css/product.css" rel="stylesheet" type="text/css" >
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link href="${ctx }/global/css/stpess.css" rel="stylesheet" type="text/css" >

<script type="text/javascript">
$(document).ready(function(){
	tt.vf.req.add("externalSysInfo.externalSysId","externalSysInfo.externalSysName");
	$("#submit").click(function(){
		if(tt.validate()==false){
			return;
		}else{
			$("#frmInput").submit();
		}
	});
	//从数据字典读取外部系统类型
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalGeCodeAjax.do",
		type : 'POST',
		dataType : 'json',
		data : {"codeType":"ExternalSysType"},
		error : function() {
		},
		success : function(data) {
			$("#externalSysType").empty();
			for(var i = 0; i < data.length ; i++){
				if(i==0){
					$("#externalSysType").append("<option value='" + data[i][0]+ "' selected='selected'>" + data[i][1] + "</option>");	
				}else{
					$("#externalSysType").append("<option value='" + data[i][0]+ "'>" + data[i][1] + "</option>");	
				}
			}
		}
	});
	$("#externalSysId").blur(function(){
		var externalSysId = $("#externalSysId").val();
		$.ajax({
			url : "${ctx}/interfacePortal/isHaveExternalSysId.do",
			data : {
				"externalSysId" : externalSysId
			},//传入参数
			type : 'POST',
			dataType : 'json',
			error : function() {
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#externalSysId").val("");
					$("#externalSysIdContent").remove();
					$("#externalSysId").parent().append("<span id='externalSysIdContent'><span class='talentErrMsg'>该外部系统ID已经存在，请修改!</span></span>")
				} else {
					$("#externalSysIdContent").remove();
				}
			}
		});
	});
	$("#externalSysId").focus(function(){
		$("#externalSysIdContent").remove();
	});
	$("#externalSysName").blur(function(){
		var externalSysName = $("#externalSysName").val();
		$.ajax({
			url : "${ctx}/interfacePortal/isHaveExternalSysName.do",
			data : {
				"externalSysName" : externalSysName
			},//传入参数
			type : 'POST',
			dataType : 'json',
			error : function() {
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#externalSysName").val("");
					$("#externalSysNameContent").remove();
					$("#externalSysName").parent().append("<span id='externalSysNameContent'><span class='talentErrMsg'>该外部系统名称已经存在，请修改!</span></span>")
				} else {
					$("#externalSysNameContent").remove();
				}
			}
		});
	});
	$("#externalSysName").focus(function(){
		$("#externalSysNameContent").remove();
	});
});
function externalSysInfoFmSubmit(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}
function externalSysInfoFmReset(){
	document.getElementById("frmInput").reset();
}
$(function (){
	var addResult = "${addResult}";
	if (addResult == "success") {
		alert("外部系统信息创建成功！");
		parent.document.location.href = "${ctx}/system/interfacePortalManage/externalSysInfo/create/index.jsp";
	} else if (addResult == "failure") {
		alert("外部系统信息创建失败！");
	}	
});
</script>
</head>
<body>
<div class="public_fun_title">
	新建外部系统信息
</div>
<div style="height: 10px"></div>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/interfacePortal/saveExternalSysInfo.do" target="myFrame">
<table align="center" style="width:650px;" id="productTable" >
	<tr>
		<td class="td_head" width="200px" nowrap>外部系统主键：</td>
		<td class="td_body" width="400px">
			<input type="text" id="externalSysId" name="externalSysInfo.externalSysId" value="" maxlength="32" />
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>外部系统名称：</td>
		<td class="td_body" width="400px">
			<input type="text" id="externalSysName" name="externalSysInfo.externalSysName" value="" maxlength="255" />
			<span id='externalSysName_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>外部系统类型：</td>
		<td class="td_body">
			<select id="externalSysType" name="externalSysInfo.externalSysType" style="width: 88px;">
			</select>
		</td>
	</tr>
		<tr>
		<td class="td_head" width="200px" nowrap>备注：</td>
		<td class="td_body">
			<textarea id="remark" name="externalSysInfo.remark" cols="46" rows="6" >${externalSysInfo.remark}</textarea>
		</td>
	</tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table align="center">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="externalSysInfoFmSubmit();" nowrap>新建</td>
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
var externalSysName =new tt.Field("","","externalSysName").setMsgId("externalSysName_msg");
new tt.RV().set(new RegExp(/^[A-Za-z0-9_]+$/),"只能输入数字、字母和下划线!").add("externalSysInfo.externalSysId");
new tt.RV().set(new RegExp(/^[A-Za-z0-9_\u0391-\uFFE5]+$/),"只能输入数字、字母、下划线和汉字!").add(externalSysName);
function doClear(){
	document.getElementById("frmInput").reset();
}
</script>
</body>
</html>


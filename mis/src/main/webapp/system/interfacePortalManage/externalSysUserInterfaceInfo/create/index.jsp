<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>电子商务后台管理系统-新建服务器端用户和接口关系</title>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<link href="${ctx }/business/cmpProductManage/productManage/update/css/product.css" rel="stylesheet" type="text/css" >
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link href="${ctx }/global/css/stpess.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var isUnique = true;
$(document).ready(function(){
	$("#submit").click(function(){
		if(tt.validate()==false){
			return;
		}else{
			$("#frmInput").submit();
		}
	});
	
	$("#transCode,#id").blur(function(){
		checkUnique();
	});
	$("#transCode").focus(function(){
		if($("#transCode").val()== "双击绑定接口交易代码"){
			$("#transCode").val("");
		}
		$("#transCodeContent").remove();
	});	
	$("#id").focus(function(){
		if($("#id").val()== "双击绑定服务器端用户主键"){
			$("#id").val("");
		}
		$("#transCodeContent").remove();
	});	
	//绑定外部系统用户id
	$("#id").dblclick(function(){
		window.open("${ctx}/system/interfacePortalManage/externalSysUserInterfaceInfo/create/externalSystemsUserList/index.jsp", "查询外部系统用户信息", "top=100, left=100, width=900,height=600,toolbar=no");
	});
	//绑定接口信息id
	$("#transCode").dblclick(function(){
		window.open("${ctx}/system/interfacePortalManage/externalSysUserInterfaceInfo/create/interfaceInfoList/index.jsp", "查询接口信息", "top=100, left=100, width=900,height=600,toolbar=no");
	});	
});
function checkUnique(){
	var transCode = $("#transCode").val();
	var id = $("#id").val();
	//校验联合唯一性
	$.ajax({
		url : "${ctx}/interfacePortal/isHaveIdAndTransCode.do",
		data : {
			"transCode" : transCode,
			"id" : id
		},//传入参数
		type : 'POST',
		dataType : 'json',
		error : function() {
		},
		success : function(data) {
			if (data.resultFlag == "success") {
				$("#transCode").val("");
				$("#id").val("");
				$("#transCodeContent").remove();
				$("#id").parent().append("<span id='transCodeContent'><span class='talentErrMsg'>服务端用户主键和交易代码不能重复！</span></span>")
				isUnique = false;
			} else {
				$("#transCodeContent").remove();
			}
		}
	});
}
function ExternalSysUserInterfaceInfoFmSubmit(){
	checkUnique();
	if(tt.validate() && isUnique){
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
		alert("服务端用户和接口关系信息创建成功！");
		parent.document.location.href = "${ctx}/system/interfacePortalManage/externalSysUserInterfaceInfo/create/index.jsp";
	} else if (addResult == "failure") {
		alert("服务端用户和接口关系信息创建失败！");
	} else if (addResult == "isNotUnique") {
		alert("创建失败：交易代码和服务端用户主键重复！");
	}	
	
});
</script>
</head>
<body>
<div class="public_fun_title">
	新建服务器端用户和接口关系
</div>
<div style="height: 10px"></div>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/interfacePortal/saveExternalSysUserInterfaceInfo.do" target="myFrame">
<table align="center" style="width:650px;" id="productTable" >
	<tr>
		<td class="td_head" width="200px" style="vertical-align:top;" nowrap>交易代码：</td>
		<td class="td_body" width="350px">
			<input type="text" id="transCode" name="externalSysUserInterfaceInfo.interfaceInfo.transCode" value="双击绑定接口交易代码" style="color: gray;" maxlength="32" />
			<span id="transCode_msg"></span><br>
		</td>
	</tr>	
	<tr>
		<td class="td_head" width="200px" style="vertical-align:top;" nowrap>服务器端用户主键：</td>
		<td class="td_body" width="350px">
			<input type="text" id="id" name="externalSysUserInterfaceInfo.externalSystemsUser.id" value="双击绑定服务器端用户主键" style="color: gray;margin:0 0 5 0;" maxlength="32" />
			<span id="id_msg"></span><br>
		</td>
	</tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table align="center">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="ExternalSysUserInterfaceInfoFmSubmit();" nowrap>新建</td>
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
var id =new tt.Field("","","id").setMsgId("id_msg");
var transCode =new tt.Field("","","transCode").setMsgId("transCode_msg");
//非空验证
tt.vf.req.add(id,transCode);
</script>
</body>
</html>


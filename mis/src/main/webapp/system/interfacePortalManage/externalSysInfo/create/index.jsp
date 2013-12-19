<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>���������̨����ϵͳ-�½��ⲿϵͳ��Ϣ</title>
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
	//�������ֵ��ȡ�ⲿϵͳ����
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
			},//�������
			type : 'POST',
			dataType : 'json',
			error : function() {
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#externalSysId").val("");
					$("#externalSysIdContent").remove();
					$("#externalSysId").parent().append("<span id='externalSysIdContent'><span class='talentErrMsg'>���ⲿϵͳID�Ѿ����ڣ����޸�!</span></span>")
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
			},//�������
			type : 'POST',
			dataType : 'json',
			error : function() {
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#externalSysName").val("");
					$("#externalSysNameContent").remove();
					$("#externalSysName").parent().append("<span id='externalSysNameContent'><span class='talentErrMsg'>���ⲿϵͳ�����Ѿ����ڣ����޸�!</span></span>")
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
		alert("�ⲿϵͳ��Ϣ�����ɹ���");
		parent.document.location.href = "${ctx}/system/interfacePortalManage/externalSysInfo/create/index.jsp";
	} else if (addResult == "failure") {
		alert("�ⲿϵͳ��Ϣ����ʧ�ܣ�");
	}	
});
</script>
</head>
<body>
<div class="public_fun_title">
	�½��ⲿϵͳ��Ϣ
</div>
<div style="height: 10px"></div>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/interfacePortal/saveExternalSysInfo.do" target="myFrame">
<table align="center" style="width:650px;" id="productTable" >
	<tr>
		<td class="td_head" width="200px" nowrap>�ⲿϵͳ������</td>
		<td class="td_body" width="400px">
			<input type="text" id="externalSysId" name="externalSysInfo.externalSysId" value="" maxlength="32" />
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�ⲿϵͳ���ƣ�</td>
		<td class="td_body" width="400px">
			<input type="text" id="externalSysName" name="externalSysInfo.externalSysName" value="" maxlength="255" />
			<span id='externalSysName_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>�ⲿϵͳ���ͣ�</td>
		<td class="td_body">
			<select id="externalSysType" name="externalSysInfo.externalSysType" style="width: 88px;">
			</select>
		</td>
	</tr>
		<tr>
		<td class="td_head" width="200px" nowrap>��ע��</td>
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
						onmouseout="this.className='btnBigOnBlur'" onclick="externalSysInfoFmSubmit();" nowrap>�½�</td>
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
var externalSysName =new tt.Field("","","externalSysName").setMsgId("externalSysName_msg");
new tt.RV().set(new RegExp(/^[A-Za-z0-9_]+$/),"ֻ���������֡���ĸ���»���!").add("externalSysInfo.externalSysId");
new tt.RV().set(new RegExp(/^[A-Za-z0-9_\u0391-\uFFE5]+$/),"ֻ���������֡���ĸ���»��ߺͺ���!").add(externalSysName);
function doClear(){
	document.getElementById("frmInput").reset();
}
</script>
</body>
</html>


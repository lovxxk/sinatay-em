<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"></link>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css"></link>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>电子商务后台管理系统-外部系统信息维护</title>
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
	//从数据字典读取外部系统类型
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalGeCodeAjax.do",
		type : 'POST',
		dataType : 'json',
		data : {"codeType":"ExternalSysType"},
		error : function() {
		},
		success : function(data) {
			var selectedIem = ${externalSysInfo.externalSysType};
			$("#externalSysType").empty();
			for(var i = 0; i < data.length ; i++){
				if(selectedIem == data[i][0]){
					$("#externalSysType").append("<option value='" + data[i][0]+ "' selected='selected'>" + data[i][1] + "</option>");
				}else
					$("#externalSysType").append("<option value='" + data[i][0]+ "'>" + data[i][1] + "</option>");
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
				外部系统信息维护
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<form name="frmInput" id="frmInput" action="${ctx}/interfacePortal/updateExternalSysInfo.do" method="post" enctype="multipart/form-data" target="myFrame">
			<table class="table_style" cellpadding="0" cellspacing="0" align="center" id="geFunctionSwitchTable">
				<tr>
					<td class="td_head">外部系统主键：</td>
					<td class="td_body">${externalSysInfo.externalSysId}</td>
				</tr>
				<tr>
					<td class="td_head">外部系统名称：</td>
					<td class="td_body">
						<input id="externalSysName" name="externalSysInfo.externalSysName" type="text" style="width:170px;" value="${externalSysInfo.externalSysName}" maxlength="255"/>
						<span id='externalSysName_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head">外部系统类型：</td>
					<td class="td_body">
						<select id="externalSysType" name="externalSysInfo.externalSysType" style="width:170px;">
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_head">备注：</td>
					<td class="td_body"><textarea id="remark" name="externalSysInfo.remark" cols="46" rows="6" >${externalSysInfo.remark}</textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<table id="submitTable">
							<tr>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/interfacePortal/viewExternalSysInfoDetail.do?externalSysInfo.externalSysId=${externalSysInfo.externalSysId}';">返回</td>
								<td id="addButtonSubmit" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" nowrap>修改</td>
								<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" id="externalSysId" name="externalSysInfo.externalSysId" value="${externalSysInfo.externalSysId}">
		</form>
		<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</div>
<script type="text/javascript">
var externalSysName =new tt.Field("","","externalSysName").setMsgId("externalSysName_msg");
new tt.RV().set(new RegExp(/^[A-Za-z0-9_]+$/),"只输入数字、字母和下划线!").add("externalSysInfo.externalSysId");
new tt.RV().set(new RegExp(/^[A-Za-z0-9_\u0391-\uFFE5]+$/),"只输入数字、字母、下划线和汉字!").add(externalSysName);
function doClear(){
	document.getElementById("frmInput").reset();
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalGeCodeAjax.do",
		type : 'POST',
		dataType : 'json',
		data : {"codeType":"ExternalSysType"},
		error : function() {
		},
		success : function(data) {
			var selectedIem = ${externalSysInfo.externalSysType};
			$("#externalSysType").empty();
			for(var i = 0; i < data.length ; i++){
				if(selectedIem == data[i][0]){
					$("#externalSysType").append("<option value='" + data[i][0]+ "' selected='selected'>" + data[i][1] + "</option>");
				}else
					$("#externalSysType").append("<option value='" + data[i][0]+ "'>" + data[i][1] + "</option>");
			}
		}
	});
}
$(document).ready(function(){
	//表单提交
	$("#addButtonSubmit").click(function(){
		$("#frmInput").submit();
	});
});
</script>
</body>
</html>

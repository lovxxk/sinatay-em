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
<title>电子商务后台管理系统-外部系统用户和接口之间的关系信息维护</title>
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
 	width:280px;
 	background:url(${ctx}/global/images/face/open_title_word_bg.jpg) repeat-x;
 	float:left;
}
</style>
</head>
<body>
	<div id="open_titleDIV">
		<div class="open_title_c_new">
			<div class="open_title">
				外部系统用户和接口之间的关系信息维护
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<form name="frmInput" id="frmInput" action="${ctx}/interfacePortal/updateExternalSysUserInterfaceInfo.do" method="post" enctype="multipart/form-data" target="myFrame">
			<table class="table_style" cellpadding="0" cellspacing="0" align="center" id="geFunctionSwitchTable">
				<tr>
					<td class="td_head">交易代码：</td>
					<td class="td_body">
						<input id="transCode" name="externalSysUserInterfaceInfo.interfaceInfo.transCode" type="text" style="width:170px;" value="${externalSysUserInterfaceInfo.interfaceInfo.transCode}" maxlength="50" />
						<span id="transCode_msg"></span><br>
						<span id='transCodeContent'><font color='red' style='padding-left:2mm;'>双击绑定接口ID</font></span>
					</td>
				</tr>
				<tr>
					<td class="td_head">服务端用户主键： </td>
					<td class="td_body">
						<input type="text" id="id" name="externalSysUserInterfaceInfo.externalSystemsUser.id" value="${externalSysUserInterfaceInfo.externalSystemsUser.id}"/>
						<span id="id_msg"></span><br>
						<span id='idContent'><font color='red' style='padding-left:2mm;'>双击绑定外部系统用户ID</font></span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table id="submitTable">
							<tr>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/interfacePortal/viewExternalSysUserInterfaceInfoDetail.do?externalSysUserInterfaceInfo.id=${externalSysUserInterfaceInfo.id}';">返回</td>
								<td id="addButtonSubmit" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" nowrap>修改</td>
								<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" id="id" name="externalSysUserInterfaceInfo.id" value="${externalSysUserInterfaceInfo.id}">
		</form>
		<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</div>
<script type="text/javascript">
function doClear(){
	document.getElementById("frmInput").reset();
}
</script>
<script type="text/javascript">
//校验
var id =new tt.Field("","","id").setMsgId("id_msg");
var transCode =new tt.Field("","","transCode").setMsgId("transCode_msg");
//非空验证
tt.vf.req.add(id,transCode);
$(document).ready(function(){
	$("#addButtonSubmit").click(function(){
		if(tt.validate()==false){
			return;
		}else{
			$("#frmInput").submit();
		}
	});
	//绑定外部系统用户id
	$("#id").dblclick(function(){
		window.open("${ctx}/system/interfacePortalManage/externalSysUserInterfaceInfo/create/externalSystemsUserList/index.jsp", "查询外部系统用户信息", "top=100, left=100, width=900,height=600,toolbar=no");
	});
	//绑定接口信息id
	$("#transCode").dblclick(function(){
		window.open("${ctx}/system/interfacePortalManage/externalSysUserInterfaceInfo/create/interfaceInfoList/index.jsp", "查询接口信息", "top=100, left=100, width=900,height=600,toolbar=no");
	});
	
	$("#transCode").blur(function(){
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
					checkUnique = "error";
					$("#transCode").val("");
					$("#transCodeContent").remove();
					$("#transCode").parent().append("<span id='transCodeContent'><span class='talentErrMsg'>外部系统用户ID和接口ID不能重复！</span></span>")
				} else {
					$("#transCodeContent").remove();
				}
			}
		});
	});
	$("#transCode").focus(function(){
		$("#transCodeContent").remove();
	});	
});
</script>

</body>
</html>

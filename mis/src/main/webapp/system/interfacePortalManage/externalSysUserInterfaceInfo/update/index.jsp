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
<title>���������̨����ϵͳ-�ⲿϵͳ�û��ͽӿ�֮��Ĺ�ϵ��Ϣά��</title>
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
				�ⲿϵͳ�û��ͽӿ�֮��Ĺ�ϵ��Ϣά��
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<form name="frmInput" id="frmInput" action="${ctx}/interfacePortal/updateExternalSysUserInterfaceInfo.do" method="post" enctype="multipart/form-data" target="myFrame">
			<table class="table_style" cellpadding="0" cellspacing="0" align="center" id="geFunctionSwitchTable">
				<tr>
					<td class="td_head">���״��룺</td>
					<td class="td_body">
						<input id="transCode" name="externalSysUserInterfaceInfo.interfaceInfo.transCode" type="text" style="width:170px;" value="${externalSysUserInterfaceInfo.interfaceInfo.transCode}" maxlength="50" />
						<span id="transCode_msg"></span><br>
						<span id='transCodeContent'><font color='red' style='padding-left:2mm;'>˫���󶨽ӿ�ID</font></span>
					</td>
				</tr>
				<tr>
					<td class="td_head">������û������� </td>
					<td class="td_body">
						<input type="text" id="id" name="externalSysUserInterfaceInfo.externalSystemsUser.id" value="${externalSysUserInterfaceInfo.externalSystemsUser.id}"/>
						<span id="id_msg"></span><br>
						<span id='idContent'><font color='red' style='padding-left:2mm;'>˫�����ⲿϵͳ�û�ID</font></span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table id="submitTable">
							<tr>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/interfacePortal/viewExternalSysUserInterfaceInfoDetail.do?externalSysUserInterfaceInfo.id=${externalSysUserInterfaceInfo.id}';">����</td>
								<td id="addButtonSubmit" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" nowrap>�޸�</td>
								<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
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
//У��
var id =new tt.Field("","","id").setMsgId("id_msg");
var transCode =new tt.Field("","","transCode").setMsgId("transCode_msg");
//�ǿ���֤
tt.vf.req.add(id,transCode);
$(document).ready(function(){
	$("#addButtonSubmit").click(function(){
		if(tt.validate()==false){
			return;
		}else{
			$("#frmInput").submit();
		}
	});
	//���ⲿϵͳ�û�id
	$("#id").dblclick(function(){
		window.open("${ctx}/system/interfacePortalManage/externalSysUserInterfaceInfo/create/externalSystemsUserList/index.jsp", "��ѯ�ⲿϵͳ�û���Ϣ", "top=100, left=100, width=900,height=600,toolbar=no");
	});
	//�󶨽ӿ���Ϣid
	$("#transCode").dblclick(function(){
		window.open("${ctx}/system/interfacePortalManage/externalSysUserInterfaceInfo/create/interfaceInfoList/index.jsp", "��ѯ�ӿ���Ϣ", "top=100, left=100, width=900,height=600,toolbar=no");
	});
	
	$("#transCode").blur(function(){
		var transCode = $("#transCode").val();
		var id = $("#id").val();
		//У������Ψһ��
		$.ajax({
			url : "${ctx}/interfacePortal/isHaveIdAndTransCode.do",
			data : {
				"transCode" : transCode,
				"id" : id
			},//�������
			type : 'POST',
			dataType : 'json',
			error : function() {
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					checkUnique = "error";
					$("#transCode").val("");
					$("#transCodeContent").remove();
					$("#transCode").parent().append("<span id='transCodeContent'><span class='talentErrMsg'>�ⲿϵͳ�û�ID�ͽӿ�ID�����ظ���</span></span>")
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

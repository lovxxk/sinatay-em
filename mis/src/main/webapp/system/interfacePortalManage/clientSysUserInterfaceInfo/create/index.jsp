<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>���������̨����ϵͳ-�ͻ����û��ͽӿڼ��ϵ</title>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<link href="${ctx }/business/cmpProductManage/productManage/update/css/product.css" rel="stylesheet" type="text/css" >
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link href="${ctx }/global/css/stpess.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var checkUnique = "";
$(document).ready(function(){
	$("#submit").click(function(){
		if(tt.validate()==false){
			return;
		}else{
			$("#frmInput").submit();
		}
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
					$("#transCode").parent().append("<span id='transCodeContent'><span class='talentErrMsg'>�ͻ����û������ͽ��״��벻���ظ���</span></span>")
				} else {
					$("#transCodeContent").remove();
				}
			}
		});
	});
	$("#transCode").focus(function(){
		if($("#transCode").val()== "˫���󶨽ӿڽ��״���"){
			$("#transCode").val("");
		}
		$("#transCodeContent").remove();
	});	
	$("#id").focus(function(){
		if($("#id").val()== "˫���󶨿ͻ����û�����"){
			$("#id").val("");
		}
		$("#transCodeContent").remove();
	});	

	//���ⲿϵͳ�û�id
	$("#id").dblclick(function(){
		window.open("${ctx}/system/interfacePortalManage/clientSysUserInterfaceInfo/create/clientSystemsUserList/index.jsp", "��ѯ�ͻ����û���Ϣ", "top=100, left=100, width=900,height=600,toolbar=no");
	});
	//�󶨽ӿ���Ϣid
	$("#transCode").dblclick(function(){
		window.open("${ctx}/system/interfacePortalManage/clientSysUserInterfaceInfo/create/interfaceInfoList/index.jsp", "��ѯ�ӿ���Ϣ", "top=100, left=100, width=900,height=600,toolbar=no");
	});	
});
function ClientSysUserInterfaceInfoFmSubmit(){
	if(tt.validate()){
		if(checkUnique == 'error'){
			alert("�ͻ����û������ͽӿڽ��״��벻���ظ���");
			return;
		}
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
		alert("�ͻ����û��ͽӿڼ��ϵ�����ɹ���");
		parent.document.location.href = "${ctx}/system/interfacePortalManage/clientSysUserInterfaceInfo/create/index.jsp";
	} else if (addResult == "failure") {
		alert("�ͻ����û��ͽӿڼ��ϵ����ʧ�ܣ�");
		parent.document.location.href = "${ctx}/system/interfacePortalManage/clientSysUserInterfaceInfo/create/index.jsp";
	}
});
</script>
</head>
<body>
<div class="public_fun_title">
	�ͻ����û��ͽӿڼ��ϵ
</div>
<div style="height: 10px"></div>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/interfacePortal/updateClientInterfaceInfo.do" target="myFrame">
<table align="center" style="width:650px;" id="productTable" >
	<tr>
		<td class="td_head" width="200px" style="vertical-align:top;" nowrap>���״��룺</td>
		<td class="td_body" width="350px">
			<input type="text" id="transCode" name="interfaceInfo.transCode" value="˫���󶨽ӿڽ��״���" style="color: gray;" maxlength="32" />
			<span id="transCode_msg"></span><br>
		</td>
	</tr>	
	<tr>
		<td class="td_head" width="200px" style="vertical-align:top;" nowrap>�ͻ����û�������</td>
		<td class="td_body" width="350px">
			<input type="text" id="id" name="interfaceInfo.clientUser.id" value="˫���󶨿ͻ����û�����" style="color: gray;" maxlength="32" />
			<span id="id_msg"></span><br>
		</td>
	</tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table align="center">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="ClientSysUserInterfaceInfoFmSubmit();" nowrap>�½�</td>
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
var id =new tt.Field("","","id").setMsgId("id_msg");
var transCode =new tt.Field("","","transCode").setMsgId("transCode_msg");
//�ǿ���֤
tt.vf.req.add(id,transCode);
</script>
</body>
</html>


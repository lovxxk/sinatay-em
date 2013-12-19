<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>电子商务后台管理系统-新建接口信息</title>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<link href="${ctx }/business/cmpProductManage/productManage/update/css/product.css" rel="stylesheet" type="text/css" >
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link href="${ctx }/global/css/stpess.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#transCode").blur(function(){
		var transCode = $("#transCode").val();
		$.ajax({
			url : "${ctx}/interfacePortal/isHaveTransCode.do",
			data : {
				"transCode" : transCode
			},//传入参数
			type : 'POST',
			dataType : 'json',
			error : function() {
			},
			success : function(data) {
				if (data.resultFlag == "success") {
					$("#transCode").val("");
					$("#transCodeContent").remove();
					$("#transCode").parent().append("<span id='transCodeContent'><span class='talentErrMsg'>该交易代码已经存在，请修改!</span></span>")
				} else {
					$("#transCodeContent").remove();
				}
			}
		});
	});
	$("#transCode").focus(function(){
		$("#transCodeContent").remove();
	});
	//从数据字典读取报文处理类型
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalGeCodeAjax.do",
		type : 'POST',
		dataType : 'json',
		data : {"codeType":"HandleMessageType"},
		error : function() {
		},
		success : function(data) {
			$("#handleMessageType").empty();
			$("#handleMessageType").append("<option value='' selected='selected'>--请选择--</option>");
			for(var i = 0; i < data.length ; i++){
				$("#handleMessageType").append("<option value='" + data[i][0]+ "' >" + data[i][1] + "</option>");
			}
		}
	});
	//从数据字典读取报文处理类型
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalGeCodeAjax.do",
		type : 'POST',
		dataType : 'json',
		data : {"codeType":"EncryptionMethod"},
		error : function() {
		},
		success : function(data) {
			$("#encryptionMethod").empty();
			$("#encryptionMethod").append("<option value='' selected='selected'>--请选择--</option>");
			for(var i = 0; i < data.length ; i++){
				$("#encryptionMethod").append("<option value='" + data[i][0]+ "' >" + data[i][1] + "</option>");
			}
		}
	});
});
function interfaceInfoFmSubmit(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}
function interfaceInfoFmReset(){
	document.getElementById("frmInput").reset();
}
$(function (){
	var addResult = "${addResult}";
	if (addResult == "success") {
		alert("接口信息创建成功！");
		parent.document.location.href = "${ctx}/system/interfacePortalManage/interfaceInfo/create/index.jsp";
	} else if (addResult == "failure") {
		alert("接口信息创建失败！");
	}	
});
</script>
</head>
<body>
<div class="public_fun_title">
	新建接口信息
</div>
<div style="height: 10px"></div>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/interfacePortal/saveInterfaceInfo.do" target="myFrame">
<table align="center" style="width:650px;" id="productTable" >
	<tr>
		<td class="td_head" width="200px" nowrap>交易代码：</td>
		<td class="td_body" width="400px">
			<input type="text" id="transCode" name="interfaceInfo.transCode" value="" maxlength="32" />
			<span id="transCode_msg"></span><br>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>交易名称：</td>
		<td class="td_body" width="400px">
			<input type="text" id="transName" name="interfaceInfo.transName" value="" maxlength="200" />
			<span id='transName_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>交易类型：</td>
		<td class="td_body">
			<select id="transType" name="interfaceInfo.transType" style="width: 88px;">
				<option value="K" selected>客户端</option>
				<option value="F">服务端</option>
			</select>		
		</td>
	</tr>		
	<tr>
		<td class="td_head" width="200px" nowrap>业务领域：</td>
		<td class="td_body" width="400px">
			<input type="text" id="businessArea" name="interfaceInfo.businessArea" value="" maxlength="255" />
			<span id='businessArea_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>接口状态：</td>
		<td class="td_body">
			<select id="status" name="interfaceInfo.status" style="width: 88px;">
				<option value="1" selected>启用</option>
				<option value="2">停用</option>
			</select>		
		</td>
	</tr>		
	<tr>
		<td class="td_head" width="200px" nowrap>是否记录日志：</td>
		<td class="td_body">
			<select id="isRecordMessage" name="interfaceInfo.isRecordMessage" style="width: 88px;">
				<option value="N" >否</option>
				<option selected value="Y">是</option>
			</select>		
		</td>
	</tr>
	<%-- 
	<tr>
		<td class="td_head" width="200px" nowrap>报文处理类型：</td>
		<td class="td_body">
			<select id="handleMessageType" name="interfaceInfo.handleMessageType" style="width: 88px;">
			</select>
		</td>
	</tr>
	--%>
	<tr>
		<td class="td_head" width="200px" nowrap>骨架类名称：</td>
		<td class="td_body" width="420px">
			<input type="text" id="className" name="interfaceInfo.className" value="" maxlength="255" />
			<span id='className_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>请求端服务器地址：</td>
		<td class="td_body" width="350px">
			<input type="text" id="requestURL" name="interfaceInfo.requestURL" value="" maxlength="255" />
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>命名空间URL：</td>
		<td class="td_body" width="350px">
			<input type="text" id="namespaceURL" name="interfaceInfo.namespaceURL" value="" maxlength="255" />
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>服务方法名：</td>
		<td class="td_body" width="400px">
			<input type="text" id="localPart" name="interfaceInfo.localPart" value="" maxlength="255" />
			<span id='localPart_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>服务方法参数数量：</td>
		<td class="td_body" width="350px">
			<input type="text" id="localPartParameterNumber" name="interfaceInfo.localPartParameterNumber" value="" maxlength="8" />
			<span id='localPartParameterNumber_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>是否支持加密：</td>
		<td class="td_body">
			<select id="isEncryption" name="isEncryption" style="width: 88px;" onchange="javascript:displayEncryption();">
				<option value="0" selected>否</option>
				<option value="1">是</option>
			</select>		
		</td>
	</tr>
	<tr id="tr_encryptionMethod">
		<td class="td_head" width="200px" nowrap>加密方式 ：</td>
		<td class="td_body">
			<select id="encryptionMethod" name="interfaceInfo.encryptionMethod" style="width: 88px;">
			</select>
	</tr>
	<tr id="tr_encryptionKey">
		<td class="td_head" width="200px" nowrap>密钥：</td>
		<td class="td_body" width="350px">
			<input type="text" id="encryptionKey" name="interfaceInfo.encryptionKey" value="" maxlength="255" />
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>接口版本：</td>
		<td class="td_body" width="400px">
			<input type="text" id="esbserviceversion" name="interfaceInfo.esbserviceversion" value="" maxlength="32" />
			<span id='esbserviceversion_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>esb接口服务名称：</td>
		<td class="td_body" width="400px">
			<input type="text" id="esbSvcName" name="interfaceInfo.esbSvcName" value="" maxlength="80" />
			<span id='esbSvcName_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>来源系统代码：</td>
		<td class="td_body" width="400px">
			<input type="text" id="esborisys" name="interfaceInfo.esborisys" value="GROUP-EBP" maxlength="80" />
			<span id='esborisys_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>路由机构编码：</td>
		<td class="td_body" width="400px">
			<input type="text" id="esbroutebranchno" name="interfaceInfo.esbroutebranchno" value="" maxlength="80" />
			<span id='esbroutebranchno_msg'></span>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>路由目标系统标识：</td>
		<td class="td_body" width="400px">
			<input type="text" id="esbroutedestsys" name="interfaceInfo.esbroutedestsys" value="" maxlength="80" />
			<span id='esbroutedestsys_msg'></span>
		</td>
	</tr>	
	<tr>
		<td class="td_head" width="200px" nowrap>备注：</td>
		<td class="td_body">
			<textarea id="remark" name="interfaceInfo.remark" cols="46" rows="6" >${interfaceInfo.remark}</textarea>
		</td>
	</tr>
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table align="center">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="interfaceInfoFmSubmit();" nowrap>新建</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="interfaceInfoFmReset();" nowrap>重置</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<input type="hidden" id="id" name="interfaceInfo.clientUser.id" value=""/>
</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
<script type="text/javascript">
//校验
var transName =new tt.Field("","","transName").setMsgId("transName_msg");
//new tt.RV().set(new RegExp(/^[\w-]{6,16}$/),"由6-16位数字.字母.'_'或'-'组成").add(transName);
var transCode =new tt.Field("","","transCode").setMsgId("transCode_msg");
var businessArea =new tt.Field("","","businessArea").setMsgId("businessArea_msg");
var localPartParameterNumber =new tt.Field("","","localPartParameterNumber").setMsgId("localPartParameterNumber_msg");
var localPart =new tt.Field("","","localPart").setMsgId("localPart_msg");
var className =new tt.Field("","","className").setMsgId("className_msg");
var esbserviceversion=new tt.Field("","","esbserviceversion").setMsgId("esbserviceversion_msg");
var esbSvcName =new tt.Field("","","esbSvcName").setMsgId("esbSvcName_msg");
var esborisys =new tt.Field("","","esborisys").setMsgId("esborisys_msg");
//针对不同需求的正则表达式验证
tt.vf.req.add(transCode,transName,esbserviceversion,esbSvcName,esborisys);
//new tt.RV().set(new RegExp(/^[A-Za-z0-9_]+$/),"只能输入数字、字母和下划线!").add(transCode,esbserviceversion);
new tt.RV().set(new RegExp(/^[0-9]+$/),"只能输入数字!").add(localPartParameterNumber);
new tt.RV().set(new RegExp(/^[A-Za-z0-9_\u0391-\uFFE5]+$/),"只能输入数字、字母、下划线和汉字!").add(businessArea,transName,localPart,esbSvcName);
new tt.RV().set(new RegExp(/^[A-Za-z0-9_.\u0391-\uFFE5]+$/),"只能输入数字、字母、下划线、.和汉字!").add(className);
displayEncryption();
function displayEncryption(){
	var checkedVal = $("#isEncryption").val(); 
	if(checkedVal == '0'){
		$("#tr_encryptionMethod").hide();
		$("#tr_encryptionKey").hide();
		$("#encryptionMethod").val("");//清空隐藏值
		$("#encryptionKey").val("");//清空隐藏值
		tt.vf.req.rm("interfaceInfo.encryptionMethod");//不需要 加密则加密方式不再校验非空以及清除密钥的值
	}else{
		$("#tr_encryptionMethod").show();
		$("#tr_encryptionKey").show();
		tt.vf.req.add("interfaceInfo.encryptionMethod");//若需要加密，则加密方式为必填项
	}
}
</script>
</body>
</html>
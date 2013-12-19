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
<title>电子商务后台管理系统-接口信息维护</title>
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
	$("#addButtonSubmit").click(function(){
		if(tt.validate()){
			$("#frmInput").submit();	
		}
	});
	//双击绑定客户端用户ID
	$("#id").dblclick(function(){
		window.open("${ctx}/system/interfacePortalManage/interfaceInfo/create/selectClientUserIdList/index.jsp", "查询客户端用户信息", "top=100, left=100, width=900,height=600,toolbar=no");
	});
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
			$("#handleMessageType").append("<option value=''>--请选择--</option>");
			for(var i = 0; i < data.length ; i++){
				var handleMessageType = "${interfaceInfo.handleMessageType}";
				if(handleMessageType  == data[i][0]){
					$("#handleMessageType").append("<option value='" + data[i][0]+ "' selected='selected'>" + data[i][1] + "</option>");
				}else{
					$("#handleMessageType").append("<option value='" + data[i][0]+ "' >" + data[i][1] + "</option>");	
				}
			}
		}
	});
	//从数据字典读取加密方式
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalGeCodeAjax.do",
		type : 'POST',
		dataType : 'json',
		data : {"codeType":"EncryptionMethod"},
		error : function() {
		},
		success : function(data) {
			$("#encryptionMethod").empty();
			$("#encryptionMethod").append("<option value=''>--请选择--</option>");
			for(var i = 0; i < data.length ; i++){
				var encryptionMethod = "${interfaceInfo.encryptionMethod}";
				if(encryptionMethod  == data[i][0]){
					$("#encryptionMethod").append("<option value='" + data[i][0]+ "' selected='selected'>" + data[i][1] + "</option>");
				}else{
					$("#encryptionMethod").append("<option value='" + data[i][0]+ "' >" + data[i][1] + "</option>");	
				}
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
				接口信息维护
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<form name="frmInput" id="frmInput" action="${ctx}/interfacePortal/updateInterfaceInfo.do" method="post" enctype="multipart/form-data" target="myFrame">
			<table class="table_style" cellpadding="0" cellspacing="0" align="center" id="geFunctionSwitchTable">
				<tr>
					<td class="td_head" width="200px" nowrap>交易代码：</td>
					<td class="td_body" width="350px">${interfaceInfo.transCode}</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>交易名称：</td>
					<td class="td_body" width="350px">
						<input type="text" id="transName" name="interfaceInfo.transName" value="${interfaceInfo.transName}" maxlength="200" />
						<span id='transName_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>交易类型：</td>
					<td class="td_body">
						<select id="transType" name="interfaceInfo.transType" style="width: 88px;">
							<option value="">--请选择--</option>
								<option value="K" <c:if test='${interfaceInfo.transType=="K"}'>selected</c:if>>客户端</option>
								<option value="F" <c:if test='${interfaceInfo.transType=="F"}'>selected</c:if>>服务端</option>
						</select>
						<span id='transType_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>业务领域：</td>
					<td class="td_body" width="350px">
						<input type="text" id="businessArea" name="interfaceInfo.businessArea" value="${interfaceInfo.businessArea}" maxlength="255" />
						<span id='businessArea_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>接口状态：</td>
					<td class="td_body">
						<select id="status" name="interfaceInfo.status" style="width: 88px;">
							<option value="">--请选择--</option>
								<option value="1" <c:if test="${interfaceInfo.status=='1'}">selected</c:if>>启用</option>
								<option value="2" <c:if test="${interfaceInfo.status=='2'}">selected</c:if>>停用</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>是否记录日志：</td>
					<td class="td_body">
						<select id="isRecordMessage" name="interfaceInfo.isRecordMessage" style="width: 88px;">
							<option value="">--请选择--</option>
								<option value="Y" <c:if test="${interfaceInfo.isRecordMessage=='Y'}">selected</c:if>>是</option>
								<option value="N" <c:if test="${interfaceInfo.isRecordMessage!='Y'}">selected</c:if>>否</option>
						</select>
					</td>
				</tr>
				<%--
				<tr>
					<td class="td_head" width="200px" nowrap>报文处理类型：</td>
					<td class="td_body" width="350px">
						<select id="handleMessageType" name="interfaceInfo.handleMessageType" style="width: 88px;">
						</select>
					</td>
				</tr>
				 --%>
				<tr>
					<td class="td_head" width="200px" nowrap>骨架类名称：</td>
					<td class="td_body" width="350px">
						<input type="text" id="className" name="interfaceInfo.className" value="${interfaceInfo.className}" maxlength="255" />
						<span id='className_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>请求端服务器地址：</td>
					<td class="td_body" width="350px">
						<input type="text" id="requestURL" name="interfaceInfo.requestURL" value="${interfaceInfo.requestURL}" maxlength="255" />
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>命名空间URL：</td>
					<td class="td_body" width="350px">
						<input type="text" id="namespaceURL" name="interfaceInfo.namespaceURL" value="${interfaceInfo.namespaceURL}" maxlength="255" />
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>服务方法名：</td>
					<td class="td_body" width="350px">
						<input type="text" id="localPart" name="interfaceInfo.localPart" value="${interfaceInfo.localPart}" maxlength="255" />
						<span id='localPart_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>服务方法参数数量：</td>
					<td class="td_body" width="350px">
						<input type="text" id="localPartParameterNumber" name="interfaceInfo.localPartParameterNumber" value="${interfaceInfo.localPartParameterNumber}" maxlength="8" />
						<span id='localPartParameterNumber_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>是否支持加密：</td>
					<td class="td_body">
						<select id="isEncryption" name="isEncryption" style="width: 88px;" onchange="javascript:displayEncryption();">
							<option value="0" <c:if test='${interfaceInfo.encryptionMethod == "" || interfaceInfo.encryptionMethod == null}'>selected</c:if>>否</option>
							<option value="1" <c:if test='${interfaceInfo.encryptionMethod != "" && interfaceInfo.encryptionMethod != nill}'>selected</c:if>>是</option>
						</select>		
					</td>
				</tr>				
				<tr id="tr_encryptionMethod">
					<td class="td_head" width="200px" nowrap>加密方式 ：</td>
					<td class="td_body" width="350px">
						<select id="encryptionMethod" name="interfaceInfo.encryptionMethod" style="width: 88px;">
						</select>
					</td>
				</tr>
				<tr id="tr_encryptionKey">
					<td class="td_head" width="200px" nowrap>密钥：</td>
					<td class="td_body" width="350px">
						<input type="text" id="encryptionKey" name="interfaceInfo.encryptionKey" value="${interfaceInfo.encryptionKey}" maxlength="255" />
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>接口版本：</td>
					<td class="td_body" width="350px">
						<input type="text" id="esbserviceversion" name="interfaceInfo.esbserviceversion" value="${interfaceInfo.esbserviceversion}" maxlength="60" />
						<span id='esbserviceversion_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>esb接口服务名称：</td>
					<td class="td_body" width="350px">
						<input type="text" id="esbSvcName" name="interfaceInfo.esbSvcName" value="${interfaceInfo.esbSvcName}" maxlength="80" />
						<span id='esbSvcName_msg'></span>
					</td>
				</tr>
				<tr>
					<td class="td_head" width="200px" nowrap>来源系统代码：</td>
					<td class="td_body" width="350px">
						<input type="text" id="esborisys" name="interfaceInfo.esborisys" value="${interfaceInfo.esborisys}" maxlength="80" />
						<span id='esborisys_msg'></span>
					</td>
				</tr>		
				<tr>
					<td class="td_head" width="200px" nowrap>路由机构编码：</td>
					<td class="td_body" width="350px">
						<input type="text" id="esbroutebranchno" name="interfaceInfo.esbroutebranchno" value="${interfaceInfo.esbroutebranchno}" maxlength="80" />
						<span id='esbroutebranchno_msg'></span>
					</td>
				</tr>	
				<tr>
					<td class="td_head" width="200px" nowrap>路由目标系统标识：</td>
					<td class="td_body" width="350px">
						<input type="text" id="esbroutedestsys" name="interfaceInfo.esbroutedestsys" value="${interfaceInfo.esbroutedestsys}" maxlength="80" />
						<span id='esbroutedestsys_msg'></span>
					</td>
				</tr>													
								
				
				<tr>
					<td class="td_head" width="200px" nowrap>备注：</td>
					<td class="td_body">
						<textarea id="remark" name="interfaceInfo.remark" cols="46" rows="6" >${interfaceInfo.remark}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table id="submitTable">
							<tr>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/interfacePortal/viewInterfaceInfoDetail.do?interfaceInfo.transCode=${interfaceInfo.transCode}';">返回</td>
								<td id="addButtonSubmit" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" nowrap>修改</td>
								<td id="resetButton"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" id="transCode" name="interfaceInfo.transCode" value="${interfaceInfo.transCode}"/>
			<input type="hidden" id=createDate name="interfaceInfo.createDate" value="${interfaceInfo.createDate}"/>
		</form>
		<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</div>

<script type="text/javascript">
function doClear(){
	document.getElementById("frmInput").reset();
	displayEncryption();
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
			$("#handleMessageType").append("<option value=''>--请选择--</option>");
			for(var i = 0; i < data.length ; i++){
				var handleMessageType = "${interfaceInfo.handleMessageType}";
				if(handleMessageType  == data[i][0]){
					$("#handleMessageType").append("<option value='" + data[i][0]+ "' selected='selected'>" + data[i][1] + "</option>");
				}else{
					$("#handleMessageType").append("<option value='" + data[i][0]+ "' >" + data[i][1] + "</option>");	
				}
			}
		}
	});
	//从数据字典读取加密方式
	$.ajax({
		url : "${ctx}/interfacePortal/findExternalGeCodeAjax.do",
		type : 'POST',
		dataType : 'json',
		data : {"codeType":"EncryptionMethod"},
		error : function() {
		},
		success : function(data) {
			$("#encryptionMethod").empty();
			$("#encryptionMethod").append("<option value=''>--请选择--</option>");
			for(var i = 0; i < data.length ; i++){
				var encryptionMethod = "${interfaceInfo.encryptionMethod}";
				if(encryptionMethod  == data[i][0]){
					$("#encryptionMethod").append("<option value='" + data[i][0]+ "' selected='selected'>" + data[i][1] + "</option>");
				}else{
					$("#encryptionMethod").append("<option value='" + data[i][0]+ "' >" + data[i][1] + "</option>");	
				}
			}
		}
	});
}
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
tt.vf.req.add(transName,esbserviceversion,esbSvcName,esborisys);
//针对不同需求的正则表达式验证
//new tt.RV().set(new RegExp(/^[A-Za-z0-9_]+$/),"只能输入数字、字母和下划线!").add(transCode);
new tt.RV().set(new RegExp(/^[0-9]+$/),"只能输入数字!").add(localPartParameterNumber);
new tt.RV().set(new RegExp(/^[A-Za-z0-9_\u0391-\uFFE5]+$/),"只能输入数字、字母、下划线和汉字!").add(businessArea,transName,localPart);
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

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
<title>电子商务后台管理系统-客户端用户和接口间关系信息维护</title>
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
				客户端用户和接口间关系信息维护
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
					<td class="td_body" width="350px">${interfaceInfo.transName}
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>交易类型：</td>
					<td class="td_body">
						<select id="transType" name="interfaceInfo.transType" style="width: 170px;" onchange="displayTransType()">
							<option value="K" selected>客户端，请求外部服务</option>
							<option value="F">服务端，为外部提供服务</option>
						</select>
					</td>
				</tr>
				<tr id="tr_clientUserid">
					<td class="td_head" width="200px" nowrap>客户端用户ID：</td>
					<td class="td_body" width="350px">
						<input type="text" id="id" name="interfaceInfo.clientUser.id" value="${interfaceInfo.clientUser.id}" maxlength="50" />
						<span id='id_msg'><font color='red' style='padding-left:2mm;'>双击绑定外部系统ID</font></span>
					</td>
				</tr>				
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>业务数据域：</td>
					<td class="td_body" width="350px">
						<input type="text" id="businessArea" name="interfaceInfo.businessArea" value="${interfaceInfo.businessArea}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>接口状态：</td>
					<td class="td_body">
						<select id="status" name="interfaceInfo.status" style="width: 170px;">
							<option value="" selected>--请选择--</option>
								<option value="1" <c:if test="${interfaceInfo.status=='1'}">selected</c:if>>启用</option>
								<option value="2" <c:if test="${interfaceInfo.status=='2'}">selected</c:if>>停用</option>
						</select>
					</td>
				</tr>
				<tr  style="display:none;">
					<td class="td_head" width="200px" nowrap>否记录日志：</td>
					<td class="td_body">
						<select id="isRecordMessage" name="interfaceInfo.isRecordMessage" style="width: 170px;">
							<option value="" selected>--请选择--</option>
								<option value="1" <c:if test="${interfaceInfo.isRecordMessage=='1'}">selected</c:if>>是</option>
								<option value="2" <c:if test="${interfaceInfo.isRecordMessage=='2'}">selected</c:if>>否</option>
						</select>
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>报文处理类型：</td>
					<td class="td_body" width="350px">
						<input type="text" id="handleMessageType" name="interfaceInfo.handleMessageType" value="${interfaceInfo.handleMessageType}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>骨架类名称：</td>
					<td class="td_body" width="350px">
						<input type="text" id="className" name="interfaceInfo.className" value="${interfaceInfo.className}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>请求端服务器地址：</td>
					<td class="td_body" width="350px">
						<input type="text" id="requestURL" name="interfaceInfo.requestURL" value="${interfaceInfo.requestURL}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>命名空间URL：</td>
					<td class="td_body" width="350px">
						<input type="text" id="namespaceURL" name="interfaceInfo.namespaceURL" value="${interfaceInfo.namespaceURL}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>服务方法名：</td>
					<td class="td_body" width="350px">
						<input type="text" id="localPart" name="interfaceInfo.localPart" value="${interfaceInfo.localPart}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>服务方法参数数量：</td>
					<td class="td_body" width="350px">
						<input type="text" id="localPartParameterNumber" name="interfaceInfo.localPartParameterNumber" value="${interfaceInfo.localPartParameterNumber}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>否记支持加密：</td>
					<td class="td_body">
						<select id="isEncryption" name="isEncryption" style="width: 88px;" onchange="javascript:displayEncryption();">
							<option value="0" selected>否</option>
							<option value="1">是</option>
						</select>		
					</td>
				</tr>				
				<tr id="tr_encryptionMethod" style="display:none;">
					<td class="td_head" width="200px" nowrap>加密方式 ：</td>
					<td class="td_body" width="350px">
						<input type="text" id="encryptionMethod" name="interfaceInfo.encryptionMethod" value="${interfaceInfo.encryptionMethod}" maxlength="50" />
					</td>
				</tr>
				<tr id="tr_encryptionKey" style="display:none;">
					<td class="td_head" width="200px" nowrap>密钥：</td>
					<td class="td_body" width="350px">
						<input type="text" id="encryptionKey" name="interfaceInfo.encryptionKey" value="${interfaceInfo.encryptionKey}" maxlength="50" />
					</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>创建时间：</td>
					<td class="td_body" width="350px">${interfaceInfo.createDate}</td>
				</tr>
				<tr style="display:none;">
					<td class="td_head" width="200px" nowrap>更新时间：</td>
					<td class="td_body" width="350px">${interfaceInfo.updateDate}</td>
				</tr>
				<tr style="display:none;">
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
								onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/interfacePortal/viewInterfaceInfoDetailNew.do?interfaceInfo.transCode=${interfaceInfo.transCode}';">返回</td>
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
}
</script>
<script type="text/javascript">
//校验
//针对不同需求的正则表达式验证
$(document).ready(function(){
	$("#addButtonSubmit").click(function(){
		$("#frmInput").submit();
	});
	//双击绑定客户端用户ID
	$("#id").dblclick(function(){
		window.open("${ctx}/system/interfacePortalManage/interfaceInfo/create/selectClientUserIdList/index.jsp", "查询客户端用户信息", "top=100, left=100, width=900,height=600,toolbar=no");
	});
});
</script>
</body>
</html>

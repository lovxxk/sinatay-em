<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"></link>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css"></link>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>电子商务后台管理系统-接口详细信息</title>
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
</head>
<body>
	<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				接口详细信息
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<table class="table_style" cellpadding="0" cellspacing="0" align="center" id="geFunctionSwitchTable">

	<tr>
		<td class="td_head" width="200px" nowrap>交易代码：</td>
		<td class="td_body" width="350px">${interfaceInfo.transCode}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>交易名称：</td>
		<td class="td_body" width="350px">${interfaceInfo.transName}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>交易类型：</td>
		<td class="td_body">
			<c:choose>
				<c:when test='${interfaceInfo.transType == "K"}'>客户端</c:when>
				<c:when test='${interfaceInfo.transType == "F"}'>服务端</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>			
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>业务领域：</td>
		<td class="td_body" width="350px">${interfaceInfo.businessArea}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>接口状态：</td>
		<td class="td_body">
			<c:choose>
				<c:when test="${interfaceInfo.status == '1'}">
					启用
				</c:when>
				<c:when test="${interfaceInfo.status == '2'}">
					停用
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>			
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>是否记录日志：</td>
		<td class="td_body">
			<c:choose>
				<c:when test="${interfaceInfo.isRecordMessage == 'Y'}">
					是
				</c:when>
				<c:when test="${interfaceInfo.isRecordMessage != 'Y'}">
					否
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>			
		</td>
	</tr>
	<%--
	<tr>
		<td class="td_head" width="200px" nowrap>报文处理类型：</td>
		<td class="td_body" width="350px">${interfaceInfo.handleMessageType}</td>
	</tr>
	 --%>
	<tr>
		<td class="td_head" width="200px" nowrap>骨架类名称：</td>
		<td class="td_body" width="350px">${interfaceInfo.className}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>请求端服务器地址：</td>
		<td class="td_body" width="350px">${interfaceInfo.requestURL}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>命名空间URL：</td>
		<td class="td_body" width="350px">${interfaceInfo.namespaceURL}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>服务方法名：</td>
		<td class="td_body" width="350px">${interfaceInfo.localPart}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>服务方法参数数量：</td>
		<td class="td_body" width="350px">${interfaceInfo.localPartParameterNumber}</td>
	</tr>
	<c:if test="${not empty interfaceInfo.encryptionMethod}">
	<tr id="tr_encryptionMethod">
		<td class="td_head" width="200px" nowrap>加密方式 ：</td>
		<td class="td_body" width="350px">${interfaceInfo.encryptionMethod}</td>
	</tr>
	<tr id="tr_encryptionKey">
		<td class="td_head" width="200px" nowrap>密钥：</td>
		<td class="td_body" width="350px">${interfaceInfo.encryptionKey}</td>
	</tr>
	</c:if>
	<tr>
		<td class="td_head" width="200px" nowrap>接口版本：</td>
		<td class="td_body" width="350px">${interfaceInfo.esbserviceversion}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>esb接口服务名称：</td>
		<td class="td_body" width="350px">${interfaceInfo.esbSvcName}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>来源系统代码：</td>
		<td class="td_body" width="350px">${interfaceInfo.esborisys}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>路由机构编码：</td>
		<td class="td_body" width="350px">${interfaceInfo.esbroutebranchno}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>路由目标系统标识：</td>
		<td class="td_body" width="350px">${interfaceInfo.esbroutedestsys}</td>
	</tr>
	
	<tr>
		<td class="td_head" width="200px" nowrap>备注：</td>
		<td class="td_body"><textarea readonly id="remark" name="clientUser.remark" cols="46" rows="6" >${interfaceInfo.remark}</textarea></td>
	</tr>
			<tr>
				<td colspan="2" >
					<table id="submitTable">
						<tr>
							<acc:showView source="ROLE_PYAMENT_EDIT">
								<td onclick="doEdit('${interfaceInfo.transCode}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">编辑</td>
								<td onclick="doDelete();" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'">删除</td>
								<td id="closeButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap>关闭</td>
							</acc:showView>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<form id="frmInput2" action="${ctx }/interfacePortal/deleteInterfaceInfo.do" target="myFrame" method="post">
			<input type="hidden" id="transCode" name="interfaceInfo.transCode" value="${interfaceInfo.transCode}">
		</form>	
		<iframe id="myFrame" name="myFrame" style="display: none"></iframe>	
	</div>
<script type="text/javascript">
//编辑
function doEdit(transCode){
	location.href = "${ctx}/interfacePortal/searchInterfaceInfo.do?interfaceInfo.transCode=" + transCode ;
}
function doDelete(){
	document.getElementById("frmInput2").submit();
}
</script>
</body>
</html>

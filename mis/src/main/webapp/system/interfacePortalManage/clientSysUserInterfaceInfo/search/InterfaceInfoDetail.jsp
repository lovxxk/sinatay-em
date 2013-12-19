<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"></link>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css"></link>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>电子商务后台管理系统-客户端用户和接口间关系信息详细信息</title>
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
				客户端用户和接口间关系信息详细信息
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
		<td class="td_head" width="200px" nowrap>客户端用户ID：</td>
		<td class="td_body" width="350px">${interfaceInfo.clientUser.id}</td>
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
		<form id="frmInput2" action="${ctx }/interfacePortal/deleteInterfaceInfoNew.do" target="myFrame" method="post">
			<input type="hidden" id="transCode" name="interfaceInfo.transCode" value="${interfaceInfo.transCode}">
		</form>	
		<iframe id="myFrame" name="myFrame" style="display: none"></iframe>	
	</div>
<script type="text/javascript">
//编辑
function doEdit(transCode){
	location.href = "${ctx}/interfacePortal/searchInterfaceInfoNew.do?interfaceInfo.transCode=" + transCode ;
}
function doDelete(){
	document.getElementById("frmInput2").submit();
}
</script>
</body>
</html>

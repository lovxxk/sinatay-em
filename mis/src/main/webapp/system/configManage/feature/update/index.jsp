<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="geFunctionSwitch" value="${requestScope.geFunctionSwitchForUpdate}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<title>���������̨����ϵͳ-�༭���ܿ�����Ϣ</title>
</head>
<body onload="pageValidate();">
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			<span id="title">�༭���ܿ�����Ϣ</span>
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/business/businessManage/feature/update.do" id="frmInput" method="post" target="myFrame">
	<table class="table_style" align="center" width="90%">
	<tr>
		<td class="td_head" width="230px" nowrap>���ܿ��ر��룺</td>
		<td class="td_body" nowrap>
			${geFunctionSwitch.functiontId}
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>���ܿ���״̬��</td>
		<td class="td_body" nowrap>
			<select id="status" name="geFunctionSwitch.status">
				<option value="">--��ѡ��--</option>
				<option value="0" ${geFunctionSwitch.status=="0"?"selected":""}>δ��ͨ</option>
				<option value="1" ${geFunctionSwitch.status=="1"?"selected":""}>��ͨ</option>
			</select>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>���ܿ���������</td>
		<td class="td_body" nowrap>
			<textarea rows="8" cols="40" id="functionInfo" name="geFunctionSwitch.functionInfo">${geFunctionSwitch.functionInfo}</textarea>
		</td>
	</tr>
	<tr height="60px">
		<td colspan=2>
			<table width=200 align="center">
			<tr>
				<acc:showView source="ROLE_S_FEAT_U">
					<td id="backButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doBack();" nowrap>����</td>
					<td>&nbsp;</td>
					<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doSubmit();" nowrap>�޸�</td>
					<td>&nbsp;</td>
					<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
					<td>&nbsp;</td>
				</acc:showView>
				<td id="backButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap>�ر�</td>
				<td>&nbsp;</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<input type="hidden" id="functiontId" name="geFunctionSwitch.functiontId" value=${geFunctionSwitch.functiontId }>
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</form>
</div>
<script type="text/javascript">
function doSubmit(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}

function doBack(){
	window.location  = "${ctx}/business/businessManage/feature/queryForShow.do?geFunctionSwitch.functiontId=${geFunctionSwitch.functiontId}";
}

function doClear(){
	document.getElementById("frmInput").reset();
}

function pageValidate(){
	tt.vf.req.addName("geFunctionSwitch.functionInfo", "geFunctionSwitch.status");
	new tt.LV().set(0,100).add("geFunctionSwitch.functionInfo");
}
</script>
</body>
</html>

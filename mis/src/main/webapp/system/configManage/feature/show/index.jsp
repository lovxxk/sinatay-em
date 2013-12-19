<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="geFunctionSwitch" value="${requestScope.geFunctionSwitchForShow}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<title>���������̨����ϵͳ-���ܿ�����ϸ��Ϣ</title>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			<span id="title">���ܿ�����ϸ��Ϣ</span>
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/business/businessManage/feature/update.do" id="frmInput" method="post" target="myFrame">
	<table class="table_style" align="center" width="600px">
	<tr>
		<td class="td_head" width="100px" nowrap>���ܿ��ر��룺</td>
		<td class="td_body" width="200px" nowrap>
			${geFunctionSwitch.functiontId}
		</td>
		<td class="td_head" width="100px"  nowrap>���ܿ���״̬��</td>
		<td class="td_body" width="200px"  nowrap>
			${geFunctionSwitch.status=="0"?"δ��ͨ":(geFunctionSwitch.status=="1"?"��ͨ":"")}
		</td>
	</tr>
	<tr height="10px"><td colspan="4">&nbsp;</td></tr>
	<tr>
		<td colspan="4" class="frmCreate_kuang" valign="top">
			<div class="frmCreate_kuang_header">
				���ܿ���������
			</div>
			<div style="padding-left:3px;height: 100px;width: 595px;">
				<textarea class="textarea_disabled" disabled>${geFunctionSwitch.functionInfo }</textarea>
			</div>
		</td>
	</tr>
	<tr height="60px">
		<td colspan=4>
			<table width=200 align="center">
			<tr>
				<acc:showView source="ROLE_S_FEAT_U">
					<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doEdit();" nowrap>�༭</td>
					<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_S_FEAT_D">
					<td id="deleteButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doDelete();" nowrap>ɾ��</td>
					<td>&nbsp;</td>
				</acc:showView>
				<td id="closeButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClose();" nowrap>�ر�</td>
				<td>&nbsp;</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</form>
</div>
<script type="text/javascript">
function doEdit(){
	window.location  = "${ctx}/business/businessManage/feature/queryForUpdate.do?geFunctionSwitch.functiontId=${geFunctionSwitch.functiontId}";
}

function doClose(){
	window.close();
}
function doDelete(){
	if(confirm("ȷ��ɾ���ù��ܿ�����")){
		$.ajax({
			url:contextRootPath+"/business/businessManage/feature/deleteAll.do",
			data:"idStr=" + '${geFunctionSwitch.functiontId}',
			async:false,
			dataType:"text",
			success:function(data){
				alert("ɾ���ɹ���");
				window.opener.parent.frames[0].doSearch();
				window.close();
			}
		});
	}
}
</script>
</body>
</html>

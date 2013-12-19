<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<style type="text/css">
	textarea{background-color:#ffffff;}
</style>
<title>���������̨����ϵͳ-��������ϸ��Ϣ</title>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			��������ϸ��Ϣ
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div> 
<div class="table_content">
	<div style="clear: both;"></div>
	<table class="table_style" align="center" width="650px" id="productTable">
	<tr>
		<td class="td_head" width="70px" align="right" nowrap>������</td>
		<td class="td_body" width="200px" nowrap>
			${geBlackList.userName}
		</td>
		<td class="td_head" width="80px" align="right" nowrap>֤�����ͣ�</td>
		<td class="td_body" width="276px" nowrap>
			<div style="float:left;">
				${identifyType }
			</div>
			<div id="identifyType_tip"></div>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�Ա�</td>
		<td class="td_body" nowrap>
			${sex}
		</td>
		<td class="td_head" nowrap>֤�����룺</td>
		<td class="td_body" nowrap>
			<div style="float:left;">
				${geBlackList.identifyNumber}
			</div>
			<div id="identifyNumber_tip" style="float: none;"></div>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>�������ڣ�</td>
		<td class="td_body" nowrap>
			<fmt:formatDate pattern="yyyy-MM-dd" value="${geBlackList.birthDay }"/>
		</td>
		<td class="td_head" nowrap>ҵ������</td>
		<td class="td_body" nowrap>
			${businessArea}
		</td>
	</tr>
	<tr height="10px"><td colspan="4">&nbsp;</td></tr>
	<tr>
		<td colspan="4" class="frmCreate_kuang" valign="top">
			<div class="frmCreate_kuang_header">
				���������ԭ��
			</div>
			<div style="padding-left:3px;height: 100px;width: 645px;">
				<textarea class="textarea_disabled" disabled>${geBlackList.reason }</textarea>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan=4>
			<table width=300 align="center">
			<tr>
				<acc:showView source="ROLE_B_BLIS_U">
					<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doEdit();" nowrap>�༭</td>
					<td>&nbsp;</td>
				</acc:showView>
				<acc:showView source="ROLE_B_BLIS_D">
					<td onclick="doDelete();" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'">ɾ��</td>
					<td>&nbsp;</td>
				</acc:showView>
				<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClose();" nowrap>�ر�</td>	
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	<form id="frmInput2" action="${ctx }/business/customerManage/blacklist/delete.do" target="myFrame" method="post">
		<input type="hidden" id="idStr" name="idStr" value="${geBlackList.id }">
	</form>
</div>
<script type="text/javascript">
function doEdit(){
	window.location = "${ctx }/business/customerManage/blacklist/queryGeBlackListForUpdate.do?geBlackList.id=${geBlackList.id }";
}

function doClose(){
	window.close();
}

function doDelete(){
	if(confirm("ȷ��ɾ���˺�������?"))
	document.getElementById("frmInput2").submit();
}

</script>
</body>
</html>

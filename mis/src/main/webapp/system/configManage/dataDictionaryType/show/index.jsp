<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<title>电子商务后台管理系统-数据字典详细</title>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			<span id="title">数据字典类型详细</span>
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<table class="table_style" align="center" width="90%" id="geCodeTable">
	<tr>
		<td class="td_head" width="200px" nowrap>代码类型：</td>
		<td class="td_body" width="200px">${geCodeType.codeType}</td>
		<td class="td_head" width="100px" nowrap>简体描述：</td>
		<td class="td_body" width="200px">${geCodeType.codeTypeCDesc}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>英文描述：</td>
		<td class="td_body" width="200px">
			${geCodeType.codeTypeEDesc}
		</td>
		<td class="td_head" width="100px" nowrap>繁体描述：</td>
		<td class="td_body" width="200px">
			${geCodeType.codeTypeTDesc}
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>业务领域：</td>
		<td class="td_body" width="200px">
			${businessArea}
		</td>
		<td class="td_head" width="100px" nowrap>&nbsp;</td>
		<td class="td_body" width="200px">
			&nbsp;
		</td>
	</tr>
	<tr height="10px"><td colspan=4>&nbsp;</td></tr>
	<tr>
		<td colspan=4>
			<table width=200 align="center">
			<tr>
				<acc:showView source="ROLE_S_DDICT_U">
					<td id="updateButton" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="toUpdate();" nowrap>编辑</td>
					<td width=5>&nbsp;<input type="hidden" id="updateFlag" value="1"></td>
				</acc:showView>
				<acc:showView source="ROLE_S_DDICT_D">
					<td id="updateButton" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doDelete();" nowrap>删除</td>
				</acc:showView>
				<td id="updateButton" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClose();" nowrap>关闭</td>
				<td>&nbsp;</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
		<form action="" id="frmInput" method="post"  target="myFrame">
	<input type="hidden"  name="geCodeType.codeType" value="${geCodeType.codeType}"/>
	</form>
	<iframe id="myFrame" name="myFrame" style="display: none">

	</iframe>
</div>
<script type="text/javascript">
function doDelete(){
	if(confirm("确定删除此数据字典类型吗？")){
	$("#frmInput")[0].action = "${ctx}/business/businessManage/dataDictionary/deleteGeCodeTypeById.do";
	$("#frmInput")[0].submit();
	}
}
function toUpdate(){
	window.location = "${ctx}/business/businessManage/dataDictionary/queryForUpdateType.do?geCodeType.codeType=${geCodeType.codeType}";
}

function doClose(){
	window.close();
}
</script>
</body>
</html>

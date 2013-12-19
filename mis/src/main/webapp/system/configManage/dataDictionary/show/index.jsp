<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="geCode" value="${requestScope.geCodeForShow}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>电子商务后台管理系统-数据字典详细</title>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			<span id="title">数据字典详细</span>
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<table class="table_style" align="center" width="90%" id="geCodeTable">
	<tr>
		<td class="td_head" width="200px" nowrap>代码：</td>
		<td class="td_body" width="200px">${geCode.id.codeCode}</td>
		<td class="td_head" width="100px" nowrap>代码类型：</td>
		<td class="td_body" width="200px">${codeAndDescMap[geCode.id.codeType]}</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>显示序号：</td>
		<td class="td_body" width="200px">
			${geCode.orderNo}
		</td>
		<td class="td_head" width="100px" nowrap>代码中文名：</td>
		<td class="td_body" width="200px">
			${geCode.codeCName}
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>代码英文名：</td>
		<td class="td_body" width="200px">
			${geCode.codeEName}
		</td>
		<td class="td_head" width="100px" nowrap>代码繁体名：</td>
		<td class="td_body" width="200px">
			${geCode.codeTName}
		</td>
	</tr>
	<tr>
		<td class="td_head" width="200px" nowrap>是否有效：</td>
		<td class="td_body" width="200px">
			<c:if test="${geCode.validInd eq '1'}">有效</c:if>
			<c:if test="${geCode.validInd eq '0'}">无效</c:if>
		</td>
		<td class="td_head" width="100px" nowrap>&nbsp;</td>
		<td class="td_body" width="200px">&nbsp;</td>
	</tr>
	<tr height="10px"><td colspan=4>&nbsp;</td></tr>
	<tr>
		<td colspan=4>
			<table width=200 align="center">
			<tr>
				<acc:showView source="ROLE_S_DDIC_U">
					<td id="updateButton" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="toUpdate();" nowrap>编辑</td>
					<input type="hidden" id="updateFlag" value="1">
				</acc:showView>
				<acc:showView source="ROLE_S_DDIC_D">
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
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</div>
<script type="text/javascript">
function doDelete(){
	if(confirm("确定删除此数据字典吗？")){
	window.location = "${ctx}/business/businessManage/dataDictionary/deleteGeCodeById.do?geCode.id.codeCode=${geCode.id.codeCode}&geCode.id.codeType=${geCode.id.codeType}";
	}
}
function toUpdate(){
	window.location = "${ctx}/business/businessManage/dataDictionary/queryForUpdate.do?geCode.id.codeType=${geCode.id.codeCode},${geCode.id.codeType}";
}

function doClose(){
	window.close();
}
</script>
</body>
</html>

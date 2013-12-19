<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="geCode" value="${requestScope.geCodeForUpdate}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<title>电子商务后台管理系统-编辑数据字典</title>
</head>
<body onload="pageValidate();">
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			<span id="title">编辑数据字典</span>
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/business/businessManage/dataDictionary/update.do" id="frmInput" method="post" target="myFrame">
	<table class="table_style" align="center" width="90%" id="geCodeTable">
	<tr>
		<td class="td_head" width="340px"  nowrap>代码：</td>
		<td class="td_body" >${geCode.id.codeCode}</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>代码类型：</td>
		<td class="td_body" >${codeAndDescMap[geCode.id.codeType]}</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>显示序号：</td>
		<td class="td_body" >
			<input id="orderNo" name="geCode.orderNo" type="text" style="width:170px;" maxlength=6  value="${geCode.orderNo}">
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>代码中文名：</td>
		<td class="td_body" >
			<input id="codeCName" name="geCode.codeCName" type="text" style="width:170px;" value="${geCode.codeCName}" maxlength=150 >
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>代码英文名：</td>
		<td class="td_body" >
			<input id="codeEName" name="geCode.codeEName" type="text" style="width:170px;" value="${geCode.codeEName}" maxlength=150 >
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>代码繁体名：</td>
		<td class="td_body" >
			<input id="codeTName" name="geCode.codeTName" type="text" style="width:170px;" value="${geCode.codeTName}" maxlength=150 >
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>是否有效：</td>
		<td class="td_body" >
			<select id="validInd" name="geCode.validInd" style="width:100px;">
				<option value="1" "<c:if test="${geCode.validInd eq '1'}">selected</c:if>" >有效</option>
				<option value="0" "<c:if test="${geCode.validInd eq '0'}">selected</c:if>" >无效</option>
			</select>
		</td>
	</tr>
	<tr height="10px"><td colspan="2">&nbsp;</td></tr>
	<tr>
		<td colspan=2>
			<table width=200 align="center">
			<tr>
				<acc:showView source="ROLE_S_DDIC_U">
					<td id="backButton" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doBack();" nowrap>返回</td>
					<td>&nbsp;</td>	
					<td id="updateButton" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doUpdate();" nowrap>修改</td>
					<td>&nbsp;<input type="hidden" id="updateFlag" value="1"></td>
					<td id="updateButton" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
					<td>&nbsp;</td>
				</acc:showView>
				<td id="backButton" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
						onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap>关闭</td>
				<td>&nbsp;</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
<!-- 	<input type="hidden" name="geCode.validInd" value="1"/> -->
	<input type="hidden" id="codeCode" name="geCode.id.codeCode" value=${geCode.id.codeCode }>
	<input type="hidden" id="codeType" name="geCode.id.codeType" value=${geCode.id.codeType }>
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</form>
</div>
<script type="text/javascript">
function doUpdate(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}

function doBack(){
	window.location = "${ctx}/business/businessManage/dataDictionary/queryForShow.do?geCode.id.codeType=${geCode.id.codeCode},${geCode.id.codeType}";
}

function doClear(){
	document.getElementById("frmInput").reset();
}
function pageValidate(){
	tt.vf.req.addName("geCode.codeCName");
	tt.vf.int.addName("geCode.orderNo");
}
</script>
</body>
</html>

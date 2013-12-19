<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<title>电子商务后台管理系统-编辑数据字典类型</title>
</head>
<body onload="pageValidate();">
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			<span id="title">编辑数据字典类型</span>
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx }/business/businessManage/dataDictionary/updateGeCodeType.do" id="frmInput" method="post" target="myFrame">
<table class="table_style" align="center" width="570" id="geCodeTable">
<tr>
	<td class="td_head"  width="200px" nowrap>代码类型：</td>
	<td class="td_body" width="370px">${geCodeType.codeType}
		<input id="codeType" name="geCodeType.codeType" type="hidden" style="width:170px;" maxlength=150 value="${geCodeType.codeType}">
	</td>
</tr>
<tr>
	<td class="td_head"  nowrap>简体描述：</td>
	<td class="td_body"  >
		<input id="codeTypeCDesc" name="geCodeType.codeTypeCDesc" value="${geCodeType.codeTypeCDesc}" type="text" style="width:170px;" maxlength=150 >
	</td>
</tr>
<tr>
	<td class="td_head" nowrap>英文描述：</td>
	<td class="td_body" >
		<input id="codeTypeEDesc" name="geCodeType.codeTypeEDesc" type="text" value="${geCodeType.codeTypeEDesc}" style="width:170px;" maxlength=150 >
	</td>
</tr>
<tr>
	<td class="td_head" nowrap>繁体描述：</td>
	<td class="td_body" >
		<input id="codeTypeTDesc" name="geCodeType.codeTypeTDesc" type="text" value="${geCodeType.codeTypeTDesc}" style="width:170px;" maxlength=300 >
	</td>
</tr>
<tr>
	<td class="td_head" style="text-align: right;" nowrap>业务领域：</td>
		<td class="td_body" >
			<select id="businessArea" name="geCodeType.businessArea"  style="width:170px;" >
				<option value="">--请选择--</option>
				<c:forEach items="${bussList}" var="bussArea" step="1" varStatus="status">
					<option value="${bussArea.id.codeCode}">${bussArea.codeCName}</option>
				</c:forEach>
			</select>
</tr>
<input type="hidden" name="geCodeType.validInd" value="1"/>
<input type="hidden" name="geCodeType.typeInd" value="0"/>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	<tr height="10px"><td colspan="2">&nbsp;</td></tr>
<tr>
		<td colspan=2>
			<table width="200" align="center">
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
	</form>
</div>
<script type="text/javascript">
$("#businessArea").val("${geCodeType.businessArea}");
function doUpdate(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}

function doBack(){
	window.location = "${ctx}/business/businessManage/dataDictionary/queryForShowType.do?geCodeType.codeType=${geCodeType.codeType}";
	
}

function doClear(){
	document.getElementById("frmInput").reset();
	$("#businessArea").val("${geCodeType.businessArea}");
}
function pageValidate(){
	tt.vf.req.addName("geCodeType.codeTypeCDesc","geCodeType.codeTypeTDesc","geCodeType.codeTypeEDesc");
	tt.vf.int.addName("geCodeType.orderNo");
}
</script>
</body>
</html>

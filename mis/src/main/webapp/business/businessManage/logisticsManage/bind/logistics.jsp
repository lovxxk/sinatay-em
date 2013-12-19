<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>电子商务后台管理系统-物流维护</title>
</head>
<body  onload="pageValidate();">
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			运单绑定
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content" STYLE="padding-top:20px;">
	<div style="clear: both;"></div>
	<form action="${ctx}/business/businessManage/onlineOrderManage/bindWayBill.do" id="frmInput" method="post" target="myFrame">
	<table align="center" width="550px" border="0" id="waybillTable">
	<tr>
		<td class="td_head" width="120px" nowrap>物流公司：</td>
		<td class="td_body" width="330px" >
			<select id="thirdParterID" name="geThirdParterOrder.geThirdParterInfo.thirdParterID" style="width:170ox;">
				<option value="">--请选择--</option>
				<c:forEach items="${geThirdParterInfoList }" var="thirdParter">
					<option value="${thirdParter.thirdParterID }" ${thirdParter.thirdParterID==geThirdParterOrder.geThirdParterInfo.thirdParterID?"selected":"" } >${thirdParter.thirdParterName }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>运单号：</td>
		<td class="td_body" width="330px" >
			<input id="wayBillNo" name="geThirdParterOrder.wayBillNo" value="${geThirdParterOrder.wayBillNo}" type="text" style="width:170px;"  maxlength=32>
		</td>
	</tr>
	<tr>
		<td colspan=2>
			<table width=200 align="center">
			<tr>
				<td id="updateButton" align=right class="btnBigOnFocus" onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'"  onclick="doBind();" nowrap>完成</td>
				<td width=5>&nbsp;</td>
				<td id="resetButton" class="btnBigOnFocus" align=right onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'"  onclick="doClear();" nowrap>取消</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<input type="hidden" id="orderNo" name="geThirdParterOrder.orderNo" value="${geOrder.orderNo }">
	</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">
function doBind(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}

function doClear(){
	window.close();
}

function pageValidate(){
	tt.vf.req.add("geThirdParterOrder.geThirdParterInfo.thirdParterID","geThirdParterOrder.wayBillNo");
}
</script>
</html>
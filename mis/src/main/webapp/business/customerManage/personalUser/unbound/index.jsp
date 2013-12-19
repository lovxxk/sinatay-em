<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/mis_basic.css">
<title>电子商务后台管理系统-保单解绑</title>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
</head>
<body onload="pageValidate();">
<div>
	<div id="header_titleDIV">
		<div class="header_title_c">
			<div class="header_title">
				保单解绑
			</div>
		</div>
		<div class="header_title_gap1"></div>
	</div>
	<div class="table_content">
		<div style="clear: both;"></div>
		<form id="frmInput" method="post" action="${ctx}/business/customerManage/personalUser/unBoundPolicy.do" target="myFrame">
			<table class="table_style" align="center" width="800px" id="productTable">
				<tr>
					<td class="td_head" nowrap>
						姓名：
					</td>
					<td class="td_body">
						<input type="text" id="name" name="lifeInsurancePolicy.name" style="width:170px;">
					</td>
				</tr>
				
				<tr>
					<td class="td_head" nowrap>
						性别：
					</td>
					<td class="td_body">
						<input type="radio" id="sex" name="lifeInsurancePolicy.sex" checked="checked">男
						<input type="radio" id="sex" name="lifeInsurancePolicy.sex" >女
					</td>
				</tr>
				
				<tr>
					<td class="td_head" nowrap>
						证件类型：
					</td>
					<td class="td_body">
						<input type="text" id="identifyType" name="lifeInsurancePolicy.idType" style="width:170px;">
					</td>
				</tr>
				
				<tr>
					<td class="td_head" nowrap>
						证件号码：
					</td>
					<td class="td_body">
						<input type="text" id="identifyNumber" name="lifeInsurancePolicy.idNo" style="width:170px;">
					</td>
				</tr>
				
				<tr>
					<td class="td_head" nowrap>出生日期：</td>
					<td class="td_body">
						<input name="lifeInsurancePolicy.birthday" size="25" id=birthday readonly onclick="WdatePicker({maxDate:'%y-%M-%d'})" class="Wdate"  style="width:170px;">
					</td>
				</tr>
				
				<tr>
					<td class="td_head" nowrap>
						保单号：
					</td>
					<td class="td_body">
						<input type="text" id="policyNo" name="lifeInsurancePolicy.policyNo" style="width:170px;">
					</td>
				</tr>
				
				<tr height="20px"><td>&nbsp;</td></tr>
				<tr>
					<td colspan="2" align="center">
						<table width=200 align="center">
							<tr>
								<td class="btnBigOnFocus" onclick="doUnBound();" nowrap>解绑</td>
								<td class="btnBigOnFocus" onclick="doClear();" nowrap>清空</td>	
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
		</form>
	</div>
</div>
</body>
<script type="text/javascript">
function doUnBound(){
	if(tt.validate()){
		document.getElementById("frmInput").submit();
	}
}

function doClear(){
	document.getElementById("updateAllForm").reset();
}

function pageValidate(){
}

</script>
</html>

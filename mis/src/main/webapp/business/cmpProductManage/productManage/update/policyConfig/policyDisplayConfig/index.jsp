<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=GBK">
<link href="${ctx }/global/css/misBasic.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<title>电子商务管理系统-保单展示配置项</title>
<script type="text/javascript">
$(document).ready(function(){
	// 在这里写你的代码...
	$("input[type='checkbox']").addClass("checkbox_border");
	init();
});

function init(){
	// 保单展示配置项
	var productname = "<s:property value='geProductPolicyDisplayConfig.productName' />";
	var benefitperiod = "<s:property value='geProductPolicyDisplayConfig.benefitPeriod' />";
	var benefitperiodtype = "<s:property value='geProductPolicyDisplayConfig.benefitPeriodType' />";
	var insuredamount = "<s:property value='geProductPolicyDisplayConfig.insuredAmount' />";
	var premium = "<s:property value='geProductPolicyDisplayConfig.premium' />";
	var paymentduration = "<s:property value='geProductPolicyDisplayConfig.paymentDuration' />";
	var paymentdurationmode = "<s:property value='geProductPolicyDisplayConfig.paymentDurationMode' />";
	var inceptiondate = "<s:property value='geProductPolicyDisplayConfig.inceptionDate' />";
	var comboname = "<s:property value='geProductPolicyDisplayConfig.comboName' />";
	var campaignname = "<s:property value='geProductPolicyDisplayConfig.campaignName' />";
	var unitcount = "<s:property value='geProductPolicyDisplayConfig.unitCount' />";
	
	if(productname == 1){
		$("input[id='geProductPolicyDisplayConfig.productName']").attr("checked", true);
		$("input[id='geProductPolicyDisplayConfig.productName']").attr("value","1");
	}
	if(benefitperiod == 1){
		$("input[id='geProductPolicyDisplayConfig.benefitPeriod']").attr("checked", true);
		$("input[id='geProductPolicyDisplayConfig.benefitPeriod']").attr("value","1");
	}
	if(benefitperiodtype == 1){
		$("input[id='geProductPolicyDisplayConfig.benefitPeriodType']").attr("checked", true);
		$("input[id='geProductPolicyDisplayConfig.benefitPeriodType']").attr("value","1");
	}
	if(insuredamount == 1){
		$("input[id='geProductPolicyDisplayConfig.insuredAmount']").attr("checked", true);
		$("input[id='geProductPolicyDisplayConfig.insuredAmount']").attr("value","1");
	}
	if(premium == 1){
		$("input[id='geProductPolicyDisplayConfig.premium']").attr("checked", true);
		$("input[id='geProductPolicyDisplayConfig.premium']").attr("value","1");
	}
	if(paymentduration == 1){
		$("input[id='geProductPolicyDisplayConfig.paymentDuration']").attr("checked", true);
		$("input[id='geProductPolicyDisplayConfig.paymentDuration']").attr("value","1");
	}
	if(paymentdurationmode == 1){
		$("input[id='geProductPolicyDisplayConfig.paymentDurationMode']").attr("checked", true);
		$("input[id='geProductPolicyDisplayConfig.paymentDurationMode']").attr("value","1");
	}
	if(inceptiondate == 1){
		$("input[id='geProductPolicyDisplayConfig.inceptionDate']").attr("checked", true);
		$("input[id='geProductPolicyDisplayConfig.inceptionDate']").attr("value","1");
	}
	if(comboname == 1){
		$("input[id='geProductPolicyDisplayConfig.comboName']").attr("checked", true);
		$("input[id='geProductPolicyDisplayConfig.comboName']").attr("value","1");
	}
	if(campaignname == 1){
		$("input[id='geProductPolicyDisplayConfig.campaignName']").attr("checked", true);
		$("input[id='geProductPolicyDisplayConfig.campaignName']").attr("value","1");
	}
	if(unitcount == 1){
		$("input[id='geProductPolicyDisplayConfig.unitCount']").attr("checked", true);
		$("input[id='geProductPolicyDisplayConfig.unitCount']").attr("value","1");
	}
	
}

function showRequeriedIterm(obj){
	if(obj.checked == true){
		obj.value = "1";
	}else{
		obj.value = "0";
	}
}
</script>
</head>
<body>
<div class="table_content">
<form id="frmInput" name="frmInput" method="post" action="${ctx }/productManage/saveOrUpdatePolicyDisplayConfig.do" target="myFrame">
<input type="hidden" id="geProductMain.coreProductCode" name="geProductMain.coreProductCode" value="<s:property value='geProductMain.coreProductCode' />">
		<div style=" width: 550px;margin: 0 auto;">
		<!-- 保单展示配置项  -->
			<table>
			<tr>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.productName" name="geProductPolicyDisplayConfig.productName" value="0" onclick="showRequeriedIterm(this);" />产品名称</td>
				<td width="30">&nbsp;</td>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.benefitPeriod" name="geProductPolicyDisplayConfig.benefitPeriod" value="0" onclick="showRequeriedIterm(this);" />保障年期</td>
				<td width="30">&nbsp;</td>
			</tr>
			<tr>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.benefitPeriodType" name="geProductPolicyDisplayConfig.benefitPeriodType" value="0" onclick="showRequeriedIterm(this);" />保障年期类型</td>
				<td width="30">&nbsp;</td>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.insuredAmount" name="geProductPolicyDisplayConfig.insuredAmount" value="0" onclick="showRequeriedIterm(this);" />保额</td>
				<td width="30">&nbsp;</td>
			</tr>
			<tr>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.premium" name="geProductPolicyDisplayConfig.premium" value="0" onclick="showRequeriedIterm(this);" />保费</td>
				<td width="30">&nbsp;</td>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.paymentDuration" name="geProductPolicyDisplayConfig.paymentDuration" value="0" onclick="showRequeriedIterm(this);" />缴费年期</td>
				<td width="30">&nbsp;</td>
			</tr>
			<tr>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.paymentDurationMode" name="geProductPolicyDisplayConfig.paymentDurationMode" value="0" onclick="showRequeriedIterm(this);" />缴费年期类型</td>
				<td width="30">&nbsp;</td>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.inceptionDate" name="geProductPolicyDisplayConfig.inceptionDate" value="0" onclick="showRequeriedIterm(this);" />保单生效日</td>
				<td width="30">&nbsp;</td>
			</tr>
			<tr>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.comboName" name="geProductPolicyDisplayConfig.comboName" value="0" onclick="showRequeriedIterm(this);" />套餐名称</td>
				<td width="30">&nbsp;</td>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.campaignName" name="geProductPolicyDisplayConfig.campaignName" value="0" onclick="showRequeriedIterm(this);" />活动名称</td>
				<td width="30">&nbsp;</td>
			</tr>
			<tr>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.unitCount" name="geProductPolicyDisplayConfig.unitCount" value="0" onclick="showRequeriedIterm(this);" />份数</td>
				<td width="30">&nbsp;</td>
			</tr>
			<tr></tr>
			</table>
		<div>
			<table style="align:center;margin: 0 auto;margin-top: 15px;">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>保存</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.parent.parent.close();" nowrap >关闭</td>
				</tr>
			</table>
		</div>
	</div>
</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
<!-- 
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.transSerialNumber" name="geProductPolicyDisplayConfig.transSerialNumber" value="0" onclick="showRequeriedIterm(this);" />交易流水号&nbsp;&nbsp;</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.policySerialNumber" name="geProductPolicyDisplayConfig.policySerialNumber" value="0" onclick="showRequeriedIterm(this);" />保单号</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.applicationNumber" name="geProductPolicyDisplayConfig.applicationNumber" value="0" onclick="showRequeriedIterm(this);" />投保单号</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.applicationSerialNumber" name="geProductPolicyDisplayConfig.applicationSerialNumber" value="0" onclick="showRequeriedIterm(this);" />投保单印刷号</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.policyStatus" name="geProductPolicyDisplayConfig.policyStatus" value="0" onclick="showRequeriedIterm(this);" />保单状态</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.insuredAmount" name="geProductPolicyDisplayConfig.insuredAmount" value="0" onclick="showRequeriedIterm(this);" />保额</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.premium" name="geProductPolicyDisplayConfig.premium" value="0" onclick="showRequeriedIterm(this);" />保费</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.discountPremium" name="geProductPolicyDisplayConfig.discountPremium" value="0" onclick="showRequeriedIterm(this);" />折扣保费</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.grossPremium" name="geProductPolicyDisplayConfig.grossPremium" value="0" onclick="showRequeriedIterm(this);" />总保费</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.faceAmount" name="geProductPolicyDisplayConfig.faceAmount" value="0" onclick="showRequeriedIterm(this);" />总保额</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.productCode" name="geProductPolicyDisplayConfig.productCode" value="0" onclick="showRequeriedIterm(this);" />产品代码</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.productName" name="geProductPolicyDisplayConfig.productName" value="0" onclick="showRequeriedIterm(this);" />产品名称</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.firstPremium" name="geProductPolicyDisplayConfig.firstPremium" value="0" onclick="showRequeriedIterm(this);" />首期保费</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.initialPremAmt" name="geProductPolicyDisplayConfig.initialPremAmt" value="0" onclick="showRequeriedIterm(this);" />首次缴费金额</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.benefitPeriod" name="geProductPolicyDisplayConfig.benefitPeriod" value="0" onclick="showRequeriedIterm(this);" />保障年期</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.benefitPeriodType" name="geProductPolicyDisplayConfig.benefitPeriodType" value="0" onclick="showRequeriedIterm(this);" />保障年期类型</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.paymentDuration" name="geProductPolicyDisplayConfig.paymentDuration" value="0" onclick="showRequeriedIterm(this);" />缴费年期</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.paymentDurationMode" name="geProductPolicyDisplayConfig.paymentDurationMode" value="0" onclick="showRequeriedIterm(this);" />缴费年期类型</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.paymentMethod" name="geProductPolicyDisplayConfig.paymentMethod" value="0" onclick="showRequeriedIterm(this);" />支付方式</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.benefitMode" name="geProductPolicyDisplayConfig.benefitMode" value="0" onclick="showRequeriedIterm(this);" />生存金领取方式</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.divType" name="geProductPolicyDisplayConfig.divType" value="0" onclick="showRequeriedIterm(this);" />红利领取方式</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.excessPremAmt" name="geProductPolicyDisplayConfig.excessPremAmt" value="0" onclick="showRequeriedIterm(this);" />附加保费/手续金额</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.policyDeliveryFee" name="geProductPolicyDisplayConfig.policyDeliveryFee" value="0" onclick="showRequeriedIterm(this);" />纸质保单快递费</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.signedDate" name="geProductPolicyDisplayConfig.signedDate" value="0" onclick="showRequeriedIterm(this);" />签约日期</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.inceptionDate" name="geProductPolicyDisplayConfig.inceptionDate" value="0" onclick="showRequeriedIterm(this);" />保单生效日</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.applicationDate" name="geProductPolicyDisplayConfig.applicationDate" value="0" onclick="showRequeriedIterm(this);" />保单申请日</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.insuranceStartPeriod" name="geProductPolicyDisplayConfig.insuranceStartPeriod" value="0" onclick="showRequeriedIterm(this);" />保险起期</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.insuranceEndPeriod" name="geProductPolicyDisplayConfig.insuranceEndPeriod" value="0" onclick="showRequeriedIterm(this);" />保险止期</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.beneficiaryMode" name="geProductPolicyDisplayConfig.beneficiaryMode" value="0" onclick="showRequeriedIterm(this);" />受益方式</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.submissionDate" name="geProductPolicyDisplayConfig.submissionDate" value="0" onclick="showRequeriedIterm(this);" />投保受理日期</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.unitCount" name="geProductPolicyDisplayConfig.unitCount" value="0" onclick="showRequeriedIterm(this);" />份数</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.specialStatement" name="geProductPolicyDisplayConfig.specialStatement" value="0" onclick="showRequeriedIterm(this);" />特别声明</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.campaignCode" name="geProductPolicyDisplayConfig.campaignCode" value="0" onclick="showRequeriedIterm(this);" />活动代码</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.campaignName" name="geProductPolicyDisplayConfig.campaignName" value="0" onclick="showRequeriedIterm(this);" />活动名称</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.discountTypeCode" name="geProductPolicyDisplayConfig.discountTypeCode" value="0" onclick="showRequeriedIterm(this);" />折扣代码</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.discountRate" name="geProductPolicyDisplayConfig.discountRate" value="0" onclick="showRequeriedIterm(this);" />折扣系数</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.comboCode" name="geProductPolicyDisplayConfig.comboCode" value="0" onclick="showRequeriedIterm(this);" />套餐代码</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.comboName" name="geProductPolicyDisplayConfig.comboName" value="0" onclick="showRequeriedIterm(this);" />套餐名称</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.personalUserSerialNo" name="geProductPolicyDisplayConfig.personalUserSerialNo" value="0" onclick="showRequeriedIterm(this);" />个人客户</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.agentCode" name="geProductPolicyDisplayConfig.agentCode" value="0" onclick="showRequeriedIterm(this);" />代理人代码</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.agreementNo" name="geProductPolicyDisplayConfig.agreementNo" value="0" onclick="showRequeriedIterm(this);" />代理人协议号</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.agentName" name="geProductPolicyDisplayConfig.agentName" value="0" onclick="showRequeriedIterm(this);" />代理人姓名</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.departmentNo" name="geProductPolicyDisplayConfig.departmentNo" value="0" onclick="showRequeriedIterm(this);" />代理人所在机构编码</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.departmentName" name="geProductPolicyDisplayConfig.departmentName" value="0" onclick="showRequeriedIterm(this);" />代理人所在机构名称</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.intermediaryCode" name="geProductPolicyDisplayConfig.intermediaryCode" value="0" onclick="showRequeriedIterm(this);" />渠道代码</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.intermediaryName" name="geProductPolicyDisplayConfig.intermediaryName" value="0" onclick="showRequeriedIterm(this);" />渠道名称</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.branchCode" name="geProductPolicyDisplayConfig.branchCode" value="0" onclick="showRequeriedIterm(this);" />网点代码</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.branchName" name="geProductPolicyDisplayConfig.branchName" value="0" onclick="showRequeriedIterm(this);" />网点代码</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.organizationCode" name="geProductPolicyDisplayConfig.organizationCode" value="0" onclick="showRequeriedIterm(this);" />机构代码</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.organizationName" name="geProductPolicyDisplayConfig.organizationName" value="0" onclick="showRequeriedIterm(this);" />机构名称</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.institutionCode" name="geProductPolicyDisplayConfig.institutionCode" value="0" onclick="showRequeriedIterm(this);" />合作机构代码</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.institutionName" name="geProductPolicyDisplayConfig.institutionName" value="0" onclick="showRequeriedIterm(this);" />合作机构名称</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.cashValue" name="geProductPolicyDisplayConfig.cashValue" value="0" onclick="showRequeriedIterm(this);" />现金价值</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.preserveAcceptTime" name="geProductPolicyDisplayConfig.preserveAcceptTime" value="0" onclick="showRequeriedIterm(this);" />保全受理时间</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.preserveEffectiveTime" name="geProductPolicyDisplayConfig.preserveEffectiveTime" value="0" onclick="showRequeriedIterm(this);" />保全生效时间</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.reFundPolicyTime" name="geProductPolicyDisplayConfig.reFundPolicyTime" value="0" onclick="showRequeriedIterm(this);" />撤单时间</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.reFundPolicySuccessTime" name="geProductPolicyDisplayConfig.reFundPolicySuccessTime" value="0" onclick="showRequeriedIterm(this);" />撤单成功时间</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.exPayMode" name="geProductPolicyDisplayConfig.exPayMode" value="0" onclick="showRequeriedIterm(this);" />续期缴费形式</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.getPolMode" name="geProductPolicyDisplayConfig.getPolMode" value="0" onclick="showRequeriedIterm(this);" />保单递送方式</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.specContent" name="geProductPolicyDisplayConfig.specContent" value="0" onclick="showRequeriedIterm(this);" />特别约定</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.tempFeeNo" name="geProductPolicyDisplayConfig.tempFeeNo" value="0" onclick="showRequeriedIterm(this);" />发票印刷号码</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.agentGroup" name="geProductPolicyDisplayConfig.agentGroup" value="0" onclick="showRequeriedIterm(this);" />代理人组别</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.disputedFlag" name="geProductPolicyDisplayConfig.disputedFlag" value="0" onclick="showRequeriedIterm(this);" />合同争议处理方式</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.acName" name="geProductPolicyDisplayConfig.acName" value="0" onclick="showRequeriedIterm(this);" />仲裁委员会名称</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.isVisit" name="geProductPolicyDisplayConfig.isVisit" value="0" onclick="showRequeriedIterm(this);" />是否回访</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.isBind" name="geProductPolicyDisplayConfig.isBind" value="0" onclick="showRequeriedIterm(this);" />绑定标志</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.renewalFlag" name="geProductPolicyDisplayConfig.renewalFlag" value="0" onclick="showRequeriedIterm(this);" />续保标志</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.currency" name="geProductPolicyDisplayConfig.currency" value="0" onclick="showRequeriedIterm(this);" />币别</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.businessArea" name="geProductPolicyDisplayConfig.businessArea" value="0" onclick="showRequeriedIterm(this);" />业务领域</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.reason" name="geProductPolicyDisplayConfig.reason" value="0" onclick="showRequeriedIterm(this);" />原因</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.precheckDate" name="geProductPolicyDisplayConfig.precheckDate" value="0" onclick="showRequeriedIterm(this);" />核保\承保时间</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.businessSource" name="geProductPolicyDisplayConfig.businessSource" value="0" onclick="showRequeriedIterm(this);" />业务来源</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.groupChannel" name="geProductPolicyDisplayConfig.groupChannel" value="0" onclick="showRequeriedIterm(this);" />渠道代码</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.sellType" name="geProductPolicyDisplayConfig.sellType" value="0" onclick="showRequeriedIterm(this);" />销售类型</td>
	<td width="30">&nbsp;</td>
</tr>
 -->
</body>
<script type="text/javascript">
	function doCreate(){
// 		alert($("input[id='geProductPolicyDisplayConfig.productname']").attr("value"));
// 		alert($("input[id='geProductPolicyDisplayConfig.benefitperiod']").attr("value"));
// 		alert($("input[id='geProductPolicyDisplayConfig.benefitperiodtype']").attr("value"));
// 		alert($("input[id='geProductPolicyDisplayConfig.insuredamount']").attr("value"));
// 		alert($("input[id='geProductPolicyDisplayConfig.premium']").attr("value"));
// 		alert($("input[id='geProductPolicyDisplayConfig.paymentduration']").attr("value"));
// 		alert($("input[id='geProductPolicyDisplayConfig.paymentdurationmode']").attr("value"));
// 		alert($("input[id='geProductPolicyDisplayConfig.inceptiondate']").attr("value"));
// 		alert($("input[id='geProductPolicyDisplayConfig.comboname']").attr("value"));
// 		alert($("input[id='geProductPolicyDisplayConfig.campaignname']").attr("value"));
// 		alert($("input[id='geProductPolicyDisplayConfig.unitcount']").attr("value"));
		
		document.getElementById("frmInput").submit();
	}
	function doClear(){
		document.getElementById("frmInput").reset();
	}
</script>
</html>

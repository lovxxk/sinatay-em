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
<title>�����������ϵͳ-����չʾ������</title>
<script type="text/javascript">
$(document).ready(function(){
	// ������д��Ĵ���...
	$("input[type='checkbox']").addClass("checkbox_border");
	init();
});

function init(){
	// ����չʾ������
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
		<!-- ����չʾ������  -->
			<table>
			<tr>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.productName" name="geProductPolicyDisplayConfig.productName" value="0" onclick="showRequeriedIterm(this);" />��Ʒ����</td>
				<td width="30">&nbsp;</td>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.benefitPeriod" name="geProductPolicyDisplayConfig.benefitPeriod" value="0" onclick="showRequeriedIterm(this);" />��������</td>
				<td width="30">&nbsp;</td>
			</tr>
			<tr>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.benefitPeriodType" name="geProductPolicyDisplayConfig.benefitPeriodType" value="0" onclick="showRequeriedIterm(this);" />������������</td>
				<td width="30">&nbsp;</td>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.insuredAmount" name="geProductPolicyDisplayConfig.insuredAmount" value="0" onclick="showRequeriedIterm(this);" />����</td>
				<td width="30">&nbsp;</td>
			</tr>
			<tr>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.premium" name="geProductPolicyDisplayConfig.premium" value="0" onclick="showRequeriedIterm(this);" />����</td>
				<td width="30">&nbsp;</td>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.paymentDuration" name="geProductPolicyDisplayConfig.paymentDuration" value="0" onclick="showRequeriedIterm(this);" />�ɷ�����</td>
				<td width="30">&nbsp;</td>
			</tr>
			<tr>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.paymentDurationMode" name="geProductPolicyDisplayConfig.paymentDurationMode" value="0" onclick="showRequeriedIterm(this);" />�ɷ���������</td>
				<td width="30">&nbsp;</td>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.inceptionDate" name="geProductPolicyDisplayConfig.inceptionDate" value="0" onclick="showRequeriedIterm(this);" />������Ч��</td>
				<td width="30">&nbsp;</td>
			</tr>
			<tr>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.comboName" name="geProductPolicyDisplayConfig.comboName" value="0" onclick="showRequeriedIterm(this);" />�ײ�����</td>
				<td width="30">&nbsp;</td>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.campaignName" name="geProductPolicyDisplayConfig.campaignName" value="0" onclick="showRequeriedIterm(this);" />�����</td>
				<td width="30">&nbsp;</td>
			</tr>
			<tr>
				<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.unitCount" name="geProductPolicyDisplayConfig.unitCount" value="0" onclick="showRequeriedIterm(this);" />����</td>
				<td width="30">&nbsp;</td>
			</tr>
			<tr></tr>
			</table>
		<div>
			<table style="align:center;margin: 0 auto;margin-top: 15px;">
				<tr>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>����</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'" onclick="window.parent.parent.close();" nowrap >�ر�</td>
				</tr>
			</table>
		</div>
	</div>
</form>
</div>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
<!-- 
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.transSerialNumber" name="geProductPolicyDisplayConfig.transSerialNumber" value="0" onclick="showRequeriedIterm(this);" />������ˮ��&nbsp;&nbsp;</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.policySerialNumber" name="geProductPolicyDisplayConfig.policySerialNumber" value="0" onclick="showRequeriedIterm(this);" />������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.applicationNumber" name="geProductPolicyDisplayConfig.applicationNumber" value="0" onclick="showRequeriedIterm(this);" />Ͷ������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.applicationSerialNumber" name="geProductPolicyDisplayConfig.applicationSerialNumber" value="0" onclick="showRequeriedIterm(this);" />Ͷ����ӡˢ��</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.policyStatus" name="geProductPolicyDisplayConfig.policyStatus" value="0" onclick="showRequeriedIterm(this);" />����״̬</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.insuredAmount" name="geProductPolicyDisplayConfig.insuredAmount" value="0" onclick="showRequeriedIterm(this);" />����</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.premium" name="geProductPolicyDisplayConfig.premium" value="0" onclick="showRequeriedIterm(this);" />����</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.discountPremium" name="geProductPolicyDisplayConfig.discountPremium" value="0" onclick="showRequeriedIterm(this);" />�ۿ۱���</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.grossPremium" name="geProductPolicyDisplayConfig.grossPremium" value="0" onclick="showRequeriedIterm(this);" />�ܱ���</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.faceAmount" name="geProductPolicyDisplayConfig.faceAmount" value="0" onclick="showRequeriedIterm(this);" />�ܱ���</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.productCode" name="geProductPolicyDisplayConfig.productCode" value="0" onclick="showRequeriedIterm(this);" />��Ʒ����</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.productName" name="geProductPolicyDisplayConfig.productName" value="0" onclick="showRequeriedIterm(this);" />��Ʒ����</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.firstPremium" name="geProductPolicyDisplayConfig.firstPremium" value="0" onclick="showRequeriedIterm(this);" />���ڱ���</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.initialPremAmt" name="geProductPolicyDisplayConfig.initialPremAmt" value="0" onclick="showRequeriedIterm(this);" />�״νɷѽ��</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.benefitPeriod" name="geProductPolicyDisplayConfig.benefitPeriod" value="0" onclick="showRequeriedIterm(this);" />��������</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.benefitPeriodType" name="geProductPolicyDisplayConfig.benefitPeriodType" value="0" onclick="showRequeriedIterm(this);" />������������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.paymentDuration" name="geProductPolicyDisplayConfig.paymentDuration" value="0" onclick="showRequeriedIterm(this);" />�ɷ�����</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.paymentDurationMode" name="geProductPolicyDisplayConfig.paymentDurationMode" value="0" onclick="showRequeriedIterm(this);" />�ɷ���������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.paymentMethod" name="geProductPolicyDisplayConfig.paymentMethod" value="0" onclick="showRequeriedIterm(this);" />֧����ʽ</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.benefitMode" name="geProductPolicyDisplayConfig.benefitMode" value="0" onclick="showRequeriedIterm(this);" />�������ȡ��ʽ</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.divType" name="geProductPolicyDisplayConfig.divType" value="0" onclick="showRequeriedIterm(this);" />������ȡ��ʽ</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.excessPremAmt" name="geProductPolicyDisplayConfig.excessPremAmt" value="0" onclick="showRequeriedIterm(this);" />���ӱ���/�������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.policyDeliveryFee" name="geProductPolicyDisplayConfig.policyDeliveryFee" value="0" onclick="showRequeriedIterm(this);" />ֽ�ʱ�����ݷ�</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.signedDate" name="geProductPolicyDisplayConfig.signedDate" value="0" onclick="showRequeriedIterm(this);" />ǩԼ����</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.inceptionDate" name="geProductPolicyDisplayConfig.inceptionDate" value="0" onclick="showRequeriedIterm(this);" />������Ч��</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.applicationDate" name="geProductPolicyDisplayConfig.applicationDate" value="0" onclick="showRequeriedIterm(this);" />����������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.insuranceStartPeriod" name="geProductPolicyDisplayConfig.insuranceStartPeriod" value="0" onclick="showRequeriedIterm(this);" />��������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.insuranceEndPeriod" name="geProductPolicyDisplayConfig.insuranceEndPeriod" value="0" onclick="showRequeriedIterm(this);" />����ֹ��</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.beneficiaryMode" name="geProductPolicyDisplayConfig.beneficiaryMode" value="0" onclick="showRequeriedIterm(this);" />���淽ʽ</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.submissionDate" name="geProductPolicyDisplayConfig.submissionDate" value="0" onclick="showRequeriedIterm(this);" />Ͷ����������</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.unitCount" name="geProductPolicyDisplayConfig.unitCount" value="0" onclick="showRequeriedIterm(this);" />����</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.specialStatement" name="geProductPolicyDisplayConfig.specialStatement" value="0" onclick="showRequeriedIterm(this);" />�ر�����</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.campaignCode" name="geProductPolicyDisplayConfig.campaignCode" value="0" onclick="showRequeriedIterm(this);" />�����</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.campaignName" name="geProductPolicyDisplayConfig.campaignName" value="0" onclick="showRequeriedIterm(this);" />�����</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.discountTypeCode" name="geProductPolicyDisplayConfig.discountTypeCode" value="0" onclick="showRequeriedIterm(this);" />�ۿ۴���</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.discountRate" name="geProductPolicyDisplayConfig.discountRate" value="0" onclick="showRequeriedIterm(this);" />�ۿ�ϵ��</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.comboCode" name="geProductPolicyDisplayConfig.comboCode" value="0" onclick="showRequeriedIterm(this);" />�ײʹ���</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.comboName" name="geProductPolicyDisplayConfig.comboName" value="0" onclick="showRequeriedIterm(this);" />�ײ�����</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.personalUserSerialNo" name="geProductPolicyDisplayConfig.personalUserSerialNo" value="0" onclick="showRequeriedIterm(this);" />���˿ͻ�</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.agentCode" name="geProductPolicyDisplayConfig.agentCode" value="0" onclick="showRequeriedIterm(this);" />�����˴���</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.agreementNo" name="geProductPolicyDisplayConfig.agreementNo" value="0" onclick="showRequeriedIterm(this);" />������Э���</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.agentName" name="geProductPolicyDisplayConfig.agentName" value="0" onclick="showRequeriedIterm(this);" />����������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.departmentNo" name="geProductPolicyDisplayConfig.departmentNo" value="0" onclick="showRequeriedIterm(this);" />���������ڻ�������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.departmentName" name="geProductPolicyDisplayConfig.departmentName" value="0" onclick="showRequeriedIterm(this);" />���������ڻ�������</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.intermediaryCode" name="geProductPolicyDisplayConfig.intermediaryCode" value="0" onclick="showRequeriedIterm(this);" />��������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.intermediaryName" name="geProductPolicyDisplayConfig.intermediaryName" value="0" onclick="showRequeriedIterm(this);" />��������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.branchCode" name="geProductPolicyDisplayConfig.branchCode" value="0" onclick="showRequeriedIterm(this);" />�������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.branchName" name="geProductPolicyDisplayConfig.branchName" value="0" onclick="showRequeriedIterm(this);" />�������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.organizationCode" name="geProductPolicyDisplayConfig.organizationCode" value="0" onclick="showRequeriedIterm(this);" />��������</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.organizationName" name="geProductPolicyDisplayConfig.organizationName" value="0" onclick="showRequeriedIterm(this);" />��������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.institutionCode" name="geProductPolicyDisplayConfig.institutionCode" value="0" onclick="showRequeriedIterm(this);" />������������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.institutionName" name="geProductPolicyDisplayConfig.institutionName" value="0" onclick="showRequeriedIterm(this);" />������������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.cashValue" name="geProductPolicyDisplayConfig.cashValue" value="0" onclick="showRequeriedIterm(this);" />�ֽ��ֵ</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.preserveAcceptTime" name="geProductPolicyDisplayConfig.preserveAcceptTime" value="0" onclick="showRequeriedIterm(this);" />��ȫ����ʱ��</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.preserveEffectiveTime" name="geProductPolicyDisplayConfig.preserveEffectiveTime" value="0" onclick="showRequeriedIterm(this);" />��ȫ��Чʱ��</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.reFundPolicyTime" name="geProductPolicyDisplayConfig.reFundPolicyTime" value="0" onclick="showRequeriedIterm(this);" />����ʱ��</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.reFundPolicySuccessTime" name="geProductPolicyDisplayConfig.reFundPolicySuccessTime" value="0" onclick="showRequeriedIterm(this);" />�����ɹ�ʱ��</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.exPayMode" name="geProductPolicyDisplayConfig.exPayMode" value="0" onclick="showRequeriedIterm(this);" />���ڽɷ���ʽ</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.getPolMode" name="geProductPolicyDisplayConfig.getPolMode" value="0" onclick="showRequeriedIterm(this);" />�������ͷ�ʽ</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.specContent" name="geProductPolicyDisplayConfig.specContent" value="0" onclick="showRequeriedIterm(this);" />�ر�Լ��</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.tempFeeNo" name="geProductPolicyDisplayConfig.tempFeeNo" value="0" onclick="showRequeriedIterm(this);" />��Ʊӡˢ����</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.agentGroup" name="geProductPolicyDisplayConfig.agentGroup" value="0" onclick="showRequeriedIterm(this);" />���������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.disputedFlag" name="geProductPolicyDisplayConfig.disputedFlag" value="0" onclick="showRequeriedIterm(this);" />��ͬ���鴦��ʽ</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.acName" name="geProductPolicyDisplayConfig.acName" value="0" onclick="showRequeriedIterm(this);" />�ٲ�ίԱ������</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.isVisit" name="geProductPolicyDisplayConfig.isVisit" value="0" onclick="showRequeriedIterm(this);" />�Ƿ�ط�</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.isBind" name="geProductPolicyDisplayConfig.isBind" value="0" onclick="showRequeriedIterm(this);" />�󶨱�־</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.renewalFlag" name="geProductPolicyDisplayConfig.renewalFlag" value="0" onclick="showRequeriedIterm(this);" />������־</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.currency" name="geProductPolicyDisplayConfig.currency" value="0" onclick="showRequeriedIterm(this);" />�ұ�</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.businessArea" name="geProductPolicyDisplayConfig.businessArea" value="0" onclick="showRequeriedIterm(this);" />ҵ������</td>
	<td width="30">&nbsp;</td>
</tr>
<tr>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.reason" name="geProductPolicyDisplayConfig.reason" value="0" onclick="showRequeriedIterm(this);" />ԭ��</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.precheckDate" name="geProductPolicyDisplayConfig.precheckDate" value="0" onclick="showRequeriedIterm(this);" />�˱�\�б�ʱ��</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.businessSource" name="geProductPolicyDisplayConfig.businessSource" value="0" onclick="showRequeriedIterm(this);" />ҵ����Դ</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.groupChannel" name="geProductPolicyDisplayConfig.groupChannel" value="0" onclick="showRequeriedIterm(this);" />��������</td>
	<td width="30">&nbsp;</td>
	<td width="180px"><input type="checkbox" id="geProductPolicyDisplayConfig.sellType" name="geProductPolicyDisplayConfig.sellType" value="0" onclick="showRequeriedIterm(this);" />��������</td>
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

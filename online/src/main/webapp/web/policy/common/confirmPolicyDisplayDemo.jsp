<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div id="policyDisplayDemo" tag="policyDisplay">
<div class="title">������ϸ</div>
	<div class="detail_row">
		<div class="name">��Ʒ���ƣ�</div>
		<div class="value" id="productName">${insureFlowDto.quoteMain.productName }</div>
	</div>
	<div class="detail_row">
		<div class="name">���ִ��룺</div>
		<div class="value" id="coreCode">${insureFlowDto.quoteMain.quoteLiabilities[0].coreCode }</div>
	</div>
	<div class="detail_row">
		<div class="name">��ѡ�ײͣ�</div>
		<div class="value" id="applicationDate">${insureFlowDto.quoteMain.comboName }</div>
	</div>
	<div class="detail_row">
		<div class="name">�����ڼ䣺</div>
		<div class="value" id="applicationDate">
			${insureFlowDto.quoteMain.benefitPeriod }&nbsp;
			${insureFlowDto.quoteMain.benefitPeriodType }
		</div>
	</div>
	<div class="detail_row">
		<div class="name">��Ч���ڣ�</div>
		<div class="value" id="specifyStartDate"></div>
	</div>
	<div class="detail_row">
		<div class="name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�</div>
		<div class="value" id="unitCount">��${insureFlowDto.quoteMain.grossPremium }</div>
	</div>
	<div class="detail_row">
		<div class="name">Ͷ��������</div>
		<div class="value" id="unitCount">${insureFlowDto.quoteMain.unitCount }&nbsp;��</div>
	</div>
</div>
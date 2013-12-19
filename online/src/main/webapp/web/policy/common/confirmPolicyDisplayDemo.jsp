<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div id="policyDisplayDemo" tag="policyDisplay">
<div class="title">保单明细</div>
	<div class="detail_row">
		<div class="name">产品名称：</div>
		<div class="value" id="productName">${insureFlowDto.quoteMain.productName }</div>
	</div>
	<div class="detail_row">
		<div class="name">险种代码：</div>
		<div class="value" id="coreCode">${insureFlowDto.quoteMain.quoteLiabilities[0].coreCode }</div>
	</div>
	<div class="detail_row">
		<div class="name">已选套餐：</div>
		<div class="value" id="applicationDate">${insureFlowDto.quoteMain.comboName }</div>
	</div>
	<div class="detail_row">
		<div class="name">保险期间：</div>
		<div class="value" id="applicationDate">
			${insureFlowDto.quoteMain.benefitPeriod }&nbsp;
			${insureFlowDto.quoteMain.benefitPeriodType }
		</div>
	</div>
	<div class="detail_row">
		<div class="name">生效日期：</div>
		<div class="value" id="specifyStartDate"></div>
	</div>
	<div class="detail_row">
		<div class="name">金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额：</div>
		<div class="value" id="unitCount">￥${insureFlowDto.quoteMain.grossPremium }</div>
	</div>
	<div class="detail_row">
		<div class="name">投保份数：</div>
		<div class="value" id="unitCount">${insureFlowDto.quoteMain.unitCount }&nbsp;份</div>
	</div>
</div>
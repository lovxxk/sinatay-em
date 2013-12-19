<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div id="policyDisplayDemo" tag="policyDisplay">
	<c:set var="policy" value="${geSaleProduct.geSaleProPolicyDisConfigs[0] }"></c:set>
	<c:if test="${not empty policy }">
		<div class="title">保单明细</div>
		<c:if test="${policy.productName eq 1}">
			<div class="detail_row">
				<div class="name">产品名称：</div>
				<div class="value" id="productName">${insureFlowDto.quoteMain.productName }</div>
			</div>
		</c:if>
		<c:if test="${policy.benefitPeriod eq 1}">
			<div class="detail_row">
				<div class="name">保险期间：</div>
				<div class="value" id="benefitPeriod">
					${insureFlowDto.quoteMain.benefitPeriod }
					<c:if test="${policy.benefitPeriodType eq 1}">
						<c:if test="${insureFlowDto.quoteMain.benefitPeriodType eq 9}">&nbsp;其他</c:if>
						<c:if test="${insureFlowDto.quoteMain.benefitPeriodType eq 42}">&nbsp;年</c:if>
						<c:if test="${insureFlowDto.quoteMain.benefitPeriodType eq 41}">&nbsp;月</c:if>
						<c:if test="${insureFlowDto.quoteMain.benefitPeriodType eq 40}">&nbsp;天</c:if>
						<c:if test="${insureFlowDto.quoteMain.benefitPeriodType eq 30}">&nbsp;周</c:if>
						<c:if test="${insureFlowDto.quoteMain.benefitPeriodType eq 20}">&nbsp;岁</c:if>
						<c:if test="${insureFlowDto.quoteMain.benefitPeriodType eq 18}">&nbsp;终身</c:if>
					</c:if>
				</div>
			</div>
		</c:if>
		<c:if test="${policy.insuredAmount eq 1}">
			<div class="detail_row">
				<div class="name">保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额：</div>
				<div class="value" id="faceAmount">￥${insureFlowDto.quoteMain.faceAmount }</div>
			</div>
		</c:if>
		<c:if test="${policy.premium eq 1}">
			<div class="detail_row">
				<div class="name">保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：</div>
				<div class="value" id="grossPremium">￥${insureFlowDto.quoteMain.grossPremium }</div>
			</div>
		</c:if>
		<c:if test="${policy.paymentDuration eq 1}">
			<div class="detail_row">
				<div class="name">缴费年期：</div>
				<div class="value" id="paymentDuration">${insureFlowDto.quoteMain.paymentDuration }&nbsp;
					<c:if test="${policy.paymentDurationMode eq 1}">
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 1}">&nbsp;年</c:if>
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 2}">&nbsp;月</c:if>
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 3}">&nbsp;日</c:if>
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 4}">&nbsp;趸缴</c:if>
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 5}">&nbsp;终生缴费</c:if>
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 6}">&nbsp;任意方式</c:if>
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 7}">&nbsp;季缴/季领</c:if>
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 8}">&nbsp;半年缴/领</c:if>
					</c:if>
				</div>
			</div>
		</c:if>
		<c:if test="${policy.inceptionDate eq 1}">
			<div class="detail_row">
				<div class="name">生效日期：</div>
				<div class="value" id="specifyStartDate"></div>
			</div>
		</c:if>
		<c:if test="${policy.comboName eq 1}">
			<div class="detail_row">
				<div class="name">已选套餐：</div>
				<div class="value" id="comboName">${insureFlowDto.quoteMain.comboName }</div>
			</div>
		</c:if>
		<c:if test="${policy.campaignName eq 1}">
			<div class="detail_row">
				<div class="name">活动名称：</div>
				<div class="value" id="campaignName">${insureFlowDto.quoteMain.campaignName }</div>
			</div>
		</c:if>
		<c:if test="${policy.unitCount eq 1}">
			<div class="detail_row">
				<div class="name">投保份数：</div>
				<div class="value" id="unitCount">${insureFlowDto.quoteMain.unitCount } &nbsp;份</div>
			</div>
		</c:if>
	</c:if>
</div>
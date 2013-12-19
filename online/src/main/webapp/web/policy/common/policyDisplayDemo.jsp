<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div id="policyDisplayDemo" tag="policyDisplay">
	<c:set var="policy" value="${geSaleProduct.geSaleProPolicyDisConfigs[0] }"></c:set>
	<c:if test="${not empty policy }">
		<div class="title">������ϸ</div>
		<c:if test="${policy.productName eq 1}">
			<div class="detail_row">
				<div class="name">��Ʒ���ƣ�</div>
				<div class="value" id="productName">${insureFlowDto.quoteMain.productName }</div>
			</div>
		</c:if>
		<c:if test="${policy.benefitPeriod eq 1}">
			<div class="detail_row">
				<div class="name">�����ڼ䣺</div>
				<div class="value" id="benefitPeriod">
					${insureFlowDto.quoteMain.benefitPeriod }
					<c:if test="${policy.benefitPeriodType eq 1}">
						<c:if test="${insureFlowDto.quoteMain.benefitPeriodType eq 9}">&nbsp;����</c:if>
						<c:if test="${insureFlowDto.quoteMain.benefitPeriodType eq 42}">&nbsp;��</c:if>
						<c:if test="${insureFlowDto.quoteMain.benefitPeriodType eq 41}">&nbsp;��</c:if>
						<c:if test="${insureFlowDto.quoteMain.benefitPeriodType eq 40}">&nbsp;��</c:if>
						<c:if test="${insureFlowDto.quoteMain.benefitPeriodType eq 30}">&nbsp;��</c:if>
						<c:if test="${insureFlowDto.quoteMain.benefitPeriodType eq 20}">&nbsp;��</c:if>
						<c:if test="${insureFlowDto.quoteMain.benefitPeriodType eq 18}">&nbsp;����</c:if>
					</c:if>
				</div>
			</div>
		</c:if>
		<c:if test="${policy.insuredAmount eq 1}">
			<div class="detail_row">
				<div class="name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�</div>
				<div class="value" id="faceAmount">��${insureFlowDto.quoteMain.faceAmount }</div>
			</div>
		</c:if>
		<c:if test="${policy.premium eq 1}">
			<div class="detail_row">
				<div class="name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ѣ�</div>
				<div class="value" id="grossPremium">��${insureFlowDto.quoteMain.grossPremium }</div>
			</div>
		</c:if>
		<c:if test="${policy.paymentDuration eq 1}">
			<div class="detail_row">
				<div class="name">�ɷ����ڣ�</div>
				<div class="value" id="paymentDuration">${insureFlowDto.quoteMain.paymentDuration }&nbsp;
					<c:if test="${policy.paymentDurationMode eq 1}">
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 1}">&nbsp;��</c:if>
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 2}">&nbsp;��</c:if>
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 3}">&nbsp;��</c:if>
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 4}">&nbsp;����</c:if>
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 5}">&nbsp;�����ɷ�</c:if>
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 6}">&nbsp;���ⷽʽ</c:if>
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 7}">&nbsp;����/����</c:if>
						<c:if test="${insureFlowDto.quoteMain.paymentDurationMode eq 8}">&nbsp;�����/��</c:if>
					</c:if>
				</div>
			</div>
		</c:if>
		<c:if test="${policy.inceptionDate eq 1}">
			<div class="detail_row">
				<div class="name">��Ч���ڣ�</div>
				<div class="value" id="specifyStartDate"></div>
			</div>
		</c:if>
		<c:if test="${policy.comboName eq 1}">
			<div class="detail_row">
				<div class="name">��ѡ�ײͣ�</div>
				<div class="value" id="comboName">${insureFlowDto.quoteMain.comboName }</div>
			</div>
		</c:if>
		<c:if test="${policy.campaignName eq 1}">
			<div class="detail_row">
				<div class="name">����ƣ�</div>
				<div class="value" id="campaignName">${insureFlowDto.quoteMain.campaignName }</div>
			</div>
		</c:if>
		<c:if test="${policy.unitCount eq 1}">
			<div class="detail_row">
				<div class="name">Ͷ��������</div>
				<div class="value" id="unitCount">${insureFlowDto.quoteMain.unitCount } &nbsp;��</div>
			</div>
		</c:if>
	</c:if>
</div>
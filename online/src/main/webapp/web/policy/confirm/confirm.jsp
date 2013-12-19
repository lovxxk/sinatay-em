<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%@include file="/web/policy/common/confirmInsuredDemo.jsp"%>
<%@include file="/web/policy/common/inputDataDemo.jsp" %>
<%@include file="/web/policy/common/confirmDataDemo.jsp" %>
<%@include file="/web/policy/common/confirmBnfDemo.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="#">��ҳ</a><span> &gt;</span></li>
				<li><a href="#">��Ʒչʾ</a><span> &gt;</span></li>
				<li><a href="#">${geSaleProduct.productName }</a><span> &gt;</span></li>
				<li class="at">Ͷ��</li>
			</ul>
		</div>
		<div class="policy_instro">
			${geSaleProduct.productName }&nbsp;
		</div>
		<div class="policy_navbar">
			<div>��д����</div>
			<div class="active">�˶Զ���</div>
			<div>֧������</div>
		</div>
		<form id="policy_confirm" method="post" action="">
		<div class="policy_area policy_detail" id="policyDisplay_info" tag="policyDisplay">
			<jsp:include page="/web/policy/common/policyDisplayDemo.jsp"></jsp:include>
		</div>
		<jsp:include page="/web/policy/common/confirmAppDemo.jsp"></jsp:include>
		<div class="policy_area inslicant_info">
		</div>
		<div class="policy_area bnflicant_info">
		</div>
		<div class="declaration">
			<div class="tip">��Ҫ��֪�������� </div>
			<div class="text">
				<c:if test="${!empty geSaleProduct.geSaleProExtraInfos && fn:length(geSaleProduct.geSaleProExtraInfos) gt 0 }">
					<div class="agree"></div>
					${geSaleProduct.geSaleProExtraInfos[0].legalNotices }
					</div>
				</c:if>
		
		</div>
		<div class="action">
			<button id="confirm_buy" class="confirm_buy click_btn" onclick="return false;">ȷ�Ϲ���</button>
			<a href="javascript:returnEdit();" id="return_edit">�����޸�</a>
		</div>
		<input type="hidden" id="legalNotice" name="legalNotice" value="" />
		<input type="hidden" id="productCode" name="productCode" value="" />
		<input type="hidden" id="quoteUrlFlag" name="quoteUrlFlag" value="" />
		<input type="hidden" id="quoteJsonSTR" name="quoteJsonSTR" value="" />
		<input type="hidden" id="quoteNo" name="quoteNo" value="${quoteNo }">
		<input type="hidden" id="confirmProposalSID" name="proposalSID" value="${proposalSID }">
		<input type="hidden" id="proposalContNo" name="proposalContNo" value="${proposalContNo }">
		</form>
	</div>
</div>
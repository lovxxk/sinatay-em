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
				<li><a href="#">首页</a><span> &gt;</span></li>
				<li><a href="#">产品展示</a><span> &gt;</span></li>
				<li><a href="#">${geSaleProduct.productName }</a><span> &gt;</span></li>
				<li class="at">投保</li>
			</ul>
		</div>
		<div class="policy_instro">
			${geSaleProduct.productName }&nbsp;
		</div>
		<div class="policy_navbar">
			<div>填写订单</div>
			<div class="active">核对订单</div>
			<div>支付订单</div>
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
			<div class="tip">重要告知与声明： </div>
			<div class="text">
				<c:if test="${!empty geSaleProduct.geSaleProExtraInfos && fn:length(geSaleProduct.geSaleProExtraInfos) gt 0 }">
					<div class="agree"></div>
					${geSaleProduct.geSaleProExtraInfos[0].legalNotices }
					</div>
				</c:if>
		
		</div>
		<div class="action">
			<button id="confirm_buy" class="confirm_buy click_btn" onclick="return false;">确认购买</button>
			<a href="javascript:returnEdit();" id="return_edit">返回修改</a>
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
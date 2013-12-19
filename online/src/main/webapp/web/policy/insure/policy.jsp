<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%@include file="/web/policy/common/inputBnfDemo.jsp"%>
<%@include file="/web/policy/common/inputDataDemo.jsp" %>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/product/index.jsp">产品展示</a><span>&gt;</span></li>
				<li><a href="#" id="productLink">${eid} ${geSaleProduct.productName }</a><span> &gt;</span></li>
				<li class="at">投保</li>
			</ul>
		</div>
		<div class="policy_instro">
			${geSaleProduct.productName }&nbsp;
		</div>
		<div class="policy_navbar">
			<div class="active">填写订单</div>
			<div>核对订单</div>
			<div>支付订单</div>
		</div>
		<div class="policy_area policy_detail" id="policyDisplay_info" tag="policyDisplay">
			<jsp:include page="/web/policy/common/policyDisplayDemo.jsp"></jsp:include>
		</div>
		<form id="policy_fill" method="post" action="">
			<div class="policy_area applicant_info">
				<div class="title"><div>投保人信息</div><div class="doubt"><div class="doubt_info">投保人是指与保险公司签订合同、有支付保险费义务的人。在填写时，请确保填写必要的真实信息。</div></div></div>
				<div id="applicant_info" tag="applicant">
					<jsp:include page="/web/policy/common/inputAppDemo.jsp"></jsp:include>
				</div>
			</div>
			<div class="policy_area insured_info">
				<div class="title"><div>被保险人信息</div><div class="doubt"><div class="doubt_info">被保险人是指根据保险合同,其财产利益或人身受保险合同保障，在保险事故发生后，享有保险金请求权的人。</div></div><div class="click">点击展开</div><div class="click_box">+</div></div>
				<div class="input_area insured">
					<div class="input_row contact_list" id="topInsList">
						<label class="input_label" for="name"><span class="name">常用被保险人：</span></label>
						<c:forEach items="${topInsureds}" var="topInsured" begin="0" varStatus="status">
							<div class="contact">
								<div class="contact_check" topId="${topInsured.serialNo }" ></div>
								<label>${topInsured.fullName }</label>
							</div>
						</c:forEach>
					</div>
					<div id="insured_info" tag="insured">
						<jsp:include page="/web/policy/common/inputInsuredDemo.jsp"></jsp:include>
					</div>
				</div>
				<div class="input_area effect">
					<div class="input_row effect_time">
						<label class="input_label" for="postcode"><span class="required">*</span><span class="name">保险生效日期：</span></label>
						<input class="input_text Wdate" name="inceptionDate" type="text" id="effect_start"/>
						<label class="tip_label" for="birthday">零时起</label>
						<label class="lab" id="effect_startTip"></label>
					</div>
				</div>
			</div>
			<div class="policy_area beneficiary_info">
				<div class="title"><div>受益人信息</div><div class="doubt"><div class="doubt_info">受益人是指人身保险合同中由被保险人或者投保人指定的享有保险金请求权的人。</div></div></div>
				<div class="beneficiary_type">
					<div class="input_row type">
						<label class="input_label" for="sex"><span class="name">受益人类型：</span></label>
						<div class="input_beneficiary selected" id="legal" val="0"></div>
						<label class="label_beneficiary" val="0">法定</label>
						<c:if test="${geSaleProduct.isSupportBeneficiary eq 1}">
							<div class="input_beneficiary" id="assign" val="1"></div>
							<label class="label_beneficiary" val="1">指定</label>
						</c:if>
						<input type="hidden" id="isExsitBnf" name="isExsitBnf" />
						<c:if test="${geSaleProduct.isSupportBeneficiary eq 1}">
							<div class="add_beneficiary" id="add_beneficiary">
								<div class="click_btn">添加新受益人</div>
							</div>
						</c:if>
					</div>
				</div>
				
			</div>
			<div class="policy_area reference_info" style="display:none;">
				<div class="title"><div>推荐人信息</div><div class="doubt"><div class="doubt_info">推荐人指由谁向您推荐了此款产品，推荐人将会获得XX以及XX的优惠等。</div></div></div>
				<div class="input_area">
					<div class="input_row reference_list">
						<div class="reference">
							<div class="reference_check" type="1"></div>
							<label>好友</label>
						</div>
						<div class="reference">
							<div class="reference_check" type="2"></div>
							<label>代理人</label>
						</div>
						<input class="input_text" id="reference_type" name="reference_type" type="hidden"/>
					</div>
					<div class="input_row reference_phone">
						<label class="input_label" for="reference_phone"><span class="name">推荐人手机号：</span></label>
						<input class="input_text" id="reference_phone" name="reference_phone" type="text" maxlength="11"/>
						<label class="lab" id="reference_phoneTip"></label>
					</div>
				</div>
			</div>
			<c:if test="${!empty geInformBooks }">
				<div class="policy_area applicant_info" id="inform">
					<div class="title"><div>健康告知栏</div></div>
					<jsp:include page="/web/policy/inform/index.jsp"></jsp:include>
				</div>
			</c:if>
			<div class="action">
				<c:if test="${!empty geUserPersonal }">
					<div id="save_info" class="save_info click_btn" onclick="saveInfo();">保存信息</div>
				</c:if>
				<div id="next_step" class="next_step click_btn" onclick="toConfirmInsurance();">下一步</div>
			</div>
			<input type="hidden" id="toConfimUrlFlag" name="toConfimUrlFlag" value="" />
			<input type="hidden" id="toConfimJsonSTR" name="toConfimJsonSTR" value="" />
			<input type="hidden" id="autoIns" name="autoIns" value="${autoIns}" />
			<input type="hidden" id="inputQuoteNo" name="quoteNo" value="${quoteNo }">
			<input type="hidden" id="inputProposalSID" name="proposalSID" value="${proposalSID }">
			<input type="hidden" id="proposalContNo" name="proposalContNo" value="${proposalContNo }">
			<input type="hidden" id="inform" value="">
			<input type="hidden" id="quoteJsonSTR" name="quoteJsonSTR" value="">
			<input type="hidden" id="quoteUrlFlag" name="quoteUrlFlag" value="">
		</form>
		<form id="policy_sub" method="post" action="">
			<input type="hidden" id="_subQuoteJsonSTR" name="quoteJsonSTR" value="">
			<input type="hidden" id="_subQuoteUrlFlag" name="quoteUrlFlag" value="">
		</form>
	</div>
</div>
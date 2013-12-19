<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%@include file="/web/policy/common/inputBnfDemo.jsp"%>
<%@include file="/web/policy/common/inputDataDemo.jsp" %>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/product/index.jsp">��Ʒչʾ</a><span>&gt;</span></li>
				<li><a href="#" id="productLink">${eid} ${geSaleProduct.productName }</a><span> &gt;</span></li>
				<li class="at">Ͷ��</li>
			</ul>
		</div>
		<div class="policy_instro">
			${geSaleProduct.productName }&nbsp;
		</div>
		<div class="policy_navbar">
			<div class="active">��д����</div>
			<div>�˶Զ���</div>
			<div>֧������</div>
		</div>
		<div class="policy_area policy_detail" id="policyDisplay_info" tag="policyDisplay">
			<jsp:include page="/web/policy/common/policyDisplayDemo.jsp"></jsp:include>
		</div>
		<form id="policy_fill" method="post" action="">
			<div class="policy_area applicant_info">
				<div class="title"><div>Ͷ������Ϣ</div><div class="doubt"><div class="doubt_info">Ͷ������ָ�뱣�չ�˾ǩ����ͬ����֧�����շ�������ˡ�����дʱ����ȷ����д��Ҫ����ʵ��Ϣ��</div></div></div>
				<div id="applicant_info" tag="applicant">
					<jsp:include page="/web/policy/common/inputAppDemo.jsp"></jsp:include>
				</div>
			</div>
			<div class="policy_area insured_info">
				<div class="title"><div>����������Ϣ</div><div class="doubt"><div class="doubt_info">����������ָ���ݱ��պ�ͬ,��Ʋ�����������ܱ��պ�ͬ���ϣ��ڱ����¹ʷ��������б��ս�����Ȩ���ˡ�</div></div><div class="click">���չ��</div><div class="click_box">+</div></div>
				<div class="input_area insured">
					<div class="input_row contact_list" id="topInsList">
						<label class="input_label" for="name"><span class="name">���ñ������ˣ�</span></label>
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
						<label class="input_label" for="postcode"><span class="required">*</span><span class="name">������Ч���ڣ�</span></label>
						<input class="input_text Wdate" name="inceptionDate" type="text" id="effect_start"/>
						<label class="tip_label" for="birthday">��ʱ��</label>
						<label class="lab" id="effect_startTip"></label>
					</div>
				</div>
			</div>
			<div class="policy_area beneficiary_info">
				<div class="title"><div>��������Ϣ</div><div class="doubt"><div class="doubt_info">��������ָ�����պ�ͬ���ɱ������˻���Ͷ����ָ�������б��ս�����Ȩ���ˡ�</div></div></div>
				<div class="beneficiary_type">
					<div class="input_row type">
						<label class="input_label" for="sex"><span class="name">���������ͣ�</span></label>
						<div class="input_beneficiary selected" id="legal" val="0"></div>
						<label class="label_beneficiary" val="0">����</label>
						<c:if test="${geSaleProduct.isSupportBeneficiary eq 1}">
							<div class="input_beneficiary" id="assign" val="1"></div>
							<label class="label_beneficiary" val="1">ָ��</label>
						</c:if>
						<input type="hidden" id="isExsitBnf" name="isExsitBnf" />
						<c:if test="${geSaleProduct.isSupportBeneficiary eq 1}">
							<div class="add_beneficiary" id="add_beneficiary">
								<div class="click_btn">�����������</div>
							</div>
						</c:if>
					</div>
				</div>
				
			</div>
			<div class="policy_area reference_info" style="display:none;">
				<div class="title"><div>�Ƽ�����Ϣ</div><div class="doubt"><div class="doubt_info">�Ƽ���ָ��˭�����Ƽ��˴˿��Ʒ���Ƽ��˽�����XX�Լ�XX���Żݵȡ�</div></div></div>
				<div class="input_area">
					<div class="input_row reference_list">
						<div class="reference">
							<div class="reference_check" type="1"></div>
							<label>����</label>
						</div>
						<div class="reference">
							<div class="reference_check" type="2"></div>
							<label>������</label>
						</div>
						<input class="input_text" id="reference_type" name="reference_type" type="hidden"/>
					</div>
					<div class="input_row reference_phone">
						<label class="input_label" for="reference_phone"><span class="name">�Ƽ����ֻ��ţ�</span></label>
						<input class="input_text" id="reference_phone" name="reference_phone" type="text" maxlength="11"/>
						<label class="lab" id="reference_phoneTip"></label>
					</div>
				</div>
			</div>
			<c:if test="${!empty geInformBooks }">
				<div class="policy_area applicant_info" id="inform">
					<div class="title"><div>������֪��</div></div>
					<jsp:include page="/web/policy/inform/index.jsp"></jsp:include>
				</div>
			</c:if>
			<div class="action">
				<c:if test="${!empty geUserPersonal }">
					<div id="save_info" class="save_info click_btn" onclick="saveInfo();">������Ϣ</div>
				</c:if>
				<div id="next_step" class="next_step click_btn" onclick="toConfirmInsurance();">��һ��</div>
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
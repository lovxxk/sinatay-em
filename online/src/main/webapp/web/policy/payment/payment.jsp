<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%@include file="/web/policy/common/confirmDataDemo.jsp" %>
<script type="text/javascript">
// �ж��Ƿ�Ϊ����ղ�Ʒ
var financialRisk = "${financialRisk}";
</script>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="#">��ҳ</a><span> &gt;</span></li>
				<li><a href="#">��Ʒչʾ</a><span> &gt;</span></li>
				<li><a href="#">${geDirectory.productName }</a><span> &gt;</span></li>
				<li class="at">Ͷ��</li>
			</ul>
		</div>
		<div class="policy_instro">
			${geDirectory.productName }
		</div>
		<div class="policy_navbar">
			<div>��д����</div>
			<div>�˶Զ���</div>
			<div class="active">֧������</div>
		</div>
		<form id="frmInput" method="post" action="">
			<div class="policy_area payment_area">
				<div class="title">
					�ɷ���Ϣ
				</div>
				<c:forEach items="${insurancePolicys}" var="insurancePolicy" begin="0" step="1" varStatus="status">
					<div class="base_info">
						<div class="info_body">
							<div class="name">��Ʒ���ƣ�</div>
							<div class="value"><span class="main_name">${insurancePolicy.productName }</span></div>
						</div>
						<div class="info_body info_right">
							<div class="name">���ѣ�</div>
							<div class="value"><span class="main_name">${insurancePolicy.grossPremium }</span>Ԫ</div>
						</div>
					</div>
				</c:forEach>
				<div class="total_price">
					<span class="name">�����ܽɷѽ�</span><span class="value">${sumPremium }</span><span>Ԫ</span>	
				</div>
			</div>
		<div class="policy_area payment_select">
			<div class="pay_tab_bar">
				<div class="tab_tip">��ѡ���������֧����ʽ</div>
				<div class="tab_item" type="1">��������</div>
				<c:if test="${financialRisk }">
					<div class="tab_item select" type="2" style="display: none;">֧��ƽ̨</div>
				</c:if>
				<c:if test="${!financialRisk }">
					<div class="tab_item" type="2">֧��ƽ̨</div>
				</c:if>
				<div class="tab_item" type="3" style="display:none;">���д���</div>
			</div>
			<div class="payment_type payment_bank">
				<div class="tip_info"><span>��ܰ��ʾ��</span>�����ʹ�����п���������֧��������ȷ���������п��Ѿ���ͨ������֧�����ܡ�</div>
				<div class="tip">����֧�����У�</div>
				<c:if test="${financialRisk }">
					<div class="select_area">
						<div class="pay_box">
							<input name="payment" type="radio" value="CCB-DEBIT">
							<image src="${ctx}/resources/image/bank/payment_ccb.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="ICBC-DEBIT">
							<image src="${ctx}/resources/image/bank/payment_icbc.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="CMB-DEBIT">
							<image src="${ctx}/resources/image/bank/payment_cmb.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="BOC-DEBIT">
							<image src="${ctx}/resources/image/bank/payment_boc.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="PSBC-DEBIT">
							<image src="${ctx}/resources/image/bank/payment_postgc.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="GDB-DEBIT">
							<image src="${ctx}/resources/image/bank/payment_cgb.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="COMM-DEBIT">
							<image src="${ctx}/resources/image/bank/payment_comm.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="CEB-DEBIT">
							<image src="${ctx}/resources/image/bank/payment_cebbank.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="SPDB-DEBIT">
							<image src="${ctx}/resources/image/bank/payment_spdb.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="BJBANK">
							<image src="${ctx}/resources/image/bank/payment_bjbank.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="SHRCB">
							<image src="${ctx}/resources/image/bank/payment_shrcb.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="WZCBB2C-DEBIT">
							<image src="${ctx}/resources/image/bank/payment_wzcbb2c.png" class="type" />
						</div>
					</div>
				</c:if>
				<c:if test="${!financialRisk }">
					<div class="select_area">
						<div class="pay_box">
							<input name="payment" type="radio" value="CCB">
							<image src="${ctx}/resources/image/bank/payment_ccb.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="ICBCB2C">
							<image src="${ctx}/resources/image/bank/payment_icbc.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="CMB">
								<image src="${ctx}/resources/image/bank/payment_cmb.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="BOCB2C">
								<image src="${ctx}/resources/image/bank/payment_boc.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="POSTGC">
								<image src="${ctx}/resources/image/bank/payment_postgc.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="GDB">
								<image src="${ctx}/resources/image/bank/payment_cgb.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment" type="radio" value="CIB">
								<image src="${ctx}/resources/image/bank/payment_cib.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment"  type="radio" value="ABC">
								<image src="${ctx}/resources/image/bank/payment_abc.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment" type="radio" value="CMBC">
								<image src="${ctx}/resources/image/bank/payment_cmbc.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment" type="radio" value="CITIC">
								<image src="${ctx}/resources/image/bank/payment_citic.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment" type="radio" value="SHBANK">
								<image src="${ctx}/resources/image/bank/payment_shbank.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment" type="radio" value="CEBBANK">
								<image src="${ctx}/resources/image/bank/payment_cebbank.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment" type="radio" value="HZCBB2C">
								<image src="${ctx}/resources/image/bank/payment_hzcb.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment" type="radio" value="NBBANK">
								<image src="${ctx}/resources/image/bank/payment_nbbank.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment" type="radio" value="SPABANK">
								<image src="${ctx}/resources/image/bank/payment_spabank.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment" type="radio" value="BJRCB">
								<image src="${ctx}/resources/image/bank/payment_bjrcb.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment" type="radio" value="FDB">
								<image src="${ctx}/resources/image/bank/payment_fdb.png" class="type" />
						</div>
						<div class="pay_box">
							<input name="payment" type="radio" value="SPDB">
								<image src="${ctx}/resources/image/bank/payment_spdb.png" class="type" />
						</div>
					</div>
				</c:if>
			</div>
			<div class="payment_type payment_platform">
				<div class="tip_info"><span>��ܰ��ʾ��</span>�����ʹ�õ�����ƽ̨����֧��������ȷ�����Ѿ���ͨ�˵�����ƽ̨֧�����ܡ�</div>
				<div class="tip">����֧��ƽ̨��</div>
				<div class="select_area">
					<div class="pay_box">
						<input name="payment" type="radio" id="alipay" value="alipay">
						<image src="${ctx}/resources/image/bank/payment_alipay.png" class="type" />
					</div>
				</div>
			</div>
			<div class="payment_type payment_withholding">
				<div class="tip_info"><span>��ܰ��ʾ��</span>ͨ������ת�˷�ʽ�Զ�֧�����շ��á�</div>
				<div class="input_area">
					<div class="input_row">
						<label class="input_label">�����ʻ�������</label>
						<label class="info_label">${insurancePolicy.insuranceApplicant.fullName }</label>
					</div>
					<div class="input_row bank">
						<label class="input_label" for="bank">�������У�</label>
						<input class="input_text alipay_bank_deposit" name="bank" type="text" id="alipay_bank_deposit"/>
					</div>
					<div class="input_row account">
						<label class="input_label" for="account">�ɷ��˻����룺</label>
						<input class="input_text" name="account" type="text" id="alipayPaymentAccount" maxlength="30"/>
						<label class="tip_label">������дͶ�������п�������˺ţ����������ÿ����й�ũҵ���д��ۣ�</label>
					</div>
					<div class="input_row">
						<label class="input_label">֤�����ͣ�</label>
						<label class="info_label">
							<c:forEach items="${identifyTypeList}" var="code" varStatus="status">
								<c:if test="${code.id.codeCode eq insurancePolicy.insuranceApplicant.idType }">${code.codeCName }</c:if>
							</c:forEach>
						</label>
					</div>
					<div class="input_row">
						<label class="input_label">֤�����룺</label>
						<label class="info_label">${insurancePolicy.insuranceApplicant.idNumber }</label>
						<label class="tip_label">��ȷ�ϸ�֤���������������п���ʱ��֤������һ��</label>
					</div>
				</div>
				<div class="authorisation">
					<div class="author_title">���շ��Զ�ת����Ȩ����</div>
					<p>1. Ͷ����ͬ����Ȩ����˾ͨ��<span>**********</span>�������д�Ͷ�����ṩ���ʻ�<span>**********</span>��ת��֧��֧������˾Լ�������ڱ��շ�</p>
					<p>2. Ͷ���˱�֤���˺����㹻�Ľ��֧��Ӧ�����շѣ������ʻ�����������ת�˲��ɹ����º�ͬ���ܳ������ܳ�����Ч�������������θ���Ͷ���˳е���Ϊ�����廧���˻������Ҫʼ�ձ��֣�10Ԫ����</p>
					<p>3. Ͷ����ͬ������˾������ȡ�ı��շѼ������ڳб�������Ͷ�����롢�������˱���������˷�ͨ��Ͷ�����ṩ�ĸ��˻����ظ�Ͷ���ˡ�</p>
					<p>4. Ͷ������ʽ�����˻���Ӧ�����¿����˻�������ʱ֪ͨ����˾���б����</p>
					<p>5. ����ϵͳԭ�򱣷ѻ�ת���ɹ�����ɺ�ͬδ���������չ�˾���е����Ρ�</p>
					<div class="agree_check">
						<div class="agree"></div>
						����ͬ�����������Զ�ת����Ȩ�����ĸ�������
					</div>
				</div>
			</div>
			</div>
			<c:if test="${insurancePolicy.orderForm.productCode != '00136' }">
			<div class="policy_area renewal_area">
				<div class="title">
					<div>���ڽ��ѡ��˱��ʻ�</div><div class="doubt">
					<div class="doubt_info">ͨ��˵���ǲ��ǵ�һ�νɷѾͽ����ڽɷѣ����ṩרҵ������</div></div>
				</div>
				<div class="input_area">
					<div class="input_row">
						<label class="input_label">�����ʻ�������</label>
						<label class="info_label">${insurancePolicy.insuranceApplicant.fullName }</label>
					</div>
					<div class="input_row provinceCity">
						<label class="input_label" for="account">ʡ�ݳ��У�</label>
						<input class="input_text" name="bankProvince" id="bankProvince" type="text"/>
						<input class="input_text" name="bankCity" id="bankCity" type="text" style="+margin-left: 6px;"/>
						<input class="input_text" name="bankArea" id="bankArea" type="text" style="display:none;"/>
					</div>
					<div class="input_row bank">
						<label class="input_label" for="renewal_bank">�������У�</label>
						<input class="input_text" name="renewal_bank" type="text" id="renewal_bank_deposit"/>
					</div>
					<div class="input_row account">
						<label class="input_label" for="account">�ɷ��˻����룺</label>
						<input class="input_text" name="account" type="text" id="paymentAccount" maxlength="30"/>
						<label class="tip_label">������дͶ�������п�������˺ţ����������ÿ����й�ũҵ���д��ۣ�</label>
					</div>
					<div class="input_row">
						<label class="input_label">֤�����ͣ�</label>
						<label class="info_label">
							<c:forEach items="${identifyTypeList}" var="code" varStatus="status">
								<c:if test="${code.id.codeCode eq insurancePolicy.insuranceApplicant.idType }">${code.codeCName }</c:if>
							</c:forEach>
						</label>
					</div>
					<div class="input_row">
						<label class="input_label">֤�����룺</label>
						<label class="info_label">${insurancePolicy.insuranceApplicant.idNumber }</label>
						<label class="tip_label">��ȷ�ϸ�֤���������������п���ʱ��֤������һ��</label>
					</div>
				</div>
			</div>
			</c:if>
			<div class="payment_confirm">
				<button id="click_btn" class="click_btn" onclick="return false;">ȷ��֧��</button>
			</div>
			<input type="hidden" id="payType" name="payType" value=""/>
			<input type="hidden" id="gateId" name="gateId" value=""/>
			<input type="hidden" id="uri" name="uri" value=""/>
			<input type="hidden" id="orderStatus" name="orderStatus" value="${insurancePolicy.orderForm.orderStatus }"/>
			<input type="hidden" id="proposalSID" name="proposalSID" value="${proposalSID }"/>
			<input type="hidden" id="orderId" name="orderId" value="${insurancePolicy.orderForm.serialNo }"/>
			<input type="hidden" id="productCode" name="productCode" value="${insurancePolicy.orderForm.productCode }"/>
		</form>
	</div>
</div>

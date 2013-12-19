<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<%@include file="/web/policy/common/confirmDataDemo.jsp" %>
<script type="text/javascript">
// 判断是否为理财险产品
var financialRisk = "${financialRisk}";
</script>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="#">首页</a><span> &gt;</span></li>
				<li><a href="#">产品展示</a><span> &gt;</span></li>
				<li><a href="#">${geDirectory.productName }</a><span> &gt;</span></li>
				<li class="at">投保</li>
			</ul>
		</div>
		<div class="policy_instro">
			${geDirectory.productName }
		</div>
		<div class="policy_navbar">
			<div>填写订单</div>
			<div>核对订单</div>
			<div class="active">支付订单</div>
		</div>
		<form id="frmInput" method="post" action="">
			<div class="policy_area payment_area">
				<div class="title">
					缴费信息
				</div>
				<c:forEach items="${insurancePolicys}" var="insurancePolicy" begin="0" step="1" varStatus="status">
					<div class="base_info">
						<div class="info_body">
							<div class="name">产品名称：</div>
							<div class="value"><span class="main_name">${insurancePolicy.productName }</span></div>
						</div>
						<div class="info_body info_right">
							<div class="name">保费：</div>
							<div class="value"><span class="main_name">${insurancePolicy.grossPremium }</span>元</div>
						</div>
					</div>
				</c:forEach>
				<div class="total_price">
					<span class="name">首期总缴费金额：</span><span class="value">${sumPremium }</span><span>元</span>	
				</div>
			</div>
		<div class="policy_area payment_select">
			<div class="pay_tab_bar">
				<div class="tab_tip">请选择合适您的支付方式</div>
				<div class="tab_item" type="1">网上银行</div>
				<c:if test="${financialRisk }">
					<div class="tab_item select" type="2" style="display: none;">支付平台</div>
				</c:if>
				<c:if test="${!financialRisk }">
					<div class="tab_item" type="2">支付平台</div>
				</c:if>
				<div class="tab_item" type="3" style="display:none;">银行代扣</div>
			</div>
			<div class="payment_type payment_bank">
				<div class="tip_info"><span>温馨提示：</span>如果您使用银行卡进行网上支付，请先确认您的银行卡已经开通了网银支付功能。</div>
				<div class="tip">常用支付银行：</div>
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
				<div class="tip_info"><span>温馨提示：</span>如果您使用第三方平台进行支付，请先确认您已经开通了第三方平台支付功能。</div>
				<div class="tip">常用支付平台：</div>
				<div class="select_area">
					<div class="pay_box">
						<input name="payment" type="radio" id="alipay" value="alipay">
						<image src="${ctx}/resources/image/bank/payment_alipay.png" class="type" />
					</div>
				</div>
			</div>
			<div class="payment_type payment_withholding">
				<div class="tip_info"><span>温馨提示：</span>通过银行转账方式自动支付保险费用。</div>
				<div class="input_area">
					<div class="input_row">
						<label class="input_label">交费帐户户名：</label>
						<label class="info_label">${insurancePolicy.insuranceApplicant.fullName }</label>
					</div>
					<div class="input_row bank">
						<label class="input_label" for="bank">开户银行：</label>
						<input class="input_text alipay_bank_deposit" name="bank" type="text" id="alipay_bank_deposit"/>
					</div>
					<div class="input_row account">
						<label class="input_label" for="account">缴费账户号码：</label>
						<input class="input_text" name="account" type="text" id="alipayPaymentAccount" maxlength="30"/>
						<label class="tip_label">请您填写投保人银行卡或存着账号（不接受信用卡和中国农业银行存折）</label>
					</div>
					<div class="input_row">
						<label class="input_label">证件类型：</label>
						<label class="info_label">
							<c:forEach items="${identifyTypeList}" var="code" varStatus="status">
								<c:if test="${code.id.codeCode eq insurancePolicy.insuranceApplicant.idType }">${code.codeCName }</c:if>
							</c:forEach>
						</label>
					</div>
					<div class="input_row">
						<label class="input_label">证件号码：</label>
						<label class="info_label">${insurancePolicy.insuranceApplicant.idNumber }</label>
						<label class="tip_label">请确认该证件号码与您在银行开户时的证件号码一致</label>
					</div>
				</div>
				<div class="authorisation">
					<div class="author_title">保险费自动转账授权声明</div>
					<p>1. 投保人同意授权您公司通过<span>**********</span>开户银行从投保人提供的帐户<span>**********</span>中转账支付支与您公司约定的首期保险费</p>
					<p>2. 投保人保证此账号有足够的金额支付应交保险费，若因帐户存款余额不足造成转账不成功，致合同不能成立或不能持续有效，因此引起的责任概由投保人承担（为避免清户，账户中余额要始终保持＞10元）。</p>
					<p>3. 投保人同意您公司将多收取的保险费及因延期承保、撤销投保申请、撤单、退保等引起的退费通过投保人提供的该账户返回给投保人。</p>
					<p>4. 投保人因故结清该账户，应该重新开立账户，并及时通知您公司进行变更。</p>
					<p>5. 如因系统原因保费划转不成功，造成合同未成立，保险公司不承担责任。</p>
					<div class="agree_check">
						<div class="agree"></div>
						本人同意上述保费自动转账授权声明的各项内容
					</div>
				</div>
			</div>
			</div>
			<c:if test="${insurancePolicy.orderForm.productCode != '00136' }">
			<div class="policy_area renewal_area">
				<div class="title">
					<div>续期交费、退保帐户</div><div class="doubt">
					<div class="doubt_info">通俗说就是不是第一次缴费就叫续期缴费，请提供专业话术。</div></div>
				</div>
				<div class="input_area">
					<div class="input_row">
						<label class="input_label">交费帐户户名：</label>
						<label class="info_label">${insurancePolicy.insuranceApplicant.fullName }</label>
					</div>
					<div class="input_row provinceCity">
						<label class="input_label" for="account">省份城市：</label>
						<input class="input_text" name="bankProvince" id="bankProvince" type="text"/>
						<input class="input_text" name="bankCity" id="bankCity" type="text" style="+margin-left: 6px;"/>
						<input class="input_text" name="bankArea" id="bankArea" type="text" style="display:none;"/>
					</div>
					<div class="input_row bank">
						<label class="input_label" for="renewal_bank">开户银行：</label>
						<input class="input_text" name="renewal_bank" type="text" id="renewal_bank_deposit"/>
					</div>
					<div class="input_row account">
						<label class="input_label" for="account">缴费账户号码：</label>
						<input class="input_text" name="account" type="text" id="paymentAccount" maxlength="30"/>
						<label class="tip_label">请您填写投保人银行卡或存折账号（不接受信用卡和中国农业银行存折）</label>
					</div>
					<div class="input_row">
						<label class="input_label">证件类型：</label>
						<label class="info_label">
							<c:forEach items="${identifyTypeList}" var="code" varStatus="status">
								<c:if test="${code.id.codeCode eq insurancePolicy.insuranceApplicant.idType }">${code.codeCName }</c:if>
							</c:forEach>
						</label>
					</div>
					<div class="input_row">
						<label class="input_label">证件号码：</label>
						<label class="info_label">${insurancePolicy.insuranceApplicant.idNumber }</label>
						<label class="tip_label">请确认该证件号码与您在银行开户时的证件号码一致</label>
					</div>
				</div>
			</div>
			</c:if>
			<div class="payment_confirm">
				<button id="click_btn" class="click_btn" onclick="return false;">确认支付</button>
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

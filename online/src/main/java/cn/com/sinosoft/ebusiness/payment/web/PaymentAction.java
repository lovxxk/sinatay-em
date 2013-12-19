package cn.com.sinosoft.ebusiness.payment.web;

import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.bankAccount.domain.PaymentAccount;
import cn.com.sinosoft.businessModule.bankAccount.service.facade.PaymentAccountService;
import cn.com.sinosoft.businessModule.enums.dictionary.OrderStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.PolicyStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.TransType;
import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.order.service.facade.OrderFormService;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyService;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.BizCommonService;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeFunctionSwitchService;
import cn.com.sinosoft.ebusiness.payment.service.facade.PaymentService;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProAttrAllowValues;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProduct;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade.GeSaleProductService;
import cn.com.sinosoft.ebusiness.sale.domain.InsureFlowDto;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;
import cn.com.sinosoft.ebusiness.sale.service.facade.SaleService;
import cn.com.sinosoft.ebusiness.sale.service.facade.UnderwritingService;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.UserService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDeptAttributeService;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.util.string.StringUtils;
import cn.com.sinosoft.util.time.DateUtils;

public class PaymentAction extends Struts2Action{

	private static final long serialVersionUID = 2324743279465830424L;

	private static Logger dbLogger = LoggerFactory.getLogger("DBLog");
	
	/** service */	
	@Autowired
	private GeCodeService geCodeService;
	@Autowired
	private OrderFormService orderFormService;
	@Autowired
	private InsurancePolicyService insurancePolicyService;
	@Autowired
	private PaymentAccountService paymentAccountService;
	@Autowired
	private GeDeptAttributeService geDeptAttributeService;
	@Autowired
	private BizCommonService bizCommonService;
	/**功能开关*/
	@Autowired
	private GeFunctionSwitchService geFunctionSwitchService;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private UnderwritingService underwritingService;
	
	@Autowired
	private GeSaleProductService geSaleProductService;
	
	private PaymentService paymentService;
	
	@Autowired
	public GeUserPersonalService geUserPersonalService;
	
	@Autowired
	private UserService userService;
	
	public OrderForm orderForm;
	
	/**页面提示信息*/
	public String message;
	
	public GeSaleProduct geSaleProduct;
	
	/**删除订单*/
	public String orderMessage;
	
	public String inceptionDateMessage;

	
	/**
	 * 选择支付平台
	 * @return
	 */
	public String showPaymentPlatForm(){
		
		getRequest().setAttribute("financialRisk", false);
		return SUCCESS;
	}
	
	
	/**
	 * 支付网关
	 * 进入调用支付网关页面
	 * @return
	 * @throws Exception 
	 */
	public String createPayMent() throws Exception{
		
	  return SUCCESS;
	}
	
	
	/**
	 * 支付网关通过浏览器的回调
	 * @return
	 */
	public String dataFeedForClient(){
		
		return "";
	}
	
	/**
	 * 去支付宝支付
	 * @return
	 */
	public String toAliPay() {
		String proposalSID = super.getRequest().getParameter("proposalSID");
		String account = getRequest().getParameter("paymentAccount");
		String bankType = getRequest().getParameter("bankType");
		String orderId = getRequest().getParameter("orderId");
		String bankProvince = getRequest().getParameter("bankProvince");
		String bankCity = getRequest().getParameter("bankCity");
		orderForm = orderFormService.getOrderFormBySerialNo(orderId);
		
		if(orderForm != null && (orderForm.getOrderStatus()==null?0:orderForm.getOrderStatus()) != OrderStatus.PAYMENTSUCCESS.getValue()){
			InsurancePolicy insurancePolicy = orderForm.getInsurancePolicy();
			if(insurancePolicy != null){
				PaymentAccount paymentAccount = orderForm.getInsurancePolicy().getPaymentAccount();
				if (paymentAccount == null) {
					paymentAccount = new PaymentAccount();
				}
				paymentAccount.setSecBankAccNo(bankType);
				paymentAccount.setSecBankCode(account);
				paymentAccount.setAccProvince(bankProvince);
				paymentAccount.setAccCity(bankCity);
				paymentAccount.setBankAcctType(4); //借记卡
				paymentAccount.addInsurancePolicy(orderForm.getInsurancePolicy());
				
				Map<String, String> map = null;
				if(geFunctionSwitchService.isSwitchOpen("directUnderwriting")){
					map = paymentService.directUnderwriting(orderForm);
					getRequest().setAttribute("alipayParamMap", map);
					orderFormService.addOrderForm(orderForm);
					getRequest().setAttribute("orderForm", orderForm);
					return "underwriting";
				}else{
					map = paymentService.alipay(orderForm);
					getRequest().setAttribute("alipayParamMap", map);
					orderFormService.addOrderForm(orderForm);
					return SUCCESS;
				}
			}else{
				return "orderStatusError";
			}
		}else{
			return "orderStatusError";
		}
	}
	
	/**
	 * 订单详情页面，直接去支付，先进行校验
	 * @return
	 */
	public String toPayment() {
		String serialNo = getRequest().getParameter("id");
		String source = getRequest().getParameter("source");
		if(StringUtils.isNotBlank(source) && source.equals("email")){
			serialNo = new String(Base64.decodeBase64(serialNo));
		}
		orderForm = orderFormService.getOrderFormBySerialNo(serialNo);
		if(orderForm == null){
			orderMessage = "链接失效，不可进行支付";
			return "500";
		}
		if (orderForm != null && !OrderStatus.UNPAID.getValue().equals(orderForm.getOrderStatus())) {
			orderMessage = "订单状态非法，不可进行支付";
			return ERROR;
		}
		
		InsurancePolicy insurancePolicy = orderForm.getInsurancePolicy();
		geSaleProduct = geSaleProductService.findByCode(orderForm.getProductCode());
		if (insurancePolicy.getInceptionDate() == null) {
			inceptionDateMessage = "保单生效日不可为空,请填写保单生效日期";
		} else if (insurancePolicy.getInceptionDate().before(new Date())) {
			inceptionDateMessage = "保单生效日期不得早于当前日期，请更新投保单数据";
		}
		if (org.apache.commons.lang.StringUtils.isNotEmpty(message)) {
			//处理当前产品生效日期
			String inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), 1),"yyyy-MM-dd");
			String effectDateType = geSaleProduct.getEffectDateType()==null?"01":geSaleProduct.getEffectDateType();
			String delayDays = geSaleProduct.getDelayDays()==null?"1":geSaleProduct.getDelayDays();
			String minEffectDateLimited = geSaleProduct.getMinEffectDateLimited()==null?"1":geSaleProduct.getMinEffectDateLimited();
			String maxEffectDateLimited = geSaleProduct.getMaxEffectDateLimited()==null?"1":geSaleProduct.getMaxEffectDateLimited();
			
			if(effectDateType.equals("01")){ //次日凌晨生效
				inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), 1),"yyyy-MM-dd");
			}else if(effectDateType.equals("02")){ //指定生效起始日期
				inceptionDate = DateUtils.formatDate(geSaleProduct.getSpecifyStartDate(),"yyyy-MM-dd");
			}else if(effectDateType.equals("03")){ //最小限制天数 -- 最大限制天数
				inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), Integer.parseInt(minEffectDateLimited)),"yyyy-MM-dd")+"|"+DateUtils.formatDate(DateUtils.addDays(new Date(), Integer.parseInt(maxEffectDateLimited)),"yyyy-MM-dd");;
			}else if(effectDateType.equals("04")){ //延迟生效天数
				inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), Integer.parseInt(delayDays)),"yyyy-MM-dd"); 
			}
			getRequest().setAttribute("inceptionDate", inceptionDate);
			/**初始化保险期间*/
			List<GeSaleProAttrAllowValues> geProductAttrAllowValues=geSaleProduct.getGeSaleProAttrAllowValueses();
			// 排序
			if(geProductAttrAllowValues.size()>1)
				geProductAttrAllowValues =  sortAllowValue(geProductAttrAllowValues);
			
			super.getRequest().setAttribute("geProductAttrAllowValues",geProductAttrAllowValues);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	private List<GeSaleProAttrAllowValues> sortAllowValue(List<GeSaleProAttrAllowValues> productAttrAllowValueList){
		List<GeSaleProAttrAllowValues> listD = new ArrayList<GeSaleProAttrAllowValues>();
		List<GeSaleProAttrAllowValues> listM = new ArrayList<GeSaleProAttrAllowValues>();
		List<GeSaleProAttrAllowValues> listY = new ArrayList<GeSaleProAttrAllowValues>();
		
		for(GeSaleProAttrAllowValues paav : productAttrAllowValueList){
			if(paav.getAttributeTypeValue().equals("Y")){
				listY.add(paav);
			}else if(paav.getAttributeTypeValue().equals("M")){
				listM.add(paav);
			}else{
				listD.add(paav);
			}
		}
		
		sortForAllowValue(listD);
		sortForAllowValue(listM);
		sortForAllowValue(listY);
		
		listD.addAll(listM);
		listD.addAll(listY);
		
		return listD;
	}
	
	
	// 冒泡排序AllowValue
	private static void sortForAllowValue(List<GeSaleProAttrAllowValues> list) {
		GeSaleProAttrAllowValues temp;
		if(list!=null && list.size()>1){
			for (int i = 0; i < list.size(); ++i) {
				for (int j = 0; j < list.size() - i - 1; ++j) {
					if (Integer.parseInt(list.get(j).getAttributeValue()) >  Integer.parseInt(list.get(j+1).getAttributeValue())) {
						temp = list.get(j);
						list.set(j, list.get(j+1));
						list.set(j + 1, temp);
					}
				}
			}
		}
	}
	
	/**
	 * 订单已创建完成，直接去支付宝支付
	 * @return
	 */
	public String alipay() {
		message = "";
		//订单号
		String serialNo = getRequest().getParameter("id");
		orderForm = orderFormService.getOrderFormBySerialNo(serialNo);
		//进行核保，判断核保是否通过
		InsurancePolicy insurancePolicy = orderForm.getInsurancePolicy();
		Map<String, Object> resultMap = underwritingService.underwriting(insurancePolicy, TransType.ST000034.getCoreValue());
		boolean updateFlag = false;
		//更新库
		if (resultMap != null) {
			LCCont lcCont = resultMap.get("LCCont") == null ? null : (LCCont) resultMap.get("LCCont");
			updateFlag = true;
			if (resultMap.get("flag").equals("1")) {
				if (lcCont != null) {
					InsureFlowDto insureFlowDto = new InsureFlowDto();
					QuoteMain quoteMain = new QuoteMain();
					quoteMain.setQuoteNo(insurancePolicy.getQuoteNo());
					insureFlowDto.setQuoteMain(quoteMain);
					if (saleService.saveQuote(lcCont, insureFlowDto, insurancePolicy)) {
//						insurancePolicy.setApplicationNumber(lcCont.getProposalContNo());
						insurancePolicy.setPolicyStatus(PolicyStatus.PROPOSAL_SUCC.getValue());
						insurancePolicy.setPolicyStatusName(PolicyStatus.PROPOSAL_SUCC.getDataName());
						insurancePolicy.setPolicyStatusDesc(PolicyStatus.PROPOSAL_SUCC.getDataName());
						insurancePolicy.setPrecheckDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						insurancePolicy.setReason("核保成功，核保日期：" + new Date().toString());
					}
				} else {
					message = "系统繁忙，请稍后再试！";
				}
			} else {
//				insurancePolicy.setApplicationNumber(lcCont.getProposalContNo());
				insurancePolicy.setPolicyStatus(PolicyStatus.PROPOSAL_FAIL.getValue());
				insurancePolicy.setPolicyStatusName(PolicyStatus.PROPOSAL_FAIL.getDataName());
				insurancePolicy.setPolicyStatusDesc(PolicyStatus.PROPOSAL_FAIL.getDataName());
				insurancePolicy.setReason("核保失败，核保日期：" + new Date().toString() + "，失败原因：（" + resultMap.get("desc") + "）");
				message = "您填写的信息不符合该产品投保条件，请进行修改（" + resultMap.get("desc") + "）";
			}
			if (updateFlag) {
				insurancePolicyService.updateInsurancePolicy(insurancePolicy);
			}
		} else {
			message = "系统繁忙，请稍后再试！";
		}
		
		Map<String, String> map = paymentService.alipay(orderForm);
		getRequest().setAttribute("alipayParamMap", map);

		
		return SUCCESS;
	}
	/**
	 * 支付宝支付成功之后，同步回调函数
	 * @return
	 */
	public String alipaySuccess() {
		Map<String, Object> map = paymentService.alipayReturnMessage(getRequest());
		Boolean flag = (Boolean) map.get("flag");
		orderForm = map.get("orderForm") != null ? (OrderForm) map.get("orderForm") : null;
		if (flag) {
			getRequest().setAttribute("flag", "success");
		} else {
			getRequest().setAttribute("flag", "fail");
		}
		
		return SUCCESS;
	}
	
	/**
	 * 支付宝支付成功之后，异步回调函数
	 * @return
	 */
	public String notifySuccess() {
		Map<String, Object> map = paymentService.alipayReturnMessage(getRequest());
		Boolean flag = (Boolean) map.get("flag");
		orderForm = map.get("orderForm") != null ? (OrderForm) map.get("orderForm") : null;
		if (flag) {
			getRequest().setAttribute("flag", "success");
		} else {
			getRequest().setAttribute("flag", "fail");
		}
		
		return SUCCESS;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public void setOrderForm(OrderForm orderForm) {
		this.orderForm = orderForm;
	}
	
	/**根据投保单创建订单*/
	public OrderForm createOrderOfPolicy(OrderForm orderForm, String proposalSID){
		String orderNo =  bizCommonService.takeSerialNo("01",new Date(), OrderForm.class);
		List<InsurancePolicy> insurancePolicys = null;
		if(StringUtils.isNotBlank(proposalSID)){
			if(proposalSID.indexOf(",") != -1){
				insurancePolicys = insurancePolicyService.findInsurancePoliciesByIds(proposalSID);
			}else{
				InsurancePolicy insurancePolicy = insurancePolicyService.findInsurancePolicyBySerialNo(proposalSID);
				if(insurancePolicy!=null){
					insurancePolicys = new ArrayList<InsurancePolicy>();
					insurancePolicys.add(insurancePolicy);
				}
			}
		}
		if(insurancePolicys!=null && !insurancePolicys.isEmpty()){
			BigDecimal sumPremium = new BigDecimal("0.00");
			for (InsurancePolicy insurancePolicy : insurancePolicys) {
				sumPremium = sumPremium.add(insurancePolicy.getGrossPremium() != null ? insurancePolicy.getGrossPremium() : new BigDecimal("0"));
			}
			if(geFunctionSwitchService.isSwitchOpen("payTest")){
				sumPremium = new BigDecimal("0.01");
			}
			orderForm.setOrderAmount(sumPremium);
			InsurancePolicy insurancePolicy = insurancePolicys.get(0);
			orderForm = insurancePolicy.getOrderForm();
		}
		
		return orderForm;
	}
	
	/**
	 * 支付宝纯网银支付
	 * @return
	 */
	public String paymentToBank() {
		String orderId = getRequest().getParameter("orderId");
		String paymentType = getRequest().getParameter("paymentType");
		String account = getRequest().getParameter("paymentAccount");
		String bankType = getRequest().getParameter("bankType");
		String bankProvince = getRequest().getParameter("bankProvince");
		String bankCity = getRequest().getParameter("bankCity");
		orderForm = orderFormService.getOrderFormBySerialNo(orderId);
		
		if(orderForm != null && (orderForm.getOrderStatus()==null?0:orderForm.getOrderStatus()) != OrderStatus.PAYMENTSUCCESS.getValue()){
			orderForm.setBankType(paymentType);
			InsurancePolicy insurancePolicy = orderForm.getInsurancePolicy();
			if(insurancePolicy != null){
				PaymentAccount paymentAccount = insurancePolicy.getPaymentAccount();
				if (paymentAccount == null) {
					paymentAccount = new PaymentAccount();
				}
				paymentAccount.setSecBankAccNo(bankType);
				paymentAccount.setSecBankCode(account);
				paymentAccount.setAccProvince(bankProvince);
				paymentAccount.setAccCity(bankCity);
				paymentAccount.setBankAcctType(4); //借记卡
				paymentAccount.addInsurancePolicy(insurancePolicy);
				Map<String, String> map = null;
				if(geFunctionSwitchService.isSwitchOpen("directUnderwriting")){
					map = paymentService.directUnderwriting(orderForm);
					getRequest().setAttribute("bankPayMap", map);
					orderFormService.addOrderForm(orderForm);
					getRequest().setAttribute("orderForm", orderForm);
					return "underwriting";
				}else{
					map = paymentService.paymentToBank(orderForm);
					getRequest().setAttribute("bankPayMap", map);
					orderFormService.addOrderForm(orderForm);
					return SUCCESS;
				}
			}else{
				return "orderStatusError";
			}
		}else{
			return "orderStatusError";
		}
		
	}
	
	/**
	 * 纯网银支付 同步回调接口
	 * @return
	 */
	public String synBank() {
		Map<String, Object> map = paymentService.processBankPayMesage(getRequest());
		Boolean flag = (Boolean) map.get("flag");
		orderForm = map.get("orderForm") != null ? (OrderForm) map.get("orderForm") : null;
		if (flag != null && flag) {
			getRequest().setAttribute("flag", "success");
		} else {
			getRequest().setAttribute("flag", "fail");
		}
		
		return SUCCESS;
	}
	/**
	 * 纯网银支付 异步回调接口
	 * @return
	 */
	public String nosynBank() {
		Map<String, Object> map = paymentService.processBankPayMesage(getRequest());
		Boolean flag = (Boolean) map.get("flag");
		orderForm = map.get("orderForm") != null ? (OrderForm) map.get("orderForm") : null;
		if (flag) {
			getRequest().setAttribute("flag", "success");
		} else {
			getRequest().setAttribute("flag", "fail");
		}
		
		return SUCCESS;
	}
	
	/**
	 * 支付成功之后，跳转到订单详情页面
	 * @return
	 */
	public String paymentSuccess() {
		String serialNo = getRequest().getParameter("id");
		orderForm = orderFormService.getOrderFormBySerialNo(serialNo);
		/**获取关系描述*/
		List<GeCode> insRelaToAppList = geCodeService.findAllByGeCodeType("applicantAndInsured");
		super.getRequest().setAttribute("insRelaToAppList", insRelaToAppList);
		
		/**获取性别描述*/
		List<GeCode> sexList = geCodeService.findAllByGeCodeType("Sex");//被保人/受益人性别
		super.getRequest().setAttribute("sexList",sexList);
		
		/**获取证件类型*/
		List<GeCode> idTypeList = geCodeService.findAllByGeCodeType("IdentifyType");//被保人/受益人性别
		super.getRequest().setAttribute("idTypeList",idTypeList);
		if (orderForm.getOrderStatus() == 81) {
			message = "恭喜您，您已支付成功";
		} else {
			message = "支付失败";
		}
		return SUCCESS;
	}


	public void setGeSaleProduct(GeSaleProduct geSaleProduct) {
		this.geSaleProduct = geSaleProduct;
	}

	public void setOrderMessage(String orderMessage) {
		this.orderMessage = orderMessage;
	}

	public void setInceptionDateMessage(String inceptionDateMessage) {
		this.inceptionDateMessage = inceptionDateMessage;
	}
	
	public String toPaymentEmail(){
		String result = "fail";
		try {
			String id = new String(Base64.decodeBase64(super.getRequest().getParameter("id")==null?"".getBytes():super.getRequest().getParameter("id").getBytes()));
			String userId = new String(Base64.decodeBase64(super.getRequest().getParameter("userId")==null?"".getBytes():super.getRequest().getParameter("userId").getBytes()));
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("userID", userId);
			GeUserPersonal user = geUserPersonalService.findUsers(queryRule);
			super.getRequest().setAttribute("id", id);
			super.getRequest().setAttribute("source", "email");
			if(user != null){
				userService.setCurrentUser(super.getSession(), user);
				result = "succ";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}

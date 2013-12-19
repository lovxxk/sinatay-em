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
	/**���ܿ���*/
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
	
	/**ҳ����ʾ��Ϣ*/
	public String message;
	
	public GeSaleProduct geSaleProduct;
	
	/**ɾ������*/
	public String orderMessage;
	
	public String inceptionDateMessage;

	
	/**
	 * ѡ��֧��ƽ̨
	 * @return
	 */
	public String showPaymentPlatForm(){
		
		getRequest().setAttribute("financialRisk", false);
		return SUCCESS;
	}
	
	
	/**
	 * ֧������
	 * �������֧������ҳ��
	 * @return
	 * @throws Exception 
	 */
	public String createPayMent() throws Exception{
		
	  return SUCCESS;
	}
	
	
	/**
	 * ֧������ͨ��������Ļص�
	 * @return
	 */
	public String dataFeedForClient(){
		
		return "";
	}
	
	/**
	 * ȥ֧����֧��
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
				paymentAccount.setBankAcctType(4); //��ǿ�
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
	 * ��������ҳ�棬ֱ��ȥ֧�����Ƚ���У��
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
			orderMessage = "����ʧЧ�����ɽ���֧��";
			return "500";
		}
		if (orderForm != null && !OrderStatus.UNPAID.getValue().equals(orderForm.getOrderStatus())) {
			orderMessage = "����״̬�Ƿ������ɽ���֧��";
			return ERROR;
		}
		
		InsurancePolicy insurancePolicy = orderForm.getInsurancePolicy();
		geSaleProduct = geSaleProductService.findByCode(orderForm.getProductCode());
		if (insurancePolicy.getInceptionDate() == null) {
			inceptionDateMessage = "������Ч�ղ���Ϊ��,����д������Ч����";
		} else if (insurancePolicy.getInceptionDate().before(new Date())) {
			inceptionDateMessage = "������Ч���ڲ������ڵ�ǰ���ڣ������Ͷ��������";
		}
		if (org.apache.commons.lang.StringUtils.isNotEmpty(message)) {
			//����ǰ��Ʒ��Ч����
			String inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), 1),"yyyy-MM-dd");
			String effectDateType = geSaleProduct.getEffectDateType()==null?"01":geSaleProduct.getEffectDateType();
			String delayDays = geSaleProduct.getDelayDays()==null?"1":geSaleProduct.getDelayDays();
			String minEffectDateLimited = geSaleProduct.getMinEffectDateLimited()==null?"1":geSaleProduct.getMinEffectDateLimited();
			String maxEffectDateLimited = geSaleProduct.getMaxEffectDateLimited()==null?"1":geSaleProduct.getMaxEffectDateLimited();
			
			if(effectDateType.equals("01")){ //�����賿��Ч
				inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), 1),"yyyy-MM-dd");
			}else if(effectDateType.equals("02")){ //ָ����Ч��ʼ����
				inceptionDate = DateUtils.formatDate(geSaleProduct.getSpecifyStartDate(),"yyyy-MM-dd");
			}else if(effectDateType.equals("03")){ //��С�������� -- �����������
				inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), Integer.parseInt(minEffectDateLimited)),"yyyy-MM-dd")+"|"+DateUtils.formatDate(DateUtils.addDays(new Date(), Integer.parseInt(maxEffectDateLimited)),"yyyy-MM-dd");;
			}else if(effectDateType.equals("04")){ //�ӳ���Ч����
				inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), Integer.parseInt(delayDays)),"yyyy-MM-dd"); 
			}
			getRequest().setAttribute("inceptionDate", inceptionDate);
			/**��ʼ�������ڼ�*/
			List<GeSaleProAttrAllowValues> geProductAttrAllowValues=geSaleProduct.getGeSaleProAttrAllowValueses();
			// ����
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
	
	
	// ð������AllowValue
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
	 * �����Ѵ�����ɣ�ֱ��ȥ֧����֧��
	 * @return
	 */
	public String alipay() {
		message = "";
		//������
		String serialNo = getRequest().getParameter("id");
		orderForm = orderFormService.getOrderFormBySerialNo(serialNo);
		//���к˱����жϺ˱��Ƿ�ͨ��
		InsurancePolicy insurancePolicy = orderForm.getInsurancePolicy();
		Map<String, Object> resultMap = underwritingService.underwriting(insurancePolicy, TransType.ST000034.getCoreValue());
		boolean updateFlag = false;
		//���¿�
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
						insurancePolicy.setReason("�˱��ɹ����˱����ڣ�" + new Date().toString());
					}
				} else {
					message = "ϵͳ��æ�����Ժ����ԣ�";
				}
			} else {
//				insurancePolicy.setApplicationNumber(lcCont.getProposalContNo());
				insurancePolicy.setPolicyStatus(PolicyStatus.PROPOSAL_FAIL.getValue());
				insurancePolicy.setPolicyStatusName(PolicyStatus.PROPOSAL_FAIL.getDataName());
				insurancePolicy.setPolicyStatusDesc(PolicyStatus.PROPOSAL_FAIL.getDataName());
				insurancePolicy.setReason("�˱�ʧ�ܣ��˱����ڣ�" + new Date().toString() + "��ʧ��ԭ�򣺣�" + resultMap.get("desc") + "��");
				message = "����д����Ϣ�����ϸò�ƷͶ��������������޸ģ�" + resultMap.get("desc") + "��";
			}
			if (updateFlag) {
				insurancePolicyService.updateInsurancePolicy(insurancePolicy);
			}
		} else {
			message = "ϵͳ��æ�����Ժ����ԣ�";
		}
		
		Map<String, String> map = paymentService.alipay(orderForm);
		getRequest().setAttribute("alipayParamMap", map);

		
		return SUCCESS;
	}
	/**
	 * ֧����֧���ɹ�֮��ͬ���ص�����
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
	 * ֧����֧���ɹ�֮���첽�ص�����
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
	
	/**����Ͷ������������*/
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
	 * ֧����������֧��
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
				paymentAccount.setBankAcctType(4); //��ǿ�
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
	 * ������֧�� ͬ���ص��ӿ�
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
	 * ������֧�� �첽�ص��ӿ�
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
	 * ֧���ɹ�֮����ת����������ҳ��
	 * @return
	 */
	public String paymentSuccess() {
		String serialNo = getRequest().getParameter("id");
		orderForm = orderFormService.getOrderFormBySerialNo(serialNo);
		/**��ȡ��ϵ����*/
		List<GeCode> insRelaToAppList = geCodeService.findAllByGeCodeType("applicantAndInsured");
		super.getRequest().setAttribute("insRelaToAppList", insRelaToAppList);
		
		/**��ȡ�Ա�����*/
		List<GeCode> sexList = geCodeService.findAllByGeCodeType("Sex");//������/�������Ա�
		super.getRequest().setAttribute("sexList",sexList);
		
		/**��ȡ֤������*/
		List<GeCode> idTypeList = geCodeService.findAllByGeCodeType("IdentifyType");//������/�������Ա�
		super.getRequest().setAttribute("idTypeList",idTypeList);
		if (orderForm.getOrderStatus() == 81) {
			message = "��ϲ��������֧���ɹ�";
		} else {
			message = "֧��ʧ��";
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

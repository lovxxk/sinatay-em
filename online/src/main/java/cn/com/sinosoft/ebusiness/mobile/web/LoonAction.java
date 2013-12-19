package cn.com.sinosoft.ebusiness.mobile.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ins.framework.web.Struts2Action;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.bankAccount.domain.PaymentAccount;
import cn.com.sinosoft.businessModule.enums.dictionary.PolicyStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.TransType;
import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.order.service.facade.OrderFormService;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyService;
import cn.com.sinosoft.ebusiness.ali.config.AlipayConfig;
import cn.com.sinosoft.ebusiness.ali.ldc.LDCode;
import cn.com.sinosoft.ebusiness.ali.util.AlipayCore;
import cn.com.sinosoft.ebusiness.ali.util.AlipayMobileCore;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.BizCommonService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeFunctionSwitchService;
import cn.com.sinosoft.ebusiness.payment.service.facade.PaymentService;
import cn.com.sinosoft.ebusiness.sale.domain.InsureFlowDto;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteApplicant;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteInsured;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteLiability;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteMainService;
import cn.com.sinosoft.ebusiness.sale.service.facade.SaleService;
import cn.com.sinosoft.ebusiness.sale.service.facade.SmsSendService;
import cn.com.sinosoft.ebusiness.sale.service.facade.UnderwritingService;
import cn.com.sinosoft.ebusiness.sale.service.facade.VerificationService;
import cn.com.sinosoft.ebusiness.sale.web.SaleAction;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.AutoUserDto;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.UserService;
import cn.com.sinosoft.ebusiness.sys.exception.userException.BusinessException;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.SaleException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONObject;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.util.string.StringUtils;


public class LoonAction extends Struts2Action {

	private static final long serialVersionUID = 4661075874288228258L;

	private static Logger log = LoggerFactory.getLogger(LoonAction.class);
	
	/**���ܿ���*/
	@Autowired
	private GeFunctionSwitchService geFunctionSwitchService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SmsSendService smsSendService;
	
	@Autowired
	private VerificationService verificationService;
	
	@Autowired
	private BizCommonService bizCommonService;
	
	@Autowired
	private QuoteMainService quoteMainService;
	
	@Autowired
	private InsurancePolicyService insurancePolicyService;
	
	@Autowired
	private UnderwritingService underwritingService;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private SaleAction saleAction;
	
	@Autowired
	private OrderFormService orderFormService;
	
	@Autowired
	private PaymentService paymentService;
	
	public List<LDCode> getProvinces() {
		return CodeInfo.provinces;
	}
	
	public List<LDCode> getCities() {
		List<LDCode> cities = new ArrayList<LDCode>();
		for (LDCode allCitie : CodeInfo.cities) {
			if (allCitie.getOtherSign().equals(province)) {
				cities.add(allCitie);
			}
		}
		return cities;
	}
	
	public List<LDCode> getCounties() {
		List<LDCode> counties = new ArrayList<LDCode>();
		for (LDCode allCountie : CodeInfo.counties) {
			if (allCountie.getOtherSign().equals(city)) {
				counties.add(allCountie);
			}
		}
		return counties;
	}
	
	public List<LDCode> getBanks() {
		return CodeInfo.banks;
	}
	
	private String province;
	
	private String city;
	
	private String countie;
	
	private String userName;
	
	private String credentialsNumber;
	
	private String gender;
	
	private String birthDate;
	
	private String mobilePhone;
	
	private String email;
	
	private String address;
	
	private String number;
	// �����
	private String quoteNo;
	// �˱���
	private String proposalContNo = "";
	// Ͷ����
	private String proposalSID = "";
	
	private String orderId;
	
	private String productCode;
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountie() {
		return countie;
	}

	public void setCountie(String countie) {
		this.countie = countie;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCredentialsNumber() {
		return credentialsNumber;
	}

	public void setCredentialsNumber(String credentialsNumber) {
		this.credentialsNumber = credentialsNumber;
	}
	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}

	public String getProposalContNo() {
		return proposalContNo;
	}

	public void setProposalContNo(String proposalContNo) {
		this.proposalContNo = proposalContNo;
	}

	public String getProposalSID() {
		return proposalSID;
	}

	public void setProposalSID(String proposalSID) {
		this.proposalSID = proposalSID;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * ����������Ʊ�
	 * @return
	 */
	public String loonIndex(){
		return SUCCESS;
	}
	
	/**
	 * ����������Ʊ�-Ͷ����֪
	 * @return
	 */
	public String loonDeclares(){
		return SUCCESS;
	}
	
	/**
	 * ����������Ʊ�-��Ʒ����
	 * @return
	 */
	public String loonProductInfo(){
		return SUCCESS;
	}
	
	/**
	 * ����������Ʊ�-�ۺ����
	 * @return
	 */
	public String loonServices(){
		return SUCCESS;
	}
	
	/**
	 * ����������Ʊ�-��������
	 * @return
	 */
	public String loonQuestions(){
		return SUCCESS;
	}
	
	/**
	 * ����������Ʊ�-��д�ͻ���Ϣ
	 * @return
	 */
	public String loonCustomInfoInput() {
		return SUCCESS;
	}
	
	/**
	 * ����������Ʊ�-��Ϣȷ��
	 * @return
	 */
	public String loonConfirm() {
		getRequest().setAttribute("total", String.valueOf(Integer.parseInt(number) * 1000) + ".00");
		return SUCCESS;
	}
	
	/**
	 * ����������Ʊ�-����֧��
	 * @return
	 */
	public String loonPay() {
		getRequest().setAttribute("total", String.valueOf(Integer.parseInt(number) * 1000) + ".00");
		return SUCCESS;
	}
	
	public String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url + "?" + param;
			URL realUrl = new URL(urlName);
			URLConnection conn = null;
			if (geFunctionSwitchService.isSwitchOpen("payTest")) {
				// ���ô���
				SocketAddress addr = new InetSocketAddress("10.20.3.168", 3128);
				Proxy typeProxy = new Proxy(Proxy.Type.HTTP, addr);
				// �򿪺�URL֮�������
				conn = realUrl.openConnection(typeProxy);
			} else {
				// �򿪺�URL֮�������
				conn = realUrl.openConnection();
			}
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// ����ʵ�ʵ�����
			conn.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = conn.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * ֧����֧���ɹ�֮��ͬ���ص�����
	 * @return
	 */
	public String alipaySuccess() {
		Map<String, Object> map = paymentService.alipayMobileReturnMessage(getRequest());
		Boolean flag = (Boolean) map.get("flag");
		OrderForm orderForm = map.get("orderForm") != null ? (OrderForm) map.get("orderForm") : null;
		if (flag) {
			getRequest().setAttribute("flag", "success");
		} else {
			getRequest().setAttribute("flag", "fail");
		}
		getRequest().setAttribute("orderForm", orderForm);
		
		return SUCCESS;
	}
	
	/**
	 * ֧����֧���ɹ�֮���첽�ص�����
	 * @return
	 */
	public String notifySuccess() {
		Map<String, Object> map = paymentService.alipayMobileReturnMessage(getRequest());
		Boolean flag = (Boolean) map.get("flag");
		OrderForm orderForm = map.get("orderForm") != null ? (OrderForm) map.get("orderForm") : null;
		if (flag) {
			getRequest().setAttribute("flag", "success");
		} else {
			getRequest().setAttribute("flag", "fail");
		}
		getRequest().setAttribute("orderForm", orderForm);
		
		return SUCCESS;
	}
	
	public void loonCheckUpdatePayfees() {
		try {
			OrderForm orderForm = orderFormService.getOrderFormBySerialNo(orderId);
			JSONObject jsonObject = new JSONObject();
			if ("1".equals(String.valueOf(orderForm.getPayStatus()))) {
				log.info("Ͷ�����ţ�" 
						+ orderForm.getInsurancePolicy().getApplicationNumber() 
						+ "���Ѿ�֧������");
				jsonObject.put("returnFlag", "true");
				jsonObject.put("message", "Ͷ�����ţ�" 
						+ orderForm.getInsurancePolicy().getApplicationNumber() 
						+ "���Ѿ�֧������");
			} else {
				jsonObject.put("returnFlag", "false");
				jsonObject.put("message", "Ͷ�����ţ�" 
						+ orderForm.getInsurancePolicy().getApplicationNumber() 
						+ "����δ���й�֧����");
			}
			getResponse().getWriter().write(jsonObject.toString());
			getResponse().getWriter().flush();
		} catch (Exception e) {
			log.error("����Ƿ�֧������", e.fillInStackTrace());
		}
	}
	
	/**
	 * ȥ֧����֧��
	 * @return
	 */
	public String toAliPay() {
		String account = getRequest().getParameter("paymentAccount");
		String bankType = getRequest().getParameter("bankType");
		String bankProvince = getRequest().getParameter("bankProvince");
		String bankCity = getRequest().getParameter("bankCity");
		OrderForm orderForm = orderFormService.getOrderFormBySerialNo(orderId);
		if ("1".equals(String.valueOf(orderForm.getPayStatus()))) {
			log.info("Ͷ�����ţ�" 
					+ orderForm.getInsurancePolicy().getApplicationNumber() 
					+ "���Ѿ�֧������");
			getRequest().setAttribute("error", "Ͷ�����ţ�" 
					+ orderForm.getInsurancePolicy().getApplicationNumber() 
					+ "���Ѿ�֧������");
			return "error";
		}
		
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
		
		try {
			Map<String, String> sParaTemp = new HashMap<String, String>();
			
//				#mobile alipay 
//				mobile_partner = 2088201564809153
//				mobile_key = umz4aea6g97skeect0jtxigvjkrimd0o
//				mobile_seller_email = alipay-test12@alipay.com
//				mobile_https_alipay_url = http://wappaygw.alipay.com/service/rest.htm
//				mobile_token_service = alipay.wap.trade.create.direct
//				mobile_pay_service = alipay.wap.auth.authAndExecute
//				mobile_call_back_url = http://ebiz-test.sinatay.com/alipay/mobileBack
//				mobile_notify_url = http://ebiz-test.sinatay.com/alipay/mobileNotify
			
//				sParaTemp.put("service", AlipayConfig.mobile_token_service);
//				sParaTemp.put("format", AlipayConfig.format);
//				sParaTemp.put("v", AlipayConfig.v);
//				sParaTemp.put("partner", AlipayConfig.mobile_partner);
//				sParaTemp.put("req_id", payInfo.getOut_trade_no());
//				sParaTemp.put("sec_id", AlipayConfig.sign_type);
			
			String out_trade_no = orderForm.getOrderSerialNumber();
			//������ ����
			String total_fee = orderForm.getOrderAmount() + "";
			sParaTemp.put("service", "alipay.wap.trade.create.direct");
			sParaTemp.put("format", AlipayConfig.mobile_format);
			sParaTemp.put("v", AlipayConfig.mobile_v);
			sParaTemp.put("partner", AlipayConfig.mobile_partner);
			sParaTemp.put("req_id", out_trade_no);
			sParaTemp.put("sec_id", "MD5");
			
			if(geFunctionSwitchService.isSwitchOpen("payTest")){
				total_fee = "0.01";
			} else {
				total_fee = String.valueOf(orderForm.getInsurancePolicy().getGrossPremium().doubleValue());
			}
			
//				if(!"PRD".equals(ConfigUtil.getString("current_environment"))){
//					total_fee = "0.01";
//				}
			String req_data = AlipayMobileCore.getTokenReqData("��̩�����������",out_trade_no,total_fee);
			sParaTemp.put("req_data", req_data);
			//����ǩ�����
	        String mysign = AlipayCore.buildMysignForMobile(sParaTemp);
	        //ǩ�������ǩ����ʽ���������ύ��������
	        sParaTemp.put("sign", mysign);
	        try {
				sParaTemp.put("req_data", URLEncoder.encode(req_data,"UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
	        String parameter = AlipayCore.createLinkString(sParaTemp);
			String result_token = sendGet(AlipayConfig.mobile_https_alipay_url, parameter);
	        String[] result = AlipayMobileCore.parseTokenRes(result_token);
	        System.out.println("=================== " +result_token + " && " + result[0] + " ======================");
	        if ("1".equals(result[0])){
	        	String sHtmlText = AlipayMobileCore.create_wap_pay(result[1]);
	        	//֧����Ϣ���
	        	orderFormService.addOrderForm(orderForm);
				
				PrintWriter out = getResponse().getWriter();
				getResponse().setCharacterEncoding("utf-8");
				getResponse().setContentType("text/html;charset=UTF-8");
				out.print(sHtmlText);
				out.close();
	        }
		} catch (BusinessException e) {
			//����֧��ҵ���쳣
			log.warn("�ɷ�ҳ-ȷ��֧��", e);
			getRequest().setAttribute("error", "�ɷ�ҳ-ȷ��֧������");
			return "error";
		} catch (Exception e) {
			log.error("�ɷ�ҳ-ȷ��֧��", e);
			getRequest().setAttribute("error", "�ɷ�ҳ-ȷ��֧������");
			return "error";
		}
		
//		Map<String, String> map = paymentService.alipay(orderForm);
//		orderFormService.addOrderForm(orderForm);
		System.out.println("=============== �ύ֧���� ===============");
		return null;
	}
	
	public void loonVerifyCustomInfo() {
		try {
			JSONObject jsonObject = new JSONObject();
			
			AutoUserDto au = new AutoUserDto();
			au.setFullName(userName);
			au.setGender(Integer.parseInt(gender));
			au.setBirthDate(DateUtils.parseDate(birthDate, "yyyy-MM-dd"));
			au.setIdType(0);
			au.setIdNumber(credentialsNumber);
			au.setEmail(email);
			au.setMobilePhone(mobilePhone);
			
			au = userService.autoRegisterAndLogin(au, super.getSession(false));
			if ("-1".equals(au.getRegFlag())) {
				jsonObject.put("isSucc", String.valueOf(false));
				jsonObject.put("message", "���Ѿ�����̩���յ��û������½��̩�������򱣵���");
				getResponse().getWriter().write(jsonObject.toString());
				getResponse().getWriter().flush();
				return;
			} else 
			if ("1".equals(au.getRegFlag())) {
				// �����
				quoteNo = "";
				// �˱���
				proposalContNo = "";
				// Ͷ��������
				proposalSID = "";
				List<String> params = new ArrayList<String>();
				// ���ŷ�����
				String sender = "9005";
				// ��������
				String msgComment = "ϵͳ��Ϊ���Զ�ע�ᣬ����Ϊ" + au.getPlaintextPassword() + "��";
				params.add(au.getMobilePhone());
				params.add(au.getPlaintextPassword());
				smsSendService.smsSend(true, "8", params, "1",
						au.getMobilePhone(), msgComment, sender, null);
			}
			
			QuoteMain quoteMain = new QuoteMain();
			quoteMain.setQuoteNo(quoteNo);
			quoteMain.setHxssProposalNo(proposalContNo);
			quoteMain.setProposalSID(proposalSID);
			quoteMain.setPremium(new BigDecimal(Integer.parseInt(number) * 1000));
			quoteMain.setInsuredAmount(BigDecimal.ZERO);
			quoteMain.setGrossPremium(quoteMain.getPremium());
			quoteMain.setFaceAmount(quoteMain.getInsuredAmount());
			quoteMain.setProductCode(productCode);
			quoteMain.setProductName("��̩������Ʊ�");
			quoteMain.setStep(1);
			Date now = new Date();
			quoteMain.setInputDate(now);
			quoteMain.setInceptionDate(DateUtils.addDays(now, 1));
			/** ���Է��� */
			quoteMain.setUnitCount(Integer.parseInt(number));
			quoteMain.setQuoteInsuredNumber(1);
			quoteMain.setBenefitPeriod(80);
			quoteMain.setBenefitPeriodType(20);
			quoteMain.setDivType(5);
			quoteMain.setEid("G120130902152647034");
			quoteMain.setCurrency("RMB");
			quoteMain.setQuoteStatus(1);
			quoteMain.setUserType("01");
			quoteMain.setRenewalFlag("01");
			quoteMain.setBusinessSource("WEB_PERSON");
			quoteMain.setGroupChannel("W");
			quoteMain.setSellType("20");
			quoteMain.setPaymentDurationMode(4);
			
			List<QuoteLiability> quoteLiabilities = new ArrayList<QuoteLiability>();
			quoteMain.setQuoteLiabilities(quoteLiabilities);
			QuoteLiability quoteLiability = new QuoteLiability();
			quoteLiabilities.add(quoteLiability);
			quoteLiability.setProductCode(productCode);
			quoteLiability.setProductName("��̩������Ʊ�");
			quoteLiability.setInceptionDate(quoteMain.getInceptionDate());
			quoteLiability.setPremium(quoteMain.getPremium());
			quoteLiability.setGrossPremium(BigDecimal.ZERO);
			quoteLiability.setBenefitPeriod(80);
			quoteLiability.setBenefitPeriodType(20);
			quoteLiability.setUnitCount(1);
			quoteLiability.setLiabilityCode("00115500");
			quoteLiability.setCoreCode("00115500");
			quoteLiability.setMainRiskCode("00115500");
			quoteLiability.setLiabilityName("��̩��������ȫ���գ������ͣ�");
			quoteLiability.setSubRiskFlag(1);
			quoteLiability.setBonusGetMode(5);
			quoteLiability.setLiabilityOrder(1);
			quoteLiability.setGrossInsuredAmount(BigDecimal.ZERO);
			quoteLiability.setPaymentDurationMode(quoteMain.getPaymentDurationMode());
			
			/** ����Ͷ���� */
			QuoteApplicant quoteApplicant = new QuoteApplicant();
			quoteApplicant.setQuoteMain(quoteMain);
			quoteApplicant.setFullName(userName);
			quoteApplicant.setIdType(0);
			quoteApplicant.setIdNumber(credentialsNumber);
			quoteApplicant.setMobilePhoneNumber(mobilePhone);
			quoteApplicant.setEmail(email);
			quoteApplicant.setProvince(province);
			quoteApplicant.setCity(city);
			quoteApplicant.setCounty(countie);
			quoteApplicant.setAddressLines(address);
			quoteApplicant.setPostalCode("310000");
			quoteApplicant.setBirthDate(DateUtils.parseDate(birthDate, "yyyy-MM-dd"));
			quoteApplicant.setGender(Integer.parseInt(gender));
			quoteApplicant.setOccupationCode("1050102");
			quoteApplicant.setOccupationClass("1");
			quoteApplicant.setRelatedToInsured(0);
			
			quoteMain.setQuoteApplicant(quoteApplicant);
			
			/** ���Ա������� */
			List<QuoteInsured> quoteInsureds = new ArrayList<QuoteInsured>();
			QuoteInsured quoteInsured = new QuoteInsured();
			quoteInsured.setQuoteMain(quoteMain);
			quoteInsured.setFullName(quoteApplicant.getFullName());
			quoteInsured.setIdType(quoteApplicant.getIdType());
			quoteInsured.setIdNumber(quoteApplicant.getIdNumber());
			quoteInsured.setProvince(quoteApplicant.getProvince());
			quoteInsured.setCity(quoteApplicant.getCity());
			quoteInsured.setCounty(quoteApplicant.getCounty());
			quoteInsured.setAddressLines(quoteApplicant.getAddressLines());
			quoteInsured.setPostalCode(quoteApplicant.getPostalCode());
			quoteInsured.setBirthDate(quoteApplicant.getBirthDate());
			quoteInsured.setGender(quoteApplicant.getGender());
			quoteInsured.setOccupationCode(quoteApplicant.getOccupationCode());
			quoteInsured.setOccupationClass(quoteApplicant.getOccupationClass());
			quoteInsured.setRelatedToApplicant(0);
			quoteInsureds.add(quoteInsured);
			quoteMain.setQuoteInsureds(quoteInsureds);
			
			InsureFlowDto insureFlowDto = new InsureFlowDto();
			insureFlowDto.setQuoteMain(quoteMain);
			insureFlowDto.setProductCode(productCode);
			insureFlowDto.setProposalSID(proposalSID);
			insureFlowDto.setIsExsitBnf("0");
			insureFlowDto.setInsuranceType("0");
			insureFlowDto.setAreaCodeProDesc(province);
			insureFlowDto.setAreaCodeDesc(city);
			
			String result = "";
			//У��Ͷ�����ֻ���
			Map<String, String> verificationMobileMap = null;
			if (geFunctionSwitchService
					.isSwitchOpen("verificationMobilePhoneNumber")) {
				verificationMobileMap = verificationService
						.verificationMobilePhoneNumber(userName, "0",
								credentialsNumber, mobilePhone);
			}
			if (verificationMobileMap != null) {
				if (!verificationMobileMap.get("flag").equals("1")) {
					result = verificationMobileMap.get("desc");
				}
			}
			
			//У�鱻���������֤
			Map<String, String> verificationIdCardMap = null;
			if (geFunctionSwitchService.isSwitchOpen("verificationIdCard")
					&& quoteInsured != null && StringUtils.isBlank(result)) {
				String fsd = quoteInsured.getIdNumber().length() < 6 ? quoteInsured
						.getIdNumber() : quoteInsured.getIdNumber().substring(
						0, 6);
				verificationIdCardMap = verificationService.verificationIdCard(
						quoteInsured.getIdNumber(), quoteInsured.getFullName(),
						fsd, "WEB");
			}
			if (verificationIdCardMap != null) {
				if (!verificationIdCardMap.get("flag").equals("1")) {
					result = verificationIdCardMap.get("desc");
				}
			}
			
			String save_topInsured = super.getRequest().getParameter("save_topInsured");
			save_topInsured = "1";
			//���ݴ�������ֵ����һЩ����
			GeUserPersonal geUserPersonal = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
			String userID = "";
			if (geUserPersonal == null) {
				geUserPersonal = new GeUserPersonal();
				geUserPersonal.setUserName(userName);
				geUserPersonal.setSex(gender);
				geUserPersonal.setBirthday(DateUtils.parseDate(birthDate, "yyyy-MM-dd"));
				geUserPersonal.setIdentifyType("0");
				geUserPersonal.setIdentifyNumber(credentialsNumber);
			} else {
				userID = geUserPersonal.getUserID();
				insureFlowDto.getQuoteMain().setUserId(userID);
			}
			
			if (StringUtils.isNotBlank(quoteNo)) {
				quoteMain = quoteMainService
						.findQuoteMainByPk(quoteNo);
				if (quoteMain != null) {
					insureFlowDto.getQuoteMain().setHxssProposalNo(
							quoteMain.getHxssProposalNo());
					insureFlowDto.getQuoteMain().setProposalSID(
							quoteMain.getProposalSID() == null ? proposalSID
									: quoteMain.getProposalSID());
				}
			}
			
			// ���㵥���
			if (StringUtils.isBlank(result)) {
				boolean bl = false;
				try {
					if (StringUtils.isBlank(quoteNo)) {
						quoteNo = bizCommonService.takeSerialNo("04",
								new Date(), QuoteMain.class);
					}
					QuoteMain tempQuoteMain = quoteMainService
							.findQuoteMainByPk(quoteNo);
					if (tempQuoteMain != null) {
						quoteMainService.deleteQuoteMain(quoteNo);
					}
					bl = saleService.saveQuote(insureFlowDto, "3", quoteNo,
							geUserPersonal.getUserID(), save_topInsured);
					super.getSession().setAttribute(userID + "_quoteNo",
							quoteNo);
					if (bl) {
						insureFlowDto.getQuoteMain().setQuoteNo(quoteNo);
						insureFlowDto.getQuoteMain().setHxssProposalNo(
								proposalContNo);
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("���㵥�������쳣���û��ţ�" + geUserPersonal.getUserID()
							+ "���쳣��ջ��Ϣ��" + e.getMessage());
					result = "ϵͳ��æ�����Ժ����ԣ�";
				}
				if (!bl) {
					result = "ϵͳ��æ�����Ժ����ԣ�";
				}
			}
			
//			jsonObject.put("quoteNo", quoteNo);
			
			
			InsurancePolicy insurancePolicy = null;
			// ��⣬�Ȳ�ѯ�����û����ֱ����⣬����ɾ�������������
			if (StringUtils.isBlank(result)) {
				try {
					if (StringUtils.isBlank(proposalSID)) {
						insureFlowDto.getQuoteMain().getProposalSID();// Ͷ�������
					}
					if (StringUtils.isNotBlank(proposalSID)) {
						insurancePolicy = insurancePolicyService
								.findInsurancePolicyBySerialNo(proposalSID);
					}
					if (insurancePolicy != null) {
						insurancePolicyService
								.deleteInsurancePolicy(insurancePolicy);
					}
					// ����ҵ����Դ��WEB��
					insureFlowDto.getQuoteMain()
							.setBusinessSource("WEB_PERSON");
					// �����������루W����������
					insureFlowDto.getQuoteMain().setGroupChannel("W");
					insureFlowDto.getQuoteMain().setSellType("20");
					insurancePolicy = saleService
							.createInsurancePolicy(insureFlowDto);
					insurancePolicy.setQuoteNo(quoteNo);
					insureFlowDto.getQuoteMain().setProposalSID(
							insurancePolicy.getSerialNo());
					insureFlowDto.setProposalSID(insurancePolicy.getSerialNo());
					// �ӹ�
					if ("1".equals(insureFlowDto.getMoreBuyFlag())) {
						// ��Ӽӹ��������
						String moreBuyNo = bizCommonService.takeSerialNo("05",
								new Date(), InsurancePolicy.class);
						insurancePolicy.setMoreBuyNo(moreBuyNo);
						String moreBuyProposalSID = insureFlowDto
								.getMoreBuyProposalSID();
						if (StringUtils.isNotBlank(moreBuyProposalSID)) {
							List<InsurancePolicy> insurancePolicies = insurancePolicyService
									.findInsurancePoliciesByIds(moreBuyProposalSID);
							for (InsurancePolicy _insurancePolicy : insurancePolicies) {
								_insurancePolicy.setMoreBuyNo(moreBuyNo);
							}
							insureFlowDto
									.setMoreBuyProposalSID(moreBuyProposalSID
											+ ","
											+ insurancePolicy.getSerialNo());
						}
					}
				} catch (SaleException e) {
					result = "ϵͳ��æ�����Ժ����ԣ���" + e.showMessage() + "��";
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("Ͷ�����������쳣���û��ţ�" + geUserPersonal.getUserID()
							+ "���쳣��Ϣ��" + e.getMessage());
					result = "ϵͳ��æ�����Ժ����ԣ�";
				}
			}
			
			if (StringUtils.isBlank(result)) {
				try {
					// �˱�
					insurancePolicy.setTransIdentify(TransType.ST000022
							.getCoreValue());
					Map<String, Object> resultMap = underwritingService
							.underwriting(insureFlowDto, insurancePolicy,
									geUserPersonal);
					
					boolean updateFlag = false;
					// ���¿�
					if (resultMap != null) {
						updateFlag = false;
						LCCont lcCont = resultMap.get("LCCont") == null ? null
								: (LCCont) resultMap.get("LCCont");
						if (resultMap.get("flag").equals("1")) {
							Map<String, Object> resultMap1 = underwritingService
									.underwriting(insureFlowDto, insurancePolicy,
											geUserPersonal, "ST000022");
							if (resultMap1 != null) {
								updateFlag = true;
								lcCont = resultMap1.get("LCCont") == null ? null
										: (LCCont) resultMap1.get("LCCont");
								if (resultMap.get("flag").equals("1")) {
									if (lcCont != null) {
										if (saleService.saveQuote(lcCont,
												insureFlowDto, insurancePolicy)) {
											jsonObject.put("proposalContNo",
													lcCont.getProposalContNo());
											insurancePolicy.setApplicationNumber(lcCont
													.getProposalContNo());
											insurancePolicy
													.setPolicyStatus(PolicyStatus.PROPOSAL_SUCC
															.getValue());
											insurancePolicy
													.setPolicyStatusName(PolicyStatus.PROPOSAL_SUCC
															.getDataName());
											insurancePolicy
													.setPolicyStatusDesc(PolicyStatus.PROPOSAL_SUCC
															.getDataName());
											insurancePolicy
													.setPrecheckDate(new SimpleDateFormat(
															"yyyy-MM-dd HH:mm:ss")
															.format(new Date()));
											insurancePolicy.setReason("�˱��ɹ����˱����ڣ�"
													+ new Date().toString());
											result = "success";
										}
									} else {
										result = "ϵͳ��æ�����Ժ����ԣ�";
									}
								} else {
									jsonObject.put("proposalContNo",
											lcCont.getProposalContNo());
									insurancePolicy.setApplicationNumber(lcCont
											.getProposalContNo());
									insurancePolicy
											.setPolicyStatus(PolicyStatus.PROPOSAL_FAIL
													.getValue());
									insurancePolicy
											.setPolicyStatusName(PolicyStatus.PROPOSAL_FAIL
													.getDataName());
									insurancePolicy
											.setPolicyStatusDesc(PolicyStatus.PROPOSAL_FAIL
													.getDataName());
									insurancePolicy.setReason("�˱�ʧ�ܣ��˱����ڣ�"
											+ new Date().toString() + "��ʧ��ԭ�򣺣�"
											+ resultMap.get("desc") + "��");
									result = "����д����Ϣ�����ϸò�ƷͶ��������������޸�";
									if (resultMap.get("desc") != null
											&& !resultMap.get("desc").equals("")) {
										result += "��" + resultMap.get("desc") + "��";
									}
								}
							} else {
								saleAction.createOrderOfPolicy(insurancePolicy, "0");
								result = "ϵͳ��æ�����Ժ����ԣ�";
							}
						} else {
							jsonObject.put("proposalContNo",
									lcCont.getProposalContNo());
							insurancePolicy.setApplicationNumber(lcCont
									.getProposalContNo());
							insurancePolicy
									.setPolicyStatus(PolicyStatus.PROPOSAL_FAIL
											.getValue());
							insurancePolicy
									.setPolicyStatusName(PolicyStatus.PROPOSAL_FAIL
											.getDataName());
							insurancePolicy
									.setPolicyStatusDesc(PolicyStatus.PROPOSAL_FAIL
											.getDataName());
							insurancePolicy.setReason("�˱�ʧ�ܣ��˱����ڣ�"
									+ new Date().toString() + "��ʧ��ԭ�򣺣�"
									+ resultMap.get("desc") + "��");
							result = "����д����Ϣ�����ϸò�ƷͶ��������������޸�";
							if (resultMap.get("desc") != null
									&& !resultMap.get("desc").equals("")) {
								result += "��" + resultMap.get("desc") + "��";
							}
						}
						if (result.equals("success") || updateFlag) {
							insurancePolicy.setOrderForm(
							saleAction
									.createOrderOfPolicy(insurancePolicy, "1"));
							insurancePolicyService
									.updateInsurancePolicy(insurancePolicy);
						}
					} else {
						saleAction.createOrderOfPolicy(insurancePolicy, "0");
						result = "ϵͳ��æ�����Ժ����ԣ�";
					}
				} catch (SaleException e) {
					result = "ϵͳ��æ�����Ժ����ԣ���" + e.showMessage() + "��";
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("�˱������������쳣���û��ţ�" + geUserPersonal.getUserID()
							+ "���쳣��Ϣ��" + e.getMessage());
					result = "ϵͳ��æ�����Ժ����ԣ�";
				}
			}
			
			if (result.equals("success")) {
				jsonObject.put("isSucc", String.valueOf(true));
			} else {
				jsonObject.put("isSucc", String.valueOf(false));
				jsonObject.put("message", result);
			}
			jsonObject.put("regFlag", au.getRegFlag());
			jsonObject.put("quoteNo", quoteNo);
			if ("1".equals(insureFlowDto.getMoreBuyFlag())) {
				jsonObject.put("proposalSID",
						insureFlowDto.getMoreBuyProposalSID());
			} else {
 				jsonObject.put("proposalSID", insureFlowDto.getProposalSID());
			}
			if (insurancePolicy.getOrderForm() != null) {
				jsonObject.put("orderId", insurancePolicy.getOrderForm().getSerialNo());
			}
			
			getResponse().getWriter().write(jsonObject.toString());
			getResponse().getWriter().flush();
//			/*
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("isSucc", String.valueOf(true));
//			map.put("message", "xxx����");
//			JSONObject jsonObject = new JSONObject(map);
//			getResponse().getWriter().write(jsonObject.toString());
//			getResponse().getWriter().flush();
//			 */
		} catch (Exception e) {
			log.error("�ֻ��˼���ͻ���Ϣ����", e.fillInStackTrace());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isSucc", String.valueOf(false));
			map.put("message", "�ֻ��˼���ͻ���Ϣ����");
			try {
				JSONObject jsonObject = new JSONObject(map);
				getResponse().getWriter().write(jsonObject.toString());
				getResponse().getWriter().flush();
			} catch (IOException e2) {
				log.error("�ֻ��˼���ͻ���Ϣ����", e2.fillInStackTrace());
			}
		}
	}
	
	public void loonConfirmCustomInfo() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isSucc", String.valueOf(true));
			map.put("message", "");
			JSONObject jsonObject = new JSONObject(map);
			getResponse().getWriter().write(jsonObject.toString());
			getResponse().getWriter().flush();
		} catch (Exception e) {
			log.error("�ֻ��������ȷ��Ͷ��ҳ�����", e.fillInStackTrace());
		}
	}
	
	public String provincesCode() {
		return SUCCESS;
	}
	
	public String citiesCode() {
		return SUCCESS;
	}
	
	public String countiesCode() {
		return SUCCESS;
	}
	
	public String banksCode() {
		return SUCCESS;
	}
	
	public void loonCheckPayPayfees() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("payMessage", "");
			JSONObject jsonObject = new JSONObject(map);
			getResponse().getWriter().write(jsonObject.toString());
			getResponse().getWriter().flush();
		} catch (Exception e) {
			log.error("�ֻ��������ȷ��Ͷ��ҳ�����", e.fillInStackTrace());
		}
	}
	
}

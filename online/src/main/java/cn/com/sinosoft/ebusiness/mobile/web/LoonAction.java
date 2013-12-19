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
	
	/**功能开关*/
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
	// 试算号
	private String quoteNo;
	// 核保号
	private String proposalContNo = "";
	// 投保号
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
	 * 进入懒人理财宝
	 * @return
	 */
	public String loonIndex(){
		return SUCCESS;
	}
	
	/**
	 * 进入懒人理财宝-投保须知
	 * @return
	 */
	public String loonDeclares(){
		return SUCCESS;
	}
	
	/**
	 * 进入懒人理财宝-产品详情
	 * @return
	 */
	public String loonProductInfo(){
		return SUCCESS;
	}
	
	/**
	 * 进入懒人理财宝-售后服务
	 * @return
	 */
	public String loonServices(){
		return SUCCESS;
	}
	
	/**
	 * 进入懒人理财宝-常见问题
	 * @return
	 */
	public String loonQuestions(){
		return SUCCESS;
	}
	
	/**
	 * 进入懒人理财宝-填写客户信息
	 * @return
	 */
	public String loonCustomInfoInput() {
		return SUCCESS;
	}
	
	/**
	 * 进入懒人理财宝-信息确认
	 * @return
	 */
	public String loonConfirm() {
		getRequest().setAttribute("total", String.valueOf(Integer.parseInt(number) * 1000) + ".00");
		return SUCCESS;
	}
	
	/**
	 * 进入懒人理财宝-订单支付
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
				// 设置代理
				SocketAddress addr = new InetSocketAddress("10.20.3.168", 3128);
				Proxy typeProxy = new Proxy(Proxy.Type.HTTP, addr);
				// 打开和URL之间的连接
				conn = realUrl.openConnection(typeProxy);
			} else {
				// 打开和URL之间的连接
				conn = realUrl.openConnection();
			}
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 建立实际的连接
			conn.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
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
	 * 支付宝支付成功之后，同步回调函数
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
	 * 支付宝支付成功之后，异步回调函数
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
				log.info("投保单号：" 
						+ orderForm.getInsurancePolicy().getApplicationNumber() 
						+ "，已经支付过！");
				jsonObject.put("returnFlag", "true");
				jsonObject.put("message", "投保单号：" 
						+ orderForm.getInsurancePolicy().getApplicationNumber() 
						+ "，已经支付过！");
			} else {
				jsonObject.put("returnFlag", "false");
				jsonObject.put("message", "投保单号：" 
						+ orderForm.getInsurancePolicy().getApplicationNumber() 
						+ "，还未进行过支付！");
			}
			getResponse().getWriter().write(jsonObject.toString());
			getResponse().getWriter().flush();
		} catch (Exception e) {
			log.error("检查是否支付错误！", e.fillInStackTrace());
		}
	}
	
	/**
	 * 去支付宝支付
	 * @return
	 */
	public String toAliPay() {
		String account = getRequest().getParameter("paymentAccount");
		String bankType = getRequest().getParameter("bankType");
		String bankProvince = getRequest().getParameter("bankProvince");
		String bankCity = getRequest().getParameter("bankCity");
		OrderForm orderForm = orderFormService.getOrderFormBySerialNo(orderId);
		if ("1".equals(String.valueOf(orderForm.getPayStatus()))) {
			log.info("投保单号：" 
					+ orderForm.getInsurancePolicy().getApplicationNumber() 
					+ "，已经支付过！");
			getRequest().setAttribute("error", "投保单号：" 
					+ orderForm.getInsurancePolicy().getApplicationNumber() 
					+ "，已经支付过！");
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
		paymentAccount.setBankAcctType(4); //借记卡
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
			//付款金额 必填
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
			String req_data = AlipayMobileCore.getTokenReqData("信泰人寿懒人理财",out_trade_no,total_fee);
			sParaTemp.put("req_data", req_data);
			//生成签名结果
	        String mysign = AlipayCore.buildMysignForMobile(sParaTemp);
	        //签名结果与签名方式加入请求提交参数组中
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
	        	//支付信息入库
	        	orderFormService.addOrderForm(orderForm);
				
				PrintWriter out = getResponse().getWriter();
				getResponse().setCharacterEncoding("utf-8");
				getResponse().setContentType("text/html;charset=UTF-8");
				out.print(sHtmlText);
				out.close();
	        }
		} catch (BusinessException e) {
			//处理支付业务异常
			log.warn("缴费页-确认支付", e);
			getRequest().setAttribute("error", "缴费页-确认支付错误！");
			return "error";
		} catch (Exception e) {
			log.error("缴费页-确认支付", e);
			getRequest().setAttribute("error", "缴费页-确认支付错误！");
			return "error";
		}
		
//		Map<String, String> map = paymentService.alipay(orderForm);
//		orderFormService.addOrderForm(orderForm);
		System.out.println("=============== 提交支付宝 ===============");
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
				jsonObject.put("message", "您已经是信泰保险的用户，请登陆信泰官网购买保单！");
				getResponse().getWriter().write(jsonObject.toString());
				getResponse().getWriter().flush();
				return;
			} else 
			if ("1".equals(au.getRegFlag())) {
				// 试算号
				quoteNo = "";
				// 核保号
				proposalContNo = "";
				// 投保号主键
				proposalSID = "";
				List<String> params = new ArrayList<String>();
				// 短信发送人
				String sender = "9005";
				// 短信内容
				String msgComment = "系统已为您自动注册，密码为" + au.getPlaintextPassword() + "。";
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
			quoteMain.setProductName("信泰懒人理财宝");
			quoteMain.setStep(1);
			Date now = new Date();
			quoteMain.setInputDate(now);
			quoteMain.setInceptionDate(DateUtils.addDays(now, 1));
			/** 属性份数 */
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
			quoteLiability.setProductName("信泰懒人理财宝");
			quoteLiability.setInceptionDate(quoteMain.getInceptionDate());
			quoteLiability.setPremium(quoteMain.getPremium());
			quoteLiability.setGrossPremium(BigDecimal.ZERO);
			quoteLiability.setBenefitPeriod(80);
			quoteLiability.setBenefitPeriodType(20);
			quoteLiability.setUnitCount(1);
			quoteLiability.setLiabilityCode("00115500");
			quoteLiability.setCoreCode("00115500");
			quoteLiability.setMainRiskCode("00115500");
			quoteLiability.setLiabilityName("信泰宝利来两全保险（万能型）");
			quoteLiability.setSubRiskFlag(1);
			quoteLiability.setBonusGetMode(5);
			quoteLiability.setLiabilityOrder(1);
			quoteLiability.setGrossInsuredAmount(BigDecimal.ZERO);
			quoteLiability.setPaymentDurationMode(quoteMain.getPaymentDurationMode());
			
			/** 属性投保人 */
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
			
			/** 属性被保险人 */
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
			//校验投保人手机号
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
			
			//校验被保险人身份证
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
			//把暂存对象里的值进行一些处理
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
			
			// 试算单入库
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
					logger.error("试算单入库出现异常，用户号：" + geUserPersonal.getUserID()
							+ "，异常堆栈信息：" + e.getMessage());
					result = "系统繁忙，请稍后再试！";
				}
				if (!bl) {
					result = "系统繁忙，请稍后再试！";
				}
			}
			
//			jsonObject.put("quoteNo", quoteNo);
			
			
			InsurancePolicy insurancePolicy = null;
			// 入库，先查询，如果没有则直接入库，有则删除后再重新入库
			if (StringUtils.isBlank(result)) {
				try {
					if (StringUtils.isBlank(proposalSID)) {
						insureFlowDto.getQuoteMain().getProposalSID();// 投保单序号
					}
					if (StringUtils.isNotBlank(proposalSID)) {
						insurancePolicy = insurancePolicyService
								.findInsurancePolicyBySerialNo(proposalSID);
					}
					if (insurancePolicy != null) {
						insurancePolicyService
								.deleteInsurancePolicy(insurancePolicy);
					}
					// 集团业务来源（WEB）
					insureFlowDto.getQuoteMain()
							.setBusinessSource("WEB_PERSON");
					// 集团渠道代码（W——网销）
					insureFlowDto.getQuoteMain().setGroupChannel("W");
					insureFlowDto.getQuoteMain().setSellType("20");
					insurancePolicy = saleService
							.createInsurancePolicy(insureFlowDto);
					insurancePolicy.setQuoteNo(quoteNo);
					insureFlowDto.getQuoteMain().setProposalSID(
							insurancePolicy.getSerialNo());
					insureFlowDto.setProposalSID(insurancePolicy.getSerialNo());
					// 加购
					if ("1".equals(insureFlowDto.getMoreBuyFlag())) {
						// 添加加购关联标记
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
					result = "系统繁忙，请稍后再试！（" + e.showMessage() + "）";
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("投保单入库出现异常，用户号：" + geUserPersonal.getUserID()
							+ "，异常信息：" + e.getMessage());
					result = "系统繁忙，请稍后再试！";
				}
			}
			
			if (StringUtils.isBlank(result)) {
				try {
					// 核保
					insurancePolicy.setTransIdentify(TransType.ST000022
							.getCoreValue());
					Map<String, Object> resultMap = underwritingService
							.underwriting(insureFlowDto, insurancePolicy,
									geUserPersonal);
					
					boolean updateFlag = false;
					// 更新库
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
											insurancePolicy.setReason("核保成功，核保日期："
													+ new Date().toString());
											result = "success";
										}
									} else {
										result = "系统繁忙，请稍后再试！";
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
									insurancePolicy.setReason("核保失败，核保日期："
											+ new Date().toString() + "，失败原因：（"
											+ resultMap.get("desc") + "）");
									result = "您填写的信息不符合该产品投保条件，请进行修改";
									if (resultMap.get("desc") != null
											&& !resultMap.get("desc").equals("")) {
										result += "（" + resultMap.get("desc") + "）";
									}
								}
							} else {
								saleAction.createOrderOfPolicy(insurancePolicy, "0");
								result = "系统繁忙，请稍后再试！";
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
							insurancePolicy.setReason("核保失败，核保日期："
									+ new Date().toString() + "，失败原因：（"
									+ resultMap.get("desc") + "）");
							result = "您填写的信息不符合该产品投保条件，请进行修改";
							if (resultMap.get("desc") != null
									&& !resultMap.get("desc").equals("")) {
								result += "（" + resultMap.get("desc") + "）";
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
						result = "系统繁忙，请稍后再试！";
					}
				} catch (SaleException e) {
					result = "系统繁忙，请稍后再试！（" + e.showMessage() + "）";
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("核保结果处理出现异常，用户号：" + geUserPersonal.getUserID()
							+ "，异常信息：" + e.getMessage());
					result = "系统繁忙，请稍后再试！";
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
//			map.put("message", "xxx错误！");
//			JSONObject jsonObject = new JSONObject(map);
//			getResponse().getWriter().write(jsonObject.toString());
//			getResponse().getWriter().flush();
//			 */
		} catch (Exception e) {
			log.error("手机端检验客户信息错误！", e.fillInStackTrace());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isSucc", String.valueOf(false));
			map.put("message", "手机端检验客户信息错误！");
			try {
				JSONObject jsonObject = new JSONObject(map);
				getResponse().getWriter().write(jsonObject.toString());
				getResponse().getWriter().flush();
			} catch (IOException e2) {
				log.error("手机端检验客户信息错误！", e2.fillInStackTrace());
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
			log.error("手机懒人理财确认投保页面错误！", e.fillInStackTrace());
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
			log.error("手机懒人理财确认投保页面错误！", e.fillInStackTrace());
		}
	}
	
}

package cn.com.sinosoft.ebusiness.payment.service.spring;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import cn.com.sinosoft.businessModule.bankAccount.domain.PaymentAccount;
import cn.com.sinosoft.businessModule.bankAccount.service.facade.PaymentAccountService;
import cn.com.sinosoft.businessModule.bindPolicy.domain.BindPolicy;
import cn.com.sinosoft.businessModule.bindPolicy.service.facade.BindPolicyService;
import cn.com.sinosoft.businessModule.enums.dictionary.OrderStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.PayStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.PolicyStatus;
import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.order.service.facade.OrderFormService;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyService;
import cn.com.sinosoft.ebusiness.ali.config.AlipayConfig;
import cn.com.sinosoft.ebusiness.ali.util.AlipayNotify;
import cn.com.sinosoft.ebusiness.ali.util.AlipaySubmit;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.SendEmailService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeFunctionSwitchService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeSmsConfigService;
import cn.com.sinosoft.ebusiness.payment.service.facade.PaymentService;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteMainService;
import cn.com.sinosoft.ebusiness.sale.service.facade.SmsSendService;
import cn.com.sinosoft.ebusiness.sale.service.facade.UnderwritingService;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.util.io.FilePathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;
import cn.com.sinosoft.util.string.StringUtils;
import cn.com.sinosoft.util.time.DateUtils;

public class PaymentServiceSpringImpl implements PaymentService {

	private static Logger log = LoggerFactory.getLogger(PaymentServiceSpringImpl.class);
	
	@Autowired
	private OrderFormService orderFormService;
	
	@Autowired
	private PaymentAccountService paymentAccountService;
	
	@Autowired
	private UnderwritingService underwritingService;
	
	@Autowired
	private InsurancePolicyService insurancePolicyService;
	
	@Autowired
	private QuoteMainService quoteMainService;
	
	/**功能开关*/
	@Autowired
	private GeFunctionSwitchService geFunctionSwitchService;
	
	@Autowired
	private SmsSendService smsSendService;
	
	@Autowired
	private GeSmsConfigService geSmsConfigService;
	
	@Autowired
	private BindPolicyService bindPolicyService;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private SendEmailService sendEmailService;
	
	public Map<String, String> alipay(OrderForm orderForm) {
		Map<String, String> sParaTemp = null;
		try {
			//支付类型
			String payment_type = "1";
			PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
			Properties properties = PropertyFileUtils.getProperties();
			String sinatayUrl = properties.getProperty("sinatayUrl");
			//必填，不能修改
			//服务器异步通知页面路径
//			String notify_url = "http://127.0.0.1:80809/online/web/payment/alipay/notify_url.jsp";
			//需http://格式的完整路径，不能加?id=123这类自定义参数
//			String notify_url = sinatayUrl + "/web/payment/alipay/notify_url.jsp";
			//用户支付成功之后，手动关闭支付宝的提示页面，无法进行页面跳转，此时采用支付宝异步处理的方式修改商户订单信息
			String notify_url = sinatayUrl + "/payment/notifySuccess.do";
//			notify_url = "";
			
			//页面跳转同步通知页面路径，需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
//			String return_url = "http://www.xxx.com/create_direct_pay_by_user-JAVA-UTF-8/return_url.jsp";
			String return_url = sinatayUrl + "/payment/alipaySuccess.do";
			
			//卖家支付宝帐户(收款方帐号) 必填
			String seller_email = properties.getProperty("sellerAccount");
			
			//商户订单号,商户网站订单系统中唯一订单号，必填
			String out_trade_no = orderForm.getOrderSerialNumber();
			
			//订单名称 必填
			String subject = "sinatay"; 
			
			//付款金额 必填
			String total_fee = orderForm.getOrderAmount() + "";
			
			//订单描述
			String body = "";
			
			//商品展示地址，需以http://开头的完整路径，例如：http://www.xxx.com/myorder.html
			String show_url = "http://item.taobao.com/item.htm?spm=686.1000925.1000774.13.6tiZWC&id=14713575154";
			
			//防钓鱼时间戳， 若要使用请调用类文件submit中的query_timestamp函数
			String anti_phishing_key = AlipaySubmit.query_timestamp();
			
			//客户端的IP地址
			String exter_invoke_ip = InetAddress.getLocalHost().getHostAddress();
			//非局域网的外网IP地址，如：221.0.0.1
			
			//把请求参数打包成数组
			sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "create_direct_pay_by_user");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("payment_type", payment_type);
			sParaTemp.put("notify_url", notify_url);
			sParaTemp.put("return_url", return_url);
			sParaTemp.put("seller_email", seller_email);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("subject", subject);
			sParaTemp.put("total_fee", total_fee);
			sParaTemp.put("body", body);
			sParaTemp.put("show_url", show_url);
			sParaTemp.put("anti_phishing_key", anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println(sParaTemp);
		return sParaTemp;
	}
	
	public Map<String, Object> alipayMobileReturnMessage(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		OrderForm orderForm = null;
		// 获取支付宝GET过来反馈信息
		try {
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("GBK"), "utf-8");
				params.put(name, valueStr);
			}
			
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = request.getParameter("out_trade_no");
			// 交易状态
			String trade_status = request.getParameter("trade_status");
			
			String result = request.getParameter("result");
			if ("success".equals(result)) {
				trade_status = "TRADE_SUCCESS";
			} else {
				trade_status = "TRADE_FINISHED";
			}
			
			orderForm = orderFormService.findOrderFormByOrderFormSerialNumber(out_trade_no);
			//如果订单状态为已支付，则直接返回
			if (orderForm == null) {
				map.put("flag", false);
				map.put("orderForm", null);
				return map;
			}
			if (!PayStatus.UNPAID.getValue().equals(orderForm.getPayStatus())) {
				map.put("flag", false);
				map.put("orderForm", orderForm);
				orderForm.setPaymentMessage("订单状态不为'未支付'，支付失败.");
				return map;
			}
			// 计算得出通知验证结果
			boolean verify_result = false;
			try {
				verify_result = AlipayNotify.verifyMobile(params);
			} catch (Exception e) {
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					orderForm.setPaymentMessage("支付宝支付成功，安全验证失败.支付宝返回验证错误信息："+e.getMessage());
				} else {
					orderForm.setPaymentMessage("支付宝支付失败，安全验证失败.支付宝返回验证错误信息："+e.getMessage());
				}
				log.error(e.getMessage());
			}
			saveMobilePaymentAccount(request, orderForm);
			if (verify_result) {// 验证成功
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					orderForm.setPaymentMessage("支付宝支付成功,验证成功.");
					final InsurancePolicy insurancePolidy = orderForm.getInsurancePolicy();
					orderForm.setOrderStatus(OrderStatus.PAYMENTSUCCESS.getValue());
					orderForm.setOrderStatusName(OrderStatus.PAYMENTSUCCESS.getDataName());
					orderForm.setPayStatus(PayStatus.PAYMENTSUCCESS.getValue());
					orderForm.setPayStatusName(PayStatus.PAYMENTSUCCESS.getDataName());
					
					
					/**支付成功之后，进行核保校验**/
					taskExecutor.execute(new Runnable() {
						@Override
						public void run() {
							insured(insurancePolidy);
						}
					});
					map.put("flag", true);
					map.put("orderForm", orderForm);
				} else {
					orderForm.setPaymentMessage("支付宝支付失败,验证成功.");
					orderForm.setOrderStatus(OrderStatus.PAYMENTFAILURE.getValue());
					orderForm.setOrderStatusName(OrderStatus.PAYMENTFAILURE.getDataName());
					orderForm.setPayStatus(PayStatus.PAYMENTFAILURE.getValue());
					orderForm.setPayStatusName(PayStatus.PAYMENTFAILURE.getDataName());
					
					map.put("flag", false);
					map.put("orderForm", orderForm);
				}
			} else {//验证失败，返回给支付宝“fail”，十分钟之后支付宝会再次发起验证
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					if (StringUtils.isNotEmpty(orderForm.getPaymentMessage() )) {
						orderForm.setPaymentMessage(orderForm.getPaymentMessage()  + ";支付宝支付成功，验证失败，需人工处理.");
					} else {
						orderForm.setPaymentMessage("支付宝支付成功，验证失败，需人工处理.");
					}
				} else {
					if (StringUtils.isNotEmpty(orderForm.getPaymentMessage() )) {
						orderForm.setPaymentMessage(orderForm.getPaymentMessage()  + ";支付宝支付失败，验证失败.");
					} else {
						orderForm.setPaymentMessage("支付宝支付失败，验证失败.");
					}
				}
				
				orderForm.setOrderStatus(OrderStatus.PAYMENTFAILURE.getValue());
				orderForm.setOrderStatusName(OrderStatus.PAYMENTFAILURE.getDataName());
				orderForm.setPayStatus(PayStatus.PAYMENTFAILURE.getValue());
				orderForm.setPayStatusName(PayStatus.PAYMENTFAILURE.getDataName());
				
				map.put("flag", false);
				map.put("orderForm", orderForm);
			}
			orderForm.setUpdateTime(new Date());
			orderFormService.updateOrderForm(orderForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	private void saveMobilePaymentAccount(HttpServletRequest request, OrderForm orderForm) {
		PaymentAccount account = orderForm.getInsurancePolicy().getPaymentAccount();
		if (account == null) {
			account = new PaymentAccount();
		}
		
		//买家支付宝帐号
		String buyer_email = request.getParameter("buyer_email");
		//买家支付宝账户号
		String buyer_id = request.getParameter("buyer_id");
		//接口名称
		String exterface = request.getParameter("exterface");
		//成功表示 ‘T’ 未成功
		String is_success = request.getParameter("is_success");
		//通知校验ID
		String notify_id = request.getParameter("notify_id");
		//通知时间
		String notify_time = request.getParameter("notify_time");
		//通知类型
		String notify_type = request.getParameter("notify_type");
		//订单号（唯一）
		String out_trade_no = request.getParameter("out_trade_no");
		//支付类型，请求时传入的支付类型，原值返回
		String payment_type = request.getParameter("payment_type");
		//卖家支付宝帐号
		String seller_email = request.getParameter("seller_email");
		//卖家支付宝账户号
		String seller_id = request.getParameter("seller_id");
		//商品名称
		String subject = request.getParameter("subject");
		//交易金额
		String total_fee = request.getParameter("total_fee");
		//支付宝交易号
		String trade_no = request.getParameter("trade_no");
		//交易状态
		String trade_status = request.getParameter("trade_status");
		//签名
		String sign = request.getParameter("sign");
		//签名方式 DSA/RSA/MD5
		String sign_type =request.getParameter("sign_type");
		//body 商品描述
		String body = request.getParameter("body");
		
		try {
			if (notify_time == null) {
				notify_time = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
			}
			orderForm.setPayTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(notify_time));
			account.setAccountNumber(orderForm.getInsurancePolicy().getInsuranceApplicant().getEmail());
			account.setPayAmount(orderForm.getInsurancePolicy().getGrossPremium());
			account.setPayTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(notify_time));
			account.setTradeNo(trade_no);
			account.setTradeStatus(trade_status);
			account.setSign(sign);
			account.setSignType(sign_type);
			account.setBody(body);
			account.setSubject(subject);
			account.setSellerId(seller_id);
			account.setBuyerId(buyer_id);
			account.setExterface(exterface);
			account.setIsSuccess(is_success);
			account.setNotifyId(notify_id);
			account.setNotifyTime(notify_time);
			account.setNotifyType(notify_type);
			account.setOutTradeNo(out_trade_no);
			account.setPaymentType(payment_type);
//			account.setSellerId(seller_email);
			
//			account.setInsurancePolicy(orderForm.getInsurancePolicy());
			account.addInsurancePolicy(orderForm.getInsurancePolicy());
			
			paymentAccountService.addPaymentAccount(account);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Object> alipayReturnMessage(HttpServletRequest request) {
		log.error("The alipay payment, payment is successful, began to return information");
		Map<String, Object> map = new HashMap<String, Object>();
		OrderForm orderForm = null;
		// 获取支付宝GET过来反馈信息
		try {
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("GBK"), "utf-8");
				params.put(name, valueStr);
			}
			
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = request.getParameter("out_trade_no");
			log.error("ali pay success.out_trade_no : " + out_trade_no);
			// 交易状态
			String trade_status = request.getParameter("trade_status");
			
			orderForm = orderFormService.findOrderFormByOrderFormSerialNumber(out_trade_no);
			//如果订单状态为已支付，则直接返回
			if (orderForm == null) {
				map.put("flag", false);
				map.put("orderForm", null);
				return map;
			}
			if (!PayStatus.UNPAID.getValue().equals(orderForm.getPayStatus())) {
				map.put("flag", false);
				map.put("orderForm", orderForm);
				orderForm.setPaymentMessage("订单状态不为'未支付'，支付失败.");
				return map;
			}
			// 计算得出通知验证结果
			boolean verify_result = false;
			try {
				verify_result = AlipayNotify.verify(params);
			} catch (Exception e) {
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					orderForm.setPaymentMessage("支付宝支付成功，安全验证失败.支付宝返回验证错误信息："+e.getMessage());
				} else {
					orderForm.setPaymentMessage("支付宝支付失败，安全验证失败.支付宝返回验证错误信息："+e.getMessage());
				}
				log.error(e.getMessage());
			}
			savePaymentAccount(request, orderForm);
			if (verify_result) {// 验证成功
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					orderForm.setPaymentMessage("支付宝支付成功,验证成功.");
					final InsurancePolicy insurancePolidy = orderForm.getInsurancePolicy();
					orderForm.setOrderStatus(OrderStatus.PAYMENTSUCCESS.getValue());
					orderForm.setOrderStatusName(OrderStatus.PAYMENTSUCCESS.getDataName());
					orderForm.setPayStatus(PayStatus.PAYMENTSUCCESS.getValue());
					orderForm.setPayStatusName(PayStatus.PAYMENTSUCCESS.getDataName());
					
					log.error("ali pay validate success. start to insured.");
					/**支付成功之后，进行核保校验**/
					taskExecutor.execute(new Runnable() {
						@Override
						public void run() {
							log.error("start insured by task executor.runing....");
							insured(insurancePolidy);
							log.error("insured end.");
						}
					});
					map.put("flag", true);
					map.put("orderForm", orderForm);
				} else {
					orderForm.setPaymentMessage("支付宝支付失败,验证成功.");
					orderForm.setOrderStatus(OrderStatus.PAYMENTFAILURE.getValue());
					orderForm.setOrderStatusName(OrderStatus.PAYMENTFAILURE.getDataName());
					orderForm.setPayStatus(PayStatus.PAYMENTFAILURE.getValue());
					orderForm.setPayStatusName(PayStatus.PAYMENTFAILURE.getDataName());
					
					map.put("flag", false);
					map.put("orderForm", orderForm);
					log.error("ali pay validate failed." + orderForm.getPaymentMessage());
				}
			} else {//验证失败，返回给支付宝“fail”，十分钟之后支付宝会再次发起验证
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					if (StringUtils.isNotEmpty(orderForm.getPaymentMessage() )) {
						orderForm.setPaymentMessage(orderForm.getPaymentMessage()  + ";支付宝支付成功，验证失败，需人工处理.");
					} else {
						orderForm.setPaymentMessage("支付宝支付成功，验证失败，需人工处理.");
					}
				} else {
					if (StringUtils.isNotEmpty(orderForm.getPaymentMessage() )) {
						orderForm.setPaymentMessage(orderForm.getPaymentMessage()  + ";支付宝支付失败，验证失败.");
					} else {
						orderForm.setPaymentMessage("支付宝支付失败，验证失败.");
					}
				}
				
				orderForm.setOrderStatus(OrderStatus.PAYMENTFAILURE.getValue());
				orderForm.setOrderStatusName(OrderStatus.PAYMENTFAILURE.getDataName());
				orderForm.setPayStatus(PayStatus.PAYMENTFAILURE.getValue());
				orderForm.setPayStatusName(PayStatus.PAYMENTFAILURE.getDataName());
				
				map.put("flag", false);
				map.put("orderForm", orderForm);
				log.error("ali pay validate failed." + orderForm.getPaymentMessage());
			}
			orderForm.setUpdateTime(new Date());
			orderFormService.updateOrderForm(orderForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	private void savePaymentAccount(HttpServletRequest request, OrderForm orderForm) {
		PaymentAccount account = orderForm.getInsurancePolicy().getPaymentAccount();
		if (account == null) {
			account = new PaymentAccount();
		}
		
		//买家支付宝帐号
		String buyer_email = request.getParameter("buyer_email");
		//买家支付宝账户号
		String buyer_id = request.getParameter("buyer_id");
		//接口名称
		String exterface = request.getParameter("exterface");
		//成功表示 ‘T’ 未成功
		String is_success = request.getParameter("is_success");
		//通知校验ID
		String notify_id = request.getParameter("notify_id");
		//通知时间
		String notify_time = request.getParameter("notify_time");
		//通知类型
		String notify_type = request.getParameter("notify_type");
		//订单号（唯一）
		String out_trade_no = request.getParameter("out_trade_no");
		//支付类型，请求时传入的支付类型，原值返回
		String payment_type = request.getParameter("payment_type");
		//卖家支付宝帐号
		String seller_email = request.getParameter("seller_email");
		//卖家支付宝账户号
		String seller_id = request.getParameter("seller_id");
		//商品名称
		String subject = request.getParameter("subject");
		//交易金额
		String total_fee = request.getParameter("total_fee");
		//支付宝交易号
		String trade_no = request.getParameter("trade_no");
		//交易状态
		String trade_status = request.getParameter("trade_status");
		//签名
		String sign = request.getParameter("sign");
		//签名方式 DSA/RSA/MD5
		String sign_type =request.getParameter("sign_type");
		//body 商品描述
		String body = request.getParameter("body");
		
		try {
			orderForm.setPayTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(notify_time));
			account.setAccountNumber(buyer_email);
			account.setPayAmount(new BigDecimal(total_fee));
			account.setPayTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(notify_time));
			account.setTradeNo(trade_no);
			account.setTradeStatus(trade_status);
			account.setSign(sign);
			account.setSignType(sign_type);
			account.setBody(body);
			account.setSubject(subject);
			account.setSellerId(seller_id);
			account.setBuyerId(buyer_id);
			account.setExterface(exterface);
			account.setIsSuccess(is_success);
			account.setNotifyId(notify_id);
			account.setNotifyTime(notify_time);
			account.setNotifyType(notify_type);
			account.setOutTradeNo(out_trade_no);
			account.setPaymentType(payment_type);
			account.setSellerId(seller_email);
			
//			account.setInsurancePolicy(orderForm.getInsurancePolicy());
			account.addInsurancePolicy(orderForm.getInsurancePolicy());
			
			paymentAccountService.addPaymentAccount(account);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**支付返回承保*/
	public void insured(InsurancePolicy insurancePolicy){
		PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
		Properties properties = PropertyFileUtils.getProperties();
		String result = "";
		log.error("start to insured.");
		Map<String, Object> resultMap = underwritingService.insured(insurancePolicy);
		log.error("insured end.result :" + resultMap);
		//更新库
		if (resultMap != null) {
			if (resultMap.get("flag").equals("0")) {
				LCCont lcCont = resultMap.get("LCCont")==null?null:(LCCont)resultMap.get("LCCont");
				if(lcCont != null){
					if(insurancePolicy.getApplicationNumber().equals(lcCont.getProposalContNo())){
						QuoteMain quoteMain = quoteMainService.findQuoteMainByPk(insurancePolicy.getQuoteNo());
						insurancePolicy.setPolicySerialNumber(lcCont.getContNo());
						if(quoteMainService.updateQuoteMain(quoteMain)) {
							insurancePolicy.setPolicyStatus(PolicyStatus.POLICY_SUCC.getValue());
							insurancePolicy.setPrecheckDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
							insurancePolicy.setReason("承保成功，承保日期：" + new Date().toString());
							result = "success";
							log.error("insured success."+ insurancePolicy.getReason());
						}
					}
				} else {
					log.error("insured failed.reason:resultMap lccont is null.");
					result = "系统繁忙，请稍后再试！";
				}
			} else {
				insurancePolicy.setReason("承保失败，承保日期：" + new Date().toString() + "，失败原因：（" + resultMap.get("desc") + "）");
				log.error("insured failed.reason:" + insurancePolicy.getReason());
			}
			if (result.equals("success")) {
				log.error("insured success.start update policy serial number.");
				insurancePolicyService.updatePolicySerialNumber(insurancePolicy);
				BindPolicy bindPolicy = new BindPolicy();
				bindPolicy.setCustomerId(insurancePolicy.getPersonalUserSerialNo());
				bindPolicy.setPolicySerialNumber(insurancePolicy.getPolicySerialNumber());
				bindPolicyService.addBindPolicy(bindPolicy);
				if (geFunctionSwitchService.isSwitchOpen("sendInsuredSMS")) { //根据功能开关判断是否发送承保短信
					List<String> params = new ArrayList<String>();
					String mobilePhoneNumber = insurancePolicy.getInsuranceApplicant()==null?"":insurancePolicy.getInsuranceApplicant().getMobilePhoneNumber();
					if(StringUtils.isNotBlank(mobilePhoneNumber)){
						params.add(insurancePolicy.getProductName());
						params.add(insurancePolicy.getPolicySerialNumber());
						
						log.error("insured success.start message to customer.phone number is " + mobilePhoneNumber);
						smsSendService.smsSend(true, "4", params, "1", mobilePhoneNumber, "", "9005", null);
					}
				}
				if (geFunctionSwitchService.isSwitchOpen("sendInsuredEmail")) { //根据功能开关判断是否发送承保邮件
					String appEmail = insurancePolicy.getInsuranceApplicant()==null?"":insurancePolicy.getInsuranceApplicant().getEmail();
					int appGender = insurancePolicy.getInsuranceApplicant()==null?0:insurancePolicy.getInsuranceApplicant().getGender();
					if (StringUtils.isNotBlank(appEmail)) {
						Map<String,String> map = new HashMap<String, String>();
						map.put("email", appEmail);
						map.put("fullName", insurancePolicy.getInsuranceApplicant()==null?"":insurancePolicy.getInsuranceApplicant().getFullName());
						map.put("date", DateUtils.formatDate(new Date(),DateUtils.ZHCN_DATATIMEF_STR_YYYYMMDDHHMM));
						String insName = insurancePolicy.getInsuranceApplicant()==null?"":insurancePolicy.getInsuranceApplicant().getFullName();
						if(insurancePolicy.getInsureds() != null && !insurancePolicy.getInsureds().isEmpty() && insurancePolicy.getInsureds().get(0) != null){
							insName = insurancePolicy.getInsureds().get(0).getFullName();
						}
						map.put("insFullName", insName);
						map.put("genderTag", appGender == 0 ? "先生" : "女士");
						map.put("productName", insurancePolicy.getProductName());
						map.put("policyNo", insurancePolicy.getPolicySerialNumber());
						map.put("inceptionDate", DateUtils.formatDate(insurancePolicy.getInceptionDate(), DateUtils.ZHCN_DATEFORMAT_STR));
						map.put("count", insurancePolicy.getUnitCount()==null ? "1" : insurancePolicy.getUnitCount().toString());
						map.put("premium", insurancePolicy.getPremium()==null ? "0" : insurancePolicy.getPremium().toString());
						map.put("grossPremium", insurancePolicy.getPremium()==null ? "0" : insurancePolicy.getPremium().toString());
						map.put("memberCenter", properties.getProperty("sinatayUrl") + properties.getProperty("memberCenter"));
						map.put("EPolicy", properties.getProperty("sinatayUrl") + properties.getProperty("EPolicy"));
						
						log.error("insured success.start email to customer.");
						boolean flag = sendEmailService.sendInsureSuccessEmail(insurancePolicy.getProductName(), map);
						log.error("insured success.start email to customer. send result is " + flag);
					}
				}
			}
		} else {
			log.error("error failed.reason : resultMap is null.");
			result = "系统繁忙，请稍后再试！";
		}
	}

	@Override
	public Map<String, String> paymentToBank(OrderForm orderForm) {
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();;
		try {
			PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
			Properties properties = PropertyFileUtils.getProperties();
			String sinatayUrl = properties.getProperty("sinatayUrl");
			
			//支付类型
			String payment_type = "1";
			//必填，不能修改
			//服务器异步通知页面路径
//			String notify_url = "http://www.xxx.com/create_direct_pay_by_user-JAVA-GBK/notify_url.jsp";
			String notify_url = sinatayUrl + "/payment/nosynBank.do";
			//需http://格式的完整路径，不能加?id=123这类自定义参数
			
			//页面跳转同步通知页面路径
			String return_url = sinatayUrl + "/payment/synBank.do";
			//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
			
			//必填 卖家支付宝帐户
			String seller_email = properties.getProperty("sellerAccount");
			//商户网站订单系统中唯一订单号，必填
			String out_trade_no = orderForm.getOrderSerialNumber();
			//必填 订单名称
			String subject = "sinatay";
			//必填 付款金额
			String total_fee = orderForm.getOrderAmount() + "";
			//订单描述
			String body = "";
			//必填 默认支付方式
			String paymethod = "bankPay";
			//默认网银 必填，银行简码请参考接口技术文档
			String defaultbank = orderForm.getBankType();
			//商品展示地址
			String show_url = "";
			//需以http://开头的完整路径，例如：http://www.xxx.com/myorder.html
			//防钓鱼时间戳
			String anti_phishing_key = AlipaySubmit.query_timestamp();
			//若要使用请调用类文件submit中的query_timestamp函数
			//客户端的IP地址
			String exter_invoke_ip = InetAddress.getLocalHost().getHostAddress();;
			//非局域网的外网IP地址，如：221.0.0.1
			
			sParaTemp.put("service", "create_direct_pay_by_user");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("payment_type", payment_type);
			sParaTemp.put("notify_url", notify_url);
			sParaTemp.put("return_url", return_url);
			sParaTemp.put("seller_email", seller_email);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("subject", subject);
			sParaTemp.put("total_fee", total_fee);
			sParaTemp.put("body", body);
			sParaTemp.put("paymethod", paymethod);
			sParaTemp.put("defaultbank", defaultbank);
			sParaTemp.put("show_url", show_url);
			sParaTemp.put("anti_phishing_key", anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return sParaTemp;
	}

	/**
	 * 处理纯网银支付银行返回的信息
	 */
	@Override
	public Map<String, Object> processBankPayMesage(HttpServletRequest request) {
		log.error("the bank payment, payment is successful, began to return information");
		Map<String, Object> map = new HashMap<String, Object>();
		OrderForm orderForm = null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter
					.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = request.getParameter("out_trade_no");
			log.error("the bank payment.out_trade_no is " + out_trade_no);
			// 支付宝交易号
			String trade_no = request.getParameter("trade_no");
			// 交易状态
			String trade_status = request.getParameter("trade_status");
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			// 计算得出通知验证结果
			orderForm = orderFormService.findOrderFormByOrderFormSerialNumber(out_trade_no);
			if (orderForm == null) {
				map.put("flag", false);
				map.put("orderForm", null);
				log.error("the bank payment.orderForm is null.");
				return map;
			}
			if (!OrderStatus.UNPAID.getValue().equals(orderForm.getOrderStatus())) {
				map.put("flag", false);
				map.put("orderForm", orderForm);
				orderForm.setPaymentMessage("订单状态不为'未支付'，支付失败,订单状态目前为:["+orderForm.getOrderStatus()+"]");
				log.error("the bank payment.order status is false.message: " + orderForm.getPaymentMessage());
				return map;
			}
			
			boolean verify_result = false;
			try {
				verify_result = AlipayNotify.verify(params);
			} catch (Exception e) {
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					orderForm.setPaymentMessage("支付宝支付成功，安全验证失败.支付宝返回验证错误信息："+e.getMessage());
				} else {
					orderForm.setPaymentMessage("支付宝支付失败，安全验证失败.支付宝返回验证错误信息："+e.getMessage());
				}
				log.error("the bank payment.validate false." + e.getMessage());
			}
			savePaymentAccount(request, orderForm);
			if (verify_result) {// 验证成功
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					map.put("flag", true);
					map.put("orderForm", orderForm);
					orderForm.setPaymentMessage("支付宝支付成功，安全验证成功");
					orderForm.setOrderStatus(OrderStatus.PAYMENTSUCCESS.getValue());
					orderForm.setOrderStatusName(OrderStatus.PAYMENTSUCCESS.getDataName());
					orderForm.setPayStatus(PayStatus.PAYMENTSUCCESS.getValue());
					orderForm.setPayStatusName(PayStatus.PAYMENTSUCCESS.getDataName());
					final InsurancePolicy insurancePolidy = orderForm.getInsurancePolicy();

					log.error("bank payment validate success.");
					/**支付成功之后，进行核保校验**/
					taskExecutor.execute(new Runnable() {
						@Override
						public void run() {
							log.error("bank payment validate success. begin to insured. runing....");
							insured(insurancePolidy);
							log.error("bank payment validate success. insured end.");
						}
					});
				} else {
					map.put("flag", false);
					map.put("orderForm", orderForm);
					orderForm.setPaymentMessage("支付宝支付失败，安全验证成功");
					orderForm.setOrderStatus(OrderStatus.PAYMENTFAILURE.getValue());
					orderForm.setOrderStatusName(OrderStatus.PAYMENTFAILURE.getDataName());
					orderForm.setPayStatus(PayStatus.PAYMENTFAILURE.getValue());
					orderForm.setPayStatusName(PayStatus.PAYMENTFAILURE.getDataName());

					log.error("bank payment validate false." + orderForm.getPaymentMessage());
				}
			} else {
				map.put("flag", false);
				map.put("orderForm", orderForm);
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					if (StringUtils.isNotEmpty(orderForm.getPaymentMessage())) {
						orderForm.setPaymentMessage(orderForm.getPaymentMessage() + ";支付宝支付成功，安全验证失败，需人工处理");						
					} else {
						orderForm.setPaymentMessage("支付宝支付成功，安全验证失败，需人工处理");	
					}
				} else {
					if (StringUtils.isNotEmpty(orderForm.getPaymentMessage())) {
						orderForm.setPaymentMessage(orderForm.getPaymentMessage() + ";支付宝支付失败，安全验证失败");						
					} else {
						orderForm.setPaymentMessage("支付宝支付失败，安全验证失败");	
					}
				}
				orderForm.setOrderStatus(OrderStatus.PAYMENTFAILURE.getValue());
				orderForm.setOrderStatusName(OrderStatus.PAYMENTFAILURE.getDataName());
				orderForm.setPayStatus(PayStatus.PAYMENTFAILURE.getValue());
				orderForm.setPayStatusName(PayStatus.PAYMENTFAILURE.getDataName());
				
				log.error("bank payment validate false." + orderForm.getPaymentMessage());
			}
			orderForm.setUpdateTime(new Date());
			orderFormService.updateOrderForm(orderForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 面支付直接承保
	 */
	public Map<String, String> directUnderwriting(OrderForm orderForm) {
		Map<String,String> map = new HashMap<String, String>();
		final InsurancePolicy insurancePolidy = orderForm==null?null:orderForm.getInsurancePolicy();
		if(insurancePolidy != null && insurancePolidy.getPolicyStatus() != PolicyStatus.POLICY_SUCC.getValue()){
			orderForm.setPaymentMessage("支付宝支付成功,验证成功.");
			orderForm.setOrderStatus(OrderStatus.PAYMENTSUCCESS.getValue());
			orderForm.setOrderStatusName(OrderStatus.PAYMENTSUCCESS.getDataName());
			orderForm.setPayStatus(PayStatus.PAYMENTSUCCESS.getValue());
			orderForm.setPayStatusName(PayStatus.PAYMENTSUCCESS.getDataName());
			
			/**支付成功之后，进行核保校验**/
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					insured(insurancePolidy);
				}
			});
		}
		return map;
	}
}

package cn.com.sinosoft.ebusiness.quartz.service.spring;

import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.order.service.facade.OrderFormService;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyService;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.SendEmailService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeFunctionSwitchService;
import cn.com.sinosoft.ebusiness.quartz.service.facade.SendEmailQuartzService;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteMainService;
import cn.com.sinosoft.util.io.FilePathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;
import cn.com.sinosoft.util.string.StringUtils;
import cn.com.sinosoft.util.time.DateUtils;

public class SendEmailQuartzServiceSpringImpl implements SendEmailQuartzService {

	@Autowired
	public OrderFormService orderFormService;
	
	@Autowired
	public QuoteMainService quoteMainService;
	
	@Autowired
	public InsurancePolicyService insurancePolicyService;
	
	@Autowired
	private GeCodeService geCodeService;// 数据字典service
	
	@Autowired
	private SendEmailService sendEmailService;
	
	/**功能开关*/
	@Autowired
	private GeFunctionSwitchService geFunctionSwitchService;
	
	/**
	 * 定时扫未支付的订单信息，给投保人发送订单支付邮件
	 */
	public void sendOrderPayEmail() {
		System.out.println("### sendOrderPayEmail()[定时扫未支付订单]..."+DateUtils.getCurDateTime());
		try {
			if(geFunctionSwitchService.isSwitchOpen("sendOrderPayEmail")){
				QueryRule queryRule = QueryRule.getInstance();
				queryRule.addEqual("orderStatus", 82);
				List<OrderForm> orders = orderFormService.findAllOrderFormByPropertyMap(queryRule);
//				System.out.println("orders.size(): "+orders.size());
				Map<String,String> map = new HashMap<String, String>();
				PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
				Properties properties = PropertyFileUtils.getProperties();
				List<OrderForm> orderFormAll = new ArrayList<OrderForm>();
				for(OrderForm order : orders){
					long minute = DateUtils.dateDiffInMinute(new Date(), order.getUpdateTime());
//					System.out.println("minute: "+minute);
					if(order != null && order.getSerialNo() != null && order.getPersonalUserSerialNo()!= null && order.getOrderStatus()!= null && order.getOrderStatus() == 82){
						int remindCount = order.getRemindCount() == null?0:order.getRemindCount();
						if(minute >= 1800 && minute < 7200 && remindCount < 1){
							orderFormAll.add(order);
							InsurancePolicy policy = order.getInsurancePolicy();
							if(policy != null && policy.getInsuranceApplicant() != null && StringUtils.isNotBlank(policy.getInsuranceApplicant().getEmail())){
								map.put("email", policy.getInsuranceApplicant().getEmail());
								map.put("fullName", policy.getInsuranceApplicant()==null?"":policy.getInsuranceApplicant().getFullName());
								map.put("date", DateUtils.formatDate(order.getUpdateTime(), DateUtils.ZHCN_DATATIMEF_STR_YYYYMMDDHHMM));
								String insName = policy.getInsuranceApplicant()==null?"":policy.getInsuranceApplicant().getFullName();
								if(policy.getInsureds() != null && !policy.getInsureds().isEmpty() && policy.getInsureds().get(0) != null){
									insName = policy.getInsureds().get(0).getFullName();
								}
								int appGender = policy.getInsuranceApplicant()==null?0:policy.getInsuranceApplicant().getGender();
								map.put("insFullName", insName);
								map.put("genderTag", appGender == 0?"先生":"女士");
								map.put("productName", policy.getProductName());
								map.put("policyNo", policy.getApplicationNumber());
								map.put("count", policy.getUnitCount()==null?"1":policy.getUnitCount().toString());
								map.put("premium", policy.getPremium()==null?"0":policy.getPremium().toString());
								map.put("grossPremium", order.getOrderAmount()==null?"0":order.getOrderAmount().toString());
								map.put("orderPay", properties.getProperty("sinatayUrl")+"/payment/toPaymentEmail.do?source=email&id="+Base64.encodeBase64String(order.getSerialNo().getBytes())+"&userId="+Base64.encodeBase64String(order.getPersonalUserSerialNo().getBytes()));
								sendEmailService.sendOrderPayEmail("",map);
								
							}
						}
					}
					
				}
//				System.out.println("orderFormAll.size(): "+orderFormAll.size());
				if(!orderFormAll.isEmpty())
					orderFormService.updateAllRemindCount(orderFormAll);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 定时扫待完善的订单信息，给投保人发送完善订单邮件
	 */
	public void sendOrderCompleteEmail() {
		System.out.println("### sendOrderCompleteEmail()[定时扫待完善订单]..."+DateUtils.getCurDateTime());
		try {
			if(geFunctionSwitchService.isSwitchOpen("sendOrderCompleteEmail")){
				QueryRule queryRule = QueryRule.getInstance();
				queryRule.addEqual("policyStatus", 1);
				List<InsurancePolicy> policys = insurancePolicyService.findInsurancePolicyByQueryRule(queryRule);
//				System.out.println("policys.size(): "+policys.size());
				Map<String,String> map = new HashMap<String, String>();
				PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
				Properties properties = PropertyFileUtils.getProperties();
				List<InsurancePolicy> insurancePolicyAll = new ArrayList<InsurancePolicy>();
				for(InsurancePolicy policy : policys){
					if(policy != null && policy.getSignedDate() != null && policy.getQuoteNo() != null && policy.getProductCode() != null && policy.getPersonalUserSerialNo() != null && policy.getPolicyStatus() != null && policy.getPolicyStatus() == 1){
						long minute = DateUtils.dateDiffInMinute(new Date(), policy.getSignedDate());
						int remindCount = policy.getRemindCount() == null?0:policy.getRemindCount();
//						System.out.println("remindCount: "+remindCount+", minute: "+minute+", signedDate: "+policy.getSignedDate()+", quoteNo: "+policy.getQuoteNo()+", productCode: "+policy.getProductCode()+", userSerialNo: "+policy.getPersonalUserSerialNo()+", policyStatus: "+policy.getPolicyStatus());
						if(minute >= 1800 && minute < 7200 && remindCount < 1){
							insurancePolicyAll.add(policy);
							if(policy.getInsuranceApplicant() != null && StringUtils.isNotBlank(policy.getInsuranceApplicant().getEmail())){
								int appGender = policy.getInsuranceApplicant()==null?0:policy.getInsuranceApplicant().getGender();
								map.put("email", policy.getInsuranceApplicant().getEmail());
								map.put("date", DateUtils.formatDate(policy.getUpdateTime(), DateUtils.ZHCN_DATATIMEF_STR_YYYYMMDDHHMM));
								map.put("fullName", policy.getInsuranceApplicant()==null?"":policy.getInsuranceApplicant().getFullName());
								map.put("genderTag", appGender == 0?"先生":"女士");
								map.put("productName", policy.getProductName());
								map.put("continueToInsure", properties.getProperty("sinatayUrl")+"/sale/obtainContinueInsuranceDataEmail.do?source=email&quoteNo="+Base64.encodeBase64String(policy.getQuoteNo().getBytes())+"&productCode="+Base64.encodeBase64String(policy.getProductCode().getBytes())+"&proposalSID="+Base64.encodeBase64String(policy.getSerialNo().getBytes())+"&userId="+Base64.encodeBase64String(policy.getPersonalUserSerialNo().getBytes()));
								sendEmailService.sendOrderCompleteEmail(policy.getProductName(),map);
							}
						}
					}
				}
//				System.out.println("insurancePolicyAll.size(): "+insurancePolicyAll.size());
				if(!insurancePolicyAll.isEmpty())
					insurancePolicyService.updateAllRemindCount(insurancePolicyAll);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

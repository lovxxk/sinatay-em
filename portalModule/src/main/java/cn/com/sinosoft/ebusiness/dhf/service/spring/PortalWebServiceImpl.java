package cn.com.sinosoft.ebusiness.dhf.service.spring;

import ins.framework.dao.GenericDaoHibernate;

import java.io.StringWriter;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.impl.cookie.DateUtils;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.sinosoft.businessModule.bankAccount.domain.PaymentAccount;
import cn.com.sinosoft.businessModule.enums.dictionary.OrderStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.PolicyStatus;
import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.InsuranceApplicant;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.Insured;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicyLiability;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyService;
import cn.com.sinosoft.ebusiness.dhf.domain.TakeATaxi;
import cn.com.sinosoft.ebusiness.dhf.service.fascade.GeFunctionSwitchPortalService;
import cn.com.sinosoft.ebusiness.dhf.service.fascade.PortalWebService;
import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.enumUtil.RequestProcessStatus;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.util.GeneratorTransSerialNumber;
import cn.com.sinosoft.portalModule.interfacePortal.xml.factory.TXInsuranceFactory;
import cn.com.sinosoft.portalModule.portalTransaction.service.fascade.PortalTransactionService;
import cn.com.sinosoft.portalModule.transport.dhf100.Data;
import cn.com.sinosoft.portalModule.transport.dhf100.Datas;
import cn.com.sinosoft.portalModule.transport.dhf100.QueryData;
import cn.com.sinosoft.portalModule.transport.service.InterfaceTransportService;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.portalModule.transport.sinatay.LCInsured;
import cn.com.sinosoft.portalModule.transport.sinatay.ReconciliationDetail;
import cn.com.sinosoft.portalModule.transport.sinatay.Risk;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.util.encode.AES;
import cn.com.sinosoft.util.string.StringUtils;
import cn.com.sinosoft.util.time.DateFormatUtils;

public class PortalWebServiceImpl extends GenericDaoHibernate implements PortalWebService {

	private PortalTransactionService portalTransactionService;

	private TakeATaxiServiceImpl takeATaxiService;

	private InsurancePolicyService insurancePolicyService;

	private InterfaceTransportService interfaceTransportService;

	private GeFunctionSwitchPortalService geFunctionSwitchPortalService;

	private static TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();
	
	private static Logger log = LoggerFactory.getLogger(PortalWebServiceImpl.class);
	
	private String tagValue(String xml, String beginTag, String endTag){
		int beginIndex = xml.indexOf(beginTag);
		int endIndex = xml.indexOf(endTag);
		if(beginIndex >= 0)
			return xml.substring(beginIndex+beginTag.length(), endIndex);
		return null;
	}
	
	private String tagValue(String xml, String beginTag, String endTag, String value){
		int beginIndex = xml.indexOf(beginTag);
		int endIndex = xml.indexOf(endTag);
		if(beginIndex >= 0)
			return xml.substring(0,beginIndex+beginTag.length())+value+xml.substring(endIndex);
		return null;
	}
	
	public Object webService(String requestXML){
		try {
			// 解密
			System.out.println(getXmlFilePath());
			requestXML = AES.Decrypt(requestXML, getXmlFilePath());
			//<FunctionFlag>ST000095</FunctionFlag>
			String invokeMethodName = tagValue(requestXML, "<FunctionFlag>", "</FunctionFlag>");
			Method method = getClass().getMethod(invokeMethodName, String.class);
			return method.invoke(this, requestXML);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtils.exception2String(e));
		} 
		return null;
	}
	

	public String ST000025(String requestXML) {
		String source = null;
		TXInsurance request = null;
		TXInsurance response = null;
		String responseXML = null;
		RequestProcessStatus requestProcessStatus = RequestProcessStatus.OK;
		RequestProcessStatus responseProcessStatus = null;
		log.error(requestXML);
		// 请求报文 => 对象
		try {
			request = (TXInsurance) txFactory.getConvertMessageService()
					.unMarshall(requestXML, "ST000025",MessageType.FRONTREQUEST.getDataENName());
			source = request.getSource();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtils.exception2String(e));
			requestProcessStatus = RequestProcessStatus.BADREQUEST;
		}
		// 保存大黄蜂请求报文
		portalTransactionService.insertAndUpdatePortalTransaction(
				source,
				"ST000025", 
				requestXML, 
				request.getTransRefGUID(), 
				null,
				null, 
				MessageType.FRONTREQUEST, 
				requestProcessStatus);
		if(RequestProcessStatus.OK == requestProcessStatus){
			// 本地 <=> 核心
			try {
				responseXML = interfaceTransportService.sendServletRequestXML(requestXML, "ST000025");
			} catch (Exception e) {
				e.printStackTrace();
				log.error(StringUtils.exception2String(e));
			}
			// 保存核心请求报文
			portalTransactionService.insertAndUpdatePortalTransaction(
					"核心",
					"ST000025", 
					requestXML, 
					request.getTransRefGUID(), 
					null,
					null, 
					MessageType.REQUEST, 
					RequestProcessStatus.OK);
			//核心返回0失败,1成功
			try {
				boolean isReturnSuccess = "1".equals(tagValue(responseXML, "<Flag>", "</Flag>"));
				String returnDesc = tagValue(responseXML, "<Desc>", "</Desc>");
				responseProcessStatus = null;
				if(isReturnSuccess){
					responseProcessStatus = RequestProcessStatus.SUCCESS;
				}else{
					responseProcessStatus = RequestProcessStatus.FAIL;
					responseProcessStatus.setDataName(returnDesc);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error(StringUtils.exception2String(e));
				responseProcessStatus = RequestProcessStatus.FAIL;
			}
			// 保存核心响应报文
			portalTransactionService.insertAndUpdatePortalTransaction(
					"核心",
					"ST000025", 
					responseXML, 
					request.getTransRefGUID(), 
					null,
					null, 
					MessageType.RESPONSE, 
					responseProcessStatus);
		}
		
		try {
			//核心响应报文 => 对象
			response = (TXInsurance) txFactory.getConvertMessageService()
					.unMarshall(responseXML, "ST000025",MessageType.RESPONSE.getDataENName());
			//对象 => 大黄蜂响应对象
			responseXML = txFactory.getConvertMessageService()
					.marshaller(response, "ST000025",MessageType.FRONTRESPONSE.getDataENName());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtils.exception2String(e));
			response = request;
			response.setTransExeDate(DateFormatUtils.format(new Date(), "yyyyMMdd"));
			response.setTransExeTime(DateFormatUtils.format(new Date(), "HH:mm:ss"));
			TranResponse tranResponse = new TranResponse();
			tranResponse.setFlag("0");
			tranResponse.setDesc("报文映射异常");
			response.getBusinessDatum().clear();
			response.getBusinessDatum().add(tranResponse);
			responseProcessStatus = RequestProcessStatus.VALIDATE;
			responseProcessStatus.setDataName("报文映射异常");
			// 对象 => 报文
			responseXML = txFactory.getConvertMessageService().marshaller(response, "ST000025",MessageType.FRONTRESPONSE.getDataENName());
		}
		if(RequestProcessStatus.BADREQUEST == requestProcessStatus){
			responseProcessStatus = RequestProcessStatus.FAIL;
		}
		// 保存大黄蜂响应报文
		portalTransactionService.insertAndUpdatePortalTransaction(
				source,
				"ST000025", 
				responseXML, 
				request.getTransRefGUID(), 
				null,
				null, 
				MessageType.FRONTRESPONSE, 
				responseProcessStatus);
		// 加密
		log.error(responseXML);
		try {
			responseXML = AES.Encrypt(responseXML, getXmlFilePath());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtils.exception2String(e));
		}
		return responseXML;
	}

	public String ST000052(String requestXML) {
		String localOrderId = GeneratorTransSerialNumber.generatorTransSerialNumber();
		String source = null;
		String merchantOrderId = null;
		TXInsurance request = null;
		TXInsurance response = null;
		String responseXML = null;
		RequestProcessStatus requestProcessStatus = RequestProcessStatus.OK;
		RequestProcessStatus responseProcessStatus = RequestProcessStatus.SUCCESS;
		boolean isReturnSuccess = false;
		boolean isRetDataMode = false;
		log.error(requestXML);
		// 请求报文 => 对象
		LCCont lcCont = null;
		try {
			request = (TXInsurance) txFactory.getConvertMessageService()
					.unMarshall(requestXML, "ST000052",MessageType.FRONTREQUEST.getDataENName());
			source = request.getSource();
			lcCont = ((TranRequest) request.getBusinessDatum().get(0)).getLcCont();
			merchantOrderId = lcCont.getOrderId();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtils.exception2String(e));
			requestProcessStatus = RequestProcessStatus.BADREQUEST;
		}
		// 保存大黄蜂请求报文
		portalTransactionService.insertAndUpdatePortalTransaction(
				source,
				"ST000052", 
				requestXML, 
				request.getTransRefGUID(),
				localOrderId, 
				merchantOrderId, 
				MessageType.FRONTREQUEST,
				requestProcessStatus);
		// 字段校验
		boolean isValidSucc = true;
		String validFailMessage = "";
		try {
			if (geFunctionSwitchPortalService.isSwitchOpen("verificationMobilePhoneNumber", source)) {
				String mobileNumberRegx = "^13[0-9]{9}|15[0-9]{9}|18[0-9]{9}|147[0-9]{8}$";
				if (!lcCont.getLcAppnt().getMobilePhoneNumber().matches(mobileNumberRegx)) {
					isValidSucc = false;
					validFailMessage += "投保人手机号码不正确。";
				}
				for (LCInsured lcInsured : lcCont.getLcInsureds()) {
					if (!lcInsured.getMobilePhoneNumber().matches(mobileNumberRegx)) {
						isValidSucc = false;
						validFailMessage += "被保人手机号码不正确。";
					}
				}
			}
			if (geFunctionSwitchPortalService.isSwitchOpen("verificationDate",source)) {
				SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
				try {
					sdfDate.parse(request.getTransExeDate());
					sdfTime.parse(request.getTransExeTime());
					sdfDate.parse(lcCont.getStartDate());
					sdfTime.parse(lcCont.getStartTime());
					sdfDate.parse(lcCont.getEndDate());
					sdfTime.parse(lcCont.getEndTime());
				} catch (Exception e) {
					isValidSucc = false;
					validFailMessage += "日期时间格式不正确。";
				}
			}
		} catch (Exception e) {
			isValidSucc = false;
			e.printStackTrace();
			log.error(StringUtils.exception2String(e));
			validFailMessage = RequestProcessStatus.VALIDATE.getDataName();
		}
		// 校验通过,进入核心交互承保
		if (isValidSucc) {
			// 本地 <=> 核心
			try {
				responseXML = interfaceTransportService.sendServletRequestXML(requestXML, "ST000052");
			} catch (Exception e) {
				e.printStackTrace();
				log.error(StringUtils.exception2String(e));
			}
			// 保存核心请求报文
			portalTransactionService.insertAndUpdatePortalTransaction(
					"核心",
					"ST000052", 
					requestXML, 
					request.getTransRefGUID(),
					localOrderId, 
					merchantOrderId, 
					MessageType.REQUEST,
					RequestProcessStatus.OK);
			/*
			//非预期异常的处理
			isRetDataMode = tagValue(responseXML, "<RetData>", "</RetData>") != null;
			//如果不是非预期异常的处理,重新生成响应对象
			if(!isRetDataMode){
				// 响应报文 => 对象
				response = (TXInsurance) txFactory.getConvertMessageService().unMarshall(responseXML, "ST000052",MessageType.RESPONSE.getDataENName());
			}
			*/
			try {
				isRetDataMode = tagValue(responseXML, "<RetData>", "</RetData>") != null;
				String reuturnFlag = tagValue(responseXML, "<Flag>", "</Flag>");
				String returnDesc = tagValue(responseXML, "<Desc>", "</Desc>");
				//非预期异常的处理 0失败,1成功
				if(isRetDataMode)
					isReturnSuccess = "1".equals(reuturnFlag);
				//核心返回0承保成功,1承保失败
				else
					isReturnSuccess = "0".equals(reuturnFlag);
				if(isReturnSuccess){
					//纠正为0承保失败,1承保成功
//					responseXML = tagValue(responseXML, "<Flag>", "</Flag>","1");
					responseProcessStatus = RequestProcessStatus.SUCCESS;
				}else{
					//纠正为0承保失败,1承保成功
//					responseXML = tagValue(responseXML, "<Flag>", "</Flag>","0");
					responseProcessStatus = RequestProcessStatus.FAIL;
					responseProcessStatus.setValue(Integer.parseInt(reuturnFlag));
					responseProcessStatus.setDataName(returnDesc);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				log.error(StringUtils.exception2String(e));
				responseProcessStatus = RequestProcessStatus.FAIL;
			}
			// 保存核心响应报文
			portalTransactionService.insertAndUpdatePortalTransaction(
					"核心",
					"ST000052", 
					responseXML, 
					request.getTransRefGUID(),
					localOrderId, 
					merchantOrderId, 
					MessageType.RESPONSE,
					responseProcessStatus);
		} 
		// 校验不正确的响应报文
		if(!isValidSucc){
			response = request;
			response.setTransExeDate(DateFormatUtils.format(new Date(), "yyyyMMdd"));
			response.setTransExeTime(DateFormatUtils.format(new Date(), "HH:mm:ss"));
			TranResponse tranResponse = new TranResponse();
			tranResponse.setFlag("0");
			tranResponse.setDesc(validFailMessage);
			response.getBusinessDatum().clear();
			response.getBusinessDatum().add(tranResponse);
			responseProcessStatus = RequestProcessStatus.VALIDATE;
			responseProcessStatus.setDataName(validFailMessage);
			// 对象 => 报文
			responseXML = txFactory.getConvertMessageService().marshaller(response, "ST000052",MessageType.FRONTRESPONSE.getDataENName());
		}
		/*
		//如果不是非预期异常的处理,重新生成响应报文
		if(!isRetDataMode){
			//纠正为0承保失败,1承保成功
			((TranResponse) response.getBusinessDatum().get(0)).setFlag(reuturnFlag);
			// 对象 => 报文
			responseXML = txFactory.getConvertMessageService().marshaller(response, "ST000052",MessageType.FRONTRESPONSE.getDataENName());
		}
		*/
		// 保存大黄蜂响应报文
		portalTransactionService.insertAndUpdatePortalTransaction(
				source,
				"ST000052", 
				responseXML, 
				request.getTransRefGUID(),
				localOrderId, 
				merchantOrderId, 
				MessageType.FRONTRESPONSE,
				responseProcessStatus);
		// 加密
		log.error(responseXML);
		try {
			responseXML = AES.Encrypt(responseXML, getXmlFilePath());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtils.exception2String(e));
		}
		// 承保后只需保存业务数据和相关的报文信息
		try {
			if (isReturnSuccess) {
				responseProcessStatus = RequestProcessStatus.SUCCESS;
				String contnNo = tagValue(responseXML, "<ContNo>", "</ContNo>"); 
				String proposalContNo = tagValue(responseXML, "<ProposalContNo>", "</ProposalContNo>"); 
				// 保存数据
				InsurancePolicy policy = new InsurancePolicy();
				policy.setTransSerialNumber(request.getTransRefGUID());// 交易流水号
				policy.setMerchantTransSerialNumber(request.getTransRefGUID());// 交易流水号
				policy.setGroupChannel(request.getSaleChannel());// 销售渠道
				policy.setSellType(request.getSellType());// 销售方式
				policy.setBusinessSource(request.getSource());// 信息来源
				policy.setApplicationDate(lcCont.getPolApplyDate());// 投保日期
				policy.setGrossPremium(lcCont.getPrem());// 总保费
				policy.setTransIdentify("ST000052");
				policy.setOrderSerialNumber(localOrderId);
				policy.setMerchantOrderNumber(merchantOrderId);// 订单号
				policy.setPolicySerialNumber(contnNo);// 保单号
				policy.setApplicationNumber(proposalContNo);// 投保单号
				policy.setApplicationSerialNumber(proposalContNo);
				policy.setPolicyStatus(PolicyStatus.POLICY_SUCC.getValue());
				policy.setPolicyStatusName(PolicyStatus.POLICY_SUCC.getDataName());
				policy.setPolicyStatusDesc(PolicyStatus.POLICY_SUCC.getDataName());
				OrderForm order = new OrderForm();
				order.setOrderSerialNumber(localOrderId);
				order.setMerchantOrderNumber(merchantOrderId);// 订单号
				order.setTransSerialNumber(lcCont.getPaySerialNo());// 支付流水号
				order.setOrderStatus(OrderStatus.PAYMENTSUCCESS.getValue());
				order.setOrderStatusName(OrderStatus.PAYMENTSUCCESS.getDataName());
				// order.setOrderStatusDesc();
				order.setInsurancePolicy(policy);
				policy.setOrderForm(order);
				PaymentAccount pay = new PaymentAccount();
				pay.setSecBankCode(lcCont.getAccBankCode());// 银行编码
				pay.setSecBankAccNo(lcCont.getBankAccNo());// 银行账户
				pay.setInsurancePolicy(policy);
				policy.setPaymentAccount(pay);
				InsuranceApplicant applicant = new InsuranceApplicant();
				applicant.setFullName(lcCont.getLcAppnt().getFullName());// 投保人姓名
				applicant.setMobilePhoneNumber(lcCont.getLcAppnt().getMobilePhoneNumber());// 投保人移动电话
				applicant.setRelatedToInsured(1);// 与被保人关系 (*)
				applicant.setInsurancePolicy(policy);
				policy.setInsuranceApplicant(applicant);
				policy.setInsuredNumber(lcCont.getLcInsuredCount());// 被保人数量
				for (LCInsured lcInsured : lcCont.getLcInsureds()) {
					Insured insured = new Insured();
					insured.setFullName(lcInsured.getFullName());// 被保人姓名
					insured.setMobilePhoneNumber(lcInsured.getMobilePhoneNumber());// 投保人移动电话
					insured.setInsurancePolicy(policy);
					policy.getInsureds().add(insured);
					policy.setInsurancePolicyLiabilityNumber(lcInsured.getRiskCount());// 险种数量
					for (Risk risk : lcInsured.getRisks()) {
						InsurancePolicyLiability liability = new InsurancePolicyLiability();
						liability.setLiabilityCode(risk.getRiskCode());// 险种代码
						liability.setCoreCode(risk.getRiskCode());// 险种代码
						liability.setMainRiskCode(risk.getMainRiskCode());// 主险险种代码
						liability.setLiabilityName(risk.getRiskName());// 险种名称
						liability.setInsuredAmount(risk.getAmnt());// 保额
						liability.setPremium(risk.getPrem());// 保险费
						liability.setInsurancePolicy(policy);
						policy.getInsurancePolicyLiabilities().add(liability);
					}
				}
				TakeATaxi taxi = new TakeATaxi();
				taxi.setCarNo(lcCont.getCarNo());// 车牌号
				taxi.setStartDate(lcCont.getStartDate());// 出发日期
				taxi.setStartTime(lcCont.getStartTime());// 出发时间
				taxi.setEndDate(lcCont.getEndDate());// 结束日期
				taxi.setEndTime(lcCont.getEndTime());// 结束时间
				taxi.setStartPlace(lcCont.getStartPlace());// 出发地
				taxi.setAcrossFlag(lcCont.getAcrossFlag());// 本地单、跨地域单标识
				taxi.setInsurancePolicy(policy);
				insurancePolicyService.addInsurancePolicy(policy);
				takeATaxiService.save(taxi);
				getSession().flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtils.exception2String(e));
		}
		return responseXML;
	}

	public String ST000095(String requestXML) {
		String source = null;
		TXInsurance request = null;
		TXInsurance response = null; 
		String responseXML = null;
		RequestProcessStatus requestProcessStatus = RequestProcessStatus.OK;
		RequestProcessStatus responseProcessStatus = RequestProcessStatus.SUCCESS;
		boolean isSuccess = true;
		String failMessage = ""; 
		log.error(requestXML);
		try {
			//请求报文 => 对象
			request = (TXInsurance) txFactory.getConvertMessageService()
					.unMarshall(requestXML, "ST000095", MessageType.FRONTREQUEST.getDataENName());
			source = request.getSource();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtils.exception2String(e));
			requestProcessStatus = RequestProcessStatus.BADREQUEST;
		}
		//保存大黄蜂请求报文
		portalTransactionService.insertAndUpdatePortalTransaction(
				source,
				"ST000095",
				requestXML,
				request.getTransRefGUID(),
				null,
				null,
				MessageType.FRONTREQUEST,
				requestProcessStatus);
		try {
			//本地 查询 核心数据库, 对账逻辑
			String billDate = ((TranRequest)request.getBusinessDatum().get(0)).getBillDate();
			BigDecimal totalPrem = ((TranRequest)request.getBusinessDatum().get(0)).getTotalPrem();
			List<ReconciliationDetail> detailList = ((TranRequest)request.getBusinessDatum().get(0)).getDetailList();
			String sql = "select lc.signdate as TransDate, lc.proposalcontno as Prtno, lc.prem as Prem, lc.appflag as AppFlag "
					+ "from lccontsource lcs, lccont lc where lcs.proposalcontno = lc.proposalcontno and lcs.source = 'DHF' "
					+ "and lc.signdate between to_date(?, 'yyyyMMdd') and to_date(?, 'yyyyMMdd')";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(billDate));
			cal.add(Calendar.DAY_OF_YEAR, 1);
			String start = billDate;
			String end = sdf.format(cal.getTime());
			List resultList = super.findBySql(sql, new Object[]{start,end});
			if(detailList.size()>0&&resultList.size()>0){
				for(int i = detailList.size()-1 ; i >= 0 ; i--){
					ReconciliationDetail detail = detailList.get(i);
					for(int j = resultList.size()-1 ; j >= 0 ; j--){
						Object[] reulstDetail = (Object[])resultList.get(j);
						if(!detail.getPrtno().equals(reulstDetail[1])){
							continue;
						}else{
							//找到投保单
							detailList.remove(i);
							resultList.remove(j);
						}
						if(detail.getPrem().compareTo((BigDecimal)reulstDetail[2])!=0){
							failMessage+="投保单号"+detail.getPrtno()+"的保费不一致。";
							isSuccess = false;
						}
						if(!detail.getAppFlag().equals(reulstDetail[3])){
							failMessage+="投保单号"+detail.getPrtno()+"的保单状态不一致。";
							isSuccess = false;
						}
					}
				}
			}
			//大黄蜂投保单多了
			for(int i=0;i<detailList.size();i++){
				failMessage+="核心的记录未找到大黄蜂的投保单号"+detailList.get(i).getPrtno()+"。";
				isSuccess = false;
			}
			//核心投保单多了
			for(int i=0;i<resultList.size();i++){
				Object[] reulstDetail = (Object[])resultList.get(i);
				failMessage+="大黄蜂的记录未找到核心的投保单号"+reulstDetail[1]+"。";
				isSuccess = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtils.exception2String(e));
			responseProcessStatus = RequestProcessStatus.FAIL;
			isSuccess = false;
		}
		try {
			response = request;
			response.setTransExeDate(DateFormatUtils.format(new Date(), "yyyyMMdd"));
			response.setTransExeTime(DateFormatUtils.format(new Date(), "HH:mm:ss"));
			TranResponse tranResponse = new TranResponse();
			tranResponse.setFlag(isSuccess?"1":"0");
			response.getBusinessDatum().clear();
			response.getBusinessDatum().add(tranResponse);
			if(!isSuccess){
				tranResponse.setDesc(failMessage);
				responseProcessStatus = RequestProcessStatus.FAIL;
				responseProcessStatus.setDataName(failMessage);
			}
			//对象 -> 报文
			responseXML = txFactory.getConvertMessageService()
					.marshaller(response, "ST000095",MessageType.FRONTRESPONSE.getDataENName());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtils.exception2String(e));
			responseProcessStatus = RequestProcessStatus.FAIL;
		}
		//保存大黄蜂响应报文
		portalTransactionService.insertAndUpdatePortalTransaction(
				source,
				"ST000095",
				responseXML,
				request.getTransRefGUID(),
				null,
				null,
				MessageType.FRONTRESPONSE,
				responseProcessStatus);
		//加密
		log.error(responseXML);
		try {
			responseXML= AES.Encrypt(responseXML,getXmlFilePath());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtils.exception2String(e));
		}
		return responseXML;
	}
	
	public String getXmlFilePath(){
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load(new String[]{"classpath:/spring/applicationContext-dataAccess.xml"});
		ctx.refresh();
		JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
		String AES_CODE = "";
		List<Map<String, Object>> objectMaps = new ArrayList<Map<String, Object>>(0);
		List<Object> objectList = new ArrayList<Object>(0);
		StringBuffer sql = new StringBuffer("select ");
		sql.append("t.CODECORERELATION");
		sql.append(" from ");
//		sql.append(schema);
		sql.append("ge_code t where t.CODETYPE =");
		sql.append(" 'AES_CODE' ");
		sql.append("and t.CODECODE =");
		sql.append(" '1' ");
		objectMaps = jdbcTemplate.queryForList(sql.toString(), objectList.toArray());
		if(objectMaps != null && !objectMaps.isEmpty() && objectMaps.get(0) != null){
			AES_CODE = (String) objectMaps.get(0).get("CODECORERELATION");
		}
		return AES_CODE;
	}

	public void setInsurancePolicyService(
			InsurancePolicyService insurancePolicyService) {
		this.insurancePolicyService = insurancePolicyService;
	}

	public static void setTxFactory(TXInsuranceFactory txFactory) {
		PortalWebServiceImpl.txFactory = txFactory;
	}

	public void setPortalTransactionService(
			PortalTransactionService portalTransactionService) {
		this.portalTransactionService = portalTransactionService;
	}

	public void setTakeATaxiService(TakeATaxiServiceImpl takeATaxiService) {
		this.takeATaxiService = takeATaxiService;
	}

	public void setInterfaceTransportService(
			InterfaceTransportService interfaceTransportService) {
		this.interfaceTransportService = interfaceTransportService;
	}

	public void setGeFunctionSwitchPortalService(
			GeFunctionSwitchPortalService geFunctionSwitchPortalService) {
		this.geFunctionSwitchPortalService = geFunctionSwitchPortalService;
	}

}

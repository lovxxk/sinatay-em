package cn.com.sinosoft.ebusiness.dhf.service.spring;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.impl.cookie.DateUtils;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;

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
import cn.com.sinosoft.portalModule.transport.service.InterfaceTransportService;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.portalModule.transport.sinatay.LCInsured;
import cn.com.sinosoft.portalModule.transport.sinatay.Risk;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.util.encode.AES;

public class PortalWebServiceImpl implements PortalWebService{
	
	private PortalTransactionService portalTransactionService;
	
	private TakeATaxiServiceImpl takeATaxiService;
	
	private InsurancePolicyService insurancePolicyService;
	
	private InterfaceTransportService interfaceTransportService;
	
	private GeFunctionSwitchPortalService geFunctionSwitchPortalService;
	
	private static TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();

	public String ST000025(String requestXML){
		TXInsurance request = null;
		TXInsurance response = null; 
		String responseXML = null;
		try {
			//解密
			requestXML= AES.Decrypt(requestXML,"ST000025ST000025");

			System.out.println(requestXML);
			//请求报文 => 对象
			request = (TXInsurance) txFactory.getConvertMessageService()
					.unMarshall(requestXML, "ST000025", MessageType.FRONTREQUEST.getDataENName());
			//保存大黄蜂请求报文
			portalTransactionService.insertAndUpdatePortalTransaction(
					"DHF",
					"ST000052",
					requestXML,
					request.getTransRefGUID(),
					null,
					null,
					MessageType.FRONTREQUEST,
					RequestProcessStatus.OK);
				//本地 <=> 核心
				responseXML = interfaceTransportService.sendServletRequestXML(requestXML, "ST000025");
				//保存核心请求报文
				portalTransactionService.insertAndUpdatePortalTransaction(
						"DHF",
						"ST000025",
						responseXML,
						request.getTransRefGUID(),
						null,
						null,
						MessageType.REQUEST,
						RequestProcessStatus.OK);
				//保存核心响应报文
				portalTransactionService.insertAndUpdatePortalTransaction(
						"DHF",
						"ST000025",
						responseXML,
						request.getTransRefGUID(),
						null,
						null,
						MessageType.RESPONSE,
						RequestProcessStatus.OK);
			//加密
			System.out.println(responseXML);
			responseXML= AES.Decrypt(requestXML,"ST000025ST000025");
			//保存大黄蜂响应报文
			portalTransactionService.insertAndUpdatePortalTransaction(
					"DHF",
					"ST000025",
					responseXML,
					request.getTransRefGUID(),
					null,
					null,
					MessageType.FRONTRESPONSE,
					RequestProcessStatus.OK);
			return responseXML;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String ST000052(String requestXML){
		String localOrderId = GeneratorTransSerialNumber.generatorTransSerialNumber();
		String merchantOrderId = null;
		TXInsurance request = null;
		TXInsurance response = null; 
		String responseXML = null;
		RequestProcessStatus responseProcessStatus = null;
		try {
			//解密
			requestXML= AES.Decrypt(requestXML,"ST000052ST000052");
			System.out.println(requestXML);
			//请求报文 => 对象
			request = (TXInsurance) txFactory.getConvertMessageService()
					.unMarshall(requestXML, "ST000052", MessageType.FRONTREQUEST.getDataENName());
			LCCont lcCont0 = ((TranRequest)request.getBusinessDatum().get(0)).getLcCont();
			merchantOrderId =lcCont0.getOrderId();
			//保存大黄蜂请求报文
			portalTransactionService.insertAndUpdatePortalTransaction(
					"DHF",
					"ST000052",
					requestXML,
					request.getTransRefGUID(),
					localOrderId,
					merchantOrderId,
					MessageType.FRONTREQUEST,
					RequestProcessStatus.OK);
			//字段校验
			boolean isValidSucc = true;
			String validFailMessage = "";
			if(geFunctionSwitchPortalService.isSwitchOpen("verificationMobilePhoneNumber","DHF")){
				String mobileNumberRegx = "^13[0-9]{9}|15[0-9]{9}|18[0-9]{9}|147[0-9]{8}$";
				if(!lcCont0.getLcAppnt().getMobilePhoneNumber().matches(mobileNumberRegx)){
					isValidSucc = false;
					validFailMessage += "投保人手机号码不正确。";
				}
				for(LCInsured lcInsured : lcCont0.getLcInsureds()){
					if(!lcInsured.getMobilePhoneNumber().matches(mobileNumberRegx)){
						isValidSucc = false;
						validFailMessage += "被保人手机号码不正确。";
					}
				}
			}
			if(geFunctionSwitchPortalService.isSwitchOpen("verificationDate","DHF")){
				SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
				try {
					sdfDate.parse(request.getTransExeDate());
					sdfTime.parse(request.getTransExeTime());
					sdfDate.parse(lcCont0.getStartDate());
					sdfTime.parse(lcCont0.getStartTime());
					sdfDate.parse(lcCont0.getEndDate());
					sdfTime.parse(lcCont0.getEndTime());
				} catch (Exception e) {
					isValidSucc = false;
					validFailMessage += "日期时间格式不正确。";
				}
			}
			if(isValidSucc){//校验通过,进入核心交互承保
				//本地 <=> 核心
				responseXML = interfaceTransportService.sendServletRequestXML(requestXML, "ST000052");
				//responseXML = ST000052ResMarshller();
				//保存核心请求报文
				portalTransactionService.insertAndUpdatePortalTransaction(
						"DHF",
						"ST000052",
						responseXML,
						request.getTransRefGUID(),
						localOrderId,
						merchantOrderId,
						MessageType.REQUEST,
						RequestProcessStatus.OK);
			}else{//校验不正确的响应报文
				responseProcessStatus = RequestProcessStatus.VALIDATE;
				responseProcessStatus.setDataName(validFailMessage);
				response = request;
				TranResponse tranResponse = new TranResponse();
				tranResponse.setFlag("0");
				tranResponse.setDesc(validFailMessage);
				response.getBusinessDatum().clear();
				response.getBusinessDatum().add(tranResponse);
				responseXML =  txFactory.getConvertMessageService()
					.marshaller(response, "ST000052", MessageType.FRONTRESPONSE.getDataENName());
			}
			if("1".equals(((TranResponse)response.getBusinessDatum().get(0)).getFlag())){//承保后只需保存业务数据和相关的报文信息
				responseProcessStatus = RequestProcessStatus.SUCCESS;
				//响应报文 => 对象
				response = (TXInsurance) txFactory.getConvertMessageService()
						.unMarshall(responseXML, "ST000052", MessageType.RESPONSE.getDataENName());
				LCCont lcCont1 = ((TranResponse)response.getBusinessDatum().get(0)).getLcCont();
				//保存数据
				InsurancePolicy policy = new InsurancePolicy();
				policy.setTransSerialNumber(request.getTransRefGUID());//交易流水号 
				policy.setMerchantTransSerialNumber(request.getTransRefGUID());//交易流水号 
				policy.setGroupChannel(request.getSaleChannel());//销售渠道 
				policy.setSellType(request.getSellType());//销售方式 
				policy.setBusinessSource(request.getSource());//信息来源 
				policy.setApplicationDate(lcCont0.getPolApplyDate());//投保日期 
				policy.setGrossPremium(lcCont0.getPrem());//总保费
				policy.setTransIdentify("ST000052");
				policy.setOrderSerialNumber(localOrderId);
				policy.setMerchantOrderNumber(merchantOrderId);//订单号
				policy.setPolicySerialNumber(lcCont1.getContNo());//保单号
				policy.setApplicationNumber(lcCont1.getProposalContNo());//投保单号 
				policy.setApplicationSerialNumber(lcCont1.getProposalContNo());
				policy.setPolicyStatus(PolicyStatus.POLICY_SUCC.getValue());
				policy.setPolicyStatusName(PolicyStatus.POLICY_SUCC.getDataName());
				policy.setPolicyStatusDesc(PolicyStatus.POLICY_SUCC.getDataName());
				OrderForm order = new OrderForm();
				order.setOrderSerialNumber(localOrderId);
				order.setMerchantOrderNumber(merchantOrderId);//订单号
				order.setTransSerialNumber(lcCont0.getPaySerialNo());//支付流水号
				order.setOrderStatus(OrderStatus.PAYMENTSUCCESS.getValue());
				order.setOrderStatusName(OrderStatus.PAYMENTSUCCESS.getDataName());
	//			order.setOrderStatusDesc();
				order.setInsurancePolicy(policy);
				policy.setOrderForm(order);
				PaymentAccount pay = new PaymentAccount();
				pay.setSecBankCode(lcCont0.getAccBankCode());//银行编码 
				pay.setSecBankAccNo(lcCont0.getBankAccNo());//银行账户
				pay.setInsurancePolicy(policy);
				policy.setPaymentAccount(pay);
				InsuranceApplicant applicant = new InsuranceApplicant();
				applicant.setFullName(lcCont0.getLcAppnt().getFullName());//投保人姓名
				applicant.setMobilePhoneNumber(lcCont0.getLcAppnt().getMobilePhoneNumber());//投保人移动电话
				applicant.setRelatedToInsured(1);//与被保人关系 (*)
				applicant.setInsurancePolicy(policy);
				policy.setInsuranceApplicant(applicant);
				policy.setInsuredNumber(lcCont0.getLcInsuredCount());//被保人数量
				for(LCInsured lcInsured : lcCont0.getLcInsureds()){
					Insured insured = new Insured();
					insured.setFullName(lcInsured.getFullName());//被保人姓名 
					insured.setMobilePhoneNumber(lcInsured.getMobilePhoneNumber());//投保人移动电话
					insured.setInsurancePolicy(policy);
					policy.getInsureds().add(insured);
					policy.setInsurancePolicyLiabilityNumber(lcInsured.getRiskCount());//险种数量
					for(Risk risk : lcInsured.getRisks()){
						InsurancePolicyLiability liability = new InsurancePolicyLiability();
						liability.setLiabilityCode(risk.getRiskCode());//险种代码
						liability.setCoreCode(risk.getRiskCode());//险种代码
						liability.setMainRiskCode(risk.getMainRiskCode());//主险险种代码 
						liability.setLiabilityName(risk.getRiskName());//险种名称 
						liability.setInsuredAmount(risk.getAmnt());//保额
						liability.setPremium(risk.getPrem());//保险费
						liability.setInsurancePolicy(policy);;
						policy.getInsurancePolicyLiabilities().add(liability);
					}
				}
				TakeATaxi taxi = new TakeATaxi();
				taxi.setCarNo(lcCont0.getCarNo());//车牌号
				taxi.setStartDate(lcCont0.getStartDate());//出发日期 
				taxi.setStartTime(lcCont0.getStartTime());//出发时间 
				taxi.setEndDate(lcCont0.getEndDate());//结束日期 
				taxi.setEndTime(lcCont0.getEndTime());//结束时间 
				taxi.setStartPlace(lcCont0.getStartPlace());//出发地
				taxi.setAcrossFlag(lcCont0.getAcrossFlag());//本地单、跨地域单标识
				taxi.setInsurancePolicy(policy);
				insurancePolicyService.addInsurancePolicy(policy);
				takeATaxiService.save(taxi);
			}
			if(isValidSucc){
				if(!"1".equals(((TranResponse)response.getBusinessDatum().get(0)).getFlag())){//本地验证通过,并且核心返回失败
					responseProcessStatus = RequestProcessStatus.FAIL;
					responseProcessStatus.setDataName(((TranResponse)response.getBusinessDatum().get(0)).getDesc());
				}else{
					responseProcessStatus = RequestProcessStatus.SUCCESS;
				}
				//保存大黄蜂响应报文
				portalTransactionService.insertAndUpdatePortalTransaction(
						"DHF",
						"ST000052",
						responseXML,
						request.getTransRefGUID(),
						localOrderId,
						merchantOrderId,
						MessageType.RESPONSE,
						responseProcessStatus);
			}
			//保存大黄蜂响应报文
			portalTransactionService.insertAndUpdatePortalTransaction(
					"DHF",
					"ST000052",
					responseXML,
					request.getTransRefGUID(),
					localOrderId,
					merchantOrderId,
					MessageType.FRONTRESPONSE,
					responseProcessStatus);
			//加密
			System.out.println(responseXML);
			responseXML= AES.Encrypt(requestXML,"ST000052ST000052");
		} catch (Exception e) {
			e.printStackTrace();
			response = request;
			TranResponse tranResponse = new TranResponse();
			tranResponse.setFlag("0");
			tranResponse.setDesc(e.getMessage());
			response.getBusinessDatum().clear();
			response.getBusinessDatum().add(tranResponse);
			responseXML =  txFactory.getConvertMessageService()
				.marshaller(response, "ST000052", MessageType.FRONTRESPONSE.getDataENName());
			//保存大黄蜂响应报文
			portalTransactionService.insertAndUpdatePortalTransaction(
					"DHF",
					"ST000052",
					responseXML,
					request.getTransRefGUID(),
					localOrderId,
					merchantOrderId,
					MessageType.FRONTRESPONSE,
					RequestProcessStatus.FAIL);
			//加密
			System.out.println(responseXML);
			try {
				responseXML= AES.Encrypt(requestXML,"ST000052ST000052");
			} catch (Exception e1) {
			}
		}
		return responseXML;
	}
	
	
	public String ST000095(String requestXML) {
		TXInsurance request = null;
		TXInsurance response = null; 
		String responseXML = null;
		try {
			//解密
			requestXML= AES.Decrypt(requestXML,"ST000095ST000095");
			System.out.println(requestXML);
			//请求报文 => 对象
			request = (TXInsurance) txFactory.getConvertMessageService()
					.unMarshall(requestXML, "ST000095", MessageType.FRONTREQUEST.getDataENName());
			//保存大黄蜂请求报文
			portalTransactionService.insertAndUpdatePortalTransaction(
					"DHF",
					"ST000095",
					requestXML,
					request.getTransRefGUID(),
					null,
					null,
					MessageType.FRONTREQUEST,
					RequestProcessStatus.OK);
				//本地 查询 核心数据库, 对账逻辑
			
				//保存核心请求报文
				portalTransactionService.insertAndUpdatePortalTransaction(
						"DHF",
						"ST000095",
						responseXML,
						request.getTransRefGUID(),
						null,
						null,
						MessageType.REQUEST,
						RequestProcessStatus.OK);
				//保存核心响应报文
				portalTransactionService.insertAndUpdatePortalTransaction(
						"DHF",
						"ST000095",
						responseXML,
						request.getTransRefGUID(),
						null,
						null,
						MessageType.RESPONSE,
						RequestProcessStatus.OK);
			//加密
			System.out.println(responseXML);
			responseXML= AES.Encrypt(requestXML,"ST000095ST000095");
			//保存大黄蜂响应报文
			portalTransactionService.insertAndUpdatePortalTransaction(
					"DHF",
					"ST000095",
					responseXML,
					request.getTransRefGUID(),
					null,
					null,
					MessageType.FRONTRESPONSE,
					RequestProcessStatus.OK);
			return responseXML;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseXML;
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
	public static String ST000052ResMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String mappingFilePath = "D:/Workspaces/workspace_sinatay/portalModule/src/main/resources/castorMappingFile/ST000052_Res_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID("201208220008");
			txInsurance.setTransType("ST000052");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo("0001");
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("DHF");
			
			TranResponse tranResponse = new TranResponse();
			tranResponse.setFlag("1");
			tranResponse.setDesc("交易成功!");
			
			LCCont lcCont = new LCCont();
			lcCont.setContNo("201208140019");
			lcCont.setProposalContNo("201208140019");
			lcCont.setPrem(new BigDecimal(10000));
			
			tranResponse.setLcCont(lcCont);
			
			List<Object> businessDatum = new ArrayList<Object>();
			businessDatum.add(tranResponse);
			txInsurance.setBusinessDatum(businessDatum);
			
			Mapping mapping = new Mapping();
			mapping.loadMapping(mappingFilePath);
			Marshaller marshaller = new Marshaller(sw);
			marshaller.setMapping(mapping);
			marshaller.setEncoding("GBK");
			marshaller.marshal(txInsurance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
}

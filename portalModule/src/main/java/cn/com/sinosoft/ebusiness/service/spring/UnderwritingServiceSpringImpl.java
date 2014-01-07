package cn.com.sinosoft.ebusiness.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.scheduling.annotation.Async;

import cn.com.sinosoft.businessModule.bankAccount.domain.PaymentAccount;
import cn.com.sinosoft.businessModule.enums.dictionary.OrderStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.PayStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.PaymentMethod;
import cn.com.sinosoft.businessModule.enums.dictionary.PolicyStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.TransType;
import cn.com.sinosoft.businessModule.insureInformBook.domain.InsureInformBook;
import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.Beneficiary;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.InsuranceApplicant;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.Insured;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicyLiability;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyService;
import cn.com.sinosoft.ebusiness.service.facade.InsuranceService;
import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.enumUtil.RequestProcessStatus;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.util.GeneratorTransSerialNumber;
import cn.com.sinosoft.portalModule.interfacePortal.xml.factory.TXInsuranceFactory;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceRuleFactorService;
import cn.com.sinosoft.portalModule.transport.service.InterfaceTransportService;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.sinatay.LCAppnt;
import cn.com.sinosoft.portalModule.transport.sinatay.LCBnf;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.portalModule.transport.sinatay.LCInsured;
import cn.com.sinosoft.portalModule.transport.sinatay.Risk;
import cn.com.sinosoft.portalModule.transport.sinatay.TellInfo;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.util.string.StringUtils;

public class UnderwritingServiceSpringImpl extends InsuranceService {

	@Autowired
	private InterfaceTransportService interfaceTransportService;
	
	@Autowired
	private InsurancePolicyService insurancePolicyService;
	
	// ����
	private TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();
	
	@Override
	protected InsuranceVerifiable handleRequest(InsuranceVerifiable ins) {
		InsuranceVerifiable ins_bak = ins;
		TXInsurance txIns = (TXInsurance) ins;
		String requestXmlToCore = createRequestOrResponseXml(ins, MessageType.REQUEST.getDataENName());

		if (interfaceTransportService == null) {
			GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
			ctx.load(new String[]{
					"classpath:/spring/applicationContext.xml"
					,"classpath:/spring/applicationContext-dataAccess.xml"
					,"classpath:/spring/applicationContext-hibernate.xml"
					,"classpath:/spring/applicationContext-interfacePortal.xml"
				});
			ctx.refresh();
			interfaceTransportService = ctx.getBean("interfaceTransportService", InterfaceTransportService.class);
		}
		// �洢�ڲ�ϵͳ�������ĵı���
		saveXmlMessage(txIns, requestXmlToCore, MessageType.REQUEST, RequestProcessStatus.OK);

		// ���ͱ��ĵ�����
		String responseXmlFromCore = "";
		try {
			responseXmlFromCore = interfaceTransportService.sendServletRequestXML(requestXmlToCore, txIns.getTransType());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// �����飬�Ӻ�����Ӧ���ķ�����
		InsuranceVerifiable insConvertFromReturn = (TXInsurance) txFactory.getConvertMessageService().unMarshall(responseXmlFromCore, txIns.getTransType(), MessageType.RESPONSE.getDataENName());

		// �������Ӧ����
		saveXmlMessage(ins, responseXmlFromCore, MessageType.RESPONSE, getRequestProcessStatus(insConvertFromReturn));

		//34�ӿڷ��سɹ��󣬵���22�ӿ�
		if(getRequestProcessStatus(insConvertFromReturn) == RequestProcessStatus.SUCCESS){
			txIns = (TXInsurance) ins_bak;
			txIns.setTransType(TransType.ST000022.getCoreValue());
			
			// �洢�ڲ�ϵͳ�������ĵı���
			saveXmlMessage(txIns, requestXmlToCore, MessageType.REQUEST, RequestProcessStatus.OK);

			// ���ͱ��ĵ�����
			responseXmlFromCore = "";
			try {
				responseXmlFromCore = interfaceTransportService.sendServletRequestXML(requestXmlToCore, txIns.getTransType());
			} catch (Exception e) {
				e.printStackTrace();
			}

			// �����飬�Ӻ�����Ӧ���ķ�����
			insConvertFromReturn = (TXInsurance) txFactory.getConvertMessageService().unMarshall(responseXmlFromCore, txIns.getTransType(), MessageType.RESPONSE.getDataENName());

			// �������Ӧ����
			saveXmlMessage(txIns, responseXmlFromCore, MessageType.RESPONSE, getRequestProcessStatus(insConvertFromReturn));
			
			TXInsurance txInsRes = (TXInsurance) insConvertFromReturn;
//			InsurancePolicy policy = new InsurancePolicy();
			createInsurancePolicy(txIns, txInsRes);
		}
		
		// ��Ӧ�ͻ���֮ǰ�����ݿ�Ĳ���δʵ��
		savaInfoBeforeResponse(insConvertFromReturn);

		return insConvertFromReturn;
	}

	@Override
	public InsuranceVerifiable savaInfoBeforeRequest(InsuranceVerifiable ins) {
		TXInsurance txIns = (TXInsurance) ins;
		txIns.setTransType(TransType.ST000034.getCoreValue());
		return txIns;
	}

	@Override
	public InsuranceVerifiable savaInfoBeforeResponse(InsuranceVerifiable ins) {
		return ins;
	}
	
	@Async
	private InsurancePolicy createInsurancePolicy(TXInsurance txIns, TXInsurance txInsRes){
		TranRequest tranReq = (TranRequest) txIns.getBusinessDatum().get(0);
		TranResponse tranRes = (TranResponse)txInsRes.getBusinessDatum().get(0);
		LCCont lcCont = null;
		LCCont resLcCont = null;
		if(tranReq != null)
			lcCont = tranReq.getLcCont();
		if(tranRes != null)
			resLcCont = tranRes.getLcCont();
		InsurancePolicy policy = new InsurancePolicy();
		if(lcCont != null && resLcCont != null){
			String localOrderId = GeneratorTransSerialNumber.generatorTransSerialNumber();
			policy.setTransSerialNumber(txIns.getTransRefGUID());// ������ˮ��
			policy.setMerchantTransSerialNumber(txIns.getTransRefGUID());// ������ˮ��
			policy.setGroupChannel(txIns.getSaleChannel());// ��������
			policy.setSellType(txIns.getSellType());// ���۷�ʽ
			policy.setBusinessSource(txIns.getSource());// ��Ϣ��Դ
			policy.setApplicationDate(lcCont.getPolApplyDate());// Ͷ������
			policy.setGrossPremium(resLcCont.getPrem());// �ܱ���
			policy.setTransIdentify(TransType.ST000034.getCoreValue());
			policy.setOrderSerialNumber(localOrderId);
			policy.setMerchantOrderNumber(lcCont.getOrderId());// ������
			policy.setApplicationNumber(resLcCont.getProposalContNo());// Ͷ������
			policy.setPolicyStatus(PolicyStatus.PROPOSAL_SUCC.getValue());
			policy.setPolicyStatusName(PolicyStatus.PROPOSAL_SUCC.getDataName());
			policy.setPolicyStatusDesc(PolicyStatus.PROPOSAL_SUCC.getDataName());
			policy.setPremium(resLcCont.getPrem());
			InsuranceApplicant insuranceApplicant = composeApplicant(lcCont.getLcAppnt(),policy);
			List<Insured> insureds = composeInsured(lcCont.getLcInsureds(),policy);
			policy.setInsuredNumber(policy.getInsureds().size());// ����������
			policy.setInsurancePolicyLiabilityNumber(policy.getInsurancePolicyLiabilities().size());
			if(resLcCont.getLcInsureds() != null && !resLcCont.getLcInsureds().isEmpty())
				composePolicyLiability(resLcCont.getLcInsureds().get(0).getRisks(), policy.getInsurancePolicyLiabilities());
			OrderForm order = new OrderForm();
			order.setOrderAmount(resLcCont.getPrem());
			order.setProductCode(policy.getProductCode());
			order.setProductName(policy.getProductName());
			order.setProductDesc(policy.getProductName());
			order.setProductNumber(policy.getUnitCount());
			order.setOrderSerialNumber(localOrderId);
			order.setMerchantOrderNumber(lcCont.getOrderId());// ������
			order.setTransSerialNumber(GeneratorTransSerialNumber.generatorTransSerialNumber());
			order.setOrderStatus(OrderStatus.UNPAID.getValue());
			order.setOrderStatusName(OrderStatus.UNPAID.getDataName());
			order.setOrderStatusDesc(OrderStatus.UNPAID.getDataName());
			order.setPaymentMethod(PaymentMethod.ALIPAY.getValue());
			order.setPayStatus(PayStatus.UNPAID.getValue());
			order.setPayStatusName(PayStatus.UNPAID.getDataName());
			order.addInsurancePolicy(policy);
			policy.setOrderForm(order);
			
			if (insurancePolicyService == null) {
				GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
				ctx.load(new String[]{
						"classpath:/spring/applicationContext.xml"
						,"classpath:/spring/applicationContext-dataAccess.xml"
						,"classpath:/spring/applicationContext-hibernate.xml"
						,"classpath:/spring/applicationContext-interfacePortal.xml"
					});
				ctx.refresh();
				insurancePolicyService = ctx.getBean("insurancePolicyService", InsurancePolicyService.class);
			}
			
			insurancePolicyService.addInsurancePolicy(policy);
		}
		
		return policy;
	}

	public InsuranceApplicant composeApplicant(LCAppnt lcAppnt, InsurancePolicy insurancePolicy){
		InsuranceApplicant applicant = new InsuranceApplicant();
		applicant.setFullName(lcAppnt.getFullName());// Ͷ��������
		if(StringUtils.isNotBlank(lcAppnt.getGender()))
			applicant.setGender(Integer.parseInt(lcAppnt.getGender()));
		applicant.setMobilePhoneNumber(lcAppnt.getMobilePhoneNumber());// Ͷ�����ƶ��绰
		applicant.setRelatedToInsuredByMerchantValue(lcAppnt.getRelaToInsured());// �뱻���˹�ϵ (*)
		applicant.setIdTypeByCoreValue(lcAppnt.getIdType());
		applicant.setIdNumber(lcAppnt.getIdNumber());
		applicant.setMobilePhoneNumber(lcAppnt.getMobilePhoneNumber());
		applicant.setPhoneNumber(lcAppnt.getPhoneNumber());
		applicant.setOfficePhoneNumber(lcAppnt.getOfficePhoneNumber());
		applicant.setEmail(lcAppnt.getEmail());
		applicant.setProvince(lcAppnt.getProvince());
		applicant.setCity(lcAppnt.getCity());
		applicant.setCounty(lcAppnt.getCounty());
		applicant.setAddressLines(lcAppnt.getHomeAddress());
		applicant.setOccupationCode(lcAppnt.getJobCode());
		applicant.setPostalCode(lcAppnt.getHomeZipCode());
		applicant.setOccupationClass(lcAppnt.getJobType());
		applicant.setMaritalStatusByCoreValue(lcAppnt.getMarriage());
		applicant.setEmployerFullName(lcAppnt.getGrpName());
//		applicant.setCitizenShip(lcAppnt.getNationality());
		applicant.setAnnualIncome(lcAppnt.getInCome());
		applicant.addInsurancePolicy(insurancePolicy);
		composeInsureInformBook(lcAppnt.getTellInfos(), insurancePolicy);
		return applicant;
	}
	
	public List<Insured> composeInsured(List<LCInsured> lcInsureds, InsurancePolicy insurancePolicy){
		List<Insured> insureds = new ArrayList<Insured>();
		if(lcInsureds!=null && !lcInsureds.isEmpty()){
			for(LCInsured lcInsured : lcInsureds){
				Insured insured = new Insured();
				insured.setFullName(lcInsured.getFullName());// Ͷ��������
				if(StringUtils.isNotBlank(lcInsured.getGender()))
					insured.setGender(Integer.parseInt(lcInsured.getGender()));
				insured.setMobilePhoneNumber(lcInsured.getMobilePhoneNumber());// Ͷ�����ƶ��绰
				insured.setRelatedToInsuredByCoreValue(lcInsured.getRelaToAppnt());// �뱻���˹�ϵ (*)
				insured.setIdTypeByCoreValue(lcInsured.getIdType());
				insured.setIdNumber(lcInsured.getIdNumber());
				insured.setMobilePhoneNumber(lcInsured.getMobilePhoneNumber());
				insured.setPhoneNumber(lcInsured.getPhoneNumber());
				insured.setOfficePhoneNumber(lcInsured.getOfficePhoneNumber());
				insured.setEmail(lcInsured.getEmail());
				insured.setProvince(lcInsured.getProvince());
				insured.setCity(lcInsured.getCity());
				insured.setCounty(lcInsured.getCounty());
				insured.setAddressLines(lcInsured.getHomeAddress());
				insured.setOccupationCode(lcInsured.getJobCode());
				insured.setPostalCode(lcInsured.getHomeZipCode());
				insured.setOccupationClass(lcInsured.getJobType());
				insured.setMaritalStatusByCoreValue(lcInsured.getMarriage());
				insured.setEmployerFullName(lcInsured.getGrpName());
//				insured.setCitizenShip(lcInsured.getNationality());
				insured.setAnnualIncome(lcInsured.getInCome());
				insured.addInsurancePolicy(insurancePolicy);
				composePolicyLiability(lcInsured.getRisks(),insured,insurancePolicy);
				insureds.add(insured);
			}
		}
		return insureds;
	}
	
	public List<Beneficiary> composeBeneficiary(List<LCBnf> lcBnfs, Insured insured, InsurancePolicy insurancePolicy){
		List<Beneficiary> beneficiaries = new ArrayList<Beneficiary>();
		if(lcBnfs!=null && !lcBnfs.isEmpty()){
			for(LCBnf lcBnf : lcBnfs){
				Beneficiary beneficiary = new Beneficiary();
				beneficiary.setBeneficiaryTypeByCoreValue(lcBnf.getBnfType());
				if(StringUtils.isNotBlank(lcBnf.getGender()))
					beneficiary.setGender(Integer.parseInt(lcBnf.getGender()));
				if(StringUtils.isNotBlank(lcBnf.getBnfGrade()))
					beneficiary.setBeneficiaryOrder(Integer.parseInt(lcBnf.getBnfGrade()));
				beneficiary.setInterestPercent(lcBnf.getBnfLot());
				beneficiary.setFullName(lcBnf.getFullName());// Ͷ��������
				beneficiary.setMobilePhoneNumber(lcBnf.getMobilePhoneNumber());// Ͷ�����ƶ��绰
				beneficiary.setRelatedToInsuredByMerchantValue(lcBnf.getRelationToInsured());// �뱻���˹�ϵ (*)
				beneficiary.setIdTypeByCoreValue(lcBnf.getIdType());
				beneficiary.setIdNumber(lcBnf.getIdNumber());
				beneficiary.setMobilePhoneNumber(lcBnf.getMobilePhoneNumber());
				beneficiary.setEmail(lcBnf.getEmail());
				beneficiary.addInsured(insured);
				beneficiary.addInsurancePolicy(insurancePolicy);
				beneficiaries.add(beneficiary);
			}
		}
		return beneficiaries;
	}
	
	public List<InsurancePolicyLiability> composePolicyLiability(List<Risk> risks, Insured insured, InsurancePolicy insurancePolicy){
		List<InsurancePolicyLiability> insurancePolicyLiabilities = new ArrayList<InsurancePolicyLiability>();
		if(risks!=null && !risks.isEmpty()){
			for(Risk risk : risks){
				InsurancePolicyLiability liability = new InsurancePolicyLiability();
				liability.setLiabilityCode(risk.getRiskCode());// ���ִ���
				liability.setCoreCode(risk.getRiskCode());// ���ִ���
				liability.setMainRiskCode(risk.getMainRiskCode());// �������ִ���
				liability.setLiabilityName(risk.getRiskName());// ��������
				liability.setInsuredAmount(risk.getAmnt());// ����
				liability.setPremium(risk.getPrem());// ���շ�
				insurancePolicy.setProductCode(risk.getRiskCode());
				insurancePolicy.setProductName(risk.getRiskName());
				insurancePolicy.setUnitCount(risk.getMult());
				liability.setUnitCount(risk.getMult());
				if(StringUtils.isNotBlank(risk.getPayIntv()))
					liability.setPaymentDuration(Integer.parseInt(risk.getPayIntv()));
				liability.setCostIntv(risk.getCostIntv());
//				liability.setCostDate(risk.getCostDate());
				liability.setBenefitPeriodTypeByCoreValue(risk.getYears());
				liability.setSpecContent(risk.getSpecContent());
				if(StringUtils.isNotBlank(risk.getPayEndYearFlag()))
					liability.setPayEndYearFlag(Integer.parseInt(risk.getPayEndYearFlag()));
				if(StringUtils.isNotBlank(risk.getPayEndYear()))
					liability.setPayEndYear(Integer.parseInt(risk.getPayEndYear()));
				if(StringUtils.isNotBlank(risk.getGetYearFlag()))
					liability.setGetYearFlag(Integer.parseInt(risk.getGetYearFlag()));
				if(StringUtils.isNotBlank(risk.getGetYear()))
					liability.setGetYear(Integer.parseInt(risk.getGetYear()));
				if(StringUtils.isNotBlank(risk.getGetIntv()))
					liability.setGetIntv(Integer.parseInt(risk.getGetIntv()));
				liability.setGetBankCode(risk.getGetBankCode());
				liability.setGetBankAccNo(risk.getGetBankAccNo());
				liability.setGetAccName(risk.getGetAccName());
				liability.setGetAccProvince(risk.getGetAccProvince());
				liability.setGetAccCity(risk.getGetAccCity());
				if(StringUtils.isNotBlank(risk.getGetAccType()))
					liability.setGetAccType(Integer.parseInt(risk.getGetAccType()));
				if(StringUtils.isNotBlank(risk.getAutoPayFlag()))
					liability.setAutoPayFlag(Integer.parseInt(risk.getAutoPayFlag()));
				if(StringUtils.isNotBlank(risk.getBonusGetMode()))
					liability.setBonusPayMode(Integer.parseInt(risk.getBonusGetMode()));
				if(StringUtils.isNotBlank(risk.getSubFlag()))
					liability.setSubFlag(Integer.parseInt(risk.getSubFlag()));
				if(StringUtils.isNotBlank(risk.getAutoRNewFlag()))
					liability.setAutoRNewFlag(Integer.parseInt(risk.getAutoRNewFlag()));
				if(StringUtils.isNotBlank(risk.getHealthFlag()))
					liability.setHealthFlag(Integer.parseInt(risk.getHealthFlag()));
				if(StringUtils.isNotBlank(risk.getFullBonusGetMode()))
					liability.setFullBonusGetMode(Integer.parseInt(risk.getFullBonusGetMode()));
				liability.setFirstRate(risk.getFirstRate());
				liability.setSureRate(risk.getSureRate());
				liability.addInsurancePolicy(insurancePolicy);
				composeBeneficiary(risk.getLcBnfs(),insured, insurancePolicy);
				insurancePolicyLiabilities.add(liability);
			}
		}
		return insurancePolicyLiabilities;
	}
	
	public List<InsurancePolicyLiability> composePolicyLiability(List<Risk> risks, List<InsurancePolicyLiability> insurancePolicyLiabilities){
		if(risks!=null && !risks.isEmpty()){
			for(Risk risk : risks){
				if(insurancePolicyLiabilities!=null && !insurancePolicyLiabilities.isEmpty()){
					for(InsurancePolicyLiability liability : insurancePolicyLiabilities){
						if(liability.getLiabilityCode().equals(risk.getRiskCode())){
							liability.setInsuredAmount(risk.getAmnt());// ����
							liability.setPremium(risk.getPrem());// ���շ�
						}
					}
				}
			}
		}
		
		return insurancePolicyLiabilities;
	}
	
	public List<InsureInformBook> composeInsureInformBook(List<TellInfo> tellInfos, InsurancePolicy insurancePolicy){
		List<InsureInformBook> insureInformBooks = new ArrayList<InsureInformBook>();
		if(tellInfos!=null && !tellInfos.isEmpty()){
			for(TellInfo tellInfo : tellInfos){
				InsureInformBook insureInformBook = new InsureInformBook();
				insureInformBook.setTellVersion(tellInfo.getTellVersion());
				insureInformBook.setInformCode(tellInfo.getTellCode());
				insureInformBook.setInformContent(tellInfo.getTellContent());
				insureInformBook.setTellRemark(tellInfo.getTellRemark());
				insureInformBook.addInsurancePolicy(insurancePolicy);
				insureInformBooks.add(insureInformBook);
			}
		}
		return insureInformBooks;
	}
	
}

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
			//����
			requestXML= AES.Decrypt(requestXML,"ST000025ST000025");

			System.out.println(requestXML);
			//������ => ����
			request = (TXInsurance) txFactory.getConvertMessageService()
					.unMarshall(requestXML, "ST000025", MessageType.FRONTREQUEST.getDataENName());
			//�����Ʒ�������
			portalTransactionService.insertAndUpdatePortalTransaction(
					"DHF",
					"ST000052",
					requestXML,
					request.getTransRefGUID(),
					null,
					null,
					MessageType.FRONTREQUEST,
					RequestProcessStatus.OK);
				//���� <=> ����
				responseXML = interfaceTransportService.sendServletRequestXML(requestXML, "ST000025");
				//�������������
				portalTransactionService.insertAndUpdatePortalTransaction(
						"DHF",
						"ST000025",
						responseXML,
						request.getTransRefGUID(),
						null,
						null,
						MessageType.REQUEST,
						RequestProcessStatus.OK);
				//���������Ӧ����
				portalTransactionService.insertAndUpdatePortalTransaction(
						"DHF",
						"ST000025",
						responseXML,
						request.getTransRefGUID(),
						null,
						null,
						MessageType.RESPONSE,
						RequestProcessStatus.OK);
			//����
			System.out.println(responseXML);
			responseXML= AES.Decrypt(requestXML,"ST000025ST000025");
			//�����Ʒ���Ӧ����
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
			//����
			requestXML= AES.Decrypt(requestXML,"ST000052ST000052");
			System.out.println(requestXML);
			//������ => ����
			request = (TXInsurance) txFactory.getConvertMessageService()
					.unMarshall(requestXML, "ST000052", MessageType.FRONTREQUEST.getDataENName());
			LCCont lcCont0 = ((TranRequest)request.getBusinessDatum().get(0)).getLcCont();
			merchantOrderId =lcCont0.getOrderId();
			//�����Ʒ�������
			portalTransactionService.insertAndUpdatePortalTransaction(
					"DHF",
					"ST000052",
					requestXML,
					request.getTransRefGUID(),
					localOrderId,
					merchantOrderId,
					MessageType.FRONTREQUEST,
					RequestProcessStatus.OK);
			//�ֶ�У��
			boolean isValidSucc = true;
			String validFailMessage = "";
			if(geFunctionSwitchPortalService.isSwitchOpen("verificationMobilePhoneNumber","DHF")){
				String mobileNumberRegx = "^13[0-9]{9}|15[0-9]{9}|18[0-9]{9}|147[0-9]{8}$";
				if(!lcCont0.getLcAppnt().getMobilePhoneNumber().matches(mobileNumberRegx)){
					isValidSucc = false;
					validFailMessage += "Ͷ�����ֻ����벻��ȷ��";
				}
				for(LCInsured lcInsured : lcCont0.getLcInsureds()){
					if(!lcInsured.getMobilePhoneNumber().matches(mobileNumberRegx)){
						isValidSucc = false;
						validFailMessage += "�������ֻ����벻��ȷ��";
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
					validFailMessage += "����ʱ���ʽ����ȷ��";
				}
			}
			if(isValidSucc){//У��ͨ��,������Ľ����б�
				//���� <=> ����
				responseXML = interfaceTransportService.sendServletRequestXML(requestXML, "ST000052");
				//responseXML = ST000052ResMarshller();
				//�������������
				portalTransactionService.insertAndUpdatePortalTransaction(
						"DHF",
						"ST000052",
						responseXML,
						request.getTransRefGUID(),
						localOrderId,
						merchantOrderId,
						MessageType.REQUEST,
						RequestProcessStatus.OK);
			}else{//У�鲻��ȷ����Ӧ����
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
			if("1".equals(((TranResponse)response.getBusinessDatum().get(0)).getFlag())){//�б���ֻ�豣��ҵ�����ݺ���صı�����Ϣ
				responseProcessStatus = RequestProcessStatus.SUCCESS;
				//��Ӧ���� => ����
				response = (TXInsurance) txFactory.getConvertMessageService()
						.unMarshall(responseXML, "ST000052", MessageType.RESPONSE.getDataENName());
				LCCont lcCont1 = ((TranResponse)response.getBusinessDatum().get(0)).getLcCont();
				//��������
				InsurancePolicy policy = new InsurancePolicy();
				policy.setTransSerialNumber(request.getTransRefGUID());//������ˮ�� 
				policy.setMerchantTransSerialNumber(request.getTransRefGUID());//������ˮ�� 
				policy.setGroupChannel(request.getSaleChannel());//�������� 
				policy.setSellType(request.getSellType());//���۷�ʽ 
				policy.setBusinessSource(request.getSource());//��Ϣ��Դ 
				policy.setApplicationDate(lcCont0.getPolApplyDate());//Ͷ������ 
				policy.setGrossPremium(lcCont0.getPrem());//�ܱ���
				policy.setTransIdentify("ST000052");
				policy.setOrderSerialNumber(localOrderId);
				policy.setMerchantOrderNumber(merchantOrderId);//������
				policy.setPolicySerialNumber(lcCont1.getContNo());//������
				policy.setApplicationNumber(lcCont1.getProposalContNo());//Ͷ������ 
				policy.setApplicationSerialNumber(lcCont1.getProposalContNo());
				policy.setPolicyStatus(PolicyStatus.POLICY_SUCC.getValue());
				policy.setPolicyStatusName(PolicyStatus.POLICY_SUCC.getDataName());
				policy.setPolicyStatusDesc(PolicyStatus.POLICY_SUCC.getDataName());
				OrderForm order = new OrderForm();
				order.setOrderSerialNumber(localOrderId);
				order.setMerchantOrderNumber(merchantOrderId);//������
				order.setTransSerialNumber(lcCont0.getPaySerialNo());//֧����ˮ��
				order.setOrderStatus(OrderStatus.PAYMENTSUCCESS.getValue());
				order.setOrderStatusName(OrderStatus.PAYMENTSUCCESS.getDataName());
	//			order.setOrderStatusDesc();
				order.setInsurancePolicy(policy);
				policy.setOrderForm(order);
				PaymentAccount pay = new PaymentAccount();
				pay.setSecBankCode(lcCont0.getAccBankCode());//���б��� 
				pay.setSecBankAccNo(lcCont0.getBankAccNo());//�����˻�
				pay.setInsurancePolicy(policy);
				policy.setPaymentAccount(pay);
				InsuranceApplicant applicant = new InsuranceApplicant();
				applicant.setFullName(lcCont0.getLcAppnt().getFullName());//Ͷ��������
				applicant.setMobilePhoneNumber(lcCont0.getLcAppnt().getMobilePhoneNumber());//Ͷ�����ƶ��绰
				applicant.setRelatedToInsured(1);//�뱻���˹�ϵ (*)
				applicant.setInsurancePolicy(policy);
				policy.setInsuranceApplicant(applicant);
				policy.setInsuredNumber(lcCont0.getLcInsuredCount());//����������
				for(LCInsured lcInsured : lcCont0.getLcInsureds()){
					Insured insured = new Insured();
					insured.setFullName(lcInsured.getFullName());//���������� 
					insured.setMobilePhoneNumber(lcInsured.getMobilePhoneNumber());//Ͷ�����ƶ��绰
					insured.setInsurancePolicy(policy);
					policy.getInsureds().add(insured);
					policy.setInsurancePolicyLiabilityNumber(lcInsured.getRiskCount());//��������
					for(Risk risk : lcInsured.getRisks()){
						InsurancePolicyLiability liability = new InsurancePolicyLiability();
						liability.setLiabilityCode(risk.getRiskCode());//���ִ���
						liability.setCoreCode(risk.getRiskCode());//���ִ���
						liability.setMainRiskCode(risk.getMainRiskCode());//�������ִ��� 
						liability.setLiabilityName(risk.getRiskName());//�������� 
						liability.setInsuredAmount(risk.getAmnt());//����
						liability.setPremium(risk.getPrem());//���շ�
						liability.setInsurancePolicy(policy);;
						policy.getInsurancePolicyLiabilities().add(liability);
					}
				}
				TakeATaxi taxi = new TakeATaxi();
				taxi.setCarNo(lcCont0.getCarNo());//���ƺ�
				taxi.setStartDate(lcCont0.getStartDate());//�������� 
				taxi.setStartTime(lcCont0.getStartTime());//����ʱ�� 
				taxi.setEndDate(lcCont0.getEndDate());//�������� 
				taxi.setEndTime(lcCont0.getEndTime());//����ʱ�� 
				taxi.setStartPlace(lcCont0.getStartPlace());//������
				taxi.setAcrossFlag(lcCont0.getAcrossFlag());//���ص�������򵥱�ʶ
				taxi.setInsurancePolicy(policy);
				insurancePolicyService.addInsurancePolicy(policy);
				takeATaxiService.save(taxi);
			}
			if(isValidSucc){
				if(!"1".equals(((TranResponse)response.getBusinessDatum().get(0)).getFlag())){//������֤ͨ��,���Һ��ķ���ʧ��
					responseProcessStatus = RequestProcessStatus.FAIL;
					responseProcessStatus.setDataName(((TranResponse)response.getBusinessDatum().get(0)).getDesc());
				}else{
					responseProcessStatus = RequestProcessStatus.SUCCESS;
				}
				//�����Ʒ���Ӧ����
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
			//�����Ʒ���Ӧ����
			portalTransactionService.insertAndUpdatePortalTransaction(
					"DHF",
					"ST000052",
					responseXML,
					request.getTransRefGUID(),
					localOrderId,
					merchantOrderId,
					MessageType.FRONTRESPONSE,
					responseProcessStatus);
			//����
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
			//�����Ʒ���Ӧ����
			portalTransactionService.insertAndUpdatePortalTransaction(
					"DHF",
					"ST000052",
					responseXML,
					request.getTransRefGUID(),
					localOrderId,
					merchantOrderId,
					MessageType.FRONTRESPONSE,
					RequestProcessStatus.FAIL);
			//����
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
			//����
			requestXML= AES.Decrypt(requestXML,"ST000095ST000095");
			System.out.println(requestXML);
			//������ => ����
			request = (TXInsurance) txFactory.getConvertMessageService()
					.unMarshall(requestXML, "ST000095", MessageType.FRONTREQUEST.getDataENName());
			//�����Ʒ�������
			portalTransactionService.insertAndUpdatePortalTransaction(
					"DHF",
					"ST000095",
					requestXML,
					request.getTransRefGUID(),
					null,
					null,
					MessageType.FRONTREQUEST,
					RequestProcessStatus.OK);
				//���� ��ѯ �������ݿ�, �����߼�
			
				//�������������
				portalTransactionService.insertAndUpdatePortalTransaction(
						"DHF",
						"ST000095",
						responseXML,
						request.getTransRefGUID(),
						null,
						null,
						MessageType.REQUEST,
						RequestProcessStatus.OK);
				//���������Ӧ����
				portalTransactionService.insertAndUpdatePortalTransaction(
						"DHF",
						"ST000095",
						responseXML,
						request.getTransRefGUID(),
						null,
						null,
						MessageType.RESPONSE,
						RequestProcessStatus.OK);
			//����
			System.out.println(responseXML);
			responseXML= AES.Encrypt(requestXML,"ST000095ST000095");
			//�����Ʒ���Ӧ����
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
			tranResponse.setDesc("���׳ɹ�!");
			
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

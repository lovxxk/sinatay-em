package cn.com.sinosoft.portalModule.transport.abstractService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.log.ImplTraced;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.portalModuleException;
import cn.com.sinosoft.portalModule.enumUtil.EncapsulationType;
import cn.com.sinosoft.portalModule.enumUtil.InterfaceStatus;
import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.enumUtil.UserStatus;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.util.GeneratorTransSerialNumber;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceAccount;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceSystem;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalService;
import cn.com.sinosoft.portalModule.transport.transactionObject.BusinessResultDatum;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsuranceExtension;
import cn.com.sinosoft.portalModule.transport.transactionObject.TransMessage;
import cn.com.sinosoft.util.time.DateUtils;

public class AbstractInterfaceTransportService implements PortalService {
	
	@Autowired
	protected PortalInterfaceService portalInterfaceService;
	
	/***
	 * 
	 * ��ȡ�ӿ���Ϣ
	 * @param transCode ���״���
	 * @return �ӿ���Ϣ
	 * @throws portalModuleException
	 */
	@ImplTraced
	public PortalInterface findPortalInterfaceByTransCode(String transCode) throws portalModuleException {
		return findPortalInterface(transCode, null);
	}
	
	/**
	 * ��ȡ�ӿ���Ϣ
	 * @param transCode ���״���
	 * @param loginName �˺���
	 * @return
	 * @throws portalModuleException
	 */
	@ImplTraced
	public PortalInterface findPortalInterface(String transCode, String loginName) throws portalModuleException {
		PortalInterface portalInterface = portalInterfaceService.findPortalInterfaceByTransCode(transCode);
		if (portalInterface != null) {
			// �жϽӿ�״̬�Ƿ����
			if (InterfaceStatus.ENABLED.equals(portalInterface.getStatus())) {
				List<PortalInterfaceAccount> portalInterfaceAccounts = portalInterface.getPortalInterfaceAccounts();
				for (PortalInterfaceAccount portalInterfaceAccount:portalInterfaceAccounts) {
					if (StringUtils.isNotBlank(loginName)) {
						if (loginName.equalsIgnoreCase(portalInterfaceAccount.getLoginName())) {
							if (UserStatus.ENABLED.getValue().equals(portalInterfaceAccount.getStatus())) {
								return portalInterface;
							} else {
								throw portalModuleException.newInstanceMsg(portalInterfaceAccount.getLoginName()
										+ "�û�����Ӧ�Ľӿ�" + portalInterface.getTransName()
										+ "δ���ã�");
							}
						}
					}
				}
				return portalInterface;
			} else {
				throw portalModuleException.newInstanceMsg(portalInterface.getTransName() + "�ӿ�δ���ã�");
			}

		} 
		return null;
	}
	
	/**
	 * ��װ���Ķ���
	 * @param portalInterface
	 * @param businessDatum
	 * @param transMessage
	 * @param transDate
	 */
	public void marshallObject(PortalInterface portalInterface, Object businessObject, TransMessage transMessage, Date transDate) {
		List<Object> businessDatum = new ArrayList<Object>();
		businessDatum.add(businessObject);
		marshallObject(portalInterface, businessDatum, transMessage, transDate, MessageType.REQUEST);
	}
	
	/**
	 * ��װ���Ķ���
	 * @param portalInterface
	 * @param businessDatum
	 * @param transMessage
	 * @param transDate
	 */
	public void marshallObject(PortalInterface portalInterface, Object businessObject, TXInsurance txInsurance, Date transDate) {
		List<Object> businessDatum = new ArrayList<Object>();
		businessDatum.add(businessObject);
		marshallObject(portalInterface, businessDatum, txInsurance, transDate, MessageType.REQUEST);
	}
	
	/**
	 * ��װ���Ķ���
	 * @param portalInterface
	 * @param businessDatum
	 * @param transMessage
	 * @param transDate
	 */
	public void marshallObject(PortalInterface portalInterface, Object businessObject, TransMessage transMessage, Date transDate, MessageType messageType) {
		List<Object> businessDatum = new ArrayList<Object>();
		businessDatum.add(businessObject);
		marshallObject(portalInterface, businessDatum, transMessage, transDate, messageType);
	}
	
	/**
	 * ��װ���Ķ���
	 * @param portalInterface �ӿ���Ϣ����
	 * @param businessDatum ���ҵ�����
	 * @param transMessage ������Ϣ
	 * @param transDate ����ʱ��
	 */
	public void marshallObject(PortalInterface portalInterface, List<Object> businessDatum, TransMessage transMessage, Date transDate) {
		marshallObject(portalInterface, businessDatum, transMessage, transDate, MessageType.REQUEST);
	}
	
	/**
	 * ��װ���Ķ���
	 * @param portalInterface �ӿ���Ϣ����
	 * @param businessDatum ���ҵ�����
	 * @param transMessage ������Ϣ
	 * @param transDate ����ʱ��
	 */
	public void marshallObject(PortalInterface portalInterface, List<Object> businessDatum, TransMessage transMessage, Date transDate, MessageType messageType) {
		if (EncapsulationType.ALL_ENCAPSULATION.getValue().equals(portalInterface.getEncapsulationType()) ||
				EncapsulationType.HEADER_ENCAPSULATION.getValue().equals(portalInterface.getEncapsulationType())) {
			marshallHeader(portalInterface, businessDatum, transMessage, transDate, messageType);
		}
		
		if (EncapsulationType.ALL_ENCAPSULATION.getValue().equals(portalInterface.getEncapsulationType()) ||
				EncapsulationType.SYSTEM_ENCAPSULATION.getValue().equals(portalInterface.getEncapsulationType())) {
			marshallTXInsuranceExtension(portalInterface, transMessage);
		}
	}
	
	/**
	 * ��װ���Ķ���
	 * @param portalInterface �ӿ���Ϣ����
	 * @param businessDatum ���ҵ�����
	 * @param transMessage ������Ϣ
	 * @param transDate ����ʱ��
	 */
	public void marshallObject(PortalInterface portalInterface, List<Object> businessDatum, TXInsurance txInsurance, Date transDate, MessageType messageType) {
		if (EncapsulationType.ALL_ENCAPSULATION.getValue().equals(portalInterface.getEncapsulationType()) ||
				EncapsulationType.HEADER_ENCAPSULATION.getValue().equals(portalInterface.getEncapsulationType())) {
			marshallHeader(portalInterface, businessDatum, txInsurance, transDate, messageType);
		}
		
	}
	
	/**
	 * ��װ����ͷ����
	 * @param portalInterface �ӿ���Ϣ����
	 * @param businessDatum ���ҵ�����
	 * @param transMessage ������Ϣ
	 * @param transDate ����ʱ��
	 */
	public void marshallHeader(PortalInterface portalInterface, List<Object> businessDatum, TransMessage transMessage, Date transDate, MessageType messageType) {
		TXInsurance txInsurance = new TXInsurance();
		txInsurance.setTransRefGUID(GeneratorTransSerialNumber.generatorTransSerialNumber());
		txInsurance.setTransType(portalInterface.getTransCode());
		txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
		txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
		txInsurance.setSaleChannel("W");
		txInsurance.setSellType("20");
		txInsurance.setSource("WEB_PERSON");
		if (MessageType.REQUEST.getValue().equals(messageType.getValue()) || MessageType.FRONTREQUEST.getValue().equals(messageType.getValue())) {
			txInsurance.setBusinessDatum(businessDatum);
		} else  {
			List<BusinessResultDatum> businessResultDatum= new ArrayList<BusinessResultDatum>();
			for (int i = 0; i < businessDatum.size(); i++) {
				Object businessResult = businessDatum.get(i);
				if (businessResult instanceof BusinessResultDatum) {
					businessResultDatum.add((BusinessResultDatum) businessResult);
				}
				
			}
			
			txInsurance.setBusinessResultDatum(businessResultDatum);
		}
		
		transMessage.setInsurance(txInsurance);
	}
	
	/**
	 * ��װ����ͷ����
	 * @param portalInterface �ӿ���Ϣ����
	 * @param businessDatum ���ҵ�����
	 * @param transMessage ������Ϣ
	 * @param transDate ����ʱ��
	 */
	public void marshallHeader(PortalInterface portalInterface, List<Object> businessDatum, TXInsurance txInsurance, Date transDate, MessageType messageType) {
		txInsurance.setTransRefGUID(GeneratorTransSerialNumber.generatorTransSerialNumber());
		txInsurance.setTransType(portalInterface.getTransCode());
		txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
		txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
		txInsurance.setSaleChannel("W");
		txInsurance.setSellType("20");
		String source = "WEB_PERSON";
//		if("ST000034".equals(portalInterface.getTransCode()) || "ST000022".equals(portalInterface.getTransCode()))
//			source = "TAOBAO";
		txInsurance.setSource(source);
		if (MessageType.REQUEST.getValue().equals(messageType.getValue()) || MessageType.FRONTREQUEST.getValue().equals(messageType.getValue())) {
			txInsurance.setBusinessDatum(businessDatum);
		} else  {
			List<BusinessResultDatum> businessResultDatum = new ArrayList<BusinessResultDatum>();
			for (int i = 0; i < businessDatum.size(); i++) {
				Object businessResult = businessDatum.get(i);
				if (businessResult instanceof BusinessResultDatum) {
					businessResultDatum.add((BusinessResultDatum) businessResult);
				}
				
			}
			
			txInsurance.setBusinessResultDatum(businessResultDatum);
		}
		
	}
	
	/**
	 * ��װ����ͷ����
	 * @param portalInterface �ӿ���Ϣ����
	 * @param businessDatum ���ҵ�����
	 * @param transMessage ������Ϣ
	 * @param transDate ����ʱ��
	 */
	public void marshallTXInsuranceExtension(PortalInterface portalInterface, TransMessage transMessage) {
		TXInsuranceExtension insuranceExtension = new TXInsuranceExtension();
		PortalInterfaceSystem portalInterfaceSystem = portalInterface.getPortalInterfaceSystem();
		if (EncapsulationType.ALL_ENCAPSULATION.getValue().equals(portalInterface.getEncapsulationType()) ||
				EncapsulationType.SYSTEM_ENCAPSULATION.getValue().equals(portalInterface.getEncapsulationType())) {
			if (portalInterfaceSystem != null) {
				insuranceExtension.setSystemCode(portalInterfaceSystem.getSystemCode());
				insuranceExtension.setSystemName(portalInterfaceSystem.getSystemName());
			}
		}
		transMessage.setInsuranceExtension(insuranceExtension);
	}
	
	
}

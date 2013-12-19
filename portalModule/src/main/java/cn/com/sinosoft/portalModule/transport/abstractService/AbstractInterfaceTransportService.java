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
	 * 获取接口信息
	 * @param transCode 交易代码
	 * @return 接口信息
	 * @throws portalModuleException
	 */
	@ImplTraced
	public PortalInterface findPortalInterfaceByTransCode(String transCode) throws portalModuleException {
		return findPortalInterface(transCode, null);
	}
	
	/**
	 * 获取接口信息
	 * @param transCode 交易代码
	 * @param loginName 账号名
	 * @return
	 * @throws portalModuleException
	 */
	@ImplTraced
	public PortalInterface findPortalInterface(String transCode, String loginName) throws portalModuleException {
		PortalInterface portalInterface = portalInterfaceService.findPortalInterfaceByTransCode(transCode);
		if (portalInterface != null) {
			// 判断接口状态是否可用
			if (InterfaceStatus.ENABLED.equals(portalInterface.getStatus())) {
				List<PortalInterfaceAccount> portalInterfaceAccounts = portalInterface.getPortalInterfaceAccounts();
				for (PortalInterfaceAccount portalInterfaceAccount:portalInterfaceAccounts) {
					if (StringUtils.isNotBlank(loginName)) {
						if (loginName.equalsIgnoreCase(portalInterfaceAccount.getLoginName())) {
							if (UserStatus.ENABLED.getValue().equals(portalInterfaceAccount.getStatus())) {
								return portalInterface;
							} else {
								throw portalModuleException.newInstanceMsg(portalInterfaceAccount.getLoginName()
										+ "用户所对应的接口" + portalInterface.getTransName()
										+ "未启用！");
							}
						}
					}
				}
				return portalInterface;
			} else {
				throw portalModuleException.newInstanceMsg(portalInterface.getTransName() + "接口未启用！");
			}

		} 
		return null;
	}
	
	/**
	 * 封装报文对象
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
	 * 封装报文对象
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
	 * 封装报文对象
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
	 * 封装报文对象
	 * @param portalInterface 接口信息对象
	 * @param businessDatum 多个业务对象
	 * @param transMessage 交易信息
	 * @param transDate 交易时间
	 */
	public void marshallObject(PortalInterface portalInterface, List<Object> businessDatum, TransMessage transMessage, Date transDate) {
		marshallObject(portalInterface, businessDatum, transMessage, transDate, MessageType.REQUEST);
	}
	
	/**
	 * 封装报文对象
	 * @param portalInterface 接口信息对象
	 * @param businessDatum 多个业务对象
	 * @param transMessage 交易信息
	 * @param transDate 交易时间
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
	 * 封装报文对象
	 * @param portalInterface 接口信息对象
	 * @param businessDatum 多个业务对象
	 * @param transMessage 交易信息
	 * @param transDate 交易时间
	 */
	public void marshallObject(PortalInterface portalInterface, List<Object> businessDatum, TXInsurance txInsurance, Date transDate, MessageType messageType) {
		if (EncapsulationType.ALL_ENCAPSULATION.getValue().equals(portalInterface.getEncapsulationType()) ||
				EncapsulationType.HEADER_ENCAPSULATION.getValue().equals(portalInterface.getEncapsulationType())) {
			marshallHeader(portalInterface, businessDatum, txInsurance, transDate, messageType);
		}
		
	}
	
	/**
	 * 封装报文头对象
	 * @param portalInterface 接口信息对象
	 * @param businessDatum 多个业务对象
	 * @param transMessage 交易信息
	 * @param transDate 交易时间
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
	 * 封装报文头对象
	 * @param portalInterface 接口信息对象
	 * @param businessDatum 多个业务对象
	 * @param transMessage 交易信息
	 * @param transDate 交易时间
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
	 * 封装报文头对象
	 * @param portalInterface 接口信息对象
	 * @param businessDatum 多个业务对象
	 * @param transMessage 交易信息
	 * @param transDate 交易时间
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

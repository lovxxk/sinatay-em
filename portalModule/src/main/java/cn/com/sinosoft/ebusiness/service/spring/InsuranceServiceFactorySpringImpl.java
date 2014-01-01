package cn.com.sinosoft.ebusiness.service.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.service.facade.InsuranceService;
import cn.com.sinosoft.ebusiness.service.facade.InsuranceServiceFactory;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceRuleFactorService;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

/**
 * @author sizhou
 *	�˴�ʡ�Խӿ�,�˴��Լ�ʵ�ֵ��岻��spring context����
 */
public class InsuranceServiceFactorySpringImpl implements InsuranceServiceFactory{
	
	@Autowired
	private PortalInterfaceRuleFactorService portalInterfaceRuleFactorService;
	
	private static InsuranceServiceFactorySpringImpl factory = new InsuranceServiceFactorySpringImpl();
	
	private InsuranceServiceFactorySpringImpl(){
		
	}
	public InsuranceService getInsuranceService(InsuranceVerifiable ins) throws Exception{
		TXInsurance txIns = (TXInsurance) ins;
		//����ins��function�����ݿ�ȡ�ö�Ӧ�Ĵ�����
		//String handleClz = "�����ݿ�ȡ�ö�Ӧ�Ĵ�����";
		
	    List<String> handleClazzs = portalInterfaceRuleFactorService.findPortalInterfaceRuleFactorProcessClass(txIns.getTransType(),txIns.getSource());
	    
		InsuranceService is = (InsuranceService) Class.forName(handleClazzs.get(0)).newInstance();
		return is;
	}
	public static InsuranceServiceFactorySpringImpl getInstance(){
		return factory;
	};
}

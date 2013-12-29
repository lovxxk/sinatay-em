package cn.com.sinosoft.ebusiness.service.spring;

import cn.com.sinosoft.ebusiness.service.facade.InsuranceService;
import cn.com.sinosoft.ebusiness.service.facade.InsuranceServiceFactory;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;

/**
 * @author sizhou
 *	�˴�ʡ�Խӿ�,�˴��Լ�ʵ�ֵ��岻��spring context����
 */
public class InsuranceServiceFactorySpringImpl implements InsuranceServiceFactory{
	private static InsuranceServiceFactorySpringImpl factory = new InsuranceServiceFactorySpringImpl();
	private InsuranceServiceFactorySpringImpl(){
		
	}
	public InsuranceService getInsuranceService(InsuranceVerifiable ins) throws Exception{
		//����ins��function�����ݿ�ȡ�ö�Ӧ�Ĵ�����
		String handleClz = "�����ݿ�ȡ�ö�Ӧ�Ĵ�����";
		InsuranceService is = (InsuranceService) Class.forName(handleClz).newInstance();
		return is;
	}
	public static InsuranceServiceFactorySpringImpl getInstance(){
		return factory;
	};
}

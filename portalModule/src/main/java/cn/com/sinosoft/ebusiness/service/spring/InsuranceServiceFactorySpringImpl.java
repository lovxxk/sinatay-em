package cn.com.sinosoft.ebusiness.service.spring;

import cn.com.sinosoft.ebusiness.service.facade.InsuranceService;
import cn.com.sinosoft.ebusiness.service.facade.InsuranceServiceFactory;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;

/**
 * @author sizhou
 *	此处省略接口,此处自己实现单体不由spring context负责
 */
public class InsuranceServiceFactorySpringImpl implements InsuranceServiceFactory{
	private static InsuranceServiceFactorySpringImpl factory = new InsuranceServiceFactorySpringImpl();
	private InsuranceServiceFactorySpringImpl(){
		
	}
	public InsuranceService getInsuranceService(InsuranceVerifiable ins) throws Exception{
		//根据ins的function从数据库取得对应的处理类
		String handleClz = "从数据库取得对应的处理类";
		InsuranceService is = (InsuranceService) Class.forName(handleClz).newInstance();
		return is;
	}
	public static InsuranceServiceFactorySpringImpl getInstance(){
		return factory;
	};
}

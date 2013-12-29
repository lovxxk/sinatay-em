package cn.com.sinosoft.portalModule.transport.sinatay;

/**
 * @author sizhou
 *	此处省略接口,此处自己实现单体不由spring context负责
 */
public class InsuranceServiceFactory {
	private static InsuranceServiceFactory factory = new InsuranceServiceFactory();
	private InsuranceServiceFactory(){
		
	}
	public InsuranceService getInsuranceService(InsuranceVerifiable ins) throws Exception{
		//根据ins的function从数据库取得对应的处理类
		String handleClz = "从数据库取得对应的处理类";
		InsuranceService is = (InsuranceService) Class.forName(handleClz).newInstance();
		return is;
	}
	public static InsuranceServiceFactory getInstance(){
		return factory;
	};
}

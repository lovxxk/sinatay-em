package cn.com.sinosoft.portalModule.transport.sinatay;

/**
 * @author sizhou
 *	�˴�ʡ�Խӿ�,�˴��Լ�ʵ�ֵ��岻��spring context����
 */
public class InsuranceServiceFactory {
	private static InsuranceServiceFactory factory = new InsuranceServiceFactory();
	private InsuranceServiceFactory(){
		
	}
	public InsuranceService getInsuranceService(InsuranceVerifiable ins) throws Exception{
		//����ins��function�����ݿ�ȡ�ö�Ӧ�Ĵ�����
		String handleClz = "�����ݿ�ȡ�ö�Ӧ�Ĵ�����";
		InsuranceService is = (InsuranceService) Class.forName(handleClz).newInstance();
		return is;
	}
	public static InsuranceServiceFactory getInstance(){
		return factory;
	};
}

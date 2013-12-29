package cn.com.sinosoft.portalModule.transport.sinatay.insvalidator;

public class InsuranceValidatorFactory {
	private static InsuranceValidatorFactory factory = new InsuranceValidatorFactory();
	private InsuranceValidatorFactory(){
		
	}
	public InsuranceValidator getInsuranceValidator(String type) throws Exception{
		
		InsuranceValidator validator = (InsuranceValidator) Class.forName(type).newInstance();
		return validator;
	}
	public static InsuranceValidatorFactory getInstance(){
		return factory;
	};
}

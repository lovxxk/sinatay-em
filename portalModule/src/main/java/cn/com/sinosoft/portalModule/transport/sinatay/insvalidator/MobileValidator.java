package cn.com.sinosoft.portalModule.transport.sinatay.insvalidator;

import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

public class MobileValidator implements InsuranceValidator {
	
	private static final String MOBILE_REG = "^13[0-9]{9}|15[0-9]{9}|18[0-9]{9}|147[0-9]{8}$";
	@Override
	public ValidationContext validate(Object ctx) throws Exception {
		ValidationContext context = (ValidationContext)ctx;
		InsuranceVerifiable ins = (TXInsurance)(context.getRelatedObj());
	}

}

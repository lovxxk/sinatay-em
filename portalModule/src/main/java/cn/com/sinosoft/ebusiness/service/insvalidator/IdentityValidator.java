package cn.com.sinosoft.ebusiness.service.insvalidator;


public class IdentityValidator implements InsuranceValidator {

	@Override
	public ValidationContext validate(Object ctx) {
		// TODO Auto-generated method stub;
		//InsuranceVerifiable ins = new Insurance();
		ValidationResult result = new ValidationResult("1","Suc"); 
		((ValidationContext)ctx).getValidationResults().push(result);
		return (ValidationContext)ctx;
	}

}

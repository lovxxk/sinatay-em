package cn.com.sinosoft.ebusiness.service.insvalidator;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ValidationContext{

	// 待校验对象可能是投保相关对象或者xml报文
	private Object relatedObj;

	private List<InsuranceValidator> validatorChain = new LinkedList<InsuranceValidator>();
	
	private Stack<ValidationResult> validationResults = new Stack<ValidationResult>();

	public ValidationContext() {
		super();
	}
	
	public ValidationContext(Object relatedObj,
			List<InsuranceValidator> validatorChain,
			Stack<ValidationResult> validationResults) {
		super();
		this.relatedObj = relatedObj;
		this.validatorChain = validatorChain;
		this.validationResults = validationResults;
	}

	public Object getRelatedObj() {
		return relatedObj;
	}

	public void setRelatedObj(Object relatedObj) {
		this.relatedObj = relatedObj;
	}

	public List<InsuranceValidator> getValidatorChain() {
		return validatorChain;
	}

	public void setValidatorChain(List<InsuranceValidator> validatorChain) {
		this.validatorChain = validatorChain;
	}

	public Stack<ValidationResult> getValidationResults() {
		return validationResults;
	}

	public void setValidationResults(Stack<ValidationResult> validationResults) {
		this.validationResults = validationResults;
	}
}

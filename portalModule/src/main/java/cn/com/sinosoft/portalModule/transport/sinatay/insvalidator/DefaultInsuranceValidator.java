package cn.com.sinosoft.portalModule.transport.sinatay.insvalidator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

/**
 * @author sizhou
 * Ͷ��������Ĭ�ϵĹ���У������ʹ��ģ�巽��
 */
public class DefaultInsuranceValidator implements InsuranceValidator {

	/* (non-Javadoc)
	 * @see insuretest.InsuranceValidation#validate(java.lang.Object)
	 * ����һ��У�����������У���������ݿ��ѯ���˽ӿڷ����䵱ģ�巽��
	 */
	
	@Override
	public ValidationContext validate(Object msg) throws Exception {
		
		//��ǰУ��������
		ValidationContext context = createValidationContext((String)msg);
		//Ĭ��У��������Ҫ֪��У��������Ҫ��ʲôУ����У���Լ��ĸ�����У���
		for(InsuranceValidator validator : context.getValidatorChain()){
			context = validator.validate(context);
			//��·У����߷Ƕ�·У��
			if(context.getValidationResults().peek().isSuccess()){
				break;
			}
		}
		return context;
	}
	
	public ValidationContext createValidationContext(String msg) throws Exception{
		//����xmlȡ��functionflag
		String functionFlag = "ST000021";
		
		//����xml�õ�����֤�Ķ���
		//��ѯ���ݿ�
		List<String> validatorTypes = new ArrayList<String>();
		List<InsuranceValidator> validatorChain = new LinkedList<InsuranceValidator>();
		for(String type : validatorTypes){
			InsuranceValidator validator =  InsuranceValidatorFactory.getInstance().getInsuranceValidator(type);
			validatorChain.add(validator);
		}
		InsuranceVerifiable ins = new TXInsurance();
		ValidationContext context = new ValidationContext(ins,validatorChain,new Stack<ValidationResult>());
		return context; 
	}
}

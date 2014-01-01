package cn.com.sinosoft.ebusiness.service.insvalidator;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceRuleFactorService;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

/**
 * @author sizhou
 * Ͷ��������Ĭ�ϵĹ���У������ʹ��ģ�巽��
 */

@Component(value="defaultInsValidator")
public class DefaultInsuranceValidator implements InsuranceValidator {
	
	@Autowired
	private PortalInterfaceRuleFactorService portalInterfaceRuleFactorService;
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
		//����xmlȡ��functionflag source
		Element baseInfo = DocumentHelper.parseText(msg).getRootElement().element("BaseInfo");
		String functionFlag = baseInfo.elementText("FunctionFlag");
		String source = baseInfo.elementText("Source");
		//����xml�õ�����֤�Ķ���
		
		List<String> validatorTypes = portalInterfaceRuleFactorService.findPortalInterfaceRuleFactorVerificationProcessClass(functionFlag,source);
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

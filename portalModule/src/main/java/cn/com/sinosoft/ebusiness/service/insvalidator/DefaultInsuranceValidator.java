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
 * 投保流程中默认的规则校验器，使用模板方法
 */

@Component(value="defaultInsValidator")
public class DefaultInsuranceValidator implements InsuranceValidator {
	
	@Autowired
	private PortalInterfaceRuleFactorService portalInterfaceRuleFactorService;
	/* (non-Javadoc)
	 * @see insuretest.InsuranceValidation#validate(java.lang.Object)
	 * 接受一个校验规则链，此校验链从数据库查询，此接口方法充当模板方法
	 */
	
	@Override
	public ValidationContext validate(Object msg) throws Exception {
		
		//当前校验上下文
		ValidationContext context = createValidationContext((String)msg);
		//默认校验器不需要知道校验流程需要被什么校验器校验以及哪个方法校验过
		for(InsuranceValidator validator : context.getValidatorChain()){
			context = validator.validate(context);
			//短路校验或者非短路校验
			if(context.getValidationResults().peek().isSuccess()){
				break;
			}
		}
		return context;
	}
	
	public ValidationContext createValidationContext(String msg) throws Exception{
		//解析xml取得functionflag source
		Element baseInfo = DocumentHelper.parseText(msg).getRootElement().element("BaseInfo");
		String functionFlag = baseInfo.elementText("FunctionFlag");
		String source = baseInfo.elementText("Source");
		//解析xml得到待验证的对象。
		
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

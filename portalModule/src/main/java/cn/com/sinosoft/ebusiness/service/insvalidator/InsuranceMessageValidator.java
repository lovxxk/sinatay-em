package cn.com.sinosoft.ebusiness.service.insvalidator;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.interfacePortal.xml.factory.TXInsuranceFactory;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;


/**
 * @author sizhou
 * 投保报文完整性校验
 */
public class InsuranceMessageValidator implements InsuranceValidator{
    
	// 编组
	private TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();
		
	@Override
	public ValidationContext validate(Object ctx) throws Exception{
		// TODO Auto-generated method stub
		//在此处完成报文完整性校验，此处可以委托给多个更具体的同样实现InsuranceValidation关于节点合法性的校验器
		//如果此处校验不合法，校验上下文中投保可校验对象可以返回null。
		ValidationContext context = (ValidationContext)ctx;
		String msg = (String) context.getRelatedObj();
		Element baseInfo = DocumentHelper.parseText(msg).getRootElement().element("BaseInfo");
		String functionFlag = baseInfo.elementText("FunctionFlag");
		InsuranceVerifiable ins = (TXInsurance) txFactory.getConvertMessageService().unMarshall(msg,functionFlag,MessageType.REQUEST.getDataENName());
		context.setRelatedObj(ins);
		context.getValidationResults().push(new ValidationResult("1","报文合法！"));
		return context;
	}

}

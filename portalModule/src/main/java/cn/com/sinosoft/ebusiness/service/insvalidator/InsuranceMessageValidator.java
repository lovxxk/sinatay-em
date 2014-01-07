package cn.com.sinosoft.ebusiness.service.insvalidator;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.interfacePortal.xml.factory.TXInsuranceFactory;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;


/**
 * @author sizhou
 * Ͷ������������У��
 */
public class InsuranceMessageValidator implements InsuranceValidator{
    
	// ����
	private TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();
		
	@Override
	public ValidationContext validate(Object ctx) throws Exception{
		// TODO Auto-generated method stub
		//�ڴ˴���ɱ���������У�飬�˴�����ί�и�����������ͬ��ʵ��InsuranceValidation���ڽڵ�Ϸ��Ե�У����
		//����˴�У�鲻�Ϸ���У����������Ͷ����У�������Է���null��
		ValidationContext context = (ValidationContext)ctx;
		String msg = (String) context.getRelatedObj();
		Element baseInfo = DocumentHelper.parseText(msg).getRootElement().element("BaseInfo");
		String functionFlag = baseInfo.elementText("FunctionFlag");
		InsuranceVerifiable ins = (TXInsurance) txFactory.getConvertMessageService().unMarshall(msg,functionFlag,MessageType.REQUEST.getDataENName());
		context.setRelatedObj(ins);
		context.getValidationResults().push(new ValidationResult("1","���ĺϷ���"));
		return context;
	}

}

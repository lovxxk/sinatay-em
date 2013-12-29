package cn.com.sinosoft.ebusiness.service.insvalidator;

import org.springframework.beans.factory.annotation.Autowired;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.portalModule.transport.sinatay.LCInsured;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

public class MobileValidator implements InsuranceValidator {
	
	private static final String MOBILE_REGEX = "^13[0-9]{9}|15[0-9]{9}|18[0-9]{9}|147[0-9]{8}$";
	
	@Autowired
	private GeCodeService codeService;
	
	@Override
	public ValidationContext validate(Object ctx) throws Exception {
		//��ʱ��ʹ�ù�������չ
		ValidationContext context = (ValidationContext)ctx;
		
		TXInsurance ins = (TXInsurance)(context.getRelatedObj());
		LCCont lcCont = ((TranRequest)ins.getBusinessDatum().get(0)).getLcCont();
		
		//������nullptr
		boolean matchAppantMobi = lcCont.getLcAppnt().getMobilePhoneNumber().matches(MOBILE_REGEX);
		
		boolean matchInsuredsMobi = false;
		
		StringBuilder errorMobiMsg = new StringBuilder();
		//�������ֻ�У��
		if(!matchAppantMobi){
			errorMobiMsg.append(codeService.getCodeCoreRelation("", "validation"));
		}
		
		//Ͷ�����ֻ�У��
		for (LCInsured lcInsured : lcCont.getLcInsureds()) {
			if (!lcInsured.getMobilePhoneNumber().matches(MOBILE_REGEX)) {
				matchInsuredsMobi = false;
				errorMobiMsg.append(codeService.getCodeCoreRelation("", "validation"));
			}
		}
		if(!matchAppantMobi || !matchInsuredsMobi){
			context.getValidationResults().push(new ValidationResult("0",errorMobiMsg.toString()));
		}
		return context;
	}

}

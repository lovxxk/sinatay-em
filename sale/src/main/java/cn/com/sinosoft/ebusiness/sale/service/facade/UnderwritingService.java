package cn.com.sinosoft.ebusiness.sale.service.facade;

import java.util.Map;

import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.ebusiness.sale.domain.InsureFlowDto;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;

public interface UnderwritingService {
	
	public Map<String, Object> underwriting(InsurancePolicy insurancePolicy, String transType);
	
	public Map<String, Object> underwriting(InsureFlowDto insureFlowDto, InsurancePolicy insurancePolicy, GeUserPersonal geUserPersonal);
	
	public Map<String, Object> underwriting(InsureFlowDto insureFlowDto, InsurancePolicy insurancePolicy, GeUserPersonal geUserPersonal, String transType);
	
	public Map<String, Object> insured(InsurancePolicy insurancePolicy);
	
}

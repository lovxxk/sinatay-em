package cn.com.sinosoft.ebusiness.service.facade;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;

public interface InsuranceServiceFactory {
	public InsuranceService getInsuranceService(InsuranceVerifiable ins) throws Exception;
}

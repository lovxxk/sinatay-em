package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

import java.util.List;
/**
 * ��������
 *  
 *
 */
public class TXInsuranceXMLRequest extends TXInsuranceRequest{
	
	private List<Object> businessData;
	
	public List<Object> getBusinessData() {
		return businessData;
	}

	public void setBusinessData(List<Object> businessData) {
		this.businessData = businessData;
	}



}

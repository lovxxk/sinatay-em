package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

import java.util.List;

/**
 * ��Ӧ������
 *  
 *
 */
public class TXInsuranceXMLResponse extends TXInsuranceResponse{
	
	private List<Object> businessData;
	
	public List<Object> getBusinessData() {
		return businessData;
	}

	public void setBusinessData(List<Object> businessData) {
		this.businessData = businessData;
	}

}

package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.io.Serializable;
/**
 * ��DTO���ڲ�Ʒ����ӿڣ����ú��ĵ����
 *
 */
public class RiskInputCoreDto extends RequestBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**���ִ���**/
	private String riskCode;
	
	/**�������**/
	
	private String riskClassCode;
	
	//set and get method

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getRiskClassCode() {
		return riskClassCode;
	}

	public void setRiskClassCode(String riskClassCode) {
		this.riskClassCode = riskClassCode;
	}
}

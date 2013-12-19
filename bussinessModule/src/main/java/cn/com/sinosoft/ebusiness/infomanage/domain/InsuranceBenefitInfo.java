package cn.com.sinosoft.ebusiness.infomanage.domain;


public class InsuranceBenefitInfo extends InfoTable{
	/**
	 * @ProjectName:
	 * @Package:     
	 * @ClassName:   
	 * @Description: 保险利益
	 * @Author:      jack_xiao
	 * @CreateDate:  2013-9-17
	 * @UpdateUser: 
	 * @UpdateDate: 
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	//保单号
	private String policyNo;
	//保单险种号
	private String polNo;
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getPolNo() {
		return polNo;
	}
	public void setPolNo(String polNo) {
		this.polNo = polNo;
	}
	
	
	
}

package cn.com.sinosoft.ebusiness.infomanage.domain;

public class EmailInfo {
	/**
	 * @ProjectName:
	 * @Package:     
	 * @ClassName:   
	 * @Description: ������Ӻ�����Ϣ
	 * @Author:      jack_xiao
	 * @CreateDate:  2013-9-11
	 * @UpdateUser: 
	 * @UpdateDate: 
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	//������
	private String policyNo;
	//��������
	private String riskName;
	//����
	private String email;
	//���Ӻ����������� 01 ����֪ͨ�� 02 ֽ��֪ͨ�� 03 ���߽���  04 ��������
	private String subType;
	
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	
	
}

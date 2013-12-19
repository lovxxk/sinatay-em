package cn.com.sinosoft.ebusiness.infomanage.domain;

import java.io.Serializable;


public class Risk implements Comparable<Risk>,Serializable {
	/**
	 * @ProjectName: online
	 * @Package: cn.com.sinosoft.ebusiness.policy.domain
	 * @ClassName: Risk
	 * @Description: ������Ϣ
	 * @Author: jack_xiao
	 * @CreateDate: 2013-09-03
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	private static final long serialVersionUID = 443510694817792235L;
	//���ִ���
	private String riskCode;
	//��������
	private String riskName;
	//���ֱ�־��M ���� S ������ A���ߣ�
	private String subRiskFlag;
	//����
	private String amnt;
	//����
	private String prem;
	//�������������־ Ŀǰ����ֻ�У�Y��  A��
	private String insuYearFlag;
	//������������
	private String insuYear;
	//������Ч����
	private String cvaliDate;
	//��������״̬
	private String state;
	//�������ֺ���
	private String PolNo;
	//������
	private String policyNo;
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	public String getSubRiskFlag() {
		return subRiskFlag;
	}
	public void setSubRiskFlag(String subRiskFlag) {
		this.subRiskFlag = subRiskFlag;
	}
	
	public String getAmnt() {
		return amnt;
	}
	public void setAmnt(String amnt) {
		this.amnt = amnt;
	}
	public String getPrem() {
		return prem;
	}
	public void setPrem(String prem) {
		this.prem = prem;
	}
	public String getInsuYearFlag() {
		return insuYearFlag;
	}
	public void setInsuYearFlag(String insuYearFlag) {
		this.insuYearFlag = insuYearFlag;
	}
	
	
	public String getInsuYear() {
		return insuYear;
	}
	public void setInsuYear(String insuYear) {
		this.insuYear = insuYear;
	}

	public String getCvaliDate() {
		return cvaliDate;
	}
	public void setCvaliDate(String cvaliDate) {
		this.cvaliDate = cvaliDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getPolNo() {
		return PolNo;
	}
	public void setPolNo(String polNo) {
		PolNo = polNo;
	}
	
	
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	//ʵ�ְ�����������
	@Override
	public int compareTo(Risk o) {
		if(!o.getCvaliDate().equals(this.getCvaliDate())){
			return o.getCvaliDate().compareTo(this.getCvaliDate());
		}else{
			//����������
			return this.getSubRiskFlag().compareTo(o.getSubRiskFlag());
		}
	}
}

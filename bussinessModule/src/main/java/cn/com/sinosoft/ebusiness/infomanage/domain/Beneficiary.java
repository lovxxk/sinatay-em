package cn.com.sinosoft.ebusiness.infomanage.domain;

import java.io.Serializable;

public class Beneficiary extends PersonInfo implements Comparable<Beneficiary>,Serializable{
	/**
	 * @ProjectName: online
	 * @Package:     cn.com.sinosoft.ebusiness.infomanage.domain
	 * @ClassName:   Beneficiary
	 * @Description: ��������Ϣ
	 * @Author:      jack_xiao
	 * @CreateDate:  2013-09-04
	 * @UpdateUser: 
	 * @UpdateDate: 
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	private static final long serialVersionUID = -8053877488393163079L;
	/** �������������� */
	private Integer beneficiaryType;

	/** �����뱻�����˹�ϵ */
	private Integer relatedToInsured;
	
	/** ����������˳�� */
	private String beneficiaryOrder;

	/** ��������ݶ� */
	private String interestPercent;

	public Integer getBeneficiaryType() {
		return beneficiaryType;
	}

	public void setBeneficiaryType(Integer beneficiaryType) {
		this.beneficiaryType = beneficiaryType;
	}

	public Integer getRelatedToInsured() {
		return relatedToInsured;
	}

	public void setRelatedToInsured(Integer relatedToInsured) {
		this.relatedToInsured = relatedToInsured;
	}


	public String getBeneficiaryOrder() {
		return beneficiaryOrder;
	}

	public void setBeneficiaryOrder(String beneficiaryOrder) {
		this.beneficiaryOrder = beneficiaryOrder;
	}

	public String getInterestPercent() {
		return interestPercent;
	}

	public void setInterestPercent(String interestPercent) {
		this.interestPercent = interestPercent;
	}

	@Override
	public int compareTo(Beneficiary o) {
		return this.getBeneficiaryOrder().compareTo(o.getBeneficiaryOrder());
	}


}

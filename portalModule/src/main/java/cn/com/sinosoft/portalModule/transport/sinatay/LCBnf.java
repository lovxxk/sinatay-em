package cn.com.sinosoft.portalModule.transport.sinatay;

import java.math.BigDecimal;

/**
 * ������DTO
 *
 */
public class LCBnf extends PartyRolePolicyDTO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	/** ��������� CD29 Ĭ������������ */
	private String bnfType;

	/** �����˼��� */
	private String bnfGrade;

	/** �뱻���˹�ϵ CD17 */
	private String relationToInsured;

	/** ����ٷ��� С����ͬһ����ϼ�Ϊ1 */
	private BigDecimal bnfLot;

	/**
	 * ���� bnfType �� getter ����
	 * @return the bnfType
	 */
	public String getBnfType() {
		return bnfType;
	}

	/**
	 * ���� bnfType �� setter ����
	 * @param bnfType the bnfType to set
	 */
	public void setBnfType(String bnfType) {
		this.bnfType = bnfType;
	}

	/**
	 * ���� bnfGrade �� getter ����
	 * @return the bnfGrade
	 */
	public String getBnfGrade() {
		return bnfGrade;
	}

	/**
	 * ���� bnfGrade �� setter ����
	 * @param bnfGrade the bnfGrade to set
	 */
	public void setBnfGrade(String bnfGrade) {
		this.bnfGrade = bnfGrade;
	}

	/**
	 * ���� relationToInsured �� getter ����
	 * @return the relationToInsured
	 */
	public String getRelationToInsured() {
		return relationToInsured;
	}

	/**
	 * ���� relationToInsured �� setter ����
	 * @param relationToInsured the relationToInsured to set
	 */
	public void setRelationToInsured(String relationToInsured) {
		this.relationToInsured = relationToInsured;
	}

	/**
	 * ���� bnfLot �� getter ����
	 * @return the bnfLot
	 */
	public BigDecimal getBnfLot() {
		return bnfLot;
	}

	/**
	 * ���� bnfLot �� setter ����
	 * @param bnfLot the bnfLot to set
	 */
	public void setBnfLot(BigDecimal bnfLot) {
		this.bnfLot = bnfLot;
	}
	
}

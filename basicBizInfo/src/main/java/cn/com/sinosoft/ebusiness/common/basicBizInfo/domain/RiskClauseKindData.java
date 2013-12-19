package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.io.Serializable;

public class RiskClauseKindData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**���ִ���**/
	private String riskCode;
	
	/**��������**/
	private String riskName;
	
	/**��������**/
	private String classCode;
	
	/**�������**/
	private String clauseCode;
	
	/**��������**/
	private String clauseName;
	
	/**�ձ����**/
	private String kindCode;
	
	/**�ձ�����**/
	private String kindName;
	
	/**�Ƿ�����ܱ���
	 * 1���� 0������
	 * */
	private String isSum;
	
	/**���������ձ�**/
	private String relateKindCode;
	
	/**���ո����ձ�ʶ
	 * 01-���գ�02-������
	 * **/
	private String kindFlag;
	
	/**���������ʶ
	 * 0:����Ͷ���������⣻1:����Ͷ����������
	 * **/
	private String noDeductFlag;
	
	//get and set method
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

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClauseCode() {
		return clauseCode;
	}

	public void setClauseCode(String clauseCode) {
		this.clauseCode = clauseCode;
	}

	public String getClauseName() {
		return clauseName;
	}

	public void setClauseName(String clauseName) {
		this.clauseName = clauseName;
	}

	public String getKindCode() {
		return kindCode;
	}

	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getIsSum() {
		return isSum;
	}

	public void setIsSum(String isSum) {
		this.isSum = isSum;
	}

	public String getRelateKindCode() {
		return relateKindCode;
	}

	public void setRelateKindCode(String relateKindCode) {
		this.relateKindCode = relateKindCode;
	}

	public String getKindFlag() {
		return kindFlag;
	}

	public void setKindFlag(String kindFlag) {
		this.kindFlag = kindFlag;
	}

	public String getNoDeductFlag() {
		return noDeductFlag;
	}

	public void setNoDeductFlag(String noDeductFlag) {
		this.noDeductFlag = noDeductFlag;
	}
	
}

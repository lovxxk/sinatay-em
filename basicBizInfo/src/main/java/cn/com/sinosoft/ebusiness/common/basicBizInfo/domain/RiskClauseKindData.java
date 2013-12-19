package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.io.Serializable;

public class RiskClauseKindData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**险种代码**/
	private String riskCode;
	
	/**险种名称**/
	private String riskName;
	
	/**险类名称**/
	private String classCode;
	
	/**条款代码**/
	private String clauseCode;
	
	/**条款名称**/
	private String clauseName;
	
	/**险别代码**/
	private String kindCode;
	
	/**险别名称**/
	private String kindName;
	
	/**是否计入总保额
	 * 1计入 0不计入
	 * */
	private String isSum;
	
	/**关联主险险别**/
	private String relateKindCode;
	
	/**主险附加险标识
	 * 01-主险；02-附加险
	 * **/
	private String kindFlag;
	
	/**不计免赔标识
	 * 0:不能投保不计免赔；1:可以投保不计免赔
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

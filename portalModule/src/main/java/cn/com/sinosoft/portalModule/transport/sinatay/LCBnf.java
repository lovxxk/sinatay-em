package cn.com.sinosoft.portalModule.transport.sinatay;

import java.math.BigDecimal;

/**
 * 受益人DTO
 *
 */
public class LCBnf extends PartyRolePolicyDTO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	/** 受益人类别 CD29 默认死亡受益人 */
	private String bnfType;

	/** 受益人级别 */
	private String bnfGrade;

	/** 与被保人关系 CD17 */
	private String relationToInsured;

	/** 受益百分数 小数，同一级别合计为1 */
	private BigDecimal bnfLot;

	/**
	 * 属性 bnfType 的 getter 方法
	 * @return the bnfType
	 */
	public String getBnfType() {
		return bnfType;
	}

	/**
	 * 属性 bnfType 的 setter 方法
	 * @param bnfType the bnfType to set
	 */
	public void setBnfType(String bnfType) {
		this.bnfType = bnfType;
	}

	/**
	 * 属性 bnfGrade 的 getter 方法
	 * @return the bnfGrade
	 */
	public String getBnfGrade() {
		return bnfGrade;
	}

	/**
	 * 属性 bnfGrade 的 setter 方法
	 * @param bnfGrade the bnfGrade to set
	 */
	public void setBnfGrade(String bnfGrade) {
		this.bnfGrade = bnfGrade;
	}

	/**
	 * 属性 relationToInsured 的 getter 方法
	 * @return the relationToInsured
	 */
	public String getRelationToInsured() {
		return relationToInsured;
	}

	/**
	 * 属性 relationToInsured 的 setter 方法
	 * @param relationToInsured the relationToInsured to set
	 */
	public void setRelationToInsured(String relationToInsured) {
		this.relationToInsured = relationToInsured;
	}

	/**
	 * 属性 bnfLot 的 getter 方法
	 * @return the bnfLot
	 */
	public BigDecimal getBnfLot() {
		return bnfLot;
	}

	/**
	 * 属性 bnfLot 的 setter 方法
	 * @param bnfLot the bnfLot to set
	 */
	public void setBnfLot(BigDecimal bnfLot) {
		this.bnfLot = bnfLot;
	}
	
}
